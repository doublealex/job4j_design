package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(line -> !line.trim().isEmpty() && !line.trim().startsWith("#"))
                    .filter(this::validate)
                    .forEach(line -> {
                        String[] el = line.split("=", 2);
                        values.put(el[0], el[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validate(String line) {
        if (line.startsWith("=") || !line.contains("=")) {
            throw new IllegalArgumentException("Неверный формат ключа");
        }
        if (line.indexOf("=") == line.length() - 1) {
            throw new IllegalArgumentException("Неверный формат значения");
        }
        return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}