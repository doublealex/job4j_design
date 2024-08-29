package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainJSONtoPOJO {
    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Person person = new Person(false, 30, new Contact("11-111"), new String[]{"Worker", "Married"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.getSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person));

        JSONObject jsonManager = new JSONObject("{\"name\":\"someone\"}");

        List<String> stats = new ArrayList<>();
        stats.add("spurs");
        stats.add("games: 38");
        JSONArray jsonStatistics = new JSONArray(stats);

        final Team team = new Team("Arsenal", true, 1886, new Manager("Arteta"),
                new String[]{"vice-champion", "points: 89"});
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "Tottenham");
        jsonObject1.put("exist", team.isExist());
        jsonObject1.put("founded", 1882);
        jsonObject1.put("manager", jsonManager);
        jsonObject1.put("statistics", jsonStatistics);

        System.out.println(jsonObject1);
        System.out.println(new JSONObject(team));
    }
}
