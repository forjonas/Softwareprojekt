package View;

import View.Lösungen.BewertungenTestat.ControllerBewertungenTestate;
import View.tableModel.KorrigiereTestatTableModel;
import app.TestatApp;
import entity.aufgabe.Aufgabe;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.aufgabensammlung.Training;
import entity.benutzer.Dozent;
import entity.benutzer.Student;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.aufgabe.MultipleChoiceAufgabe;
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
    private Dozent aktuellerBenutzer;
    private List<TestatBearbeitung> testatBearbeitungsListe;
    private JFrame jframe;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Dozent dozent1 = new Dozent("admin", "asdf", "Peter", "Zwegat");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dozent dozent2 = new Dozent();
                    List<Dozent> dozenten = DatabaseService.getInstance().readDozentenFromDatabase();
                    for(Dozent dozent: dozenten) {
                        if(dozent.getBenutzername().equals("KKubisch")){
                            dozent2 = dozent;
                            break;
                        }
                    }
                    KorrigiereTestatKatalogView frame = new KorrigiereTestatKatalogView(null, dozent1);
                    //KorrigiereTestatKatalogView frame = new KorrigiereTestatKatalogView(dozent2);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<TestatBearbeitung> getTestData() {
        Student student1 = new Student("BBirne", "bbb", "Bert", "Birne", 2222);
        Student student2 = new Student("CClown", "ccc", "Chris", "Clown", 3333);
        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Aufgabe a1 = new EinfachantwortAufgabe(10, "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null);
        Aufgabe a2 = new Designaufgabe(15, "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null);
        Aufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, Arrays.asList(new String[]{"char", "int", "double"}));
        List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});
        List<Aufgabe> aufgabenListe2 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a2, a2, a3});
        Testat t1 = new Testat(aufgabenListe1, "Hallo1234", "Sommertestat", dozent1);
        Testat t2 = new Testat(aufgabenListe2, "asdf", "Wintertestat", dozent1);
        Testat t3 = new Testat();
        TestatBearbeitung tb1 = new TestatBearbeitung(t1, 50, student1, dozent1);
        TestatBearbeitung tb2 = new TestatBearbeitung(t1, 65, student2, dozent1);
        TestatBearbeitung tb3 = new TestatBearbeitung(t2, 0, student1, null);
        TestatBearbeitung tb4 = new TestatBearbeitung(t2, 0, student2, null);
        TestatBearbeitung tb5 = new TestatBearbeitung(t3);
        TestatBearbeitung tb6 = new TestatBearbeitung();
        List<TestatBearbeitung> testatbearbeitungsliste = Arrays.asList(new TestatBearbeitung[]{tb1, tb2, tb3, tb4, tb1, tb2, tb3, tb4, tb5, tb6});
        return testatbearbeitungsliste;
    }

    /**
     * Create the frame.
     */
    public KorrigiereTestatKatalogView(JFrame jframe, Dozent aktuellerBenutzer) {
        this.jframe = jframe;
        this.aktuellerBenutzer = aktuellerBenutzer;
        testatBearbeitungsListe = DatabaseService.getInstance().readTestatBearbeitungenFromDatabase();
        //Test
        //testatBearbeitungsListe = new LinkedList<TestatBearbeitung>();
        testatBearbeitungsListe = getTestData();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnZurueck) {
            zurueckButtonLogik();
        }
        if (e.getSource() == this.btnKorrigieren) {
            korrigierenButtonLogik();
        }
    }

    private void zurueckButtonLogik() {
        jframe.setVisible(true);
        dispose();
    }

    private void korrigierenButtonLogik() {
        if (testatBearbeitungsListe.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es gibt keine Testatbearbeitungen zum Korrigieren", "Keine Testatbearbeitungen", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedRow = tableTestate.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Es wurde keine Testatbearbeitung zum Korrigieren ausgewählt", "Keine Testatbearbeitung ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                TestatBearbeitung testatBearbeitung = testatBearbeitungsListe.get(selectedRow);
                //Testat wurde bereits korrigiert
                if (testatBearbeitung.getTestatBewerter() != null) {
                    JOptionPane.showMessageDialog(this, "Die Testatbearbeitung wurde bereits korrigiert", "Testatbearbeitung bereits korrigiert", JOptionPane.WARNING_MESSAGE);
                }
                //Dozent darf das Testat nicht korrigieren
                else if (!testatBearbeitung.darfDozentTestatBearbeitungBewerten(aktuellerBenutzer)) {
                    JOptionPane.showMessageDialog(this, "Sie verfügen nicht über die Berechtigung, die Testatbearbeitung zu korrigieren", "Fehlende Berechtigung", JOptionPane.WARNING_MESSAGE);
                }
                //Testatbearbeitung enthält keine Aufgaben
                else if (testatBearbeitung.getTestat() == null || testatBearbeitung.getTestat().getAnzahlAufgaben() == 0) {
                    JOptionPane.showMessageDialog(this, "Fehler: Testatbearbeitung enthält keine Aufgaben", "Testatbearbeitung konnte nicht geöffnet werden", JOptionPane.ERROR_MESSAGE);
                }
                //Dozent korrigiert das Testat
                else {
                    //Version des ControllerBewertungenTestate, die gerade in meinem Branch liegt
                    //Müsste eigentlich mit TestatBearbeitung statt Testat und mit Benutzer/Dozent initialisiert werden
                    new ControllerBewertungenTestate(testatBearbeitung,aktuellerBenutzer);
                    dispose();
                }
            }
        }
    }

}