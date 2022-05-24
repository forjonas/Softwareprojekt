package View.AufgabenErstellen;

import entity.aufgabe.MultipleChoiceAufgabe;
import entity.benutzer.Dozent;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.MusterloesungMultipleChoiceAufgabe;
import persistence.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Die View zur Erstellung einer Multiple Choice Aufgabe
 *
 * @author Jannik Oehme
 * @version 15.05.2022 switch zu extends JFRame, Dozentübergabe gemacht, Musterlösung eingebunden, Filechooser ausgelagert, TA teilweise zu TF gemacht
 */
public class AufgabeErstellenMultipleChoiceView extends JFrame implements ActionListener {
    private Dozent doz;

    private JFrame aufgabeErstellenStartViewFrame;

    private JPanel AufgabeErstellenMultipleChoicePnl;
    private JPanel centerPnl;
    private JPanel northPnl;
    private JPanel southPnl;

    private BorderLayout bl = new BorderLayout();
    private GridLayout gl = new GridLayout(15, 1);

    private JComboBox schwierigkeitCB;
    private JComboBox kategorienCB;
    private JComboBox anzCB;

    private JButton zurueckBtn;
    private JButton speichernBtn;
    private JButton bspBildBtn;

    private JLabel bspBildLbl;
    private JLabel kategorieLbl;
    private JLabel schwierigkeitLbl;
    private JLabel titelLbl;
    private JLabel aufgabenTxtLbl;
    private JLabel loesungsHinweisLbl;
    private JLabel schwierigketiLbl;
    private JLabel bearbeitungszeitLbl;
    private JLabel punkteLbl;
    private JLabel loesungLbl;
    private JLabel antwort1Lbl;
    private JLabel antwort2Lbl;
    private JLabel antwort3Lbl;
    private JLabel antwort4Lbl;
    private JLabel anzAntLbl;

    private JTextArea aufgabenTextTA;
    private JTextArea loesungshinwTA;

    private JTextField titelTF;
    private JTextField bearbeitungsZeitTF;
    private JTextField punkteTF;
    private JTextField loesungTF;
    private JTextField antwort1TF;
    private JTextField antwort2TF;
    private JTextField antwort3TF;
    private JTextField antwort4TF;
    private File bspBild;

