package persistence;


import View.LoginView;
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
import entity.loesung.userloesung.Userloesung;
import entity.loesung.userloesung.UserloesungEinfachantwort;
import entity.loesung.userloesung.UserloesungMultipleChoiceAufgabe;
import entity.loesung.userloesung.UserloesungProgrammieraufgabe;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CreateBetaData {

    DatabaseService ds = DatabaseService.getInstance();

    /**
     * Beta-Data Attribute
     *
     * Dozenten, Studenten, Testate, Trainings, Musterlösungen, Aufgaben, ...
     */
    private Student student1;
    private Student student2;
    private Student student3;
    private Student student4;

    private Dozent dozent1;
    private Dozent dozent2;
    private Dozent dozent3;

    private Designaufgabe da1, da2, da3, da4;
    private Programmieraufgabe pa1, pa2, pa3, pa4;
    private MultipleChoiceAufgabe ma1, ma2, ma3, ma4;
    private EinfachantwortAufgabe ea1, ea2, ea3, ea4;

    private MusterloesungDesignaufgabe mda1, mda2, mda3, mda4;
    private MusterloesungProgrammieraufgabe mpa1, mpa2, mpa3, mpa4;
    private MusterloesungMultipleChoiceAufgabe mma1, mma2, mma3, mma4;
    private MusterloesungEinfachantwort mea1, mea2, mea3, mea4;

    private Userloesung ul1,ul2,ul3,ul4,ul5,ul6,ul7,ul8,ul9;

    private TestatBearbeitung testatBearbeitung;

    private File fmda1, fmda2, fmda3, fmda4;
    private Testat testat1, testat2, testat3;
    private Training training1, training2,training3;

    public static void main(String[] args)
    {
        new CreateBetaData();
    }

    public CreateBetaData(){
        try {
            createBetaUser();
            createBetaAufgaben();
            createBetaMusterloesung();
            createBetaAufgabensammlungen();
            createBetaUserLösungen();

        }catch (Exception e){}

        ds.persistObject(dozent1);
        ds.persistObject(dozent2);
        ds.persistObject(student1);
        ds.persistObject(student2);
        ds.persistObject(student3);
        ds.persistObject(student4);
        ds.persistObject(dozent3);


        new LoginView();
    }

    public void createBetaUser() throws Exception {
        student1 = new Student("mmustermann", "123", "Max", "Mustermann", 123678);
        student2 = new Student("vvogel", "1234", "Verena", "Vogel", 234789);
        student3 = new Student("eengel", "234", "Erik", "Engel", 345890);
        student4 = new Student("eEngel", "345", "Erina", "Engel", 345786);

        dozent1 = new Dozent("tJoswig", "64bc9", "Timo", "Joswig");
        dozent2 = new Dozent("kkubisch", "987caf6", "Kristin", "Kubisch");

        dozent3 = new Dozent("admin","000","Admin","");
        dozent3.setBerechtigungsstufe(3);//->übergibt Adminrechte an diesen Dozenten
    }


    public void createBetaAufgaben() throws Exception {
        da1 = new Designaufgabe(7, "Programmcode", Kategorie.Klassendiagramme, 15, Schwierigkeitsgrad.Leicht, "Designen Sie die passende UML-Struktur für den Programmcode", "Programmcode zu UML-Design", dozent1);
        da2 = new Designaufgabe(15, null, Kategorie.Datenbanken, 20, Schwierigkeitsgrad.Mittel, "Designen Sie das UML-Diagramm zu dieser Datenbankstruktur", "UML-Designaufgabe", dozent1);
        da3 = new Designaufgabe(20, null, Kategorie.Software_Engineering, 25, Schwierigkeitsgrad.Schwer, "Stellen Sie die unterschiedlichen Architekturprinzipien dar.", "Architekturprinzipien", dozent2);
        da4 = new Designaufgabe(10, null, Kategorie.Software_Engineering, 30, Schwierigkeitsgrad.Schwer, "Stellen Sie die 3-Schichten-Architektur dar und beschriften Sie diese.", "3-Schichten-Architektur", dozent2);

        pa1 = new Programmieraufgabe(15, "UML-Design", Kategorie.Java_Programmierung, 20, Schwierigkeitsgrad.Mittel, "Stellen Sie die Beziehungen aus dem UML-Design in Java dar.", "Beziehungen Programmieren", dozent2);
        pa2 = new Programmieraufgabe(10, null, Kategorie.Java_Grundlagen, 10, Schwierigkeitsgrad.Leicht, "Erstellen Sie eine Methode, die in der Konsole einen Tannenbaum aus Sternen(*) ausgibt.", "Baum erstellen.", dozent1);
        pa3 = new Programmieraufgabe(20, "Programmcode", Kategorie.Datenbanken, 35, Schwierigkeitsgrad.Schwer, "Bearbeiten Sie den Programmcode so, dass die Entitäten in der Datenbank persistiert werden.", "Datenbankpersistenz", dozent1);
        pa4 = new Programmieraufgabe(15, null, Kategorie.Java_Programmierung, 25, Schwierigkeitsgrad.Mittel, "Erstellen Sie eine Methode, die die ersten 100 Primzahlen auf der Konsole ausgibt", "Primzahlenausgabe", dozent2);

        List<String> lma1 = Arrays.asList("int", "String", "char", "boolean");
        List<String> lma2 = Arrays.asList("int", "String", "char", "boolean");
        List<String> lma3 = Arrays.asList("Assoziation", "Aggregation", "Komposition", "Kardinalität");
        List<String> lma4 = Arrays.asList("break", "stop", "return", "finish");

        ma1 = new MultipleChoiceAufgabe(5, null, Kategorie.Java_Grundlagen, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Wahrheitswerte geeignet", "Datentypabfrage", dozent1, lma1);
        ma2 = new MultipleChoiceAufgabe(5, null, Kategorie.Java_Grundlagen, 5, Schwierigkeitsgrad.Leicht, "Welche der Datentypen ist kein primitiver Datentyp?", "Primitiver Datentyp", dozent2, lma2);
        ma3 = new MultipleChoiceAufgabe(5, null, Kategorie.Klassendiagramme, 15, Schwierigkeitsgrad.Mittel, "welches ist keine Art der Beziehung in einem UML-Diagramm", "Beziehungen in UML", dozent1, lma3);
        ma4 = new MultipleChoiceAufgabe(5, null, Kategorie.Java_Programmierung, 20, Schwierigkeitsgrad.Mittel, "Wie beendet man vorläufig eine Switch-Case-Anweisung?", "Switch-Case-Beenden", dozent2, lma4);

        ea1 = new EinfachantwortAufgabe(7, "UML-Design", Kategorie.Datenbanken, 25, Schwierigkeitsgrad.Schwer, "Begründen Sie warum für diese Beziehung eine Komposition und nicht eine Aggregation gewählt wurde.", "BeziehungsbegründungDB", dozent2);
        ea2 = new EinfachantwortAufgabe(15, "Programmcode", Kategorie.Software_Engineering, 30, Schwierigkeitsgrad.Schwer, "Erläutern Sie, warum die Methode berrechneVortex() 'null' als return-Wert setzt.", "MethodenErläuterung", dozent2);
        ea3 = new EinfachantwortAufgabe(20, "Programmcode", Kategorie.Java_Programmierung, 20, Schwierigkeitsgrad.Mittel, "Erläutern Sie, warum diese Methode kein Objekt der Klasse Auto erstellen kann.", "MethodenErläuterung2", dozent1);
        ea4 = new EinfachantwortAufgabe(15, "Programmcode", Kategorie.Java_Programmierung, 15, Schwierigkeitsgrad.Leicht, "Erläuteren Sie den Programmcode und warum ein Objekt der Klasse Fahrrad erstellt wird", "Programmcodeweiterführung", dozent1);

        dozent1.addErstellteAufgabe(ea4);
        dozent1.addErstellteAufgabe(ea3);
        dozent1.addErstellteAufgabe(ma3);
        dozent1.addErstellteAufgabe(ma1);
        dozent1.addErstellteAufgabe(pa3);
        dozent1.addErstellteAufgabe(da4);
        dozent1.addErstellteAufgabe(da2);
        dozent1.addErstellteAufgabe(da1);
        dozent1.addErstellteAufgabe(pa2);

        dozent2.addErstellteAufgabe(da3);
        dozent2.addErstellteAufgabe(pa1);
        dozent2.addErstellteAufgabe(ea2);
        dozent2.addErstellteAufgabe(ea1);
        dozent2.addErstellteAufgabe(ma4);
        dozent2.addErstellteAufgabe(ma2);
        dozent2.addErstellteAufgabe(pa3);

        //List<Aufgabe> aufgaben=Arrays.asList(da1,da2,da3,da4,pa1,pa2,pa3,pa4,ma1,ma2,ma3,ma4,ea1,ea2,ea3,ea4);
        //ds.persistObjects(aufgaben);
    }

    public void createBetaMusterloesung() throws Exception {
        mda1 = new MusterloesungDesignaufgabe(da1, "Beziehungen wie Aggregation und Komposition sind hier nicht gefragt.", fmda1);
        mda2 = new MusterloesungDesignaufgabe(da2, "Beziehungen wie Aggregation und Komposition sind hier nicht gefragt.", fmda2);
        mda3 = new MusterloesungDesignaufgabe(da3, "Es gibt 7 unterschiedliche Architekturprinzipien.", fmda3);
        mda4 = new MusterloesungDesignaufgabe(da1, null, fmda4);
        da1.setMusterloesung(mda1);
        da2.setMusterloesung(mda2);
        da3.setMusterloesung(mda3);
        da4.setMusterloesung(mda4);

        mpa1 = new MusterloesungProgrammieraufgabe(pa1, "Beziehungen lassen sich durch Attribute darstellen", "Hier steht die Lösung, als Programmcode - Bsplw. Aufgabe aufgabe = new Aufgabe()");
        mpa2 = new MusterloesungProgrammieraufgabe(pa2, "Dies lässt sich durch eine Schleife vereinfachen,", "Siehe Lösung");
        mpa3=new MusterloesungProgrammieraufgabe(pa3,"Zum Persistieren braucht man eine DatabaseService-Klasse","Siehe Lösung");
        mpa4=new MusterloesungProgrammieraufgabe(pa4,"Primzahlen sind nur durch 1 und sich selber teilbar","Siehe Lösung");
        pa1.setMusterloesung(mpa1);
        pa2.setMusterloesung(mpa2);
        pa3.setMusterloesung(mpa3);
        pa4.setMusterloesung(mpa4);

        List<Boolean> lmma1= Arrays.asList(false,false,false,true);
        List<Boolean> lmma2= Arrays.asList(false,true,false,false);
        List<Boolean> lmma3= Arrays.asList(false,false,false,true);
        List<Boolean> lmma4= Arrays.asList(true,false,true,false);

        mma1=new MusterloesungMultipleChoiceAufgabe(ma1,null,lmma1);
        mma2=new MusterloesungMultipleChoiceAufgabe(ma2,"Nicht primitive Datentypen sind bspwl. Klassen",lmma2);
        mma3=new MusterloesungMultipleChoiceAufgabe(ma3,"Hier steht ein Lösungshinweis",lmma3);
        mma4=new MusterloesungMultipleChoiceAufgabe(ma4,"Die Schwitch-Case-Anweisung lässt sich auch mit dem beenden der Methode beenden.",lmma4);
        ma1.setMusterloesung(mma1);
        ma2.setMusterloesung(mma2);
        ma3.setMusterloesung(mma3);
        ma4.setMusterloesung(mma4);

        mea1=new MusterloesungEinfachantwort(ea1,"Hier steht der Unterschied zwischen Aggregation und Komposition.","Hier sthet die Begründung");
        mea2=new MusterloesungEinfachantwort(ea2,"Erklärung eines Return-Wertes","Begründung für 'null'.");
        mea3=new MusterloesungEinfachantwort(ea3,"Um Objekte erstellen zu können muss man Sie kennen","Die Methode/Klasse sthet in keiner Weise mit der Klasse Auto in verbindung");
        mea4=new MusterloesungEinfachantwort(ea4,"Um ein Objekt zu erstellen muss die Klasse/Methode diese Objekt kennen.","Erläuterung");
        ea1.setMusterloesung(mea1);
        ea2.setMusterloesung(mea2);
        ea3.setMusterloesung(mea3);
        ea4.setMusterloesung(mea4);

        //List<Musterloesung>musterlösung=Arrays.asList(mda1,mda2,mda3,mda4,mpa1,mpa2,mpa3,mpa4,mma1,mma2,mma3,mma4,mea1,mea2,mea3,mea4);
        //ds.persistObjects(musterlösung);
    }

    public void createBetaAufgabensammlungen() throws Exception
    {
        List<Aufgabe> aufgabenliste1=Arrays.asList(da1,da4,ea3,pa4,pa1,pa2,da2);
        List<Aufgabe> aufgabenliste2=Arrays.asList(da2,da4,ma3,ma4,ma1,pa3,da3);
        List<Aufgabe> aufgabenliste3=Arrays.asList(ma1,ma2,ma3,ma4);
        List<Aufgabe> aufgabenliste4=Arrays.asList(ea1,ma1,da1,pa1);
        List<Aufgabe> aufgabenliste5=Arrays.asList(ea3,ea4,ma4,pa4,pa1);

        testat1=new Testat(aufgabenliste1,"123","Testat für Anfänger",dozent1);dozent1.addErstelltesTestat(testat1);
        testat2=new Testat(aufgabenliste3,"456","Testat nur MultipleChoice-Aufgaben",dozent2);dozent2.addErstelltesTestat(testat2);
        testat3=new Testat(aufgabenliste2,"passwort","Frühlingstestat",dozent1);dozent1.addErstelltesTestat(testat3);

        training1=new Training(aufgabenliste5,50,Kategorie.Java_Programmierung,Schwierigkeitsgrad.Mittel,student2);student2.addBearbeitetesTraining(training1);
        training2=new Training(aufgabenliste4,30,Kategorie.Java_Grundlagen,Schwierigkeitsgrad.Mittel,student4);student4.addBearbeitetesTraining(training2);
        training3=new Training(aufgabenliste1,90,Kategorie.Java_Grundlagen,Schwierigkeitsgrad.Mittel,student3);student3.addBearbeitetesTraining(training3);


        //List<Aufgabensammlung> aufgabensammlung=Arrays.asList(testat1,testat2,testat3,training1,training3);
        //ds.persistObjects(aufgabensammlung);
    }

    public void createBetaUserLösungen() throws Exception
    {
        ul1=new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe)ma4,false,Arrays.asList(false,false,false,true),student3,training3);
        ul2=new UserloesungEinfachantwort((EinfachantwortAufgabe)ea3,false,"Erklärung",student3,training3);
        ul3=new UserloesungEinfachantwort((EinfachantwortAufgabe)ea4,false,"System.out.println()",student3,training3);
        ul4=new UserloesungProgrammieraufgabe((Programmieraufgabe)pa1,true,"Code",student3,training3);
        ul5=new UserloesungProgrammieraufgabe((Programmieraufgabe)pa4,true,"Code",student3,training3);

        student3.addErstellteLoesung(ul1);
        student3.addErstellteLoesung(ul2);
        student3.addErstellteLoesung(ul3);
        student3.addErstellteLoesung(ul4);
        student3.addErstellteLoesung(ul5);

        training3.addUserloesung(ul1);
        training3.addUserloesung(ul2);
        training3.addUserloesung(ul3);
        training3.addUserloesung(ul4);
        training3.addUserloesung(ul5);

        List<Boolean> ul6List=Arrays.asList(true,false,false,true);
        List<Boolean> ul7List=Arrays.asList(true,true,true,false);
        List<Boolean> ul8List=Arrays.asList(false,false,false,true);
        List<Boolean> ul9List=Arrays.asList(true,false,false,true);

        ul6=new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma1,false,ul6List,student1,testat2);
        ul7=new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma1,false,ul7List,student1,testat2);
        ul8=new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma1,false,ul8List,student1,testat2);
        ul9=new UserloesungMultipleChoiceAufgabe((MultipleChoiceAufgabe) ma1,false,ul9List,student1,testat2);

        testat2.addUserloesung(ul6);
        testat2.addUserloesung(ul7);
        testat2.addUserloesung(ul8);
        testat2.addUserloesung(ul9);

        testatBearbeitung=new TestatBearbeitung(testat2,15,student1,dozent2);

        testat2.addBearbeitung(testatBearbeitung);

        student1.addErstellteLoesung(ul6);
        student1.addErstellteLoesung(ul7);
        student1.addErstellteLoesung(ul8);
        student1.addErstellteLoesung(ul9);
    }
}