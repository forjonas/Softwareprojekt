package entity;

import jakarta.persistence.*;

import java.io.File;
import java.math.BigInteger;

/**
 * Aufgabe mit Design als Antwort
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
public class Designaufgabe extends Aufgabe {

    //Datentyp ändern
    private File musterloesung;
    //Datentyp ändern
    private String userloesung;

    /**
     * Leerer Konstruktor für Klasse Designaufgabe
     */
    public Designaufgabe() {
        super();
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse Designaufgabe
     *
     * @param bearbeitungszeit   Bearbeitungszeit der Aufgabe
     * @param javaDesign         JavaDesign der Aufgabe
     * @param umlDesign          UmlDesign der Aufgabe
     * @param kategorie          Kategorie der Aufgabe
     * @param loesungshinweis    Lösungshinweis der Aufgabe
     * @param punktewert         Punktewert der Aufgabe
     * @param schwierigkeitsgrad Schwierigkeitsgrad der Aufgabe
     * @param textbeschreibung   Textbeschreibung der Aufgabe
     * @param name               Name der Aufgabe
     * @param musterloesung      Musterlösung der Aufgabe
     * @param userloesung        Userlösung der Aufgabe
     */
    public Designaufgabe(int bearbeitungszeit, String javaDesign, File umlDesign, Kategorie kategorie, String loesungshinweis, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, File musterloesung, String userloesung) {
        super(bearbeitungszeit, javaDesign, umlDesign, kategorie, loesungshinweis, punktewert, schwierigkeitsgrad, textbeschreibung, name);
        this.musterloesung = musterloesung;
        this.userloesung = userloesung;
        this.aufgabentyp = Aufgabentyp.Design;
    }

    /**
     * Gibt die Musterlösung der Aufgabe zurück
     *
     * @return Musterlösung der Aufgabe
     */
    public File getMusterloesung() {
        return musterloesung;
    }

    /**
     * Setzt die Musterlösung der Aufgabe
     *
     * @param musterloesung Musterlösung der Aufgabe
     */
    public void setMusterloesung(File musterloesung) {
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