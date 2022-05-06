package entity;

import jakarta.persistence.*;

/**
 * Musterlösung des Dozenten für eine Aufgabe des Typs Design.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MusterloesungDesignaufgabe extends Loesung{

    private Object loesung; //ToDo: Filetype

    public MusterloesungDesignaufgabe(){
        super();
    }

    /**
     * Konstruktor für eine Musterlösung vom Typ Design
     *
     * @param loesung Musterlösung in Form einer Bilddatei
     */
    public MusterloesungDesignaufgabe(Object loesung){
        super();
        this.loesung = loesung;
    }
}
