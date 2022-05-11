package entity;

import jakarta.persistence.*;

import java.io.File;

/**
 * Abstrakte Superklasse für Aufgaben
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity //Abstrakt nimmt er irgendwie nicht
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Aufgabe {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long aufgabenId;
    private int bearbeitungszeit;
    //Datentyp ändern
    private String javaDesign;
    //Datentyp ändern
    private File umlDesign;
    private Kategorie kategorie;
    private String loesungshinweis;
    private int punktewert;
    private Schwierigkeitsgrad schwierigkeitsgrad;
    private String textbeschreibung;
    private String name;
    protected Aufgabentyp aufgabentyp;

    /**
     * Leerer Konstruktor für Klasse Aufgabe
     */
    public Aufgabe() {
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse Aufgabe
     *
     * @param bearbeitungszeit   Bearbeitungszeit der Aufgabe
     * @param javaDesign         JavaDesign der Aufgabe
     * @param umlDesign          UmlDesign der Aufgabe
     * @param kategorie          Kategorie der Aufgabe
     * @param loesungshinweis    Lösungshinweis der Aufgabe
     * @param punktewert         Punktewert der Aufgabe
     * @param schwierigkeitsgrad Schwierigkeitsgrad der Aufgabe
     * @param textbeschreibung   Textbeschreibung der Aufgabe
     */
    public Aufgabe(int bearbeitungszeit, String javaDesign, File umlDesign, Kategorie kategorie, String loesungshinweis, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name) {
        this.bearbeitungszeit = bearbeitungszeit;
        this.javaDesign = javaDesign;
        this.umlDesign = umlDesign;
        this.kategorie = kategorie;
        this.loesungshinweis = loesungshinweis;
        this.punktewert = punktewert;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.textbeschreibung = textbeschreibung;
        this.name = name;
    }

    /**
     * Gibt die Bearbeitungszeit der Aufgabe zurück
     *
     * @return Bearbeitungszeit der Aufgabe
     */
    public int getBearbeitungszeit() {
        return bearbeitungszeit;
    }

    /**
     * Setzt die Bearbeitungszeit der Aufgabe
     *
     * @param bearbeitungszeit Bearbeitungszeit der Aufgabe
     */
    public void setBearbeitungszeit(int bearbeitungszeit) {
        this.bearbeitungszeit = bearbeitungszeit;
    }

    /**
     * Gibt das Java-Design der Aufgabe zurück
     *
     * @return Java-Design der Aufgabe
     */
    public String getJavaDesign() {
        return javaDesign;
    }

    /**
     * Setzt das Java-Design der Aufgabe
     *
     * @param javaDesign Java-Design der Aufgabe
     */
    public void setJavaDesign(String javaDesign) {
        this.javaDesign = javaDesign;
    }

    /**
     * Gibt das Uml-Design der Aufgabe zurück
     *
     * @return Uml-Design der Aufgabe
     */
    public File getUmlDesign() {
        return umlDesign;
    }

    /**
     * Setzt das Uml-Design der Aufgabe
     *
     * @param umlDesign Uml-Design der Aufgabe
     */
    public void setUmlDesign(File umlDesign) {
        this.umlDesign = umlDesign;
    }

    /**
     * Gibt die Kategorie der Aufgabe zurück
     *
     * @return Kategorie der Aufgabe
     */
    public Kategorie getKategorie() {
        return kategorie;
    }

    /**
     * Setzt die Kategorie der Aufgabe
     *
     * @param kategorie Kategorie der Aufgabe
     */
    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    /**
     * Gibt den Lösungshinweis der Aufgabe zurück
     *
     * @return Lösungshinweis der Aufgabe
     */
    public String getLoesungshinweis() {
        return loesungshinweis;
    }

    /**
     * Setzt den Lösungshinweis der Aufgabe
     *
     * @param loesungshinweis Lösungshinweis der Aufgabe
     */
    public void setLoesungshinweis(String loesungshinweis) {
        this.loesungshinweis = loesungshinweis;
    }

    /**
     * Gibt den Punktewert der Aufgabe zurück
     *
     * @return Punktewert der Aufgabe
     */
    public int getPunktewert() {
        return punktewert;
    }

    /**
     * Setzt den Punktewert der Aufgabe
     *
     * @param punktewert Punktewert der Aufgabe
     */
    public void setPunktewert(int punktewert) {
        this.punktewert = punktewert;
    }

    /**
     * Gibt den Schwierigkeitsgrad der Aufgabe zurück
     *
     * @return Schwierigkeitsgrad der Aufgabe
     */
    public Schwierigkeitsgrad getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    /**
     * Setzt den Schwierigkeitsgrad der Aufgabe
     *
     * @param schwierigkeitsgrad Schwierigkeitsgrad der Aufgabe
     */
    public void setSchwierigkeitsgrad(Schwierigkeitsgrad schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    /**
     * Gibt die Textbeschreibung der Aufgabe zurück
     *
     * @return Textbeschreibung der Aufgabe
     */
    public String getTextbeschreibung() {
        return textbeschreibung;
    }

    /**
     * Setzt die Textbeschreibung der Aufgabe
     *
     * @param textbeschreibung Textbeschreibung der Aufgabe
     */
    public void setTextbeschreibung(String textbeschreibung) {
        this.textbeschreibung = textbeschreibung;
    }

    /**
     * Gibt den Aufgabentyp der Aufgabe zurück
     *
     * @return Aufgabentyp der Aufgabe
     */
    public Aufgabentyp getAufgabentyp() {
        return aufgabentyp;
    }

    /**
     * Gibt den Namen der Aufgabe zurück
     *
     * @return Name der Aufgabe
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen der Aufgabe
     *
     * @param name Name der Aufgabe
     */
    public void setName(String name) {
        this.name = name;
    }
}