package app;

import View.AufgabenBearbeiten.Training.BearbeiteTrainingDesignaufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingEinfachantwortAufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingProgrammieraufgabeView;
import entity.aufgabe.*;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.musterloesung.MusterloesungEinfachantwort;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;
import persistence.DatabaseService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version2: 13.05.22
 */

public class TrainingApp {

    private Training training;
    private int index;
    private JFrame aktuellerFrame;
    private DatabaseService database;
    private Benutzer benutzer;
    //zwischenspeichern der AntwortenListe
    public List<Object> usereingaben = new ArrayList<>();


    public TrainingApp(Training training, Benutzer benutzer) {
        this.index = 0;
        this.training = training;
        this.benutzer = benutzer; //Muss DatabaseService mit übergeben(dahin speichern)

    }

    public void zeigeAktuelleAufgabe() {
        Aufgabe aufgabe = training.getAufgaben().get(this.index);//bekomme Aufgabe an der Position

        if (this.aktuellerFrame != null) {//Alte Ansicht Aufgabe weg (Fenster schließen)
            this.aktuellerFrame.dispose();
        }

        // Öffne Ansicht mit Aufgabe bzw Frame bauen
        if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Einfachantwort)) {
            this.aktuellerFrame = new BearbeiteTrainingEinfachantwortAufgabeView(this, (EinfachantwortAufgabe) aufgabe);

        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.MultipleChoice)) {
            this.aktuellerFrame = new BearbeiteTrainingMultipleChoiceAufgabeView(this, (MultipleChoiceAufgabe) aufgabe);
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Programmieren)) {
            this.aktuellerFrame = new BearbeiteTrainingProgrammieraufgabeView(this, (Programmieraufgabe) aufgabe);
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Design)) {
            this.aktuellerFrame = new BearbeiteTrainingDesignaufgabeView(this, (Designaufgabe) aufgabe);
        }

        this.aktuellerFrame.setVisible(true);       //Frame anzeigen

    }

    public void weiter() { //Wie ist der aktuelle Index, kann er noch eine Aufgabe finden? Ja: Index einstellen + AktuelleAufgabe anzeigen
        if (this.index < training.getAnzahlAufgaben() - 1) {
            this.index++;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Aufgabe Beenden");
            //UserLösungen speichern
            //Beenden--> in Datenbank persistieren
        }
    }

    public void finish() {
        // nimm usereingaben Liste und
    }

    public void printTest() {
        System.out.println(usereingaben);

        // nimm usereingaben Liste und
    }

}