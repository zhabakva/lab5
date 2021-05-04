package edu.commands;

import edu.FlatAsker;
import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.exceptions.WrongAmountOfElementsException;

/**
 * Command 'save'. Saves the collection to a file.
 */
public class CommandSave extends AbstractCommand {

    private FlatAsker flatasker;


    public CommandSave(CommandManager commandManager, Console console, CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл", commandManager, console, collectionManager);
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.saveCollection();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
