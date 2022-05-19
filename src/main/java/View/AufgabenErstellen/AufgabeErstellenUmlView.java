package View.AufgabenErstellen;

import entity.aufgabe.Designaufgabe;
import entity.benutzer.Dozent;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
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
 * @version 09.05.2022 Layout gefixed Funktionalität geadded schreibt passig in die Datenbank.
 * @version 15.05.2022 switch zu extends JFRame, Dozentübergabe gemacht, Musterlösung eingebunden, Filechooser ausgelagert, TA teilweise zu TF gemacht
 */
public class AufgabeErstellenUmlView extends JFrame implements ActionListener {
    //JFrames
    private JFrame aufgabeErstellenStartViewFrame;
    private Dozent doz;
    //Panels
    private JPanel AufgabeErstellenUMLPnl;
    private JPanel centerPnl;
    private JPanel northPnl;
    private JPanel southPnl;
    //Layouts
    private BorderLayout bl = new BorderLayout();
    private GridLayout gl = new GridLayout(10, 2);
    //Buttons
    private JButton zurueckBtn;
    private JButton speichernBtn;
    private JButton UMLHochladenBtn;
    private JButton musterloesungBtn;
    //JComboboxen
    private JComboBox kategorienCB;
    private JComboBox schwierigkeitCB;
    //Labels
    private JLabel titelLbl;
    private JLabel aufgabenTxtLbl;
    private JLabel loesungsHinweisLbl;
    private JLabel schwierigketiLbl;
    private JLabel kategorienLbl;
    private JLabel bearbeitungszeitLbl;
    private JLabel punkteLbl;
    //TextAreas
    private JTextField titelTF;
    private JTextArea aufgabenTextTA;
    private JTextArea loesungshinwTA;
    private JTextField bearbeitungsZeitTF;
    private JTextField punkteTF;
    //Files
    private File designFile;
    private String loesungString;
    private File musterloesungFile;

    public AufgabeErstellenUmlView(JFrame aufgabeErstellenStartViewFrame,Dozent doz) {
        this.doz = doz;
        this.aufgabeErstellenStartViewFrame = aufgabeErstellenStartViewFrame;
        this.setName("Design Aufgabe Erstellen");
        AufgabeErstellenUMLViewFuellen();
        this.pack();
        this.setMinimumSize(new Dimension(1500, 900));
        this.setSize(1500, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
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
        aufgabenTextTA = new JTextArea();
        aufgabenTextTA.setLineWrap(true);

        loesungshinwTA = new JTextArea();
        loesungshinwTA.setLineWrap(true);
        //TextFields
        punkteTF = new JTextField();
        bearbeitungsZeitTF = new JTextField();
        titelTF = new JTextField();
        //ComboBoxes
        Kategorie[] kat = {Kategorie.Java_Programmierung, Kategorie.Datenbanken, Kategorie.Software_Engineering, Kategorie.Java_Grundlagen,};
        kategorienCB = new JComboBox(kat);

        Schwierigkeitsgrad[] schw = {Schwierigkeitsgrad.Leicht, Schwierigkeitsgrad.Schwer, Schwierigkeitsgrad.Mittel};
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
        centerPnl.add(titelTF);
        centerPnl.add(aufgabenTxtLbl);
        centerPnl.add(aufgabenTextTA);
        centerPnl.add(kategorienLbl);
        centerPnl.add(kategorienCB);
        centerPnl.add(UMLHochladenBtn);
        centerPnl.add(musterloesungBtn);
        centerPnl.add(schwierigketiLbl);
        centerPnl.add(schwierigkeitCB);
        centerPnl.add(bearbeitungszeitLbl);
        centerPnl.add(bearbeitungsZeitTF);
        centerPnl.add(punkteLbl);
        centerPnl.add(punkteTF);
        centerPnl.add(loesungsHinweisLbl);
        centerPnl.add(loesungshinwTA);

        northPnl.add(zurueckBtn);
        southPnl.add(speichernBtn);

        AufgabeErstellenUMLPnl.add(centerPnl, BorderLayout.CENTER);
        AufgabeErstellenUMLPnl.add(northPnl, BorderLayout.NORTH);
        AufgabeErstellenUMLPnl.add(southPnl, BorderLayout.SOUTH);
        this.add(AufgabeErstellenUMLPnl);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckBtn) {
            zurueck();
        } else if (e.getSource() == this.speichernBtn) {
            speichern();
        } else if (e.getSource() == this.UMLHochladenBtn) {
            FileChooserAuslagerung filcV = new FileChooserAuslagerung();
            designFile = filcV.fileChooser();
        } else if (e.getSource() == this.musterloesungBtn) {
            FileChooserAuslagerung filcV = new FileChooserAuslagerung();
            musterloesungFile = filcV.fileChooser();
        }
    }
    private void zurueck() {
        this.dispose();
        aufgabeErstellenStartViewFrame.setVisible(true);
    }

    private void speichern() {
        String aufgTitel = null;
        String aufText = null;
        String loesungshinweis = null;
        int bearbeitungsZeit = 0;
        int punkte = 0;
        Kategorie kat = null;
        Schwierigkeitsgrad schw = null;

        try {
            aufgTitel = titelTF.getText();
            aufText = aufgabenTextTA.getText();
            loesungshinweis = loesungshinwTA.getText();
            bearbeitungsZeit = Integer.parseInt(bearbeitungsZeitTF.getText());
            schw = (Schwierigkeitsgrad) schwierigkeitCB.getSelectedItem();
            kat = (Kategorie) kategorienCB.getSelectedItem();
            punkte = Integer.parseInt(punkteTF.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Eine Eingabe entsprach nicht dem nötigen Datentyp",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (AufgabeErstellenStartView.inputcleaner(bearbeitungsZeit, punkte, this) && aufgTitel != null) {
            createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte, kat, schw, doz);
            this.dispose();
            aufgabeErstellenStartViewFrame.setVisible(true);
        }
    }

    private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte, Kategorie kat, Schwierigkeitsgrad schw, Dozent doz) {

        DatabaseService ds = DatabaseService.getInstance();
        Designaufgabe neueAufgabe = new Designaufgabe(bearbeitungsZeit,
                "a", //Eigentlich designFile
                kat,
                punkte,
                schw,
                aufText,
                aufgTitel,
                doz,
                null);
        doz.addErstellteAufgabe(neueAufgabe);
        MusterloesungDesignaufgabe mlp = new MusterloesungDesignaufgabe(neueAufgabe, loesungshinweis, loesungString);
        try {
            neueAufgabe.setMusterloesung(mlp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Musterlösung setzten fehlgeschlagen",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        ds.persistObject(neueAufgabe);
        ds.persistObject(mlp);

    }
}