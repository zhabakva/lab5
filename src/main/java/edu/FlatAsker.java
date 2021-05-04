package edu;

import edu.Collection.*;
import edu.Utility.CollectionManager;
import edu.Utility.Console;
import edu.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;


/**
 * Gets all information about flat or its components from user
 * **/
public class FlatAsker {
    private Scanner scanner;
    private BufferedReader fileReader;
    private Console console;
    private CollectionManager collectionManager;

    private final Long MAX_AREA = 957L;
    private final Long MIN_AREA = 0L;
    private final int MIN_NUMBER_OF_ROOMS = 0;
    private final double MIN_KITCHEN_AREA = 0d;
    private final int MIN_YEAR = 0;
    public final int MIN_NUMBER_OF_LIFTS = 0;
    private final String YES = "y";
    private final String NO = "n";
    public FlatAsker(Console console, CollectionManager collectionManager){
        this.console = console;
        this.scanner = console.getCurrentScanner();
        this.collectionManager = collectionManager;
    }

    /**
     * Asks the user specified question
     * @returns true if user's answer is yes, else false;
     * @param question
     * **/
    public boolean askQuestion(String question){
        if(!console.isInteractive()) return true;
        String resQuestion = question + " (y/n): ";
        String answer;
        while(true){
            try {
                System.out.println(resQuestion);
                answer = scanner.nextLine().trim();
                if (!answer.equals(YES) && !answer.equals(NO)) {
                    throw new IncorrectInputException("Problems with access to file");
                }
                else {
                    return answer.equals(YES);
                }
            } catch (IncorrectInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Throws EmptyStringException if the input is empty to prevent the executing a command with empty arguments
     * **/
    private void exceptionIfEmpty(String s) throws EmptyStringException{
        if (s.isEmpty()) throw new EmptyStringException("Empty string");
    }



    /**
     * Asks all information about flat
     * @return flat
     * **/
    public Flat askFlat(){
        return new Flat(collectionManager.generateNextId(), askName(),askCoordinates(),askArea(),askNumberOfRooms(),askKitchenArea(),askFurnish(),askView(),askHouse());

    }


    /**
     * Asks the name
     * @return name
     * **/
    public String askName(){
        if (console.isInteractive()) console.println("Enter the name: ");
        String name = "";
        while(true){
            try{
                if(console.isInteractive()){
                    name = scanner.nextLine().trim();
                } else {
                    name = fileReader.readLine().trim();
                }
                if (name == null) throw new RuntimeException();
                exceptionIfEmpty(name);
                break;
            } catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            } catch (RuntimeException e){
                System.out.println("Empty string");
                if(!console.isInteractive()) return null;
            } catch ( IOException e){
                System.out.println("Problems with access to file");
                return null;
            }

        }
        return name;
    }
    /**
     * Asks the X coordinate
     * @return X coordinate
     * **/
    public Float askX() throws IncorrectInputException{
        if (console.isInteractive()) console.println("Enter the x coordinate: ");
        float x;
        while(true){
            String rawX;
            try{
                if(console.isInteractive()){
                    rawX = scanner.nextLine().trim();
                } else {
                    //System.out.println(fileReader == null);
                    rawX = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawX);
                x = Float.parseFloat(rawX);
                return x;
            } catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            } catch (NullPointerException | NumberFormatException e){
                if (!console.isInteractive()) return null;
                System.out.println("Enter the value");
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }

    }
    /**
     * Asks the Y coordinate
     * @return Y coordinate
     * **/
    public Float askY(){
        if (console.isInteractive()) console.println("Enter the y coordinate: ");
        Float y;
        while(true){
            String rawY;
            try{
                if (console.isInteractive()){
                    rawY = scanner.nextLine().trim();
                } else {

                    rawY = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawY);
                if (rawY == null) throw new NullPointerException();
                y = Float.parseFloat(rawY);
                return y;
            } catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Enter the number");
                if (!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }

    }

    /**
     * Asks the coordinates
     * @return coordinates
     */
    public Coordinates askCoordinates(){
        try{
            Coordinates coord =  new Coordinates(askX(),askY());
            if (coord == null){
                console.println("Вы должны задать координаты!");
            }
            return coord;
        } catch (IncorrectInputException e){
            System.out.println(e.getMessage());
            return null;
        } catch (NullPointerException e){
            if (console.isInteractive()) console.println("Неправильный формат координат");
            return null;
        }
    }
    /**
     * Asks the area
     * @return area
     * **/
    public Long askArea(){
        if (console.isInteractive()) console.println("Enter the area: ");
        long area;
        while(true){
            String rawArea;
            try{
                if (console.isInteractive()){
                    rawArea = scanner.nextLine().trim();
                } else {
                    rawArea = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawArea);
                area = Long.parseLong(rawArea);
                if (area > MAX_AREA) throw new IncorrectInputException("Area cannot be more than " + MAX_AREA);
                if (area <= MIN_AREA) throw new IncorrectInputException("Area must be more than " + MIN_AREA);
                break;
            }catch(EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }
            catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                if(!console.isInteractive()) return MIN_AREA-1;
            }catch(NumberFormatException e){
                System.out.println("Enter the number");
                if(!console.isInteractive()) return MIN_AREA-1;
            }catch(IOException e){
                System.out.println("Problems with access to file");
                return MIN_AREA-1;
            }
        }

        return area;
    }

    /**
     * Asks the number of rooms
     * @return number of rooms
     * **/
    public Integer askNumberOfRooms(){
        if (console.isInteractive()) console.println("Enter the number of rooms: ");
        Integer numberOfRooms;
        while(true){
            String rawNumberOfRooms;
            try{
                if(console.isInteractive()){
                    rawNumberOfRooms = scanner.nextLine().trim();
                } else{
                    rawNumberOfRooms = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawNumberOfRooms);
                numberOfRooms = Integer.parseInt(rawNumberOfRooms);
                if(numberOfRooms <= MIN_NUMBER_OF_ROOMS) throw new IncorrectInputException("Number of rooms must be more than " + MIN_NUMBER_OF_ROOMS);
                break;
            }catch(EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }
            catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                if(!console.isInteractive()) return null;
            } catch (NumberFormatException e){
                System.out.println("Enter the number");
                if(!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }

        return numberOfRooms;
    }
    /**
     * Asks the kitchen area
     * @reutrn kitchen area
     * **/
   public Double askKitchenArea(){
       if (console.isInteractive()) console.println("Enter the kitchen area: ");
       double kitchenArea;
       while(true){
           String rawKitchenArea;
           try{
               if(console.isInteractive()){
                   rawKitchenArea = scanner.nextLine().trim();
               } else{
                   rawKitchenArea = fileReader.readLine().trim();
               }
               exceptionIfEmpty(rawKitchenArea);
               kitchenArea = Double.parseDouble(rawKitchenArea);
               if(kitchenArea <= MIN_KITCHEN_AREA) throw new IncorrectInputException("Number of rooms must be more than " + MIN_KITCHEN_AREA);
               break;
           }catch(EmptyStringException e){
               if(console.isInteractive()) System.out.println(e.getMessage());
           }
           catch (IncorrectInputException e){
               System.out.println(e.getMessage());
               if(!console.isInteractive()) return null;
           } catch (NumberFormatException e){
               System.out.println("Enter the number");
               if(!console.isInteractive()) return null;
           } catch (IOException e){
               System.out.println("Problems with access to file");
               return null;
           }
       }

       return kitchenArea;
   }
    /**
     * Asks the furnish condition
     * @return furnish condition
     * **/
   public Furnish askFurnish() {
       if (console.isInteractive()) console.println("Enter the furnish condition: ");
       if (console.isInteractive()) console.println("You should choose from these options: NONE, FINE, BAD ");
       Furnish furnish = null;
       while (true) {
           String rawFurnish;
           try {
               if (console.isInteractive()) {
                   rawFurnish = scanner.nextLine().trim();
               } else {
                   rawFurnish = fileReader.readLine().trim();
               }
               exceptionIfEmpty(rawFurnish);
               if (rawFurnish.equals("NONE")) {
                   furnish = Furnish.NONE;
               }
               if (rawFurnish.equals("FINE")) {
                   furnish = Furnish.FINE;
               }
               if (rawFurnish.equals("BAD")) {
                   furnish = Furnish.BAD;
               }

               if (furnish == null)
                   throw new IncorrectInputException("You should choose the condition from the provided options ");
               break;
           } catch (EmptyStringException e) {
               if (console.isInteractive()) System.out.println(e.getMessage());
           } catch (IncorrectInputException e) {
               System.out.println(e.getMessage());
               if (!console.isInteractive()) return null;
           } catch (NumberFormatException e) {
               System.out.println("Enter the number");
               if (!console.isInteractive()) return null;
           } catch (IOException e) {
               System.out.println("Problems with access to file");
               return null;
           }
       }
       return furnish;
   }
    /**
     * Asks the view type
     * @return view type
     * **/
    public View askView() {
        if (console.isInteractive()) console.println("Enter the type of view: ");
        if (console.isInteractive()) console.println("You should choose from these options: STREET, YARD, NORMAL");
        View view = null;
        while (true) {
            String rawView;
            try {
                if (console.isInteractive()) {
                    rawView = scanner.nextLine().trim();
                } else {
                    rawView = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawView);
                if (rawView.equals("YARD")) {
                    view = View.YARD;
                }
                if (rawView.equals("NORMAL")) {
                    view = View.NORMAL;
                }
                if (rawView.equals("STREET")) {
                    view = View.STREET;
                }

                if (view == null)
                    throw new IncorrectInputException("You should choose the type of view from the provided options ");
                break;
            } catch (EmptyStringException e) {
                if (console.isInteractive()) System.out.println(e.getMessage());
            } catch (IncorrectInputException e) {
                System.out.println(e.getMessage());
                if (!console.isInteractive()) return null;
            } catch (NumberFormatException e) {
                System.out.println("Enter the number");
                if (!console.isInteractive()) return null;
            } catch (IOException e) {
                System.out.println("Problems with access to file");
                return null;
            }
        }
        return view;
    }

    /**
     * Asks the year of house
     * @return year of house
     * **/
    public Integer askHouseYear(){
        if (console.isInteractive()) console.println("Enter the year of house: ");
        Integer year;
        while(true){
            String rawYear;
            try{
                if(console.isInteractive()){
                    rawYear = scanner.nextLine().trim();
                } else {
                    rawYear = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawYear);
                year = Integer.parseInt(rawYear);
                if (year <= MIN_YEAR) throw new IncorrectInputException("Year must be more than " + MIN_YEAR);
                break;
            }catch(EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }
            catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                if(!console.isInteractive()) return null;
            } catch (NumberFormatException e){
                System.out.println("Enter the number");
                if(!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }
        return year;
    }

    /**
     * Asks the number of lifts in house
     * @reutrn number of lifts in house
     * **/
    public Integer askHouseNumberOfLifts(){
        if (console.isInteractive()) console.println("Enter the number of lifts: ");
        Integer numberOfLifts;
        while(true){
            String rawNumberOfLifts;
            try{
                if (console.isInteractive()){
                    rawNumberOfLifts = scanner.nextLine().trim();
                } else {
                    rawNumberOfLifts = fileReader.readLine().trim();
                }
                exceptionIfEmpty(rawNumberOfLifts);
                numberOfLifts = Integer.parseInt(rawNumberOfLifts);
                if (numberOfLifts <= MIN_NUMBER_OF_LIFTS) throw new IncorrectInputException("Number of lifts must be more than " + MIN_NUMBER_OF_LIFTS);
                break;
            }catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            }
            catch (IncorrectInputException e){
                System.out.println(e.getMessage());
                if (!console.isInteractive()) return null;
            } catch (NumberFormatException e){
                System.out.println("Enter the number");
                if(!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }

        return numberOfLifts;
    }
    /**
     * Asks the name of the house
     * @return name of the house
     * **/
    public String askHouseName(){
        if (console.isInteractive()) console.println("Enter the house name: ");
        String houseName;
        while(true){
            try{
                if (console.isInteractive()){
                    houseName = scanner.nextLine().trim();
                } else {
                    houseName = fileReader.readLine().trim();
                }
                exceptionIfEmpty(houseName);
                return houseName;
            }catch (EmptyStringException e){
                if(console.isInteractive()) System.out.println(e.getMessage());
            } catch (NumberFormatException e){
                System.out.println("Enter the name");
                if(!console.isInteractive()) return null;
            } catch (IOException e){
                System.out.println("Problems with access to file");
                return null;
            }
        }


    }
    /**
     * Asks all information about the house
     * @return house
     * **/
    public House askHouse(){
        if (console.isInteractive()) console.println("Enter the information about house: ");
        House house = new House(askHouseName(),askHouseYear(),askHouseNumberOfLifts());
        return house;

    }
    /**
     * Sets new console
     * **/
    public void setFileReader(){
        this.fileReader = console.getFileReader();
    }
}