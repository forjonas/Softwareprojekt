package entity;

import jakarta.persistence.Entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Aufgabe mit mehreren Antwortmöglichkeiten
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
public class MultipleChoiceAufgabe extends Aufgabe {

    private List<String> antwortmoeglichkeiten;
    private List<Boolean> musterloesung;
    private List<Boolean> userloesung;

    /**
     * Leerer Konstruktor für Klasse MultipleChoiceAufgabe
     */
    public MultipleChoiceAufgabe() {
        super();
        this.antwortmoeglichkeiten = new LinkedList<String>();
        this.musterloesung = new LinkedList<Boolean>();
        this.userloesung = new LinkedList<Boolean>();
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse MultipleChoiceAufgabe
     *
     * @param bearbeitungszeit      Bearbeitungszeit der Aufgabe
     * @param javaDesign            JavaDesign der Aufgabe
     * @param umlDesign             UmlDesign der Aufgabe
     * @param kategorie             Kategorie der Aufgabe
     * @param loesungshinweis       Lösungshinweis der Aufgabe
     * @param punktewert            Punktewert der Aufgabe
     * @param schwierigkeitsgrad    Schwierigkeitsgrad der Aufgabe
     * @param textbeschreibung      Textbeschreibung der Aufgabe
     * @param name                  Name der Aufgabe
     * @param antwortmoeglichkeiten Antwortmöglichkeiten der Aufgabe
     * @param musterloesung         Musterlösung der Aufgabe
     * @param userloesung           Userlösung der Aufgabe
     */
    public MultipleChoiceAufgabe(int bearbeitungszeit, String javaDesign, String umlDesign, Kategorie kategorie, String loesungshinweis,
                                 int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, List<String> antwortmoeglichkeiten,
                                 List<Boolean> musterloesung, List<Boolean> userloesung) {
        super(bearbeitungszeit, javaDesign, umlDesign, kategorie, loesungshinweis, punktewert, schwierigkeitsgrad, textbeschreibung, name);
        this.antwortmoeglichkeiten = antwortmoeglichkeiten;
        this.musterloesung = musterloesung;
        this.userloesung = userloesung;
        this.aufgabentyp = Aufgabentyp.MultipleChoice;
    }

    /**
     * Gibt die Antwortmöglichkeiten der Aufgabe zurück
     *
     * @return Antwortmöglichkeiten der Aufgabe
     * @param i
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
     * Gibt die Musterlösung der Aufgabe zurück
     *
     * @return Musterlösung der Aufgabe
     */
    public List<Boolean> getMusterloesung() {
        return musterloesung;
    }

    /**
     * Setzt die Musterlösung der Aufgabe
     *
     * @param musterloesung Musterlösung der Aufgabe
     */
    public void setMusterloesung(List<Boolean> musterloesung) {
        this.musterloesung = musterloesung;
    }

    /**
     * Gibt die Userlösung der Aufgabe zurück
     *
     * @return Userlösung der Aufgabe
     */
    public List<Boolean> getUserloesung() {
        return userloesung;
    }

    /**
     * Setzt die Userlösung der Aufgabe
     *
     * @param userloesung Userlösung der Aufgabe
     */
    public void setUserloesung(List<Boolean> userloesung) {
        this.userloesung = userloesung;
    }

}