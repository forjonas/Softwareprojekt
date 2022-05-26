package entity.loesung.musterloesung;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import jakarta.persistence.*;

/**
 * Musterlösung des Dozenten für eine Aufgabe des Typs Einfachantwort.
 *
 * @author Timo Joswig
 * @version 09.05.22
 */

@Entity
public class MusterloesungEinfachantwort extends Musterloesung {

    private String musterloesung;

    /**
     * Leerer Konstruktor für Klasse MusterloesungEinfachantwort
     */
    public MusterloesungEinfachantwort() {
        super();
    }

    /**
     * Konstruktor für eine Musterlösung vom Typ Design
     *
     * @param aufgabe         zur Lösung gehörende Einfachantwort-Aufgabe
     * @param loesungshinweis zur Lösung gehörender Lösungshinweis
     * @param musterloesung   Musterlösung in Form eines Textes
     */
    public MusterloesungEinfachantwort(EinfachantwortAufgabe aufgabe, String loesungshinweis, String musterloesung) {
        super(aufgabe, loesungshinweis);
        this.musterloesung = musterloesung;
    }

    /**
     * Gibt die Musterlösung in Form eines Textes zurück
     *
     * @return Musterlösung in Form eines Textes
     */
    public String getMusterloesung() {
        return musterloesung;
    }

    /**
     * Setzt die Musterlösung in Form eines Textes
     *
     * @param musterloesung in Form eines Textes
     */
    public void setMusterloesung(String musterloesung) {
        this.musterloesung = musterloesung;
    }

    /**
     * Setzt die zur Lösung gehörende Aufgabe
     *
     * @param aufgabe Aufgabe die zur entsprechenden Lösung gesetzt werden soll
     */
    @Override
    public void setAufgabe(Aufgabe aufgabe) throws Exception {
        if (aufgabe == null) {
            this.aufgabe = null;
        } else {
            if (aufgabe.getClass() == EinfachantwortAufgabe.class) {
                this.aufgabe = aufgabe;
            } else {
                throw new Exception("MusterloesungEinfachantwort kann nur eine Aufgabe vom Typ Einfachantwort erhalten");
            }
        }
    }

}
