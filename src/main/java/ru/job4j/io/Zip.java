package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean validate(ArgsName argsName) {
        if (!Files.exists(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException("Указанный путь не существует");
        }
        if (!Files.isDirectory(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException("Указанный путь не является каталогом");
        }
        if (!argsName.get("e").startsWith(".") && argsName.get("e").length() <= 1) {
            throw new IllegalArgumentException("Указано несуществующее расширение файла");
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Указано неверное расширение архива");
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        if (args.length != 3) {
            throw new IllegalArgumentException("При запуске утилиты необходимо указать 3 параметра.");
        }
        ArgsName argsName = ArgsName.of(args);
        if (validate(argsName)) {
            Path start = Paths.get(argsName.get("d"));
            zip.packFiles(Search.search(start, p -> !p.toFile().getName().
                            endsWith(argsName.get("e"))),
                    new File(argsName.get("o")));
        }
    }
}