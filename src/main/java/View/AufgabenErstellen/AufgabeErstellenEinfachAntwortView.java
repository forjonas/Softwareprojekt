package View.AufgabenErstellen;

import View.DozentAnsicht;
import View.ImageFilter;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.benutzer.Dozent;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.MusterloesungEinfachantwort;
import persistence.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Die View zur Erstellung einer Einfachantwort Aufgabe
 *
 * @author Jannik Oehme
 * @version 09.05.2022 Layout gefixed Funktionalität geadded schreibt passig in die Datenbank.
 */
public class AufgabeErstellenEinfachAntwortView extends JFrame implements ActionListener {
    private Dozent doz;
    //Frames
    private JPanel AufgabeErstellenEinfachAntwortViewPnl;
    //Panels
    private JPanel centerPnl;
    private JPanel northPnl;
    private JPanel southPnl;
    //JComboboxen
    private JComboBox kategorienCB;
    private JComboBox schwierigkeitCB;
    //Layouts
    private BorderLayout bl = new BorderLayout();
    private GridLayout gl = new GridLayout(10, 2);
    //Buttons
    private JButton zurueckBtn;
    private JButton speichernBtn;
    private JButton bspBildBtn;

    //Labels
    private JLabel bspBildLbl;
    private JLabel kategorieLbl;
    private JLabel titelLbl;
    private JLabel aufgabenTxtLbl;
    private JLabel loesungsHinweisLbl;
    private JLabel schwierigketiLbl;
    private JLabel bearbeitungszeitLbl;
    private JLabel punkteLbl;
    private JLabel loesungLbl;
    //TextAreas
    private JTextField titelTF;
    private JTextArea aufgabenTextTA;
    private JTextArea loesungshinwTA;
    private JTextField bearbeitungsZeitTF;
    private JTextField punkteTF;
    private JTextArea loesungTA;
    private JFrame aufgabeErstellenStartViewFrame;
    private File bspBild;
    private JFileChooser FC;

    public static void main(String[] args) {
        new AufgabeErstellenEinfachAntwortView(null);
    }

    public AufgabeErstellenEinfachAntwortView(JFrame aufgabeErstellenStartViewFrame) {
        this.aufgabeErstellenStartViewFrame = aufgabeErstellenStartViewFrame;
        this.setName("Einfach Antwort");
        AufgabeErstellenEinfachAntwortViewFuellen();
        this.pack();
        this.setMinimumSize(new Dimension(1500, 900));
        this.setSize(1500, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    private void AufgabeErstellenEinfachAntwortViewFuellen() {
        doz = new Dozent();
        gl.setVgap(25);
        gl.setHgap(25);
        centerPnl = new JPanel(gl);
        northPnl = new JPanel();
        southPnl = new JPanel();
        centerPnl.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        AufgabeErstellenEinfachAntwortViewPnl = new JPanel();
        AufgabeErstellenEinfachAntwortViewPnl.setLayout(bl);
        //Buttons
        bspBildBtn = new JButton("Beispiel Bild Hochladen");
        bspBildBtn.addActionListener(this);

        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        speichernBtn = new JButton("Speichern");
        speichernBtn.addActionListener(this);
        //Text Areas
        titelTF = new JTextField();

        titelTF.setBounds(20, 75, 250, 200);

        aufgabenTextTA = new JTextArea();
        aufgabenTextTA.setLineWrap(true);
        aufgabenTextTA.setBounds(20, 75, 250, 200);

        loesungshinwTA = new JTextArea();
        loesungshinwTA.setBounds(20, 75, 250, 200);
        loesungshinwTA.setLineWrap(true);

        bearbeitungsZeitTF = new JTextField();
        bearbeitungsZeitTF.setBounds(20, 75, 250, 200);


        punkteTF = new JTextField();
        punkteTF.setBounds(20, 75, 250, 200);


        loesungTA = new JTextArea();
        loesungTA.setBounds(20, 75, 250, 200);
        loesungTA.setLineWrap(true);
        //ComboBoxes
        Kategorie[] kat = {Kategorie.Java_Programmierung, Kategorie.Datenbanken, Kategorie.Software_Engineering, Kategorie.Java_Grundlagen,};
        kategorienCB = new JComboBox(kat);

        Schwierigkeitsgrad[] schw = {Schwierigkeitsgrad.Leicht, Schwierigkeitsgrad.Schwer, Schwierigkeitsgrad.Mittel};
        schwierigkeitCB = new JComboBox(schw);
        //Label
        bspBildLbl = new JLabel("Beispiel Bild Hochladen: ");
        kategorieLbl = new JLabel("Kategorie: ");
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
        centerPnl.add(schwierigketiLbl);
        centerPnl.add(schwierigkeitCB);
        centerPnl.add(kategorieLbl);
        centerPnl.add(kategorienCB);
        centerPnl.add(bspBildLbl);
        centerPnl.add(bspBildBtn);
        centerPnl.add(bearbeitungszeitLbl);
        centerPnl.add(bearbeitungsZeitTF);
        centerPnl.add(punkteLbl);
        centerPnl.add(punkteTF);
        centerPnl.add(loesungsHinweisLbl);
        centerPnl.add(loesungshinwTA);

        northPnl.add(zurueckBtn);
        southPnl.add(speichernBtn);

        AufgabeErstellenEinfachAntwortViewPnl.add(centerPnl, BorderLayout.CENTER);
        AufgabeErstellenEinfachAntwortViewPnl.add(northPnl, BorderLayout.NORTH);
        AufgabeErstellenEinfachAntwortViewPnl.add(southPnl, BorderLayout.SOUTH);
        this.add(AufgabeErstellenEinfachAntwortViewPnl);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckBtn) {
            zurueck();
        } else if (e.getSource() == this.speichernBtn) {
            speichern();
        } else if (e.getSource() == this.bspBildBtn) {
            bspBild = bspBildHochladen();
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
            createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte, kat, schw, doz, loesung);
            this.dispose();
            DozentAnsicht.main(null);
        }
    }

    private File bspBildHochladen() {
        FC = new JFileChooser((String) null);
        FC.setAcceptAllFileFilterUsed(false);
        FC.setFileFilter(new ImageFilter());
        int returnVal = FC.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            bspBild = FC.getSelectedFile();
            System.out.println(bspBild.getName());
            return bspBild;
        }
        return null;
    }

    private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte, Kategorie kat, Schwierigkeitsgrad schw, Dozent doz, String loesung) {

        DatabaseService ds = DatabaseService.getInstance();
        EinfachantwortAufgabe neueAufgabe = new EinfachantwortAufgabe(bearbeitungsZeit, null, kat, punkte, schw, aufText, aufgTitel, doz, null);
        doz.addErstellteAufgabe(neueAufgabe);
        MusterloesungEinfachantwort mlp = new MusterloesungEinfachantwort(neueAufgabe, loesungshinweis, loesung);
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
