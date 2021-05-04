package edu.commands;

import edu.FlatAsker;
import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.exceptions.WrongAmountOfElementsException;
/**
 * Command 'clear'. Clears the collection.
 */
public class CommandClear extends AbstractCommand{

    private FlatAsker flatasker;

    public CommandClear(CommandManager commandManager, Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", commandManager, console, collectionManager);
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {


        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.clear();
            console.println("Коллекция очищена!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
