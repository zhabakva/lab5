package edu.commands;

import edu.Collection.*;
import edu.FlatAsker;
import edu.Utility.CommandManager;
import edu.exceptions.*;
import edu.Utility.CollectionManager;
import edu.Utility.Console;

import java.util.Date;

/**
 * Command 'update'. Updates the information about selected flat.
 */
public class CommandUpdate extends AbstractCommand {

    private FlatAsker flatAsker;

    public CommandUpdate(CommandManager commandManager, Console console, CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции по ID", commandManager, console, collectionManager);
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
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            Integer id = Integer.parseInt(argument);
            Flat oldFlat = collectionManager.getById(id);
            if (oldFlat == null) throw new FlatNotFoundException();

            String name = oldFlat.getName();
            Coordinates coordinates = oldFlat.getCoordinates();
            Date creationDate = oldFlat.getCreationDate();
            long area = oldFlat.getArea();
            int numberOfRooms = oldFlat.getNumberOfRooms();
            double kitchenArea = oldFlat.getKitchenArea();
            Furnish furnish = oldFlat.getFurnish();
            View view = oldFlat.getView();
            House house = oldFlat.getHouse();
            collectionManager.remove(id);

            if (flatAsker.askQuestion("Хотите изменить имя квартиры?")) name = flatAsker.askName();
            if (flatAsker.askQuestion("Хотите изменить координаты квартиры?")) coordinates = flatAsker.askCoordinates();
            if (flatAsker.askQuestion("Хотите изменить площадь квартиры?")) area = flatAsker.askArea();
            if (flatAsker.askQuestion("Хотите изменить количество комнат в квартире?")) numberOfRooms = flatAsker.askNumberOfRooms();
            if (flatAsker.askQuestion("Хотите изменить площадь кухни в квартире?")) kitchenArea = flatAsker.askKitchenArea();
            if (flatAsker.askQuestion("Хотите изменить мебель в квартире?")) furnish = flatAsker.askFurnish();
            if (flatAsker.askQuestion("Хотите изменить вид из квартиры?")) view = flatAsker.askView();
            if (flatAsker.askQuestion("Хотите изменить дом, в котором находится квартира?")) house = flatAsker.askHouse();

            collectionManager.add(new Flat(
                    id, name, coordinates, area, numberOfRooms, kitchenArea, furnish, view, house));
            console.println("Квартира успешно изменена!");
        return true;
    } catch (WrongAmountOfElementsException exception) {
        console.println("Использование: '" + getName() + "'");
    } catch (CollectionIsEmptyException exception) {
        console.printerror("Коллекция пуста!");
    } catch (NumberFormatException exception) {
        console.printerror("ID должен быть представлен числом!");
    } catch (FlatNotFoundException exception) {
        console.printerror("Солдата с таким ID в коллекции нет!");
    } catch (ValidationException exception) {}
        return false;
    }
}
