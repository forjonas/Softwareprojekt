package persistence;

import entity.aufgabe.*;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.benutzer.Student;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.Loesung;
import entity.loesung.musterloesung.*;
import entity.loesung.userloesung.*;

import java.util.Arrays;
import java.util.List;

/**
 * Klasse zum Testen der Persistenz und des OR-Mappers
 *
 * @author Jonas Herbst
 * @version 13.05.22
 */
public class PersistenceTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Methode zum Testen der Persistenz");
        DatabaseService ds = DatabaseService.getInstance();

        createTestData();
        //ds.clearDatabase();
        //deleteTestData();
        //testRelations();
        //readTestData(65);

        System.out.println("Testprogramm terminiert");
    }

    public static void createTestData() throws Exception {
        DatabaseService ds = DatabaseService.getInstance();
        Student student1 = new Student("AApfel", "aaa", "Adam", "Apfel", 1111);
        Student student2 = new Student("BBirne", "bbb", "Bert", "Birne", 2222);
        Student student3 = new Student("CClown", "ccc", "Chris", "Clown", 3333);
        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Dozent dozent2 = new Dozent("PPanzer", "jklö", "Paul", "Panzer");
        List<Benutzer> benutzerListe = Arrays.asList(new Benutzer[]{student1, student2, student3, dozent1, dozent2});

        Aufgabe a1 = new EinfachantwortAufgabe(10, " javaDesign", "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", dozent2);
        Aufgabe a2 = new Designaufgabe(15, " javaDesign", "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", dozent2);
        Aufgabe a3 = new Programmieraufgabe(5, null, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", dozent2);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "javaDesign", "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", dozent2, Arrays.asList(new String[]{"char", "int", "double"}));
        List<Aufgabe> aufgabenListe = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});
        dozent2.addErstellteAufgabe(a1);
        dozent2.addErstellteAufgabe(a2);
        dozent2.addErstellteAufgabe(a3);
        dozent2.addErstellteAufgabe(a4);

        Training training1 = new Training(aufgabenListe, 60, Kategorie.Java_Programmierung, Schwierigkeitsgrad.Leicht, Arrays.asList(new Aufgabentyp[] {Aufgabentyp.Einfachantwort, Aufgabentyp.Programmieren}), student1);
        student1.addBearbeitetesTraining(training1);
        for(Aufgabe a : aufgabenListe) {
            a.addVerwendung(training1);
        }

        Testat testat1 = new Testat(aufgabenListe, "Hallo1234", "Sommertestat", dozent1);
        dozent1.addErstelltesTestat(testat1);
        for(Aufgabe a : aufgabenListe) {
            a.addVerwendung(testat1);
        }

        TestatBearbeitung t1 = new TestatBearbeitung(testat1);
        testat1.addBearbeitung(t1);
        TestatBearbeitung t2 = new TestatBearbeitung(testat1);
        testat1.addBearbeitung(t2);

        student1.addBearbeitetesTestat(t1);
        t1.setTestatBearbeiter(student1);
        student2.addBearbeitetesTestat(t2);
        t2.setTestatBearbeiter(student2);
        dozent1.addBewertetesTestat(t2);
        t2.setTestatBewerter(dozent1);


        ds.persistObjects(benutzerListe);
        //Nicht mehr erforderlich, da Objekte über Beziehungen automatisch mit persistiert werden
