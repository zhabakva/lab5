package edu.Collection;

import java.lang.reflect.Field;
import java.util.Date;
/**
 * Flat with all the parameters
 * **/

public class Flat implements Comparable<Flat> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area; //Максимальное значение поля: 957, Значение поля должно быть больше 0
    private int numberOfRooms; //Значение поля должно быть больше 0
    private double kitchenArea; //Значение поля должно быть больше 0
    private Furnish furnish; //Поле может быть null
    private View view; //Поле не может быть null
    private House house; //Поле может быть null

    public Flat(Integer id, String name, Coordinates coordinates, Long area, int numberOfRooms, double kitchenArea, Furnish furnish, View view, House house) {
        this.name = name;
        this.coordinates = coordinates;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.kitchenArea = kitchenArea;
        this.furnish = furnish;
        this.view = view;
        this.house = house;
    }

    public Flat() {
    }

    public void setArea(Long area) {
        this.area = area;
    }
    public Long getArea() {
        return area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }



    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getKitchenArea() {
        return kitchenArea;
    }

    public void setKitchenArea(double kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
    /**
     * Converts Flat to the string with all the parameters using the same method in  House and Coordinates
     * **/
    @Override
    public String toString(){
        String result = "";
        Field[] attributes =  Flat.class.getDeclaredFields();
        for (Field field : attributes){
            try{
                if (field.get(this) != null) {
                    //System.out.println(field);
                    //System.out.println(field.get(this));
                    //System.out.println(this.house == null);
                    field.setAccessible(true);
                    String str = field.get(this).toString();
                    result = result + "\n" + field.getName() + ": " + str;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public Flat(Integer id, String name, Coordinates coordinates, Date creationDate, Long area, int numberOfRooms, double kitchenArea, Furnish furnish, View view, House house) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.kitchenArea = kitchenArea;
        this.furnish = furnish;
        this.view = view;
        this.house = house;
    }
    /**
     * Compares one Flat to another by it's number of Rooms
     * **/
    @Override
    public int compareTo(Flat flat) {
        return flat != null ? numberOfRooms - flat.numberOfRooms : 1;

    }
}
