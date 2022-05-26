package entity.aufgabe;

import entity.benutzer.Dozent;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.Musterloesung;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;
import entity.loesung.userloesung.Userloesung;
import entity.loesung.userloesung.UserloesungProgrammieraufgabe;
import jakarta.persistence.Entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Aufgabe mit Programmcode als Antwort
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
public class Programmieraufgabe extends Aufgabe {

    /**
     * Leerer Konstruktor für Klasse Programmieraufgabe
     */
    public Programmieraufgabe() {
        super();
        this.aufgabentyp = Aufgabentyp.Programmieren;
    }

    /**
     * Konstruktor für Klasse Programmieraufgabe
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
    public Programmieraufgabe(int bearbeitungszeit, byte[] aufgabenstellungsbild, Kategorie kategorie, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, Dozent aufgabenErsteller, MusterloesungProgrammieraufgabe musterloesung) {
        super(bearbeitungszeit, aufgabenstellungsbild, kategorie, punktewert, schwierigkeitsgrad, textbeschreibung, name, aufgabenErsteller, musterloesung);
        this.aufgabentyp = Aufgabentyp.Programmieren;
    }

    /**
     * Konstruktor für Klasse Programmieraufgabe (ohne Musterlösung, da bei der 1:1 Beziehung zwischen Aufgabe und Musterlösung
     * * ein Objekt zuerst, ohne Beziehungspartner, erstellt werden muss)
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
    public Programmieraufgabe(int bearbeitungszeit, byte[] aufgabenstellungsbild, Kategorie kategorie, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, Dozent aufgabenErsteller) {
        super(bearbeitungszeit, aufgabenstellungsbild, kategorie, punktewert, schwierigkeitsgrad, textbeschreibung, name, aufgabenErsteller);
        this.aufgabentyp = Aufgabentyp.Programmieren;
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
            if (musterloesung.getClass() == MusterloesungProgrammieraufgabe.class) {
                this.musterloesung = musterloesung;
            } else {
                throw new Exception("Programmieraufgabe kann nur eine Musterlösung vom Typ Programmieraufgabe erhalten");
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
        if (userloesung.getClass() == UserloesungProgrammieraufgabe.class) {
            this.userloesungen.add(userloesung);
        } else {
            throw new Exception("Programmieraufgabe kann nur Userlösungen vom Typ Programmieraufgabe erhalten");
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
                if (userloesungen.get(0).getClass() == UserloesungProgrammieraufgabe.class) {
                    this.userloesungen = userloesungen;
                } else {
                    throw new Exception("Programmieraufgabe kann nur Userlösungen vom Typ Programmieraufgabe erhalten");
                }
            } else {
                this.userloesungen = new LinkedList<Userloesung>();
            }
        }
    }

}