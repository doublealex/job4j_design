package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[]{"Worker", "Married"});
        final Team team = new Team("Arsenal", true, 1886, new Manager("Mikel Arteta"),
                new String[]{"vice-champion", "points:89"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Создаём новую json-строку с модифицированными данными*/
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        /* Превращаем json-строку обратно в объект */
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);

        final Gson gson2 = new GsonBuilder().create();
        String stringTeam = gson2.toJson(team);
        System.out.println(stringTeam);

        final Team teamFromJson = gson2.fromJson(stringTeam, Team.class);
        System.out.println(teamFromJson);
    }
}
