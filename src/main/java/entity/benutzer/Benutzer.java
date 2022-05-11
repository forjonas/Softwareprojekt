package entity.benutzer;

import entity.aufgabensammlung.TestatBearbeitung;
import entity.aufgabensammlung.Training;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Abstrakte Superklasse für Benutzer
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Benutzer implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "benutzerId")
    private long benutzerId;
    private String benutzername;
    private String passwort;
    private int berechtigungsstufe;
    private String vorname;
    private String nachname;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "trainingsErsteller")
    private List<Training> bearbeiteteTrainings;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "testatBearbeiter")
    private List<TestatBearbeitung> bearbeiteteTestate;

    /**
     * Leerer Konstruktor für Klasse Benutzer
     */
    public Benutzer() {
        this.bearbeiteteTrainings = new LinkedList<Training>();
        this.bearbeiteteTestate = new LinkedList<TestatBearbeitung>();
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
        this.bearbeiteteTrainings = new LinkedList<Training>();
        this.bearbeiteteTestate = new LinkedList<TestatBearbeitung>();
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
     * Gibt die Liste der bearbeiteten Trainings des Benutzers zurück
     *
     * @return bearbeitete Trainings des Benutzers
     */
    public List<Training> getBearbeiteteTrainings() {
        return bearbeiteteTrainings;
    }

    /**
     * Fügt ein bearbeitetes Training des Benutzers zur Liste der bearbeiteten Trainings hinzu
     *
     * @param bearbeitetesTraining bearbeitetes Training des Benutzers
     */
    public void addBearbeitetesTraining(Training bearbeitetesTraining) {
        this.bearbeiteteTrainings.add(bearbeitetesTraining);
    }

    /**
     * Setzt die Liste der bearbeiteten Trainings des Benutzers
     * --> vielleicht rausnehmen oder auf private setzen
     *
     * @param bearbeiteteTrainings bearbeitete Trainings des Benutzers
     */
    public void setBearbeiteteTrainings(List<Training> bearbeiteteTrainings) {
        this.bearbeiteteTrainings = bearbeiteteTrainings;
    }

    /**
     * Gibt die Liste der bearbeiteten Testate des Benutzers zurück
     *
     * @return bearbeitete Testate des Benutzers
     */
    public List<TestatBearbeitung> getBearbeiteteTestate() {
        return bearbeiteteTestate;
    }

    /**
     * Fügt ein bearbeitetes Testat des Benutzers zur Liste der bearbeiteten Testate hinzu
     *
     * @param bearbeitetesTestat bearbeitetes Testat des Benutzers
     */
    public void addBearbeitetesTestat(TestatBearbeitung bearbeitetesTestat) {
        this.bearbeiteteTestate.add(bearbeitetesTestat);
    }

    /**
     * Setzt die Liste der bearbeiteten Testate des Benutzers
     * --> vielleicht rausnehmen oder auf private setzen
     *
     * @param bearbeiteteTestate bearbeitete Testate des Benutzers
     */
    public void setBearbeiteteTestate(List<TestatBearbeitung> bearbeiteteTestate) {
        this.bearbeiteteTestate = bearbeiteteTestate;
    }

    /**
     * Gibt den Benutzer als String aus
     *
     * @return Benutzer als String
     */
    @Override
    public String toString() {
        return "benutzerId=" + benutzerId +
                ", benutzername='" + benutzername + '\'' +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'';
    }

}