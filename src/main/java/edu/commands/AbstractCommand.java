package edu.commands;

import edu.Utility.CollectionManager;
import edu.Utility.CommandManager;
import edu.Utility.Console;

/**
 * Abstract Command class contains Object methods, name and description.
 */
public abstract class AbstractCommand implements Command {
    protected CollectionManager collectionManager;
    private String name;
    private String description;
    protected CommandManager commandManager;
    protected Console console;

    public AbstractCommand(String name, String description, CommandManager commandManager, Console console, CollectionManager collectionManager) {
        this.name = name;
        this.description = description;
        this.commandManager = commandManager;
        this.console = console;
        this.collectionManager = collectionManager;

    }

    /**
     * @return Name and usage way of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Description of the command.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " (" + description + ")";
    };

    @Override
    public int hashCode() {
        return name.hashCode() + description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AbstractCommand other = (AbstractCommand) obj;
        return name.equals(other.name) && description.equals(other.description);
    }
}