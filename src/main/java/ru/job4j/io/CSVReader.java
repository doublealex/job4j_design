package ru.job4j.io;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;

/**
 *  Для корректной работы обязательно ввести следующие параметры запуска:
 *  путь к CSV файлу; разделитель; вывод(консоль, либо запись в другой файл); фильтр(заголовки столбцов).
 *  Пример:
 *  -path=file.csv -delimiter=; -out=stdout -filter=name,age
 *  Если фильтрация не нужна, необходимо ввести заголовки всех столбцов.
 */

public class CSVReader {
    private static final String PATH = "path";
    private static final String DELIMITER = "delimiter";
    private static final String OUT = "out";
    private static final String FILTER = "filter";

    /** Метод получает список из метода filterCSV и по строкам выводит его в консоль, либо записывает в файл*/
    public static void handle(ArgsName argsName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(argsName.get("out")))) {
            List<String> filteredData = filterCSV(argsName);

            if ("stdout".equals(argsName.get("out"))) {
                for (String line : filteredData) {
                    System.out.println(line);
                }
            } else {
                for (String line : filteredData) {
                    writer.write(line + System.lineSeparator());
                }
            }
        } catch (IOException e) {
                e.printStackTrace();
            }
        }

    /** Метод фильтрует данные из CSV файла в соответствии с параметром filter и возвращает отфильтрованный список */
    public static List<String> filterCSV(ArgsName argsName) {
        List<String> filtered = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(argsName.get("path")))) {
            /* Читаем первую строку из CSV файла и создаем Map с названием всех заголовков */
            String header  = reader.readLine();
            Scanner headerScanner = new Scanner((new ByteArrayInputStream(header.getBytes())))
                    .useDelimiter(argsName.get("delimiter"));
            Map<String, Integer> headerIndexMap = new HashMap<>();
            int index = 0;
            while (headerScanner.hasNext()) {
                String head = headerScanner.next();
                headerIndexMap.put(head, index++);
            }
            /* Добавляем заголовки переданные в filter в список filtered в формате, как они представлены в CSV файле */
            filtered.add(argsName.get("filter").replace(",", argsName.get("delimiter")));
            /* Читаем строки CSV файла, записываем в массив values значения из строк CSV файла */
            String line;
            while ((line = reader.readLine()) != null) {
                Scanner lineScanner = new Scanner((new ByteArrayInputStream(line.getBytes())))
                        .useDelimiter(argsName.get("delimiter"));
                String[] values = new String[headerIndexMap.size()];
                int colIndex = 0;
                while (lineScanner.hasNext()) {
                    values[colIndex++] = lineScanner.next();
                }
                /* Добавляем из массива values в промежуточный список selected данные, соответствующие параметру filter,
                 затем добавляем selected в список filtered в нужным разделителем */
                String[] filters = argsName.get("filter").split(",");
                List<String> selected = new ArrayList<>();
                for (String filter : filters) {
                    Integer columnIndex = headerIndexMap.get(filter);
                    if (columnIndex != null && columnIndex < values.length) {
                        selected.add(values[columnIndex]);
                    }
                }
                filtered.add(String.join(argsName.get("delimiter"), selected));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    /** Метод валидации аргументов(параметров), заданных при запуске класса CSVReader */
    private static void validateArgsForReader(ArgsName argsName) {
        String path = argsName.get(PATH);
        if (path == null) {
            throw new IllegalArgumentException(String.format("argument needed: %s", PATH));
        }

        String delimiter = argsName.get(DELIMITER);
        if (delimiter == null) {
            throw
                    new IllegalArgumentException(String.format("argument needed: %s", DELIMITER));
        }
        String filter = argsName.get(FILTER);
        if (filter == null) {
            throw new IllegalArgumentException(String.format("argument needed: %s", FILTER));
        }

        String out = argsName.get("out");
        if (out == null) {
            throw new IllegalArgumentException(String.format("argument needed: %s", OUT));
        }
        if (!"stdout".equals(out)) {
            try {
                Path.of(out);
            } catch (InvalidPathException e) {
                throw new InvalidPathException("Should declare valid path", e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        validateArgsForReader(argsName);
        handle(argsName);
    }
}
