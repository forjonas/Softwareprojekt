package View.Lösungen;

import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneEinfachantwortaufgabeView;
import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneMultipleChoiceAufgabeView;
import entity.*;
import entity.aufgabe.*;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.aufgabensammlung.Training;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;

import java.util.Arrays;
import java.util.List;

public class LoesungsViewsTestklasse {

    public static void main(String[] args) {
        //Testate
        /*
        BewertungProgrammieraufgabeView bewertungProgrammieraufgabeView = new BewertungProgrammieraufgabeView();
        BewertungDesignaufgabeView bewertungDesignaufgabeView = new BewertungDesignaufgabeView();
        BewertungEinfachantwortView bewertungEinfachantwortView = new BewertungEinfachantwortView();
        BewertungMultipleChoiceAufgabeView bewertungMultipleChoiceAufgabeView = new BewertungMultipleChoiceAufgabeView();
        */

        Aufgabe a1 = new EinfachantwortAufgabe(10," javaDesign", "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null, null);
        Aufgabe a2 = new Designaufgabe(15," javaDesign", "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null, null);
        Aufgabe a3 = new Programmieraufgabe(5,null, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null, null);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "javaDesign", "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, null);
        List<Aufgabe> aufgabenListe = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});
        Aufgabensammlung sammlung2 = new Training(aufgabenListe, 60, Kategorie.Java_Programmierung, Schwierigkeitsgrad.Schwer, Aufgabentyp.Einfachantwort);

        //Einzelaufgaben
        //LoesungEinzelneProgrammieraufgabeView loesungEinzelneProgrammieraufgabeView = new LoesungEinzelneProgrammieraufgabeView((Programmieraufgabe) a3);
        //LoesungEinzelneDesignaufgabeView loesungEinzelneDesignaufgabeView = new LoesungEinzelneDesignaufgabeView((Designaufgabe) a2);
        //LoesungEinzelneEinfachantwortaufgabeView loesungEinzelneEinfachantwortaufgabeView = new LoesungEinzelneEinfachantwortaufgabeView((EinfachantwortAufgabe) a1);
        //LoesungEinzelneMultipleChoiceAufgabeView loesungEinzelneMultipleChoiceAufgabeView = new LoesungEinzelneMultipleChoiceAufgabeView((MultipleChoiceAufgabe) a4);


        //Trainings
        //LoesungTrainingProgrammieraufgabeView loesungTrainingProgrammieraufgabeView = new LoesungTrainingProgrammieraufgabeView();
        //LoesungTrainingDesignaufgabeView loesungTrainingDesignaufgabeView = new LoesungTrainingDesignaufgabeView();
        //LoesungTrainingEinfachantwortaufgabeView loesungTrainingEinfachantwortaufgabeView = new LoesungTrainingEinfachantwortaufgabeView((EinfachantwortAufgabe) sammlung2.getAufgaben().get(0));
        //LoesungTrainingMultipleChoiceAufgabeView loesungTrainingMultipleChoiceAufgabeView = new LoesungTrainingMultipleChoiceAufgabeView();

    }

}
