package entity.aufgabe;

import entity.benutzer.Dozent;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.Musterloesung;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.userloesung.Userloesung;
import entity.loesung.userloesung.UserloesungDesignaufgabe;
import jakarta.persistence.Entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Aufgabe mit Design als Antwort
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
@Entity
public class Designaufgabe extends Aufgabe {

    /**
     * Leerer Konstruktor für Klasse Designaufgabe
     */
    public Designaufgabe() {
        super();
        this.aufgabentyp = Aufgabentyp.Design;
    }

    /**
     * Konstruktor für Klasse Designaufgabe
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
    public Designaufgabe(int bearbeitungszeit, byte[] aufgabenstellungsbild, Kategorie kategorie, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, Dozent aufgabenErsteller, MusterloesungDesignaufgabe musterloesung) {
        super(bearbeitungszeit, aufgabenstellungsbild, kategorie, punktewert, schwierigkeitsgrad, textbeschreibung, name, aufgabenErsteller, musterloesung);
        this.aufgabentyp = Aufgabentyp.Design;
    }

    /**
     * Konstruktor für Klasse Designaufgabe (ohne Musterlösung, da bei der 1:1 Beziehung zwischen Aufgabe und Musterlösung
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
    public Designaufgabe(int bearbeitungszeit, byte[] aufgabenstellungsbild, Kategorie kategorie, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, Dozent aufgabenErsteller) {
        super(bearbeitungszeit, aufgabenstellungsbild, kategorie, punktewert, schwierigkeitsgrad, textbeschreibung, name, aufgabenErsteller);
        this.aufgabentyp = Aufgabentyp.Design;
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
            if (musterloesung.getClass() == MusterloesungDesignaufgabe.class) {
                this.musterloesung = musterloesung;
            } else {
                throw new Exception("Designaufgabe kann nur eine Musterlösung vom Typ Designaufgabe erhalten");
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
        if (userloesung.getClass() == UserloesungDesignaufgabe.class) {
            this.userloesungen.add(userloesung);
        } else {
            throw new Exception("Designaufgabe kann nur Userlösungen vom Typ Designaufgabe erhalten");
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
                if (userloesungen.get(0).getClass() == UserloesungDesignaufgabe.class) {
                    this.userloesungen = userloesungen;
                } else {
                    throw new Exception("Designaufgabe kann nur Userlösungen vom Typ Designaufgabe erhalten");
                }
            } else {
                this.userloesungen = new LinkedList<Userloesung>();
            }
        }
    }

}