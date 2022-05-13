package View.Lösungen;

import View.Lösungen.BewertungenTestat.ControllerBewertungenTestate;
import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneDesignaufgabeView;
import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneEinfachantwortaufgabeView;
import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneMultipleChoiceAufgabeView;
import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneProgrammieraufgabeView;
import View.Lösungen.LoesungenTraining.ControllerLoesungenTraining;
import entity.*;
import entity.aufgabe.*;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.Training;
import entity.benutzer.Dozent;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.musterloesung.MusterloesungEinfachantwort;
import entity.loesung.musterloesung.MusterloesungMultipleChoiceAufgabe;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;
import persistence.DatabaseService;

import java.util.Arrays;
import java.util.List;

public class LoesungsViewsTestklasse {

    public static void main(String[] args) throws Exception {

        Aufgabe a1 = new EinfachantwortAufgabe(10, "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null, null);
        MusterloesungEinfachantwort mLE = new MusterloesungEinfachantwort((EinfachantwortAufgabe) a1, "Hier könnte ihr Lösungshinweis stehen.", "Musterlösungen sind eine Lüge.");
        a1.setMusterloesung(mLE);
        Aufgabe a2 = new Designaufgabe(15, "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null, null);
        //MusterloesungDesignaufgabe mlD = new MusterloesungDesignaufgabe((Designaufgabe) a2, "Hier könnte ihr Lösungshinweis stehen.", "Lösungshinweise sind eine Lüge.");
        //a2.setMusterloesung(mlD);
        Aufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null, null);
        MusterloesungProgrammieraufgabe mLP = new MusterloesungProgrammieraufgabe((Programmieraufgabe) a3, "Hier könnte ihr Lösungshinweis stehen.", "public static void main(String[] args){}");
        a3.setMusterloesung(mLP);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, null, null);
        MusterloesungMultipleChoiceAufgabe mLMCA = new MusterloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) a4, "Hier könnte ihr Lösungshinweis stehen.", Arrays.asList(false, true, false, false));
        a4.setMusterloesung(mLMCA);

        //Einzelaufgaben
        //LoesungEinzelneProgrammieraufgabeView loesungEinzelneProgrammieraufgabeView = new LoesungEinzelneProgrammieraufgabeView((Programmieraufgabe) a3);
        //LoesungEinzelneDesignaufgabeView loesungEinzelneDesignaufgabeView = new LoesungEinzelneDesignaufgabeView((Designaufgabe) a2);
        //LoesungEinzelneEinfachantwortaufgabeView loesungEinzelneEinfachantwortaufgabeView = new LoesungEinzelneEinfachantwortaufgabeView((EinfachantwortAufgabe) a1);
        //LoesungEinzelneMultipleChoiceAufgabeView loesungEinzelneMultipleChoiceAufgabeView = new LoesungEinzelneMultipleChoiceAufgabeView((MultipleChoiceAufgabe) a4);

        //Testate
        /*
        BewertungProgrammieraufgabeView bewertungProgrammieraufgabeView = new BewertungProgrammieraufgabeView((Programmieraufgabe) a3);
        BewertungDesignaufgabeView bewertungDesignaufgabeView = new BewertungDesignaufgabeView((Designaufgabe) a2);
        BewertungEinfachantwortView bewertungEinfachantwortView = new BewertungEinfachantwortView((EinfachantwortAufgabe) a1);
        BewertungMultipleChoiceAufgabeView bewertungMultipleChoiceAufgabeView = new BewertungMultipleChoiceAufgabeView((MultipleChoiceAufgabe) a4);
        */

        //Trainings
        //LoesungTrainingProgrammieraufgabeView loesungTrainingProgrammieraufgabeView = new LoesungTrainingProgrammieraufgabeView();
        //LoesungTrainingDesignaufgabeView loesungTrainingDesignaufgabeView = new LoesungTrainingDesignaufgabeView();
        //LoesungTrainingEinfachantwortaufgabeView loesungTrainingEinfachantwortaufgabeView = new LoesungTrainingEinfachantwortaufgabeView((EinfachantwortAufgabe) sammlung2.getAufgaben().get(0));
        //LoesungTrainingMultipleChoiceAufgabeView loesungTrainingMultipleChoiceAufgabeView = new LoesungTrainingMultipleChoiceAufgabeView();

        List<Aufgabe> aufgabenListe = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});

        //Testatstest
        //Dozent dozent = new Dozent("benutzername", "Passwort", "Vorname", "Nachname");
        //Testat sammlung1 = new Testat(aufgabenListe, "Passwort", "Name", dozent);
        //ControllerBewertungenTestate.getInstance().setTestat(sammlung1);

        //Trainingstest
        //Training sammlung2 = new Training(aufgabenListe, 60, Kategorie.Java_Programmierung, Schwierigkeitsgrad.Schwer, Aufgabentyp.Einfachantwort);
        //ControllerLoesungenTraining.getInstance().setTraining(sammlung2);

        //Aus der Datenbank laden
        //DatabaseService ds = DatabaseService.getInstance();
        //List<Training> trainingDB = ds.readTrainingsFromDatabase();
        //ControllerLoesungenTraining.getInstance().setTraining(trainingDB.get(0));

        //List<Testat> testatDB = ds.readTestateFromDatabase();
        //ControllerBewertungenTestate.getInstance().setTestat(testatDB.get(0));

    }

}
