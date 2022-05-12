package View.AufgabenErstellen;

import View.DozentAnsicht;
import entity.*;
import entity.aufgabe.MultipleChoiceAufgabe;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import persistence.DatabaseService;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Die View zur Erstellung einer Multiple Choice Aufgabe
 *
 * @author Jannik Oehme
 * @version 05.05.2022
 *  @version 09.05.2022 Layout gefixed Funktionalität geadded schreibt passig in die Datenbank.
 */
public class AufgabeErstellenMultipleChoiceView implements ActionListener {

    JFrame AufgabeErstellenMultipleChoiceViewFrame;
    //Panels
    JPanel AufgabeErstellenMultipleChoicePnl;
    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;
    //Layouts
    BorderLayout bl = new BorderLayout();
    GridLayout gl = new GridLayout(13,1);
    //JComboBoxen
    JComboBox schwierigkeitCB;
    JComboBox kategorienCB;
    //Buttons
    JButton zurueckBtn;
    JButton speichernBtn;
    //Labels
    JLabel kategorieLbl;
    JLabel schwierigkeitLbl;
    JLabel titelLbl;
    JLabel aufgabenTxtLbl;
    JLabel loesungsHinweisLbl;
    JLabel schwierigketiLbl;
    JLabel bearbeitungszeitLbl;
    JLabel punkteLbl;
    JLabel loesungLbl;
    JLabel antwort1Lbl;
    JLabel antwort2Lbl;
    JLabel antwort3Lbl;
    JLabel antwort4Lbl;
    //TextAreas
    JTextArea titelTA;
    JTextArea aufgabenTextTA;
    JTextArea loesungshinwTA;
    JTextArea schwierigkeitTA;
    JTextArea bearbeitungsZeitTA;
    JTextArea punkteTA;
    JTextArea loesungTA;
    JTextArea antwort1TA;
    JTextArea antwort2TA;
    JTextArea antwort3TA;
    JTextArea antwort4TA;

