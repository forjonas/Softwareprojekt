package view.aufgabenErstellen;

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
 * Die View zur Erstellung einer Codeaufgabe
 *
 * @author Jannik Oehme
 * @version 15.05.2022 switch zu extends JFrame, Dozentübergabe gemacht, Musterlösung eingebunden, Filechooser ausgelagert, TA teilweise zu TF gemacht
 */
public class AufgabeErstellenCodeView extends JFrame implements ActionListener {
    private Dozent doz;
    private JFrame aufgabeErstellenStartViewFrame;
    private JPanel pnlAufgabeErstellenCode;
    private BorderLayout borderLayout = new BorderLayout();
    private GridLayout gridLayout = new GridLayout(10, 2);
    private JPanel pnlCenter;
    private JPanel pnlNorth;
    private JPanel pnlSouth;

    private JButton btnCodeHoch;
    private JButton btnZurueck;
    private JButton btnSpeichern;
    private JComboBox cbKategorien;
    private JComboBox cbSchwierigkeit;
    private JLabel lblCodebeispielHoch;
    private JLabel lblCodeBeispiel;
    private JLabel lblKategorie;
    private JLabel lblTitel;
    private JLabel lblAufgabentext;
    private JLabel lblLoesungshinweis;
    private JLabel lblSchwierigkeit;
    private JLabel lblBearbeitungszeit;
    private JLabel lblPunkte;
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
     * Konstruktor der Klasse, benötigt einen Dozenten und den vorherigen JFrame. Setzt Parameter des JFrames und ruft AufgabeErstellenCodeViewFuellen() auf.
     *
     * @param doz                            Ein Dozent
     * @param aufgabeErstellenStartViewFrame dervorherige Frame
     */
    public AufgabeErstellenCodeView(JFrame aufgabeErstellenStartViewFrame, Dozent doz) {
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

    /**
     * Erstellt die Komponenten des JFrames sowie JPanels welche später eingefügt werden. Und fügt die Komponenten in den Frame ein.
     */
    private void AufgabeErstellenCodeViewFuellen() {
        gridLayout.setVgap(25);
        gridLayout.setHgap(25);
        pnlCenter = new JPanel(gridLayout);
        pnlNorth = new JPanel();
        pnlSouth = new JPanel();

        pnlAufgabeErstellenCode = new JPanel();
        pnlAufgabeErstellenCode.setLayout(borderLayout);
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        btnCodeHoch = new JButton("Optionales Java Design Hochladen");
        btnCodeHoch.addActionListener(this);

        btnZurueck = new JButton("Zurück");
        btnZurueck.addActionListener(this);

        btnSpeichern = new JButton("Speichern");
        btnSpeichern.addActionListener(this);

        Kategorie[] kat = {Kategorie.Java_Programmierung, Kategorie.Datenbanken, Kategorie.Software_Engineering, Kategorie.Java_Grundlagen,};
        cbKategorien = new JComboBox(kat);

        Schwierigkeitsgrad[] schw = {Schwierigkeitsgrad.Leicht, Schwierigkeitsgrad.Schwer, Schwierigkeitsgrad.Mittel};
        cbSchwierigkeit = new JComboBox(schw);

        txtaLoesung = new JTextArea();
        txtaLoesung.setLineWrap(true);

        txtaAufgabentext = new JTextArea(50, 50);
        txtaAufgabentext.setLineWrap(true);

        txtaLoesungshinweis = new JTextArea();
        txtaLoesungshinweis.setLineWrap(true);

        txtfTitel = new JTextField();
        txtfBearbeitungszeit = new JTextField();
        txtfPunkte = new JTextField();

        lblCodebeispielHoch = new JLabel("Optionales Java Design ");
        lblKategorie = new JLabel("Kategorien");
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
        pnlCenter.add(lblKategorie);
        pnlCenter.add(cbKategorien);
        pnlCenter.add(lblSchwierigkeit);
        pnlCenter.add(cbSchwierigkeit);
        pnlCenter.add(lblCodebeispielHoch);
        pnlCenter.add(btnCodeHoch);
        pnlCenter.add(lblBearbeitungszeit);
        pnlCenter.add(txtfBearbeitungszeit);
        pnlCenter.add(lblPunkte);
        pnlCenter.add(txtfPunkte);
        pnlCenter.add(lblLoesungshinweis);
        pnlCenter.add(txtaLoesungshinweis);

        pnlNorth.add(btnZurueck);
        pnlSouth.add(btnSpeichern);

        pnlAufgabeErstellenCode.add(pnlCenter, BorderLayout.CENTER);
        pnlAufgabeErstellenCode.add(pnlNorth, BorderLayout.NORTH);
        pnlAufgabeErstellenCode.add(pnlSouth, BorderLayout.SOUTH);
        this.add(pnlAufgabeErstellenCode);
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
        } else if (e.getSource() == this.btnCodeHoch) {
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
     * Ruft die Daten aus den JTextAreas sowie aus den JTextFields auf. Setzt diese in die Instanzvariablen ein.
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
        else {
            JOptionPane.showMessageDialog(this, "Eine eingabe Fehlt", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Erstellt eine Aufgabe vom Typ Programmieraufgabe. Erstellt eine Musterlösung für den Typen, verknüpft die Aufgabe und die Musterlösung. Speichert beide über den DatabaseService in die Datenbank.
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
        Programmieraufgabe neueAufgabe = new Programmieraufgabe(bearbeitungsZeit, byteArrayBeispielBild, kat, punkte, schw, aufText, aufgTitel, doz, null);
        MusterloesungProgrammieraufgabe mlp = new MusterloesungProgrammieraufgabe(neueAufgabe, loesungshinweis, loesung);
        doz.addErstellteAufgabe(neueAufgabe);
        try {
            neueAufgabe.setMusterloesung(mlp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Musterlösung setzten fehlgeschlagen", "Error", JOptionPane.ERROR_MESSAGE);
        }

        ds.persistObject(neueAufgabe);
        ds.persistObject(mlp);
    }
}
