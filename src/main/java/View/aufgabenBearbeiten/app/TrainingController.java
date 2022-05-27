package View.aufgabenBearbeiten.app;

import View.aufgabenBearbeiten.BearbeitungsController;
import View.EinsehenTrainingKatalogView;
import View.Lösungen.LoesungenTraining.ControllerLoesungenTraining;
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
 * @version: 10.05.22
 * @version2: 13.05.22
 * @version3: 16.05.22
 * @version4: 18.05.22
 * @version5: 23.05.22 Anpassungen u.v.m
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
        new EinsehenTrainingKatalogView(this.hauptmenueFrame, (Dozent) aktuellerBenutzer);
    }

    /**
     * Fügt der Userloesung den UserloesungErsteller hinzu und persistiert die Userlösungen in der Datenbank
     */
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
        //ds.persistObject(training); //nötig????
        System.out.println(userloesungen);

        new ControllerLoesungenTraining(training, aktuellerBenutzer, hauptmenueFrame);
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

    public static void main(String[] args) throws Exception {
        DatabaseService ds = DatabaseService.getInstance();
        List<Aufgabe> aufgabenliste;
        aufgabenliste = ds.readAufgabenFromDatabase();

        List<String> antwortmoeglichkeiten = new ArrayList<>();
        antwortmoeglichkeiten.add("Test1");
        antwortmoeglichkeiten.add("Test2");
        antwortmoeglichkeiten.add("Test3");
        //  antwortmoeglichkeiten.add("Test4");

        Aufgabe a1 = new EinfachantwortAufgabe(10, null, Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null);
        Aufgabe a2 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null);
        Aufgabe a3 = new Designaufgabe(15, null, Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, null, Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, antwortmoeglichkeiten);

        MusterloesungEinfachantwort m1 = new MusterloesungEinfachantwort();
        a1.setMusterloesung(m1);
        m1.setLoesungshinweis("Lösungshinweis MusterloesungEinfachantwort");

        MusterloesungProgrammieraufgabe m2 = new MusterloesungProgrammieraufgabe();
        a2.setMusterloesung(m2);
        m2.setLoesungshinweis("Lösungshinweis MusterloesungProgrammieraufgabe");

        MusterloesungMultipleChoiceAufgabe m3 = new MusterloesungMultipleChoiceAufgabe();
        a4.setMusterloesung(m3);
        m3.setLoesungshinweis("Lösungshinweis MusterloesungMultipleChoiceAufgabe");

        MusterloesungDesignaufgabe m4 = new MusterloesungDesignaufgabe();
        a3.setMusterloesung(m4);
        m4.setLoesungshinweis("Lösungshinweis MusterloesungDesignaufgabe");

        java.util.List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});
        java.util.List<Aufgabe> aufgabenListe2 = Arrays.asList(new Aufgabe[]{a1, a1, a2, a2,});
        java.util.List<Aufgabe> aufgabenListe3 = Arrays.asList(new Aufgabe[]{a3, a3, a3, a4, a4, a4});
        java.util.List<Aufgabe> aufgabenListe4 = Arrays.asList(new Aufgabe[]{a4, a3, a3, a4});
        java.util.List<Aufgabe> aufgabenListe5 = Arrays.asList(new Aufgabe[]{a1, a1, a2, a2, a3, a3, a4, a4});

        java.util.List<Aufgabe> aufgabenListe6 = Arrays.asList(new Aufgabe[]{a3, a3, a3, a4,});
        java.util.List<Aufgabe> aufgabenListe7 = Arrays.asList(new Aufgabe[]{a1, a3, a2, a4});


        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Dozent dozent2 = new Dozent("PPanzer", "jklö", "Paul", "Panzer");

        Training training1 = new Training(aufgabenliste, 10, Kategorie.Java_Programmierung, Schwierigkeitsgrad.Schwer, dozent1);
        Testat t2 = new Testat(aufgabenListe2, "asdf", "Wintertestat", dozent2);
        Testat t3 = new Testat(aufgabenListe3, "qwertz", "Herbsttestat", dozent1);
        // java.util.List<Testat> testatliste = Arrays.asList(new Testat[]{t1, t2, t3, t1, t2, t3, t1, t2, t3, t1, t2, t3});
        Student student1 = new Student("AApfel", "aaa", "Adam", "Apfel", 1111);

        TrainingController trainingController = new TrainingController(training1, dozent2, null);
        // trainingController.zeigeAktuelleAufgabe();

    }

}