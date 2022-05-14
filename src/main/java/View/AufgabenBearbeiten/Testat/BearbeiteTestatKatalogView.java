package View.AufgabenBearbeiten.Testat;

import View.AufgabenBearbeiten.Einzelne.BearbeiteEinzelneDesignaufgabeView;
import View.AufgabenBearbeiten.Einzelne.BearbeiteEinzelneEinfachantwortAufgabeView;
import View.AufgabenBearbeiten.Einzelne.BearbeiteEinzelneMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Einzelne.BearbeiteEinzelneProgrammieraufgabeView;
import View.DozentAnsicht;
import View.StudentMainView;
import View.tableModel.BearbeiteTestatTableModel;
import app.TestatApp;
import entity.aufgabe.Aufgabe;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.benutzer.Benutzer;
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
 * Ansicht in der aus einer Tabelle eine einzelne Aufgabe zur Bearbeitung ausgewählt werden kann.
 *
 * @author Jonas Herbst
 * @version 04.05.22
 */
public class BearbeiteTestatKatalogView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable tableTestate;
    private BearbeiteTestatTableModel bearbeiteTestatTableModel;
    private JButton btnZurueck;
    private JButton btnBearbeiten;
    private List<Testat> testatListe;
    private Benutzer aktuellerBenutzer;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Student student1 = new Student("AApfel", "aaa", "Adam", "Apfel", 1111);
        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Student student2 = (Student) DatabaseService.getInstance().readStudentnachBenutzernamen("AApfel");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BearbeiteTestatKatalogView frame = new BearbeiteTestatKatalogView(student1);
                    //BearbeiteTestatKatalogView frame = new BearbeiteTestatKatalogView(student2);
                    //BearbeiteTestatKatalogView frame = new BearbeiteTestatKatalogView(dozent1);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<Testat> getTestData() {
        Student student1 = new Student("AApfel", "aaa", "Adam", "Apfel", 1111);
        Aufgabe a1 = new EinfachantwortAufgabe(10, "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null);
        Aufgabe a2 = new Designaufgabe(15, "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null);
        Aufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, Arrays.asList(new String[]{"char", "int", "double"}));
        List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});
        List<Aufgabe> aufgabenListe2 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a2, a2, a3});
        List<Aufgabe> aufgabenListe3 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a4, a1, a2, a3});
        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Dozent dozent2 = new Dozent("PPanzer", "jklö", "Paul", "Panzer");
        Testat t1 = new Testat(aufgabenListe1, "Hallo1234", "Sommertestat", dozent1);
        TestatBearbeitung tb = new TestatBearbeitung(t1, 0, student1, null);
        t1.addBearbeitung(tb);
        Testat t2 = new Testat(aufgabenListe2, "asdf", "Wintertestat", dozent2);
        Testat t3 = new Testat(aufgabenListe3, "qwertz", "Herbsttestat", dozent1);
        Testat t4 = new Testat();
        List<Testat> testatliste = Arrays.asList(new Testat[]{t1, t2, t3, t1, t2, t3, t1, t2, t3, t1, t2, t3, t4});
        return testatliste;
    }

    /**
     * Create the frame.
     */
    public BearbeiteTestatKatalogView(Benutzer aktuellerBenutzer) {
        DatabaseService ds = DatabaseService.getInstance();
        this.aktuellerBenutzer = aktuellerBenutzer;
        testatListe = ds.readTestateFromDatabase();
        //Test
        //testatListe = getTestData();
        //testatListe = new LinkedList<Testat>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bearbeite Testat");
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

        tableTestate = new JTable();
        bearbeiteTestatTableModel = new BearbeiteTestatTableModel(testatListe, this.aktuellerBenutzer);
        tableTestate.setModel(bearbeiteTestatTableModel);
        tableTestate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableTestate);

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
            zurueckButtonLogik();
        }
        if (e.getSource() == this.btnBearbeiten) {
            bearbeitenButtonLogik();
        }
    }

    private void zurueckButtonLogik() {
        if (aktuellerBenutzer.getClass() == Student.class) {
            new StudentMainView((Student) aktuellerBenutzer);
        } else if (aktuellerBenutzer.getClass() == Dozent.class) {
            new DozentAnsicht();
            //Noch nicht implementiert in meinem Branch
            //new DozentAnsicht((Dozent) aktuellerBenutzer);
        }
        dispose();
    }

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
                        TestatApp testatApp = new TestatApp(testat, DatabaseService.getInstance());
                        //Noch nicht implementiert in meinem Branch
                        //TestatApp testatApp = new TestatApp(testat, aktuellerBenutzer);
                        testatApp.zeigeAktuelleAufgabe();
                        dispose();
                    } else if (passwort != null) {
                        JOptionPane.showMessageDialog(this, "Fehler: Falsches Passwort eingegeben", "Falsches Passwort", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        }
    }

}