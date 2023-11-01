package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("data/log.txt"))) {
            rsl = in.lines()
                    .map(s -> s.split(" "))
                    .filter(s -> ("404").equals(s[s.length - 2]))
                    .map(s -> String.join(" ", s))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)))) {
                data.forEach(writer::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}