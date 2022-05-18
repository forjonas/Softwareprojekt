package View.AufgabenErstellen;

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
 * @version 15.05.2022 switch zu extends JFRame, Dozentübergabe gemacht, Musterlösung eingebunden, Filechooser ausgelagert, TA teilweise zu TF gemacht
 */
public class AufgabeErstellenEinfachAntwortView extends JFrame implements ActionListener {
    private Dozent doz;
    //Frames
    private JFrame aufgabeErstellenStartViewFrame;
    private JPanel AufgabeErstellenEinfachAntwortViewPnl;
    //Layouts
    private BorderLayout bl = new BorderLayout();
    private GridLayout gl = new GridLayout(10, 2);
    //Panels
    private JPanel centerPnl;
    private JPanel northPnl;
    private JPanel southPnl;
    //JComboboxen
    private JComboBox kategorienCB;
    private JComboBox schwierigkeitCB;
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
    private JTextArea aufgabenTextTA;
    private JTextArea loesungshinwTA;
    private JTextArea loesungTA;
    //TextFields
    private JTextField titelTF;
    private JTextField bearbeitungsZeitTF;
    private JTextField punkteTF;
    //Files
    File bspBild;
    public AufgabeErstellenEinfachAntwortView(JFrame aufgabeErstellenStartViewFrame,Dozent doz) {
        this.doz = doz;
        this.aufgabeErstellenStartViewFrame = aufgabeErstellenStartViewFrame;
        this.setName("Einfach Antwort");
        AufgabeErstellenEinfachAntwortViewFuellen();
        this.setMinimumSize(new Dimension(1500, 900));
        this.setSize(1500, 900);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    private void AufgabeErstellenEinfachAntwortViewFuellen() {
        gl.setVgap(25);
        gl.setHgap(25);
        //Panels
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
        aufgabenTextTA = new JTextArea();
        aufgabenTextTA.setLineWrap(true);

        loesungshinwTA = new JTextArea();
        loesungshinwTA.setLineWrap(true);

        loesungTA = new JTextArea();
        loesungTA.setLineWrap(true);
        //TextFields
        titelTF = new JTextField();
        bearbeitungsZeitTF = new JTextField();
        punkteTF = new JTextField();
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
            FileChooserAuslagerung filcV = new FileChooserAuslagerung();
            bspBild = filcV.fileChooser();
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
            aufgabeErstellenStartViewFrame.setVisible(true);
        }
    }
    private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte, Kategorie kat, Schwierigkeitsgrad schw, Dozent doz, String loesung) {

        DatabaseService ds = DatabaseService.getInstance();
        EinfachantwortAufgabe neueAufgabe = new EinfachantwortAufgabe(
                bearbeitungsZeit,
                "asd", // Eigentlich bspBild
                kat,
                punkte,
                schw,
                aufText,
                aufgTitel,
                doz,
                null);
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
