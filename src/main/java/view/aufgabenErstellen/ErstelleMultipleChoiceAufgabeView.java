package view.aufgabenErstellen;

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
public class ErstelleMultipleChoiceAufgabeView extends JFrame implements ActionListener {
    private Dozent doz;
    private JFrame aufgabeErstellenStartViewFrame;
    private JPanel pnlAufgabeErstellenMultipleChoice;
    private BorderLayout borderLayout = new BorderLayout();
    private GridLayout gridLayout = new GridLayout(15, 1);
    private JPanel pnlCenter;
    private JPanel pnlNorth;
    private JPanel pnlSouth;

    private JButton btnZurueck;
    private JButton btnSpeichern;
    private JButton btnBeispielBild;
    private JComboBox cbAnzahl;
    private JComboBox cbKategorien;
    private JComboBox cbSchwierigkeit;
    private JLabel lblBeispielBild;
    private JLabel lblKategorie;
    private JLabel lblTitel;
    private JLabel lblSchwierigkeit;
    private JLabel lblAufgabentext;
    private JLabel lblLoesungshinweis;
    private JLabel lblBearbeitungszeit;
    private JLabel lblPunkte;
    private JLabel lblLoesung;
    private JLabel lblAntwort1;
    private JLabel lblAntwort2;
    private JLabel lblAntwort3;
    private JLabel lblAntwort4;
    private JLabel lblAnzahlAntworten;

