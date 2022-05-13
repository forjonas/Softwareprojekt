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
 */
public class AufgabeErstellenCodeView extends JFrame implements ActionListener {
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
    private JTextArea codeTA;
    private JTextField titelTF;
    private JTextArea aufgabenTextTA;
    private JTextArea loesungshinwTA;
    private JTextField bearbeitungsZeitTF;
    private JTextField punkteTF;
    private JTextArea loesungTA;
    //File
    private File codeBspFile;
    private JFileChooser FC;
    private JFrame aufgabeErstellenStartViewFrame;

    public static void main(String[] args) {
        new AufgabeErstellenCodeView(null);
    }

    public AufgabeErstellenCodeView(JFrame aufgabeErstellenStartViewFrame) {
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
        doz = new Dozent();
        gl.setVgap(25);
        gl.setHgap(25);
        centerPnl = new JPanel(gl);
        northPnl = new JPanel();
        southPnl = new JPanel();
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

        titelTF = new JTextField();

        //ComboBoxes
        Kategorie[] kat = {Kategorie.Java_Programmierung, Kategorie.Datenbanken, Kategorie.Software_Engineering, Kategorie.Java_Grundlagen,};
        kategorienCB = new JComboBox(kat);

        Schwierigkeitsgrad[] schw = {Schwierigkeitsgrad.Leicht, Schwierigkeitsgrad.Schwer, Schwierigkeitsgrad.Mittel};
        schwierigkeitCB = new JComboBox(schw);

        aufgabenTextTA = new JTextArea(50, 50);
        aufgabenTextTA.setLineWrap(true);

        loesungshinwTA = new JTextArea();
        loesungshinwTA.setLineWrap(true);

        bearbeitungsZeitTF = new JTextField();

        punkteTF = new JTextField();

        codeTA = new JTextArea();
        codeTA.setLineWrap(true);

        loesungTA = new JTextArea();
        loesungTA.setLineWrap(true);

        codeBspHochLbl = new JLabel("Optionales Java Design ");
        codeBeispiel = new JLabel("Code Beispiel: ");
        kategorieLbl = new JLabel("Kategorien");
        titelLbl = new JLabel("Aufgaben Titel");
        loesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        loesungLbl = new JLabel("Lösung");
        aufgabenTxtLbl = new JLabel("Aufgaben Text");

        centerPnl.add(titelLbl);
        centerPnl.add(titelTF);
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
            codeBspHochladen();
        }
    }

    private File codeBspHochladen() {
        FC = new JFileChooser((String) null);
        FC.setAcceptAllFileFilterUsed(false);
        FC.setFileFilter(new ImageFilter());
        int returnVal = FC.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            codeBspFile = FC.getSelectedFile();
            System.out.println(codeBspFile.getName());
            return codeBspFile;
        }
        return null;
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
        String codeText = null;
        String loesung = null;

        try {
            aufgTitel = titelTF.getText();
            aufText = aufgabenTextTA.getText();
            loesungshinweis = loesungshinwTA.getText();
            bearbeitungsZeit = Integer.parseInt(bearbeitungsZeitTF.getText());
            schw = (Schwierigkeitsgrad) schwierigkeitCB.getSelectedItem();
            kat = (Kategorie) kategorienCB.getSelectedItem();
            punkte = Integer.parseInt(punkteTF.getText());
            codeText = codeTA.getText();
            loesung = loesungTA.getText();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Eine Eingabe entsprach nicht dem nötigen Datentyp",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (AufgabeErstellenStartView.inputcleaner(bearbeitungsZeit, punkte, this) && aufgTitel != null) {

            createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte, kat, schw, loesung, codeText, doz);
            this.dispose();
            DozentAnsicht.main(null);
        }
    }

    private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte, Kategorie kat, Schwierigkeitsgrad schw, String loesung, String codeText, Dozent doz) {

        DatabaseService ds = DatabaseService.getInstance();
        Programmieraufgabe neueAufgabe = new Programmieraufgabe(
                bearbeitungsZeit,
                codeText,
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
