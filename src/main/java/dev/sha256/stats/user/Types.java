package dev.sha256.stats.user;

public enum Types {

    BLOCK_BREAKING("block_breaking"),
    BLOCK_PLACE("block_place"),
    PASSIVE_MOB_KILLING("passive_mob_killing"),
    KILLING_OFFENSIVE_CREATURE("killing_offensive_creature"),
    OBTAINING_ORE("obtaining_ore"),
    PLAYERS_KILLED("players_killed"),
    AMOUNT_KILLED("amount_killed");


    private final String id;

    Types(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
