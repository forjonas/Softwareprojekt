package persistence;


import entity.enums.Aufgabentyp;
import entity.enums.AufgabentypConverter;
import view.LoginView;
import entity.aufgabe.*;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.aufgabensammlung.Training;
import entity.benutzer.Dozent;
import entity.benutzer.Student;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.*;
import entity.loesung.userloesung.*;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Testdatenbestand für die Finalevorstellung des Programmes.
 *
 * @author Martin Bergen
 * @version 04.06.2022
 */
public class CreateBetaData {


    /**
     * Beta-Data Attribute
     * <p>
     * Dozenten, Studenten, Testate, Trainings, Musterlösungen, Aufgaben, ...
     */
    private List<Student> studentenliste;
    private Student student1;
    private Student student2;
    private Student student3;
    private Student student4;

    private List<Dozent> dozentenliste;
    private Dozent dozent1;
    private Dozent dozent2;
    private Dozent admin;

    private List<Aufgabe> aufgabenliste;
    private Designaufgabe da1, da2, da3, da4, da5, da6;
    private Programmieraufgabe pa1, pa2, pa3, pa4, pa5, pa6, pa7, pa8, pa9, pa10;
    private MultipleChoiceAufgabe ma1, ma2, ma3, ma4, ma5, ma6, ma7, ma8, ma9;
    private EinfachantwortAufgabe ea1, ea2, ea3, ea4, ea5, ea6, ea7, ea8, ea9, ea10, ea11, ea12, ea13, ea14, ea15;

    private List<Musterloesung> musterloesungsliste;
    private MusterloesungDesignaufgabe mda1, mda2, mda3, mda4, mda5, mda6;
    private MusterloesungProgrammieraufgabe mpa1, mpa2, mpa3, mpa4, mpa5, mpa6, mpa7, mpa8, mpa9, mpa10;
    private MusterloesungMultipleChoiceAufgabe mma1, mma2, mma3, mma4, mma5, mma6, mma7, mma8, mma9;
    private MusterloesungEinfachantwort mea1, mea2, mea3, mea4, mea5, mea6, mea7, mea8, mea9, mea10, mea11, mea12, mea13, mea14, mea15;

    private List<Userloesung> userloesungsliste;
    private Userloesung ul1, ul2, ul3, ul4, ul5, ul6, ul7, ul8, ul9, ul10, ul11, ul12, ul13, ul14, ul15, ul16, ul17, ul18, ul19;

    private List<TestatBearbeitung> testatBearbeitungsliste;
    private TestatBearbeitung testatBearbeitung, testatBearbeitung2, testatBearbeitung3, testatBearbeitung4, testatBearbeitung5, testatBearbeitung6, testatBearbeitung7;

    private List<Aufgabensammlung> aufgabensammlungsliste;
    private Testat testat1, testat2, testat3, testat4;
    private Training training1, training2, training3;

    public static void main(String[] args) {
        new CreateBetaData();
    }

    public CreateBetaData() {
        try {
            createBetaUser();
            createBetaAufgaben();
            createBetaMusterloesung();
            createBetaAufgabensammlungen();
            createBetaUserLösungen();

        } catch (Exception e) {
        }
        DatabaseService ds = DatabaseService.getInstance();

        ds.persistObject(dozent1);
        ds.persistObject(dozent2);
        ds.persistObject(admin);
        ds.persistObject(student1);
        ds.persistObject(student2);
        ds.persistObject(student3);
        ds.persistObject(student4);


        new LoginView();
    }

    public void createBetaUser() {
        student1 = new Student("mmustermann", "000", "Max", "Mustermann", 123678);
        student2 = new Student("vvogel", "000", "Verena", "Vogel", 234789);
        student3 = new Student("eengel", "000", "Erik", "Engel", 345890);
        student4 = new Student("eEngel", "000", "Erina", "Engel", 345786);
        studentenliste = Arrays.asList(new Student[]{student1, student2, student3, student4});
        studentenliste = new LinkedList<>(studentenliste);
        dozent1 = new Dozent("tJoswig", "000", "Timo", "Joswig");
        dozent2 = new Dozent("kkubisch", "000", "Kristin", "Kubisch");
        admin = new Dozent("admin", "000", "Admin", "");
        dozentenliste = Arrays.asList(new Dozent[]{dozent1, dozent2, admin});
        dozentenliste = new LinkedList<>(dozentenliste);
    }

