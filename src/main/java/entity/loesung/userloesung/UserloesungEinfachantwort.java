package entity.loesung.userloesung;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.benutzer.Benutzer;
import jakarta.persistence.*;

/**
 * Userlösung eines Benutzers für eine Aufgabe des Typs Einfachantwort.
 *
 * @author Timo Joswig
 * @version 26.05.22
 */

@Entity
public class UserloesungEinfachantwort extends Userloesung {

    @Column(length=10485760)
    private String userloesung;

    /**
     * Leerer Konstruktor für Klasse UserloesungEinfachantwort
     */
    public UserloesungEinfachantwort() {
        super();
    }

    /**
     * Konstruktor für eine Userlösung vom Typ Einfachantwort
     *
     * @param aufgabe              zur Lösung gehörende Aufgabe
     * @param hinweisVerwendet     gibt an, ob der Lösungshinweis verwendet wurde
     * @param userloesung          Userlösung in Form von Text
     * @param userloesungErsteller Ersteller der Userlösung
     * @param aufgabensammlung     Aufgabensammlung, in deren Kontext die Userlösung erstellt wurde
     */
    public UserloesungEinfachantwort(EinfachantwortAufgabe aufgabe, boolean hinweisVerwendet, String userloesung, Benutzer userloesungErsteller, Aufgabensammlung aufgabensammlung) {
        super(aufgabe, hinweisVerwendet, userloesungErsteller, aufgabensammlung);
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
                throw new Exception("UserloesungEinfachantwort kann nur eine Aufgabe vom Typ Einfachantwort erhalten");
            }
        }
    }

}