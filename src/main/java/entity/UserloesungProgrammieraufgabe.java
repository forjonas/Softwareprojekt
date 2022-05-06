package entity;

import jakarta.persistence.*;

/**
 * Userlösung eines Benutzers für eine Aufgabe des Typs Programmieren.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserloesungProgrammieraufgabe extends Loesung{

    private String loesung;

    public UserloesungProgrammieraufgabe() {
        super();
    }

    /**
     * Konstruktor für eine Userlösung vom Typ Programmieren
     *
     * @param loesung Userlösung in Form von Text, genauer Programmcode
     */
    public UserloesungProgrammieraufgabe(String loesung){
        super();
        this.loesung = loesung;
    }
}
