package entity.aufgabensammlung;

import entity.aufgabe.Aufgabe;
import entity.benutzer.Benutzer;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import jakarta.persistence.*;

import java.util.LinkedList;
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
    private Kategorie kategorie;
    private Schwierigkeitsgrad schwierigkeitsgrad;
    @ElementCollection
    private List<Aufgabentyp> aufgabentypen;
    @ManyToOne
    @JoinColumn(name = "trainingsErsteller_benutzerid")
    private Benutzer trainingsErsteller;

    /**
     * Leerer Konstruktor für Klasse Training
     */
    public Training() {
        super();
        this.aufgabentypen = new LinkedList<Aufgabentyp>();
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse Training
     *
     * @param aufgaben           Aufgaben, die im Training enthalten sind
     * @param bearbeitungszeit   Ausgewählte Bearbeitungszeit des Trainings
     * @param kategorie          Ausgewählte Kategorie des Testats
     * @param schwierigkeitsgrad Ausgewählter Schwierigkeitsgrad des Testats
     * @param aufgabentypen      Ausgewählte Aufgabentypen des Testats
     */
    public Training(List<Aufgabe> aufgaben, int bearbeitungszeit, Kategorie kategorie, Schwierigkeitsgrad schwierigkeitsgrad, List<Aufgabentyp> aufgabentypen) {
        super(aufgaben);
        this.bearbeitungszeit = bearbeitungszeit;
        this.kategorie = kategorie;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.aufgabentypen = new LinkedList<Aufgabentyp>();
        this.aufgabentypen.addAll(aufgabentypen);
    }

    /**
     * Konstruktor für Klasse Training
     *
     * @param aufgaben           Aufgaben, die im Training enthalten sind
     * @param bearbeitungszeit   Ausgewählte Bearbeitungszeit des Trainings
     * @param kategorie          Ausgewählte Kategorie des Testats
     * @param schwierigkeitsgrad Ausgewählter Schwierigkeitsgrad des Testats
     */
    public Training(List<Aufgabe> aufgaben, int bearbeitungszeit, Kategorie kategorie, Schwierigkeitsgrad schwierigkeitsgrad, List<Aufgabentyp> aufgabentypen, Benutzer trainingsErsteller) {
        super(aufgaben);
        this.bearbeitungszeit = bearbeitungszeit;
        this.kategorie = kategorie;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.aufgabentypen = new LinkedList<Aufgabentyp>();
        this.aufgabentypen.addAll(aufgabentypen);
        this.trainingsErsteller = trainingsErsteller;
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
    public Kategorie getKategorie() {
        return kategorie;
    }

    /**
     * Setzt die Kategorie des Trainings
     *
     * @param kategorie Kategorie des Trainings
     */
    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    /**
     * Gibt den Schwierigkeitsgrad des Trainings zurück
     *
     * @return Schwierigkeitsgrad des Trainings
     */
    public Schwierigkeitsgrad getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    /**
     * Setzt den Schwierigkeitsgrad des Trainings
     *
     * @param schwierigkeitsgrad Schwierigkeitsgrad des Trainings
     */
    public void setSchwierigkeitsgrad(Schwierigkeitsgrad schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    /**
     * Gibt die Aufgabentypen des Trainings zurück
     *
     * @return Aufgabentypen des Trainings
     */
    public List<Aufgabentyp> getAufgabentypen() {
        return aufgabentypen;
    }

    /**
     * Setzt die Aufgabentypen des Trainings
     *
     * @param aufgabentypen Aufgabentypen des Trainings
     */
    public void setAufgabentypen(List<Aufgabentyp> aufgabentypen) {
        this.aufgabentypen = aufgabentypen;
    }

    /**
     * Gibt den Ersteller des Trainings zurück
     *
     * @return Ersteller des Trainings
     */
    public Benutzer getTrainingsErsteller() {
        return trainingsErsteller;
    }

    /**
     * Setzt den Ersteller des Trainings
     *
     * @param trainingsErsteller Ersteller des Trainings
     */
    public void setTrainingsErsteller(Benutzer trainingsErsteller) {
        this.trainingsErsteller = trainingsErsteller;
    }

}