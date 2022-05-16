package TestateK;

//import app.TrainingApp;
import app.TrainingApp;
import entity.aufgabe.*;
        import entity.aufgabensammlung.Training;
        import entity.benutzer.Student;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.MusterloesungMultipleChoiceAufgabe;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;
import persistence.DatabaseService;

import java.awt.*;
import java.util.Arrays;

public class MainTestenTraining {

    public static void main(String[] args) {

        Aufgabe a1 = new EinfachantwortAufgabe(10, " javaDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null);
        Aufgabe a2 = new Designaufgabe(15, " javaDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null);
        Aufgabe a3 = new Programmieraufgabe(5, "String", Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null);
        //Aufgabe a4 = new MultipleChoiceAufgabe(2, "javaDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null);
        java.util.List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a1});
        java.util.List<Aufgabe> aufgabenListe2 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a2, a2, a3});
        java.util.List<Aufgabe> aufgabenListe3 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a1, a2, a3});

        /*
        Training training1 = new Training (aufgabenListe1, 10 ,Kategorie.Software_Engineering,Schwierigkeitsgrad.Leicht, Aufgabentyp.MultipleChoice);
        Training training2 = new Training (aufgabenListe2, 10 ,Kategorie.Software_Engineering,Schwierigkeitsgrad.Leicht, Aufgabentyp.MultipleChoice);
        Training training3 = new Training (aufgabenListe3, 10 ,Kategorie.Software_Engineering,Schwierigkeitsgrad.Leicht, Aufgabentyp.MultipleChoice);
        */


        //java.util.List<Training> trainingliste = Arrays.asList(new Training[]{training1, training2, training3,});
        Student student1 = new Student("AApfel", "aaa", "Adam", "Apfel", 1111);

        DatabaseService ds = DatabaseService.getInstance();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    /*
                    TrainingApp trainingApp = new TrainingApp(training1, ds);
                    trainingApp.zeigeAktuelleAufgabe();

                     */

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}