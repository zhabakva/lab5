package edu.commands;

import edu.Collection.Flat;
import edu.Collection.House;
import edu.FlatAsker;
import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.exceptions.WrongAmountOfElementsException;

import java.util.ArrayList;
import java.util.List;
/**
 * Command 'print_field_descending_house'. Prints all the house values from max to min.
 */
public class CommandPrintFieldDescendingHouse extends AbstractCommand {

    private FlatAsker flatAsker;

    public CommandPrintFieldDescendingHouse(CommandManager commandManager, Console console, CollectionManager collectionManager) {
        super("print_field_descending_house", "вывести значения всех полей house в порядке убывания", commandManager, console, collectionManager);
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
            collectionManager.getNumberDescending();
            console.println("Отсортированный список:");
            List<House> houses = collectionManager.getNumberDescending();
            for (int i = 0; i < houses.size(); i++ ) {
                //
                // console.println(Integer.toString(houses.size()));
                console.println("Имя: "+ houses.get(i).getName());
                long numberoflifts = houses.get(i).getNumberOfLifts();
                String s = Long.toString(numberoflifts);
                console.println("Количество лифтов: " + s);
                int year = houses.get(i).getYear();
                s = Integer.toString(year);
                console.println("Год постройки: " + s);
            }
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        }
        return false;
    }

}
