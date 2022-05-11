package entity.loesung.musterloesung;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.Designaufgabe;
import jakarta.persistence.*;

/**
 * Musterlösung des Dozenten für eine Aufgabe des Typs Design.
 *
 * @author Timo Joswig
 * @version 09.05.22
 */

@Entity
public class MusterloesungDesignaufgabe extends Musterloesung {

    private String musterloesung; //ToDo: Filetype

    /**
     * Leerer Konstruktor für Klasse MusterloesungDesignaufgabe
     */
    public MusterloesungDesignaufgabe() {
        super();
    }

    /**
     * Konstruktor für eine Musterlösung vom Typ Design
     *
     * @param aufgabe         zur Lösung gehörende Designaufgabe
     * @param loesungshinweis zur Lösung gehörender Lösungshinweis
     * @param musterloesung   Musterlösung in Form einer Bilddatei
     */
    public MusterloesungDesignaufgabe(Designaufgabe aufgabe, String loesungshinweis, String musterloesung) {
        super(aufgabe, loesungshinweis);
        this.musterloesung = musterloesung;
    }

    /**
     * Gibt die Musterlösung in Form einer Bilddatei zurück
     *
     * @return Musterlösung in Form einer Bilddatei
     */
    public String getMusterloesung() {
        return musterloesung;
    }

    /**
     * Setzt die Musterlösung in Form einer Bilddatei
     *
     * @param musterloesung in Form einer Bilddatei
     */
    public void setMusterloesung(String musterloesung) {
        this.musterloesung = musterloesung;
    }

    /**
     * Setzt die zur Lösung gehörende Aufgabe
     *
     * @param aufgabe
     * @return zur Lösung gehörende Aufgabe
     */
    @Override
    public void setAufgabe(Aufgabe aufgabe) throws Exception {
        if (aufgabe.getClass() == Designaufgabe.class) {
            this.aufgabe = aufgabe;
        } else {
            throw new Exception("MusterloesungDesignaufgabe kann nur eine Aufgabe vom Typ Design erhalten");
        }
    }
}