    public void createBetaAufgaben() {
        pa1 = new Programmieraufgabe(12, getAufgabenImage("Programmieren\\ProgrammierenAufgabenstellungsbild-Mittel"), Kategorie.Klassendiagramme, 20, Schwierigkeitsgrad.Mittel, "Programmieren Sie die Klassen Reservierung und Reservierungslisten", "ProgrammierungReservierung", dozent1);
        pa2 = new Programmieraufgabe(15, null, Kategorie.Java_Grundlagen, 10, Schwierigkeitsgrad.Leicht, "Geben Sie stiliserte 'Weihnachtsbäume' aus. Die Höhe soll vom Benutzer anpassbar sein.", "Weihnachtsbäume", dozent2);
        pa3 = new Programmieraufgabe(12, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Leicht, "Geben Sie Ihren Namen, Vornamen und E-Mail-Adresse in drei einzelnen Zeilen aus.", "Texte ausgeben", dozent2);
        pa4 = new Programmieraufgabe(25, null, Kategorie.Java_Grundlagen, 30, Schwierigkeitsgrad.Schwer, "Erzeugen Sie Zahlworte für die Zahlen 1...999", "Zahlenworte", dozent1);
        pa5 = new Programmieraufgabe(20, null, Kategorie.Java_Grundlagen, 25, Schwierigkeitsgrad.Mittel, "Lesen Sie zwei Zahlen ein und berechnen Sie die Summe. Die Ausgabe soll folgende Form haben: Die Summe von 123 und 234 ist 357.", "Summenberechnung", dozent1);
        pa6 = new Programmieraufgabe(20, null, Kategorie.Java_Programmierung, 20, Schwierigkeitsgrad.Mittel, "Schreiben Sie eine Methode, die überprüft, ob das eingegebene Jahr ein Schaltjahr ist.", "Schaltjahr", dozent1);
        pa7 = new Programmieraufgabe(25, null, Kategorie.Java_Programmierung, 30, Schwierigkeitsgrad.Schwer, "Schreiben Sie eine Methode, die Literangaben als Kommazahl einließt und den berechneten WEert als String ausgibt.", "Literumrechner", dozent2);
        pa8 = new Programmieraufgabe(12, getAufgabenImage("Programmieren\\SQL-Aufgabenbild"), Kategorie.Datenbanken, 15, Schwierigkeitsgrad.Leicht, "Lassen Sie sich alle in Tabelle Country gespeicherten Datensätze ausgeben.", "Programmieren-SQL-Leicht", dozent1);
        pa9 = new Programmieraufgabe(15, getAufgabenImage("Programmieren\\SQL-Aufgabenbild"), Kategorie.Datenbanken, 20, Schwierigkeitsgrad.Mittel, "Lassen Sie sich die Anzahl der in Tabelle Country gespeicherten Länder ausgeben", "Programmieren-SQL-Mittel", dozent1);
        pa10 = new Programmieraufgabe(20, getAufgabenImage("Programmieren\\SQL-Aufgabenbild"), Kategorie.Datenbanken, 25, Schwierigkeitsgrad.Schwer, "Lassen Sie sich die größte Einwohnerzahl sowie die größte Flache der in Tabelle Country gespeicherten Länder ausgeben.", "Programmieren-SQL-Schwer", dozent1);

        da1 = new Designaufgabe(12, getAufgabenImage("Design\\Klassendiagramme\\KlassendiagrammAufgabenstellungsbild-Leicht"), Kategorie.Klassendiagramme, 15, Schwierigkeitsgrad.Leicht, "Modellieren Sie die folgende Situation als Klassendiagramm.", "Klassendiagramm modellieren.", dozent1);
        da2 = new Designaufgabe(15, getAufgabenImage("Design\\Klassendiagramme\\Klassendiagramm-Mittel-Aufgabenstellung"), Kategorie.Klassendiagramme, 20, Schwierigkeitsgrad.Mittel, "Modellieren Sie diesen Sachverhalt mit einem UML-Klassendiagramm", "UML-Modellierung1", dozent2);
        da3 = new Designaufgabe(19, getAufgabenImage("Design\\Klassendiagramme\\Klassendiagramme-Aufgabenbild-Schwer"), Kategorie.Klassendiagramme, 22, Schwierigkeitsgrad.Schwer, "Modellieren Sie das Klassendiagramm zu folgendem Programmcode.", "UML_Modellierung2", dozent1);
        da4 = new Designaufgabe(12, getAufgabenImage("Design\\Software Engineering\\SOftwareEngineeringAufgabenstellungsbild-Leicht"), Kategorie.Software_Engineering, 20, Schwierigkeitsgrad.Leicht, "Gegeben ist folgender Programmcode, designen Sie den Kontrollflussgraphen", "Kontrollflussgraph Leicht", dozent2);
        da5 = new Designaufgabe(15, getAufgabenImage("Design\\Software Engineering\\SOftwareEngineeringAufgabenstellungsbild-Mittel"), Kategorie.Software_Engineering, 25, Schwierigkeitsgrad.Mittel, "Gegeben ist folgender Programmcode, erstellen Sie einen passenden Kontrollflussgraphen dazu.", "Kontrollflussgraph Mittel", dozent1);
        da6 = new Designaufgabe(19, getAufgabenImage("Design\\Software Engineering\\SoftwareEngineeringAufgabenstellungsbild-Schwer"), Kategorie.Software_Engineering, 30, Schwierigkeitsgrad.Schwer, "Erstellen Sie zum folgenden Programmcode den Kotrollflussgraphen.", "Kontrollflussgraph Schwer", dozent1);

        ma1 = new MultipleChoiceAufgabe(12, null, Kategorie.Java_Grundlagen, 15, Schwierigkeitsgrad.Leicht, "Angenommen, x ist eine int-Variable. Wählen Sie jene Ausdrücke aus, die in Java zu ++x+x hinsichtlich Seiteneffekten und Ergebnissen äquivalent sind.", "Int-Multiple-Choice", dozent1, Arrays.asList(new String[]{"x+x+1", "2*(++x)", "2*(x++)+1", "(x+1)*2"}));
        ma2 = new MultipleChoiceAufgabe(12, null, Kategorie.Java_Programmierung, 15, Schwierigkeitsgrad.Leicht, "Die Auswertung von 0.0 +(char)('a' +0.6) liefert in Java folgendes Ergebnis(wobei 97 der ASCII-Wert von 'a' ist):", "ASCII-Multiple-Choice", dozent2, Arrays.asList(new String[]{"'b'", "98", "97", "keines davon"}));
        ma3 = new MultipleChoiceAufgabe(15, getAufgabenImage("MultipleChoice\\Grundlagen der Programmierung\\Aufgabenstellungsbild-Mittel"), Kategorie.Java_Programmierung, 20, Schwierigkeitsgrad.Mittel, "Gegeben ist folgender Programmcode, welche Ausgabe erhält man bei dem Aufruf forEachLoop(8)?", "ForEachLoopMultipleChoice", dozent1, Arrays.asList(new String[]{"1", "2", "3", "4"}));
        ma4 = new MultipleChoiceAufgabe(20, getAufgabenImage("MultipleChoice\\Grundlagen der Programmierung\\Aufgabenstellungsbild-Schwer"), Kategorie.Java_Programmierung, 25, Schwierigkeitsgrad.Schwer, "Gegeben ist folgender Programmcode, wie viele Zeilen werden bei dem Aufruf von recursion(600) ausgegeben?", "Recursion-Multiple-Choice", dozent1, Arrays.asList((new String[]{"0", "3", "6", "300"})));
        ma5 = new MultipleChoiceAufgabe(20, null, Kategorie.Java_Grundlagen, 25, Schwierigkeitsgrad.Schwer, "for(int i = n; i != 0;) {f(i/=2);} ist Java äquivalent zu;", "JavaÄquivalenz-Multiple-Choice", dozent2, Arrays.asList(new String[]{"For (int i = n; i > 0; i/= 2) { f(i);}", "{ int i; do {i = 1; f(i); } while ((i /= 2) != 0); };", "for (int i = 1; i <= n; i*= 2) { f(i); };", "{ int i; for ( i = n; ( i /=2) != 0; ) { f(i);}}"}));
        ma6 = new MultipleChoiceAufgabe(15, null, Kategorie.Java_Grundlagen, 20, Schwierigkeitsgrad.Mittel, "Was bricht der Befehl 'break' ab", "Break-Multiple-Choice", dozent2, Arrays.asList(new String[]{"Klasse", "Methode", "alle Schleifen", "derzeitige Schleife in der sich break befindet"}));
        ma7 = new MultipleChoiceAufgabe(12, null, Kategorie.Datenbanken, 15, Schwierigkeitsgrad.Leicht, "Was bewirkt der Befehl ASC, in einer SQL-Abfrage", "ASC-Multiple-Choice", dozent1, Arrays.asList(new String[]{"Ausgaben aufsteigend sortieren", "Ausgaben groß schreiben", "Ausgaben klein schreiben", "Datenbank aufsteigend sortieren"}));
        ma8 = new MultipleChoiceAufgabe(15, null, Kategorie.Datenbanken, 20, Schwierigkeitsgrad.Mittel, "Was bewirkt der Befehl ORDER BY, in einer SQL-Abfrage", "ORDER-BY-Multiple-Choice", dozent2, Arrays.asList(new String[]{"Abfragewerte geordnet ausgeben", "Ausgaben sortieren", "Ausgaben in einen Ordner schreiben", "Ausgaben werden nach dem Schlüssel sortiert ausgegeben"}));
        ma9 = new MultipleChoiceAufgabe(20, null, Kategorie.Datenbanken, 25, Schwierigkeitsgrad.Schwer, "Mit welchem Befehl sortiert man absteigend?", "Absteigend_Multiple-Choice", dozent1, Arrays.asList(new String[]{"DOWN", "DOWNWARD", "Decs", "keins davon"}));

        ea1 = new EinfachantwortAufgabe(15, null, Kategorie.Java_Grundlagen, 20, Schwierigkeitsgrad.Mittel, "Was ist der Unterschied zwischen 'equals()' und '=='?", "Equals/==-Unterschied", dozent2);
        ea2 = new EinfachantwortAufgabe(15, getAufgabenImage("EinfachAntwort\\EinfachantwortAufgabeKlassendiagrammeAufgabenstellungsbild"), Kategorie.Klassendiagramme, 24, Schwierigkeitsgrad.Mittel, "Beschreiben Sie die Multiplizitäten des Klassendiagramms", "Multiplizitäten beschreiben", dozent1);
        ea3 = new EinfachantwortAufgabe(20, null, Kategorie.Java_Grundlagen, 25, Schwierigkeitsgrad.Schwer, "Kann ein Konstruktor als 'final' deklariert werden?", "FinalerKonstruktor", dozent2);
        ea4 = new EinfachantwortAufgabe(12, null, Kategorie.Java_Grundlagen, 12, Schwierigkeitsgrad.Leicht, "Was benötigt man zum Ausführen eines Java-Programms?", "Java-Essentiell", dozent2);
        ea5 = new EinfachantwortAufgabe(12, null, Kategorie.Java_Programmierung, 12, Schwierigkeitsgrad.Leicht, "Wenn wir einen typischen Java Quelltext betrachten, fällt auf, dass die Textzeilen unterschiedlich eingerückt sind. Warum?", "Java-Einrückung", dozent2);
        ea6 = new EinfachantwortAufgabe(15, null, Kategorie.Java_Programmierung, 20, Schwierigkeitsgrad.Mittel, "Erläutern Sie den Nutzen von 'extends'", "Extends-Nutzen", dozent1);
        ea7 = new EinfachantwortAufgabe(20, null, Kategorie.Java_Programmierung, 25, Schwierigkeitsgrad.Schwer, "Beschreiben Sie was ein Interface ist.", "Interface-Beschreibung", dozent2);
        ea8 = new EinfachantwortAufgabe(12, null, Kategorie.Klassendiagramme, 12, Schwierigkeitsgrad.Leicht, "Womit werden Beziehungen in Klassendiagrammen dargestellt?", "Beziehungen in Klassendiagrammen", dozent1);
        ea9 = new EinfachantwortAufgabe(20, null, Kategorie.Klassendiagramme, 23, Schwierigkeitsgrad.Schwer, "Was ist ein Klassendiagramm? Beschreiben Sie ausführlich.", "Klassendiagramm?", dozent2);
        ea10 = new EinfachantwortAufgabe(12, null, Kategorie.Datenbanken, 15, Schwierigkeitsgrad.Leicht, "Beschreiben Sie was SQL ist.", "SQL-Beschreibung", dozent1);
        ea11 = new EinfachantwortAufgabe(15, null, Kategorie.Datenbanken, 20, Schwierigkeitsgrad.Mittel, "Geben Sie die 2. Normalform aus.", "2. Normalform", dozent2);
        ea12 = new EinfachantwortAufgabe(20, null, Kategorie.Datenbanken, 25, Schwierigkeitsgrad.Schwer, "Geben Sie die 3. Normalform aus.", "3. Normalform", dozent2);
        ea13 = new EinfachantwortAufgabe(12, null, Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Was ist das Pfadüberdeckungskriterium?", "Pfadüberdeckungskriterium", dozent2);
        ea14 = new EinfachantwortAufgabe(15, null, Kategorie.Software_Engineering, 20, Schwierigkeitsgrad.Mittel, "Was ist das Kriterium eines C0-Tests?", "C0-Test Kriterium", dozent2);
        ea15 = new EinfachantwortAufgabe(20, null, Kategorie.Software_Engineering, 25, Schwierigkeitsgrad.Schwer, "Was ist das Kriterium eines C1-Tests?", "C1-Test Kriterium", dozent1);

        dozent1.addErstellteAufgabe(pa1);
        dozent1.addErstellteAufgabe(pa4);
        dozent1.addErstellteAufgabe(pa5);
        dozent1.addErstellteAufgabe(pa6);
        dozent1.addErstellteAufgabe(pa8);
        dozent1.addErstellteAufgabe(pa9);
        dozent1.addErstellteAufgabe(pa10);
        dozent1.addErstellteAufgabe(da1);
        dozent1.addErstellteAufgabe(da3);
        dozent1.addErstellteAufgabe(da5);
        dozent1.addErstellteAufgabe(da6);
        dozent1.addErstellteAufgabe(ma1);
        dozent1.addErstellteAufgabe(ma3);
        dozent1.addErstellteAufgabe(ma4);
        dozent1.addErstellteAufgabe(ma7);
        dozent1.addErstellteAufgabe(ma9);
        dozent1.addErstellteAufgabe(ea2);
        dozent1.addErstellteAufgabe(ea6);
        dozent1.addErstellteAufgabe(ea8);
        dozent1.addErstellteAufgabe(ea10);
        dozent1.addErstellteAufgabe(ea15);

        dozent2.addErstellteAufgabe(pa2);
        dozent2.addErstellteAufgabe(pa3);
        dozent2.addErstellteAufgabe(pa7);
        dozent2.addErstellteAufgabe(da2);
        dozent2.addErstellteAufgabe(da4);
        dozent2.addErstellteAufgabe(ea1);
        dozent2.addErstellteAufgabe(ea3);
        dozent2.addErstellteAufgabe(ea4);
        dozent2.addErstellteAufgabe(ea5);
        dozent2.addErstellteAufgabe(ea7);
        dozent2.addErstellteAufgabe(ea9);
        dozent2.addErstellteAufgabe(ea11);
        dozent2.addErstellteAufgabe(ea12);
        dozent2.addErstellteAufgabe(ea13);
        dozent2.addErstellteAufgabe(ea14);
    }

    public void createBetaMusterloesung() throws Exception {
        mpa1 = new MusterloesungProgrammieraufgabe(pa1, "Zu dieser Aufgabe gibt es keinen Hinweis", "class Reservierung \n{\n private int reservierungsId; \n private int zimmerId; \n private DateTime anfang; \n private DateTime ende; \n private String kategorie; \n private boolean doppelzimmer; \n public int getReservierungsId()\n {\n return reservierungsId;\n}\n public Reservierung()\n{\n reservierungsId=0;\n}\n}");
        mpa2 = new MusterloesungProgrammieraufgabe(pa2, "Zu dieser Aufgabe gibt es keinen Hinweis", "public void baum()\n{\nint spaces = height - 1;\n   for( int i = 1; i <= height; i++ ) {\n       for( int j = 0; j < spaces; j++ ) {\n            System.out.print( \" \" );\n        }\n        for( int k = 0; k < (2 * i); k++ ) {\n           System.out.print( \"*\" );\n       }\n        System.out.println( \"\" );\n      spaces--;\n" + "    }\n\n   // Stamm\n    for( int i = 0; i < 2; i++ ) {\n        for( int j = 0; j < (height - 1); j++ ) {\n            System.out.print( \" \" );\n        }\n        System.out.println( \"**\" );\n    }");
        mpa3 = new MusterloesungProgrammieraufgabe(pa3, "Für die Abfrage an Werten, kann System.in() verwendet werden.", "public void ausgabe()\n{\n String name=System.in();\n String nname=System.in();\n String email=System.in();\n System.out.println(name);\n System.out.println(nname);\n System.out.println(email);\n}");
        mpa4 = new MusterloesungProgrammieraufgabe(pa4, "String-Array abfragen, vereinfachen diese Aufgabe.", "pubic void zahlwortausgabe(int zahl)\n{\n String einer[]= new String[10]{'null','eins','zwei','drei','vier','fünf','sechs','sieben','acht','neun'};\n String zehnerund[] = new String[10]{'','einund','zweiund','dreiund','vierund','fünfund','sechsund','siebenund','achtund','neunund'};\n String sonderzehn[]=new String[3]{'zehn','elf','zwölf'};\n String zehner[]=new String[10]{'','zehn','zwanzig','dreißig','vierzig','fünfzig','sechzig','siebzig','achtzieg','neunzig'};\n String hunderter[]=new String[10]{'','einhundert','zweihundert','dreihundert','vierhundert','fünfhundert','sechshundert','siebenhundert','achthundert','neunhundert'};\n if(zahl<10) System.out.println(einer[zahl]);\n else if(zahl<13) System.out.println(zehnerund[zahl-10]);\n else if(zahl<20){\n if(zahl==16)System.out.println('sechzehn');\nelse if(zahl==17) System.out.println('siebzehn');\n else if(zahl<100) System.out.println(zehnerund[zahl%10]+zehner[(zahl/10)]);\n else System.out.println(hunderter[zahl/100]+zehnerund[zahl%10]+zehner[(zahl/10)]);\n}");
        mpa5 = new MusterloesungProgrammieraufgabe(pa5, "Für die Abfrage an Werten kann System.in() verwendet werden.", "public void rechnung(){\n int zahl1=System.in();\n int zahl2=System.in();\n System.out.println('Die Zahl '+ zahl1+' und die Zahl '+zahl2+' ergeben die Summe '+ zahl1+zahl2);\n}\n");
        mpa6 = new MusterloesungProgrammieraufgabe(pa6, "Schaltjahre sind alle 4 Jahre, sowie alle 400 Jahre", "boolean checkLeapYear(int yearInput) {\n if (yearInput % 400 == 0) {\n return true;\n }\n else if (yearInput % 4 == 0 && yearInput % 100 != 0) {\n return true; \n }\n else { \n return false;\n }\n}");
        mpa7 = new MusterloesungProgrammieraufgabe(pa7, "Zu dieser Aufgabe gibt es keinen Hinweis", "String volumeConverter(float volume) {\n if(volume >= 1.0f) {\n return volume + ' l';\n}\n else if(volume >= 0.1f) {\n int result = (int)(volume * 100) / 1;\n return result + ' cl';\n }\n else if(volume >= 0.001f)\n {\n int result = (int)(volume * 1000) / 1;\n return result + ' ml';\n }\n else return 'Number too small!'; }");
        mpa8 = new MusterloesungProgrammieraufgabe(pa8, "Zu dieser Aufgabe gibt es keinen Hinweis", "SELECT *\nFROM country");
        mpa9 = new MusterloesungProgrammieraufgabe(pa9, "Zu dieser Aufgabe gibt es keinen Hinweis", "SELECT COUNT(*)\nFROM country");
        mpa10 = new MusterloesungProgrammieraufgabe(pa10, "Zu dieser Aufgabe gibt es keinen Hinweis", "SELECT MAX(population), MAX(area)\nFROM country");

        pa1.setMusterloesung(mpa1);
        pa2.setMusterloesung(mpa2);
        pa3.setMusterloesung(mpa3);
        pa4.setMusterloesung(mpa4);
        pa5.setMusterloesung(mpa5);
        pa6.setMusterloesung(mpa6);
        pa7.setMusterloesung(mpa7);
        pa8.setMusterloesung(mpa8);
        pa9.setMusterloesung(mpa9);
        pa10.setMusterloesung(mpa10);

        mda1 = new MusterloesungDesignaufgabe(da1, "Zu dieser Aufgabe gibt es keinen Hinweis", getAufgabenImage("Design\\Klassendiagramme\\KlassendiagrammLösung-Leicht"));
        mda2 = new MusterloesungDesignaufgabe(da2, "Zu dieser Aufgabe gibt es keinen Hinweis", getAufgabenImage("Design\\Klassendiagramme\\Klassendiagramm-Mittel-Lösung"));
        mda3 = new MusterloesungDesignaufgabe(da3, "Zu dieser Aufgabe gibt es keinen Hinweis", getAufgabenImage("Design\\Klassendiagramme\\KlassendiagrammLösung-Schwer"));
        mda4 = new MusterloesungDesignaufgabe(da4, "Zu dieser Aufgabe gibt es keinen Hinweis", getAufgabenImage("Design\\Software Engineering\\SoftwareEngineeringLösung-Leicht"));
        mda5 = new MusterloesungDesignaufgabe(da5, "Zu dieser Aufgabe gibt es keinen Hinweis", getAufgabenImage("Design\\Software Engineering\\SoftwareEngineeringLösung-Mittel"));
        mda6 = new MusterloesungDesignaufgabe(da6, "Zu dieser Aufgabe gibt es keinen Hinweis", getAufgabenImage("Design\\Software Engineering\\SoftwareEngineeringLösung-Schwer"));

        da1.setMusterloesung(mda1);
        da2.setMusterloesung(mda2);
        da3.setMusterloesung(mda3);
        da4.setMusterloesung(mda4);
        da5.setMusterloesung(mda5);
        da6.setMusterloesung(mda6);

        mma1 = new MusterloesungMultipleChoiceAufgabe(ma1, "Zu dieser Aufgabe gibt es keinen Hinweis", 2);
        mma2 = new MusterloesungMultipleChoiceAufgabe(ma2, "Zu dieser Aufgabe gibt es keinen Hinweis", 3);
        mma3 = new MusterloesungMultipleChoiceAufgabe(ma3, "Zu dieser Aufgabe gibt es keinen Hinweis", 3);
        mma4 = new MusterloesungMultipleChoiceAufgabe(ma4, "Zu dieser Aufgabe gibt es keinen Hinweis", 3);
        mma5 = new MusterloesungMultipleChoiceAufgabe(ma5, "Zu dieser Aufgabe gibt es keinen Hinweis", 3);
        mma6 = new MusterloesungMultipleChoiceAufgabe(ma6, "'break' findet sich häufig in Schwitch-Cases wieder", 4);
        mma7 = new MusterloesungMultipleChoiceAufgabe(ma7, "Wird in Verbindung mit ORDER BY verwendet", 1);
        mma8 = new MusterloesungMultipleChoiceAufgabe(ma8, "Zu dieser Aufgabe gibt es keinen Hinweis", 1);
        mma9 = new MusterloesungMultipleChoiceAufgabe(ma9, "Steht in direkter Verbindung mit ASC", 3);

        ma1.setMusterloesung(mma1);
        ma1.setMusterloesung(mma1);
        ma2.setMusterloesung(mma2);
        ma3.setMusterloesung(mma3);
        ma4.setMusterloesung(mma4);
        ma5.setMusterloesung(mma5);
        ma6.setMusterloesung(mma6);
        ma7.setMusterloesung(mma8);
        ma9.setMusterloesung(mma9);

        mea1 = new MusterloesungEinfachantwort(ea1, "Zu dieser Aufgabe gibt es keinen Hinweis", "Zunächst einmal ist '==' ein Operator und 'equals()' eine Methode. Der Operator '00' verwenden wir für einen Referenzvergleich, während wir die Mehtode 'equals()' für einen Inhaltsvergleich nutzen.");
        mea2 = new MusterloesungEinfachantwort(ea2, "Zu dieser Aufgabe gibt es keinen Hinweis", "Die Anforderungen für eine Softwäre zur Auftragsverwaltung sehen vor, dass ein Kunde keinen, einen oder mehrere Aufträge erteilen kann. Umgekehrt kann ein Auftrag immer nur von genau einem Kunden erteilt werden.");
        mea3 = new MusterloesungEinfachantwort(ea3, "Zu dieser Aufgabe gibt es keinen Hinweis", "Nein. Ein Konstruktor kann nicht als 'final' deklariert werden, weil er nicht vererbt werden kann.");
        mea4 = new MusterloesungEinfachantwort(ea4, "Zu dieser Aufgabe gibt es keinen Hinweis", "Zum Ausführen von Java-Programmen benötigt man die Java Runtime Enviroment(kurz JRE):");
        mea5 = new MusterloesungEinfachantwort(ea5, "Zu dieser Aufgabe gibt es keinen Hinweis", "Das Einrücken der unterschiedlichen Textzeilen in einem Java Quelltext dient ausschließlich der Lesbarkeit und ist für jeden Java Compiler unwichtig.");
        mea6 = new MusterloesungEinfachantwort(ea6, "Bedenken Sie das Thema Vererbung.", "Extends dient in Java zur Implementation von Vererbung.");
        mea7 = new MusterloesungEinfachantwort(ea7, "Zu dieser Aufgabe gibt es keinen Hinweis", "Ein Interface ist eine Schnittstelle, über die einer Klasse bestimmte Funktionen zur Verfügung gestellt werden. Um die Funktionen nutzen zu können, müssen sie aber erst von der Klasse implementiert werden. Das Interface gibt nur den Rahmen (die Methodendeklarationen) vor.");
        mea8 = new MusterloesungEinfachantwort(ea8, "Zu dieser Aufgabe gibt es keinen Hinweis", "Durch Assoziation, Aggregation, Komposition, Vererbung und Kardinalitäten.");
        mea9 = new MusterloesungEinfachantwort(ea9, "Zu dieser Aufgabe gibt es keinen Hinweis", "Ein Klassendiagramm ist ein Strukturdiagramm der Unified Modeling Language (UML) zur grafischen Darstellung (Modellierung) von Klassen, Schnittstellen sowie deren Beziehungen.");
        mea10 = new MusterloesungEinfachantwort(ea10, "Zu dieser Aufgabe gibt es keinen Hinweis", "Die Abkürzung SQL steht für den Begriff Structured Query Language und bezeichnet eine Sprache für die Kommunikation mit relationalen Datenbanken. ");
        mea11 = new MusterloesungEinfachantwort(ea11, "Zu dieser Aufgabe gibt es keinen Hinweis", "Ein Relationenschema ist in der 2. Normalform, wenn es in der 1. Normalform ist und wenn jedes nicht zum Identifikationsschlüssel gehörige Attribut von diesem voll funktional abhängig ist.");
        mea12 = new MusterloesungEinfachantwort(ea12, "Zu dieser Aufgabe gibt es keinen Hinweis", "Ein Relationenschema befindet sich in der 3. Normalform, wenn es in der 2. Normalform ist und kein Attribut, das nicht zum Identifikationsschlüssel gehört, von diesem transitiv abhängt.");
        mea13 = new MusterloesungEinfachantwort(ea13, "Zu dieser Aufgabe gibt es keinen Hinweis", "Für jeden möglichen Pfad durch den Kontrollflussgraphen muss ein separater Testfall existieren.");
        mea14 = new MusterloesungEinfachantwort(ea14, "Zu dieser Aufgabe gibt es keinen Hinweis", "Alle Anweisungen des Programms müssen mindestens einmal ausgeführt werden.");
        mea15 = new MusterloesungEinfachantwort(ea15, "Zu dieser Aufgabe gibt es keinen Hinweis", " Jede Kante des Graphen muss von mindestens einem Testfall durchlaufen werden.");

        ea1.setMusterloesung(mea1);
        ea2.setMusterloesung(mea2);
        ea3.setMusterloesung(mea3);
        ea4.setMusterloesung(mea4);
        ea5.setMusterloesung(mea5);
        ea6.setMusterloesung(mea6);
        ea7.setMusterloesung(mea7);
        ea8.setMusterloesung(mea8);
        ea9.setMusterloesung(mea9);
        ea10.setMusterloesung(mea10);
        ea11.setMusterloesung(mea11);
        ea12.setMusterloesung(mea12);
        ea13.setMusterloesung(mea13);
        ea14.setMusterloesung(mea14);
        ea15.setMusterloesung(mea15);
    }

    public void createBetaAufgabensammlungen() throws Exception {
        training1 = new Training(Arrays.asList(new Aufgabe[]{ma1,pa2, ea4}), 40, Kategorie.Java_Grundlagen, Schwierigkeitsgrad.Leicht, Arrays.asList(new Aufgabentyp[]{Aufgabentyp.MultipleChoice, Aufgabentyp.Einfachantwort, Aufgabentyp.Programmieren}), student2);
        training2 = new Training(Arrays.asList(new Aufgabe[]{ea12, ma9, pa10}), 60, Kategorie.Datenbanken, Schwierigkeitsgrad.Schwer, Arrays.asList(new Aufgabentyp[]{Aufgabentyp.MultipleChoice, Aufgabentyp.Programmieren, Aufgabentyp.Einfachantwort}), student3);
        training3 = new Training(Arrays.asList(new Aufgabe[]{ea6, ma3, pa6}), 50, Kategorie.Java_Programmierung, Schwierigkeitsgrad.Mittel, Arrays.asList(new Aufgabentyp[]{Aufgabentyp.MultipleChoice, Aufgabentyp.Programmieren, Aufgabentyp.Einfachantwort}), student1);

        student2.addBearbeitetesTraining(training1);
        student3.addBearbeitetesTraining(training2);
        student1.addBearbeitetesTraining(training3);

        testat1 = new Testat(Arrays.asList(new Aufgabe[]{da1, da4, ma1, ea8, ea10}), "000", "Einsteigertestat", dozent1);
        testat4 = new Testat(Arrays.asList(new Aufgabe[]{pa5, da2, da5, ea2, ma8}), "000", "Fortgeschrittenentestat", dozent1);
        testat3 = new Testat(Arrays.asList(new Aufgabe[]{ da6,da3,ea7, pa10}), "000", "Profitestat", dozent2);
        testat2 = new Testat(Arrays.asList(new Aufgabe[]{da1, da3, da2, da4, da6}), "000", "Sondertestat", dozent2);

        dozent1.addErstelltesTestat(testat1);
        dozent1.addErstelltesTestat(testat4);
        dozent2.addErstelltesTestat(testat3);
        dozent2.addErstelltesTestat(testat2);
    }

    public void createBetaUserLösungen() throws Exception {
        //training1 von student2
        ul1 = new UserloesungMultipleChoiceAufgabe(ma1, true, 2, student2, training1);
        ul2 = new UserloesungEinfachantwort(ea4, false, "JRE", student2, training1);
        ul3 = new UserloesungProgrammieraufgabe(pa2, true, "public void baum(int h){\n String s='*';\nfor(int i=i;i<h;i++){\nSystem.out.println(i*s);\n}\n}", student2, training1);

        student2.addErstellteLoesung(ul1);
        student2.addErstellteLoesung(ul2);
        student2.addErstellteLoesung(ul3);

        //training2 von student3
        ul4 = new UserloesungEinfachantwort(ea12, false, "", student3, training2);
        ul5 = new UserloesungMultipleChoiceAufgabe(ma9, false, 2, student3, training2);
        ul6 = new UserloesungProgrammieraufgabe(pa10, false, "SELECT MAX(Einwohnerzahl)\nWHERE country;", student3, training2);

        student3.addErstellteLoesung(ul4);
        student3.addErstellteLoesung(ul5);
        student3.addErstellteLoesung(ul6);

        //training3 von student1
        ul7 = new UserloesungEinfachantwort(ea6, false, "Dient zur Vererbung.", student1, training3);
        ul8 = new UserloesungMultipleChoiceAufgabe(ma3, false, 2, student1, training3);
        ul9 = new UserloesungProgrammieraufgabe(pa6, true, "public void istSchaltjahr(int jahr)\n{\n Ka Mann;\n}\n", student1, training3);

        student1.addErstellteLoesung(ul7);
        student1.addErstellteLoesung(ul8);
        student1.addErstellteLoesung(ul9);

        //testat1 von student1
        ul10 = new UserloesungDesignaufgabe(da1, false, getAufgabenImage("Design\\Userlösung\\userloesung1"), student1, testat1);
        ul11 = new UserloesungDesignaufgabe(da4, false, getAufgabenImage("Design\\Userlösung\\userloesung2"), student1, testat1);
        ul12 = new UserloesungMultipleChoiceAufgabe(ma1, false, 2, student1, testat1);
        ul13 = new UserloesungEinfachantwort(ea8, false, "Kardinalitäten", student1, testat1);
        ul14 = new UserloesungEinfachantwort(ea10, false, "Datenbankabfragesprache", student1, testat1);

        student1.addErstellteLoesung(ul10);
        student1.addErstellteLoesung(ul11);
        student1.addErstellteLoesung(ul12);
        student1.addErstellteLoesung(ul13);
        student1.addErstellteLoesung(ul14);

        //testat2 von student1
        ul15 = new UserloesungDesignaufgabe(da1, false, getAufgabenImage("Design\\Userlösung\\userloesung2"), student1, testat2);
        ul16 = new UserloesungDesignaufgabe(da3, false, getAufgabenImage("Design\\Userlösung\\userloesung3"), student1, testat2);
        ul17 = new UserloesungDesignaufgabe(da2, false, getAufgabenImage("Design\\Userlösung\\userloesung4"), student1, testat2);
        ul18 = new UserloesungDesignaufgabe(da4, false, getAufgabenImage("Design\\Userlösung\\userloesung5"), student1, testat2);
        ul19 = new UserloesungDesignaufgabe(da6, false, getAufgabenImage("Design\\Software Engineering\\SoftwareEngineeringLösung-Schwer"), student1, testat2);

        student1.addErstellteLoesung(ul15);
        student1.addErstellteLoesung(ul16);
        student1.addErstellteLoesung(ul17);
        student1.addErstellteLoesung(ul18);
        student1.addErstellteLoesung(ul19);

        testatBearbeitung = new TestatBearbeitung(testat1, 40, student1, dozent1);
        dozent1.addBewertetesTestat(testatBearbeitung);
        student1.addBearbeitetesTestat(testatBearbeitung);

        testatBearbeitung2 = new TestatBearbeitung(testat2);
        testatBearbeitung2.setTestatBearbeiter(student1);
        student1.addBearbeitetesTestat(testatBearbeitung2);

        student2.addBearbeitetesTraining(training1);
        student3.addBearbeitetesTraining(training2);
        student1.addBearbeitetesTraining(training3);
    }

    private byte[] getAufgabenImage(String filename) {
        String userDirectory = System.getProperty("user.dir");

        File file = new File(userDirectory + "\\Aufgabenbilder\\" + filename + ".png");
        byte[] byteArray = DatabaseService.convertFileToByteArray(file, null);
        return byteArray;
    }
}