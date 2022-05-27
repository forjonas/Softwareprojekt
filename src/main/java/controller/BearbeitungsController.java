package controller;

import view.aufgabenBearbeiten.aufgabenSammlungenBearbeiten.*;
import controller.TrainingController;
import entity.aufgabe.*;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.musterloesung.MusterloesungEinfachantwort;
import entity.loesung.musterloesung.MusterloesungMultipleChoiceAufgabe;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;
import entity.loesung.userloesung.Userloesung;
import entity.loesung.userloesung.UserloesungMultipleChoiceAufgabe;
import persistence.DatabaseService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kristin Kubisch
 * @version: 27.05.22
 * Schnittstelle um eine Aufgabensammlung auszuführen
 */
public abstract class BearbeitungsController {

    protected int index = 0;
    protected JFrame aktuellerFrame;
    protected JFrame hauptmenueFrame;
    protected Aufgabensammlung sammlung;
    protected Benutzer aktuellerBenutzer;
    protected Aufgabe aufgabe;
    protected List<Userloesung> userloesungen;

    /**
     * @param sammlung die Sammlungt einer Aufgabensammlung
     * @param aktuellerBenutzer der aktuelle Benutzer der die Aufgabensammlung bearbeitet
     * @param hauptmenueFrame das dazugehörige Fenster des Benutzers
     */
    public BearbeitungsController(Aufgabensammlung sammlung, Benutzer aktuellerBenutzer, JFrame hauptmenueFrame) {
        this.sammlung = sammlung;
        this.aktuellerBenutzer = aktuellerBenutzer;
        this.hauptmenueFrame = hauptmenueFrame;
        this.userloesungen = new ArrayList<>();
        for (int i = 0; i < sammlung.getAnzahlAufgaben(); i++) {
            userloesungen.add(i, null);
        }
    }

