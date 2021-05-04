package edu.Collection;
/**
 * Enum of views from the Flat
 * **/
public enum View {
    STREET,
    YARD,
    NORMAL;
    @Override
    public String toString() {
        if (this == STREET){
            return ("Вид на улицу, будет шумновато");
        }
        if (this == YARD){
            return ("Вид на садик, но не детский");
        }
        if (this == NORMAL){
            return ("Ну, если честно, ничего особенного. Ни хорошо, ни плохо");
        }
        return null;
    }
}
