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
        final Node<E> n = head;
        Node<E> lastNode = new Node<E>(value, n);
        if (n == null) {
            head = lastNode;
            } else {
            head.next = lastNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> el = head;
        for (int i = 0; i < index; i++) {
            el = getHead(el);
        }
        return el.item;
    }

    private Node<E> getHead(Node<E> el) {
        return el.next;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> el;
            int index = 0;
            final int expectedCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                    return get(index++);
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