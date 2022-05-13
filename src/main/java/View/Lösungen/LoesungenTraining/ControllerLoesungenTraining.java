package View.Lösungen.LoesungenTraining;

import entity.aufgabe.*;
import entity.aufgabensammlung.Training;

public class ControllerLoesungenTraining {

    private static ControllerLoesungenTraining instance;
    private Training training;
    private int index;

    private ControllerLoesungenTraining() {

    }

    public static ControllerLoesungenTraining getInstance() {
        if (instance == null) {
            instance = new ControllerLoesungenTraining();
        }
        return instance;
    }

    public void setTraining(Training training) {            //MUSS aufgerufen werden
        this.training = training;
        this.index = 0;
        startLoesungTraining();
    }

    public void startLoesungTraining() {
        Aufgabe aufgabe = training.getAufgaben().get(0);
        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                try {
                    LoesungTrainingEinfachantwortaufgabeView lTEV = new LoesungTrainingEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe);
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
                    LoesungTrainingProgrammieraufgabeView lTPV = new LoesungTrainingProgrammieraufgabeView((Programmieraufgabe) aufgabe);
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
                    LoesungTrainingMultipleChoiceAufgabeView lTMCV = new LoesungTrainingMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe);
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
                    LoesungTrainingDesignaufgabeView lDV = new LoesungTrainingDesignaufgabeView((Designaufgabe) aufgabe);
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
        //zurück zum vorherigen Menü
        System.out.println("Hier gehts bald wieder zur Trainingsübersicht!");
    }

    public void naechsteAufgabe(){
        index++;
        Aufgabe aufgabe = training.getAufgaben().get(index);
        switch (aufgabe.getAufgabentyp()) {
            case Einfachantwort: {
                LoesungTrainingEinfachantwortaufgabeView lTEV = new LoesungTrainingEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe);
                if (index == training.getAnzahlAufgaben() - 1) {
                    lTEV.versteckeNaechsteAufgabe();
                }
            }
            case Programmieren: {
                assert aufgabe instanceof Programmieraufgabe;
                LoesungTrainingProgrammieraufgabeView lTPV = new LoesungTrainingProgrammieraufgabeView((Programmieraufgabe) aufgabe);
                if (index == training.getAnzahlAufgaben() - 1) {
                    lTPV.versteckeNaechsteAufgabe();
                }
            }
            case MultipleChoice: {
                assert aufgabe instanceof MultipleChoiceAufgabe;
                LoesungTrainingMultipleChoiceAufgabeView lTMCAV = new LoesungTrainingMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe);
                if (index == training.getAnzahlAufgaben() - 1) {
                    lTMCAV.versteckeNaechsteAufgabe();
                }
            }
            case Design: {
                assert aufgabe instanceof Designaufgabe;
                LoesungTrainingDesignaufgabeView lDV = new LoesungTrainingDesignaufgabeView((Designaufgabe) aufgabe);
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
                LoesungTrainingEinfachantwortaufgabeView lTEV = new LoesungTrainingEinfachantwortaufgabeView((EinfachantwortAufgabe) aufgabe);
                if (index == 0) {
                    lTEV.versteckeVorherigeAufgabe();
                }
            }
            case Programmieren: {
                assert aufgabe instanceof Programmieraufgabe;
                LoesungTrainingProgrammieraufgabeView lTPV = new LoesungTrainingProgrammieraufgabeView((Programmieraufgabe) aufgabe);
                if (index == 0) {
                    lTPV.versteckeVorherigeAufgabe();
                }
            }
            case MultipleChoice: {
                assert aufgabe instanceof MultipleChoiceAufgabe;
                LoesungTrainingMultipleChoiceAufgabeView lTMCAV = new LoesungTrainingMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe);
                if (index == 0) {
                    lTMCAV.versteckeVorherigeAufgabe();
                }
            }
            case Design: {
                assert aufgabe instanceof Designaufgabe;
                LoesungTrainingDesignaufgabeView lDV = new LoesungTrainingDesignaufgabeView((Designaufgabe) aufgabe);
                if (index == 0) {
                    lDV.versteckeVorherigeAufgabe();
                }
            }
        }
    }

}
