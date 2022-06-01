package controller;

import view.aufgabenBearbeiten.aufgabenSammlungenBearbeiten.*;
import entity.aufgabe.*;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.benutzer.Benutzer;
import entity.enums.Aufgabentyp;
import entity.loesung.userloesung.Userloesung;
import entity.loesung.userloesung.UserloesungMultipleChoiceAufgabe;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kristin Kubisch
 * @version: 31.05.22
 * Abstrakte Oberklasse von TestatController und TrainingsController
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
     * @param sammlung die Sammlung einer Aufgabensammlung
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
    public void zeigeAktuelleAufgabe() {
        aufgabe = sammlung.getAufgaben().get(this.index);

        if (this.aktuellerFrame != null) {
            this.aktuellerFrame.dispose();
        }
        if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Einfachantwort)) {
            BearbeiteEinfachantwortAufgabeView frame = new BearbeiteEinfachantwortAufgabeView(this, (EinfachantwortAufgabe) aufgabe);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;
            if (index + 1 >= sammlung.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.MultipleChoice)) {
            BearbeiteMultipleChoiceAufgabeView frame = new BearbeiteMultipleChoiceAufgabeView(this, (MultipleChoiceAufgabe) aufgabe);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else {
                frame.setUserloesung(userloesungen.get(index));
            }
            this.aktuellerFrame = frame;
            if (index + 1 >= sammlung.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Programmieren)) {
            BearbeiteProgrammieraufgabeView frame = new BearbeiteProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;
            if (index + 1 >= sammlung.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Design)) {
            BearbeiteDesignaufgabeView frame = new BearbeiteDesignaufgabeView(this, (Designaufgabe) aufgabe);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;
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
            this.index++;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Keine weiteren Aufgaben. Klicken Sie auf Beenden");
        }
    }

    /**
     * Fügt die Userloesung der Userloesungenliste an dem passenden Index hinzu
     *
     * @param userloesung Die Eingaben der jeweiligen Aufgabe von dem Benutzer
     */
    public void addUserloesung(Userloesung userloesung) {
        userloesungen.set(this.index, userloesung);
    }

    public Benutzer getAktuellerBenutzer() {
        return aktuellerBenutzer;
    }

}
