package entity.aufgabensammlung;

import entity.aufgabensammlung.Testat;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import jakarta.persistence.*;

/**
 * Von einem Benutzer bearbeitetes Testat
 *
 * @author Jonas Herbst
 * @version 09.05.22
 */
@Entity
public class TestatBearbeitung {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long TestatBearbeitungsId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "testat_testatid")
    private Testat testat;
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
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse TestatBearbeitung
     *
     * @param testat             Testat, auf dessen Grundlage die TestatBearbeitung erstellt wird
     * @param erreichtePunktzahl Beim Bearbeiten vom Benutzer erreichte Punktzahl (0 wenn es noch nicht bearbeitet wurde)
     * @param testatBearbeiter   Benutzer, der das Testat bearbeitet hat (null wenn es noch nicht bearbeitet wurde)
     * @param testatBewerter     Dozent, der das Testat bewertete hat (null wenn es noch nicht bewertet wurde)
     */
    public TestatBearbeitung(Testat testat, int erreichtePunktzahl, Benutzer testatBearbeiter, Dozent testatBewerter) {
        this.testat = testat;
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
        this.testat = testat;
    }

    /**
     * Gibt das zur Testatbearbeitung gehörige Testat zurück
     *
     * @return zur Testatbearbeitung gehöriges Testat
     */
    public Testat getTestat() {
        return testat;
    }

    /**
     * Setzt das zur Testatbearbeitung gehörige Testat
     *
     * @param testat zur Testatbearbeitung gehöriges Testat
     */
    public void setTestat(Testat testat) {
        this.testat = testat;
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

    /**
     * Gibt zurück, ob die Testatbearbeitung bereits bewertetet wurde
     *
     * @return Wahrheitswert, der angibt, ob die Testatbearbeitung bereits bewertetet wurde
     */
    public boolean isTestatBewertet() {
        return this.testatBewerter != null;
    }

    /**
     * Gibt zurück, ob die Testatbearbeitung bereits bearbeitet wurde
     *
     * @return Wahrheitswert, der angibt, ob die Testatbearbeitung bereits bearbeitet wurde
     */
    public boolean isTestatBearbeitet() {
        return this.testatBearbeiter != null;
    }

    /**
     * Gibt zurück, ob der übergebene Dozent die Testatbearbeitung bewerten darf
     *
     * @param dozent Dozent, für den überprüft wird, ob er die Testatbearbeitung bewerten darf
     * @return Wahrheitswert, der angibt, ob der übergebene Dozent die Testatbearbeitung bewerten darf
     */
    public boolean darfDozentTestatBearbeitungBewerten(Dozent dozent) {
        boolean leereTestatbearbeitung = (this.getTestat() == null || this.getTestat().getTestatErsteller() == null || this.getTestat().getAnzahlAufgaben() == 0);
        if(!leereTestatbearbeitung) {
            boolean selbstErstellt = (this.getTestat().getTestatErsteller() == dozent);
            boolean isAdmin = (dozent.getBenutzername() == "admin");
            return (selbstErstellt || isAdmin);
        } else {
            return true;
        }
    }

}