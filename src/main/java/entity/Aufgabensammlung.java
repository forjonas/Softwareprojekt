package entity;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstrakte Superklasse für Sammlungen von Aufgaben
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity //Abstrakt nimmt er irgendwie nicht
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Art", discriminatorType = DiscriminatorType.STRING)
public abstract class Aufgabensammlung {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long aufgabensammlungId;
    private List<Aufgabe> aufgaben;

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
        this.aufgaben = aufgaben;
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
    }

    /**
     * Fügt eine Aufgabe zur Liste der Aufgaben hinzu
     *
     * @param aufgabe Aufgabe, die hinzugefügt werden soll
     */
    public void addAufgabe(Aufgabe aufgabe) {
        aufgaben.add(aufgabe);
    }

    /**
     * Gibt die Anzahl der Aufgaben in der Aufgabensammlung zurück
     *
     * @return Anzahl der Aufgaben in der Aufgabensammlung
     */
    public int getAnzahlAufgaben() {
        return aufgaben.size();
    }

}