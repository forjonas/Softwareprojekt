package View.Lösungen;

public class LoesungsViewsTestklasse {

    public static void main(String[] args) throws Exception {

        //Testatstest LOKAL
        /*
        Dozent dozent = new Dozent("tJoswig", "passwort", "Timo", "Joswig");

        EinfachantwortAufgabe a1 = new EinfachantwortAufgabe(10, "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null, null);
        MusterloesungEinfachantwort mLE = new MusterloesungEinfachantwort(a1, "Hier könnte ihr Lösungshinweis stehen.", "Musterlösungen sind eine Lüge.");
        a1.setMusterloesung(mLE);
        Designaufgabe a2 = new Designaufgabe(15, "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null, null);
        MusterloesungDesignaufgabe mlD = new MusterloesungDesignaufgabe(a2, "Hier könnte ihr Lösungshinweis stehen.", "Lösungshinweise sind eine Lüge.");
        a2.setMusterloesung(mlD);
        Programmieraufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null, null);
        MusterloesungProgrammieraufgabe mLP = new MusterloesungProgrammieraufgabe(a3, "Hier könnte ihr Lösungshinweis stehen.", "public static void main(String[] args){}");
        a3.setMusterloesung(mLP);
        MultipleChoiceAufgabe a4 = new MultipleChoiceAufgabe(2, "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, null, null);
        MusterloesungMultipleChoiceAufgabe mLMCA = new MusterloesungMultipleChoiceAufgabe(a4, "Hier könnte ihr Lösungshinweis stehen.", Arrays.asList(false, true, false, false));
        a4.setMusterloesung(mLMCA);

        List<Aufgabe> aufgabenListe = Arrays.asList(a1, a2, a3, a4);

        Testat sammlung1 = new Testat(aufgabenListe, "Passwort", "Name", dozent);
        TestatBearbeitung bearbeitung1 = new TestatBearbeitung(sammlung1, 20, dozent, dozent);

        UserloesungEinfachantwort uLE = new UserloesungEinfachantwort(a1, false, "Userlösung Einfachantwort", dozent, sammlung1);
        UserloesungDesignaufgabe uLD = new UserloesungDesignaufgabe(a2, false, "Userlösung Designaufgabe", dozent, sammlung1);
        UserloesungProgrammieraufgabe uLP = new UserloesungProgrammieraufgabe(a3, false, "Userlösung Programmieraufgabe", dozent, sammlung1);
        UserloesungMultipleChoiceAufgabe uLMC = new UserloesungMultipleChoiceAufgabe(a4, false, Arrays.asList(true, false, false, false), dozent, sammlung1);

        List<Userloesung> loesungen= new LinkedList<>();
        loesungen.add(uLE);
        loesungen.add(uLD);
        loesungen.add(uLP);
        loesungen.add(uLMC);

        new ControllerBewertungenTestate(bearbeitung1, dozent, new JFrame());
        */

        //TestatstestÜberDB
        /*
        Dozent dozent1 = new Dozent("tJoswig", "64bc9", "Timo", "Joswig");
        Dozent dozent2 = new Dozent("kkubisch", "987caf6", "Kristin", "Kubisch");
        Student student1 = new Student("mmustermann", "123", "Max", "Mustermann", 123678);
        List<String> lma1 = Arrays.asList("int", "String", "char", "boolean");
        List<String> lma2 = Arrays.asList("int", "String", "char", "boolean");
        List<String> lma3 = Arrays.asList("Assoziation", "Aggregation", "Komposition", "Kardinalität");
        List<String> lma4 = Arrays.asList("break", "stop", "return", "finish");
        MultipleChoiceAufgabe ma1 = new MultipleChoiceAufgabe(5, null, Kategorie.Java_Grundlagen, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Wahrheitswerte geeignet", "Datentypabfrage", dozent1, lma1);
        MultipleChoiceAufgabe ma2 = new MultipleChoiceAufgabe(5, null, Kategorie.Java_Grundlagen, 5, Schwierigkeitsgrad.Leicht, "Welche der Datentypen ist kein primitiver Datentyp?", "Primitiver Datentyp", dozent2, lma2);
        MultipleChoiceAufgabe ma3 = new MultipleChoiceAufgabe(5, null, Kategorie.Klassendiagramme, 15, Schwierigkeitsgrad.Mittel, "welches ist keine Art der Beziehung in einem UML-Diagramm", "Beziehungen in UML", dozent1, lma3);
        MultipleChoiceAufgabe ma4 = new MultipleChoiceAufgabe(5, null, Kategorie.Java_Programmierung, 20, Schwierigkeitsgrad.Mittel, "Wie beendet man vorläufig eine Switch-Case-Anweisung?", "Switch-Case-Beenden", dozent2, lma4);
        List<Aufgabe> aufgabenliste3=Arrays.asList(ma1,ma2,ma3,ma4);
        List<Boolean> lmma1= Arrays.asList(false,false,false,true);
        List<Boolean> lmma2= Arrays.asList(false,true,false,false);
        List<Boolean> lmma3= Arrays.asList(false,false,false,true);
        List<Boolean> lmma4= Arrays.asList(true,false,true,false);
        MusterloesungMultipleChoiceAufgabe mma1=new MusterloesungMultipleChoiceAufgabe(ma1,null,lmma1);
        MusterloesungMultipleChoiceAufgabe mma2=new MusterloesungMultipleChoiceAufgabe(ma2,"Nicht primitive Datentypen sind bspwl. Klassen",lmma2);
        MusterloesungMultipleChoiceAufgabe mma3=new MusterloesungMultipleChoiceAufgabe(ma3,"Hier steht ein Lösungshinweis",lmma3);
        MusterloesungMultipleChoiceAufgabe mma4=new MusterloesungMultipleChoiceAufgabe(ma4,"Die Schwitch-Case-Anweisung lässt sich auch mit dem beenden der Methode beenden.",lmma4);
        ma1.setMusterloesung(mma1);
        ma2.setMusterloesung(mma2);
        ma3.setMusterloesung(mma3);
        ma4.setMusterloesung(mma4);
        */

        /*DatabaseService ds = DatabaseService.getInstance();
        Dozent dozent1 = new Dozent("admin", "64bc9", "Peter", "Joswig");
        TestatBearbeitung bearbeitung1 = (TestatBearbeitung) ds.readTestatBearbeitungenFromDatabase().get(0);
        new ControllerBewertungenTestate(bearbeitung1, dozent1, new JFrame());*/


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






        //Trainingstest
        //Training sammlung2 = new Training(aufgabenListe, 60, Kategorie.Java_Programmierung, Schwierigkeitsgrad.Schwer, Collections.singletonList(Aufgabentyp.Einfachantwort));
        //ControllerLoesungenTraining contTrainingTest = new ControllerLoesungenTraining(sammlung2, dozent);

        //Aus der Datenbank laden
        //DatabaseService ds = DatabaseService.getInstance();
        //List<Training> trainingDB = ds.readTrainingsFromDatabase();
        //Training training = trainingDB.get(0);
        //ControllerLoesungenTraining contTrainingDB = new ControllerLoesungenTraining(training, dozent);

        /*
        List<TestatBearbeitung> testatBearbeitungDB = ds.readTestatBearbeitungenFromDatabase();
        TestatBearbeitung testatBearbeitung = testatBearbeitungDB.get(0);
        Student student = (Student) ds.readStudentnachBenutzernamen("CClown");
        //jetzt nur noch sicherstellen dass CClown ein Testat bearbeitet hat
        ControllerBewertungenTestate contTestatDB = new ControllerBewertungenTestate(testatBearbeitung, student);
         */



    }

}
