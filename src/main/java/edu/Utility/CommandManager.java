package edu.Utility;

import edu.commands.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<Command> commands = new ArrayList<Command>() ;
    private CommandAddElement addElement;
    private CommandAddIfMax addIfMax;
    private CommandClear clear;
    private CommandCountGreaterThanView countGreaterThanView;
    private CommandExecute_Script executeScript;
    private CommandExit exit;
    private CommandHelp help;
    private CommandInfo info;
    private CommandPrintFieldDescendingHouse print_field_descending_house;
    private CommandPrintUniqueView printUniqueView;
    private CommandRemove_by_ID remove_by_id;
    private CommandRemoveFirst removeFirst;
    private CommandRemoveLast removeLast;
    private CommandSave save;
    private CommandShow show;
    private CommandUpdate update;
    Console console;
    ArrayList ScriptArray = new ArrayList();
    public CommandManager(Console console, CollectionManager collectionManager) {
        this.console = console;
        this.addElement = new CommandAddElement(this, console, collectionManager);
        this.addIfMax =  new CommandAddIfMax(this, console, collectionManager);
        this.clear = new CommandClear(this, console, collectionManager);
        this.countGreaterThanView = new CommandCountGreaterThanView(this, console, collectionManager);
        this.executeScript = new CommandExecute_Script(this, console, collectionManager);
        this.exit = new CommandExit(this, console, collectionManager);
        this.help = new CommandHelp(this, console, collectionManager);
        this.info = new CommandInfo(this, console, collectionManager);
        this.print_field_descending_house = new CommandPrintFieldDescendingHouse(this, console, collectionManager);
        this.printUniqueView = new CommandPrintUniqueView(this, console, collectionManager);
        this.remove_by_id = new CommandRemove_by_ID(this, console, collectionManager);
        this.removeFirst = new CommandRemoveFirst(this, console, collectionManager);
        this.removeLast = new CommandRemoveLast(this, console, collectionManager);
        this.save = new CommandSave(this, console, collectionManager);
        this.show = new CommandShow(this, console, collectionManager);
        this.update = new CommandUpdate(this, console, collectionManager);
        commands.add(addElement);
        commands.add(addIfMax);
        commands.add(clear);
        commands.add(countGreaterThanView);
        commands.add(executeScript);
        commands.add(exit);
        commands.add(help);
        commands.add(info);
        commands.add(print_field_descending_house);
        commands.add(printUniqueView);
        commands.add(remove_by_id);
        commands.add(removeFirst);
        commands.add(removeLast);
        commands.add(save);
        commands.add(show);
        commands.add(update);

    }


    /**
     * @return List of manager's commands.
     */
    public List<Command> getCommands() {
        return commands;
    }



    /**
     * Prints that command is not found.
     * @param command Command, which is not found.
     * @return Command exit status.n
     */
    public boolean noSuchCommand(String command) {
        console.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }

    /**
     * Prints info about the all commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean help(String argument) {

            for (Command command : commands) {
                Console.printTable(command.getName(), command.getDescription());
            }
            return true;

    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean info(String argument) {
        return info.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean show(String argument) {
        return show.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean addElement(String argument) {
        return addElement.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean update(String argument) {
        return update.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean remove_By_ID(String argument) {
        return remove_by_id.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean clear(String argument) {
        return clear.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean save(String argument) {
        return save.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean exit(String argument) {
        return exit.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeScript(String argument) {
        return executeScript.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean addIfMax(String argument) {
        return addIfMax.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean countGreaterThanView(String argument) {
        return countGreaterThanView.execute(argument);
    }


    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean printFieldDescendingHouse(String argument) {
        return print_field_descending_house.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean printUniqueView(String argument) {
        return printUniqueView.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeFirst(String argument) {
        return removeFirst.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeLast(String argument) {
        return removeLast.execute(argument);
    }

    /**
     * Executes the command with specified name
     * @return exit status of executable command
     * **/
public boolean executeCommand(String nameOfCommand, String parameter) throws NoSuchFieldException, IllegalAccessException {
    // запуск команды

    for (Command command : commands){
        //console.println(command.getName());
        if (nameOfCommand.equals(command.getName())){
            //console.println("Начинаю мучаться");
            // записать параметр в стек, если NameOfCommand == execute_script
            if (nameOfCommand.equals("execute_script"))
            {
                // проверка на рекурсивность
                if (ScriptArray.contains(parameter)){
                    console.println("Рекурсия!!!");
                    return false;
                }
                // иначе добавляем в стек
                ScriptArray.add(parameter);
            }
            // выполнить команду
            boolean result = command.execute(parameter);

            // удалить из стека, если NameOfCommand == execute_script и result == true
            if ((nameOfCommand.equals("execute_script")) && (result)){
                ScriptArray.remove(parameter);
            }
            return result;
        }
    }
    return false;

}

}
