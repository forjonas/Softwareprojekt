package entity.loesung.userloesung;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import jakarta.persistence.*;

/**
 * Userlösung eines Benutzers für eine Aufgabe des Typs Einfachantwort.
 *
 * @author Timo Joswig
 * @version 10.05.22
 */

@Entity
public class UserloesungEinfachantwort extends Userloesung {

    private String userloesung;

    /**
     * Leerer Konstruktor für Klasse UserloesungDesignaufgabe
     */
    public UserloesungEinfachantwort() {
        super();
    }

    /**
     * Konstruktor für eine Userlösung vom Typ Einfachantwort
     *
     * @param aufgabe          zur Lösung gehörende Aufgabe
     * @param hinweisVerwendet gibt an, ob der Lösungshinweis verwendet wurde
     * @param userloesung      Userlösung in Form von Text
     */
    public UserloesungEinfachantwort(EinfachantwortAufgabe aufgabe, boolean hinweisVerwendet, String userloesung) {
        super(aufgabe, hinweisVerwendet);
        this.userloesung = userloesung;
    }

    /**
     * Gibt die Userlösung in Form von Text zurück
     *
     * @return Userlösung in Form von Text
     */
    public String getUserloesung() {
        return userloesung;
    }

    /**
     * Setzt die Userlösung in Form von Text
     *
     * @param userloesung Userlösung in Form von Text
     */
    public void setUserloesung(String userloesung) {
        this.userloesung = userloesung;
    }

    /**
     * Setzt die zur Lösung gehörende Aufgabe
     *
     * @param aufgabe
     * @return zur Lösung gehörende Aufgabe
     */
    @Override
    public void setAufgabe(Aufgabe aufgabe) throws Exception {
        if (aufgabe.getClass() == EinfachantwortAufgabe.class) {
            this.aufgabe = aufgabe;
        } else {
            throw new Exception("UserloesungEinfachantwort kann nur eine Aufgabe vom Typ Einfachantwort erhalten");
        }
    }

}