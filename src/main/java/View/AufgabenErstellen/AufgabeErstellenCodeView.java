package View.AufgabenErstellen;

import View.DozentAnsicht;
import View.ImageFilter;
import entity.benutzer.Dozent;
import entity.enums.Kategorie;
import entity.aufgabe.Programmieraufgabe;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;
import persistence.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


/**
 * Die View zur Erstellung einer Code Aufgabe
 *
 * @author Jannik Oehme
 * @version 09.05.2022 Layout gefixed funktionalität geadded schreibt passig in die Datenbank.
 * @version 15.05.2022 switch zu extends JFRame, Dozentübergabe gemacht, Musterlösung eingebunden, Filechooser ausgelagert, TA teilweise zu TF gemacht
 */
public class AufgabeErstellenCodeView extends JFrame implements ActionListener {
    //JFrame
    private JFrame aufgabeErstellenStartViewFrame;
    private Dozent doz;
    //Panels
    private JPanel AufgabeErstellenCodePnl;
    private JPanel centerPnl;
    private JPanel northPnl;
    private JPanel southPnl;
    //Layouts
    private BorderLayout bl = new BorderLayout();
    private GridLayout gl = new GridLayout(10, 2);
    //Buttons
    private JButton codeHochBtn;
    private JButton zurueckBtn;
    private JButton speichernBtn;
    //JComboBoxen
    private JComboBox kategorienCB;
    private JComboBox schwierigkeitCB;
    //Labels
    private JLabel codeBspHochLbl;
    private JLabel codeBeispiel;
    private JLabel kategorieLbl;
    private JLabel titelLbl;
    private JLabel aufgabenTxtLbl;
    private JLabel loesungsHinweisLbl;
    private JLabel schwierigketiLbl;
    private JLabel bearbeitungszeitLbl;
    private JLabel punkteLbl;
    private JLabel loesungLbl;
    //TextAreas
    //private JTextArea codeTA;
    private JTextField titelTF;
    private JTextArea aufgabenTextTA;
    private JTextArea loesungshinwTA;
    private JTextField bearbeitungsZeitTF;
    private JTextField punkteTF;
    private JTextArea loesungTA;
    //File
    private File codeBspFile;
    private JFileChooser FC;

    public AufgabeErstellenCodeView(JFrame aufgabeErstellenStartViewFrame,Dozent doz) {
        this.doz = doz;
        this.aufgabeErstellenStartViewFrame = aufgabeErstellenStartViewFrame;
        this.setName("ProgrammierAufgabe");
        AufgabeErstellenCodeViewFuellen();
        this.pack();

        this.setMinimumSize(new Dimension(1500, 900));
        this.setSize(1500, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    private void AufgabeErstellenCodeViewFuellen() {
        gl.setVgap(25);
        gl.setHgap(25);
        centerPnl = new JPanel(gl);
        northPnl = new JPanel();
        southPnl = new JPanel();
        //Panels
        AufgabeErstellenCodePnl = new JPanel();
        AufgabeErstellenCodePnl.setLayout(bl);
        centerPnl.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        //Buttons
        codeHochBtn = new JButton("Optionales Java Design Hochladen");
        codeHochBtn.addActionListener(this);

        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        speichernBtn = new JButton("Speichern");
        speichernBtn.addActionListener(this);

        //ComboBoxes
        Kategorie[] kat = {Kategorie.Java_Programmierung, Kategorie.Datenbanken, Kategorie.Software_Engineering, Kategorie.Java_Grundlagen,};
        kategorienCB = new JComboBox(kat);

        Schwierigkeitsgrad[] schw = {Schwierigkeitsgrad.Leicht, Schwierigkeitsgrad.Schwer, Schwierigkeitsgrad.Mittel};
        schwierigkeitCB = new JComboBox(schw);
        //TextAreas
        loesungTA = new JTextArea();
        loesungTA.setLineWrap(true);

        aufgabenTextTA = new JTextArea(50, 50);
        aufgabenTextTA.setLineWrap(true);

        loesungshinwTA = new JTextArea();
        loesungshinwTA.setLineWrap(true);
        //TextFields
        titelTF = new JTextField();
        bearbeitungsZeitTF = new JTextField();
        punkteTF = new JTextField();
        //Labels
        codeBspHochLbl = new JLabel("Optionales Java Design ");
        kategorieLbl = new JLabel("Kategorien");
        titelLbl = new JLabel("Aufgaben Titel");
        loesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        loesungLbl = new JLabel("Lösung");
        aufgabenTxtLbl = new JLabel("Aufgaben Text");
        //Components Adden
        centerPnl.add(titelLbl);
        centerPnl.add(titelTF);
        centerPnl.add(aufgabenTxtLbl);
        centerPnl.add(aufgabenTextTA);
        centerPnl.add(loesungLbl);
        centerPnl.add(loesungTA);
        centerPnl.add(kategorieLbl);
        centerPnl.add(kategorienCB);
        centerPnl.add(schwierigketiLbl);
        centerPnl.add(schwierigkeitCB);
        centerPnl.add(codeBspHochLbl);
        centerPnl.add(codeHochBtn);
        centerPnl.add(bearbeitungszeitLbl);
        centerPnl.add(bearbeitungsZeitTF);
        centerPnl.add(punkteLbl);
        centerPnl.add(punkteTF);
        centerPnl.add(loesungsHinweisLbl);
        centerPnl.add(loesungshinwTA);

        northPnl.add(zurueckBtn);
        southPnl.add(speichernBtn);

        AufgabeErstellenCodePnl.add(centerPnl, BorderLayout.CENTER);
        AufgabeErstellenCodePnl.add(northPnl, BorderLayout.NORTH);
        AufgabeErstellenCodePnl.add(southPnl, BorderLayout.SOUTH);
        this.add(AufgabeErstellenCodePnl);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckBtn) {
            zurueck();
        } else if (e.getSource() == this.speichernBtn) {
            speichern();
        } else if (e.getSource() == this.codeHochBtn) {
            FileChooserView filcV = new FileChooserView();
            codeBspFile = filcV.fileChooser();
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
        String loesung = null;

        try {
            aufgTitel = titelTF.getText();
            aufText = aufgabenTextTA.getText();
            loesungshinweis = loesungshinwTA.getText();
            bearbeitungsZeit = Integer.parseInt(bearbeitungsZeitTF.getText());
            schw = (Schwierigkeitsgrad) schwierigkeitCB.getSelectedItem();
            kat = (Kategorie) kategorienCB.getSelectedItem();
            punkte = Integer.parseInt(punkteTF.getText());
            loesung = loesungTA.getText();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Eine Eingabe entsprach nicht dem nötigen Datentyp",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (AufgabeErstellenStartView.inputcleaner(bearbeitungsZeit, punkte, this) && aufgTitel != null) {

            createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte, kat, schw, loesung,/**codeText,**/doz);
            this.dispose();
            aufgabeErstellenStartViewFrame.setVisible(true);

        }
    }

    private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte, Kategorie kat, Schwierigkeitsgrad schw, String loesung, /**String codeText,**/Dozent doz) {

        DatabaseService ds = DatabaseService.getInstance();
        Programmieraufgabe neueAufgabe = new Programmieraufgabe(bearbeitungsZeit,
                "asd", // Eigentlich codeBEispFile
                kat,
                punkte,
                schw,
                aufText,
                aufgTitel,
                doz,
                null);
        MusterloesungProgrammieraufgabe mlp = new MusterloesungProgrammieraufgabe(neueAufgabe, loesungshinweis, loesung);
        doz.addErstellteAufgabe(neueAufgabe);
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
