package entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//import javax.swing.*;

/**
 * Aufgabe mit Design als Antwort
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
public class Designaufgabe extends Aufgabe {

    //Datentyp ändern
    private String musterloesung;
    //Datentyp ändern
    private String userloesung;
   // private ImageIcon iconBild;

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
   //  * @param iconBild           ImageIcon der Aufgabe  //von Kristin hinzugefügt 10.05.22 (evt. unvollständig)
     */
    public Designaufgabe(int bearbeitungszeit, String javaDesign, String umlDesign, Kategorie kategorie, String loesungshinweis, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, String musterloesung, String userloesung) { //, ImageIcon iconBild) {
        super(bearbeitungszeit, javaDesign, umlDesign, kategorie, loesungshinweis, punktewert, schwierigkeitsgrad, textbeschreibung, name);
        this.musterloesung = musterloesung;
        this.userloesung = userloesung;
        this.aufgabentyp = Aufgabentyp.Design;
        //this.iconBild = iconBild;
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
/**
    public ImageIcon getIconBild() {
        return iconBild;
    }

    public void setIconBild(ImageIcon iconBild) {
        this.iconBild = iconBild;
    }
 */
}