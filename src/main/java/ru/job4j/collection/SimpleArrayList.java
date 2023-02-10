package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Метод добавляет значение в список. Проводится проверка на входе "ёмкости" списка (capacity) и при необходимости
     * происходит расширение через вызов метод grow(), затем помещает значение в ячейку, индекс которой соответсвует
     * размеру списка (size). Счетчик изменений (modCount) и размер списка (size) инкрементируются.
     * @param value - значение, которое помещаем в список.
     */
    @Override
    public void add(T value) {
        if (container.length == 0 || size == container.length - 1) {
            grow();
        }
        container[size] = value;
        modCount++;
        size++;
    }

    /**
     * Метод расширения ёмкости списка (capacity). Увеличивает capacity в два раза, либо до 1 (если capacity == 0).
     * Расширение происходит за счет Arrays.copyOf.
     */
    private void grow() {
        int newCapacity = 1;
        if (container.length > 0) {
            newCapacity = container.length * 2;
        }
        container = Arrays.copyOf(container, newCapacity);
    }

    /**
     * Метод позволяет "положить" новое значение в нужную ячейку.
     * Сохраняем предыдущее значение в переменную,вызывая метод get(int index), который также проверяет входящий индекс.
     * @param index - номер яйчеки, в которую хотим положить значение.
     * @param newValue - новое значение.
     * @return - возвращаем предыдущее значение oldValue.
     */
    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    /**
     * Метод удаления значения в ячейке.Сохраняем удаляемое значение в переменную, с помощью System.arraycopy
     * перекопируем элементы списка, перемещая "удаленное" в конец списка, затем обнуляем последнюю ячейку
     * и уменьшаем размер списка (поскольку произашло удаления объекта из списка).
     * @param index - номер яйчеки, в которой хотим удалить значение.
     * @return - возвращаем предыдущее значение oldValue (удаленное значение).
     */
    @Override
    public T remove(int index) {
        final int newSize = size - 1;
        T oldValue = get(index);
        System.arraycopy(container, index + 1, container, index, newSize - index);
        container[size - 1] = null;
        modCount++;
        size--;
        return oldValue;
    }

    /**
     * Метод проверяет индекс (index < 0 || index >= array.length) и возвращает значение по индексу.
     * @param index - номер ячейки возвращаемого элемента.
     * @return - возвращает значение в ячейке по индексу.
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    /**
     * @return - возвращает текущий размер списка.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * fail-fast iterator, т.е. если с момента создания итератора в коллекцию добавили или удалили элемент,
     * итератор кидает ConcurrentModificationException с помощью счетчика изменений - переменной modCount.
     * Если операция структурно модифицирует коллекцию, то значение modCount увеличивается.
     * Итератор на момент своего создания запоминает его значение - expectedModCount и на каждой итерации сравнивает его
     * с текущим значением modCount и если они отличаются, то генерируется исключение.
     */
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
                return index < size();
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