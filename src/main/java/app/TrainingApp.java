package app;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingDesignaufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingEinfachantwortAufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Training.BearbeiteTrainingProgrammieraufgabeView;
import entity.*;
import entity.aufgabe.*;
import entity.aufgabensammlung.Training;
import entity.benutzer.Dozent;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.musterloesung.MusterloesungEinfachantwort;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author Kristin Kubisch
 * @version 10.05.22
 */
public class TrainingApp {

    private Training training;
    private int index;
    private JFrame aktuellerFrame;

    public static void main(String[] args) {
        Aufgabe a1 = new EinfachantwortAufgabe(10, " javaDesign", "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", new Dozent(), new MusterloesungEinfachantwort());
        Aufgabe a2 = new Designaufgabe(15, " javaDesign", "umlDesign", Kategorie.Datenbanken,  23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", new Dozent(), new MusterloesungDesignaufgabe());
        Aufgabe a3 = new Programmieraufgabe(5, "", "", Kategorie.Java_Programmierung,10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife",new Dozent(), new MusterloesungProgrammieraufgabe());
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "javaDesign", "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", new Dozent(), null,null);


        //List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});
        List<Aufgabe> aufgabenListe1 = new ArrayList<Aufgabe>();
        aufgabenListe1.add(a1);
        aufgabenListe1.add(a2);
        aufgabenListe1.add(a3);
        aufgabenListe1.add(a4);


        Training training = new Training(aufgabenListe1, 10, null, null, Aufgabentyp.MultipleChoice);
        TrainingApp app = new TrainingApp(training);
        app.zeigeAktuelleAufgabe();
    }

    public TrainingApp(Training testat) {
        this.index = 0;
        this.training = training;
    }

    public void zeigeAktuelleAufgabe() {
        Aufgabe aufgabe = training.getAufgaben().get(this.index); //bekomme Aufgabe an der Position

        if (this.aktuellerFrame != null) {          //Alte Ansicht Aufgabe weg (Fenster schließen)
            this.aktuellerFrame.dispose();
        }

        // Öffne Ansicht mit Aufgabe bzw Frame bauen
        if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Einfachantwort)) {
            this.aktuellerFrame = new BearbeiteTrainingEinfachantwortAufgabeView(this, new EinfachantwortAufgabe());

        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.MultipleChoice)) {
            this.aktuellerFrame = new BearbeiteTrainingMultipleChoiceAufgabeView(this, new MultipleChoiceAufgabe());
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Programmieren)) {
            this.aktuellerFrame = new BearbeiteTrainingProgrammieraufgabeView(this, new Programmieraufgabe());
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Design)) {
            this.aktuellerFrame = new BearbeiteTrainingDesignaufgabeView(this, new Designaufgabe());
        }

        this.aktuellerFrame.setVisible(true);       //Frame anzeigen

    }

    public void weiter() { //Wie ist der aktuelle Index, kann er noch eine Aufgabe finden? Ja: Index einstellen + AktuelleAufgabe anzeigen
        if (this.index < training.getAnzahlAufgaben() - 1) {
            this.index++;
            zeigeAktuelleAufgabe();
        } else {
            //Öffne Hauptansicht
        }

    }

}
