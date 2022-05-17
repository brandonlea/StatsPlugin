package dev.sha256.stats.manager;

import com.mongodb.*;
import com.mongodb.client.model.Filters;
import dev.sha256.stats.Stats;
import dev.sha256.stats.user.StatsUser;
import dev.sha256.stats.user.Types;
import org.bson.Document;
import org.bukkit.entity.Player;


public class DatabaseManager {


    private final MongoClient client;
    private final DB db;
    private final DBCollection collection;


    public DatabaseManager(Stats stats) {
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append("mongodb://");
        uriBuilder.append(stats.getSettings().getConfiguration().getString("Settings.database.host"));
        uriBuilder.append(":");
        uriBuilder.append(stats.getSettings().getConfiguration().getInt("Settings.database.port"));
        String uri = uriBuilder.toString();
        client = new MongoClient(new MongoClientURI(uri));
        this.db = client.getDB("stats");
        this.collection = db.getCollection("players");
    }


    public void insertUser(StatsUser user) {
        DBObject dbObject = new BasicDBObject("uuid", user.getUUID().toString());
        insertStatsData(dbObject, user);
        collection.insert(dbObject);
    }

    public void saveUser(StatsUser user) {
        DBObject r = new BasicDBObject("uuid", user.getUUID().toString());

        DBObject found = collection.findOne(r);

        if(found == null) {
            insertUser(user);
            return;
        }

        DBObject obj = new BasicDBObject("uuid", user.getUUID().toString());
        insertStatsData(obj, user);
        collection.update(found, obj);
    }


    public void loadStats(StatsUser user) {
        DBObject r = new BasicDBObject("uuid", user.getUUID().toString());

        DBObject found = collection.findOne(r);

        if(found == null) {
            insertUser(user);
            return;
        }

        for(Types key : Types.values()) {
            user.addStat(key.getId(), (Integer) found.get(key.getId()));
        }
    }

    public void insertStatsData(DBObject dbObject, StatsUser user) {
        for(Types key : Types.values()) {
            if(user.getStat(key.getId()) == null) {
                dbObject.put(key.getId(),0);
            } else {
                dbObject.put(key.getId(), user.getStat(key.getId()));
            }
        }
    }

}
