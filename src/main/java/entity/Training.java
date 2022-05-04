package entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

/**
 * Aufgabensammlung, die von einem Benutzer generiert und bearbeitet wird
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
public class Training extends Aufgabensammlung {

    private int bearbeitungszeit;
    private String kategorie;
    private int schwierigkeitsgrad;
    private Aufgabentyp aufgabentyp;

    /**
     * Leerer Konstruktor für Klasse Training
     */
    public Training() {
        super();
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse Training
     *
     * @param aufgaben           Aufgaben, die im Training enthalten sind
     * @param bearbeitungszeit   Ausgewählte Bearbeitungszeit des Trainings
     * @param kategorie          Ausgewählte Kategorie des Testats
     * @param schwierigkeitsgrad Ausgewählter Schwierigkeitsgrad des Testats
     * @param aufgabentyp        Ausgewählter Aufgabentyp des Testats
     */
    public Training(List<Aufgabe> aufgaben, int bearbeitungszeit, String kategorie, int schwierigkeitsgrad, Aufgabentyp aufgabentyp) {
        super(aufgaben);
        this.bearbeitungszeit = bearbeitungszeit;
        this.kategorie = kategorie;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.aufgabentyp = aufgabentyp;
    }

    /**
     * Gibt die Bearbeitungszeit des Trainings zurück
     *
     * @return Bearbeitungszeit des Trainings
     */
    public int getBearbeitungszeit() {
        return bearbeitungszeit;
    }

    /**
     * Setzt die Bearbeitungszeit des Trainings
     *
     * @param bearbeitungszeit Bearbeitungszeit des Trainings
     */
    public void setBearbeitungszeit(int bearbeitungszeit) {
        this.bearbeitungszeit = bearbeitungszeit;
    }

    /**
     * Gibt die Kategorie des Trainings zurück
     *
     * @return Kategorie des Trainings
     */
    public String getKategorie() {
        return kategorie;
    }

    /**
     * Setzt die Kategorie des Trainings
     *
     * @param kategorie Kategorie des Trainings
     */
    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    /**
     * Gibt den Schwierigkeitsgrad des Trainings zurück
     *
     * @return Schwierigkeitsgrad des Trainings
     */
    public int getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    /**
     * Setzt den Schwierigkeitsgrad des Trainings
     *
     * @param schwierigkeitsgrad Schwierigkeitsgrad des Trainings
     */
    public void setSchwierigkeitsgrad(int schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    /**
     * Gibt den Aufgabentyp des Trainings zurück
     *
     * @return Aufgabentyp des Trainings
     */
    public Aufgabentyp getAufgabentyp() {
        return aufgabentyp;
    }

    /**
     * Setzt den Aufgabentyp des Trainings
     *
     * @param aufgabentyp Aufgabentyp des Trainings
     */
    public void setAufgabentyp(Aufgabentyp aufgabentyp) {
        this.aufgabentyp = aufgabentyp;
    }

}