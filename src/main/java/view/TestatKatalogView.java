package view;

import view.tableModel.TestatTableModel;
import entity.aufgabensammlung.Testat;
import entity.benutzer.Dozent;
import persistence.DatabaseService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Ansicht in der die bestehenden Testate administriert und neue hinzugefügt werden können.
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public class TestatKatalogView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable tableTestate;
    private TestatTableModel testatTableModel;
    private JButton btnZurueck;
    private JButton btnErstellen;
    private JButton btnAufgabenEinsehen;
    private JButton btnLoeschen;
    private Dozent aktuellerBenutzer;
    private List<Testat> testatliste;
    private JFrame jframe;

    /**
     * Konstruktor, der den Frame erstellt
     *
     * @param jframe            Hauptmenü-Frame, auf den beim Drücken des Zurück-Buttons zurückgekehrt werden soll
     * @param aktuellerBenutzer aktuell angemeldeter Benutzer
     */
    public TestatKatalogView(JFrame jframe, Dozent aktuellerBenutzer) {
        this.jframe = jframe;
        this.aktuellerBenutzer = aktuellerBenutzer;
        testatliste = DatabaseService.getInstance().readTestateFromDatabase();
        testatliste = new LinkedList<Testat>(testatliste);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Testatkatalog");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);

        GridBagLayout gbl_panelNorth = new GridBagLayout();
        gbl_panelNorth.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_panelNorth.rowHeights = new int[]{0, 0};
        gbl_panelNorth.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
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

        JPanel panelCenterLeftNorth = new JPanel();
        GridBagConstraints gbc_panelCenterLeftNorth = new GridBagConstraints();
        gbc_panelCenterLeftNorth.anchor = GridBagConstraints.CENTER;
        gbc_panelCenterLeftNorth.fill = GridBagConstraints.VERTICAL;
        gbc_panelCenterLeftNorth.gridx = 1;
        gbc_panelCenterLeftNorth.gridy = 0;
        panelNorth.add(panelCenterLeftNorth, gbc_panelCenterLeftNorth);

        btnErstellen = new JButton("Neues Testat erstellen");
        panelCenterLeftNorth.add(btnErstellen);

        JPanel panelCenterRightNorth = new JPanel();
        GridBagConstraints gbc_panelCenterRightNorth = new GridBagConstraints();
        gbc_panelCenterRightNorth.anchor = GridBagConstraints.CENTER;
        gbc_panelCenterRightNorth.fill = GridBagConstraints.VERTICAL;
        gbc_panelCenterRightNorth.gridx = 2;
        gbc_panelCenterRightNorth.gridy = 0;
        panelNorth.add(panelCenterRightNorth, gbc_panelCenterRightNorth);

        btnAufgabenEinsehen = new JButton("Aufgaben einsehen");
        panelCenterRightNorth.add(btnAufgabenEinsehen);

        JPanel panelRightNorth = new JPanel();
        GridBagConstraints gbc_panelRightNorth = new GridBagConstraints();
        gbc_panelRightNorth.anchor = GridBagConstraints.EAST;
        gbc_panelRightNorth.fill = GridBagConstraints.VERTICAL;
        gbc_panelRightNorth.gridx = 3;
        gbc_panelRightNorth.gridy = 0;
        panelNorth.add(panelRightNorth, gbc_panelRightNorth);

        btnLoeschen = new JButton("Löschen");
        panelRightNorth.add(btnLoeschen);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableTestate = new JTable();
        testatTableModel = new TestatTableModel(testatliste);
        tableTestate.setModel(testatTableModel);
        tableTestate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableTestate);

        btnLoeschen.addActionListener(this);
        btnErstellen.addActionListener(this);
        btnAufgabenEinsehen.addActionListener(this);
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
        if (e.getSource() == this.btnErstellen) {
            erstellenButtonLogik();
        }
        if (e.getSource() == this.btnAufgabenEinsehen) {
            aufgabenEinsehenButtonLogik();
        }
        if (e.getSource() == this.btnLoeschen) {
            loeschenButtonLogik();
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
     * Beinhaltet die Logik des Erstellen-Buttons
     */
    private void erstellenButtonLogik() {
        new TestatErstellenView(jframe, aktuellerBenutzer);
        dispose();
    }

    /**
     * Beinhaltet die Logik des Aufgaben-Einsehen-Buttons
     */
    private void aufgabenEinsehenButtonLogik() {
        if (testatliste.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es gibt keine Testate, in denen die Aufgaben eingesehen werden können.", "Keine Testate", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedRow = tableTestate.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Es wurde kein Testat zum Einsehen der Aufgaben ausgewählt.", "Kein Testat ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                Testat testat = testatliste.get(selectedRow);
                if(testat.getAnzahlAufgaben() == 0) {
                    JOptionPane.showMessageDialog(this, "Das gewählte Testat enthält keine Aufgaben.", "Kein Aufgaben einsehbar", JOptionPane.WARNING_MESSAGE);
                } else {
                    new AufgabenEinesTestatsView(testat);
                }
            }
        }
    }

    /**
     * Beinhaltet die Logik des Löschen-Buttons
     */
    private void loeschenButtonLogik() {
        if (testatliste.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es gibt keine Testate zum Löschen", "Keine Testate", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedRow = tableTestate.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Es wurde kein Testat zum Löschen ausgewählt", "Kein Testat ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                boolean loeschenGewuenscht = false;
                Testat testat = testatliste.get(selectedRow);
                if (!testat.darfDozentTestatLoeschen(aktuellerBenutzer)) {
                    JOptionPane.showMessageDialog(this, "Sie sind nicht berechtigt, dieses Testat zu löschen", "Fehlende Berechtigung", JOptionPane.WARNING_MESSAGE);
                } else if (testat.getBearbeitungen() != null && testat.getBearbeitungen().size() > 0) {
                    loeschenGewuenscht = (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Wollen Sie das Testat wirklich löschen?\nAchtung! Es gibt zu ihm Testatbearbeitungen, die beim Löschen ebenfalls gelöscht werden.", "Testat löschen", JOptionPane.YES_NO_OPTION));
                } else {
                    loeschenGewuenscht = (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Wollen Sie das Testat wirklich löschen?", "Testat löschen", JOptionPane.YES_NO_OPTION));
                }
                if (loeschenGewuenscht) {
                    testatliste.remove(testat);
                    testatTableModel.fireTableDataChanged();
                    DatabaseService.getInstance().saveDeleteTestatFromDatabase(testat);
                    JOptionPane.showMessageDialog(this, "Das ausgewählte Testat wurde gelöscht", "Testat gelöscht", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

}