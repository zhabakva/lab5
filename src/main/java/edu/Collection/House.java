package edu.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;
/**
 * Information about the house, where the Flat is
 * **/
public class House implements Comparable<House>{
    private String name; //Поле может быть null
    private int year; //Значение поля должно быть больше 0
    private long numberOfLifts; //Значение поля должно быть больше 0

    public House(@JsonProperty("name")String name, @JsonProperty("year")int year, @JsonProperty("numberOfLifts")long numberOfLifts) {
        this.name = name;
        this.year = year;
        this.numberOfLifts = numberOfLifts;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public long getNumberOfLifts() {
        return numberOfLifts;
    }
    /**
     * Compares one house to another by it's name
     * **/
    @Override
    public int compareTo(House house) {
        if (this.name == null) {
            return house != null && house.name != null ? -1 : 0;
        } else {
            if (house == null) {
                return 1;
            } else {
                return this.name.compareTo(house.name);
            }
        }
    }
    /**
     * Converts House to a string with house parameters and their values
     * @return string with house parameters and their values
     * **/
    @Override
    public String toString(){
        String result = "";
        Field[] attributes =  House.class.getDeclaredFields();
        for (Field field : attributes){
            try{
                if (field.get(this) != null) {
                    //System.out.println(field);
                    //System.out.println(field.get(this));
                    //System.out.println(this.house == null);
                    field.setAccessible(true);
                    String str = field.get(this).toString();
                    result = result + "\n  " + field.getName() + ": " + str;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
