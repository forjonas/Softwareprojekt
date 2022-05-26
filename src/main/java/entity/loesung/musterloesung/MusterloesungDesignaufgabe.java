package entity.loesung.musterloesung;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.Designaufgabe;
import jakarta.persistence.*;

/**
 * Musterlösung des Dozenten für eine Aufgabe des Typs Design.
 *
 * @author Timo Joswig
 * @version 26.05.22
 */

@Entity
public class MusterloesungDesignaufgabe extends Musterloesung {

    @Lob
    private byte[] musterloesung;

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
    public MusterloesungDesignaufgabe(Designaufgabe aufgabe, String loesungshinweis, byte[] musterloesung) {
        super(aufgabe, loesungshinweis);
        this.musterloesung = musterloesung;
    }

    /**
     * Gibt die Musterlösung in Form einer Bilddatei zurück
     *
     * @return Musterlösung in Form einer Bilddatei
     */
    public byte[] getMusterloesung() {
        return musterloesung;
    }

    /**
     * Setzt die Musterlösung in Form einer Bilddatei
     *
     * @param musterloesung in Form einer Bilddatei
     */
    public void setMusterloesung(byte[] musterloesung) {
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
            if (aufgabe.getClass() == Designaufgabe.class) {
                this.aufgabe = aufgabe;
            } else {
                throw new Exception("MusterloesungDesignaufgabe kann nur eine Aufgabe vom Typ Design erhalten");
            }
        }
    }

}