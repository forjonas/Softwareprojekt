package persistence;

import entity.*;

import java.util.*;

/**
 * Klasse zum Testen der Persistenz und des OR-Mappers
 *
 * @author Jonas Herbst
 * @version 28.04.22
 */
public class PersistenceTest {
    public static void main(String[] args) {
        System.out.println("Methode zum Testen der Persistenz");
        DatabaseService ds = DatabaseService.getInstance();
        ds.clearDatabase();
        createTestData();
        readTestData();
        System.out.println("Testprogramm terminiert");
    }

    public static void createTestData() {
        DatabaseService ds = DatabaseService.getInstance();
        Benutzer student1 = new Student("AApfel", "aaa", "Adam", "Apfel", 1111);
        Benutzer student2 = new Student("BBirne", "bbb", "Bert", "Birne", 2222);
        Benutzer student3 = new Student("CClown", "ccc", "Chris", "Clown", 3333);
        Benutzer dozent1 = new Dozent("JKüster", "asdf", "Jochen", "Küster");
        Benutzer dozent2 = new Dozent("PHartel", "jklö", "Peter", "Hartel");
        Benutzer admin1 = new Administrator("MoritzMayer", "qwertz", "Moritz", "Mayer");
        Benutzer admin2 = new Administrator("MartinMüller", "asdf", "Martin", "Müller");
        List<Benutzer> benutzerListe = Arrays.asList(new Benutzer[]{student1, student2, student3, dozent1, dozent2, admin1, admin2});
        ds.persistObjects(benutzerListe);
//        Aufgabe a1 = new EinfachantwortAufgabe(10," javaDesign", "umlDesign", "Software Engineering", "Test Test", 12, 3, "Wie heißt der Datentyp für Text?", "Pi mal Daumen", "Peace");
//        Aufgabe a2 = new Designaufgabe(15," javaDesign", "umlDesign", "Datenbanken", "Kein Lösungshinweis", 23, 5, "Erstellen sie ein ER-Diagramm.", "Richtig", "RRRRichtig");
//        Aufgabe a3 = new Programmieraufgabe(5,null, null, "Programmieren", "for-Schleife", 10, 2, "Programmieren Sie eine for-Schleife", "for(int i=0; i<5; i++) {\n\tSystem.out.println(\"Hello World!\");\n}", "Keine Ahnung");
//        Aufgabe a4 = new MultipleChoiceAufgabe(2, "javaDesign", "umlDesign", "Programmieren", "Char ist es nicht.", 5, 1, "Welcher Datentyp ist für Ganzzahlen?", Arrays.asList(new String[]{"char","int","double"}), Arrays.asList(new Boolean[]{false, true, false}), Arrays.asList(new Boolean[]{false, true, false}));
//        List<Aufgabe> aufgabenListe = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});
//        Aufgabe a1 = new EinfachantwortAufgabe();
//        Aufgabe a2 = new Designaufgabe();
//        Aufgabe a3 = new Programmieraufgabe();
//        Aufgabe a4 = new MultipleChoiceAufgabe();
//        List<Aufgabe> aufgabenListe = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});
//        ds.persistObjects(aufgabenListe);
        Aufgabensammlung sammlung1 = new Testat(null, "Sehr Gut", "Hallo1234");
        Aufgabensammlung sammlung2 = new Training(null, 60, "Programmieren", 3, Aufgabentyp.EINFACHANTWORTAUFGABE);
        List<Aufgabensammlung> sammlungsliste = Arrays.asList(new Aufgabensammlung[]{sammlung1, sammlung2});
        ds.persistObjects(sammlungsliste);
    }

    public static void readTestData() {
        DatabaseService ds = DatabaseService.getInstance();
        List<Benutzer> benutzerliste = ds.readBenutzerFromDatabase();
        ds.printResults(benutzerliste);
    }

}