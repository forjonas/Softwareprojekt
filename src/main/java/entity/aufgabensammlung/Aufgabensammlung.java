package entity.aufgabensammlung;

import entity.aufgabe.Aufgabe;
import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstrakte Superklasse für Sammlungen von Aufgaben
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Aufgabensammlung {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long aufgabensammlungId;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Aufgabe> aufgaben;
    private int gesamtzeit;
    private int gesamtpunktzahl;

    /**
     * Leerer Konstruktor für Klasse Aufgabensammlung
     */
    public Aufgabensammlung() {
        this.aufgaben = new LinkedList<Aufgabe>();
    }

    /**
     * Konstruktor für Klasse Aufgabensammlung
     *
     * @param aufgaben Aufgaben, die in der Aufgabensammlung enthalten sind
     */
    public Aufgabensammlung(List<Aufgabe> aufgaben) {
        this.aufgaben = new LinkedList<Aufgabe>();
        this.aufgaben.addAll(aufgaben);
        aktualisiereGesamtpunktzahl();
        aktualisierteGesamtzeit();
    }

    /**
     * Gibt die Liste der Aufgaben zurück
     *
     * @return Liste der Aufgaben
     */
    public List<Aufgabe> getAufgaben() {
        return aufgaben;
    }

    /**
     * Setzt die Liste der Aufgaben
     *
     * @param aufgaben Liste der Aufgaben
     */
    public void setAufgaben(List<Aufgabe> aufgaben) {
        this.aufgaben = aufgaben;
        aktualisiereGesamtpunktzahl();
        aktualisierteGesamtzeit();
    }

    /**
     * Fügt eine Aufgabe zur Liste der Aufgaben hinzu
     *
     * @param aufgabe Aufgabe, die hinzugefügt werden soll
     */
    public void addAufgabe(Aufgabe aufgabe) {
        aufgaben.add(aufgabe);
        gesamtpunktzahl += aufgabe.getPunktewert();
        gesamtzeit += aufgabe.getBearbeitungszeit();
    }

    /**
     * Gibt die Anzahl der Aufgaben in der Aufgabensammlung zurück
     *
     * @return Anzahl der Aufgaben in der Aufgabensammlung
     */
    public int getAnzahlAufgaben() {
        return aufgaben.size();
    }

    /**
     * Gibt die gesamte Bearbeitungszeit der Aufgabensammlung zurück
     *
     * @return gesamte Bearbeitungszeit der Aufgabensammlung
     */
    public int getGesamtzeit() {
        return gesamtzeit;
    }

    /**
     * Hilfsmethode zum Aktualisieren der Gesamtzeit der Aufgabensammlung
     */
    private void aktualisierteGesamtzeit() {
        gesamtzeit = 0;
        for (Aufgabe a : getAufgaben()) {
            gesamtzeit += a.getBearbeitungszeit();
        }
    }

    /**
     * Gibt gesamte Punktzahl der Aufgabensammlung zurück
     *
     * @return gesamte Punktzahl der Aufgabensammlung
     */
    public int getGesamtpunktzahl() {
        return gesamtpunktzahl;
    }

    /**
     * Hilfsmethode zum Aktualisieren der Gesamtpunktzahl der Aufgabensammlung
     */
    private void aktualisiereGesamtpunktzahl() {
        gesamtpunktzahl = 0;
        for (Aufgabe a : getAufgaben()) {
            gesamtpunktzahl += a.getPunktewert();
        }
    }
}