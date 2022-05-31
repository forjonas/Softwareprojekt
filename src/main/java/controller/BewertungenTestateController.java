package controller;

import view.KorrigiereTestatKatalogView;
import view.loesungen.bewertungenTestat.BewertungDesignaufgabeView;
import view.loesungen.bewertungenTestat.BewertungEinfachantwortaufgabeView;
import view.loesungen.bewertungenTestat.BewertungMultipleChoiceAufgabeView;
import view.loesungen.bewertungenTestat.BewertungProgrammieraufgabeView;
import view.MeineTestateKatalogView;
import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.loesung.userloesung.Userloesung;
import persistence.DatabaseService;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller-Klasse welche die Interaktion zwischen den Lösungsviews von Testatbearbeitungen darstellen und Klassen,
 * die diese aufrufen wollen, steuert.
 * Stellt zusätzlich die Funktionalitäten für die Bewertung von Testaten und der Persistierung der Eingaben.
 *
 * @author Timo Joswig
 * @version 29.05.22
 */

public class BewertungenTestateController {
    private final TestatBearbeitung testatBearbeitung;
    private final Testat testat;
    private final JFrame homeFrame;
    private int index;
    public Benutzer benutzer;
    private DatabaseService ds = DatabaseService.getInstance();
    private List<Userloesung> userloesungList;
    private List<Boolean> bewertetStatus;

    /**
     * Konstruktor für einen Controller der die Logik hinter der Bewertung und Einsicht von Testatbearbeitungen bereitstellt.
     *
     * @param testatBearbeitung Objekt welches das von einem Nutzer bearbeitetes Testat darstellt
     * @param benutzer          Der zurzeit angemeldete Benutzer
     * @param homeFrame             Das Hauptmenü der Klasse des angemeldeten Benutzers
     */
    public BewertungenTestateController(TestatBearbeitung testatBearbeitung, Benutzer benutzer, JFrame homeFrame) {
        this.homeFrame = homeFrame;
        this.testatBearbeitung = testatBearbeitung;
        this.testat = ds.readTestatMitTestatbearbeitung(testatBearbeitung);
        this.index = 0;
        this.benutzer = benutzer;
        this.userloesungList = new LinkedList<>();
        List<Userloesung> datenbanklisteUserloesungen = ds.readUserloesungenFromDatabse();
        for (Userloesung ul : datenbanklisteUserloesungen) {
            if (ul.getAufgabensammlung() == testat && ul.getUserloesungErsteller() == testatBearbeitung.getTestatBearbeiter()) {
                userloesungList.add(ul);
            }
        }
        this.userloesungList = ds.readUserloesungVonTestat(testat, testatBearbeitung.getTestatBearbeiter());
        this.bewertetStatus = new LinkedList<>();
        for (int i = 0; i < userloesungList.size(); i++) {
            bewertetStatus.add(false);
        }
        startBewertungTestat();
    }

    /**
     * Funktion die aus der bereits geladenen Liste aller Userlösungen, die eine zurückgibt, welche zur angegebenen Aufgabe passt.
     *
     * @param aufgabe Die Aufgabe zu welcher die passende Userlösung zurückgegeben werden soll
     * @return die passende Userlösung
     */
    public Userloesung getUserloesung(Aufgabe aufgabe) {
        Userloesung userloesung = null;
        for (Userloesung userloesungDB : userloesungList) {
            if (userloesungDB.getAufgabe().equals(aufgabe)) {
                userloesung = userloesungDB;
            }
        }
        return userloesung;
    }

    /**
     * Überprüft ob der zurzeit angemeldete Benutzer ein Dozent ist.
     *
     * @return Wahrheitswert ob der Benutzer der Klasse Dozent angehört.
     */
    public boolean userIstDozent() {
        return benutzer.getClass().equals(Dozent.class);
    }

    /**
     * Setzt in der internen Boolean-Liste des Controllers den Eintrag an der zur Zeit bearbeiteten Stelle auf true, nachdem dieser erfolgreich bewertet wurde.
     */
    public void setBewertet() {
        bewertetStatus.set(index, true);
    }

