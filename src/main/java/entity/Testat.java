package entity;

import jakarta.persistence.Entity;

import java.util.List;

/**
 * Aufgabensammlung, die von einem Dozenten erstellt und bewertet und von einem Studenten bearbeitet wird
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
public class Testat extends Aufgabensammlung {

    private String bewertung;
    private String passwort;
    private String name;

    /**
     * Leerer Konstruktor für Klasse Testat
     */
    public Testat() {
        super();
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse Testat
     *
     * @param aufgaben  Aufgaben, die im Testat enthalten sind
     * @param bewertung Bewertung des Testats
     * @param passwort  Passwort des Testats
     * @param name      Name des Testats
     */
    public Testat(List<Aufgabe> aufgaben, String bewertung, String passwort, String name) {
        super(aufgaben);
        this.bewertung = bewertung;
        this.passwort = passwort;
        this.name = name;
    }

    /**
     * Gibt die Bewertung des Testats zurück
     *
     * @return Bewertung des Testats
     */
    public String getBewertung() {
        return bewertung;
    }

    /**
     * Setzt die Bewertung des Testats
     *
     * @param bewertung Bewertung des Testats
     */
    public void setBewertung(String bewertung) {
        this.bewertung = bewertung;
    }

    /**
     * Gibt das Passwort des Testats zurück
     *
     * @return Passwort des Testats
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Setzt die Bewertung des Testats
     *
     * @param passwort Bewertung des Testats
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    /**
     * Gibt den Namen des Testats zurück
     *
     * @return Name des Testats
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Testats
     *
     * @param name Bewertung des Testats
     */
    public void setName(String name) {
        this.name = name;
    }

}