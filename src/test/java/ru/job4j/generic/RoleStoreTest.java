package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFind() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Lalala"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Lalala");
    }

    @Test
    void whenAddGoesNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Lalala"));
        Role result = store.findById("8");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicate() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Lalala"));
        store.add(new Role("1", "Mam"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Lalala");
    }

    @Test
    void whenIsEmptyThenNoReplace() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Lalala"));
        store.replace("2", new Role("2", "Mam"));
        Role result = store.findById("2");
        assertThat(result).isNull();
    }

    @Test
    void whenDeleteRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Lalala"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoReplaceThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Lalala"));
        boolean rsl = store.replace("2", new Role("2", "Mam"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDelete() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Lalala"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }
}