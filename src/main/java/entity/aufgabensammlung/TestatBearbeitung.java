package entity.aufgabensammlung;

import entity.aufgabensammlung.Testat;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Von einem Benutzer bearbeitetes Testat
 *
 * @author Jonas Herbst
 * @version 09.05.22
 */
@Entity
public class TestatBearbeitung extends Testat {

    private int erreichtePunktzahl;
    @ManyToOne(cascade = jakarta.persistence.CascadeType.PERSIST)
    @JoinColumn(name = "testatBearbeiter_benutzerid")
    private Benutzer testatBearbeiter;
    @ManyToOne(cascade = jakarta.persistence.CascadeType.PERSIST)
    @JoinColumn(name = "testatBewerter_benutzerid")
    private Dozent testatBewerter;

    /**
     * Leerer Konstruktor für Klasse TestatBearbeitung
     */
    public TestatBearbeitung() {
        super();
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse TestatBearbeitung
     *
     * @param testat             Testat, auf dessen Grundlage die TestatBearbeitung erstellt wird
     * @param erreichtePunktzahl Beim Bearbeiten vom Benutzer erreichte Punktzahl
     * @param testatBearbeiter   Benutzer, der das Testat bearbeitet hat (null wenn es noch nicht bearbeitet wurde)
     * @param testatBewerter     Dozent, der das Testat bewertete hat (null wenn es noch nicht bewertet wurde)
     */
    public TestatBearbeitung(Testat testat, int erreichtePunktzahl, Benutzer testatBearbeiter, Dozent testatBewerter) {
        super(testat.getAufgaben(), testat.getPasswort(), testat.getName(), testat.getTestatErsteller());
        this.erreichtePunktzahl = erreichtePunktzahl;
        this.testatBearbeiter = testatBearbeiter;
        this.testatBewerter = testatBewerter;
    }

    /**
     * Konstruktor für Klasse TestatBearbeitung (TestatBearbeitung, die noch nicht bearbeitet oder bewertete wurde)
     *
     * @param testat Testat, auf dessen Grundlage die TestatBearbeitung erstellt wird
     */
    public TestatBearbeitung(Testat testat) {
        super(testat.getAufgaben(), testat.getPasswort(), testat.getName(), testat.getTestatErsteller());
    }

    /**
     * Gibt die erreichte Punktzahl des Benutzers beim Bearbeiten des Testats zurück
     *
     * @return Erreichte Punktzahl bei der Bearbeitung des Testats
     */
    public int getErreichtePunktzahl() {
        return erreichtePunktzahl;
    }

    /**
     * Setzt die erreichte Punktzahl des Benutzers beim Bearbeiten des Testats
     *
     * @param erreichtePunktzahl Erreichte Punktzahl bei der Bearbeitung des Testats
     */
    public void setErreichtePunktzahl(int erreichtePunktzahl) {
        this.erreichtePunktzahl = erreichtePunktzahl;
    }

    /**
     * Gibt den Nutzer, der das Testat bearbeitet hat, zurück
     *
     * @return Nutzer, der das Testat bearbeitet hat
     */
    public Benutzer getTestatBearbeiter() {
        return testatBearbeiter;
    }

    /**
     * Setzt den Nutzer, der das Testat bearbeitet hat
     *
     * @param testatBearbeiter Nutzer, der das Testat bearbeitet hat
     */
    public void setTestatBearbeiter(Benutzer testatBearbeiter) {
        this.testatBearbeiter = testatBearbeiter;
    }

    /**
     * Gibt den Dozenten, der das Testat bewertet hat, zurück
     *
     * @return Dozent, der das Testat bewertet hat
     */
    public Dozent getTestatBewerter() {
        return testatBewerter;
    }

    /**
     * Setzt den Dozenten, der das Testat bewertet hat
     *
     * @param testatBewerter Dozent, der das Testat bewertet hat
     */
    public void setTestatBewerter(Dozent testatBewerter) {
        this.testatBewerter = testatBewerter;
    }

    /**
     * Bewertet ein Testat, indem die erreichte Punktzahl und der bewertende Dozent gesetzt werden
     *
     * @param erreichtePunktzahl Punktzahl, die beim erreicht wurde
     * @param testatBewerter     Dozent, der das Testat bewertet hat
     */
    public void bewerteTestat(int erreichtePunktzahl, Dozent testatBewerter) {
        this.erreichtePunktzahl = erreichtePunktzahl;
        this.testatBewerter = testatBewerter;
    }
}
