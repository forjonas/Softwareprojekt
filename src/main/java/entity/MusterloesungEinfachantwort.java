package entity;

import jakarta.persistence.*;

/**
 * Musterlösung des Dozenten für eine Aufgabe des Typs Einfachantwort.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MusterloesungEinfachantwort extends Loesung{

    private String loesung;

    public MusterloesungEinfachantwort(){
        super();
    }

    /**
     * Konstruktor für eine Musterlösung vom Typ Einfachantwort
     *
     * @param loesung Musterlösung in Form von Text
     */
    public MusterloesungEinfachantwort(String loesung){
        super();
        this.loesung = loesung;
    }
}
