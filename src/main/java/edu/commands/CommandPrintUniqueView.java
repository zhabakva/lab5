package edu.commands;

import edu.Collection.House;
import edu.Collection.View;
import edu.FlatAsker;
import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.exceptions.WrongAmountOfElementsException;

import java.util.List;
import java.util.Set;
/**
 * Command 'print_unique_view'. Prints all unique values of view field.
 */
public class CommandPrintUniqueView extends AbstractCommand{

    private FlatAsker flatasker;

    public CommandPrintUniqueView(CommandManager commandManager, Console console, CollectionManager collectionManager) {
        super("print_unique_view", "вывести уникальные значения поля view всех элементов в коллекции", commandManager, console, collectionManager);
    }
    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {

        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Set<View> set = collectionManager.getUniqueView();
            console.println("Уникальные значения поля view:");
            for (View current : set) {
                console.println(current.toString());
            }
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        }
        return false;

    }
}
