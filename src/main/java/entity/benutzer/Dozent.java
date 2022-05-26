package entity.benutzer;

import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.aufgabe.Aufgabe;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Benutzer vom Typ Dozent
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
public class Dozent extends Benutzer implements Serializable {

    @OneToMany(cascade = jakarta.persistence.CascadeType.PERSIST, mappedBy = "testatBewerter")
    private List<TestatBearbeitung> bewerteteTestate;
    @OneToMany(cascade = jakarta.persistence.CascadeType.PERSIST, mappedBy = "testatErsteller")
    private List<Testat> erstellteTestate;
    @OneToMany(cascade = jakarta.persistence.CascadeType.PERSIST, mappedBy = "aufgabenErsteller")
    private List<Aufgabe> erstellteAufgaben;

    /**
     * Leerer Konstruktor für Klasse Dozent
     */
    public Dozent() {
        super();
        this.bewerteteTestate = new LinkedList<TestatBearbeitung>();
        this.erstellteTestate = new LinkedList<Testat>();
        this.erstellteAufgaben = new LinkedList<Aufgabe>();
    }

    /**
     * Konstruktor für Klasse Dozent
     *
     * @param benutzername Benutzername des Dozenten
     * @param passwort     Passwort des Dozenten
     * @param vorname      Vorname des Dozenten
     * @param nachname     Nachname des Dozenten
     */
    public Dozent(String benutzername, String passwort, String vorname, String nachname) {
        super(benutzername, passwort, 2, vorname, nachname);
        this.bewerteteTestate = new LinkedList<TestatBearbeitung>();
        this.erstellteTestate = new LinkedList<Testat>();
        this.erstellteAufgaben = new LinkedList<Aufgabe>();
    }

    /**
     * Gibt die Liste der bewerteten Testate des Dozenten zurück
     *
     * @return bewertete Testate des Dozenten
     */
    public List<TestatBearbeitung> getBewerteteTestate() {
        return bewerteteTestate;
    }

    /**
     * Fügt ein bewertetes Testat des Dozenten zur Liste der bewerteten Testate hinzu
     *
     * @param bewertetesTestat bewertetes Testat des Dozenten
     */
    public void addBewertetesTestat(TestatBearbeitung bewertetesTestat) {
        this.bewerteteTestate.add(bewertetesTestat);
    }

    /**
     * Entfernt ein bewertetes Testat des Dozenten aus der Liste der bewerteten Testate
     *
     * @param bewertetesTestat bewertetes Testat des Dozenten
     */
    public void removeBewertetesTestat(TestatBearbeitung bewertetesTestat) {
        this.bewerteteTestate.remove(bewertetesTestat);
    }

    /**
     * Setzt die Liste der bewerteten Testate des Dozenten
     * --> vielleicht rausnehmen oder auf private setzen
     *
     * @param bewerteteTestate bewertete Testate des Doznenten
     */
    public void setBewerteteTestate(List<TestatBearbeitung> bewerteteTestate) {
        this.bewerteteTestate = bewerteteTestate;
    }

    /**
     * Gibt die Liste der erstellten Testate des Dozenten zurück
     *
     * @return erstellte Testate des Dozenten
     */
    public List<Testat> getErstellteTestate() {
        return erstellteTestate;
    }

    /**
     * Fügt ein erstelltes Testat des Dozenten zur Liste der erstellten Testate hinzu
     *
     * @param erstelltesTestat erstelltes Testat des Dozenten
     */
    public void addErstelltesTestat(Testat erstelltesTestat) {
        this.erstellteTestate.add(erstelltesTestat);
    }

    /**
     * Entfernt ein erstelltes Testat des Dozenten aus der Liste der erstellten Testate
     *
     * @param erstelltesTestat erstelltes Testat des Dozenten
     */
    public void removeErstelltesTestat(Testat erstelltesTestat) {
        this.erstellteTestate.remove(erstelltesTestat);
    }

    /**
     * Setzt die Liste der erstellten Testate des Dozenten
     * --> vielleicht rausnehmen oder auf private setzen
     *
     * @param erstellteTestate erstellte Testate des Doznenten
     */
    public void setErstellteTestate(List<Testat> erstellteTestate) {
        this.erstellteTestate = erstellteTestate;
    }

    /**
     * Gibt die Liste der erstellten Aufgaben des Dozenten zurück
     *
     * @return erstellte Aufgaben des Dozenten
     */
    public List<Aufgabe> getErstellteAufgaben() {
        return erstellteAufgaben;
    }

    /**
     * Fügt eine erstellte Aufgabe des Dozenten zur Liste der erstellten Aufgaben hinzu
     *
     * @param erstellteAufgabe erstellte Aufgabe des Dozenten
     */
    public void addErstellteAufgabe(Aufgabe erstellteAufgabe) {
        this.erstellteAufgaben.add(erstellteAufgabe);
    }

    /**
     * Entfernt eine erstellte Aufgabe des Dozenten aus der Liste der erstellten Aufgaben
     *
     * @param erstellteAufgabe erstellte Aufgabe des Dozenten
     */
    public void removeErstellteAufgabe(Aufgabe erstellteAufgabe) {
        this.erstellteAufgaben.remove(erstellteAufgabe);
    }

    /**
     * Setzt die Liste der erstellten Aufgaben des Dozenten
     * --> vielleicht rausnehmen oder auf private setzen
     *
     * @param erstellteAufgaben erstellte Aufgaben des Doznenten
     */
    public void setErstellteAufgaben(List<Aufgabe> erstellteAufgaben) {
        this.erstellteAufgaben = erstellteAufgaben;
    }

    /**
     * Gibt den Dozenten als String aus
     *
     * @return Dozent als String
     */
    @Override
    public String toString() {
        return "Dozent: {" + super.toString() + "}";
    }
}