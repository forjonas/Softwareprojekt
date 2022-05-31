package view.aufgabenErstellen;

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
 * @version 15.05.2022 switch zu extends JFrame, Dozentübergabe gemacht, Musterlösung eingebunden, Filechooser ausgelagert, TA teilweise zu TF gemacht
 */
public class AufgabeErstellenEinfachAntwortView extends JFrame implements ActionListener {
    private Dozent doz;
    private JFrame aufgabeErstellenStartViewFrame;
    private BorderLayout borderLayout = new BorderLayout();
    private GridLayout gridLayout = new GridLayout(10, 2);
    private JPanel pnlAufgabeErstellenEinfachAntwortView;
    private JPanel pnlCenter;
    private JPanel pnlNorth;
    private JPanel pnlSouth;

    private JButton btnZurueck;
    private JButton btnSpeichern;
    private JButton btnBeispielBild;
    private JComboBox cbKategorien;
    private JComboBox cbSchwierigkeit;
    private JLabel lblTitel;
    private JLabel lblAufgabentext;
    private JLabel lblLoesungshinweis;
    private JLabel lblSchwierigkeit;
    private JLabel lblBearbeitungszeit;
    private JLabel lblPunkte;
    private JLabel lblBeispielBild;
    private JLabel lblKategorie;
    private JLabel lblLoesung;
    private JTextField txtfTitel;
    private JTextArea txtaAufgabentext;
    private JTextArea txtaLoesungshinweis;
    private JTextField txtfBearbeitungszeit;
    private JTextField txtfPunkte;
    private JTextArea txtaLoesung;
    private File fileCodeBsp;
    byte [] byteArrayBeispielBild;

