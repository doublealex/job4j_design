package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 1. Создаем HashMap с ключами равными по значению id пользователей previous Set
 * 2. через цикл добавляем все элементы previous Set в map
 * 3. Далее через цикл сравниваем все элементы current Set с элементами map:
 * 3.1. Если в map (где находятся элементы previous) нет ключа как в current => элемент был добавлен,
 * 3.2. Если такой ключ есть, но нет такого элемента (т.е. User не равны) => он был изменен (id тот же, name изменен)
 * 3.3. Удаляем все элементы по ключам current => те, что остались в map и были удалены из previous,
 * 3.4. присваеваем их количество в переменную
 * 4. возвращаем созданный счетчик Info с переменными
 */

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted;

        HashMap<Integer, User> map = new HashMap<>();
        for (var prev : previous) {
            map.put(prev.getId(), prev);
        }

        for (var cur : current) {
            if (!map.containsKey(cur.getId())) {
                added++;
            }
            if (map.containsKey(cur.getId()) && !map.containsValue(cur)) {
                changed++;
            }
            map.remove(cur.getId());
        }
        deleted = map.size();

        return new Info(added, changed, deleted);
    }
}