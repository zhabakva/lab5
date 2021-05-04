package edu.commands;

import edu.FlatAsker;
import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.exceptions.WrongAmountOfElementsException;
/**
 * Command 'count_greater_than_view'. Prints the amount of elements if their view is better than the set one.
 */
public class CommandCountGreaterThanView extends AbstractCommand{

    private FlatAsker flatAsker;

    public CommandCountGreaterThanView(CommandManager commandManager, Console console, CollectionManager collectionManager) {
        super("count_greater_than_view", "выводит количество элементов, значение поля view которых больше заданного", commandManager, console, collectionManager);
        flatAsker = new FlatAsker(console, collectionManager);
    }
    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {

        flatAsker.setFileReader();
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            int result = collectionManager.countGreaterThanView(flatAsker.askView());
            console.println("Количество элементов с лучшим видом = " + result);
            return true;

        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
