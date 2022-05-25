package app;

import View.AufgabenBearbeiten.Testat.*;
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
 * @version7: 23.05.22 Button von "nächste" zu "beenden" u.v.m
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

    /**
     * Konstruktor für Klasse TestatController
     *
     * @param testat
     * @param aktuellerBenutzer
     * @param hauptmenueFrame
     */
    public TestatController(Testat testat, Benutzer aktuellerBenutzer, JFrame hauptmenueFrame) {
        this.hauptmenueFrame = hauptmenueFrame;
        this.index = 0;
        this.testat = testat;
        this.userloesungen = new ArrayList<>();
        for (int i = 0; i < testat.getAnzahlAufgaben(); i++) {
            userloesungen.add(i, null);
        }
        this.aktuellerBenutzer = aktuellerBenutzer;
        this.testatBearbeitung = new TestatBearbeitung(testat, 0, aktuellerBenutzer, null);
        this.testat.addBearbeitung(testatBearbeitung);
        zeigeAktuelleAufgabe();
    }

    /**
     * Setzt TestatKatalog
     */
    public void setNewTestatKatalog() {
        new MeineTestateKatalogView(hauptmenueFrame, aktuellerBenutzer);
    }

    /**
     * Zeigt die aktuelle Aufgabe die zu bearbeiten ist an
     */
    public void zeigeAktuelleAufgabe() {
        aufgabe = testat.getAufgaben().get(this.index);

        if (this.aktuellerFrame != null) {
            this.aktuellerFrame.dispose();
        }
        if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Einfachantwort)) {
            BearbeiteTestatEinfachantwortAufgabeView frame = new BearbeiteTestatEinfachantwortAufgabeView(this, (EinfachantwortAufgabe) aufgabe);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;
            if (index + 1 >= testat.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.MultipleChoice)) {
            BearbeiteTestatMultipleChoiceAufgabeView frame = new BearbeiteTestatMultipleChoiceAufgabeView(this, (MultipleChoiceAufgabe) aufgabe);

            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;
            if (index + 1 >= testat.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Programmieren)) {
            BearbeiteTestatProgrammieraufgabeView frame = new BearbeiteTestatProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;
            if (index + 1 >= testat.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        } else if (aufgabe.getAufgabentyp().equals(Aufgabentyp.Design)) {
            BearbeiteTestatDesignaufgabeView frame = new BearbeiteTestatDesignaufgabeView(this, (Designaufgabe) aufgabe);
            if (userloesungen.get(index) == null) {
                frame.setUserloesungNull();
            } else frame.setUserloesung(userloesungen.get(index));
            this.aktuellerFrame = frame;
            if (index + 1 >= testat.getAnzahlAufgaben()) {
                frame.setNaechsteZuSpeichern();
            }
        }
        this.aktuellerFrame.setVisible(true);
    }

    /**
     * Erhöht den Index und zeigt die nächste Aufgabe die zu bearbeiten ist an
     */
    public void weiter() {
        if (this.index < testat.getAnzahlAufgaben() - 1) {
            this.index++;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Keine weiteren Aufgaben. Klicken Sie auf Beenden");
        }
    }

    /**
     * Verringert den Index und zeigt die vorherige Aufgabe die bearbeitet wurde an
     */
    public void zurueckTestat() {
        if (this.index > 0) {
            this.index--;
            zeigeAktuelleAufgabe();
        } else {
            JOptionPane.showMessageDialog(null, "Keine vorherige Aufgabe");
        }
    }

    /**
     * Fügt die Userloesung der Userloesungenliste an dem passenden hinzu
     *
     * @param userloesung
     */
    public void addUserloesung(Userloesung userloesung) {
        userloesungen.set(this.index, userloesung);
    }

    /**
     * Fügt der Userloesung den UserloesungErsteller hinzu und persistiert die Userlösungen in der Datenbank
     * Fügt den aktuellerBenutzer die testatBearbeitung hinzu
     */
    public void persistTestat() {
        for (Userloesung userloesung : userloesungen) {
            userloesung.getUserloesungErsteller().addErstellteLoesung(userloesung);
            try {
                userloesung.getAufgabe().addUserloesung(userloesung);
            } catch (Exception ignored) {
            }
            userloesung.getAufgabensammlung().addUserloesung(userloesung);
        }


        aktuellerBenutzer.addBearbeitetesTestat(testatBearbeitung);
        DatabaseService ds = DatabaseService.getInstance();
        ds.persistObjects(userloesungen);
        ds.persistObject(testat);
        ds.persistObject(testatBearbeitung);

        System.out.println(userloesungen);
        new MeineTestateKatalogView(hauptmenueFrame, aktuellerBenutzer);
    }

    /**
     * @return das aktuelle Testat
     */
    public Testat getTestat() {
        return testat;
    }

    /**
     * @return den aktuelle Benutzer
     */
    public Benutzer getAktuellerBenutzer() {
        return aktuellerBenutzer;
    }

}