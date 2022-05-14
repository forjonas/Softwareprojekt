package TestateK;

import app.TestatApp;
import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;
import entity.benutzer.Dozent;
import entity.benutzer.Student;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.musterloesung.MusterloesungEinfachantwort;
import entity.loesung.musterloesung.MusterloesungMultipleChoiceAufgabe;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;
import persistence.DatabaseService;

import java.awt.*;
import java.util.Arrays;

public class MainTestenTestate {

    public static void main(String[] args) throws Exception {

        Aufgabe a1 = new EinfachantwortAufgabe(10, "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null);
        Aufgabe a2 = new Designaufgabe(15, "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null);
        Aufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, Arrays.asList(new String[]{"char", "int", "double"}));

        MusterloesungEinfachantwort m1 = new MusterloesungEinfachantwort();
        m1.setLoesungshinweis("Lösungshinweis MusterloesungEinfachantwort");
        a1.setMusterloesung(m1);
/**
        MusterloesungProgrammieraufgabe m2 = new MusterloesungProgrammieraufgabe();
        m2.setLoesungshinweis("Lösungshinweis MusterloesungProgrammieraufgabe");
        a2.setMusterloesung(m2);

        MusterloesungMultipleChoiceAufgabe m3 = new MusterloesungMultipleChoiceAufgabe();
        m3.setLoesungshinweis("Lösungshinweis MusterloesungMultipleChoiceAufgabe");
        a3.setMusterloesung(m3);

        MusterloesungDesignaufgabe m4 = new MusterloesungDesignaufgabe();
        m4.setLoesungshinweis("Lösungshinweis MusterloesungDesignaufgabe");
        a4.setMusterloesung(m4);
*/
        java.util.List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a1});
        java.util.List<Aufgabe> aufgabenListe2 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a2, a2, a3});
        java.util.List<Aufgabe> aufgabenListe3 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a4, a1, a2, a3});
        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Dozent dozent2 = new Dozent("PPanzer", "jklö", "Paul", "Panzer");
        Testat t1 = new Testat(aufgabenListe1, "Hallo1234", "Sommertestat", dozent1);
        Testat t2 = new Testat(aufgabenListe2, "asdf", "Wintertestat", dozent2);
        Testat t3 = new Testat(aufgabenListe3, "qwertz", "Herbsttestat", dozent1);
        java.util.List<Testat> testatliste = Arrays.asList(new Testat[]{t1, t2, t3, t1, t2, t3, t1, t2, t3, t1, t2, t3});
        Student student1 = new Student("AApfel", "aaa", "Adam", "Apfel", 1111);


        //Ich bekomme TestatApp testat = new TestatApp(testat1, database);
        //testat1.zeigeAufgabe;

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    TestatApp testatApp = new TestatApp(t1,student1);
                    testatApp.zeigeAktuelleAufgabe();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}