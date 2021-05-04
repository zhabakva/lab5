package edu.Utility;

import edu.exceptions.IncorrectInputInScriptException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * Controls the modes of input and processes user's input
 * **/
public class Console {
    private CommandManager commandManager;
    private CollectionManager collectionManager;
    private Scanner currentScanner;
    private BufferedReader fileReader;
        private ConsoleStatus status;
        public Console(CollectionManager collectionManager, Scanner scanner){
            this.collectionManager = collectionManager;
            this.currentScanner = scanner;
            this.commandManager = new CommandManager(this, collectionManager);
            this.status = ConsoleStatus.OFF;
            this.interactiveMode(scanner);
        }

    public BufferedReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(BufferedReader bufferedReader) {
        this.fileReader = bufferedReader;
    }

    public Scanner getCurrentScanner() {
        return currentScanner;
    }

    /**
     * Prints error: toOut.toString() to Console
     * @param toOut Error to print
     */
    public static void printerror(Object toOut) {
        System.out.println("error: " + toOut);
    }

    /**
         * Interrupt the loop of input
         * **/
        public void turnOff(){
            status = ConsoleStatus.OFF;
        }
        /**
         * Turn on the mode that allows user to enter the commands interactive in console
         * **/
        public void interactiveMode(Scanner scanner)  {

            status = ConsoleStatus.INTERACTIVE;
            while(!status.equals(ConsoleStatus.OFF)){
                System.out.println("Enter your command: ");
                String[] input;
                input = (scanner.nextLine().trim()+" ").split(" ",2);
                input[0] = input[0].trim();
                input[1] = input[1].trim();
                if (input[0].isEmpty() && input[1].isEmpty()) continue;
                try {
                    if (commandManager.executeCommand(input[0],input[1])) System.out.println("Command completed successfully");
                    else System.out.println("Execution failed");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        /**
         * Start executing the script with specified buffered reader
         * @param bufferedReader
         * **/
        public void scriptMode(BufferedReader bufferedReader, ConsoleStatus previousMode) throws IncorrectInputInScriptException {

            status = ConsoleStatus.SCRIPT;
            String rawInput;
            try{
                while(!status.equals(ConsoleStatus.OFF) && (rawInput = bufferedReader.readLine())!=null){
                    if (rawInput.isEmpty()) continue;
                    String[] input;
                    input = (rawInput.trim()+" ").split(" ",2);
                    input[0] = input[0].trim();
                    input[1] = input[1].trim();
                    if(commandManager.executeCommand(input[0],input[1])) System.out.println("Command " + input[0] + " completed successfully");
                    else {
                        System.out.println("Execution of " + input[0] + " failed");
                        System.out.println("Stopping the script");
                        throw new IncorrectInputInScriptException();
                    }
                }
            } catch (IOException e){
                System.out.println("Reading exception. Script execution failed");
                status = previousMode;
                return;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            status = previousMode;

        }
        /**
         * @return status of the console
         * **/
        public ConsoleStatus getStatus(){
            return this.status;
        }

    public void setStatus(ConsoleStatus status) {
        this.status = status;
    }

    /**
         * Prints argument if console mode is interactive
         * **/
        public void println(String s){
            if(!status.equals(ConsoleStatus.OFF)) System.out.println(s);
        }
        /**
         * @return true if interactive mode is active
         * **/
        public boolean isInteractive(){
            return status.equals(ConsoleStatus.INTERACTIVE);
        }
    /**
     * Prints formatted 2-element table to Console
     * @param element1 Left element of the row.
     * @param element2 Right element of the row.
     */
    public static void printTable(Object element1, Object element2) {
        System.out.printf("%-37s%-1s%n", element1, element2);
    }


    }

