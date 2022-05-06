package entity;

import jakarta.persistence.*;

/**
 * Userlösung eines Benutzers für eine Aufgabe des Typs Einfachantwort.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserloesungEinfachantwort extends Loesung{

    private String loesung;

    public UserloesungEinfachantwort(){
        super();
    }

    /**
     * Konstruktor für eine Userlösung vom Typ Einfachantwort
     *
     * @param loesung Userlösung in Form von Text
     */
    public UserloesungEinfachantwort(String loesung){
        super();
        this.loesung = loesung;
    }
}