    private JTextField txtfTitel;
    private JTextArea txtaAufgabentext;
    private JTextArea txtaLoesungshinweis;
    private JTextField txtfBearbeitungszeit;
    private JTextField txtfPunkte;
    private JTextField txtfLoesung;
    private JTextField txtfAntwort1;
    private JTextField txtfAntwort2;
    private JTextField txtfAntwort3;
    private JTextField txtfAntwort4;
    private File fileBspBild;
    byte [] byteArrayBeispielBild;
    /**
     * Konstruktor der Klasse, benötigt einen Dozenten und den vorherigen JFrame
     * Setzt Parameter des JFrames und ruft AufgabeErstellenEInfachANtwortViewFuellen() auf.
     */
    public ErstelleMultipleChoiceAufgabeView(JFrame aufgabeErstellenStartViewFrame, Dozent doz) {
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

        gridLayout.setVgap(10);
        gridLayout.setHgap(25);
        pnlCenter = new JPanel(gridLayout);
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        pnlNorth = new JPanel();
        pnlSouth = new JPanel();
        pnlAufgabeErstellenMultipleChoice = new JPanel();
        pnlAufgabeErstellenMultipleChoice.setLayout(borderLayout);

        Integer[] anz = {2, 3, 4};
        cbAnzahl = new JComboBox(anz);
        cbAnzahl.addActionListener(this);

        Kategorie[] kat = {Kategorie.Java_Programmierung, Kategorie.Datenbanken, Kategorie.Software_Engineering, Kategorie.Java_Grundlagen,};
        cbKategorien = new JComboBox(kat);

        Schwierigkeitsgrad[] schw = {Schwierigkeitsgrad.Leicht, Schwierigkeitsgrad.Schwer, Schwierigkeitsgrad.Mittel};
        cbSchwierigkeit = new JComboBox(schw);

        btnBeispielBild = new JButton("Beispiel Bild Hochladen");
        btnBeispielBild.addActionListener(this);

        btnZurueck = new JButton("Zurück");
        btnZurueck.addActionListener(this);

        btnSpeichern = new JButton("Speichern");
        btnSpeichern.addActionListener(this);

        txtfTitel = new JTextField();

        txtaAufgabentext = new JTextArea();
        txtaAufgabentext.setLineWrap(true);

        txtaLoesungshinweis = new JTextArea();
        txtaLoesungshinweis.setLineWrap(true);

        txtfBearbeitungszeit = new JTextField();
        txtfPunkte = new JTextField();
        txtfLoesung = new JTextField();
        txtfAntwort1 = new JTextField();
        txtfAntwort2 = new JTextField();
        txtfAntwort3 = new JTextField();
        txtfAntwort4 = new JTextField();

        lblBeispielBild = new JLabel("Beispiel Bild Hochladen: ");
        lblAnzahlAntworten = new JLabel("Anzahl der Antwortmöglichkeiten");
        lblSchwierigkeit = new JLabel("Schwierigkeit");
        lblKategorie = new JLabel("Kategorien:");
        lblTitel = new JLabel("Aufgaben Titel:");
        lblLoesungshinweis = new JLabel("Lösungshinweis: ");
        lblSchwierigkeit = new JLabel("Schwierigkeit: ");
        lblBearbeitungszeit = new JLabel("BearbeitungsZeit: ");
        lblPunkte = new JLabel("Punkte: ");
        lblLoesung = new JLabel("Lösung (Hier Nummer der richtigen Antwort eingeben");
        lblAufgabentext = new JLabel("Aufgaben Text: ");
        lblAntwort1 = new JLabel("Antwort 1:");
        lblAntwort2 = new JLabel("Antwort 2:");
        lblAntwort3 = new JLabel("Antwort 3:");
        lblAntwort4 = new JLabel("Antwort 4:");

        pnlCenter.add(lblTitel);
        pnlCenter.add(txtfTitel);
        pnlCenter.add(lblAufgabentext);
        pnlCenter.add(txtaAufgabentext);
        pnlCenter.add(lblLoesung);
        pnlCenter.add(txtfLoesung);
        pnlCenter.add(lblSchwierigkeit);
        pnlCenter.add(cbSchwierigkeit);
        pnlCenter.add(lblKategorie);
        pnlCenter.add(cbKategorien);
        pnlCenter.add(lblBeispielBild);
        pnlCenter.add(btnBeispielBild);
        pnlCenter.add(lblBearbeitungszeit);
        pnlCenter.add(txtfBearbeitungszeit);
        pnlCenter.add(lblPunkte);
        pnlCenter.add(txtfPunkte);
        pnlCenter.add(lblLoesungshinweis);
        pnlCenter.add(txtaLoesungshinweis);
        pnlCenter.add(lblAnzahlAntworten);
        pnlCenter.add(cbAnzahl);

        pnlCenter.add(lblAntwort1);
        lblAntwort1.setVisible(false);
        pnlCenter.add(txtfAntwort1);
        txtfAntwort1.setVisible(false);
        pnlCenter.add(lblAntwort2);
        lblAntwort2.setVisible(false);
        pnlCenter.add(txtfAntwort2);
        txtfAntwort2.setVisible(false);
        pnlCenter.add(lblAntwort3);
        lblAntwort3.setVisible(false);
        pnlCenter.add(txtfAntwort3);
        txtfAntwort3.setVisible(false);
        pnlCenter.add(lblAntwort4);
        lblAntwort4.setVisible(false);
        pnlCenter.add(txtfAntwort4);
        txtfAntwort4.setVisible(false);

        pnlNorth.add(btnZurueck);
        pnlSouth.add(btnSpeichern);

        pnlAufgabeErstellenMultipleChoice.add(pnlCenter, BorderLayout.CENTER);
        pnlAufgabeErstellenMultipleChoice.add(pnlNorth, BorderLayout.NORTH);
        pnlAufgabeErstellenMultipleChoice.add(pnlSouth, BorderLayout.SOUTH);
        this.add(pnlAufgabeErstellenMultipleChoice);
    }

