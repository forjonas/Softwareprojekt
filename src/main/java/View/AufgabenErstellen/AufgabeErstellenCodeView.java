package View.AufgabenErstellen;

import View.DozentAnsicht;
import entity.benutzer.Dozent;
import entity.enums.Kategorie;
import entity.aufgabe.Programmieraufgabe;
import entity.enums.Schwierigkeitsgrad;
import persistence.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Die View zur Erstellung einer Code Aufgabe
 *
 * @author Jannik Oehme
 * @version 05.05.2022
 *  @version 09.05.2022 Layout gefixed funktionalität geadded schreibt passig in die Datenbank.
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
    GridLayout gl = new GridLayout(10,2);
    //Buttons
    JButton zurueckBtn;
    JButton speichernBtn;
    //JComboBoxen
    JComboBox kategorienCB;
    JComboBox schwierigkeitCB;
    //Labels
    JLabel codeBeispiel;
    JLabel kategorieLbl;
    JLabel titelLbl;
    JLabel aufgabenTxtLbl;
    JLabel loesungsHinweisLbl;
    JLabel schwierigketiLbl;
    JLabel bearbeitungszeitLbl;
    JLabel punkteLbl;
    JLabel loesungLbl;
    //TextAreas
    JTextArea codeTA;
    JTextArea titelTA;
    JTextArea aufgabenTextTA;
    JTextArea loesungshinwTA;
    JTextArea bearbeitungsZeitTA;
    JTextArea punkteTA;
    JTextArea loesungTA;

    public static void main(String[] args) {
        new AufgabeErstellenCodeView();
    }

    AufgabeErstellenCodeView(){
         AufgabeErstellenCodeViewFrame= new JFrame("ProgrammierAufgabe");
        AufgabeErstellenCodeViewFuellen();
        AufgabeErstellenCodeViewFrame.setSize(800, 800);
        AufgabeErstellenCodeViewFrame.pack();
        AufgabeErstellenCodeViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AufgabeErstellenCodeViewFrame.setVisible(true);
    }
    private void AufgabeErstellenCodeViewFuellen() {
        gl.setVgap(25);
        gl.setHgap(25);
        centerPnl = new JPanel(gl);
        northPnl = new JPanel();
        southPnl = new JPanel();
        AufgabeErstellenCodePnl = new JPanel();
        AufgabeErstellenCodePnl.setLayout(bl);
        centerPnl.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        speichernBtn = new JButton("Speichern");
        speichernBtn.addActionListener(this);

        titelTA = new JTextArea();
        titelTA.setLineWrap(true);
        titelTA.setBounds(20,75,250,200);

        //ComboBoxes
        Kategorie[] kat = {Kategorie.Java_Programmierung,Kategorie.Datenbanken,Kategorie.Software_Engineering,Kategorie.Java_Grundlagen,};
        kategorienCB = new JComboBox(kat);

        Schwierigkeitsgrad [] schw = {Schwierigkeitsgrad.Leicht,Schwierigkeitsgrad.Schwer,Schwierigkeitsgrad.Mittel};
        schwierigkeitCB = new JComboBox(schw);

        aufgabenTextTA = new JTextArea(50,50);
        aufgabenTextTA.setLineWrap(true);

        loesungshinwTA = new JTextArea();
        loesungshinwTA.setLineWrap(true);

        bearbeitungsZeitTA = new JTextArea();
        bearbeitungsZeitTA.setBounds(20,75,250,200);
        bearbeitungsZeitTA.setLineWrap(true);

        punkteTA = new JTextArea();
        punkteTA.setBounds(20,75,250,200);
        punkteTA.setLineWrap(true);

        codeTA = new JTextArea();
        codeTA.setBounds(20,75,250,200);
        codeTA.setLineWrap(true);

        loesungTA = new JTextArea();
        loesungTA.setBounds(20,75,250,200);
        loesungTA.setLineWrap(true);

        codeBeispiel = new JLabel("Code Beispiel: ");
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
        centerPnl.add(loesungTA);
        centerPnl.add(codeBeispiel);
        centerPnl.add(codeTA);
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
            String codeText;
            String loesung;

            aufgTitel = titelTA.getText();
            aufText = aufgabenTextTA.getText();
            loesungshinweis = loesungshinwTA.getText();
            bearbeitungsZeit = Integer.parseInt(bearbeitungsZeitTA.getText());
            schw = (Schwierigkeitsgrad) schwierigkeitCB.getSelectedItem();
            kat = (Kategorie) kategorienCB.getSelectedItem();
            punkte = Integer.parseInt(punkteTA.getText());
            codeText = codeTA.getText();
            loesung = loesungTA.getText();

            if (AufgabeErstellenStartView.inputcleaner(bearbeitungsZeit, punkte, AufgabeErstellenCodeViewFrame)){

                createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte,kat,schw,loesung,codeText);

            }

            AufgabeErstellenCodeViewFrame.dispose();
            DozentAnsicht.main(null);
    }

        private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte, Kategorie kat, Schwierigkeitsgrad schw,String loesung, String codeText) {

            DatabaseService ds = DatabaseService.getInstance();
            Programmieraufgabe neueAufgabe = new Programmieraufgabe(bearbeitungsZeit,codeText,null,kat, punkte,schw, aufText, aufgTitel,new Dozent(),null);
            ds.persistObject(neueAufgabe);

        }
    }
