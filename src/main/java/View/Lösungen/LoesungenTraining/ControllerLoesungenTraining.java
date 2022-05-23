package View.Lösungen.LoesungenTraining;

import View.EinsehenTrainingKatalogView;
import entity.aufgabe.*;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.loesung.userloesung.Userloesung;
import persistence.DatabaseService;

import javax.swing.*;
import java.util.List;

public class ControllerLoesungenTraining {

    private Training training;
    private JFrame frame;
    private int index;
    public Benutzer benutzer;
    private DatabaseService ds = DatabaseService.getInstance();
    private List<Userloesung> userloesungList;

    public ControllerLoesungenTraining(Training training, Benutzer benutzer, JFrame frame) {
        this.frame = frame;
        this.training = training;
        this.index = 0;
        this.benutzer = benutzer;

        this.userloesungList = ds.readUserloesungVonTraining(training, training.getTrainingsErsteller());

        startLoesungTraining();
    }

    public void setTraining(Training training) {
        this.training = training;
        this.index = 0;

        this.userloesungList = ds.readUserloesungVonTraining(training, training.getTrainingsErsteller());

        startLoesungTraining();
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

    public void startLoesungTraining() {
        Aufgabe aufgabe = training.getAufgaben().get(0);
        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                try {
                    LoesungTrainingEinfachantwortaufgabeView lTEV = new LoesungTrainingEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe, this);
                    lTEV.versteckeVorherigeAufgabe();
                    if (training.getAnzahlAufgaben() == 1) {
                        lTEV.versteckeNaechsteAufgabe();
                    }
                } catch (Exception ignored) {
                }break;
            }
            case Programmieren: {
                try {
                    assert aufgabe instanceof Programmieraufgabe;
                    LoesungTrainingProgrammieraufgabeView lTPV = new LoesungTrainingProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                    lTPV.versteckeVorherigeAufgabe();
                    if (training.getAnzahlAufgaben() == 1) {
                        lTPV.versteckeNaechsteAufgabe();
                    }
                } catch (Exception ignored) {
                }break;
            }
            case MultipleChoice: {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    LoesungTrainingMultipleChoiceAufgabeView lTMCV = new LoesungTrainingMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                    lTMCV.versteckeVorherigeAufgabe();
                    if (training.getAnzahlAufgaben() == 1) {
                        lTMCV.versteckeNaechsteAufgabe();
                    }
                break;
            }
            case Design: {
                try {
                    assert aufgabe instanceof Designaufgabe;
                    LoesungTrainingDesignaufgabeView lDV = new LoesungTrainingDesignaufgabeView((Designaufgabe) aufgabe, this);
                    lDV.versteckeVorherigeAufgabe();
                    if (training.getAnzahlAufgaben() == 1) {
                        lDV.versteckeNaechsteAufgabe();
                    }
                } catch (Exception ignored) {
                }break;
            }
        }
    }

    public void beendeLoesungTraining() {
        if (benutzer.getClass().equals(Dozent.class)) {
            new EinsehenTrainingKatalogView(frame, (Dozent) benutzer);
        } else {
            frame.setVisible(true);
        }
    }

    public void naechsteAufgabe() {
        index++;
        Aufgabe aufgabe = training.getAufgaben().get(index);
        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                LoesungTrainingEinfachantwortaufgabeView lTEV = new LoesungTrainingEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe, this);
                if (index == training.getAnzahlAufgaben() - 1) {
                    lTEV.versteckeNaechsteAufgabe();
                }
                break;
            }
            case Programmieren: {
                assert aufgabe instanceof Programmieraufgabe;
                LoesungTrainingProgrammieraufgabeView lTPV = new LoesungTrainingProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                if (index == training.getAnzahlAufgaben() - 1) {
                    lTPV.versteckeNaechsteAufgabe();
                }
                break;
            }
            case MultipleChoice: {

                assert aufgabe instanceof MultipleChoiceAufgabe;
                LoesungTrainingMultipleChoiceAufgabeView lTMCAV = new LoesungTrainingMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                if (index == training.getAnzahlAufgaben() - 1) {
                    lTMCAV.versteckeNaechsteAufgabe();
                }
                break;
            }
            case Design: {
                assert aufgabe instanceof Designaufgabe;
                LoesungTrainingDesignaufgabeView lDV = new LoesungTrainingDesignaufgabeView((Designaufgabe) aufgabe, this);
                if (index == training.getAnzahlAufgaben() - 1) {
                    lDV.versteckeNaechsteAufgabe();
                }
                break;
            }
        }
    }

    public void vorherigeAufgabe() {
        index--;
        Aufgabe aufgabe = training.getAufgaben().get(index);
        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                assert aufgabe instanceof EinfachantwortAufgabe;
                LoesungTrainingEinfachantwortaufgabeView lTEV = new LoesungTrainingEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe, this);
                if (index == 0) {
                    lTEV.versteckeVorherigeAufgabe();
                }
                break;
            }
            case Programmieren: {
                assert aufgabe instanceof Programmieraufgabe;
                LoesungTrainingProgrammieraufgabeView lTPV = new LoesungTrainingProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                if (index == 0) {
                    lTPV.versteckeVorherigeAufgabe();
                }
                break;
            }
            case MultipleChoice: {
                assert aufgabe instanceof MultipleChoiceAufgabe;
                LoesungTrainingMultipleChoiceAufgabeView lTMCAV = new LoesungTrainingMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                if (index == 0) {
                    lTMCAV.versteckeVorherigeAufgabe();
                }
                break;
            }
            case Design: {
                assert aufgabe instanceof Designaufgabe;
                LoesungTrainingDesignaufgabeView lDV = new LoesungTrainingDesignaufgabeView((Designaufgabe) aufgabe, this);
                if (index == 0) {
                    lDV.versteckeVorherigeAufgabe();
                }
                break;
            }
        }
    }

}
