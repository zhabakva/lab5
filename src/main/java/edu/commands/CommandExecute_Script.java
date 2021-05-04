package edu.commands;

import edu.FlatAsker;
import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.exceptions.IncorrectInputInScriptException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
/**
 * Command 'execute_script'. Executes script from file. Standard example: execute_script /home/sofia/Документы/script.jopa
 */
public class CommandExecute_Script extends AbstractCommand{

    private FlatAsker flatAsker;


    public CommandExecute_Script(CommandManager commandManager, Console console, CollectionManager collectionManager){
        super("execute_script", "выполнить программу из файла со скриптом", commandManager, console, collectionManager);
        flatAsker = new FlatAsker(console, collectionManager);
    }
    /**
     * Executes the command.
     * @return Command exit status.
     */
    public boolean execute(String filePath) {
        try{
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            console.setFileReader(bufferedReader);
            flatAsker.setFileReader();
            console.scriptMode(bufferedReader, console.getStatus());
            return true;
        } catch (FileNotFoundException e1){
            System.out.println("No such file found");
            return false;
        }  catch (IncorrectInputInScriptException e1) {
            return false;
        }

    }
}
