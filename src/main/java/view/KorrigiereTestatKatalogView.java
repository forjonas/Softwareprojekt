package view;

import controller.BewertungenTestateController;
import view.tableModel.KorrigiereTestatTableModel;
import entity.aufgabensammlung.TestatBearbeitung;
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
 * Ansicht in der aus einer Tabelle ein Testat zum Korrigieren ausgewählt werden kann.
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public class KorrigiereTestatKatalogView extends JFrame implements ActionListener {

    private Dozent aktuellerBenutzer;
    private List<TestatBearbeitung> testatBearbeitungsListe;
    private JFrame jframe;
    private JPanel contentPane;
    private JTable tableTestate;
    private KorrigiereTestatTableModel korrigiereTestatTableModel;
    private JButton btnZurueck;
    private JButton btnKorrigieren;

    /**
     * Konstruktor, der den Frame erstellt
     *
     * @param jframe            Hauptmenü-Frame, auf den beim Drücken des Zurück-Buttons zurückgekehrt werden soll
     * @param aktuellerBenutzer aktuell angemeldeter Benutzer
     */
    public KorrigiereTestatKatalogView(JFrame jframe, Dozent aktuellerBenutzer) {
        this.jframe = jframe;
        this.aktuellerBenutzer = aktuellerBenutzer;
        testatBearbeitungsListe = DatabaseService.getInstance().readTestatBearbeitungenFromDatabase();
        testatBearbeitungsListe = new LinkedList<TestatBearbeitung>(testatBearbeitungsListe);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Korrigiere Testat");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        GridBagLayout gbl_panelNorth = new GridBagLayout();
        gbl_panelNorth.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panelNorth.rowHeights = new int[]{0, 0};
        gbl_panelNorth.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
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

        JPanel panelRightNorth = new JPanel();
        GridBagConstraints gbc_panelCenterNorth = new GridBagConstraints();
        gbc_panelCenterNorth.anchor = GridBagConstraints.EAST;
        gbc_panelCenterNorth.fill = GridBagConstraints.VERTICAL;
        gbc_panelCenterNorth.gridx = 1;
        gbc_panelCenterNorth.gridy = 0;
        panelNorth.add(panelRightNorth, gbc_panelCenterNorth);

        btnKorrigieren = new JButton("Korrigieren");
        panelRightNorth.add(btnKorrigieren);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableTestate = new JTable();
        korrigiereTestatTableModel = new KorrigiereTestatTableModel(testatBearbeitungsListe);
        tableTestate.setModel(korrigiereTestatTableModel);
        tableTestate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableTestate);

        btnKorrigieren.addActionListener(this);
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
        if (e.getSource() == this.btnKorrigieren) {
            korrigierenButtonLogik();
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
     * Beinhaltet die Logik des Korrigieren-Buttons
     */
    private void korrigierenButtonLogik() {
        if (testatBearbeitungsListe.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es gibt keine Testatbearbeitungen zum Korrigieren", "Keine Testatbearbeitungen", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedRow = tableTestate.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Es wurde keine Testatbearbeitung zum Korrigieren ausgewählt", "Keine Testatbearbeitung ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                TestatBearbeitung testatBearbeitung = testatBearbeitungsListe.get(selectedRow);
                if (testatBearbeitung.getTestatBewerter() != null) {
                    JOptionPane.showMessageDialog(this, "Die Testatbearbeitung wurde bereits korrigiert", "Testatbearbeitung bereits korrigiert", JOptionPane.WARNING_MESSAGE);
                }
                else if (!testatBearbeitung.darfDozentTestatBearbeitungBewerten(aktuellerBenutzer)) {
                    JOptionPane.showMessageDialog(this, "Sie verfügen nicht über die Berechtigung, die Testatbearbeitung zu korrigieren", "Fehlende Berechtigung", JOptionPane.WARNING_MESSAGE);
                }
                else if (testatBearbeitung.getTestat() == null || testatBearbeitung.getTestat().getAnzahlAufgaben() == 0) {
                    JOptionPane.showMessageDialog(this, "Fehler: Testatbearbeitung enthält keine Aufgaben", "Testatbearbeitung konnte nicht geöffnet werden", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    new BewertungenTestateController(testatBearbeitung,aktuellerBenutzer, jframe);
                    dispose();
                }
            }
        }
    }

}