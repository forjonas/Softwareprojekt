package app;

import View.AufgabenBearbeiten.Training.*;
import View.EinsehenTrainingKatalogView;
import View.Lösungen.LoesungenTraining.ControllerLoesungenTraining;
import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.benutzer.Student;
import entity.enums.Aufgabentyp;
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
 * @version: 10.05.22
 * @version2: 13.05.22
 * @version3: 16.05.22
 * @version4: 18.05.22
 * @version5: 23.05.22 Anpassungen u.v.m
 * Schnittstelle um ein Training auszuführen
 */

public class TrainingController {

    private Training training;
    private JFrame hauptmenueFrame;
    private int index;
    private JFrame aktuellerFrame;
    private Benutzer aktuellerBenutzer;
    private Aufgabe aufgabe;
    private List<Userloesung> userloesungen;

    /**
     * Konstruktor für Klasse TrainingController
     *
     * @param training
     * @param aktuellerBenutzer
     * @param hauptmenueFrame
     */
    public TrainingController(Training training, Benutzer aktuellerBenutzer, JFrame hauptmenueFrame) {
        this.hauptmenueFrame = hauptmenueFrame;
        this.index = 0;
        this.training = training;
        this.aktuellerBenutzer = aktuellerBenutzer;
        this.userloesungen = new ArrayList<>();
        for (int i = 0; i < training.getAnzahlAufgaben(); i++) {
            userloesungen.add(i, null);
            zeigeAktuelleAufgabe();
        }
    }

    public void setNewTrainingKatalog() {
        new EinsehenTrainingKatalogView(hauptmenueFrame, (Dozent) aktuellerBenutzer);
    }

    /**
     * Zeigt die aktuelle Aufgabe die zu bearbeiten ist an
     */
    public void zeigeAktuelleAufgabe() {
        aufgabe = training.getAufgaben().get(this.index);

        if (this.aktuellerFrame != null) {
            this.aktuellerFrame.dispose();
        }
        if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Einfachantwort)) {
            BearbeiteTrainingEinfachantwortAufgabeView frame = new BearbeiteTrainingEinfachantwortAufgabeView(this, (EinfachantwortAufgabe) aufgabe);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;
            if (index + 1 >= training.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.MultipleChoice)) {
            BearbeiteTrainingMultipleChoiceAufgabeView frame = new BearbeiteTrainingMultipleChoiceAufgabeView(this, (MultipleChoiceAufgabe) aufgabe);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;
            if (index + 1 >= training.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Programmieren)) {
            BearbeiteTrainingProgrammieraufgabeView frame = new BearbeiteTrainingProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;
            if (index + 1 >= training.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Design)) {
            BearbeiteTrainingDesignaufgabeView frame = new BearbeiteTrainingDesignaufgabeView(this, (Designaufgabe) aufgabe);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;
            if (index + 1 >= training.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        }
        this.aktuellerFrame.setVisible(true);
    }

    /**
     * Erhöht den Index und zeigt die nächste Aufgabe die zu bearbeiten ist an
     */
    public void weiter() {
        if (this.index < training.getAnzahlAufgaben() - 1) {
            this.index++;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Keine weiteren Aufgaben. Klicken Sie auf Beenden");
        }
    }

    /**
     * Verringert den Index und zeigt die vorherige Aufgabe die bearbeitet wurde an
     */
    public void zurueckTraining() {
        if (this.index > 0) {
            this.index--;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Keine vorherige Aufgabe");
        }
    }

    /**
     * Fügt die Userloesung der Userloesungenliste an dem passenden hinzu
     *
     * @param userloesung
     */
    public void addUserloesung(Userloesung userloesung) {
        userloesungen.set(this.index, userloesung);
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

        new ControllerLoesungenTraining(training, aktuellerBenutzer, hauptmenueFrame);
    }

    /**
     * @return das aktuelle Training
     */
    public Training getTraining() {
        return training;
    }

    /**
     * @return den aktuelle Benutzer
     */
    public Benutzer getAktuellerBenutzer() {
        return aktuellerBenutzer;
    }

}