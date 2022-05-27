package persistence;


import View.LoginView;
import entity.aufgabe.*;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.benutzer.Student;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.*;
import entity.loesung.userloesung.*;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
    private Dozent dozent3;

    private List<Aufgabe> aufgabenliste;
    private Designaufgabe da1, da2, da3, da4;
    private Programmieraufgabe pa1, pa2, pa3, pa4;
    private MultipleChoiceAufgabe ma1, ma2, ma3, ma4;
    private EinfachantwortAufgabe ea1, ea2, ea3, ea4;
    private Aufgabe ta1, ta2, ta3, ta4, ta5;

    private List<Musterloesung> musterloesungsliste;
    private MusterloesungDesignaufgabe mda1, mda2, mda3, mda4;
    private MusterloesungProgrammieraufgabe mpa1, mpa2, mpa3, mpa4;
    private MusterloesungMultipleChoiceAufgabe mma1, mma2, mma3, mma4;
    private MusterloesungEinfachantwort mea1, mea2, mea3, mea4;
    private Musterloesung mta1, mta2, mta3, mta4, mta5;

    private List<Userloesung> userloesungsliste;
    private Userloesung ul1, ul2, ul3, ul4, ul5, ul6, ul7, ul8, ul9, ul10, ul11, ul12, ul13, ul14, ul15, ul16, ul17, ul18, ul19;

    private List<TestatBearbeitung> testatBearbeitungsliste;
    private TestatBearbeitung testatBearbeitung, testatBearbeitung2, testatBearbeitung3, testatBearbeitung4, testatBearbeitung5, testatBearbeitung6, testatBearbeitung7;

    private List<Aufgabensammlung> aufgabensammlungsliste;
    private Testat testat1, testat2, testat3;
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
        ds.persistObject(dozent3);
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
        dozent3 = new Dozent("admin", "000", "Admin", "");
        dozentenliste = Arrays.asList(new Dozent[]{dozent1, dozent2, dozent3});
        dozentenliste = new LinkedList<>(dozentenliste);
    }

    public void createBetaAufgaben() {
        da1 = new Designaufgabe(7, null, Kategorie.Klassendiagramme, 15, Schwierigkeitsgrad.Leicht, "Designen Sie die passende UML-Struktur für den Programmcode", "Programmcode zu UML-Design", dozent1);
        da2 = new Designaufgabe(15, null, Kategorie.Datenbanken, 20, Schwierigkeitsgrad.Mittel, "Designen Sie das UML-Diagramm zu dieser Datenbankstruktur", "UML-Designaufgabe", dozent1);
        da3 = new Designaufgabe(20, null, Kategorie.Software_Engineering, 25, Schwierigkeitsgrad.Schwer, "Stellen Sie die unterschiedlichen Architekturprinzipien dar.", "Architekturprinzipien", dozent2);
        da4 = new Designaufgabe(10, null, Kategorie.Software_Engineering, 30, Schwierigkeitsgrad.Schwer, "Stellen Sie die 3-Schichten-Architektur dar und beschriften Sie diese.", "3-Schichten-Architektur", dozent2);

        pa1 = new Programmieraufgabe(15, null, Kategorie.Java_Programmierung, 20, Schwierigkeitsgrad.Mittel, "Stellen Sie die Beziehungen aus dem UML-Design in Java dar.", "Beziehungen Programmieren", dozent2);
        pa2 = new Programmieraufgabe(10, null, Kategorie.Java_Grundlagen, 10, Schwierigkeitsgrad.Leicht, "Erstellen Sie eine Methode, die in der Konsole einen Tannenbaum aus Sternen(*) ausgibt.", "Baum erstellen.", dozent1);
        pa3 = new Programmieraufgabe(20, null, Kategorie.Datenbanken, 35, Schwierigkeitsgrad.Schwer, "Bearbeiten Sie den Programmcode so, dass die Entitäten in der Datenbank persistiert werden.", "Datenbankpersistenz", dozent1);
        pa4 = new Programmieraufgabe(15, null, Kategorie.Java_Programmierung, 25, Schwierigkeitsgrad.Mittel, "Erstellen Sie eine Methode, die die ersten 100 Primzahlen auf der Konsole ausgibt", "Primzahlenausgabe", dozent2);

        List<String> lma1 = Arrays.asList("int", "String", "char", "boolean");
        List<String> lma2 = Arrays.asList("int", "String", "char", "boolean");
        List<String> lma3 = Arrays.asList("Assoziation", "Aggregation", "Komposition", "Kardinalität");
        List<String> lma4 = Arrays.asList("break", "stop", "return", "finish");

        ma1 = new MultipleChoiceAufgabe(5, null, Kategorie.Java_Grundlagen, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Wahrheitswerte geeignet", "Datentypabfrage", dozent1, lma1);
        ma2 = new MultipleChoiceAufgabe(5, null, Kategorie.Java_Grundlagen, 5, Schwierigkeitsgrad.Leicht, "Welche der Datentypen ist kein primitiver Datentyp?", "Primitiver Datentyp", dozent2, lma2);
        ma3 = new MultipleChoiceAufgabe(5, null, Kategorie.Klassendiagramme, 15, Schwierigkeitsgrad.Mittel, "Welche ist keine Art der Beziehung in einem UML-Diagramm", "Beziehungen in UML", dozent1, lma3);
        ma4 = new MultipleChoiceAufgabe(5, null, Kategorie.Java_Programmierung, 20, Schwierigkeitsgrad.Mittel, "Wie beendet man vorläufig eine Switch-Case-Anweisung?", "Switch-Case-Beenden", dozent2, lma4);

        ea1 = new EinfachantwortAufgabe(7, null, Kategorie.Datenbanken, 25, Schwierigkeitsgrad.Schwer, "Begründen Sie warum für diese Beziehung eine Komposition und nicht eine Aggregation gewählt wurde.", "BeziehungsbegründungDB", dozent2);
        ea2 = new EinfachantwortAufgabe(15, null, Kategorie.Software_Engineering, 30, Schwierigkeitsgrad.Schwer, "Erläutern Sie, warum die Methode berrechneVortex() 'null' als return-Wert setzt.", "MethodenErläuterung", dozent2);
        ea3 = new EinfachantwortAufgabe(20, null, Kategorie.Java_Programmierung, 20, Schwierigkeitsgrad.Mittel, "Erläutern Sie, warum diese Methode kein Objekt der Klasse Auto erstellen kann.", "MethodenErläuterung2", dozent1);
        ea4 = new EinfachantwortAufgabe(15, null, Kategorie.Java_Programmierung, 15, Schwierigkeitsgrad.Leicht, "Erläuteren Sie den Programmcode und warum ein Objekt der Klasse Fahrrad erstellt wird", "Programmcodeweiterführung", dozent1);

        ta1 = new EinfachantwortAufgabe(7, null, Kategorie.Java_Grundlagen, 7, Schwierigkeitsgrad.Leicht, "Programmieren Sie den Konstruktor dieser Klasse", "Klassenkonstruktor", dozent2);
        ta2 = new Programmieraufgabe(10, null, Kategorie.Java_Grundlagen, 12, Schwierigkeitsgrad.Leicht, "Eliminieren SIe die Codeduplizierung in der gegebenen Klasse", "Codeduplizierung", dozent1);
        ta3 = new Programmieraufgabe(12, null, Kategorie.Java_Grundlagen, 8, Schwierigkeitsgrad.Leicht, "Programmieren Sie eine for-Schleife", "for-Schleife", dozent1);
        ta4 = new EinfachantwortAufgabe(15, null, Kategorie.Java_Grundlagen, 12, Schwierigkeitsgrad.Leicht, "Warum gibt dieser Code 7 zurück", "Rückgabewerte", dozent2);
        ta5 = new Designaufgabe(20, null, Kategorie.Java_Grundlagen, 15, Schwierigkeitsgrad.Leicht, "Designen Sie das Klassendiagramm", "Klassendiagramm", dozent2);

        dozent1.addErstellteAufgabe(ea4);
        dozent1.addErstellteAufgabe(ea3);
        dozent1.addErstellteAufgabe(ma3);
        dozent1.addErstellteAufgabe(ma1);
        dozent1.addErstellteAufgabe(pa3);
        dozent1.addErstellteAufgabe(da4);
        dozent1.addErstellteAufgabe(da2);
        dozent1.addErstellteAufgabe(da1);
        dozent1.addErstellteAufgabe(pa2);
        dozent1.addErstellteAufgabe(ta2);
        dozent1.addErstellteAufgabe(ta3);

        dozent2.addErstellteAufgabe(da3);
        dozent2.addErstellteAufgabe(pa1);
        dozent2.addErstellteAufgabe(ea2);
        dozent2.addErstellteAufgabe(ea1);
        dozent2.addErstellteAufgabe(ma4);
        dozent2.addErstellteAufgabe(ma2);
        dozent2.addErstellteAufgabe(pa3);
        dozent2.addErstellteAufgabe(ta1);
        dozent2.addErstellteAufgabe(ta4);
        dozent2.addErstellteAufgabe(ta5);

        aufgabenliste = Arrays.asList(da1, da2, da3, da4, pa1, pa2, pa3, pa4, ma1, ma2, ma3, ma4, ea1, ea2, ea3, ea4, ta1, ta2, ta3, ta4, ta5);
        aufgabenliste = new LinkedList<>(aufgabenliste);

        //70% der Aufgaben erhalten ein zufälliges Bild als Aufgabenstellungsbild
        //Da das Bild optional ist, erhalten nicht alle Aufgaben ein Bild
        for (Aufgabe a : aufgabenliste) {
            double randomNumber = Math.random();
            if (randomNumber < 0.7) {
                a.setAufgabenstellungsbild(getRandomTestImage());
            }
        }
    }

    public void createBetaMusterloesung() throws Exception {
        mda1 = new MusterloesungDesignaufgabe(da1, "Beziehungen wie Aggregation und Komposition sind hier nicht gefragt.", getRandomTestImage());
        mda2 = new MusterloesungDesignaufgabe(da2, "Beziehungen wie Aggregation und Komposition sind hier nicht gefragt.", getRandomTestImage());
        mda3 = new MusterloesungDesignaufgabe(da3, "Es gibt 7 unterschiedliche Architekturprinzipien.", getRandomTestImage());
        mda4 = new MusterloesungDesignaufgabe(da4, "Ich bin ein Lösungshinweis", getRandomTestImage());
        da1.setMusterloesung(mda1);
        da2.setMusterloesung(mda2);
        da3.setMusterloesung(mda3);
        da4.setMusterloesung(mda4);

        mpa1 = new MusterloesungProgrammieraufgabe(pa1, "Beziehungen lassen sich durch Attribute darstellen", "Hier steht die Lösung, als Programmcode - Bsplw. Aufgabe aufgabe = new Aufgabe()");
        mpa2 = new MusterloesungProgrammieraufgabe(pa2, "Dies lässt sich durch eine Schleife vereinfachen,", "Siehe Lösung");
        mpa3 = new MusterloesungProgrammieraufgabe(pa3, "Zum Persistieren braucht man eine DatabaseService-Klasse", "Siehe Lösung");
        mpa4 = new MusterloesungProgrammieraufgabe(pa4, "Primzahlen sind nur durch 1 und sich selber teilbar", "Siehe Lösung");
        pa1.setMusterloesung(mpa1);
        pa2.setMusterloesung(mpa2);
        pa3.setMusterloesung(mpa3);
        pa4.setMusterloesung(mpa4);

        mma1 = new MusterloesungMultipleChoiceAufgabe(ma1, "Ich bin ein Lösungshinweis", 4);
        mma2 = new MusterloesungMultipleChoiceAufgabe(ma2, "Nicht primitive Datentypen sind bspw. Klassen", 2);
        mma3 = new MusterloesungMultipleChoiceAufgabe(ma3, "Hier steht ein Lösungshinweis", 4);
        mma4 = new MusterloesungMultipleChoiceAufgabe(ma4, "Die Switch-Case-Anweisung lässt sich auch mit dem beenden der Methode beenden.", 1);
        ma1.setMusterloesung(mma1);
        ma2.setMusterloesung(mma2);
        ma3.setMusterloesung(mma3);
        ma4.setMusterloesung(mma4);

        mea1 = new MusterloesungEinfachantwort(ea1, "Hier steht der Unterschied zwischen Aggregation und Komposition.", "Hier sthet die Begründung");
        mea2 = new MusterloesungEinfachantwort(ea2, "Erklärung eines Return-Wertes", "Begründung für 'null'.");
        mea3 = new MusterloesungEinfachantwort(ea3, "Um Objekte erstellen zu können muss man Sie kennen", "Die Methode/Klasse sthet in keiner Weise mit der Klasse Auto in verbindung");
        mea4 = new MusterloesungEinfachantwort(ea4, "Um ein Objekt zu erstellen muss die Klasse/Methode diese Objekt kennen.", "Erläuterung");
        ea1.setMusterloesung(mea1);
        ea2.setMusterloesung(mea2);
        ea3.setMusterloesung(mea3);
        ea4.setMusterloesung(mea4);

        mta1 = new MusterloesungEinfachantwort((EinfachantwortAufgabe) ta1, "Der Konstruktor hat den selben Namen wie seine Klasse", "public Konstruktor()");
        mta2 = new MusterloesungProgrammieraufgabe((Programmieraufgabe) ta2, "Lösunghinweis", "Musterlösung");
        mta3 = new MusterloesungProgrammieraufgabe((Programmieraufgabe) ta3, "Lösungshinweis", "Müsterlösung");
        mta4 = new MusterloesungEinfachantwort((EinfachantwortAufgabe) ta4, "Lösungshinweis", "Musterlösung");
        mta5 = new MusterloesungDesignaufgabe((Designaufgabe) ta5, "Lösungshinweis", getRandomTestImage());
        ta1.setMusterloesung(mta1);
        ta2.setMusterloesung(mta2);
        ta3.setMusterloesung(mta3);
        ta4.setMusterloesung(mta4);
        ta5.setMusterloesung(mta5);

        musterloesungsliste = Arrays.asList(mda1, mda2, mda3, mda4, mpa1, mpa2, mpa3, mpa4, mma1, mma2, mma3, mma4, mea1, mea2, mea3, mea4, mta1, mta2, mta3, mta4, mta5);
        musterloesungsliste = new LinkedList<>(musterloesungsliste);
    }

    public void createBetaAufgabensammlungen() throws Exception {
        List<Aufgabe> aufgabenliste1 = Arrays.asList(da1, da4, ea3, pa4, pa1, pa2, da2);
        List<Aufgabe> aufgabenliste2 = Arrays.asList(da2, da4, ma3, ma4, ma1, pa3, da3);
        List<Aufgabe> aufgabenliste3 = Arrays.asList(ma1, ma2, ma3, ma4);
        List<Aufgabe> aufgabenliste4 = Arrays.asList(ea1, ma1, da1, pa1);
        List<Aufgabe> aufgabenliste5 = Arrays.asList(ea3, ea4, ma4, pa4, pa1);

        testat1 = new Testat(aufgabenliste1, "000", "Testat für Anfänger", dozent1);
        for (Aufgabe a : aufgabenliste1)
            a.addVerwendung(testat1);
        dozent1.addErstelltesTestat(testat1);
        testat2 = new Testat(aufgabenliste3, "000", "Testat nur MultipleChoice-Aufgaben", dozent2);
        for (Aufgabe a : aufgabenliste3)
            a.addVerwendung(testat2);
        dozent2.addErstelltesTestat(testat2);
        testat3 = new Testat(aufgabenliste2, "000", "Frühlingstestat", dozent1);
        for (Aufgabe a : aufgabenliste2)
            a.addVerwendung(testat3);
        dozent1.addErstelltesTestat(testat3);

        training1 = new Training(aufgabenliste5, 50, Kategorie.Java_Programmierung, Schwierigkeitsgrad.Mittel, student2);
        for (Aufgabe a : aufgabenliste5)
            a.addVerwendung(training1);
        student2.addBearbeitetesTraining(training1);
        training2 = new Training(aufgabenliste4, 30, Kategorie.Java_Grundlagen, Schwierigkeitsgrad.Mittel, student4);
        for (Aufgabe a : aufgabenliste4)
            a.addVerwendung(training2);
        student4.addBearbeitetesTraining(training2);
        training3 = new Training(aufgabenliste1, 90, Kategorie.Java_Grundlagen, Schwierigkeitsgrad.Mittel, student3);
        for (Aufgabe a : aufgabenliste1)
            a.addVerwendung(training3);
        student3.addBearbeitetesTraining(training3);

        aufgabensammlungsliste = Arrays.asList(testat1, testat2, testat3, training1, training2, training3);
        aufgabensammlungsliste = new LinkedList<>(aufgabensammlungsliste);
    }

    public void createBetaUserLösungen() throws Exception {
        byte [] testBild = getRandomTestImage();
        ul1 = new UserloesungDesignaufgabe(da1,false,testBild,student3,training3);
        ul2 = new UserloesungEinfachantwort((EinfachantwortAufgabe) ea3, false, "Erklärung", student3, training3);
        ul3 = new UserloesungDesignaufgabe(da2, false, testBild, student3, training3);
        ul4 = new UserloesungProgrammieraufgabe((Programmieraufgabe) pa1, true, "Code", student3, training3);
        ul5 = new UserloesungProgrammieraufgabe((Programmieraufgabe) pa4, true, "Code", student3, training3);
        ul6 = new UserloesungDesignaufgabe(da4, false, testBild, student3, training3);
        ul7 = new UserloesungProgrammieraufgabe(pa2, false, "assa", student3, training3);
        student3.addErstellteLoesung(ul1);
        student3.addErstellteLoesung(ul2);
        student3.addErstellteLoesung(ul3);
        student3.addErstellteLoesung(ul4);
        student3.addErstellteLoesung(ul5);
        student3.addErstellteLoesung(ul6);
        student3.addErstellteLoesung(ul7);
        training3.addUserloesung(ul1);
        training3.addUserloesung(ul2);
        training3.addUserloesung(ul3);
        training3.addUserloesung(ul4);
        training3.addUserloesung(ul5);
        training3.addUserloesung(ul6);
        training3.addUserloesung(ul7);


        Userloesung test1 = new UserloesungEinfachantwort(ea1, false, "pong", student4, training2);
        Userloesung test2 = new UserloesungMultipleChoiceAufgabe(ma1, false, 3, student4, training2);
        Userloesung test3 = new UserloesungDesignaufgabe(da1, false, testBild, student4, training2);
        Userloesung test4 = new UserloesungProgrammieraufgabe(pa1, false, "asdasd", student4, training2);
        student4.addErstellteLoesung(test1);
        student4.addErstellteLoesung(test2);
        student4.addErstellteLoesung(test3);
        student4.addErstellteLoesung(test4);
        training2.addUserloesung(test1);
        training2.addUserloesung(test2);
        training2.addUserloesung(test3);
        training2.addUserloesung(test4);


        Userloesung a = new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma4, false, 3, student2, training1);
        Userloesung b = new UserloesungEinfachantwort((EinfachantwortAufgabe) ea3, false, "Erklärung", student2, training1);
        Userloesung c = new UserloesungEinfachantwort((EinfachantwortAufgabe) ea4, false, "System.out.println()", student2, training1);
        Userloesung d = new UserloesungProgrammieraufgabe((Programmieraufgabe) pa1, true, "Code", student2, training1);
        Userloesung e = new UserloesungProgrammieraufgabe((Programmieraufgabe) pa4, true, "Code", student2, training1);
        student2.addErstellteLoesung(a);
        student2.addErstellteLoesung(b);
        student2.addErstellteLoesung(c);
        student2.addErstellteLoesung(d);
        student2.addErstellteLoesung(e);
        training1.addUserloesung(a);
        training1.addUserloesung(b);
        training1.addUserloesung(c);
        training1.addUserloesung(d);
        training1.addUserloesung(e);

        ul6 = new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma1, false, 4, student1, testat2);
        ul7 = new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma2, false, 2, student1, testat2);
        ul8 = new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma3, false, 3, student1, testat2);
        ul9 = new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma4, false, 1, student1, testat2);
        testat2.addUserloesung(ul6);
        testat2.addUserloesung(ul7);
        testat2.addUserloesung(ul8);
        testat2.addUserloesung(ul9);


        testatBearbeitung = new TestatBearbeitung(testat2, 15, student1, dozent2);
        student1.addErstellteLoesung(ul6);
        student1.addErstellteLoesung(ul7);
        student1.addErstellteLoesung(ul8);
        student1.addErstellteLoesung(ul9);
        student1.addBearbeitetesTestat(testatBearbeitung);
        testat2.addBearbeitung(testatBearbeitung);

        List<Boolean> ul10List = Arrays.asList(true, false, true, true);
        List<Boolean> ul11List = Arrays.asList(true, false, true, false);
        List<Boolean> ul12List = Arrays.asList(false, false, true, false);
        List<Boolean> ul13List = Arrays.asList(true, false, false, true);
        ul10 = new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma1, false, 4, student2, testat2);
        ul11 = new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma2, false, 2, student2, testat2);
        ul12 = new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma3, false, 3, student2, testat2);
        ul13 = new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma4, false, 1, student2, testat2);
        testat2.addUserloesung(ul10);
        testat2.addUserloesung(ul11);
        testat2.addUserloesung(ul12);
        testat2.addUserloesung(ul13);

        testatBearbeitung2 = new TestatBearbeitung(testat2);
        testatBearbeitung2.setTestatBearbeiter(student2);
        student2.addErstellteLoesung(ul10);
        student2.addErstellteLoesung(ul11);
        student2.addErstellteLoesung(ul12);
        student2.addErstellteLoesung(ul13);
        student2.addBearbeitetesTestat(testatBearbeitung2);

        userloesungsliste = Arrays.asList(ul1, ul2, ul3, ul4, ul5, ul6, ul7, ul8, ul9, ul10, ul11, ul12, ul13);
        userloesungsliste = new LinkedList<>(userloesungsliste);
        testatBearbeitungsliste = Arrays.asList(testatBearbeitung, testatBearbeitung2);
        testatBearbeitungsliste = new LinkedList<>(testatBearbeitungsliste);
    }

    private byte[] getRandomTestImage() {
        //int randomNumber = (int)(Math.random()*(max-min+1)+min);
        int randomNumber = (int) ((Math.random() * 4) + 1);
        File file = new File("C:\\newImages\\img" + randomNumber + ".png");
        byte[] byteArray = DatabaseService.convertFileToByteArray(file, null);
        return byteArray;
    }
}