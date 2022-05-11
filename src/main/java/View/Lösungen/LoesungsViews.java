package View.Lösungen;

import View.Lösungen.BewertungenTestat.BewertungDesignaufgabeView;
import View.Lösungen.BewertungenTestat.BewertungEinfachantwortView;
import View.Lösungen.BewertungenTestat.BewertungMultipleChoiceAufgabeView;
import View.Lösungen.BewertungenTestat.BewertungProgrammieraufgabeView;
import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneDesignaufgabeView;
import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneEinfachantwortaufgabeView;
import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneMultipleChoiceAufgabeView;
import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneProgrammieraufgabeView;
import View.Lösungen.LoesungenTraining.LoesungTrainingDesignaufgabeView;
import View.Lösungen.LoesungenTraining.LoesungTrainingEinfachantwortaufgabeView;
import View.Lösungen.LoesungenTraining.LoesungTrainingMultipleChoiceAufgabeView;
import View.Lösungen.LoesungenTraining.LoesungTrainingProgrammieraufgabeView;

public class LoesungsViews {

    public static void main(String[] args) {
        //Testate
        /*
        BewertungProgrammieraufgabeView bewertungProgrammieraufgabeView = new BewertungProgrammieraufgabeView();
        BewertungDesignaufgabeView bewertungDesignaufgabeView = new BewertungDesignaufgabeView();
        BewertungEinfachantwortView bewertungEinfachantwortView = new BewertungEinfachantwortView();
        BewertungMultipleChoiceAufgabeView bewertungMultipleChoiceAufgabeView = new BewertungMultipleChoiceAufgabeView();
        */

        //Einzelaufgaben
        LoesungEinzelneProgrammieraufgabeView loesungEinzelneProgrammieraufgabeView = new LoesungEinzelneProgrammieraufgabeView();
        LoesungEinzelneDesignaufgabeView loesungEinzelneDesignaufgabeView = new LoesungEinzelneDesignaufgabeView();
        LoesungEinzelneEinfachantwortaufgabeView loesungEinzelneEinfachantwortaufgabeView = new LoesungEinzelneEinfachantwortaufgabeView();
        LoesungEinzelneMultipleChoiceAufgabeView loesungEinzelneMultipleChoiceAufgabeView = new LoesungEinzelneMultipleChoiceAufgabeView();


        /*
        //Trainings
        LoesungTrainingProgrammieraufgabeView loesungTrainingProgrammieraufgabeView = new LoesungTrainingProgrammieraufgabeView();
        LoesungTrainingDesignaufgabeView loesungTrainingDesignaufgabeView = new LoesungTrainingDesignaufgabeView();
        LoesungTrainingEinfachantwortaufgabeView loesungTrainingEinfachantwortaufgabeView = new LoesungTrainingEinfachantwortaufgabeView();
        LoesungTrainingMultipleChoiceAufgabeView loesungTrainingMultipleChoiceAufgabeView = new LoesungTrainingMultipleChoiceAufgabeView();
        */
    }

}
