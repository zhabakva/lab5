package edu.commands;


import edu.FlatAsker;
import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;
import edu.exceptions.WrongAmountOfElementsException;

/**
     * Command 'help'. Prints the info about available commands .
     */
    public class CommandHelp extends AbstractCommand {

        private FlatAsker flatasker;

        public CommandHelp(CommandManager commandManager, Console console, CollectionManager collectionManager) {
            super("help", "вывести справку по доступным командам", commandManager, console, collectionManager);
        }

        /**
         * Executes the command.
         * @return Command exit status.
         */
        @Override
        public boolean execute(String argument) {
            try {
                if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
                commandManager.help(argument);
                return true;
            } catch (WrongAmountOfElementsException exception) {
                console.println("Использование: '" + getName() + "'");
            }
            return false;
        }
    }
