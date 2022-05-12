package entity.loesung.userloesung;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.benutzer.Benutzer;
import jakarta.persistence.*;

/**
 * Userlösung eines Benutzers für eine Aufgabe des Typs Programmieren.
 *
 * @author Timo Joswig
 * @version 10.05.22
 */

@Entity
public class UserloesungProgrammieraufgabe extends Userloesung {

    private String userloesung;

    /**
     * Leerer Konstruktor für Klasse UserloesungProgrammieraufgabe
     */
    public UserloesungProgrammieraufgabe() {
        super();
    }

    /**
     * Konstruktor für eine Userlösung vom Typ Programmieren
     *
     * @param aufgabe              zur Lösung gehörende Aufgabe
     * @param hinweisVerwendet     gibt an, ob der Lösungshinweis verwendet wurde
     * @param userloesung          Userlösung in Form von Text, genauer Programmcode
     * @param userloesungErsteller Ersteller der Userlösung
     * @param aufgabensammlung     Aufabensammlung, in deren Kontext die Userlösung erstellt wurde
     */
    public UserloesungProgrammieraufgabe(Programmieraufgabe aufgabe, boolean hinweisVerwendet, String userloesung, Benutzer userloesungErsteller, Aufgabensammlung aufgabensammlung) {
        super(aufgabe, hinweisVerwendet, userloesungErsteller, aufgabensammlung);
        this.userloesung = userloesung;
    }

    /**
     * Gibt die Userlösung in Form von Text, genauer Programmcode zurück
     *
     * @return Userlösung in Form von Text, genauer Programmcode
     */
    public String getUserloesung() {
        return userloesung;
    }

    /**
     * Setzt die Userlösung in Form von Text, genauer Programmcode
     *
     * @param userloesung Userlösung in Form von Text, genauer Programmcode
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
        if (aufgabe.getClass() == Programmieraufgabe.class) {
            this.aufgabe = aufgabe;
        } else {
            throw new Exception("UserloesungProgrammieraufgabe kann nur eine Aufgabe vom Typ Programmieren erhalten");
        }
    }

}