    /**
     * zeigt die aktuelle Aufgabe der Aufgabensammlung
     */
    public void zeigeAktuelleAufgabe() { //Aufgabe anzeigen
        aufgabe = sammlung.getAufgaben().get(this.index); //Aufgabe am Index erhalten

        if (this.aktuellerFrame != null) {
            this.aktuellerFrame.dispose();
        }
        if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Einfachantwort)) {
            BearbeiteEinfachantwortAufgabeView frame = new BearbeiteEinfachantwortAufgabeView(this, (EinfachantwortAufgabe) aufgabe);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;// Für funktionalität: TestatApp mit übergeben
            if (index + 1 >= sammlung.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.MultipleChoice)) {
            BearbeiteMuktipleChoiceAufgabeView frame = new BearbeiteMuktipleChoiceAufgabeView(this, (MultipleChoiceAufgabe) aufgabe);
            // frame.setUserloesung(userloesungen.get(index));
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else {
                frame.setUserloesung(userloesungen.get(index));
                System.out.println((UserloesungMultipleChoiceAufgabe) (userloesungen.get(index)));
            }
            this.aktuellerFrame = frame;// Für funktionalität: TestatApp mit übergeben
            if (index + 1 >= sammlung.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Programmieren)) {
            BearbeiteProgrammieraufgabeView frame = new BearbeiteProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;// Für funktionalität: TestatApp mit übergeben
            if (index + 1 >= sammlung.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Design)) {
            BearbeiteDesignaufgabeView frame = new BearbeiteDesignaufgabeView(this, (Designaufgabe) aufgabe);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;// Für funktionalität: TestatApp mit übergeben
            if (index + 1 >= sammlung.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        }
        this.aktuellerFrame.setVisible(true);
    }

    /**
     * geht zurück zu der vorherigen Aufgabe
     */
    public void zurueck() {
        if (this.index > 0) {
            this.index--;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Keine vorherige Aufgabe");
        }
    }

    /**
     * geht weiter zu der nächsten Aufgabe
     */
    public void weiter() {
        if (this.index < sammlung.getAnzahlAufgaben() - 1) {
            this.index++;  //Index fuer Controller erhoet
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Keine weiteren Aufgaben. Klicken Sie auf Beenden");
        }
    }

    /**
     * Fügt die Userloesung der Userloesungenliste an dem passenden Index hinzu
     *
     * @param userloesung an dem passenden Index der Aufgabe
     */
    public void addUserloesung(Userloesung userloesung) {
        userloesungen.set(this.index, userloesung);
    }

    public Benutzer getAktuellerBenutzer() {
        return aktuellerBenutzer;
    }

    public static void main(String[] args) throws Exception {
        DatabaseService ds = DatabaseService.getInstance();
        List<Aufgabe> aufgabenliste;
        aufgabenliste = ds.readAufgabenFromDatabase();

        List<String> antwortmoeglichkeiten = new ArrayList<>();
        antwortmoeglichkeiten.add("Test1");
        antwortmoeglichkeiten.add("Test2");
        antwortmoeglichkeiten.add("Test3");

        List<String> antwortmoeglichkeiten1 = new ArrayList<>();
        antwortmoeglichkeiten1.add("Test1");
        antwortmoeglichkeiten1.add("Test2");
        antwortmoeglichkeiten1.add("Test3");
        antwortmoeglichkeiten1.add("Test4");

        List<String> antwortmoeglichkeiten2 = new ArrayList<>();
        antwortmoeglichkeiten2.add("Test1");
        antwortmoeglichkeiten2.add("Test2");


        Aufgabe a1 = new EinfachantwortAufgabe(10, null, Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null);
        Aufgabe a2 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null);
        Aufgabe a3 = new Designaufgabe(15, null, Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, null, Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, antwortmoeglichkeiten);

        Aufgabe a5 = new MultipleChoiceAufgabe(2, null, Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, antwortmoeglichkeiten1);
        Aufgabe a6 = new MultipleChoiceAufgabe(2, null, Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, antwortmoeglichkeiten2);


        MusterloesungEinfachantwort m1 = new MusterloesungEinfachantwort();
        a1.setMusterloesung(m1);
        m1.setLoesungshinweis("Lösungshinweis MusterloesungEinfachantwort");

        MusterloesungProgrammieraufgabe m2 = new MusterloesungProgrammieraufgabe();
        a2.setMusterloesung(m2);
        m2.setLoesungshinweis("Lösungshinweis MusterloesungProgrammieraufgabe");

        MusterloesungMultipleChoiceAufgabe m3 = new MusterloesungMultipleChoiceAufgabe();
        a4.setMusterloesung(m3);
        m3.setLoesungshinweis("Lösungshinweis MusterloesungMultipleChoiceAufgabe");

        MusterloesungDesignaufgabe m4 = new MusterloesungDesignaufgabe();
        a3.setMusterloesung(m4);
        m4.setLoesungshinweis("Lösungshinweis MusterloesungDesignaufgabe");

        java.util.List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});
        java.util.List<Aufgabe> aufgabenListe2 = Arrays.asList(new Aufgabe[]{a1, a1, a2, a2,});
        java.util.List<Aufgabe> aufgabenListe3 = Arrays.asList(new Aufgabe[]{a3, a3, a3, a4, a4, a4});
        java.util.List<Aufgabe> aufgabenListe4 = Arrays.asList(new Aufgabe[]{a4, a3, a2, a1});
        java.util.List<Aufgabe> aufgabenListe5 = Arrays.asList(new Aufgabe[]{a1, a1, a2, a2, a3, a3, a4, a4});

        java.util.List<Aufgabe> aufgabenListe6 = new ArrayList<>(); //= Arrays.asList(new Aufgabe[]{a4, a5, a6, a5,});
        aufgabenListe6.add(0, a4);
        aufgabenListe6.add(0, a5);
        java.util.List<Aufgabe> aufgabenListe7 = Arrays.asList(new Aufgabe[]{a4, a4, a5, a6});


        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Dozent dozent2 = new Dozent("PPanzer", "jklö", "Paul", "Panzer");
        Testat t1 = new Testat(aufgabenListe7, "Hallo1234", "Sommertestat", dozent1);

      /*
       Testat t2 = new Testat(aufgabenListe2, "asdf", "Wintertestat", dozent2);
        Testat t3 = new Testat(aufgabenListe3, "qwertz", "Herbsttestat", dozent1);
        java.util.List<Testat> testatliste = Arrays.asList(new Testat[]{t1, t2, t3, t1, t2, t3, t1, t2, t3, t1, t2, t3});
        Student student1 = new Student("AApfel", "aaa", "Adam", "Apfel", 1111);
       */


      //TestatController testatApp = new TestatController(t1, dozent2, null);
        Training training1 = new Training(aufgabenListe1, 10, Kategorie.Java_Programmierung, Schwierigkeitsgrad.Schwer, dozent1);
        TrainingController trainingController = new TrainingController(training1, dozent2, null);

    }

}
