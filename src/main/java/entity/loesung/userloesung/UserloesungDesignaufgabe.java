package entity.loesung.userloesung;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.Designaufgabe;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.benutzer.Benutzer;
import jakarta.persistence.*;

/**
 * Userlösung eines Benutzers für eine Aufgabe des Typs Design.
 *
 * @author Timo Joswig
 * @version 26.05.22
 */

@Entity
public class UserloesungDesignaufgabe extends Userloesung {

    private byte[] userloesung;

    /**
     * Leerer Konstruktor für Klasse UserloesungDesignaufgabe
     */
    public UserloesungDesignaufgabe() {
        super();
    }

    /**
     * Konstruktor für eine Userlösung vom Typ Design
     *
     * @param aufgabe              zur Lösung gehörende Aufgabe
     * @param hinweisVerwendet     gibt an, ob der Lösungshinweis verwendet wurde
     * @param userloesung          Userlösung in Form einer Bilddatei
     * @param userloesungErsteller Ersteller der Userlösung
     * @param aufgabensammlung     Aufabensammlung, in deren Kontext die Userlösung erstellt wurde
     */
    public UserloesungDesignaufgabe(Designaufgabe aufgabe, boolean hinweisVerwendet, byte[] userloesung, Benutzer userloesungErsteller, Aufgabensammlung aufgabensammlung) {
        super(aufgabe, hinweisVerwendet, userloesungErsteller, aufgabensammlung);
        this.userloesung = userloesung;
    }

    /**
     * Gibt die Userlösung in Form einer Bilddatei zurück
     *
     * @return Userlösung in Form einer Bilddatei
     */
    public byte[] getUserloesung() {
        return userloesung;
    }

    /**
     * Setzt die Userlösung in Form einer Bilddatei
     *
     * @param userloesung Userlösung in Form einer Bilddatei
     */
    public void setUserloesung(byte[] userloesung) {
        this.userloesung = userloesung;
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
                throw new Exception("UserloesungDesignaufgabe kann nur eine Aufgabe vom Typ Design erhalten");
            }
        }
    }

}