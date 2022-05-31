package view.aufgabenErstellen;

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
 * @version 15.05.2022 switch zu extends JFRame, Dozentübergabe gemacht, Musterlösung eingebunden, Filechooser ausgelagert, TA teilweise zu TF gemacht
 */
public class ErstelleDesignaufgabeView extends JFrame implements ActionListener {

    private Dozent doz;
    private JFrame aufgabeErstellenStartViewFrame;
    private JPanel pnlAufgabeErstellenUML;
    private JPanel pnlCenter;
    private JPanel pnlNorth;
    private JPanel pnlSouth;
    private BorderLayout borderLayout = new BorderLayout();
    private GridLayout gridLayout = new GridLayout(10, 2);

    private JButton btnZurueck;
    private JButton btnSpeichern;
    private JButton btnUMLHochladen;
    private JButton btnMusterloesung;
    private JComboBox cbKategorien;
    private JComboBox cbSchwierigkeit;
    private JLabel lblTitel;
    private JLabel lblAufgabentext;
    private JLabel lblLoesungshinweis;
    private JLabel lblSchwierigkeit;
    private JLabel lblKategorien;
    private JLabel lblBearbeitungszeit;
    private JLabel lblPunkte;
    private JTextField txtfTitel;
    private JTextArea txtaAufgabentext;
    private JTextArea txtaLoesungshinweis;
    private JTextField txtfBearbeitungszeit;
    private JTextField txtfPunkte;
    private File fileDesign;
    private File fileMusterloesung;

