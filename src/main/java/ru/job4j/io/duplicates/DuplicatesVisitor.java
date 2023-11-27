package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final HashMap<FileProperty, List<Path>> files = new HashMap<>();
    private final List<Path> paths = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(filePath.toFile().length(), filePath.toFile().getName());
        if (files.containsKey(fileProperty)) {
            files.get(fileProperty).add(filePath);
        } else {
            files.put(fileProperty, paths);
            paths.add(filePath);
        }
        return super.visitFile(filePath, attrs);
    }

    public List<FileProperty> getDuplicates() {
        return files.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}