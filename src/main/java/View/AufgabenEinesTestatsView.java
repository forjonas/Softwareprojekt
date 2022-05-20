package View;

import View.tableModel.AufgabeTableModel;
import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;
import entity.benutzer.Dozent;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import persistence.DatabaseService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Ansicht, die eine tabellarische Übersicht der Aufgaben in einem Testat zeigt
 *
 * @author Jonas Herbst
 * @version 04.05.22
 */
public class AufgabenEinesTestatsView extends JDialog implements ActionListener {

    private JPanel contentPane;
    private JTable tableAufgaben;
    private AufgabeTableModel aufgabeTableModel;
    private JButton btnZurueck;
    private JButton btnEinsehen;
    private List<Aufgabe> aufgabenliste;
    private Testat testat;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Dozent dozent1 = new Dozent("admin", "asdf", "Arne", "Admin");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AufgabenEinesTestatsView frame = new AufgabenEinesTestatsView(getTestData());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static Testat getTestData() {
        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Aufgabe a1 = new EinfachantwortAufgabe(10, null, Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", dozent1);
        Aufgabe a2 = new Designaufgabe(15, null, Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", dozent1);
        Aufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", dozent1);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, null, Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", dozent1, Arrays.asList(new String[]{"char", "int", "double"}));
        Aufgabe a5 = new EinfachantwortAufgabe();
        List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a5});
        Testat testat = new Testat(aufgabenListe1, "000", "NeuesTestat", dozent1);
        return testat;
    }

    /**
     * Create the frame.
     */
    public AufgabenEinesTestatsView(Testat testat) {
        this.testat = testat;
        aufgabenliste = testat.getAufgaben();
        aufgabenliste = new LinkedList<Aufgabe>(aufgabenliste);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Aufgaben des Testats \""+testat.getName()+"\"");
        //setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        //setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
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

        JPanel panelRightNorth = new JPanel();
        GridBagConstraints gbc_panelRightNorth = new GridBagConstraints();
        gbc_panelRightNorth.anchor = GridBagConstraints.EAST;
        gbc_panelRightNorth.fill = GridBagConstraints.VERTICAL;
        gbc_panelRightNorth.gridx = 2;
        gbc_panelRightNorth.gridy = 0;
        panelNorth.add(panelRightNorth, gbc_panelRightNorth);

        btnEinsehen = new JButton("Einsehen");
        panelRightNorth.add(btnEinsehen);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableAufgaben = new JTable();
        aufgabeTableModel = new AufgabeTableModel(aufgabenliste);
        tableAufgaben.setModel(aufgabeTableModel);
        tableAufgaben.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableAufgaben);

        btnEinsehen.addActionListener(this);
        btnZurueck.addActionListener(this);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnZurueck) {
            zurueckButtonLogik();
        }
        if (e.getSource() == this.btnEinsehen) {
            einsehenButtonLogik();
        }
    }

    private void zurueckButtonLogik() {
        dispose();
    }

    private void einsehenButtonLogik() {
        if (aufgabenliste.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es gibt keine Aufgaben zum Einsehen", "Keine Aufgaben", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedRow = tableAufgaben.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Es wurde keine Aufgabe zum Einsehen ausgewählt", "Keine Aufgabe ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                Aufgabe aufgabe = aufgabenliste.get(selectedRow);
                new TestatErstellenAufgabenPreview(aufgabe);
            }
        }
    }

}