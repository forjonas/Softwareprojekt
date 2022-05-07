package View.AufgabenErstellen;

import View.DozentAnsicht;
import View.ImageFilter;
import entity.Kategorie;
import entity.Programmieraufgabe;
import entity.Schwierigkeitsgrad;
import persistence.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;


/**
 * Die View zur Erstellung einer Code Aufgabe
 *
 * @author Jannik Oehme
 * @version 05.05.2022
 */
public class AufgabeErstellenCodeView implements ActionListener {
    JFrame AufgabeErstellenCodeViewFrame;

    //Panels
    JPanel AufgabeErstellenCodePnl;
    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;
    //Layouts
    BorderLayout bl = new BorderLayout();
    //Buttons
    JButton zurueckBtn;
    JButton speichernBtn;
    JButton codeHochladenBtn;
    JButton musterloesungHochladenBtn;
    //JComboBoxen
    JComboBox kategorienCB;
    JComboBox schwierigkeitCB;
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
    JTextArea bearbeitungsZeitTA;
    JTextArea punkteTA;
    //Filechooser
    JFileChooser FCC;
    File codeFile;
    File loesungFile;

    public static void main(String[] args) {
        new AufgabeErstellenCodeView();
    }

    AufgabeErstellenCodeView(){
         AufgabeErstellenCodeViewFrame= new JFrame("MultipleChoice");
        AufgabeErstellenCodeViewFuellen();
        AufgabeErstellenCodeViewFrame.pack();
        AufgabeErstellenCodeViewFrame.setSize(500, 500);
        AufgabeErstellenCodeViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AufgabeErstellenCodeViewFrame.setVisible(true);
    }
    private void AufgabeErstellenCodeViewFuellen() {
        centerPnl = new JPanel();
        northPnl = new JPanel();
        southPnl = new JPanel();
        AufgabeErstellenCodePnl = new JPanel();
        AufgabeErstellenCodePnl.setLayout(bl);

        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        speichernBtn = new JButton("Speichern");
        speichernBtn.addActionListener(this);

        codeHochladenBtn = new JButton("Code Hochladen");
        codeHochladenBtn.addActionListener(this);

        musterloesungHochladenBtn = new JButton("Musterlösung Hochladen");
        musterloesungHochladenBtn.addActionListener(this);

        titelTA = new JTextArea();
        titelTA.setLineWrap(true);
        titelTA.setBounds(20,75,250,200);

        //ComboBoxes
        Kategorie[] kat = {Kategorie.Java_Programmierung,Kategorie.Datenbanken,Kategorie.Software_Engineering,Kategorie.Java_Grundlagen,};
        kategorienCB = new JComboBox(kat);

        Schwierigkeitsgrad [] schw = {Schwierigkeitsgrad.Leicht,Schwierigkeitsgrad.Schwer,Schwierigkeitsgrad.Mittel};
        schwierigkeitCB = new JComboBox(schw);

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

        kategorieLbl = new JLabel("Kategorien");
        titelLbl = new JLabel("Aufgaben Titel");
        loesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        loesungLbl= new JLabel("Lösung");
        aufgabenTxtLbl = new JLabel("Aufgaben Text");

        centerPnl.add(titelLbl);
        centerPnl.add(titelTA);
        centerPnl.add(aufgabenTxtLbl);
        centerPnl.add(aufgabenTextTA);
        centerPnl.add(loesungLbl);
        centerPnl.add(musterloesungHochladenBtn);
        centerPnl.add(codeHochladenBtn);
        centerPnl.add(musterloesungHochladenBtn);
        centerPnl.add(kategorieLbl);
        centerPnl.add(kategorienCB);
        centerPnl.add(schwierigketiLbl);
        centerPnl.add(schwierigkeitCB);
        centerPnl.add(bearbeitungszeitLbl);
        centerPnl.add(bearbeitungsZeitTA);
        centerPnl.add(punkteLbl);
        centerPnl.add(punkteTA);
        centerPnl.add(loesungsHinweisLbl);
        centerPnl.add(loesungshinwTA);

        northPnl.add(zurueckBtn);
        southPnl.add(speichernBtn);

        AufgabeErstellenCodePnl.add(centerPnl,BorderLayout.CENTER);
        AufgabeErstellenCodePnl.add(northPnl,BorderLayout.NORTH);
        AufgabeErstellenCodePnl.add(southPnl,BorderLayout.SOUTH);
        AufgabeErstellenCodeViewFrame.add(AufgabeErstellenCodePnl);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckBtn) {
            zurueck();
        } else if (e.getSource() == this.speichernBtn) {
            speichern();
        }
        else if(e.getSource()== this.codeHochladenBtn){
            codeFile = codeHochladen();
        }
        else if(e.getSource() == this.musterloesungHochladenBtn){
            loesungFile= loesungHochladen();
        }
    }
    private File codeHochladen() {
        FCC = new JFileChooser((String) null);
        FCC.setAcceptAllFileFilterUsed(false);
        FCC.setFileFilter(new ImageFilter());
        int returnVal = FCC.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            codeFile = FCC.getSelectedFile();
            System.out.println(codeFile.getName());
            return codeFile;
        }
        return null;
    }
    private File loesungHochladen() {
        FCC = new JFileChooser((String) null);
        FCC.setAcceptAllFileFilterUsed(false);
        FCC.setFileFilter(new ImageFilter());
        int returnVal = FCC.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            loesungFile = FCC.getSelectedFile();
            System.out.println(loesungFile.getName());
            return loesungFile;
        }
        return null;
    }
    private void zurueck() {
        AufgabeErstellenCodeViewFrame.dispose();
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

            createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte,kat,schw,loesungFile,codeFile);

            AufgabeErstellenCodeViewFrame.dispose();
            DozentAnsicht.main(null);
    }

        private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte, Kategorie kat, Schwierigkeitsgrad schw,File loesungFile,File codeFile) {

            DatabaseService ds = DatabaseService.getInstance();
            Programmieraufgabe neueAufgabe = new Programmieraufgabe(bearbeitungsZeit,codeFile,null,kat, loesungshinweis, punkte,schw, aufText, aufgTitel,loesungFile,null);
            ds.persistObject(neueAufgabe);

        }
    }
