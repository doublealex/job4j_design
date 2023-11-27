package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size C: %s Gb", file.getTotalSpace() / Math.pow(1024, 3)));
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            System.out.println(subfile.getName() + String.format(" %s Kb", subfile.length() / 1024));
        }
    }
}