/*        ds.persistObjects(aufgabenListe);
        ds.persistObjects(sammlungsliste);
        ds.persistObjects(Arrays.asList(new TestatBearbeitung[]{t1, t2}));*/

        Musterloesung ml1 = new MusterloesungEinfachantwort((EinfachantwortAufgabe) a1, "Lösungshinweis", "Lösung");
        //Musterloesung ml2 = new MusterloesungDesignaufgabe((Designaufgabe) a2, "Lösungshinweis", "Lösung");
        Musterloesung ml3 = new MusterloesungProgrammieraufgabe((Programmieraufgabe) a3, "Lösungshinweis", "Lösung");
        Musterloesung ml4 = new MusterloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) a4, "Lösungshinweis", Arrays.asList(new Boolean[]{true, false, false}));
        a1.setMusterloesung(ml1);
        //a2.setMusterloesung(ml2);
        a3.setMusterloesung(ml3);
        a4.setMusterloesung(ml4);
        Userloesung ul1 = new UserloesungEinfachantwort((EinfachantwortAufgabe) a1, false, "Lösung", student1, testat1);
        Userloesung ul2 = new UserloesungDesignaufgabe((Designaufgabe) a2, false, "Lösung", student1, testat1);
        Userloesung ul3 = new UserloesungProgrammieraufgabe((Programmieraufgabe) a3, false, "Lösung", student1, testat1);
        Userloesung ul4 = new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) a4, true, Arrays.asList(new Boolean[]{true, false, false}), student1, testat1);
        testat1.addUserloesung(ul1);
        testat1.addUserloesung(ul2);
        testat1.addUserloesung(ul3);
        testat1.addUserloesung(ul4);
        student1.addErstellteLoesung(ul1);
        student1.addErstellteLoesung(ul2);
        student1.addErstellteLoesung(ul3);
        student1.addErstellteLoesung(ul4);
        a1.addUserloesung(ul1);
        a2.addUserloesung(ul2);
        a3.addUserloesung(ul3);
        a4.addUserloesung(ul4);

        //List<Loesung> loesungsliste = Arrays.asList(new Loesung[]{ml1, ml2, ml3, ml4, ul1, ul2, ul3, ul4});
       // ds.persistObjects(loesungsliste);

    }

    public static void deleteTestData() throws Exception {
        DatabaseService ds = DatabaseService.getInstance();
        List<Dozent> dozenten = ds.readDozentenFromDatabase();
        for(Dozent d :  dozenten) {
            ds.saveDeleteDozentFromDatabase(d);
        }
        List<Student> studenten = ds.readStudentenFromDatabase();
        for(Student s :  studenten) {
            ds.saveDeleteStudentFromDatabase(s);
        }
        List<Userloesung> userloesungen = ds.readUserloesungenFromDatabse();
        for (Userloesung ul : userloesungen) {
            ds.saveDeleteUserloesungFromDatabase(ul);
        }
        List<Musterloesung> musterloesungen = ds.readMusterloesungenFromDatabse();
        for (Musterloesung ml : musterloesungen) {
            ds.saveDeleteMusterloesungFromDatabase(ml);
        }
        List<TestatBearbeitung> testatBearbeitungen = ds.readTestatBearbeitungenFromDatabase();
        for (TestatBearbeitung tb : testatBearbeitungen) {
            ds.saveDeleteTestatBearbeitungFromDatabase(tb);
        }
        List<Testat> testate = ds.readTestateFromDatabase();
        for (Testat testat : testate) {
            ds.saveDeleteTestatFromDatabase(testat);
        }
        List<Training> trainings = ds.readTrainingsFromDatabase();
        for (Training training : trainings) {
            ds.saveDeleteTrainingFromDatabase(training);
        }
        List<Aufgabe> aufgaben = ds.readAufgabenFromDatabase();
        for (Aufgabe aufgabe : aufgaben) {
            ds.saveDeleteAufgabeFromDatabase(aufgabe);
        }

    }

    public static void testRelations() throws Exception {
        DatabaseService ds = DatabaseService.getInstance();
        List<Training> trainings = ds.readTrainingsFromDatabase();
        for (Training t : trainings) {
            System.out.println("Training, erstellt von: " + t.getTrainingsErsteller().getNachname());
            List<Aufgabe> aufgaben = t.getAufgaben();
            for (Aufgabe a : aufgaben) {
                System.out.println("Aufgabe: " + a.getName());
            }
        }
    }

    public static void testEnums() {
        //Fancy Zeug mit Enums: wenn auf den Bezeichner der Enums zugegriffen wird nur zB getAufgabentyp nutzen,
        //wenn man den Wert des Enums haben will noch .getCode() dahinter packen
        DatabaseService ds = DatabaseService.getInstance();
        List<Aufgabe> aufgaben = ds.readAufgabenFromDatabase();
        for (Aufgabe a : aufgaben) {
            System.out.println(a.getAufgabentyp().getCode());
            System.out.println(a.getSchwierigkeitsgrad().getCode());
            System.out.println(a.getKategorie().getCode());
        }
    }

    public static void readTestData() {
        DatabaseService ds = DatabaseService.getInstance();
        List<Benutzer> liste = ds.readBenutzerFromDatabase();
        ds.printResults(liste);
    }

}
