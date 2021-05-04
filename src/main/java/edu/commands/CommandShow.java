package edu.commands;

import edu.Collection.Flat;
import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.exceptions.WrongAmountOfElementsException;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Command 'show'. Shows information about all elements of the collection.
 */
public class CommandShow extends AbstractCommand {


    public CommandShow(CommandManager commandManager, Console console, CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", commandManager, console, collectionManager);

    }
    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
String result = "";
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Field[] attributes =  Flat.class.getDeclaredFields();
            ArrayList<Flat> flats = collectionManager.getFlats();
            for (Flat flat : flats){
                result = result + flat.toString();
            }
            console.println(result);


            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        }
        return false;
    }

}
