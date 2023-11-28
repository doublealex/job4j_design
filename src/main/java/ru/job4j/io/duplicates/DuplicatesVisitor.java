package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), filePath.getFileName().toString());
        files.computeIfAbsent(fileProperty, k -> new ArrayList<>()).add(filePath);
        return super.visitFile(filePath, attrs);
    }

    public void getDuplicates() {
        files.entrySet().stream()
                .filter(map -> map.getValue().size() > 1)
                .forEach(System.out::println);
    }
        /*
        for (Map.Entry<FileProperty, List<Path>> duplicates : files.entrySet()) {
            if (duplicates.getValue().size() > 1) {
                System.out.println(duplicates.getKey().toString());
                System.out.println("Paths:");
                for (Path path : duplicates.getValue()) {
                    System.out.println(path);
                }
            }
            */
}