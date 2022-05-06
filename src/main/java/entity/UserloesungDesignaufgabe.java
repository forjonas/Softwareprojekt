package entity;

import jakarta.persistence.*;

/**
 * Userlösung eines Benutzers für eine Aufgabe des Typs Design.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserloesungDesignaufgabe extends Loesung{

    private Object loesung; //ToDo: Filetype

    public UserloesungDesignaufgabe() {
        super();
    }

    /**
     * Konstruktor für eine Userlösung vom Typ Design
     *
     * @param loesung Userlösung in Form einer Bilddatei
     */
    public UserloesungDesignaufgabe(Object loesung) {
        super();
        this.loesung = loesung;
    }
}
