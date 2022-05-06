package entity;

import jakarta.persistence.*;

/**
 * Musterlösung des Dozenten für eine Aufgabe des Typs Programmieren.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MusterloesungProgrammieraufgabe extends Loesung{

    private String loesung;

    public MusterloesungProgrammieraufgabe() {
        super();
    }

    /**
     * Konstruktor für eine Musterlösung vom Typ Programmieren
     *
     * @param loesung Musterlösung in Form von Text, genauer Programmcode
     */
    public MusterloesungProgrammieraufgabe(String loesung){
        super();
        this.loesung = loesung;
    }
}
