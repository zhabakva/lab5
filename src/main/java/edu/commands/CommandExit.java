package edu.commands;

import edu.FlatAsker;
import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.Utility.ConsoleStatus;
import edu.exceptions.WrongAmountOfElementsException;

/**
 * Command 'exit'. Exits the app.
 */
public class CommandExit extends AbstractCommand {

    private FlatAsker flatasker;

    public CommandExit(CommandManager commandManager, Console console, CollectionManager collectionManager) {
        super("exit", "завершить программу (без сохранения в файл)", commandManager, console, collectionManager);
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            console.setStatus(ConsoleStatus.OFF);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}