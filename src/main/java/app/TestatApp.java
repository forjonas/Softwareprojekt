package app;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatDesignaufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatEinfachantwortAufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatProgrammieraufgabeView;
import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.benutzer.Benutzer;
import entity.enums.Aufgabentyp;
import entity.loesung.userloesung.Userloesung;
import persistence.DatabaseService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version2: 13.05.22
 * @version3: 16.05.22
 */
public class TestatApp {

    private Testat testat;
    private TestatBearbeitung bearbeitet;
    private int index;
    private JFrame aktuellerFrame;
    private DatabaseService database;
    private Benutzer benutzer;
    public List<Userloesung> usereingaben = new ArrayList<>(); //Liste vom Typ Userlösungen mit antworten//bei Beeenden .persist

    public TestatApp(Testat testat, Benutzer benutzer) { //Konstruktor: bekomme das Testat mit
        this.index = 0;
        this.testat = testat;
        this.benutzer = benutzer; //Muss DatabaseService mit übergeben(dahin speichern)
        this.bearbeitet = new TestatBearbeitung(testat);
    }

    public void zeigeAktuelleAufgabe() { //Aufgaben anzeigen

        Aufgabe aufgabeB = bearbeitet.getTestat().getAufgaben().get(this.index);
        Aufgabe aufgabe = testat.getAufgaben().get(this.index); //Aufgabe am Index erhalten

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
            JOptionPane.showMessageDialog(null, "Aufgabe Beenden");
        }
    }

    public void zurueckTestat() {
        if (this.index > 0) {
            this.index--;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Aufgabe Beenden");
        }
    }


    public void printPersistenz() {//usereingaben Liste persistieren

        DatabaseService ds1 = database.getInstance();
        ds1.persistObjects(usereingaben);
        System.out.println(usereingaben);
    }

    public void finish() {
    }

}
