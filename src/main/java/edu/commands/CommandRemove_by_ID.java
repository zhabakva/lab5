package edu.commands;

import edu.FlatAsker;
import edu.Utility.CommandManager;
import edu.exceptions.CollectionIsEmptyException;
import edu.Utility.CollectionManager;
import edu.Utility.Console;
import edu.exceptions.WrongAmountOfElementsException;

/**
 * Command 'remove_by_id'. Removes the element by its ID.
 */
public class CommandRemove_by_ID extends AbstractCommand {

    private FlatAsker flatasker;


    public CommandRemove_by_ID(CommandManager commandManager, Console console, CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по ID", commandManager, console, collectionManager);

    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Integer id = Integer.parseInt(argument);
            collectionManager.remove(id);
            console.println("Квартира успешно удалена!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            console.printerror("ID должен быть представлен числом!");
        }
        return false;
    }
}