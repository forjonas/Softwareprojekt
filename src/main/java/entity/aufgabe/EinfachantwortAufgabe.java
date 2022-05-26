package entity.aufgabe;

import entity.benutzer.Dozent;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.Musterloesung;
import entity.loesung.musterloesung.MusterloesungEinfachantwort;
import entity.loesung.userloesung.Userloesung;
import entity.loesung.userloesung.UserloesungEinfachantwort;
import jakarta.persistence.Entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Aufgabe mit einfachem Text als Antwort
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
@Entity
public class EinfachantwortAufgabe extends Aufgabe {

    /**
     * Leerer Konstruktor für Klasse EinfachantwortAufgabe
     */
    public EinfachantwortAufgabe() {
        super();
        this.aufgabentyp = Aufgabentyp.Einfachantwort;
    }

    /**
     * Konstruktor für Klasse EinfachantwortAufgabe
     *
     * @param bearbeitungszeit      Bearbeitungszeit der Aufgabe
     * @param aufgabenstellungsbild Bild, das Teil der Aufgabenstellung ist
     * @param kategorie             Kategorie der Aufgabe
     * @param punktewert            Punktewert der Aufgabe
     * @param schwierigkeitsgrad    Schwierigkeitsgrad der Aufgabe
     * @param textbeschreibung      Textbeschreibung der Aufgabe
     * @param name                  Name der Aufgabe
     * @param aufgabenErsteller     Dozent, der die Aufgabe erstellt hat
     * @param musterloesung         Musterlösung der Aufgabe
     */
    public EinfachantwortAufgabe(int bearbeitungszeit, byte[] aufgabenstellungsbild, Kategorie kategorie, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, Dozent aufgabenErsteller, MusterloesungEinfachantwort musterloesung) {
        super(bearbeitungszeit, aufgabenstellungsbild, kategorie, punktewert, schwierigkeitsgrad, textbeschreibung, name, aufgabenErsteller, musterloesung);
        this.aufgabentyp = Aufgabentyp.Einfachantwort;
    }

    /**
     * Konstruktor für Klasse EinfachantwortAufgabe (ohne Musterlösung, da bei der 1:1 Beziehung zwischen Aufgabe und Musterlösung
     * ein Objekt zuerst, ohne Beziehungspartner, erstellt werden muss)
     *
     * @param bearbeitungszeit      Bearbeitungszeit der Aufgabe
     * @param aufgabenstellungsbild Bild, das Teil der Aufgabenstellung ist
     * @param kategorie             Kategorie der Aufgabe
     * @param punktewert            Punktewert der Aufgabe
     * @param schwierigkeitsgrad    Schwierigkeitsgrad der Aufgabe
     * @param textbeschreibung      Textbeschreibung der Aufgabe
     * @param name                  Name der Aufgabe
     * @param aufgabenErsteller     Dozent, der die Aufgabe erstellt hat
     */
    public EinfachantwortAufgabe(int bearbeitungszeit, byte[] aufgabenstellungsbild, Kategorie kategorie, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, Dozent aufgabenErsteller) {
        super(bearbeitungszeit, aufgabenstellungsbild, kategorie, punktewert, schwierigkeitsgrad, textbeschreibung, name, aufgabenErsteller);
        this.aufgabentyp = Aufgabentyp.Einfachantwort;
    }

    /**
     * Setzt die Musterlösung der Aufgabe
     *
     * @param musterloesung Musterlösung der Aufgabe
     */
    @Override
    public void setMusterloesung(Musterloesung musterloesung) throws Exception {
        if (musterloesung == null) {
            this.musterloesung = null;
        } else {
            if (musterloesung.getClass() == MusterloesungEinfachantwort.class) {
                this.musterloesung = musterloesung;
            } else {
                throw new Exception("Einfachantwortaufgabe kann nur eine Musterlösung vom Typ Einfachantwortaufgabe erhalten");
            }
        }
    }

    /**
     * Fügt eine Userlösung zur Liste der Userlösungen der Aufgabe hinzu
     *
     * @param userloesung eine Userlösung der Aufgabe
     */
    @Override
    public void addUserloesung(Userloesung userloesung) throws Exception {
        if (userloesung.getClass() == UserloesungEinfachantwort.class) {
            this.userloesungen.add(userloesung);
        } else {
            throw new Exception("Einfachantwortaufgabe kann nur Userlösungen vom Typ Einfachantwortaufgabe erhalten");
        }
    }

    /**
     * Setzt die Liste der Userlösungen der Aufgabe
     *
     * @param userloesungen Liste der Userlösungen der Aufgabe
     */
    @Override
    public void setUserloesungen(List<Userloesung> userloesungen) throws Exception {
        if (userloesungen == null) {
            this.userloesungen = null;
        } else {
            if (userloesungen.size() > 0) {
                if (userloesungen.get(0).getClass() == UserloesungEinfachantwort.class) {
                    this.userloesungen = userloesungen;
                } else {
                    throw new Exception("Einfachantwortaufgabe kann nur Userlösungen vom Typ Einfachantwortaufgabe erhalten");
                }
            } else {
                this.userloesungen = new LinkedList<Userloesung>();
            }
        }
    }

}