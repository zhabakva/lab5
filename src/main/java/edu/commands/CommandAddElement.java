package edu.commands;

import edu.Collection.Flat;
import edu.FlatAsker;
import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.exceptions.ValidationException;
import edu.exceptions.WrongAmountOfElementsException;
/**
 * Command 'add'. Adds new element to collection.
 */

public class CommandAddElement extends AbstractCommand{

    private FlatAsker flatAsker;


    public CommandAddElement(CommandManager commandManager, Console console, CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию", commandManager, console, collectionManager);
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
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.add(flatAsker.askFlat());
            console.println("Квартира успешно добавлена!");

            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        }  catch (ValidationException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            console.println("Проблемы с данными");
        }
        return false;
    }
}

