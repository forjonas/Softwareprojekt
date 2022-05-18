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

public class ControllerBewertungenTestate {
    private TestatBearbeitung testatBearbeitung;
    private Testat testat;
    private JFrame jframe;
    private int index;
    public Benutzer benutzer;
    private DatabaseService ds = DatabaseService.getInstance();
    private List<Userloesung> userloesungList;
    private List<Boolean> bewertetStatus;

    public ControllerBewertungenTestate(TestatBearbeitung testatBearbeitung, Benutzer benutzer, JFrame frame) {
        this.jframe = frame;
        this.testatBearbeitung = testatBearbeitung;
        this.testat = ds.readTestatMitTestatbearbeitung(testatBearbeitung);
        this.index = 0;
        this.benutzer = benutzer;
        this.userloesungList = new LinkedList<Userloesung>();
        List<Userloesung> datenbankliste = ds.readUserloesungenFromDatabse();
        for(Userloesung ul : datenbankliste) {
            if(ul.getAufgabensammlung() == testat && ul.getUserloesungErsteller() == testatBearbeitung.getTestatBearbeiter()) {
                userloesungList.add(ul);
            }
        }
        System.out.println(userloesungList.size());
        for(Userloesung ul : userloesungList) {
            System.out.println(ul.getUserloesungErsteller() + "  " + ul.getAufgabe().getName());
        }
        //this.userloesungList = ds.readUserloesungVonTestat(testat, testatBearbeitung.getTestatBearbeiter());
        this.bewertetStatus = new LinkedList<>();
        for (int i = 0; i < userloesungList.size(); i++) {
            bewertetStatus.add(false);
        }
        System.out.println("Bewertung Controller");
        startBewertungTestat();
    }

    public void setTestatBearbeitung(TestatBearbeitung testatBearbeitung) {
        this.testatBearbeitung = testatBearbeitung;
        this.testat = ds.readTestatMitTestatbearbeitung(testatBearbeitung);
        this.index = 0;
        this.userloesungList = new LinkedList<Userloesung>();
        List<Userloesung> datenbankliste = ds.readUserloesungenFromDatabse();
        for(Userloesung ul : datenbankliste) {
            if(ul.getAufgabensammlung() == testat && ul.getUserloesungErsteller() == testatBearbeitung.getTestatBearbeiter()) {
                userloesungList.add(ul);
            }
        }
        //this.userloesungList = ds.readUserloesungVonTestat(testat, testatBearbeitung.getTestatBearbeiter());
        this.bewertetStatus = new LinkedList<>();
        for (int i = 0; i < userloesungList.size(); i++) {
            bewertetStatus.add(false);
        }

        startBewertungTestat();
    }

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

    public void setBewertet() {
        bewertetStatus.set(index, true);
    }

    public boolean bewertungVollstaendig() {
        boolean result = true;
        for (boolean b:bewertetStatus) {
            result = result && b;
        }
        return result;
    }

    public void beendeBewertungTestat() {
        if (benutzer.getClass().equals(Dozent.class)) {
            int counter = 0;
            for (Userloesung uL:userloesungList) {
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

    public void abbrechenBewertungTestat() {
        if (benutzer.getClass().equals(Dozent.class)) {
            new KorrigiereTestatKatalogView(jframe, (Dozent) benutzer);
        } else {
            new MeineTestateKatalogView(jframe, benutzer);
        }
    }

    public void startBewertungTestat() {
        System.out.println("Bewertung einer Aufgabe");
        Aufgabe aufgabe = testat.getAufgaben().get(0);
        System.out.println(aufgabe.getAufgabentyp().getCode());
        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                try {
                    BewertungEinfachantwortView bEV = new BewertungEinfachantwortView((EinfachantwortAufgabe) aufgabe, this);
                    bEV.versteckeVorherigeAufgabe();
                    if (testat.getAnzahlAufgaben() == 1) {
                        bEV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent()) {
                        bEV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                System.out.println("Einfach");
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
                    if (userIstDozent()) {
                        bPV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                System.out.println("Programm");
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
                    if (userIstDozent()) {
                        bMCV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
                System.out.println("Multiple");
                break;
            }
            case Design: {
//                try {
                    assert aufgabe instanceof Designaufgabe;
                    BewertungDesignaufgabeView bDV = new BewertungDesignaufgabeView((Designaufgabe) aufgabe, this);
                    bDV.versteckeVorherigeAufgabe();
                    if (testat.getAnzahlAufgaben() == 1) {
                        bDV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent()) {
                        bDV.bewertbar();
                    }
//                } catch (Exception ignored) {
//                }
                System.out.println("Design");
                break;
            } default: System.out.println("Dumm");
        }
    }

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
                    if (userIstDozent()) {
                        bEV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
            }
            case Programmieren: {
                try {
                    assert aufgabe instanceof Programmieraufgabe;
                    BewertungProgrammieraufgabeView bPV = new BewertungProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                    if (index == testat.getAnzahlAufgaben() - 1) {
                        bPV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent()) {
                        bPV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
            }
            case MultipleChoice: {
                try {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    BewertungMultipleChoiceAufgabeView bMCV = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                    if (index == testat.getAnzahlAufgaben() - 1) {
                        bMCV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent()) {
                        bMCV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
            }
            case Design: {
                try {
                    assert aufgabe instanceof Designaufgabe;
                    BewertungDesignaufgabeView bDV = new BewertungDesignaufgabeView((Designaufgabe) aufgabe, this);
                    if (index == testat.getAnzahlAufgaben() - 1) {
                        bDV.versteckeNaechsteAufgabe();
                    }
                    if (userIstDozent()) {
                        bDV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }

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
                    if (userIstDozent()) {
                        bEV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
            }
            case Programmieren: {
                try {
                    assert aufgabe instanceof Programmieraufgabe;
                    BewertungProgrammieraufgabeView bPV = new BewertungProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                    if (index == 0) {
                        bPV.versteckeVorherigeAufgabe();
                    }
                    if (userIstDozent()) {
                        bPV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
            }
            case MultipleChoice: {
                try {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    BewertungMultipleChoiceAufgabeView bMCV = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                    if (index == 0) {
                        bMCV.versteckeVorherigeAufgabe();
                    }
                    if (userIstDozent()) {
                        bMCV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
            }
            case Design: {
                try {
                    assert aufgabe instanceof Designaufgabe;
                    BewertungDesignaufgabeView bDV = new BewertungDesignaufgabeView((Designaufgabe) aufgabe, this);
                    if (index == 0) {
                        bDV.versteckeVorherigeAufgabe();
                    }
                    if (userIstDozent()) {
                        bDV.bewertbar();
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }
}
