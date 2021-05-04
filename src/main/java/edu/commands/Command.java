package edu.commands;
/**
 * Interface for all edu.commands.
 */
public interface Command {
    String getDescription();
    String getName();
    boolean execute(String argument);
}
