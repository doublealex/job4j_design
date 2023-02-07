package ru.job4j.generic;

public class Role extends Base {

    private final String role;

    public Role(String id, String name) {
        super(id);
        this.role = name;
    }

    public String getRole() {
        return role;
    }
}