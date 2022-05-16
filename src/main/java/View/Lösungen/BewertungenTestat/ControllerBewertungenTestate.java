package View.Lösungen.BewertungenTestat;

import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.benutzer.Benutzer;
import entity.loesung.userloesung.Userloesung;
import persistence.DatabaseService;

import java.util.List;

public class ControllerBewertungenTestate {
    private TestatBearbeitung testatBearbeitung;
    private Testat testat;
    private int index;
    public Benutzer benutzer;
    private DatabaseService ds = DatabaseService.getInstance();
    private List<Userloesung> userloesungList;

    public ControllerBewertungenTestate(TestatBearbeitung testatBearbeitung, Benutzer benutzer) {
        this.testatBearbeitung = testatBearbeitung;
        this.testat = ds.readTestatMitTestatbearbeitung(testatBearbeitung);
        this.index = 0;
        this.benutzer = benutzer;

        this.userloesungList = ds.readUserloesungVonTestat(testat, benutzer);

        startBewertungTestat();
    }

    public void setTestatBearbeitung(TestatBearbeitung testatBearbeitung) {
        this.testatBearbeitung = testatBearbeitung;
        this.testat = ds.readTestatMitTestatbearbeitung(testatBearbeitung);
        this.index = 0;

        this.userloesungList = ds.readUserloesungVonTestat(testat, benutzer);

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
                } catch (Exception ignored) {
                }
            }
            case Programmieren: {
                try {
                    assert aufgabe instanceof Programmieraufgabe;
                    BewertungProgrammieraufgabeView bPV = new BewertungProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                    bPV.versteckeVorherigeAufgabe();
                    if (testat.getAnzahlAufgaben() == 1) {
                        bPV.versteckeNaechsteAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
            case MultipleChoice: {
                try {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    BewertungMultipleChoiceAufgabeView bTMCV = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                    bTMCV.versteckeVorherigeAufgabe();
                    if (testat.getAnzahlAufgaben() == 1) {
                        bTMCV.versteckeNaechsteAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
            case Design: {
                try {
                    assert aufgabe instanceof Designaufgabe;
                    BewertungDesignaufgabeView bDV = new BewertungDesignaufgabeView((Designaufgabe) aufgabe, this);
                    bDV.versteckeVorherigeAufgabe();
                    if (testat.getAnzahlAufgaben() == 1) {
                        bDV.versteckeNaechsteAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }

    public void beendeBewertungTestat() {
        //zurück zum vorherigen Menü; Benutzer weitergeben!
        System.out.println("Hier gehts bald zurück zur Testatsübersicht.");
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
                } catch (Exception ignored) {
                }
            }
            case MultipleChoice: {
                try {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    BewertungMultipleChoiceAufgabeView lTMCV = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                    if (index == testat.getAnzahlAufgaben() - 1) {
                        lTMCV.versteckeNaechsteAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
            case Design: {
                try {
                    assert aufgabe instanceof Designaufgabe;
                    BewertungDesignaufgabeView lDV = new BewertungDesignaufgabeView((Designaufgabe) aufgabe, this);
                    if (index == testat.getAnzahlAufgaben() - 1) {
                        lDV.versteckeNaechsteAufgabe();
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
                } catch (Exception ignored) {
                }
            }
            case MultipleChoice: {
                try {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    BewertungMultipleChoiceAufgabeView lTMCV = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                    if (index == 0) {
                        lTMCV.versteckeVorherigeAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
            case Design: {
                try {
                    assert aufgabe instanceof Designaufgabe;
                    BewertungDesignaufgabeView lDV = new BewertungDesignaufgabeView((Designaufgabe) aufgabe, this);
                    if (index == 0) {
                        lDV.versteckeVorherigeAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }
}
