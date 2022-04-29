package entity;

import jakarta.persistence.*;

/**
 * Abstrakte Superklasse für Benutzer
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Art", discriminatorType = DiscriminatorType.STRING)
public abstract class Benutzer {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long benutzerId;
    private String benutzername;
    private String passwort;
    private int berechtigungsstufe;
    private String vorname;
    private String nachname;

    /**
     * Leerer Konstruktor für Klasse Benutzer
     */
    public Benutzer() {
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse Benutzer
     *
     * @param benutzername       Benutzername des Benutzers
     * @param passwort           Passwort des Benutzers
     * @param berechtigungsstufe Berechtigungsstufe des Benutzers
     * @param vorname            Vorname des Benutzers
     * @param nachname           Nachname des Benutzers
     */
    public Benutzer(String benutzername, String passwort, int berechtigungsstufe, String vorname, String nachname) {
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.berechtigungsstufe = berechtigungsstufe;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    /**
     * Gibt den Benutzernamen des Benutzers zurück
     *
     * @return Benutzername des Benutzers
     */
    public String getBenutzername() {
        return benutzername;
    }

    /**
     * Setzt den Benutzernamen des Benutzers
     *
     * @param benutzername Benutzername des Benutzers
     */
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    /**
     * Gibt das Passwort des Benutzers zurück
     *
     * @return Passwort des Benutzers
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Setzt das Passwort des Benutzers
     *
     * @param passwort Passwort des Benutzers
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    /**
     * Gibt die Berechtigungsstufe des Benutzers zurück
     *
     * @return Berechtigungsstufe des Benutzers
     */
    public int getBerechtigungsstufe() {
        return berechtigungsstufe;
    }

    /**
     * Setzt die Berechtigungsstufe des Benutzers
     *
     * @param berechtigungsstufe Berechtigungsstufe des Benutzers
     */
    public void setBerechtigungsstufe(int berechtigungsstufe) {
        this.berechtigungsstufe = berechtigungsstufe;
    }

    /**
     * Gibt den Vornamen des Benutzers zurück
     *
     * @return Vorname des Benutzers
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Setzt den Vornamen des Benutzers
     *
     * @param vorname Vorname des Benutzers
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gibt den Nachnamen des Benutzers zurück
     *
     * @return Nachname des Benutzers
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * Setzt den Nachnamen des Benutzers
     *
     * @param nachname Nachname des Benutzers
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Gibt den Benutzer als String aus
     *
     * @return Benutzer als String
     */
    @Override
    public String toString() {
        return "{" +
                "benutzerId=" + benutzerId +
                ", benutzername='" + benutzername + '\'' +
                ", passwort='" + passwort + '\'' +
                ", berechtigungsstufe=" + berechtigungsstufe +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'';
    }
}