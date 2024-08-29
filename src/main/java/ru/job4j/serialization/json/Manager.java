package ru.job4j.serialization.json;

public class Manager {
    private final String name;

    public Manager(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "manager: {" + "name='" + name + '\'' + '}';
    }
}
