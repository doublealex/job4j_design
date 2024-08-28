package ru.job4j.serialization.json;

import java.util.Arrays;

public class Team {
    private final String name;
    private final boolean exist;
    private final int founded;
    private final Manager manager;
    private final String[] statistics;

    public Team(String name, boolean exist, int founded, Manager manager, String[] statistics) {
        this.name = name;
        this.exist = exist;
        this.founded = founded;
        this.manager = manager;
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "Team{"
                + "name='"
                + name
                + '\''
                + ", exist="
                + exist
                + ", founded="
                + founded
                + ", manager="
                + manager
                + ", statistics="
                + Arrays.toString(statistics)
                + '}';
    }
}
