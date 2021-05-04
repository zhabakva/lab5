package edu.commands;

import edu.Collection.Flat;
import edu.FlatAsker;
import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.exceptions.ValidationException;
import edu.exceptions.WrongAmountOfElementsException;
/**
 * Command 'add_if_max'. Adds new element if the value is more than maximal value.
 */
public class CommandAddIfMax extends AbstractCommand{


        private FlatAsker flatAsker;


        public CommandAddIfMax(CommandManager commandManager, Console console, CollectionManager collectionManager) {
            super("add_if_max", "добавить новый элемент, если его значение больше, чем у наибольшего", commandManager, console, collectionManager);
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
                Flat flatToAdd = flatAsker.askFlat();
                if (collectionManager.addIfMax(flatToAdd)) {
                    console.println("Квартира успешно добавлена!");
                    return true;
                }
                else Console.printerror("Эта квартира не самая лучшая!");
            } catch (WrongAmountOfElementsException exception) {
                console.println("Использование: '" + getName() + "'");
            } catch (ValidationException e) {
                e.printStackTrace();
            }
            return false;
        }
    }


