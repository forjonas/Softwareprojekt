package controller;

import View.EinsehenTrainingKatalogView;
import View.Lösungen.LoesungenTraining.LoesungTrainingDesignaufgabeView;
import View.Lösungen.LoesungenTraining.LoesungTrainingEinfachantwortaufgabeView;
import View.Lösungen.LoesungenTraining.LoesungTrainingMultipleChoiceAufgabeView;
import View.Lösungen.LoesungenTraining.LoesungTrainingProgrammieraufgabeView;
import entity.aufgabe.*;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.loesung.userloesung.Userloesung;
import persistence.DatabaseService;

import javax.swing.*;
import java.util.List;

public class controllerLoesungenTraining {

    private final Training training;
    private final JFrame frame;
    private int index;
    public Benutzer benutzer;
    private DatabaseService ds = DatabaseService.getInstance();
    private List<Userloesung> userloesungList;

    public controllerLoesungenTraining(Training training, Benutzer benutzer, JFrame frame) {
        this.frame = frame;
        this.training = training;
        this.index = 0;
        this.benutzer = benutzer;

        this.userloesungList = ds.readUserloesungVonTraining(training, training.getTrainingsErsteller());

        startLoesungTraining();
    }

    public Userloesung getUserloesung(Aufgabe aufgabe) {
        Userloesung userloesung = null;
        for (Userloesung userloesungDB : userloesungList) {
            if (userloesungDB.getAufgabe().equals(aufgabe)) {
                userloesung = userloesungDB;
            }
        }
        return userloesung;
    }

    public void startLoesungTraining() {
        Aufgabe aufgabe = training.getAufgaben().get(0);
        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {

                LoesungTrainingEinfachantwortaufgabeView loesungTrainingEinfachantwortaufgabeView = new LoesungTrainingEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe, this);
                loesungTrainingEinfachantwortaufgabeView.versteckeVorherigeAufgabe();
                if (training.getAnzahlAufgaben() == 1) {
                    loesungTrainingEinfachantwortaufgabeView.versteckeNaechsteAufgabe();
                }
                break;
            }
            case Programmieren: {

                assert aufgabe instanceof Programmieraufgabe;
                LoesungTrainingProgrammieraufgabeView loesungTrainingProgrammieraufgabeView = new LoesungTrainingProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                loesungTrainingProgrammieraufgabeView.versteckeVorherigeAufgabe();
                if (training.getAnzahlAufgaben() == 1) {
                    loesungTrainingProgrammieraufgabeView.versteckeNaechsteAufgabe();
                }
                break;
            }
            case MultipleChoice: {
                assert aufgabe instanceof MultipleChoiceAufgabe;
                LoesungTrainingMultipleChoiceAufgabeView loesungTrainingMultipleChoiceAufgabeView = new LoesungTrainingMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                loesungTrainingMultipleChoiceAufgabeView.versteckeVorherigeAufgabe();
                if (training.getAnzahlAufgaben() == 1) {
                    loesungTrainingMultipleChoiceAufgabeView.versteckeNaechsteAufgabe();
                }
                break;
            }
            case Design: {

                assert aufgabe instanceof Designaufgabe;
                LoesungTrainingDesignaufgabeView loesungTrainingDesignaufgabeView = new LoesungTrainingDesignaufgabeView((Designaufgabe) aufgabe, this);
                loesungTrainingDesignaufgabeView.versteckeVorherigeAufgabe();
                if (training.getAnzahlAufgaben() == 1) {
                    loesungTrainingDesignaufgabeView.versteckeNaechsteAufgabe();
                }
                break;
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
                LoesungTrainingEinfachantwortaufgabeView loesungTrainingEinfachantwortaufgabeView = new LoesungTrainingEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe, this);
                if (index == training.getAnzahlAufgaben() - 1) {
                    loesungTrainingEinfachantwortaufgabeView.versteckeNaechsteAufgabe();
                }
                break;
            }
            case Programmieren: {
                assert aufgabe instanceof Programmieraufgabe;
                LoesungTrainingProgrammieraufgabeView loesungTrainingProgrammieraufgabeView = new LoesungTrainingProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                if (index == training.getAnzahlAufgaben() - 1) {
                    loesungTrainingProgrammieraufgabeView.versteckeNaechsteAufgabe();
                }
                break;
            }
            case MultipleChoice: {

                assert aufgabe instanceof MultipleChoiceAufgabe;
                LoesungTrainingMultipleChoiceAufgabeView loesungTrainingMultipleChoiceAufgabeView = new LoesungTrainingMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                if (index == training.getAnzahlAufgaben() - 1) {
                    loesungTrainingMultipleChoiceAufgabeView.versteckeNaechsteAufgabe();
                }
                break;
            }
            case Design: {
                assert aufgabe instanceof Designaufgabe;
                LoesungTrainingDesignaufgabeView loesungTrainingDesignaufgabeView = new LoesungTrainingDesignaufgabeView((Designaufgabe) aufgabe, this);
                if (index == training.getAnzahlAufgaben() - 1) {
                    loesungTrainingDesignaufgabeView.versteckeNaechsteAufgabe();
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
                LoesungTrainingEinfachantwortaufgabeView loesungTrainingEinfachantwortaufgabeView = new LoesungTrainingEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe, this);
                if (index == 0) {
                    loesungTrainingEinfachantwortaufgabeView.versteckeVorherigeAufgabe();
                }
                break;
            }
            case Programmieren: {
                assert aufgabe instanceof Programmieraufgabe;
                LoesungTrainingProgrammieraufgabeView loesungTrainingProgrammieraufgabeView = new LoesungTrainingProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                if (index == 0) {
                    loesungTrainingProgrammieraufgabeView.versteckeVorherigeAufgabe();
                }
                break;
            }
            case MultipleChoice: {
                assert aufgabe instanceof MultipleChoiceAufgabe;
                LoesungTrainingMultipleChoiceAufgabeView loesungTrainingMultipleChoiceAufgabeView = new LoesungTrainingMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                if (index == 0) {
                    loesungTrainingMultipleChoiceAufgabeView.versteckeVorherigeAufgabe();
                }
                break;
            }
            case Design: {
                assert aufgabe instanceof Designaufgabe;
                LoesungTrainingDesignaufgabeView loesungTrainingDesignaufgabeView = new LoesungTrainingDesignaufgabeView((Designaufgabe) aufgabe, this);
                if (index == 0) {
                    loesungTrainingDesignaufgabeView.versteckeVorherigeAufgabe();
                }
                break;
            }
        }
    }

}
