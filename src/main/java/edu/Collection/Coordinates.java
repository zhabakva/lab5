package edu.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;
/**
 * Coordinates x-y
 * **/

public class Coordinates {
    private float x;
    private Float y; //Поле не может быть null
    public Coordinates() {
    }

    public Coordinates(@JsonProperty("x")float x,@JsonProperty("y") Float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    @Override
    public String toString(){
        String result = "";
        Field[] attributes =  Coordinates.class.getDeclaredFields();
        for (Field field : attributes){
            try{
                field.setAccessible(true);
                //System.out.println(this.y);
                String str = field.get(this).toString();
                result = result + "\n  " + field.getName() + ": " + str;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
