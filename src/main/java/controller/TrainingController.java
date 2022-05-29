package controller;

import view.*;
import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.benutzer.Student;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.musterloesung.MusterloesungEinfachantwort;
import entity.loesung.musterloesung.MusterloesungMultipleChoiceAufgabe;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;
import entity.loesung.userloesung.Userloesung;
import persistence.DatabaseService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kristin Kubisch
 * @version: 27.05.22
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


    public void setNewTrainingKatalog() {

        if (aktuellerBenutzer.getClass() == Dozent.class) {
            new DozentAnsicht((Dozent) aktuellerBenutzer);
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
        System.out.println(userloesungen);

        new controllerLoesungenTraining(training, aktuellerBenutzer, hauptmenueFrame);
    }

    /**
     * @return das aktuelle Training
     */
    public Training getTraining() {
        return training;
    }


    public void setUserFrameVisible() {
        hauptmenueFrame.setVisible(true);
    }

}