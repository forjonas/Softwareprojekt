package entity.loesung.userloesung;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.MultipleChoiceAufgabe;
import jakarta.persistence.*;

import java.util.List;

/**
 * Userlösung eines Benutzers für eine Aufgabe des Typs Multiple-Choice.
 *
 * @author Timo Joswig
 * @version 10.05.22
 */

@Entity
public class UserloesungMultipleChoiceAufgabe extends Userloesung {

    @ElementCollection
    private List<Boolean> userloesung;

    /**
     * Leerer Konstruktor für Klasse UserloesungMultipleChoiceAufgabe
     */
    public UserloesungMultipleChoiceAufgabe() {
        super();
    }

    /**
     * Konstruktor für eine Userlösung vom Typ Multiple-Choice
     *
     * @param aufgabe          zur Lösung gehörende Aufgabe
     * @param hinweisVerwendet gibt an, ob der Lösungshinweis verwendet wurde
     * @param userloesung      Userlösung in Form einer Boolean-Liste
     */
    public UserloesungMultipleChoiceAufgabe(MultipleChoiceAufgabe aufgabe, boolean hinweisVerwendet, List<Boolean> userloesung) {
        super(aufgabe, hinweisVerwendet);
        this.userloesung = userloesung;
    }

    /**
     * Gibt die Userlösung in Form einer Boolean-Liste zurück
     *
     * @return Userlösung in Form einer Boolean-Liste
     */
    public List<Boolean> getUserloesung() {
        return userloesung;
    }

    /**
     * Setzt die Userlösung in Form einer Boolean-Liste
     *
     * @param userloesung Userlösung in Form einer Boolean-Liste
     */
    public void setUserloesung(List<Boolean> userloesung) {
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
        if (aufgabe.getClass() == MultipleChoiceAufgabe.class) {
            this.aufgabe = aufgabe;
        } else {
            throw new Exception("UserloesungMultipleChoiceAufgabe kann nur eine Aufgabe vom Typ MultipleChoice erhalten");
        }
    }

}