package edu.commands;

import edu.FlatAsker;
import edu.Utility.CommandManager;
import edu.exceptions.CollectionIsEmptyException;
import edu.Utility.CollectionManager;
import edu.Utility.Console;
import edu.exceptions.WrongAmountOfElementsException;
/**
 * Command 'remove_first'. Removes the first element.
 */
public class CommandRemoveFirst extends AbstractCommand{

    private FlatAsker flatasker;

    public CommandRemoveFirst(CommandManager commandManager, Console console, CollectionManager collectionManager) {
        super("remove_first", "удалить первый элемент из коллекции", commandManager, console, collectionManager);
    }
    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            collectionManager.removeFirst();
            console.println("Элемент удален!");
            return true;
        } catch (WrongAmountOfElementsException | CollectionIsEmptyException exception) {
            console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
