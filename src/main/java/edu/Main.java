package edu;

import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.Utility.FileManager;
import edu.commands.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Начинаю работу");

        String filePath = System.getenv("LABA_PROGA_1");
        System.out.println(filePath);


        // костыль для убрания фактора переменной окружения
        filePath = "/home/sofia/Документы/input_file.json";

        FileManager fileManager = new FileManager(filePath);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(collectionManager, scanner);

    }
    }

