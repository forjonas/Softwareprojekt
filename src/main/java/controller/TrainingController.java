package controller;

import view.*;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.benutzer.Student;
import entity.loesung.userloesung.Userloesung;
import persistence.DatabaseService;

import javax.swing.*;

/**
 * @author Kristin Kubisch
 * @version: 31.05.22
 * Schnittstelle um ein Training auszuführen
 */

public class TrainingController extends BearbeitungsController {

    protected Training training;

    /**
     * @param training
     * @param aktuellerBenutzer
     * @param hauptmenueFrame
     */
    public TrainingController(Training training, Benutzer aktuellerBenutzer, JFrame hauptmenueFrame) {
        super(training, aktuellerBenutzer, hauptmenueFrame);
        this.training = training;
        super.zeigeAktuelleAufgabe();
    }

    /**
     * Setzte die passende Ansicht für den Benutzer
     */
    public void setNewTrainingKatalog() {

        if (aktuellerBenutzer.getClass() == Dozent.class) {
            new DozentMainView((Dozent) aktuellerBenutzer);
        } else if (aktuellerBenutzer.getClass() == Student.class) {
            new StudentMainView((Student) aktuellerBenutzer);
        }
    }

    /**
     * Fügt der Userloesung den UserloesungErsteller hinzu und persistiert die Userlösungen in der Datenbank
     */
    public void persistTraining() {
        for (Userloesung userloesung : userloesungen) {
            userloesung.getUserloesungErsteller().addErstellteLoesung(userloesung);
            try {
                userloesung.getAufgabe().addUserloesung(userloesung);
            } catch (Exception ignored) {
            }
            userloesung.getAufgabensammlung().addUserloesung(userloesung);
        }

        aktuellerBenutzer.addBearbeitetesTraining(training);
        DatabaseService ds = DatabaseService.getInstance();
        ds.persistObjects(userloesungen);
    }

    /**
     * @return das aktuelle Training
     */
    public Training getTraining() {
        return training;
    }

    /**
     * erzeugt einen neuen Controller für die Lösung des Trainings
     */
    public void zeigeTrainingLoesungView() {
        new LoesungenTrainingController(training, aktuellerBenutzer, hauptmenueFrame);

    }
}