    /**
     * Überprüft, ob für jeden Eintrag in der internen Boolean-Liste jeder Eintrag als erfolgreich bewertet eingetragen wurde.
     *
     * @return true, falls für jede Aufgabe eine Bewertung gespeichert wurde und false, falls es nicht der Fall ist
     */
    public boolean bewertungVollstaendig() {
        boolean result = true;
        for (boolean b : bewertetStatus) {
            result = result && b;
        }
        return result;
    }

    /**
     * Im Falle, dass ein Dozent seine Bewertung abschließen möchte, wird zunächst die Gesamtpunktzahl des Testates errechnet,
     * der Dozent als Bewerter gesetzt und schließlich werden die geänderten Daten persistiert.
     *
     * Falls ein Student bloß seine Bewertung eingesehen hat, wird nur ein neuer TestatKatalogView erstellt.
     */
    public void beendeBewertungTestat() {
        if (benutzer.getClass().equals(Dozent.class)) {
            int counter = 0;
            for (Userloesung uL : userloesungList) {
                counter += uL.getErreichtePunkte();
            }
            testatBearbeitung.setErreichtePunktzahl(counter);
            testatBearbeitung.setTestatBewerter((Dozent) benutzer);
            ds.persistObject(testatBearbeitung);
            ds.persistObjects(userloesungList);
            new KorrigiereTestatKatalogView(homeFrame, (Dozent) benutzer);
        } else {
            new MeineTestateKatalogView(homeFrame, benutzer);
        }
    }

    /**
     * Beim Abbruch einer laufenden Testatbewertung oder bei der Einsicht eines bewerteten Testats durch einen Studenten wird
     * entsprechend der nächste View geladen.
     */
    public void abbrechenBewertungTestat() {
        if (benutzer.getClass().equals(Dozent.class)) {
            new KorrigiereTestatKatalogView(homeFrame, (Dozent) benutzer);
        } else {
            new MeineTestateKatalogView(homeFrame, benutzer);
        }
    }

