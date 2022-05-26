package View.Lösungen.BewertungenTestat;

import View.KorrigiereTestatKatalogView;
import View.MeineTestateKatalogView;
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
 * @version 19.05.22
 */

public class ControllerBewertungenTestate {
    private TestatBearbeitung testatBearbeitung;
    private Testat testat;
    private JFrame jframe;
    private int index;
    public Benutzer benutzer;
    private DatabaseService ds = DatabaseService.getInstance();
    private List<Userloesung> userloesungList;
    private List<Boolean> bewertetStatus;

    /**
     * Konstruktor für einen Controller welcher die Logik hinter der Bewertung und Einsicht von Testatbearbeitungen bereitstellt
     *
     * @param testatBearbeitung Objekt welches das von einem Nutzer bearbeitetes Testat darsellt
     * @param benutzer          Der zur Zeit angemeldete Benutzer
     * @param frame             Das Hauptmenü der Klasse des angemeldeten Benutzers
     */
    public ControllerBewertungenTestate(TestatBearbeitung testatBearbeitung, Benutzer benutzer, JFrame frame) {
        this.jframe = frame;
        this.testatBearbeitung = testatBearbeitung;
        this.testat = ds.readTestatMitTestatbearbeitung(testatBearbeitung);
        this.index = 0;
        this.benutzer = benutzer;
        this.userloesungList = new LinkedList<Userloesung>();
        List<Userloesung> datenbankliste = ds.readUserloesungenFromDatabse();
        for (Userloesung ul : datenbankliste) {
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
     * Eine Funktion welche eine nachträgliche Änderung der Testatbearbeitung ermöglicht
     *
     * @param testatBearbeitung die Testatbearbeitung die nun dargestellt werden soll
     */
    public void setTestatBearbeitung(TestatBearbeitung testatBearbeitung) {
        this.testatBearbeitung = testatBearbeitung;
        this.testat = ds.readTestatMitTestatbearbeitung(testatBearbeitung);
        this.index = 0;
        this.userloesungList = new LinkedList<Userloesung>();
        this.userloesungList = ds.readUserloesungVonTestat(testat, testatBearbeitung.getTestatBearbeiter());
        this.bewertetStatus = new LinkedList<>();
        for (int i = 0; i < userloesungList.size(); i++) {
            bewertetStatus.add(false);
        }

        startBewertungTestat();
    }

    /**
     * Funktion die aus der bereits geladenen Liste aller Userlösungen die eine zurückgibt, welche zur angegebenen Aufgabe passt
     *
     * @param aufgabe Die Aufgabe zu welcher die passende Userlösung zurückgegeben werden soll
     * @return die passende Userlösung
     */
    public Userloesung getUserloesung(Aufgabe aufgabe) {
        Userloesung userloesung = null;
        for (Userloesung uDB : userloesungList) {
            if (uDB.getAufgabe().equals(aufgabe)) {
                userloesung = uDB;
            }
        }
        return userloesung;
    }


    public boolean userIstDozent() {
        return benutzer.getClass().equals(Dozent.class);
    }

    /**
     * Setzt in der internen Boolean-Liste des Controllers den Eintrag an der zur Zeit bearbeiteten Stelle auf true, nachdem dieser erfolgreich bewertet wurde
     */
    public void setBewertet() {
        bewertetStatus.set(index, true);
    }

    /**
     * Überprüft, ob für jeden Eintrag in der internen Boolean-Liste jeder Eintrag als erfolgreich bewertet eingetragen wurde
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
     * der Dozent als Bewerter gesetzt und schließlich werden die geänderten Daten persistiert
     * <p>
     * Falls ein Student bloß seine Bewertung eingesehen hat, wird nur ein neuer TestatKatalogView erstellt
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
            new KorrigiereTestatKatalogView(jframe, (Dozent) benutzer);
        } else {
            new MeineTestateKatalogView(jframe, benutzer);
        }
    }

    /**
     * Beim Abbruch einer laufenden Testatbewertung oder bei der Einsicht eines bewerteten Testats durch einen Studenten wird
     * entsprechend der nächste View geladen
     */
    public void abbrechenBewertungTestat() {
        if (benutzer.getClass().equals(Dozent.class)) {
            new KorrigiereTestatKatalogView(jframe, (Dozent) benutzer);
        } else {
            new MeineTestateKatalogView(jframe, benutzer);
        }
    }

    /**
     * Initialisierung des ersten Views des Testats abhängig vom Aufgabentyps der ersten Aufgabe.
     * Im Falle einer Bewertung eines Testats durch einen Dozenten wird dem View dies mitgeteilt
     */
    public void startBewertungTestat() {
        Aufgabe aufgabe = testat.getAufgaben().get(0);
        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                try {
                    BewertungEinfachantwortView bEV = new BewertungEinfachantwortView((EinfachantwortAufgabe) aufgabe, this);
                    bEV.versteckeVorherigeAufgabe();
                    if (testat.getAnzahlAufgaben() == 1) {
                        bEV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                        bEV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                break;
            }
            case Programmieren: {
                try {
                    assert aufgabe instanceof Programmieraufgabe;
                    BewertungProgrammieraufgabeView bPV = new BewertungProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                    bPV.versteckeVorherigeAufgabe();
                    if (testat.getAnzahlAufgaben() == 1) {
                        bPV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                        bPV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                break;
            }
            case MultipleChoice: {
                try {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    BewertungMultipleChoiceAufgabeView bMCV = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                    bMCV.versteckeVorherigeAufgabe();
                    if (testat.getAnzahlAufgaben() == 1) {
                        bMCV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                        bMCV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                break;
            }
            case Design: {
                try {
                    assert aufgabe instanceof Designaufgabe;
                    BewertungDesignaufgabeView bDV = new BewertungDesignaufgabeView((Designaufgabe) aufgabe, this);
                    bDV.versteckeVorherigeAufgabe();
                    if (testat.getAnzahlAufgaben() == 1) {
                        bDV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                        bDV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                break;
            }
            default:
                break;
        }
    }

    /**
     * Initialisierung des Views der nächsten Aufgabe im Testat, abhängig vom internen Index und des entsprechenden
     * Aufgabentyps
     */
    public void naechsteAufgabe() {
        index++;
        Aufgabe aufgabe = testat.getAufgaben().get(index);

        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                try {
                    BewertungEinfachantwortView bEV = new BewertungEinfachantwortView((EinfachantwortAufgabe) aufgabe, this);
                    if (index == testat.getAnzahlAufgaben() - 1) {
                        bEV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                        bEV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                break;
            }
            case Programmieren: {
                try {
                    assert aufgabe instanceof Programmieraufgabe;
                    BewertungProgrammieraufgabeView bPV = new BewertungProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                    if (index == testat.getAnzahlAufgaben() - 1) {
                        bPV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                        bPV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                break;
            }
            case MultipleChoice: {
                try {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    BewertungMultipleChoiceAufgabeView bMCV = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                    if (index == testat.getAnzahlAufgaben() - 1) {
                        bMCV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                        bMCV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                break;
            }
            case Design: {
                try {
                    assert aufgabe instanceof Designaufgabe;
                    BewertungDesignaufgabeView bDV = new BewertungDesignaufgabeView((Designaufgabe) aufgabe, this);
                    if (index == testat.getAnzahlAufgaben() - 1) {
                        bDV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                        bDV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                break;
            }
        }
    }

    /**
     * Initialisierung des Views der vorherigen Aufgabe im Testat, abhängig vom internen Index und des entsprechenden
     * Aufgabentyps
     */
    public void vorherigeAufgabe() {
        index--;
        Aufgabe aufgabe = testat.getAufgaben().get(index);

        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                try {
                    BewertungEinfachantwortView bEV = new BewertungEinfachantwortView((EinfachantwortAufgabe) aufgabe, this);
                    if (index == 0) {
                        bEV.versteckeVorherigeAufgabe();
                    }
                    if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                        bEV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                break;
            }
            case Programmieren: {
                try {
                    assert aufgabe instanceof Programmieraufgabe;
                    BewertungProgrammieraufgabeView bPV = new BewertungProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                    if (index == 0) {
                        bPV.versteckeVorherigeAufgabe();
                    }
                    if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                        bPV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                break;
            }
            case MultipleChoice: {
                try {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    BewertungMultipleChoiceAufgabeView bMCV = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                    if (index == 0) {
                        bMCV.versteckeVorherigeAufgabe();
                    }
                    if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                        bMCV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                break;
            }
            case Design: {
                try {
                    assert aufgabe instanceof Designaufgabe;
                    BewertungDesignaufgabeView bDV = new BewertungDesignaufgabeView((Designaufgabe) aufgabe, this);
                    if (index == 0) {
                        bDV.versteckeVorherigeAufgabe();
                    }
                    if (userIstDozent() && testatBearbeitung.getTestatBewerter() == null) {
                        bDV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                break;
            }
        }
    }
}
