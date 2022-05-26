package entity.loesung.musterloesung;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.Programmieraufgabe;
import jakarta.persistence.*;

/**
 * Musterlösung des Dozenten für eine Aufgabe des Typs Programmieren.
 *
 * @author Timo Joswig
 * @version 09.05.22
 */

@Entity
public class MusterloesungProgrammieraufgabe extends Musterloesung {

    private String musterloesung;

    /**
     * Leerer Konstruktor für Klasse MusterloesungProgrammieraufgabe
     */
    public MusterloesungProgrammieraufgabe() {
        super();
    }

    /**
     * Konstruktor für eine Musterlösung vom Typ Programmieren
     *
     * @param aufgabe         zur Lösung gehörende Einfachantwort-Aufgabe
     * @param loesungshinweis zur Lösung gehörender Lösungshinweis
     * @param musterloesung   Musterlösung in Form von Text, genauer Programmcode
     */
    public MusterloesungProgrammieraufgabe(Programmieraufgabe aufgabe, String loesungshinweis, String musterloesung) {
        super(aufgabe, loesungshinweis);
        this.musterloesung = musterloesung;
    }

    /**
     * Gibt die Musterlösung in Form von Text, genauer Programmcode
     *
     * @return Musterlösung in Form von Text, genauer Programmcode
     */
    public String getMusterloesung() {
        return musterloesung;
    }

    /**
     * Setzt die Musterlösung in Form von Text, genauer Programmcode
     *
     * @param musterloesung in Form von Text, genauer Programmcode
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
            if (aufgabe.getClass() == Programmieraufgabe.class) {
                this.aufgabe = aufgabe;
            } else {
                throw new Exception("MusterloesungProgrammieraufgabe kann nur eine Aufgabe vom Typ Programmieren erhalten");
            }
        }
    }

}