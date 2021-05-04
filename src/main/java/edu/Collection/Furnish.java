package edu.Collection;

import javax.management.BadAttributeValueExpException;
/**
 * Enum of furnish conditions
 * **/
public enum Furnish {
    NONE,
    FINE,
    BAD;
    /**
     * Converts furnish condition to printable String
     * **/
    @Override
    public String toString() {
        if (this == NONE){
            return ("Откровенно говоря, присутствие мебели отсутствует");
        }
        if (this == FINE){
            return ("Отличная мебель, вам очень повезло!");
        }
        if (this == BAD){
            return ("Мда, лучше бы мебели вообще не было, чем такая ужасная");
        }
        return null;
    }
}
