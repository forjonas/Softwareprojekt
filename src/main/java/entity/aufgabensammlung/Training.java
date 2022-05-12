package entity.aufgabensammlung;

import entity.aufgabe.Aufgabe;
import entity.benutzer.Benutzer;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import jakarta.persistence.*;

import java.util.List;

/**
 * Aufgabensammlung, die von einem Benutzer generiert und bearbeitet wird
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
public class Training extends Aufgabensammlung {
    //Sollte rausgenommen werden. Training hat bereits eine Id, da es von Aufgabensammlung ableitet
    // und ein Id sollte auch nicht manuell gesetzt oder ausgelesen werden. Sie dient nur zur Persistenz in der Datenbank.
    /*    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trainingID;*/

    private int bearbeitungszeit;
    private Kategorie kategorie;
    private Schwierigkeitsgrad schwierigkeitsgrad;
    private Aufgabentyp aufgabentyp;
    @ManyToOne
    @JoinColumn(name = "trainingsErsteller_benutzerid")
    private Benutzer trainingsErsteller;



/*    public Long getTrainingID() {
        return trainingID;
    }

    public void setTrainingID(Long trainingID) {
        this.trainingID = trainingID;
    }*/

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
    public Training(List<Aufgabe> aufgaben, int bearbeitungszeit, Kategorie kategorie, Schwierigkeitsgrad schwierigkeitsgrad, Aufgabentyp aufgabentyp) {
        super(aufgaben);
        this.bearbeitungszeit = bearbeitungszeit;
        this.kategorie = kategorie;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.aufgabentyp = aufgabentyp;
    }

    /**
     * Konstruktor für Klasse Training
     *
     * @param aufgaben           Aufgaben, die im Training enthalten sind
     * @param bearbeitungszeit   Ausgewählte Bearbeitungszeit des Trainings
     * @param kategorie          Ausgewählte Kategorie des Testats
     * @param schwierigkeitsgrad Ausgewählter Schwierigkeitsgrad des Testats
     */
    public Training(List<Aufgabe> aufgaben, int bearbeitungszeit, Kategorie kategorie, Schwierigkeitsgrad schwierigkeitsgrad, Aufgabentyp aufgabentyp, Benutzer trainingsErsteller) {
        super(aufgaben);
        this.bearbeitungszeit = bearbeitungszeit;
        this.kategorie = kategorie;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.aufgabentyp = aufgabentyp;
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