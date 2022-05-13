package app;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatDesignaufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatEinfachantwortAufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatProgrammieraufgabeView;
import entity.*;
import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.benutzer.Dozent;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.Musterloesung;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.musterloesung.MusterloesungEinfachantwort;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;
import persistence.DatabaseService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kristin Kubisch
 * @version 10.05.22
 * Schnittstelle
 */
public class TestatApp {

    private Testat testat;
    //private Testat TestatBearbeitung;
    private int index;
    private JFrame aktuellerFrame;
    private DatabaseService database;
    private TestatBearbeitung bearbeitet;
    //zwischenspeichern der AntwortenListe
    public List<Object> usereingaben = new ArrayList<>();

    public TestatApp(Testat testat, DatabaseService database) { //Konstruktor: bekomme das Testat mit
        this.index = 0;
        this.testat = testat;
        this.database = database; //Muss DatabaseService mit übergeben(dahin speichern)
        this.bearbeitet = new TestatBearbeitung(testat, 0, null, null);
    }

    public void zeigeAktuelleAufgabe() { //Aufgaben anzeigen
        Aufgabe aufgabe = testat.getAufgaben().get(this.index); //Aufgabe an Position bekommen

        if (this.aktuellerFrame != null) { //Alte (aktuelle) Ansicht der Aufgabe weg (Fenster schließen)
            // Userlösung.persist
            // TestatBearbeitung
            //
            // speichern in TestatBearbeitung
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

        // speichere aktuelleAufgabe in TestatBearbeitung

        if (this.index < testat.getAnzahlAufgaben() - 1) {
            this.index++;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Aufgabe Beenden");
            //UserLösungen speichern
            //Öffne Hauptansicht

            //BeendenButton
            //Beenden--> in Datenbank persistieren
        }
    }

    public void finish() {
        // nimm usereingaben Liste und
        /**
        DatabaseService ds = DatabaseService.getInstance();
        Testat neuesTestat = new Testat(usereingaben, testat.getPasswort(), testat.getName(), testat.getTestatErsteller());
        TestatBearbeitung testatB = new TestatBearbeitung (neuesTestat);
        ds.persistObject(neuesTestat);
        */
    }

    public void printTest() {
        System.out.println(usereingaben);

        // nimm usereingaben Liste und
    }


}
