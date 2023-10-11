package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.listIterator(index).add(value);
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.listIterator(index + 1).add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        if (filter.test(iterator.next())) {
            iterator.remove();
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        if (filter.test(iterator.next())) {
            iterator.set(value);
        }
    }

    /**
     * Метод удаляет из списка list те элементы, которые содержатся в списке elements
     * Вызываем у list метод removeIf с указанием в параметрах ссылки на метод: "удалить, если содержится в elements"
     * @param list - редактируемый список
     * @param elements - список элементов, которые нужно удалить из списка list
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
            list.removeIf(elements::contains);
    }
}
