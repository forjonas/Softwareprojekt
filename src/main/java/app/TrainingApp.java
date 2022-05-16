package app;

import View.AufgabenBearbeiten.Training.BearbeiteTrainingDesignaufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingEinfachantwortAufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingProgrammieraufgabeView;
import entity.aufgabe.*;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.enums.Aufgabentyp;
import entity.loesung.userloesung.Userloesung;
import persistence.DatabaseService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version2: 13.05.22
 * @version3: 16.05.22
 *
 * Schnittstelle um ein Training auszuführen
 */

public class TrainingApp {

    private Training training;
    private int index;
    private JFrame aktuellerFrame;
    private DatabaseService database;
    private Benutzer benutzer;
    public List<Userloesung> usereingaben = new ArrayList<>();


    public TrainingApp(Training training, Benutzer benutzer) {
        this.index = 0;
        this.training = training;
        this.benutzer = benutzer;
    }

    public void zeigeAktuelleAufgabe() {//Aufgaben anzeigen

        Aufgabe aufgabe = training.getAufgaben().get(this.index);

        if (this.aktuellerFrame != null) {
            this.aktuellerFrame.dispose();
        }

        if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Einfachantwort)) {
            this.aktuellerFrame = new BearbeiteTrainingEinfachantwortAufgabeView(this, (EinfachantwortAufgabe) aufgabe);

        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.MultipleChoice)) {
            this.aktuellerFrame = new BearbeiteTrainingMultipleChoiceAufgabeView(this, (MultipleChoiceAufgabe) aufgabe);
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Programmieren)) {
            this.aktuellerFrame = new BearbeiteTrainingProgrammieraufgabeView(this, (Programmieraufgabe) aufgabe);
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Design)) {
            this.aktuellerFrame = new BearbeiteTrainingDesignaufgabeView(this, (Designaufgabe) aufgabe);
        }

        this.aktuellerFrame.setVisible(true);

    }

    public void zurueckTrainig() {
        if (this.index > 0) {
            this.index--;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Aufgabe Beenden");
        }
    }

    public void weiter() {
        if (this.index < training.getAnzahlAufgaben() - 1) {
            this.index++;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Aufgabe Beenden");
        }
    }
    public void printPersistenz() {//usereingaben Liste persistieren

        DatabaseService ds1 = database.getInstance();
        ds1.persistObjects(usereingaben);
        System.out.println(usereingaben);
    }

    public void finish() {
    }

}