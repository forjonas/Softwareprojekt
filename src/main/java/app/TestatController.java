package app;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatDesignaufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatEinfachantwortAufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Testat.BearbeiteTestatProgrammieraufgabeView;
import View.MeineTestateKatalogView;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version2: 13.05.22
 * @version3: 16.05.22
 * @version4: 18.05.22
 * @version5: 19.05.22 Vorher Button mit Inhalt
 * @version6: 20.05.22 "Beenden" Button auf setVisible(false)
 * Schnittstelle um ein Testat auszuführen
 */
public class TestatController {

    private Testat testat;
    private TestatBearbeitung testatBearbeitung;
    private int index;
    private JFrame aktuellerFrame;
    private Benutzer aktuellerBenutzer;
    private Aufgabe aufgabe;
    private JFrame hauptmenueFrame;
    private List<Userloesung> userloesungen;

    public TestatController(Testat testat, Benutzer aktuellerBenutzer, JFrame hauptmenueFrame) { //Konstruktor: bekomme das Testat mit
        this.hauptmenueFrame = hauptmenueFrame;
        this.index = 0;
        this.testat = testat;
        this.userloesungen = new ArrayList<>();
        this.aktuellerBenutzer = aktuellerBenutzer;
        this.testatBearbeitung = new TestatBearbeitung(testat, 0, aktuellerBenutzer, null);
        this.testat.addBearbeitung(testatBearbeitung);
        zeigeAktuelleAufgabe();
    }

    public void setNewTestatKatalog() {
        new MeineTestateKatalogView(hauptmenueFrame, aktuellerBenutzer);
    }

    public void zeigeAktuelleAufgabe() { //Aufgaben anzeigen

        //Aufgabe aufgabeB = bearbeitet.getTestat().getAufgaben().get(this.index);
        aufgabe = testat.getAufgaben().get(this.index); //Aufgabe am Index erhalten

        if (this.aktuellerFrame != null) { //Alte (aktuelle) Ansicht der Aufgabe schließen (Fenster schließen)
            this.aktuellerFrame.dispose();
        }
        // Passende View zusammen mit Aufgabe öffnen
        if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Einfachantwort)) {
            BearbeiteTestatEinfachantwortAufgabeView frame = new BearbeiteTestatEinfachantwortAufgabeView(this, (EinfachantwortAufgabe) aufgabe);
            if (userloesungen.size() >= index + 1) {
                frame.setUserloesung(userloesungen.get(index));
            }

            this.aktuellerFrame = frame;// Für funktionalität: TestatApp mit übergeben
            if (index+1 < testat.getAnzahlAufgaben()) {
                frame.hideButton();
            }


        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.MultipleChoice)) {

            BearbeiteTestatMultipleChoiceAufgabeView frame = new BearbeiteTestatMultipleChoiceAufgabeView(this, (MultipleChoiceAufgabe) aufgabe);
            if (userloesungen.size() >= index + 1) {
                frame.setUserloesung(userloesungen.get(index));
            }




            this.aktuellerFrame = frame;
            if (index+1 < testat.getAnzahlAufgaben()) {
                frame.hideButton();
            }

        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Programmieren)) {
            BearbeiteTestatProgrammieraufgabeView frame = new BearbeiteTestatProgrammieraufgabeView(this, (Programmieraufgabe) aufgabe);
            if (userloesungen.size() >= index + 1) {
                frame.setUserloesung(userloesungen.get(index));
            }



            this.aktuellerFrame = frame;
            if (index+1 < testat.getAnzahlAufgaben()) {
                frame.hideButton();
            }

        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Design)) {
            BearbeiteTestatDesignaufgabeView frame = new BearbeiteTestatDesignaufgabeView(this, (Designaufgabe) aufgabe);
            if (userloesungen.size() >= index + 1) {
                frame.setUserloesung(userloesungen.get(index));
            }



            this.aktuellerFrame = frame;
            if (index+1 < testat.getAnzahlAufgaben()) {
                frame.hideButton();
            }
        }
        this.aktuellerFrame.setVisible(true);
    }

    public void weiter() {
        if (this.index < testat.getAnzahlAufgaben() - 1) {
            this.index++;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Keine weiteren Aufgaben. Klicken Sie auf Beenden");
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

    public void addUserloesung(Userloesung userloesung) {
        if (userloesungen.size() < this.index + 1) {
            userloesungen.add(userloesung);
        } else {
            userloesungen.set(this.index, userloesung);
        }

    }

    public void persistTestat() {//usereingaben Liste persistieren
        for (Userloesung userloesung : userloesungen) {
            userloesung.getUserloesungErsteller().addErstellteLoesung(userloesung);
            try {
                userloesung.getAufgabe().addUserloesung(userloesung);
            } catch (Exception ignored) {
            }
            userloesung.getAufgabensammlung().addUserloesung(userloesung);
        }
        DatabaseService ds = DatabaseService.getInstance();
        ds.persistObjects(userloesungen);
        ds.persistObject(testatBearbeitung);
        new MeineTestateKatalogView(hauptmenueFrame, aktuellerBenutzer);
    }

    public boolean isIndexNotFirst() {
        return (index > 0);
    }

    public boolean isIndexNotLast() {
        return (index < testat.getAnzahlAufgaben() - 1);
    }

    public Testat getTestat() {
        return testat;
    }

    public Benutzer getAktuellerBenutzer() {
        return aktuellerBenutzer;
    }

    public static void main(String[] args) throws Exception {

        Aufgabe a1 = new EinfachantwortAufgabe(10, null, Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null);
        Aufgabe a2 = new Designaufgabe(15, null, Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null);
        Aufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, null, Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, Arrays.asList(new String[]{"char", "int", "double"}));

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

        TestatController testatApp = new TestatController(t1, dozent2, null);
        testatApp.zeigeAktuelleAufgabe();

    }

}