    /**
     * Initialisierung des ersten Views des Testats, abhängig vom Aufgabentyps der ersten Aufgabe.
     * Im Falle einer Bewertung eines Testats durch einen Dozenten wird dem View dies mitgeteilt.
     */
    public void startBewertungTestat() {
        Aufgabe aufgabe = testat.getAufgaben().get(0);
        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                BewertungEinfachantwortaufgabeView bewertungEinfachantwortaufgabeView = new BewertungEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe, this);
                bewertungEinfachantwortaufgabeView.versteckeVorherigeAufgabe();
                if (testat.getAnzahlAufgaben() == 1) {
                    bewertungEinfachantwortaufgabeView.versteckeNaechsteAufgabe();
                }
                if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                    bewertungEinfachantwortaufgabeView.bewertbar();
                }
                break;
            }
            case Programmieren: {
                assert aufgabe instanceof Programmieraufgabe;
                BewertungProgrammieraufgabeView bewertungProgrammieraufgabeView = new BewertungProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                bewertungProgrammieraufgabeView.versteckeVorherigeAufgabe();
                if (testat.getAnzahlAufgaben() == 1) {
                    bewertungProgrammieraufgabeView.versteckeNaechsteAufgabe();
                }
                if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                    bewertungProgrammieraufgabeView.bewertbar();
                }
                break;
            }
            case MultipleChoice: {
                assert aufgabe instanceof MultipleChoiceAufgabe;
                BewertungMultipleChoiceAufgabeView bewertungMultipleChoiceAufgabeView = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                bewertungMultipleChoiceAufgabeView.versteckeVorherigeAufgabe();
                if (testat.getAnzahlAufgaben() == 1) {
                    bewertungMultipleChoiceAufgabeView.versteckeNaechsteAufgabe();
                }
                if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                    bewertungMultipleChoiceAufgabeView.bewertbar();
                }
                break;
            }
            case Design: {
                assert aufgabe instanceof Designaufgabe;
                BewertungDesignaufgabeView bewertungDesignaufgabeView = new BewertungDesignaufgabeView((Designaufgabe) aufgabe, this);
                bewertungDesignaufgabeView.versteckeVorherigeAufgabe();
                if (testat.getAnzahlAufgaben() == 1) {
                    bewertungDesignaufgabeView.versteckeNaechsteAufgabe();
                }
                if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                    bewertungDesignaufgabeView.bewertbar();
                }
                break;
            }
            default:
                break;
        }
    }

    /**
     * Initialisierung des Views der nächsten Aufgabe im Testat, abhängig vom internen Index und des entsprechenden
     * Aufgabentyps.
     */
    public void naechsteAufgabe() {
        index++;
        Aufgabe aufgabe = testat.getAufgaben().get(index);

        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                BewertungEinfachantwortaufgabeView bewertungEinfachantwortaufgabeView = new BewertungEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe, this);
                if (index == testat.getAnzahlAufgaben() - 1) {
                    bewertungEinfachantwortaufgabeView.versteckeNaechsteAufgabe();
                }
                if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                    bewertungEinfachantwortaufgabeView.bewertbar();
                }
                break;
            }
            case Programmieren: {
                assert aufgabe instanceof Programmieraufgabe;
                BewertungProgrammieraufgabeView bewertungProgrammieraufgabeView = new BewertungProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                if (index == testat.getAnzahlAufgaben() - 1) {
                    bewertungProgrammieraufgabeView.versteckeNaechsteAufgabe();
                }
                if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                    bewertungProgrammieraufgabeView.bewertbar();
                }
                break;
            }
            case MultipleChoice: {
                assert aufgabe instanceof MultipleChoiceAufgabe;
                BewertungMultipleChoiceAufgabeView bewertungMultipleChoiceAufgabeView = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                if (index == testat.getAnzahlAufgaben() - 1) {
                    bewertungMultipleChoiceAufgabeView.versteckeNaechsteAufgabe();
                }
                if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                    bewertungMultipleChoiceAufgabeView.bewertbar();
                }
                break;
            }
            case Design: {
                assert aufgabe instanceof Designaufgabe;
                BewertungDesignaufgabeView bewertungDesignaufgabeView = new BewertungDesignaufgabeView((Designaufgabe) aufgabe, this);
                if (index == testat.getAnzahlAufgaben() - 1) {
                    bewertungDesignaufgabeView.versteckeNaechsteAufgabe();
                }
                if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                    bewertungDesignaufgabeView.bewertbar();
                }
                break;
            }
        }
    }

    /**
     * Initialisierung des Views der vorherigen Aufgabe im Testat, abhängig vom internen Index und des entsprechenden
     * Aufgabentyps.
     */
    public void vorherigeAufgabe() {
        index--;
        Aufgabe aufgabe = testat.getAufgaben().get(index);

        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                BewertungEinfachantwortaufgabeView bewertungEinfachantwortaufgabeView = new BewertungEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe, this);
                if (index == 0) {
                    bewertungEinfachantwortaufgabeView.versteckeVorherigeAufgabe();
                }
                if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                    bewertungEinfachantwortaufgabeView.bewertbar();
                }
                break;
            }
            case Programmieren: {
                assert aufgabe instanceof Programmieraufgabe;
                BewertungProgrammieraufgabeView bewertungProgrammieraufgabeView = new BewertungProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                if (index == 0) {
                    bewertungProgrammieraufgabeView.versteckeVorherigeAufgabe();
                }
                if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                    bewertungProgrammieraufgabeView.bewertbar();
                }
                break;
            }
            case MultipleChoice: {
                assert aufgabe instanceof MultipleChoiceAufgabe;
                BewertungMultipleChoiceAufgabeView bewertungMultipleChoiceAufgabeView = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                if (index == 0) {
                    bewertungMultipleChoiceAufgabeView.versteckeVorherigeAufgabe();
                }
                if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                    bewertungMultipleChoiceAufgabeView.bewertbar();
                }
                break;
            }
            case Design: {
                assert aufgabe instanceof Designaufgabe;
                BewertungDesignaufgabeView bewertungDesignaufgabeView = new BewertungDesignaufgabeView((Designaufgabe) aufgabe, this);
                if (index == 0) {
                    bewertungDesignaufgabeView.versteckeVorherigeAufgabe();
                }
                if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                    bewertungDesignaufgabeView.bewertbar();
                }
                break;
            }
        }
    }
}