    /**
     * Konstruktor der Klasse, benötigt einen Dozenten und den vorherigen JFrame.
     * Setzt Parameter des JFrames und ruft AufgabeErstellenUMLViewFuellen() auf.
     */
    public ErstelleDesignaufgabeView(JFrame aufgabeErstellenStartViewFrame, Dozent doz) {
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

    /**
     * Erstellt die Komponenten des JFrames sowie JPanels welche später eingefügt werden. Und fügt die Componenten in den Frame ein.
     */
    private void AufgabeErstellenUMLViewFuellen() {

        gridLayout.setVgap(25);
        gridLayout.setHgap(25);
        pnlCenter = new JPanel(gridLayout);
        pnlNorth = new JPanel();
        pnlSouth = new JPanel();
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        pnlAufgabeErstellenUML = new JPanel();
        pnlAufgabeErstellenUML.setLayout(borderLayout);

        btnZurueck = new JButton("Zurück");
        btnZurueck.addActionListener(this);

        btnSpeichern = new JButton("Speichern");
        btnSpeichern.addActionListener(this);

        btnUMLHochladen = new JButton("Design Hochladen");
        btnUMLHochladen.addActionListener(this);

        btnMusterloesung = new JButton("Musterlösung Hochladen");
        btnMusterloesung.addActionListener(this);

        txtaAufgabentext = new JTextArea();
        txtaAufgabentext.setLineWrap(true);

        txtaLoesungshinweis = new JTextArea();
        txtaLoesungshinweis.setLineWrap(true);

        txtfPunkte = new JTextField();
        txtfBearbeitungszeit = new JTextField();
        txtfTitel = new JTextField();

        Kategorie[] kat = {Kategorie.Java_Programmierung, Kategorie.Datenbanken, Kategorie.Software_Engineering, Kategorie.Java_Grundlagen,};
        cbKategorien = new JComboBox(kat);

        Schwierigkeitsgrad[] schw = {Schwierigkeitsgrad.Leicht, Schwierigkeitsgrad.Schwer, Schwierigkeitsgrad.Mittel};
        cbSchwierigkeit = new JComboBox(schw);

        lblKategorien = new JLabel("Kategorie: ");
        lblTitel = new JLabel("Aufgaben Titel");
        lblLoesungshinweis = new JLabel("Lösungshinweis: ");
        lblSchwierigkeit = new JLabel("Schwierigkeit: ");
        lblBearbeitungszeit = new JLabel("BearbeitungsZeit: ");
        lblPunkte = new JLabel("Punkte: ");
        lblAufgabentext = new JLabel("Aufgaben Text");

        pnlCenter.add(lblTitel);
        pnlCenter.add(txtfTitel);
        pnlCenter.add(lblAufgabentext);
        pnlCenter.add(txtaAufgabentext);
        pnlCenter.add(lblKategorien);
        pnlCenter.add(cbKategorien);
        pnlCenter.add(btnUMLHochladen);
        pnlCenter.add(btnMusterloesung);
        pnlCenter.add(lblSchwierigkeit);
        pnlCenter.add(cbSchwierigkeit);
        pnlCenter.add(lblBearbeitungszeit);
        pnlCenter.add(txtfBearbeitungszeit);
        pnlCenter.add(lblPunkte);
        pnlCenter.add(txtfPunkte);
        pnlCenter.add(lblLoesungshinweis);
        pnlCenter.add(txtaLoesungshinweis);

        pnlNorth.add(btnZurueck);
        pnlSouth.add(btnSpeichern);

        pnlAufgabeErstellenUML.add(pnlCenter, BorderLayout.CENTER);
        pnlAufgabeErstellenUML.add(pnlNorth, BorderLayout.NORTH);
        pnlAufgabeErstellenUML.add(pnlSouth, BorderLayout.SOUTH);
        this.add(pnlAufgabeErstellenUML);
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
        } else if (e.getSource() == this.btnUMLHochladen) {
            FileChooserAuslagerung filcV = new FileChooserAuslagerung();
            fileDesign = filcV.fileChooser();
        } else if (e.getSource() == this.btnMusterloesung) {
            FileChooserAuslagerung filcV = new FileChooserAuslagerung();
            fileMusterloesung = filcV.fileChooser();
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

        try {
            aufgTitel = txtfTitel.getText();
            aufText = txtaAufgabentext.getText();
            loesungshinweis = txtaLoesungshinweis.getText();
            bearbeitungsZeit = Integer.parseInt(txtfBearbeitungszeit.getText());
            schw = (Schwierigkeitsgrad) cbSchwierigkeit.getSelectedItem();
            kat = (Kategorie) cbKategorien.getSelectedItem();
            punkte = Integer.parseInt(txtfPunkte.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eine Eingabe entsprach nicht dem nötigen Datentyp", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (ErstelleAufgabeStartView.inputcleaner(bearbeitungsZeit, punkte, this) && !aufgTitel.isEmpty()) {
            createObjectandPersist(aufgTitel, aufText, loesungshinweis, bearbeitungsZeit, punkte, kat, schw);
        }
    }

    /**
     * Erstellt eine Aufgabe vom Typ Designaufgabe, Erstellt eine Musterlösung für den Typen, verknüpft die Aufgabe und die Musterlösung. Speichert beide über den DatabaseService in die Datenbank.
     *
     * @param aufgTitel        Titel der aufgabe
     * @param aufText          AufgabenText
     * @param loesungshinweis  loesungshinweis der Aufgabe
     * @param bearbeitungsZeit bearbeitungszeit der Aufgabe
     * @param punkte           maximale punkte der Aufgabe
     * @param kat              kategorie der Aufgabe
     * @param schw             Schwierigkeitsgrd der Aufgabe
     */
    private void createObjectandPersist(String aufgTitel, String aufText, String loesungshinweis, int bearbeitungsZeit, int punkte, Kategorie kat, Schwierigkeitsgrad schw) {
        if(fileDesign != null && fileMusterloesung != null) {
            DatabaseService ds = DatabaseService.getInstance();
            byte[] designByteArray = DatabaseService.convertFileToByteArray(fileDesign, this);
            byte[] musterloesungByteArray = DatabaseService.convertFileToByteArray(fileMusterloesung, this);
            Designaufgabe neueAufgabe = new Designaufgabe(bearbeitungsZeit, designByteArray, kat, punkte, schw, aufText, aufgTitel, doz, null);
            doz.addErstellteAufgabe(neueAufgabe);
            MusterloesungDesignaufgabe mlp = new MusterloesungDesignaufgabe(neueAufgabe, loesungshinweis, musterloesungByteArray);

            try {
                neueAufgabe.setMusterloesung(mlp);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Musterlösung setzten fehlgeschlagen überprüfen sie ihre eingaben", "Error", JOptionPane.ERROR_MESSAGE);
            }
            ds.persistObject(neueAufgabe);
            ds.persistObject(mlp);
            this.dispose();
            aufgabeErstellenStartViewFrame.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(this, "Sie müssen einen DesignFile und einen MusterlösungsFile auswählen", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}