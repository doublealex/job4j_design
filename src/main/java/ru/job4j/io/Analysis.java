package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean server = false;
        try (BufferedReader read = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                String[] elements = line.split(" ");
                if (!server && (line.contains("400") || line.contains("500"))) {
                    server = true;
                    out.write(String.format("%s; ", elements[1]));
                } else if (server && (line.contains("200") || line.contains("300"))) {
                    server = false;
                    out.write(String.format("%s;", elements[1]));
                    out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}