package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count == table.length * LOAD_FACTOR) {
            expand();
        }
        int index = indexKey(key);
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private int indexKey(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean compareKeys(K key1, K key2) {
        return Objects.hashCode(key1) == Objects.hashCode(key2)
                && Objects.equals(key1, key2);
    }

    private void expand() {
            capacity = capacity * 2;
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
            for (MapEntry<K, V> entry : table) {
                if (entry != null) {
                    newTable[indexKey(entry.key)] = entry;
                }
            }
            table = newTable;
    }

    @Override
    public V get(K key) {
        int index = indexKey(key);
        V val = null;
        if (table[index] != null && compareKeys(table[index].key, key)) {
                val = table[index].value;
            }
            return val;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexKey(key);
        if (table[index] != null && compareKeys(table[index].key, key)) {
            table[index] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
