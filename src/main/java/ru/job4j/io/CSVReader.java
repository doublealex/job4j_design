package ru.job4j.io;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;

// -path=file.csv -delimiter=; -out=stdout -filter=name,age

public class CSVReader {
    private static final String PATH = "path";
    private static final String DELIMITER = "delimiter";
    private static final String OUT = "out";
    private static final String FILTER = "filter";

    public static void handle(ArgsName argsName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(argsName.get("out")))) {
            if ("stdout".equals(argsName.get("out"))) {
                List<String> filteredData = filterCSV(argsName);
                for (String line : filteredData) {
                    System.out.println(line);
                }
            } else {
                writer.write((filterCSV(argsName) + System.lineSeparator()));
            }
        } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static List<String> filterCSV(ArgsName argsName) {
        List<String> filteredValues = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(argsName.get("path")))) {
            String[] filters = argsName.get("filter").split(",");
            String header  = reader.readLine();
            Scanner headerScanner = new Scanner((new ByteArrayInputStream(header.getBytes())))
                    .useDelimiter(argsName.get("delimiter"));

            Map<String, Integer> headerIndexMap = new HashMap<>();
            int index = 0;
            // Считываем заголовки
            while (headerScanner.hasNext()) {
                String head = headerScanner.next();
                headerIndexMap.put(head, index++);
            }
            // Чтение и вывод данных, соответствующих заголовкам из filter
            String line;
            while ((line = reader.readLine()) != null) {
                Scanner lineScanner = new Scanner((new ByteArrayInputStream(line.getBytes())))
                        .useDelimiter(argsName.get("delimiter"));
                String[] values = new String[headerIndexMap.size()];
                int colIndex = 0;
                while (lineScanner.hasNext()) {
                    values[colIndex++] = lineScanner.next();
                }

                // Собираем те значения, которые соответствуют фильтрам
                for (String filter : filters) {
                    int columnIndex = headerIndexMap.get(filter);
                    if (columnIndex < values.length) {
                        filteredValues.add(values[columnIndex]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filteredValues;
    }

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
        /* здесь добавьте валидацию принятых параметров*/
        ArgsName argsName = ArgsName.of(args);
        validateArgsForReader(argsName);
        handle(argsName);
    }
}
