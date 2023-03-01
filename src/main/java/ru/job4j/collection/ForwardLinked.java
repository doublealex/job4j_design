package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        Node<T> previous = head;
        Node<T> lastNode = new Node<T>(value, null);
        if (head == null) {
            head = lastNode;
        } else {
            while (previous.next != null) {
                previous = previous.next;
            }
            previous.next = lastNode;
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> el = head;
        for (int i = 0; i < index; i++) {
            el = el.next;
        }
        return el.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T el = head.item;
        Node<T> f = head.next;
        head.item = null;
        head.next = null;
        head = f;
        size--;
        modCount++;
        return el;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> el = head;
            final int expectedCount = modCount;

            public boolean hasNext() {
                if (modCount != expectedCount) {
                    throw new ConcurrentModificationException();
                }
                return el != null;
            }

            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T res = el.item;
                el = el.next;
                return res;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}