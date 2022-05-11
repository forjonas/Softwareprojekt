package app;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatDesignaufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatEinfachantwortAufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatProgrammieraufgabeView;
import entity.*;

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
        Aufgabe a1 = new EinfachantwortAufgabe(10, " javaDesign", "umlDesign", Kategorie.Software_Engineering, "Test Test", 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", "Pi mal Daumen", "Peace");
        Aufgabe a2 = new Designaufgabe(15, " javaDesign", "umlDesign", Kategorie.Datenbanken, "Kein Lösungshinweis", 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", "Richtig", "RRRRichtig");
        Aufgabe a3 = new Programmieraufgabe(5, null, null, Kategorie.Java_Programmierung, "for-Schleife", 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", "for(int i=0; i<5; i++) {\n\tSystem.out.println(\"Hello World!\");\n}", "Keine Ahnung");
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "javaDesign", "umlDesign", Kategorie.Java_Programmierung, "Char ist es nicht.", 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", Arrays.asList("char", "int", "double"), Arrays.asList(false, true, false), Arrays.asList(false, true, false));
        List<Aufgabe> aufgabenListe1 = Arrays.asList(a1, a2, a3, a4); //Liste mit Aufgaben
        Testat testat = new Testat(aufgabenListe1, null, "Hallo1234", "Sommertestat"); //neues Testat
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
            this.aktuellerFrame = new BearbeiteTestatProgrammieraufgabeView(this, (Programmieraufgabe) aufgabe);
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Design)) {
            this.aktuellerFrame = new BearbeiteTestatDesignaufgabeView(this, (Designaufgabe) aufgabe);
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
