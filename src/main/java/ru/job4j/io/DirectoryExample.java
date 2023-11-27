package ru.job4j.io;

import java.io.File;
import java.io.IOException;

public class DirectoryExample {
    public static void main(String[] args) throws IOException {
        File dir = new File("src/main/java/ru/job4j/io/files/directory");
        dir.mkdirs();
        File target = new File("src/main/java/ru/job4j/io/files");
        File file1 = new File("src/main/java/ru/job4j/io/files/file1.txt");
        file1.createNewFile();
        File file2 = new File("src/main/java/ru/job4j/io/files/directory/file2.txt");
        file2.createNewFile();
        String[] list = target.list();
        for (String f : list) {
            System.out.println(f);
        }
        File[] listFiles = target.listFiles();
        for (File f : listFiles) {
            System.out.println(f);
        }
    }
}
