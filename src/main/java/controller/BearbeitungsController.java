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

}