    public static void main(String[] args) {
        new AufgabeErstellenMultipleChoiceView();
    }
    AufgabeErstellenMultipleChoiceView(){
        AufgabeErstellenMultipleChoiceViewFrame = new JFrame("MultipleChoice");
        AufgabeErstellenEinfachAntwortViewFuellen();
        AufgabeErstellenMultipleChoiceViewFrame.setSize(800, 800);
        AufgabeErstellenMultipleChoiceViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AufgabeErstellenMultipleChoiceViewFrame.pack();
        AufgabeErstellenMultipleChoiceViewFrame.setVisible(true);
    }
    private void AufgabeErstellenEinfachAntwortViewFuellen() {
        //Panels
        gl.setVgap(10);
        gl.setHgap(25);
        centerPnl = new JPanel(gl);
        centerPnl.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        northPnl = new JPanel();
        southPnl = new JPanel();
        AufgabeErstellenMultipleChoicePnl = new JPanel();
        AufgabeErstellenMultipleChoicePnl.setLayout(bl);
        //JComboboxen
        Kategorie[] kat = {Kategorie.Java_Programmierung,Kategorie.Datenbanken,Kategorie.Software_Engineering,Kategorie.Java_Grundlagen,};
        kategorienCB = new JComboBox(kat);

        Schwierigkeitsgrad[] schw = {Schwierigkeitsgrad.Leicht,Schwierigkeitsgrad.Schwer,Schwierigkeitsgrad.Mittel};
        schwierigkeitCB = new JComboBox(schw);
        //Buttons
        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        speichernBtn = new JButton("Speichern");
        speichernBtn.addActionListener(this);
        //TextAreas
        titelTA = new JTextArea();
        titelTA.setLineWrap(true);

        aufgabenTextTA = new JTextArea();
        aufgabenTextTA.setLineWrap(true);

        loesungshinwTA = new JTextArea();
        loesungshinwTA.setLineWrap(true);

        schwierigkeitTA = new JTextArea();
        schwierigkeitTA.setLineWrap(true);


        bearbeitungsZeitTA = new JTextArea();
        bearbeitungsZeitTA.setLineWrap(true);

        punkteTA = new JTextArea();

        punkteTA.setLineWrap(true);

        loesungTA = new JTextArea();

        loesungTA.setLineWrap(true);

        antwort1TA = new JTextArea();

        antwort1TA.setLineWrap(true);

        antwort2TA = new JTextArea();
        antwort2TA.setLineWrap(true);

        antwort3TA = new JTextArea();
        antwort3TA.setLineWrap(true);

        antwort4TA = new JTextArea();
        antwort4TA.setLineWrap(true);
        //Label
        schwierigkeitLbl = new JLabel("Schwierigkeit");
        kategorieLbl = new JLabel("Kategorien");
        titelLbl = new JLabel("Aufgaben Titel");
        loesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        loesungLbl= new JLabel("Lösung");
        aufgabenTxtLbl = new JLabel("Aufgaben Text: ");
        antwort1Lbl = new JLabel("Antwort 1:");
        antwort2Lbl = new JLabel("Antwort 2:");
        antwort3Lbl = new JLabel("Antwort 3:");
        antwort4Lbl = new JLabel("Antwort 4:");
        //ComponentsAdden
        centerPnl.add(titelLbl);
        centerPnl.add(titelTA);
        centerPnl.add(aufgabenTxtLbl);
        centerPnl.add(aufgabenTextTA);
        centerPnl.add(loesungLbl);
        centerPnl.add(loesungTA);
        centerPnl.add(schwierigketiLbl);
        centerPnl.add(schwierigkeitCB);
        centerPnl.add(kategorieLbl);
        centerPnl.add(kategorienCB);
        centerPnl.add(bearbeitungszeitLbl);
        centerPnl.add(bearbeitungsZeitTA);
        centerPnl.add(punkteLbl);
        centerPnl.add(punkteTA);
        centerPnl.add(loesungsHinweisLbl);
        centerPnl.add(loesungshinwTA);
        centerPnl.add(antwort1Lbl);
        centerPnl.add(antwort1TA);
        centerPnl.add(antwort2Lbl);
        centerPnl.add(antwort2TA);
        centerPnl.add(antwort3Lbl);
        centerPnl.add(antwort3TA);
        centerPnl.add(antwort4Lbl);
        centerPnl.add(antwort4TA);

        northPnl.add(zurueckBtn);
        southPnl.add(speichernBtn);

        AufgabeErstellenMultipleChoicePnl.add(centerPnl,BorderLayout.CENTER);
        AufgabeErstellenMultipleChoicePnl.add(northPnl,BorderLayout.NORTH);
        AufgabeErstellenMultipleChoicePnl.add(southPnl,BorderLayout.SOUTH);
        AufgabeErstellenMultipleChoiceViewFrame.add(AufgabeErstellenMultipleChoicePnl);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckBtn) {
            zurueck();
        } else if (e.getSource() == this.speichernBtn) {
            speichern();
        }
    }

    private void zurueck() {
        AufgabeErstellenMultipleChoiceViewFrame.dispose();
        AufgabeErstellenStartView.main(null);
    }
    private void speichern() {
        String aufgTitel;
        String aufText;
        String loesungshinweis;
        int bearbeitungsZeit;
        int punkte;
        Kategorie kat;
        Schwierigkeitsgrad schw;
        ArrayList<String> liste = new ArrayList<String>();
        liste.add(antwort1TA.getText());
        liste.add(antwort2TA.getText());
        liste.add(antwort3TA.getText());
        liste.add(antwort4TA.getText());

        aufgTitel = titelTA.getText();
        aufText = aufgabenTextTA.getText();
        loesungshinweis = loesungshinwTA.getText();
        bearbeitungsZeit = Integer.parseInt(bearbeitungsZeitTA.getText());
        schw = (Schwierigkeitsgrad) schwierigkeitCB.getSelectedItem();
        kat = (Kategorie) kategorienCB.getSelectedItem();
        punkte = Integer.parseInt(punkteTA.getText());

        if (AufgabeErstellenStartView.inputcleaner(bearbeitungsZeit, punkte, AufgabeErstellenMultipleChoiceViewFrame)){

            createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte,kat,schw,liste);

        }



        AufgabeErstellenMultipleChoiceViewFrame.dispose();
        DozentAnsicht.main(null);
    }

    private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte, Kategorie kat, Schwierigkeitsgrad schw, ArrayList<String> antworten) {

        DatabaseService ds = DatabaseService.getInstance();
        MultipleChoiceAufgabe neueAufgabe = new MultipleChoiceAufgabe(bearbeitungsZeit,null,null,kat, punkte,schw, aufText, aufgTitel,null,null);
        ds.persistObject(neueAufgabe);

    }
}
