package controller;

import view.MeineTestateKatalogView;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.benutzer.Benutzer;
import entity.loesung.userloesung.Userloesung;
import persistence.DatabaseService;

import javax.swing.*;

/**
 * @author Kristin Kubisch
 * @version: 28.05.2022
 * Schnittstelle um ein Testat auszuführen
 */
public class TestatController extends BearbeitungsController {

    protected Testat testat;
    protected TestatBearbeitung testatBearbeitung;

    /**
     * Konstruktor für Klasse TestatController
     *
     * @param testat mit den zu bearbeitenden Aufgaben
     * @param aktuellerBenutzer der das Testat bearbeitet
     * @param hauptmenueFrame Hauptmenü Fenster des Benutzers
     */
    public TestatController(Testat testat, Benutzer aktuellerBenutzer, JFrame hauptmenueFrame) {
        super(testat, aktuellerBenutzer, hauptmenueFrame);
        this.testatBearbeitung = new TestatBearbeitung(testat, 0, aktuellerBenutzer, null);
        this.testat = testat;
        this.testat.addBearbeitung(testatBearbeitung);
        super.zeigeAktuelleAufgabe(); //für manche relevant ??
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
    public void persistTestat() {//usereingaben Liste persistieren
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
        ds.persistObject(testat);//nötig????
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