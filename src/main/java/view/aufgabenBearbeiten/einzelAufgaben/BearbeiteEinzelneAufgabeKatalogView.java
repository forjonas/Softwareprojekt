package view.aufgabenBearbeiten.einzelAufgaben;

import view.tableModel.BearbeiteAufgabeTableModel;
import entity.aufgabe.*;
import entity.benutzer.Benutzer;
import persistence.DatabaseService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ansicht, in der aus einer Tabelle eine einzelne Aufgabe zur Bearbeitung ausgewählt werden kann.
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public class BearbeiteEinzelneAufgabeKatalogView extends JFrame implements ActionListener {

    private Benutzer aktuellerBenutzer;
    private List<Aufgabe> aufgabenliste;
    private JFrame jFrame;
    private JPanel contentPane;
    private JTable tableAufgaben;
    private BearbeiteAufgabeTableModel bearbeiteAufgabeTableModel;
    private JButton btnZurueck;
    private JButton btnBearbeiten;

    /**
     * Konstruktor, der den Frame erstellt
     *
     * @param jframe            Hauptmenü-Frame, auf den beim Drücken des Zurück-Buttons zurückgekehrt werden soll
     * @param aktuellerBenutzer aktuell angemeldeter Benutzer
     */
    public BearbeiteEinzelneAufgabeKatalogView(JFrame jframe, Benutzer aktuellerBenutzer) {
        this.jFrame = jframe;
        DatabaseService ds = DatabaseService.getInstance();
        this.aktuellerBenutzer = aktuellerBenutzer;
        aufgabenliste = ds.readAufgabenFromDatabase();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bearbeite einzelne Aufgabe");
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

        btnBearbeiten = new JButton("Bearbeiten");
        panelRightNorth.add(btnBearbeiten);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableAufgaben = new JTable();
        bearbeiteAufgabeTableModel = new BearbeiteAufgabeTableModel(aufgabenliste);
        tableAufgaben.setModel(bearbeiteAufgabeTableModel);
        tableAufgaben.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableAufgaben);

        btnBearbeiten.addActionListener(this);
        btnZurueck.addActionListener(this);

        super.pack();
        this.setMinimumSize(new Dimension(650, 650));
        this.setSize(this.getMinimumSize());
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
        if (e.getSource() == this.btnBearbeiten) {
            bearbeitenButtonLogik();
        }
    }

    /**
     * Beinhaltet die Logik des Zurück-Buttons
     */
    private void zurueckButtonLogik() {
        jFrame.setVisible(true);
        dispose();
    }

    /**
     * Beinhaltet die Logik des Bearbeiten-Buttons
     */
    private void bearbeitenButtonLogik() {
        if (aufgabenliste.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es gibt keine Aufgaben zum Bearbeiten", "Keine Aufgaben", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedRow = tableAufgaben.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Es wurde keine Aufgabe zum Bearbeiten ausgewählt", "Keine Aufgabe ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                Aufgabe aufgabe = aufgabenliste.get(selectedRow);
                switch (aufgabe.getAufgabentyp()) {
                    case Design: {
                        new BearbeiteEinzelneDesignaufgabeView((Designaufgabe) aufgabe, aktuellerBenutzer, jFrame);
                        break;
                    }
                    case Einfachantwort: {
                        new BearbeiteEinzelneEinfachantwortAufgabeView((EinfachantwortAufgabe) aufgabe, aktuellerBenutzer, jFrame);
                        break;
                    }
                    case MultipleChoice: {
                        new BearbeiteEinzelneMultipleChoiceAufgabeView((MultipleChoiceAufgabe) aufgabe, aktuellerBenutzer, jFrame);
                        break;
                    }
                    case Programmieren: {
                        new BearbeiteEinzelneProgrammieraufgabeView((Programmieraufgabe) aufgabe, aktuellerBenutzer, jFrame);
                        break;
                    }
                    default:
                        break;
                }
                dispose();
            }
        }
    }

}