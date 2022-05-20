package app;

import View.AufgabenBearbeiten.Training.BearbeiteTrainingDesignaufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingEinfachantwortAufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingProgrammieraufgabeView;
import View.Lösungen.LoesungenTraining.ControllerLoesungenTraining;
import entity.aufgabe.*;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.benutzer.Student;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.userloesung.Userloesung;
import persistence.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * * @version2: 13.05.22
 * * @version3: 16.05.22
 * * @version4: 18.05.22
 * Schnittstelle um ein Training auszuführen
 */

public class TrainingController {

    private Training training;
    private JFrame hauptmenueFrame;
    private int index;
    private JFrame aktuellerFrame;
    private Benutzer aktuellerBenutzer;
    public List<Userloesung> userloesungen = new ArrayList<>();


    public TrainingController(Training training, Benutzer aktuellerBenutzer, JFrame hauptmenueFrame) {
        this.index = 0;
        this.hauptmenueFrame = hauptmenueFrame;
        this.training = training;
        this.aktuellerBenutzer = aktuellerBenutzer;
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

    public void zurueckTraining() {
        if (this.index > 0) {
            this.index--;
            zeigeAktuelleAufgabe();
        }
    }

    public void weiter() {
        if (this.index < training.getAnzahlAufgaben() - 1) {
            this.index++;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Keine weiteren Aufgaben. Klicken Sie auf Beenden");
        }
    }

    public void persistTraining() {//usereingaben Liste persistieren
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
        ds.persistObject(training);
        new ControllerLoesungenTraining(training, aktuellerBenutzer, hauptmenueFrame);
    }

    public boolean isIndexNotFirst() {
        return (index > 0);
    }

    public boolean isIndexNotLast() {
        return (index < training.getAnzahlAufgaben() - 1);
    }

    public Training getTraining() {
        return training;
    }

    public Benutzer getAktuellerBenutzer() {
        return aktuellerBenutzer;
    }

    public void addUserloesung(Userloesung userloesung) {
        userloesungen.add(userloesung);
    }

    public static void main(String[] args) {

        Aufgabe a1 = new EinfachantwortAufgabe(10, null, Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null);
        Aufgabe a2 = new Designaufgabe(15, null, Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null);
        Aufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, null, Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, Arrays.asList(new String[]{"char", "int", "double"}));

        /**
         MusterloesungEinfachantwort m1 = new MusterloesungEinfachantwort();
         m1.setLoesungshinweis("Lösungshinweis MusterloesungEinfachantwort");
         a1.setMusterloesung(m1);
         */

        java.util.List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a1});
        java.util.List<Aufgabe> aufgabenListe2 = Arrays.asList(new Aufgabe[]{a1, a3, a4});
        java.util.List<Aufgabe> aufgabenListe3 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a4, a1, a2, a3});
        Student student1 = new Student("AApfel", "aaa", "Adam", "Apfel", 1111);
        Training training1 = new Training(aufgabenListe2, 10, Kategorie.Software_Engineering, Schwierigkeitsgrad.Leicht, student1);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    TrainingController trainingController = new TrainingController(training1, student1, null);
                    trainingController.zeigeAktuelleAufgabe();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void setUserFrameVisible() {
        hauptmenueFrame.setVisible(true);
    }

}