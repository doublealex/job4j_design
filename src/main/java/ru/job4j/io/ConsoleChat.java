package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        boolean isActive = true;

        while (isRunning) {
            String userIn = input.nextLine();
            log.add("User: " + userIn + "\n");

            if (userIn.equalsIgnoreCase(STOP)) {
                isActive = false;
            }
            if (userIn.equalsIgnoreCase(CONTINUE)) {
                isActive = true;
            }
            if (userIn.equalsIgnoreCase(OUT)) {
                isActive = false;
                isRunning = false;
            }

            if (isActive) {
                Random random = new Random();
                String answer = readPhrases(botAnswers).get(random.nextInt(readPhrases(botAnswers).size()));
                System.out.println(answer);
                log.add("ChatBot: " + answer + "\n");
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases(String botAnswers) {
        List<String> phrases = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(botAnswers));
            String line = reader.readLine();
            while (line != null) {
                phrases.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::write);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/consoleChatLog.txt", "data/consoleChatBotAnswers.txt");
        consoleChat.run();
    }
}
