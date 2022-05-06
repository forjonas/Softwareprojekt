package views;

import entity.*;
import tableModel.KorrigiereTestatTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 * Ansicht in der aus einer Tabelle ein Testat zum Korrigieren ausgewählt werden kann.
 *
 * @author Jonas Herbst
 * @version 04.05.22
 */
public class KorrigiereTestatKatalogView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable tableTestate;
    private KorrigiereTestatTableModel korrigiereTestatTableModel;
    private JButton btnZurueck;
    private JButton btnKorrigieren;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        Aufgabe a1 = new EinfachantwortAufgabe(10," javaDesign", "umlDesign", Kategorie.Software_Engineering, "Test Test", 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", "Pi mal Daumen", "Peace");
        Aufgabe a2 = new Designaufgabe(15," javaDesign", "umlDesign", Kategorie.Datenbanken, "Kein Lösungshinweis", 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", "Richtig", "RRRRichtig");
        Aufgabe a3 = new Programmieraufgabe(5,null, null, Kategorie.Java_Programmierung, "for-Schleife", 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", "for(int i=0; i<5; i++) {\n\tSystem.out.println(\"Hello World!\");\n}", "Keine Ahnung");
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "javaDesign", "umlDesign", Kategorie.Java_Programmierung, "Char ist es nicht.", 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", Arrays.asList(new String[]{"char","int","double"}), Arrays.asList(new Boolean[]{false, true, false}), Arrays.asList(new Boolean[]{false, true, false}));
        List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});
        List<Aufgabe> aufgabenListe2 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a2, a2, a3});
        List<Aufgabe> aufgabenListe3 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a4, a1, a2, a3});
        Testat t1 = new Testat(aufgabenListe1, null, "Hallo1234", "Sommertestat");
        Testat t2 = new Testat(aufgabenListe2, null, "asdf", "Wintertestat");
        Testat t3 = new Testat(aufgabenListe3, null, "qwertz", "Herbsttestat");
        List<Testat> testatliste = Arrays.asList(new Testat[]{t1, t2, t3, t1, t2, t3, t1, t2, t3, t1, t2, t3});
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    KorrigiereTestatKatalogView frame = new KorrigiereTestatKatalogView(testatliste);
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
    public KorrigiereTestatKatalogView(List<Testat> testatListe) {
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
        korrigiereTestatTableModel = new KorrigiereTestatTableModel(testatListe);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnZurueck) {
            System.out.println("zurück");
            dispose();
        }
        if (e.getSource() == this.btnKorrigieren) {
            System.out.println("korrigieren");
        }
    }

}