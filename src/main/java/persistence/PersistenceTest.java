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
        Benutzer dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Benutzer dozent2 = new Dozent("PPanzer", "jklö", "Paul", "Panzer");
        Benutzer admin1 = new Administrator("MoritzMayer", "qwertz", "Moritz", "Mayer");
        Benutzer admin2 = new Administrator("MartinMüller", "asdf", "Martin", "Müller");
        List<Benutzer> benutzerListe = Arrays.asList(new Benutzer[]{student1, student2, student3, dozent1, dozent2, admin1, admin2});
        ds.persistObjects(benutzerListe);
        Aufgabe a1 = new EinfachantwortAufgabe(10,null, null, Kategorie.Software_Engineering, "Test Test", 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", "Pi mal Daumen", "Peace");
        //Aufgabe a2 = new Designaufgabe(15," javaDesign", "umlDesign", Kategorie.Datenbanken, "Kein Lösungshinweis", 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", "Richtig", "RRRRichtig");
        Aufgabe a3 = new Programmieraufgabe(5,null, null, Kategorie.Java_Programmierung, "for-Schleife", 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null, "Keine Ahnung");
        Aufgabe a4 = new MultipleChoiceAufgabe(2, null, null, Kategorie.Java_Programmierung, "Char ist es nicht.", 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", Arrays.asList(new String[]{"char","int","double"}), Arrays.asList(new Boolean[]{false, true, false}), Arrays.asList(new Boolean[]{false, true, false}));
        List<Aufgabe> aufgabenListe = Arrays.asList(new Aufgabe[]{a1, a3, a4});
        ds.persistObjects(aufgabenListe);
        Aufgabensammlung sammlung1 = new Testat(aufgabenListe, "Sehr Gut", "Hallo1234", "Sommertestat");
        Aufgabensammlung sammlung2 = new Training(aufgabenListe, 60, "Programmieren", 3, Aufgabentyp.Einfachantwort);
        List<Aufgabensammlung> sammlungsliste = Arrays.asList(new Aufgabensammlung[]{sammlung1, sammlung2});
        ds.persistObjects(sammlungsliste);
        Aufgabe a5 = new MultipleChoiceAufgabe();
        a5.setSchwierigkeitsgrad(Schwierigkeitsgrad.Schwer);
        ds.persistObject(a5);
    }

    public static void readTestData() {
        DatabaseService ds = DatabaseService.getInstance();
        List<Benutzer> liste = ds.readBenutzerFromDatabase();
        ds.printResults(liste);
    }

}