    /**
     * Konstruktor der Klasse, benötigt einen Dozenten und den vorherigen JFrame
     * Setzt Parameter des JFrames und ruft AufgabeErstellenEInfachANtwortViewFuellen() auf.
     */
    public AufgabeErstellenMultipleChoiceView(JFrame aufgabeErstellenStartViewFrame, Dozent doz) {
        this.doz = doz;
        this.aufgabeErstellenStartViewFrame = aufgabeErstellenStartViewFrame;
        this.setName("MultipleChoice");
        AufgabeErstellenEinfachAntwortViewFuellen();
        this.pack();
        this.setMinimumSize(new Dimension(1500, 900));
        this.setSize(1500, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    /**
     * Erstellt die Komponenten des JFrames sowie JPanels welche später eingefügt werden. Und fügt die Componenten in den Frame ein.
     */
    private void AufgabeErstellenEinfachAntwortViewFuellen() {

        gl.setVgap(10);
        gl.setHgap(25);
        centerPnl = new JPanel(gl);
        centerPnl.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        northPnl = new JPanel();
        southPnl = new JPanel();
        AufgabeErstellenMultipleChoicePnl = new JPanel();
        AufgabeErstellenMultipleChoicePnl.setLayout(bl);

        Integer[] anz = {2, 3, 4};
        anzCB = new JComboBox(anz);
        anzCB.addActionListener(this);

        Kategorie[] kat = {Kategorie.Java_Programmierung, Kategorie.Datenbanken, Kategorie.Software_Engineering, Kategorie.Java_Grundlagen,};
        kategorienCB = new JComboBox(kat);

        Schwierigkeitsgrad[] schw = {Schwierigkeitsgrad.Leicht, Schwierigkeitsgrad.Schwer, Schwierigkeitsgrad.Mittel};
        schwierigkeitCB = new JComboBox(schw);

        bspBildBtn = new JButton("Beispiel Bild Hochladen");
        bspBildBtn.addActionListener(this);

        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        speichernBtn = new JButton("Speichern");
        speichernBtn.addActionListener(this);

        titelTF = new JTextField();

        aufgabenTextTA = new JTextArea();
        aufgabenTextTA.setLineWrap(true);

        loesungshinwTA = new JTextArea();
        loesungshinwTA.setLineWrap(true);

        bearbeitungsZeitTF = new JTextField();
        punkteTF = new JTextField();
        loesungTF = new JTextField();
        antwort1TF = new JTextField();
        antwort2TF = new JTextField();
        antwort3TF = new JTextField();
        antwort4TF = new JTextField();

        bspBildLbl = new JLabel("Beispiel Bild Hochladen: ");
        anzAntLbl = new JLabel("Anzahl der Antwortmöglichkeiten");
        schwierigkeitLbl = new JLabel("Schwierigkeit");
        kategorieLbl = new JLabel("Kategorien");
        titelLbl = new JLabel("Aufgaben Titel");
        loesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        loesungLbl = new JLabel("Lösung (Hier Nummer der richtigen Antwort eingeben");
        aufgabenTxtLbl = new JLabel("Aufgaben Text: ");
        antwort1Lbl = new JLabel("Antwort 1:");
        antwort2Lbl = new JLabel("Antwort 2:");
        antwort3Lbl = new JLabel("Antwort 3:");
        antwort4Lbl = new JLabel("Antwort 4:");

        centerPnl.add(titelLbl);
        centerPnl.add(titelTF);
        centerPnl.add(aufgabenTxtLbl);
        centerPnl.add(aufgabenTextTA);
        centerPnl.add(loesungLbl);
        centerPnl.add(loesungTF);
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
        centerPnl.add(anzAntLbl);
        centerPnl.add(anzCB);

        centerPnl.add(antwort1Lbl);
        antwort1Lbl.setVisible(false);
        centerPnl.add(antwort1TF);
        antwort1TF.setVisible(false);
        centerPnl.add(antwort2Lbl);
        antwort2Lbl.setVisible(false);
        centerPnl.add(antwort2TF);
        antwort2TF.setVisible(false);
        centerPnl.add(antwort3Lbl);
        antwort3Lbl.setVisible(false);
        centerPnl.add(antwort3TF);
        antwort3TF.setVisible(false);
        centerPnl.add(antwort4Lbl);
        antwort4Lbl.setVisible(false);
        centerPnl.add(antwort4TF);
        antwort4TF.setVisible(false);

        northPnl.add(zurueckBtn);
        southPnl.add(speichernBtn);

        AufgabeErstellenMultipleChoicePnl.add(centerPnl, BorderLayout.CENTER);
        AufgabeErstellenMultipleChoicePnl.add(northPnl, BorderLayout.NORTH);
        AufgabeErstellenMultipleChoicePnl.add(southPnl, BorderLayout.SOUTH);
        this.add(AufgabeErstellenMultipleChoicePnl);
    }

    /**
     * Führt Aktionen der JButtons sowie der JComboboxen aus und ruft ggf. weitere Methoden auf.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckBtn) {
            zurueck();
        } else if (e.getSource() == this.speichernBtn) {
            speichern();
        } else if (e.getSource() == this.anzCB) {
            switchVisibility();
        } else if (e.getSource() == this.bspBildBtn) {
            FileChooserAuslagerung filcV = new FileChooserAuslagerung();
            bspBild = filcV.fileChooser();
        }
    }

    /**
     * Ändert die Visibility der JTextlabels und JTextfields abhängig von der zuvor ausgewählten aufgaben anzahl.
     */
    private void switchVisibility() {
        int switcher = (Integer) anzCB.getSelectedItem();
        switch (switcher) {
            case 2:
                antwort1TF.setVisible(true);
                antwort1Lbl.setVisible(true);
                antwort2TF.setVisible(true);
                antwort2Lbl.setVisible(true);
                antwort3TF.setVisible(false);
                antwort3Lbl.setVisible(false);
                antwort4TF.setVisible(false);
                antwort4Lbl.setVisible(false);
                break;
            case 3:
                antwort1TF.setVisible(true);
                antwort1Lbl.setVisible(true);
                antwort2TF.setVisible(true);
                antwort2Lbl.setVisible(true);
                antwort3TF.setVisible(true);
                antwort3Lbl.setVisible(true);
                antwort4TF.setVisible(false);
                antwort4Lbl.setVisible(false);
                break;
            case 4:
                antwort1TF.setVisible(true);
                antwort1Lbl.setVisible(true);
                antwort2TF.setVisible(true);
                antwort2Lbl.setVisible(true);
                antwort3TF.setVisible(true);
                antwort3Lbl.setVisible(true);
                antwort4TF.setVisible(true);
                antwort4Lbl.setVisible(true);
                break;
            default:
        }
    }

    /**
     * disposed diesen frame und ruft den vorherigen frame auf. Bzw setzt diesen wieder Visible.
     */
    private void zurueck() {
        this.dispose();
        aufgabeErstellenStartViewFrame.setVisible(true);
    }

    /**
     * Ruft die daten aus den JTextAreas sowie aus den JTextFields auf. Setzt diese in die Instanz variablen ein.
     * Ruft createObjectandPersist auf.
     */
    private void speichern() {
        String aufgTitel = null;
        String aufText = null;
        String loesungshinweis = null;
        int bearbeitungsZeit = 0;
        int punkte = 0;
        Kategorie kat = null;
        Schwierigkeitsgrad schw = null;
        ArrayList<String> listefinal = mcSpeichern();
        String loesungsText;
        ArrayList<Boolean> loesung = null;

        try {
            aufgTitel = titelTF.getText();
            aufText = aufgabenTextTA.getText();
            loesungshinweis = loesungshinwTA.getText();
            bearbeitungsZeit = Integer.parseInt(bearbeitungsZeitTF.getText());
            schw = (Schwierigkeitsgrad) schwierigkeitCB.getSelectedItem();
            kat = (Kategorie) kategorienCB.getSelectedItem();
            punkte = Integer.parseInt(punkteTF.getText());
            loesungsText = loesungTF.getText();
            loesung = getLoesung(loesungsText);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eine Eingabe entsprach nicht dem nötigen DatenTyp", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (AufgabeErstellenStartView.inputcleaner(bearbeitungsZeit, punkte, this) && aufgTitel != null) {
            createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte, kat, schw, listefinal, loesung);

            this.dispose();
            aufgabeErstellenStartViewFrame.setVisible(true);
        }
    }

    /**
     * @param loesungText Gibt eine ArrayList<Boolean> aus welche per switchcase erstellt wird. Welche für die Lösungsdarstellung benötigt wird.
     */
    private ArrayList<Boolean> getLoesung(String loesungText) {
        int switcher = Integer.parseInt(loesungText);
        ArrayList<Boolean> loesung = new ArrayList<Boolean>();
        switch (switcher) {
            case 1:
                loesung.add(true);
                loesung.add(false);
                loesung.add(false);
                loesung.add(false);
                return loesung;
            case 2:
                loesung.add(false);
                loesung.add(true);
                loesung.add(false);
                loesung.add(false);
                return loesung;
            case 3:
                loesung.add(false);
                loesung.add(false);
                loesung.add(true);
                loesung.add(false);
                return loesung;
            case 4:
                loesung.add(false);
                loesung.add(false);
                loesung.add(false);
                loesung.add(true);
                return loesung;

            default:
                return loesung;
        }
    }

    /**
     * Erstellt eine ArrayList<String> in welcher die Antwortmöglichkeiten der Multiplechoice gespeichert werden.
     */
    private ArrayList<String> mcSpeichern() {
        ArrayList<String> liste = new ArrayList<String>();
        int switcher = (Integer) anzCB.getSelectedItem();
        switch (switcher) {
            case 2:
                liste.add(antwort1TF.getText());
                liste.add(antwort2TF.getText());
                return liste;
            case 3:
                liste.add(antwort1TF.getText());
                liste.add(antwort2TF.getText());
                liste.add(antwort3TF.getText());
                return liste;
            case 4:
                liste.add(antwort1TF.getText());
                liste.add(antwort2TF.getText());
                liste.add(antwort3TF.getText());
                liste.add(antwort4TF.getText());
                return liste;
            default:
        }
        return liste;
    }

    /**
     * Erstellt eine Aufgabe vom Typ MultipleChoiceAufgabe, Erstellt eine Musterlösung für den Typen, verknüpft die Aufgabe und die Musterlösung. Speichert beide über den DatabaseService in die Datenbank.
     *
     * @param aufgTitel        Titel der Aufgabe
     * @param aufText          Text der Aufgabe
     * @param loesungshinweis  loesungshinweis der Aufgabe
     * @param bearbeitungsZeit bearbeitungszeit der Aufgabe
     * @param punkte           maximale punkte der Aufgabe
     * @param kat              kategorie der Aufgabe
     * @param schw             schwierigkeitsgrad der Aufgabe
     * @param antworten        antwortmöglichkeiten der Aufgabe
     * @param loesung          loesung der Aufgabe
     */
    private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte, Kategorie kat, Schwierigkeitsgrad schw, ArrayList<String> antworten, ArrayList<Boolean> loesung) {

        DatabaseService ds = DatabaseService.getInstance();
        byte[] bspBildByteArray = DatabaseService.convertFileToByteArray(bspBild, this);
        MultipleChoiceAufgabe neueAufgabe = new MultipleChoiceAufgabe(bearbeitungsZeit, bspBildByteArray, kat, punkte, schw, aufText, aufgTitel, doz, antworten, null);
        doz.addErstellteAufgabe(neueAufgabe);
        MusterloesungMultipleChoiceAufgabe mlp = new MusterloesungMultipleChoiceAufgabe(neueAufgabe, loesungshinweis, loesung);
        try {
            neueAufgabe.setMusterloesung(mlp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Musterlösung setzten fehlgeschlagen", "Error", JOptionPane.ERROR_MESSAGE);
        }

        ds.persistObject(neueAufgabe);
        ds.persistObject(mlp);
    }
}
