package View.Lösungen.LoesungenTraining;

import entity.aufgabe.*;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.loesung.userloesung.Userloesung;
import persistence.DatabaseService;
import java.util.List;

public class ControllerLoesungenTraining {

    private Training training;
    private int index;
    public Benutzer benutzer;
    private DatabaseService ds = DatabaseService.getInstance();
    private List<Userloesung> userloesungList;

    public ControllerLoesungenTraining(Training training, Benutzer benutzer) {
        this.training = training;
        this.index = 0;
        this.benutzer = benutzer;

        this.userloesungList = ds.readUserloesungVonTraining(training, benutzer);

        startLoesungTraining();
    }

    public void setTraining(Training training) {
        this.training = training;
        this.index = 0;

        this.userloesungList = ds.readUserloesungVonTraining(training, benutzer);

        startLoesungTraining();
    }

    public Userloesung getUserloesung(Aufgabe aufgabe) {
        Userloesung userloesung = null;
        for (Userloesung uDB:userloesungList) {
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
                }
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
                }
            }
            case MultipleChoice: {
                try {
                    assert aufgabe instanceof MultipleChoiceAufgabe;
                    LoesungTrainingMultipleChoiceAufgabeView lTMCV = new LoesungTrainingMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                    lTMCV.versteckeVorherigeAufgabe();
                    if (training.getAnzahlAufgaben() == 1) {
                        lTMCV.versteckeNaechsteAufgabe();
                    }
                } catch (Exception ignored) {
                }
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
                }
            }
        }
    }

    public void beendeLoesungTraining() {
        //zurück zum vorherigen Menü; Benutzer weitergeben!
        System.out.println("Hier gehts bald wieder zur Trainingsübersicht!");
    }

    public void naechsteAufgabe(){
        index++;
        Aufgabe aufgabe = training.getAufgaben().get(index);
        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                LoesungTrainingEinfachantwortaufgabeView lTEV = new LoesungTrainingEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe, this);
                if (index == training.getAnzahlAufgaben() - 1) {
                    lTEV.versteckeNaechsteAufgabe();
                }
            }
            case Programmieren: {
                assert aufgabe instanceof Programmieraufgabe;
                LoesungTrainingProgrammieraufgabeView lTPV = new LoesungTrainingProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                if (index == training.getAnzahlAufgaben() - 1) {
                    lTPV.versteckeNaechsteAufgabe();
                }
            }
            case MultipleChoice: {
                assert aufgabe instanceof MultipleChoiceAufgabe;
                LoesungTrainingMultipleChoiceAufgabeView lTMCAV = new LoesungTrainingMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                if (index == training.getAnzahlAufgaben() - 1) {
                    lTMCAV.versteckeNaechsteAufgabe();
                }
            }
            case Design: {
                assert aufgabe instanceof Designaufgabe;
                LoesungTrainingDesignaufgabeView lDV = new LoesungTrainingDesignaufgabeView((Designaufgabe) aufgabe, this);
                if (index == training.getAnzahlAufgaben() - 1) {
                    lDV.versteckeNaechsteAufgabe();
                }
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
            }
            case Programmieren: {
                assert aufgabe instanceof Programmieraufgabe;
                LoesungTrainingProgrammieraufgabeView lTPV = new LoesungTrainingProgrammieraufgabeView((Programmieraufgabe) aufgabe, this);
                if (index == 0) {
                    lTPV.versteckeVorherigeAufgabe();
                }
            }
            case MultipleChoice: {
                assert aufgabe instanceof MultipleChoiceAufgabe;
                LoesungTrainingMultipleChoiceAufgabeView lTMCAV = new LoesungTrainingMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, this);
                if (index == 0) {
                    lTMCAV.versteckeVorherigeAufgabe();
                }
            }
            case Design: {
                assert aufgabe instanceof Designaufgabe;
                LoesungTrainingDesignaufgabeView lDV = new LoesungTrainingDesignaufgabeView((Designaufgabe) aufgabe, this);
                if (index == 0) {
                    lDV.versteckeVorherigeAufgabe();
                }
            }
        }
    }

}
