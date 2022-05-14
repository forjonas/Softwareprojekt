package View;

import View.tableModel.AufgabenAuswaehlenTableModel;
import entity.aufgabe.Aufgabe;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.aufgabe.MultipleChoiceAufgabe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 * Ansicht in der ein Testat erstellt werden kann, indem Aufgaben aus einer
 * Tabelle ausgewählt werden und ein Passwort gesetzt wird.
 *
 * @author Jonas Herbst
 * @version 04.05.22
 */
public class TestatErstellenView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable tableAufgaben;
    private AufgabenAuswaehlenTableModel aufgabenAuswaehlenTableModel;
    private JButton btnZurueck;
    private JButton btnFreigeben;
    private JTextField txtPasswort;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        Aufgabe a1 = new EinfachantwortAufgabe(10, "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null);
        Aufgabe a2 = new Designaufgabe(15, "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null);
        Aufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, Arrays.asList(new String[]{"char", "int", "double"}));
        List<Aufgabe> aufgabenListe = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a1, a2, a3, a4, a1, a2, a3, a4, a1, a2, a3, a4, a1, a2, a3, a4, a1, a2, a3, a4});
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TestatErstellenView frame = new TestatErstellenView(aufgabenListe);
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
    public TestatErstellenView(List<Aufgabe> aufgabenliste) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Testat erstellen");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);

        GridBagLayout gbl_panelNorth = new GridBagLayout();
        gbl_panelNorth.columnWidths = new int[]{0, 0, 0};
        gbl_panelNorth.rowHeights = new int[]{0};
        gbl_panelNorth.columnWeights = new double[]{1.0, 1.0, 1.0};
        gbl_panelNorth.rowWeights = new double[]{1.0};
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
        gbc_panelCenterNorth.fill = GridBagConstraints.BOTH;
        gbc_panelCenterNorth.gridx = 1;
        gbc_panelCenterNorth.gridy = 0;
        panelNorth.add(panelCenterNorth, gbc_panelCenterNorth);

        JLabel lblTestatErstellen = new JLabel("Testat erstellen");
        lblTestatErstellen.setHorizontalAlignment(SwingConstants.CENTER);
        panelCenterNorth.add(lblTestatErstellen);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableAufgaben = new JTable();
        aufgabenAuswaehlenTableModel = new AufgabenAuswaehlenTableModel(aufgabenliste);
        tableAufgaben.setModel(aufgabenAuswaehlenTableModel);
        tableAufgaben.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableAufgaben);

        JPanel panelSouth = new JPanel();
        contentPane.add(panelSouth, BorderLayout.SOUTH);
        GridBagLayout gbl_panelSouth = new GridBagLayout();
        gbl_panelSouth.columnWidths = new int[]{0, 0};
        gbl_panelSouth.rowHeights = new int[]{0};
        gbl_panelSouth.columnWeights = new double[]{2.0, 1.0};
        gbl_panelSouth.rowWeights = new double[]{1.0};
        panelSouth.setLayout(gbl_panelSouth);

        JPanel panelLeftSouth = new JPanel();
        GridBagConstraints gbc_panelLeftSouth = new GridBagConstraints();
        gbc_panelLeftSouth.insets = new Insets(0, 0, 5, 0);
        gbc_panelLeftSouth.fill = GridBagConstraints.BOTH;
        gbc_panelLeftSouth.gridx = 0;
        gbc_panelLeftSouth.gridy = 0;
        panelSouth.add(panelLeftSouth, gbc_panelLeftSouth);

        JLabel lblPasswort = new JLabel("Passwort:");
        panelLeftSouth.add(lblPasswort);

        txtPasswort = new JTextField();
        panelLeftSouth.add(txtPasswort);
        txtPasswort.setColumns(10);

        JPanel panelRightSouth = new JPanel();
        GridBagConstraints gbc_panelRightSouth = new GridBagConstraints();
        gbc_panelRightSouth.fill = GridBagConstraints.BOTH;
        gbc_panelRightSouth.gridx = 1;
        gbc_panelRightSouth.gridy = 0;
        panelSouth.add(panelRightSouth, gbc_panelRightSouth);

        btnFreigeben = new JButton("Freigeben");
        panelRightSouth.add(btnFreigeben);

        btnZurueck.addActionListener(this);
        btnFreigeben.addActionListener(this);

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
        if (e.getSource() == this.btnFreigeben) {
            String passwort = txtPasswort.getText();
            System.out.println("freigeben. Passwort: " + passwort);
        }
    }

}