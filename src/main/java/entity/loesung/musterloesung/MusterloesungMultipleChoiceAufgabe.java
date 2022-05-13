package entity.loesung.musterloesung;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.MultipleChoiceAufgabe;
import jakarta.persistence.*;

import java.util.List;

/**
 * Musterlösung des Dozenten für eine Aufgabe des Typs Multiple-Choice.
 *
 * @author Timo Joswig
 * @version 09.05.22
 */

@Entity
public class MusterloesungMultipleChoiceAufgabe extends Musterloesung {

    @ElementCollection
    private List<Boolean> musterloesung;

    /**
     * Leerer Konstruktor für Klasse MusterloesungEinfachantwort
     */
    public MusterloesungMultipleChoiceAufgabe() {
        super();
    }

    /**
     * Konstruktor für eine Musterlösung vom Typ Multiple-Choice
     *
     * @param aufgabe         zur Lösung gehörende Einfachantwort-Aufgabe
     * @param loesungshinweis zur Lösung gehörender Lösungshinweis
     * @param musterloesung   Musterlösung in Form einer Boolean-Liste
     */
    public MusterloesungMultipleChoiceAufgabe(MultipleChoiceAufgabe aufgabe, String loesungshinweis, List<Boolean> musterloesung) {
        super(aufgabe, loesungshinweis);
        this.musterloesung = musterloesung;
    }

    /**
     * Gibt die Musterlösung in Form einer Boolean-Liste zurück
     *
     * @return Musterlösung in Form einer Boolean-Liste
     */
    public List<Boolean> getMusterloesung() {
        return musterloesung;
    }

    /**
     * Setzt die Musterlösung in Form einer Boolean-Liste
     *
     * @param musterloesung in Form einer Boolean-Liste
     */
    public void setMusterloesung(List<Boolean> musterloesung) {
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
        if(aufgabe == null) {
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