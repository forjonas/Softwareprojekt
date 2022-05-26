package View;

import View.Lösungen.BewertungenTestat.ControllerBewertungenTestate;
import View.tableModel.MeineTestateTableModel;
import app.TestatController;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.benutzer.Benutzer;
import persistence.DatabaseService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ansicht in der aus einer Tabelle eine einzelne Aufgabe zur Bearbeitung ausgewählt werden kann.
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public class MeineTestateKatalogView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable tableTestate;
    private MeineTestateTableModel meineTestateTableModel;
    private JButton btnZurueck;
    private JButton btnBewertungEinsehen;
    private JButton btnBearbeiten;
    private List<Testat> testatListe;
    private Benutzer aktuellerBenutzer;
    private JFrame jframe;

    /**
     * Konstruktor, der den Frame erstellt
     *
     * @param jframe            Hauptmenü-Frame, auf den beim Drücken des Zurück-Buttons zurückgekehrt werden soll
     * @param aktuellerBenutzer aktuell angemeldeter Benutzer
     */
    public MeineTestateKatalogView(JFrame jframe, Benutzer aktuellerBenutzer) {
        this.jframe = jframe;
        this.aktuellerBenutzer = aktuellerBenutzer;
        testatListe = DatabaseService.getInstance().readTestateFromDatabase();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Meine Testat");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        GridBagLayout gbl_panelNorth = new GridBagLayout();
        gbl_panelNorth.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panelNorth.rowHeights = new int[]{0, 0};
        gbl_panelNorth.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_panelNorth.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panelNorth.setLayout(gbl_panelNorth);

        JPanel panelLeftNorth = new JPanel();
        GridBagConstraints gbc_panelLeftNorth = new GridBagConstraints();
        gbc_panelLeftNorth.anchor = GridBagConstraints.WEST;
        gbc_panelLeftNorth.fill = GridBagConstraints.VERTICAL;
        gbc_panelLeftNorth.gridx = 0;
        gbc_panelLeftNorth.gridy = 0;
        panelNorth.add(panelLeftNorth, gbc_panelLeftNorth);

        btnZurueck = new JButton("Zurück");
        panelLeftNorth.add(btnZurueck);

        JPanel panelCenterNorth = new JPanel();
        GridBagConstraints gbc_panelCenterNorth = new GridBagConstraints();
        gbc_panelCenterNorth.anchor = GridBagConstraints.CENTER;
        gbc_panelCenterNorth.fill = GridBagConstraints.VERTICAL;
        gbc_panelCenterNorth.gridx = 1;
        gbc_panelCenterNorth.gridy = 0;
        panelNorth.add(panelCenterNorth, gbc_panelCenterNorth);

        btnBewertungEinsehen = new JButton("Bewertung einsehen");
        panelCenterNorth.add(btnBewertungEinsehen);

        JPanel panelRightNorth = new JPanel();
        GridBagConstraints gbc_panelRightNorth = new GridBagConstraints();
        gbc_panelRightNorth.anchor = GridBagConstraints.EAST;
        gbc_panelRightNorth.fill = GridBagConstraints.VERTICAL;
        gbc_panelRightNorth.gridx = 2;
        gbc_panelRightNorth.gridy = 0;
        panelNorth.add(panelRightNorth, gbc_panelRightNorth);

        btnBearbeiten = new JButton("Bearbeiten");
        panelRightNorth.add(btnBearbeiten);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableTestate = new JTable();
        meineTestateTableModel = new MeineTestateTableModel(testatListe, this.aktuellerBenutzer);
        tableTestate.setModel(meineTestateTableModel);
        tableTestate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableTestate);

        btnBearbeiten.addActionListener(this);
        btnBewertungEinsehen.addActionListener(this);
        btnZurueck.addActionListener(this);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);
    }

    /**
     * Wird ausgeführt, wenn ein ActionEvent auftritt
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnZurueck) {
            zurueckButtonLogik();
        }
        if (e.getSource() == this.btnBewertungEinsehen) {
            bewertungEinsehenButtonLogik();
        }
        if (e.getSource() == this.btnBearbeiten) {
            bearbeitenButtonLogik();
        }
    }

    /**
     * Beinhaltet die Logik des Zurück-Buttons
     */
    private void zurueckButtonLogik() {
        jframe.setVisible(true);
        dispose();
    }

    /**
     * Beinhaltet die Logik des Einsehen-Buttons
     */
    private void bewertungEinsehenButtonLogik() {
        if (testatListe.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es gibt keine bewerteten Testate zum Einsehen", "Keine Testate", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedRow = tableTestate.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Es wurde kein bewertetes Testat zum Einsehen ausgewählt", "Keine Testat ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                Testat testat = testatListe.get(selectedRow);
                if (!testat.isTestatVonUserBearbeitetWorden(aktuellerBenutzer)) {
                    JOptionPane.showMessageDialog(this, "Sie haben dieses Testat noch nicht bearbeitet", "Testat noch nicht bearbeitet", JOptionPane.WARNING_MESSAGE);
                } else {
                    TestatBearbeitung testatBearbeitung = testat.getBearbeitungVonBenutzer(aktuellerBenutzer);
                    if (testatBearbeitung.getTestatBewerter() == null) {
                        JOptionPane.showMessageDialog(this, "Ihre Bearbeitung dieses Testats wurde noch nicht bewertet", "Testatbearbeitung noch nicht bewertet", JOptionPane.WARNING_MESSAGE);
                    } else if (testat.getAufgaben().size() <= 0) {
                        JOptionPane.showMessageDialog(this, "Fehler: Testat enthält keine Aufgaben", "Testat konnte nicht geöffnet werden", JOptionPane.ERROR_MESSAGE);
                    } else {
                        new ControllerBewertungenTestate(testatBearbeitung, aktuellerBenutzer, jframe);
                        dispose();
                    }
                }
            }
        }
    }

    /**
     * Beinhaltet die Logik des Bearbeiten-Buttons
     */
    private void bearbeitenButtonLogik() {
        if (testatListe.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es gibt keine Testate zum Bearbeiten", "Keine Testate", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedRow = tableTestate.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Es wurde kein Testat zum Bearbeiten ausgewählt", "Keine Testat ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                Testat testat = testatListe.get(selectedRow);
                if (testat.isTestatVonUserBearbeitetWorden(aktuellerBenutzer)) {
                    JOptionPane.showMessageDialog(this, "Sie haben dieses Testat bereits bearbeitet", "Testat bereits bearbeitet", JOptionPane.WARNING_MESSAGE);
                } else if (testat.getAufgaben().size() <= 0) {
                    JOptionPane.showMessageDialog(this, "Fehler: Testat enthält keine Aufgaben", "Testat konnte nicht geöffnet werden", JOptionPane.ERROR_MESSAGE);
                } else {
                    String passwort = JOptionPane.showInputDialog(this, "Bitte Passwort für das Testat eingeben:", "Passwort eingeben", JOptionPane.INFORMATION_MESSAGE);
                    if (passwort != null && passwort.equals(testat.getPasswort())) {
                        TestatController testatController = new TestatController(testat, aktuellerBenutzer, jframe);
                        testatController.zeigeAktuelleAufgabe(); //Methoden aufruf hinzugefuegt
                        dispose();
                    } else if (passwort != null) {
                        JOptionPane.showMessageDialog(this, "Fehler: Falsches Passwort eingegeben", "Falsches Passwort", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

}