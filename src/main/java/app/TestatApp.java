package app;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatDesignaufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatEinfachantwortAufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatProgrammieraufgabeView;
import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
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
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version2: 13.05.22
 * @version3: 16.05.22
 * @version4: 18.05.22
 * Schnittstelle um ein Testat auszuführen
 */
public class TestatApp {

    private Testat testat;
    private TestatBearbeitung bearbeitet;
    private int index;
    private JFrame aktuellerFrame;
    private DatabaseService database;
    private Benutzer benutzer;
    private Aufgabe aufgabe;

    //private
    public List<Userloesung> usereingaben = new ArrayList<>(); //Liste vom Typ Userlösungen mit antworten//bei Beeenden .persist

    public TestatApp(Testat testat, Benutzer benutzer) { //Konstruktor: bekomme das Testat mit
        this.index = 0;
        this.testat = testat;
        this.benutzer = benutzer; //Muss DatabaseService mit übergeben(dahin speichern)
        this.bearbeitet = new TestatBearbeitung(testat, 0, benutzer, null);
    }

    public void zeigeAktuelleAufgabe() { //Aufgaben anzeigen

        //Aufgabe aufgabeB = bearbeitet.getTestat().getAufgaben().get(this.index);
        aufgabe = testat.getAufgaben().get(this.index); //Aufgabe am Index erhalten

        if (this.aktuellerFrame != null) { //Alte (aktuelle) Ansicht der Aufgabe schließen (Fenster schließen)
            this.aktuellerFrame.dispose();
        }
        // Passende View zusammen mit Aufgabe öffnen
        if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Einfachantwort)) {
            this.aktuellerFrame = new BearbeiteTestatEinfachantwortAufgabeView(this, (EinfachantwortAufgabe) aufgabe);// Für funktionalität: TestatApp mit übergeben
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.MultipleChoice)) {
            this.aktuellerFrame = new BearbeiteTestatMultipleChoiceAufgabeView(this, (MultipleChoiceAufgabe) aufgabe);
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Programmieren)) {
            this.aktuellerFrame = new BearbeiteTestatProgrammieraufgabeView(this, (Programmieraufgabe) aufgabe);
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Design)) {
            this.aktuellerFrame = new BearbeiteTestatDesignaufgabeView(this, (Designaufgabe) aufgabe);
        }
        this.aktuellerFrame.setVisible(true);
    }

    public void weiter() {
        if (this.index < testat.getAnzahlAufgaben() - 1) {
            this.index++;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Keine weitern Aufgaben. Klicken Sie auf Beenden");
        }
    }

    public void zurueckTestat() {
        if (this.index > 0) {
            this.index--;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Keine vorherige Aufgabe");
        }
    }

    public void persistTestat() {//usereingaben Liste persistieren

        DatabaseService ds1 = database.getInstance();
        ds1.persistObjects(usereingaben);
        ds1.persistObject(bearbeitet);
/**
 *
 *     public void setUsereingaben(List<Userloesung> usereingaben) {
 *         this.usereingaben = usereingaben;
 *     }
 *
 *     public void setUsereingabe(String usereingabe) {
 *
 *         userloesung.setUserloesung
 *
 *                 add(usereingabe);
 *         //this.usereingaben = usereingaben;
 *     }
 *
 */
    }
    public Testat getTestat() {
        return testat;
    }


    public static void main(String[] args) throws Exception {

        Aufgabe a1 = new EinfachantwortAufgabe(10, "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null);
        Aufgabe a2 = new Designaufgabe(15, "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null);
        Aufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, Arrays.asList(new String[]{"char", "int", "double"}));

        MusterloesungEinfachantwort m1 = new MusterloesungEinfachantwort();
        a1.setMusterloesung(m1);
        m1.setLoesungshinweis("Lösungshinweis MusterloesungEinfachantwort");

        MusterloesungProgrammieraufgabe m2 = new MusterloesungProgrammieraufgabe();
        a3.setMusterloesung(m2);
        m2.setLoesungshinweis("Lösungshinweis MusterloesungProgrammieraufgabe");

        MusterloesungMultipleChoiceAufgabe m3 = new MusterloesungMultipleChoiceAufgabe();
        a4.setMusterloesung(m3);
        m3.setLoesungshinweis("Lösungshinweis MusterloesungMultipleChoiceAufgabe");

        MusterloesungDesignaufgabe m4 = new MusterloesungDesignaufgabe();
        a2.setMusterloesung(m4);
        m4.setLoesungshinweis("Lösungshinweis MusterloesungDesignaufgabe");


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


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    TestatApp testatApp = new TestatApp(t1, student1);
                    testatApp.zeigeAktuelleAufgabe();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

