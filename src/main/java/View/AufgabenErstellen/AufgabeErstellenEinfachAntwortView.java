package View.AufgabenErstellen;

import View.DozentAnsicht;
import entity.Designaufgabe;
import entity.EinfachantwortAufgabe;
import entity.Kategorie;
import entity.Schwierigkeitsgrad;
import persistence.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Die View zur Erstellung einer Einfachantwort Aufgabe
 *
 * @author Jannik Oehme
 * @version 05.05.2022
 *  @version 09.05.2022 Layout gefixed Funktionalität geadded schreibt passig in die Datenbank.
 */
public class AufgabeErstellenEinfachAntwortView implements ActionListener {
    //Frames
    JFrame AufgabeErstellenEinfachAntwortFrame;
    JPanel AufgabeErstellenEinfachAntwortViewPnl;
    //Panels
    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;
    //JComboboxen
    JComboBox kategorienCB;
    JComboBox schwierigkeitCB;
    //Layouts
    BorderLayout bl = new BorderLayout();
    GridLayout gl = new GridLayout(10,2);
    //Buttons
    JButton zurueckBtn;
    JButton speichernBtn;
    //Labels
    JLabel kategorieLbl;
    JLabel titelLbl;
    JLabel aufgabenTxtLbl;
    JLabel loesungsHinweisLbl;
    JLabel schwierigketiLbl;
    JLabel bearbeitungszeitLbl;
    JLabel punkteLbl;
    JLabel loesungLbl;
    //TextAreas
    JTextArea titelTA;
    JTextArea aufgabenTextTA;
    JTextArea loesungshinwTA;
    JTextArea schwierigkeitTA;
    JTextArea bearbeitungsZeitTA;
    JTextArea punkteTA;
    JTextArea loesungTA;

    public static void main(String[] args) {
        new AufgabeErstellenEinfachAntwortView();
    }

    AufgabeErstellenEinfachAntwortView(){

        AufgabeErstellenEinfachAntwortFrame = new JFrame("Einfach Antwort");
        AufgabeErstellenEinfachAntwortViewFuellen();
        AufgabeErstellenEinfachAntwortFrame.setSize(800, 800);
        AufgabeErstellenEinfachAntwortFrame.pack();
        AufgabeErstellenEinfachAntwortFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AufgabeErstellenEinfachAntwortFrame.setVisible(true);
    }
    private void AufgabeErstellenEinfachAntwortViewFuellen() {
        gl.setVgap(25);
        gl.setHgap(25);
        centerPnl = new JPanel(gl);
        northPnl = new JPanel();
        southPnl = new JPanel();
        centerPnl.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        AufgabeErstellenEinfachAntwortViewPnl = new JPanel();
        AufgabeErstellenEinfachAntwortViewPnl.setLayout(bl);
        //Buttons
        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        speichernBtn = new JButton("Speichern");
        speichernBtn.addActionListener(this);
        //Text Areas
        titelTA = new JTextArea();
        titelTA.setLineWrap(true);
        titelTA.setBounds(20,75,250,200);

        aufgabenTextTA = new JTextArea();
        aufgabenTextTA.setLineWrap(true);
        aufgabenTextTA.setBounds(20,75,250,200);

        loesungshinwTA = new JTextArea();
        loesungshinwTA.setBounds(20,75,250,200);
        loesungshinwTA.setLineWrap(true);

        bearbeitungsZeitTA = new JTextArea();
        bearbeitungsZeitTA.setBounds(20,75,250,200);
        bearbeitungsZeitTA.setLineWrap(true);

        punkteTA = new JTextArea();
        punkteTA.setBounds(20,75,250,200);
        punkteTA.setLineWrap(true);

        loesungTA = new JTextArea();
        loesungTA.setBounds(20,75,250,200);
        loesungTA.setLineWrap(true);
        //ComboBoxes
        Kategorie[] kat = {Kategorie.Java_Programmierung,Kategorie.Datenbanken,Kategorie.Software_Engineering,Kategorie.Java_Grundlagen,};
        kategorienCB = new JComboBox(kat);

        Schwierigkeitsgrad[] schw = {Schwierigkeitsgrad.Leicht,Schwierigkeitsgrad.Schwer,Schwierigkeitsgrad.Mittel};
        schwierigkeitCB = new JComboBox(schw);
        //Label
        kategorieLbl = new JLabel("Kategorie: ");
        titelLbl = new JLabel("Aufgaben Titel");
        loesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        loesungLbl= new JLabel("Lösung");
        aufgabenTxtLbl = new JLabel("Aufgaben Text");
        //Components Adden
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

        northPnl.add(zurueckBtn);
        southPnl.add(speichernBtn);

        AufgabeErstellenEinfachAntwortViewPnl.add(centerPnl,BorderLayout.CENTER);
        AufgabeErstellenEinfachAntwortViewPnl.add(northPnl,BorderLayout.NORTH);
        AufgabeErstellenEinfachAntwortViewPnl.add(southPnl,BorderLayout.SOUTH);
        AufgabeErstellenEinfachAntwortFrame.add(AufgabeErstellenEinfachAntwortViewPnl);
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
        AufgabeErstellenEinfachAntwortFrame.dispose();
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

        aufgTitel = titelTA.getText();
        aufText = aufgabenTextTA.getText();
        loesungshinweis = loesungshinwTA.getText();
        bearbeitungsZeit = Integer.parseInt(bearbeitungsZeitTA.getText());
        schw = (Schwierigkeitsgrad) schwierigkeitCB.getSelectedItem();
        kat = (Kategorie) kategorienCB.getSelectedItem();
        punkte = Integer.parseInt(punkteTA.getText());

        if (AufgabeErstellenStartView.inputcleaner(bearbeitungsZeit, punkte, AufgabeErstellenEinfachAntwortFrame)){
            createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte,kat,schw);
        }

        createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte,kat,schw);

        AufgabeErstellenEinfachAntwortFrame.dispose();
        DozentAnsicht.main(null);
    }

    private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte,Kategorie kat,Schwierigkeitsgrad schw) {

        DatabaseService ds = DatabaseService.getInstance();
        EinfachantwortAufgabe neueAufgabe = new EinfachantwortAufgabe(bearbeitungsZeit,null,null,kat, loesungshinweis, punkte,schw, aufText, aufgTitel,null,null);
        ds.persistObject(neueAufgabe);

    }
}
