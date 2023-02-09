package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = grow();
            container[size] = value;
        }
        modCount++;
        size++;
    }

    private T[] grow() {
        int newCapacity = 1;
        if (container.length > 0) {
            newCapacity = container.length * 2;
        }
        container = Arrays.copyOf(container, newCapacity);
        return container;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        final int newSize = size - 1;
        T oldValue = get(index);
        if (newSize > index) {
            System.arraycopy(container, index + 1, container, index, newSize - index - 1);
            container[size - 1] = null;
        }
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            final int expectedCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedCount) {
                    throw new ConcurrentModificationException();
                }
                return index < container.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }

        };
    }
}