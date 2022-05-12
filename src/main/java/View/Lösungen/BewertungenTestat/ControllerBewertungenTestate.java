package View.Lösungen.BewertungenTestat;

import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;

public class ControllerBewertungenTestate {
    private static ControllerBewertungenTestate instance;
    private Testat testat;
    private int index;

    private ControllerBewertungenTestate() {

    }

    public static ControllerBewertungenTestate getInstance() {
        if (instance == null) {
            instance = new ControllerBewertungenTestate();
        }
        return instance;
    }

    public void setTestat(Testat testat) {
        this.testat = testat;
        this.index = 0;
        startBewertungTestat();
    }

    public void startBewertungTestat() {
        Aufgabe aufgabe = testat.getAufgaben().get(0);
        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                try {
                    BewertungEinfachantwortView bEV = new BewertungEinfachantwortView((EinfachantwortAufgabe) aufgabe);
                    bEV.versteckeVorherigeAufgabe();
                } catch (Exception ignored) {
                }
            }
            case Programmieren: {
                try {
                    assert aufgabe instanceof Programmieraufgabe;
                    BewertungProgrammieraufgabeView bPV = new BewertungProgrammieraufgabeView((Programmieraufgabe) aufgabe);
                    bPV.versteckeVorherigeAufgabe();
                } catch (Exception ignored) {
                }
            }
            case MultipleChoice: {
                try {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    BewertungMultipleChoiceAufgabeView lTMCV = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe);
                    lTMCV.versteckeVorherigeAufgabe();
                } catch (Exception ignored) {
                }
            }
            case Design: {
                try {
                    assert aufgabe instanceof Designaufgabe;
                    BewertungDesignaufgabeView lDV = new BewertungDesignaufgabeView((Designaufgabe) aufgabe);
                    lDV.versteckeVorherigeAufgabe();
                } catch (Exception ignored) {
                }
            }
        }
    }

    public void beendeBewertungTestat() {
        //zurück zum vorherigen Menü
        System.out.println("Hier gehts bald zurück zur Testatsübersicht.");
    }

    public void naechsteAufgabe() {
        index++;
        Aufgabe aufgabe = testat.getAufgaben().get(index);
        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                try {
                    BewertungEinfachantwortView bEV = new BewertungEinfachantwortView((EinfachantwortAufgabe) aufgabe);
                    if (index == testat.getAnzahlAufgaben() - 1) {
                        bEV.versteckeNaechsteAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
            case Programmieren: {
                try {
                    assert aufgabe instanceof Programmieraufgabe;
                    BewertungProgrammieraufgabeView bPV = new BewertungProgrammieraufgabeView((Programmieraufgabe) aufgabe);
                    if (index == testat.getAnzahlAufgaben() - 1) {
                        bPV.versteckeNaechsteAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
            case MultipleChoice: {
                try {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    BewertungMultipleChoiceAufgabeView lTMCV = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe);
                    if (index == testat.getAnzahlAufgaben() - 1) {
                        lTMCV.versteckeNaechsteAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
            case Design: {
                try {
                    assert aufgabe instanceof Designaufgabe;
                    BewertungDesignaufgabeView lDV = new BewertungDesignaufgabeView((Designaufgabe) aufgabe);
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
                    BewertungEinfachantwortView bEV = new BewertungEinfachantwortView((EinfachantwortAufgabe) aufgabe);
                    if (index == 0) {
                        bEV.versteckeVorherigeAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
            case Programmieren: {
                try {
                    assert aufgabe instanceof Programmieraufgabe;
                    BewertungProgrammieraufgabeView bPV = new BewertungProgrammieraufgabeView((Programmieraufgabe) aufgabe);
                    if (index == 0) {
                        bPV.versteckeVorherigeAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
            case MultipleChoice: {
                try {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    BewertungMultipleChoiceAufgabeView lTMCV = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe);
                    if (index == 0) {
                        lTMCV.versteckeVorherigeAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
            case Design: {
                try {
                    assert aufgabe instanceof Designaufgabe;
                    BewertungDesignaufgabeView lDV = new BewertungDesignaufgabeView((Designaufgabe) aufgabe);
                    if (index == 0) {
                        lDV.versteckeVorherigeAufgabe();
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }
}
