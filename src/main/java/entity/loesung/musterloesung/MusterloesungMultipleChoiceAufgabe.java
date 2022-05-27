package entity.loesung.musterloesung;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.MultipleChoiceAufgabe;
import jakarta.persistence.*;

import java.util.*;


/**
 * Musterlösung des Dozenten für eine Aufgabe des Typs Multiple-Choice.
 *
 * @author Timo Joswig
 * @version 26.05.22
 */

@Entity
public class MusterloesungMultipleChoiceAufgabe extends Musterloesung {

    private int musterloesung;

    /**
     * Leerer Konstruktor für Klasse MusterloesungMultipleChoiceAufgabe
     */
    public MusterloesungMultipleChoiceAufgabe() {
        super();
    }

    /**
     * Konstruktor für eine Musterlösung vom Typ Multiple-Choice
     *
     * @param aufgabe         zur Lösung gehörende Multiple-Choice-Aufgabe
     * @param loesungshinweis zur Lösung gehörender Lösungshinweis
     * @param musterloesung   Musterlösung in Form der Nummer der richtigen Antwort
     */
    public MusterloesungMultipleChoiceAufgabe(MultipleChoiceAufgabe aufgabe, String loesungshinweis, int musterloesung) {
        super(aufgabe, loesungshinweis);
        this.musterloesung = musterloesung;
    }

    /**
     * Gibt die Musterlösung in Form der Nummer der richtigen Antwort zurück
     *
     * @return Musterlösung in Form der Nummer der richtigen Antwort
     */
    public int getMusterloesung() {
        return musterloesung;
    }

    /**
     * Setzt die Musterlösung in Form der Nummer der richtigen Antwort
     *
     * @param musterloesung in Form der Nummer der richtigen Antwort
     */
    public void setMusterloesung(int musterloesung) {
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
            if (aufgabe.getClass() == MultipleChoiceAufgabe.class) {
                this.aufgabe = aufgabe;
            } else {
                throw new Exception("MusterloesungMultipleChoiceAufgabe kann nur eine Aufgabe vom Typ MultipleChoice erhalten");
            }
        }
    }
}