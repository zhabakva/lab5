package edu.commands;

import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.exceptions.WrongAmountOfElementsException;

import java.time.LocalDateTime;

/**
     * Command 'info'. Prints information about the collection.
     */
    public class CommandInfo extends AbstractCommand {

        public CommandInfo(CommandManager commandManager, Console console, CollectionManager collectionManager) {
            super("info", "вывести информацию о коллекции", commandManager, console, collectionManager);
        }

        /**
         * Executes the command.
         * @return Command exit status.
         */
        @Override
        public boolean execute(String argument) {
            try {
                if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
                LocalDateTime lastInitTime = collectionManager.getLastInitTime();
                String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                        lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

                LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
                String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                        lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

                console.println("Сведения о коллекции:");
                console.println(" Тип: " + collectionManager.collectionType());
                console.println(" Количество элементов: " + collectionManager.collectionSize());
                console.println(" Дата последнего сохранения: " + lastSaveTimeString);
                console.println(" Дата последней инициализации: " + lastInitTimeString);
                return true;
            } catch (WrongAmountOfElementsException exception) {
                console.println("Использование: '" + getName() + "'");
            }
            return false;
        }
    }
