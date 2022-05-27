package controller;

import view.MeineTestateKatalogView;
import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.benutzer.Student;
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
public class TestatController extends BearbeitungsController {

    protected Testat testat;
    protected TestatBearbeitung testatBearbeitung;

    /**
     * Konstruktor für Klasse TestatController
     *
     * @param testat            mit den zu bearbeitenden Aufgaben
     * @param aktuellerBenutzer der das Testat bearbeitet
     * @param hauptmenueFrame   Hauptmenü Fenster des Benutzers
     */
    public TestatController(Testat testat, Benutzer aktuellerBenutzer, JFrame hauptmenueFrame) {
        super(testat, aktuellerBenutzer, hauptmenueFrame);
        this.testatBearbeitung = new TestatBearbeitung(testat, 0, aktuellerBenutzer, null);
        this.testat = testat;
        this.testat.addBearbeitung(testatBearbeitung);
        super.zeigeAktuelleAufgabe();
    }

    /**
     * Setzt TestatKatalog
     */
    public void setNewTestatKatalog() {
        new MeineTestateKatalogView(hauptmenueFrame, aktuellerBenutzer);
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

}