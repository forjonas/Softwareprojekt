package entity.aufgabe;

import entity.benutzer.Dozent;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.Musterloesung;
import entity.loesung.musterloesung.MusterloesungMultipleChoiceAufgabe;
import entity.loesung.userloesung.Userloesung;
import entity.loesung.userloesung.UserloesungMultipleChoiceAufgabe;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Aufgabe mit mehreren Antwortmöglichkeiten
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
@Entity
public class MultipleChoiceAufgabe extends Aufgabe {

    @ElementCollection
    private List<String> antwortmoeglichkeiten;

    /**
     * Leerer Konstruktor für Klasse MultipleChoiceAufgabe
     */
    public MultipleChoiceAufgabe() {
        super();
        this.antwortmoeglichkeiten = new LinkedList<String>();
        this.aufgabentyp = Aufgabentyp.MultipleChoice;
    }

    /**
     * Konstruktor für Klasse MultipleChoiceAufgabe (ohne Musterlösung, da bei der 1:1 Beziehung zwischen Aufgabe und Musterlösung
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
     * @param antwortmoeglichkeiten Antwortmöglichkeiten der Aufgabe
     * @param musterloesung         Musterlösung der Aufgabe
     */
    public MultipleChoiceAufgabe(int bearbeitungszeit, byte[] aufgabenstellungsbild, Kategorie kategorie, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, Dozent aufgabenErsteller, List<String> antwortmoeglichkeiten, MusterloesungMultipleChoiceAufgabe musterloesung) {
        super(bearbeitungszeit, aufgabenstellungsbild, kategorie, punktewert, schwierigkeitsgrad, textbeschreibung, name, aufgabenErsteller, musterloesung);
        this.antwortmoeglichkeiten = antwortmoeglichkeiten;
        this.aufgabentyp = Aufgabentyp.MultipleChoice;
    }

    /**
     * Konstruktor für Klasse MultipleChoiceAufgabe
     *
     * @param bearbeitungszeit      Bearbeitungszeit der Aufgabe
     * @param aufgabenstellungsbild Bild, das Teil der Aufgabenstellung ist
     * @param kategorie             Kategorie der Aufgabe
     * @param punktewert            Punktewert der Aufgabe
     * @param schwierigkeitsgrad    Schwierigkeitsgrad der Aufgabe
     * @param textbeschreibung      Textbeschreibung der Aufgabe
     * @param name                  Name der Aufgabe
     * @param aufgabenErsteller     Dozent, der die Aufgabe erstellt hat
     * @param antwortmoeglichkeiten Antwortmöglichkeiten der Aufgabe
     */
    public MultipleChoiceAufgabe(int bearbeitungszeit, byte[] aufgabenstellungsbild, Kategorie kategorie, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, Dozent aufgabenErsteller, List<String> antwortmoeglichkeiten) {
        super(bearbeitungszeit, aufgabenstellungsbild, kategorie, punktewert, schwierigkeitsgrad, textbeschreibung, name, aufgabenErsteller);
        this.antwortmoeglichkeiten = antwortmoeglichkeiten;
        this.aufgabentyp = Aufgabentyp.MultipleChoice;
    }

    /**
     * Gibt die Antwortmöglichkeiten der Aufgabe zurück
     *
     * @return Antwortmöglichkeiten der Aufgabe
     */
    public List<String> getAntwortmoeglichkeiten() {
        return antwortmoeglichkeiten;
    }

    /**
     * Setzt die Antwortmöglichkeiten der Aufgabe
     *
     * @param antwortmoeglichkeiten Antwortmöglichkeiten der Aufgabe
     */
    public void setAntwortmoeglichkeiten(List<String> antwortmoeglichkeiten) {
        this.antwortmoeglichkeiten = antwortmoeglichkeiten;
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
            if (musterloesung.getClass() == MusterloesungMultipleChoiceAufgabe.class) {
                this.musterloesung = musterloesung;
            } else {
                throw new Exception("MultipleChoiceAufgabe kann nur eine Musterlösung vom Typ MultipleChoiceAufgabe erhalten");
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
        if (userloesung.getClass() == UserloesungMultipleChoiceAufgabe.class) {
            this.userloesungen.add(userloesung);
        } else {
            throw new Exception("MultipleChoiceAufgabe kann nur Userlösungen vom Typ MultipleChoiceAufgabe erhalten");
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
                if (userloesungen.get(0).getClass() == UserloesungMultipleChoiceAufgabe.class) {
                    this.userloesungen = userloesungen;
                } else {
                    throw new Exception("MultipleChoiceAufgabe kann nur Userlösungen vom Typ MultipleChoiceAufgabe erhalten");
                }
            } else {
                this.userloesungen = new LinkedList<Userloesung>();
            }
        }
    }

}