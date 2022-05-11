package app;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatDesignaufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatEinfachantwortAufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatProgrammieraufgabeView;
import entity.*;
import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;
import entity.benutzer.Dozent;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.Musterloesung;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.musterloesung.MusterloesungEinfachantwort;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kristin Kubisch
 * @version 10.05.22
 */
public class TestatApp {

    private Testat testat;
    private int index;
    private JFrame aktuellerFrame;

    public static void main(String[] args) {
        Aufgabe a1 = new EinfachantwortAufgabe(10, " javaDesign", "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", new Dozent(), new MusterloesungEinfachantwort());
        Aufgabe a2 = new Designaufgabe(15, " javaDesign", "umlDesign", Kategorie.Datenbanken,  23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", new Dozent(), new MusterloesungDesignaufgabe());
        Aufgabe a3 = new Programmieraufgabe(5, "", "", Kategorie.Java_Programmierung,10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife",new Dozent(), new MusterloesungProgrammieraufgabe());
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "javaDesign", "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", new Dozent(), null,null);
        List<Aufgabe> aufgabenListe1 = Arrays.asList(a1, a2, a3, a4); //Liste mit Aufgaben
        Testat testat = new Testat(aufgabenListe1, null, "Hallo1234", new Dozent()); //neues Testat
        TestatApp app = new TestatApp(testat); //TestatApp Testat übergeben
        app.zeigeAktuelleAufgabe(); //Die App soll die erste Aufgabe Zeigen usw.
    }

    public TestatApp(Testat testat) { //Konstruktor: bekomme das Testat mit
        this.index = 0;
        this.testat = testat;
    }

    public void zeigeAktuelleAufgabe() { //Aufgaben anzeigen
        Aufgabe aufgabe = testat.getAufgaben().get(this.index); //Aufgabe an Position bekommen

        if (this.aktuellerFrame != null) { //Alte (aktuelle) Ansicht der Aufgabe weg (Fenster schließen)
            this.aktuellerFrame.dispose();
        }

        // Öffne passende Ansicht(Klasse) mit Aufgabe bzw Frame bauen
        if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Einfachantwort)) {
            this.aktuellerFrame = new BearbeiteTestatEinfachantwortAufgabeView(this, (EinfachantwortAufgabe) aufgabe); //Einfachantwort View öffnen
            // TestatApp mit übergeben für funktionalität // Aufgabe mit Daten übergeben
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.MultipleChoice)) {
            this.aktuellerFrame = new BearbeiteTestatMultipleChoiceAufgabeView(this, (MultipleChoiceAufgabe) aufgabe);
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Programmieren)) {
            this.aktuellerFrame = new BearbeiteTestatProgrammieraufgabeView(this, new Programmieraufgabe());
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Design)) {
            this.aktuellerFrame = new BearbeiteTestatDesignaufgabeView(this, (Designaufgabe)  aufgabe);
        }

        this.aktuellerFrame.setVisible(true);//Frame anzeigen

    }

    public void weiter() {//Wie ist der aktuelle Index, kann er noch eine Aufgabe finden? Ja: Index einstellen + AktuelleAufgabe anzeigen
        // Aufgabe weiter (Index) --> (Ansicht öffnen)
        if (this.index < testat.getAnzahlAufgaben() - 1) {
            this.index++;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Aufgabe Beenden");
            //UserLösungen speichern
            //Öffne Hauptansicht

        }

    }

}
