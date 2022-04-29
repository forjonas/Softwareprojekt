package entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Aufgabe mit Programmcode als Antwort
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
@DiscriminatorValue("Programmieraufgabe")
public class Programmieraufgabe extends Aufgabe {

    private String musterloesung;
    private String userloesung;

    /**
     * Leerer Konstruktor für Klasse Programmieraufgabe
     */
    public Programmieraufgabe() {
        super();
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse Programmieraufgabe
     *
     * @param bearbeitungszeit   Bearbeitungszeit der Aufgabe
     * @param javaDesign         JavaDesign der Aufgabe
     * @param umlDesign          UmlDesign der Aufgabe
     * @param kategorie          Kategorie der Aufgabe
     * @param loesungshinweis    Lösungshinweis der Aufgabe
     * @param punktewert         Punktewert der Aufgabe
     * @param schwierigkeitsgrad Schwierigkeitsgrad der Aufgabe
     * @param textbeschreibung   Textbeschreibung der Aufgabe
     * @param musterloesung      Musterlösung der Aufgabe
     * @param userloesung        Userlösung der Aufgabe
     */
    public Programmieraufgabe(int bearbeitungszeit, String javaDesign, String umlDesign, String kategorie, String loesungshinweis, int punktewert, int schwierigkeitsgrad, String textbeschreibung, String musterloesung, String userloesung) {
        super(bearbeitungszeit, javaDesign, umlDesign, kategorie, loesungshinweis, punktewert, schwierigkeitsgrad, textbeschreibung);
        this.musterloesung = musterloesung;
        this.userloesung = userloesung;
    }

    /**
     * Gibt die Musterlösung der Aufgabe zurück
     *
     * @return Musterlösung der Aufgabe
     */
    public String getMusterloesung() {
        return musterloesung;
    }

    /**
     * Setzt die Musterlösung der Aufgabe
     *
     * @param musterloesung Musterlösung der Aufgabe
     */
    public void setMusterloesung(String musterloesung) {
        this.musterloesung = musterloesung;
    }

    /**
     * Gibt die Userlösung der Aufgabe zurück
     *
     * @return Userlösung der Aufgabe
     */
    public String getUserloesung() {
        return userloesung;
    }

    /**
     * Setzt die Userlösung der Aufgabe
     *
     * @param userloesung Userlösung der Aufgabe
     */
    public void setUserloesung(String userloesung) {
        this.userloesung = userloesung;
    }

}