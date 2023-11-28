package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Must be 2 arguments.");
        }
        Path start = Paths.get(args[0]);
        if (!Files.isDirectory(start)) {
            throw new IllegalArgumentException("First argument must be a folder.Root folder does not exist");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Second argument must be file extension and starts with dot.");
        }
    }
}