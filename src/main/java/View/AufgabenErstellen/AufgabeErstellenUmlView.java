package View.AufgabenErstellen;

import View.DozentAnsicht;
import View.ImageFilter;
import entity.aufgabe.Designaufgabe;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import persistence.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Die View zur Erstellung einer UML Aufgabe
 *
 * @author Jannik Oehme
 * @version 05.05.2022
 *  @version 09.05.2022 Layout gefixed Funktionalität geadded schreibt passig in die Datenbank.
 */
public class AufgabeErstellenUmlView implements ActionListener {
    JFrame AufgabeErstellenUMLViewFrame;
    //Panels
    JPanel AufgabeErstellenUMLPnl;
    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;
    //Layouts
    BorderLayout bl = new BorderLayout();
    GridLayout gl = new GridLayout(10,2);
    //Buttons
    JButton zurueckBtn;
    JButton speichernBtn;
    JButton UMLHochladenBtn;
    JButton musterloesungBtn;
    //JComboboxen
    JComboBox kategorienCB;
    JComboBox schwierigkeitCB;
    //Labels
    JLabel titelLbl;
    JLabel aufgabenTxtLbl;
    JLabel loesungsHinweisLbl;
    JLabel schwierigketiLbl;
    JLabel kategorienLbl;
    JLabel bearbeitungszeitLbl;
    JLabel punkteLbl;
    //TextAreas
    JTextArea titelTA;
    JTextArea aufgabenTextTA;
    JTextArea loesungshinwTA;
    JTextArea bearbeitungsZeitTA;
    JTextArea punkteTA;
    //Files
    JFileChooser FC;
    File designFile;
    File loesungFile;

    public static void main(String[] args) {
        new AufgabeErstellenUmlView();
    }

    AufgabeErstellenUmlView(){
        AufgabeErstellenUMLViewFrame= new JFrame("Design Aufgabe Erstellen");
        AufgabeErstellenUMLViewFuellen();
        AufgabeErstellenUMLViewFrame.setSize(800, 800);
        AufgabeErstellenUMLViewFrame.pack();
        AufgabeErstellenUMLViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AufgabeErstellenUMLViewFrame.setVisible(true);
    }
    private void AufgabeErstellenUMLViewFuellen() {
        //Panels
        gl.setVgap(25);
        gl.setHgap(25);
        centerPnl = new JPanel(gl);
        northPnl = new JPanel();
        southPnl = new JPanel();
        centerPnl.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        AufgabeErstellenUMLPnl = new JPanel();
        AufgabeErstellenUMLPnl.setLayout(bl);
        //Buttons
        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        speichernBtn = new JButton("Speichern");
        speichernBtn.addActionListener(this);

        UMLHochladenBtn = new JButton("Design Hochladen");
        UMLHochladenBtn.addActionListener(this);

        musterloesungBtn = new JButton("Musterlösung Hochladen");
        musterloesungBtn.addActionListener(this);
        //TextAreas
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
        //ComboBoxes
        Kategorie[] kat = {Kategorie.Java_Programmierung,Kategorie.Datenbanken,Kategorie.Software_Engineering,Kategorie.Java_Grundlagen,};
        kategorienCB = new JComboBox(kat);

        Schwierigkeitsgrad [] schw = {Schwierigkeitsgrad.Leicht,Schwierigkeitsgrad.Schwer,Schwierigkeitsgrad.Mittel};
        schwierigkeitCB = new JComboBox(schw);

        //Labels
        kategorienLbl = new JLabel("Kategorie: ");
        titelLbl = new JLabel("Aufgaben Titel");
        loesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        aufgabenTxtLbl = new JLabel("Aufgaben Text");
        //ComponentsAdden
        centerPnl.add(titelLbl);
        centerPnl.add(titelTA);
        centerPnl.add(aufgabenTxtLbl);
        centerPnl.add(aufgabenTextTA);
        centerPnl.add(kategorienLbl);
        centerPnl.add(kategorienCB);
        centerPnl.add(UMLHochladenBtn);
        centerPnl.add(musterloesungBtn);
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

        AufgabeErstellenUMLPnl.add(centerPnl,BorderLayout.CENTER);
        AufgabeErstellenUMLPnl.add(northPnl,BorderLayout.NORTH);
        AufgabeErstellenUMLPnl.add(southPnl,BorderLayout.SOUTH);
        AufgabeErstellenUMLViewFrame.add(AufgabeErstellenUMLPnl);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckBtn) {
            zurueck();
        } else if (e.getSource() == this.speichernBtn) {
            speichern();
        }
        else if(e.getSource()== this.UMLHochladenBtn){
            designFile = UMLHochladen();
        }
        else if(e.getSource() == this.musterloesungBtn){
            loesungFile = loesungHochladen();
        }
    }

    private File loesungHochladen() {
        FC = new JFileChooser((String) null);
        FC.setAcceptAllFileFilterUsed(false);
        FC.setFileFilter(new ImageFilter());
        int returnVal = FC.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            designFile = FC.getSelectedFile();
            System.out.println(designFile.getName());
            return designFile;
        }
        return null;
    }

    private File UMLHochladen() {
        FC = new JFileChooser((String) null);
        FC.setAcceptAllFileFilterUsed(false);
        FC.setFileFilter(new ImageFilter());
        int returnVal = FC.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            designFile = FC.getSelectedFile();
            System.out.println(designFile.getName());
            return designFile;
        }
        return null;
    }
    private void zurueck() {
        AufgabeErstellenUMLViewFrame.dispose();
        //AufgabeErstellenStartView.main(null);
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


        if (AufgabeErstellenStartView.inputcleaner(bearbeitungsZeit, punkte, AufgabeErstellenUMLViewFrame)){
            createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte,kat,schw);
            AufgabeErstellenUMLViewFrame.dispose();

            //DozentAnsicht.main(null);
        }

    }

    private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte,Kategorie kat,Schwierigkeitsgrad schw) {

        DatabaseService ds = DatabaseService.getInstance();
        Designaufgabe neueAufgabe = new Designaufgabe(bearbeitungsZeit,null,"",kat, punkte,schw, aufText, aufgTitel,null,null);
        ds.persistObject(neueAufgabe);

    }
}