    /**
     * Konstruktor der Klasse, benötigt einen Dozenten und den vorherigen JFrame.
     * Setzt Parameter des JFrames und ruft AufgabeErstellenEinfachAntwortViewFuellen() auf.
     *
     * @param aufgabeErstellenStartViewFrame
     * @param doz
     */
    public AufgabeErstellenEinfachAntwortView(JFrame aufgabeErstellenStartViewFrame, Dozent doz) {
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

    /**
     * Erstellt die Komponenten des JFrames sowie JPanels welche später eingefügt werden. Und fügt die Komponenten in den Frame ein.
     */
    private void AufgabeErstellenEinfachAntwortViewFuellen() {
        gridLayout.setVgap(25);
        gridLayout.setHgap(25);

        pnlCenter = new JPanel(gridLayout);
        pnlNorth = new JPanel();
        pnlSouth = new JPanel();
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        pnlAufgabeErstellenEinfachAntwortView = new JPanel();
        pnlAufgabeErstellenEinfachAntwortView.setLayout(borderLayout);

        btnBeispielBild = new JButton("Beispiel Bild Hochladen");
        btnBeispielBild.addActionListener(this);

        btnZurueck = new JButton("Zurück");
        btnZurueck.addActionListener(this);

        btnSpeichern = new JButton("Speichern");
        btnSpeichern.addActionListener(this);

        txtaAufgabentext = new JTextArea();
        txtaAufgabentext.setLineWrap(true);

        txtaLoesungshinweis = new JTextArea();
        txtaLoesungshinweis.setLineWrap(true);

        txtaLoesung = new JTextArea();
        txtaLoesung.setLineWrap(true);

        txtfTitel = new JTextField();
        txtfBearbeitungszeit = new JTextField();
        txtfPunkte = new JTextField();

        Kategorie[] kat = {Kategorie.Java_Programmierung, Kategorie.Datenbanken, Kategorie.Software_Engineering, Kategorie.Java_Grundlagen,};
        cbKategorien = new JComboBox(kat);

        Schwierigkeitsgrad[] schw = {Schwierigkeitsgrad.Leicht, Schwierigkeitsgrad.Schwer, Schwierigkeitsgrad.Mittel};
        cbSchwierigkeit = new JComboBox(schw);

        lblBeispielBild = new JLabel("Beispiel Bild Hochladen: ");
        lblKategorie = new JLabel("Kategorie: ");
        lblTitel = new JLabel("Aufgaben Titel");
        lblLoesungshinweis = new JLabel("Lösungshinweis: ");
        lblSchwierigkeit = new JLabel("Schwierigkeit: ");
        lblBearbeitungszeit = new JLabel("BearbeitungsZeit: ");
        lblPunkte = new JLabel("Punkte: ");
        lblLoesung = new JLabel("Lösung");
        lblAufgabentext = new JLabel("Aufgaben Text");

        pnlCenter.add(lblTitel);
        pnlCenter.add(txtfTitel);
        pnlCenter.add(lblAufgabentext);
        pnlCenter.add(txtaAufgabentext);
        pnlCenter.add(lblLoesung);
        pnlCenter.add(txtaLoesung);
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

        pnlNorth.add(btnZurueck);
        pnlSouth.add(btnSpeichern);

        pnlAufgabeErstellenEinfachAntwortView.add(pnlCenter, BorderLayout.CENTER);
        pnlAufgabeErstellenEinfachAntwortView.add(pnlNorth, BorderLayout.NORTH);
        pnlAufgabeErstellenEinfachAntwortView.add(pnlSouth, BorderLayout.SOUTH);
        this.add(pnlAufgabeErstellenEinfachAntwortView);
    }

    /**
     * Führt Aktionen der JButtons sowie der JComboBoxen aus und ruft ggf. weitere Methoden auf.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnZurueck) {
            zurueck();
        } else if (e.getSource() == this.btnSpeichern) {
            speichern();
        } else if (e.getSource() == this.btnBeispielBild) {
            FileChooserAuslagerung filcV = new FileChooserAuslagerung();
            fileCodeBsp = filcV.fileChooser();
            if(fileCodeBsp != null)
                byteArrayBeispielBild = DatabaseService.convertFileToByteArray(fileCodeBsp, this);
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
     * Ruft die daten aus den JTextAreas sowie aus den JTextFields auf. Setzt diese in die Instanzvariablen ein.
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
        String loesung = null;

        try {
            aufgTitel = txtfTitel.getText();
            aufText = txtaAufgabentext.getText();
            loesungshinweis = txtaLoesungshinweis.getText();
            bearbeitungsZeit = Integer.parseInt(txtfBearbeitungszeit.getText());
            schw = (Schwierigkeitsgrad) cbSchwierigkeit.getSelectedItem();
            kat = (Kategorie) cbKategorien.getSelectedItem();
            punkte = Integer.parseInt(txtfPunkte.getText());
            loesung = txtaLoesung.getText();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eine Eingabe entsprach nicht dem nötigen Datentyp", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (AufgabeErstellenStartView.inputcleaner(bearbeitungsZeit, punkte, this) && !aufgTitel.isEmpty() && !loesung.isEmpty()) {
            createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte, kat, schw, loesung);
            this.dispose();
            aufgabeErstellenStartViewFrame.setVisible(true);
        }
    }

    /**
     * Erstellt eine Aufgabe vom Typ Einfachantwortaufgabe. Erstellt eine Musterlösung für den Typen, verknüpft die Aufgabe und die Musterlösung. Speichert beide über den DatabaseService in die Datenbank.
     *
     * @param aufgTitel        Titel der Aufgabe
     * @param aufText          Text der Aufgabe
     * @param loesungshinweis  loesungshinweis der Aufgabe
     * @param bearbeitungsZeit bearbeitungszeit der Aufgabe
     * @param punkte           maximale punkte der Aufgabe
     * @param kat              kategorie der Aufgabe
     * @param schw             schwierigkeit der Aufgabe
     * @param loesung          loesung der Aufgabe
     */
    private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte, Kategorie kat, Schwierigkeitsgrad schw, String loesung) {

        DatabaseService ds = DatabaseService.getInstance();
        EinfachantwortAufgabe neueAufgabe = new EinfachantwortAufgabe(bearbeitungsZeit, byteArrayBeispielBild, kat, punkte, schw, aufText, aufgTitel, doz, null);
        doz.addErstellteAufgabe(neueAufgabe);
        MusterloesungEinfachantwort mlp = new MusterloesungEinfachantwort(neueAufgabe, loesungshinweis, loesung);
        try {
            neueAufgabe.setMusterloesung(mlp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Musterlösung setzten fehlgeschlagen", "Error", JOptionPane.ERROR_MESSAGE);
        }

        ds.persistObject(neueAufgabe);
        ds.persistObject(mlp);
    }
}