    /**
     * Führt Aktionen der JButtons sowie der JComboboxen aus und ruft ggf. weitere Methoden auf.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnZurueck) {
            zurueck();
        } else if (e.getSource() == this.btnSpeichern) {
            speichern();
        } else if (e.getSource() == this.cbAnzahl) {
            switchVisibility();
        } else if (e.getSource() == this.btnBeispielBild) {
            FileChooserAuslagerung filcV = new FileChooserAuslagerung();
            fileBspBild = filcV.fileChooser();
            if(fileBspBild != null)
                byteArrayBeispielBild = DatabaseService.convertFileToByteArray(fileBspBild, this);
        }
    }

    /**
     * Ändert die Visibility der JTextlabels und JTextfields abhängig von der zuvor ausgewählten aufgaben anzahl.
     */
    private void switchVisibility() {
        int switcher = (Integer) cbAnzahl.getSelectedItem();
        switch (switcher) {
            case 2:
                txtfAntwort1.setVisible(true);
                lblAntwort1.setVisible(true);
                txtfAntwort2.setVisible(true);
                lblAntwort2.setVisible(true);
                txtfAntwort3.setVisible(false);
                lblAntwort3.setVisible(false);
                txtfAntwort4.setVisible(false);
                lblAntwort4.setVisible(false);
                break;
            case 3:
                txtfAntwort1.setVisible(true);
                lblAntwort1.setVisible(true);
                txtfAntwort2.setVisible(true);
                lblAntwort2.setVisible(true);
                txtfAntwort3.setVisible(true);
                lblAntwort3.setVisible(true);
                txtfAntwort4.setVisible(false);
                lblAntwort4.setVisible(false);
                break;
            case 4:
                txtfAntwort1.setVisible(true);
                lblAntwort1.setVisible(true);
                txtfAntwort2.setVisible(true);
                lblAntwort2.setVisible(true);
                txtfAntwort3.setVisible(true);
                lblAntwort3.setVisible(true);
                txtfAntwort4.setVisible(true);
                lblAntwort4.setVisible(true);
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
        int loesungsWert = -1;

        try {
            aufgTitel = txtfTitel.getText();
            aufText = txtaAufgabentext.getText();
            loesungshinweis = txtaLoesungshinweis.getText();
            bearbeitungsZeit = Integer.parseInt(txtfBearbeitungszeit.getText());
            schw = (Schwierigkeitsgrad) cbSchwierigkeit.getSelectedItem();
            kat = (Kategorie) cbKategorien.getSelectedItem();
            punkte = Integer.parseInt(txtfPunkte.getText());
            loesungsWert = Integer.parseInt(txtfLoesung.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eine Eingabe entsprach nicht dem nötigen DatenTyp", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (ErstelleAufgabeStartView.inputcleaner(bearbeitungsZeit, punkte, this) && !aufgTitel.isEmpty() && !(loesungsWert < 1 || loesungsWert > 4)) {
            createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte, kat, schw, listefinal, loesungsWert);

            this.dispose();
            aufgabeErstellenStartViewFrame.setVisible(true);
        }
    }

    /**
     * Erstellt eine ArrayList<String> in welcher die Antwortmöglichkeiten der Multiplechoice gespeichert werden.
     */
    private ArrayList<String> mcSpeichern() {
        ArrayList<String> liste = new ArrayList<String>();
        int switcher = (Integer) cbAnzahl.getSelectedItem();
        switch (switcher) {
            case 2:
                liste.add(txtfAntwort1.getText());
                liste.add(txtfAntwort2.getText());
                return liste;
            case 3:
                liste.add(txtfAntwort1.getText());
                liste.add(txtfAntwort2.getText());
                liste.add(txtfAntwort3.getText());
                return liste;
            case 4:
                liste.add(txtfAntwort1.getText());
                liste.add(txtfAntwort2.getText());
                liste.add(txtfAntwort3.getText());
                liste.add(txtfAntwort4.getText());
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
    private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte, Kategorie kat, Schwierigkeitsgrad schw, ArrayList<String> antworten, int loesung) {

        DatabaseService ds = DatabaseService.getInstance();
        MultipleChoiceAufgabe neueAufgabe = new MultipleChoiceAufgabe(bearbeitungsZeit, byteArrayBeispielBild, kat, punkte, schw, aufText, aufgTitel, doz, antworten, null);
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
