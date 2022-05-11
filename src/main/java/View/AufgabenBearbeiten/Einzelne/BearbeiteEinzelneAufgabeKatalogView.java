package View.AufgabenBearbeiten.Einzelne;

import View.tableModel.AufgabeTableModel;
import entity.aufgabe.Aufgabe;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.aufgabe.MultipleChoiceAufgabe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 * Ansicht in der aus einer Tabelle eine einzelne Aufgabe zur Bearbeitung ausgewählt werden kann.
 *
 * @author Jonas Herbst
 * @version 04.05.22
 */
public class BearbeiteEinzelneAufgabeKatalogView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable tableAufgaben;
    private AufgabeTableModel aufgabeTableModel;
    private JButton btnZurueck;
    private JButton btnBearbeiten;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        Aufgabe a1 = new EinfachantwortAufgabe(10, " javaDesign", "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null);
        Aufgabe a2 = new Designaufgabe(15, " javaDesign", "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null);
        Aufgabe a3 = new Programmieraufgabe(5, null, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "javaDesign", "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, Arrays.asList(new String[]{"char", "int", "double"}));
        List<Aufgabe> aufgabenListe = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a1, a2, a3, a4, a1, a2, a3, a4, a1, a2, a3, a4, a1, a2, a3, a4, a1, a2, a3, a4});
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BearbeiteEinzelneAufgabeKatalogView frame = new BearbeiteEinzelneAufgabeKatalogView(aufgabenListe);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public BearbeiteEinzelneAufgabeKatalogView(List<Aufgabe> aufgabenliste) {
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
        aufgabeTableModel = new AufgabeTableModel(aufgabenliste);
        tableAufgaben.setModel(aufgabeTableModel);
        tableAufgaben.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableAufgaben);

        btnBearbeiten.addActionListener(this);
        btnZurueck.addActionListener(this);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnZurueck) {
            System.out.println("zurück");
            dispose();
        }
        if (e.getSource() == this.btnBearbeiten) {
            System.out.println("bearbeiten");
            dispose();
        }
    }

}