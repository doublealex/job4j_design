package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> previous = head;
        Node<E> lastNode = new Node<E>(value, null);
        if (previous == null) {
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

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> el = head;
        for (int i = 0; i < index; i++) {
            el = el.next;
        }
        return el.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> el;
            final int expectedCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedCount) {
                    throw new ConcurrentModificationException();
                }
                el = head;
                while (el.next != null) {
                   el = el.next;
            }
                return false;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return el.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}