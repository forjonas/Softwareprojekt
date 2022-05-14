package View;

import View.AufgabenErstellen.AufgabeErstellenStartView;
import View.tableModel.AufgabeTableModel;
import View.tableModel.BearbeiteAufgabeTableModel;
import app.TestatApp;
import entity.aufgabe.Aufgabe;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.aufgabensammlung.Testat;
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
import java.util.*;
import java.util.List;

/**
 * Ansicht in der die bestehenden Aufgaben administriert und neue hinzugefügt werden können.
 *
 * @author Jonas Herbst
 * @version 04.05.22
 */
public class AufgabenKatalogView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable tableAufgaben;
    private AufgabeTableModel aufgabeTableModel;
    private JButton btnZurueck;
    private JButton btnLoeschen;
    private JButton btnErstellen;
    private Dozent aktuellerBenutzer;
    private List<Aufgabe> aufgabenliste;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Dozent dozent1 = new Dozent("admin", "asdf", "Arne", "Admin");
        Dozent dozent2 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Dozent dozent3 = (Dozent) DatabaseService.getInstance().readDozentnachBenutzernamen("PPanzer");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //AufgabenKatalogView frame = new AufgabenKatalogView(dozent1);
                    AufgabenKatalogView frame = new AufgabenKatalogView(dozent2);
                    //AufgabenKatalogView frame = new AufgabenKatalogView(dozent3);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<Aufgabe> getTestData() {
        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Dozent dozent2 = new Dozent("PPanzer", "jklö", "Paul", "Panzer");
        Aufgabe a1 = new EinfachantwortAufgabe(10, "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", dozent1);
        Aufgabe a2 = new Designaufgabe(15, "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", dozent1);
        Aufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", dozent2);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", dozent2, Arrays.asList(new String[]{"char", "int", "double"}));
        Aufgabe a5 = new EinfachantwortAufgabe();
        List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a1, a2, a3, a4, a1, a2, a3, a4, a1, a2, a3, a4, a5});
        List<Aufgabe> aufgabenListe2 = new LinkedList<Aufgabe>(aufgabenListe1);
        return aufgabenListe1;
    }

    /**
     * Create the frame.
     */
    public AufgabenKatalogView(Dozent aktuellerBenutzer) {
        this.aktuellerBenutzer = aktuellerBenutzer;
        DatabaseService ds = DatabaseService.getInstance();
        aufgabenliste = ds.readAufgabenFromDatabase();
        //Test
        //aufgabenliste = getTestData();
        //aufgabenliste = new LinkedList<Aufgabe>();
        aufgabenliste = new LinkedList<Aufgabe>(aufgabenliste);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aufgabenkatalog");
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

        btnErstellen = new JButton("Neue Aufgabe erstellen");
        panelCenterNorth.add(btnErstellen);

        JPanel panelRightNorth = new JPanel();
        GridBagConstraints gbc_panelRightNorth = new GridBagConstraints();
        gbc_panelRightNorth.anchor = GridBagConstraints.EAST;
        gbc_panelRightNorth.fill = GridBagConstraints.VERTICAL;
        gbc_panelRightNorth.gridx = 2;
        gbc_panelRightNorth.gridy = 0;
        panelNorth.add(panelRightNorth, gbc_panelRightNorth);

        btnLoeschen = new JButton("Löschen");
        panelRightNorth.add(btnLoeschen);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableAufgaben = new JTable();
        aufgabeTableModel = new AufgabeTableModel(aufgabenliste);
        tableAufgaben.setModel(aufgabeTableModel);
        tableAufgaben.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableAufgaben);

        btnLoeschen.addActionListener(this);
        btnErstellen.addActionListener(this);
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
        if (e.getSource() == this.btnErstellen) {
            erstellenButtonLogik();
        }
        if (e.getSource() == this.btnLoeschen) {
            loeschenButtonLogik();
        }
    }

    private void zurueckButtonLogik() {
        if (aktuellerBenutzer.getClass() == Dozent.class) {
            new DozentAnsicht();
            //Noch nicht implementiert in meinem Branch
            //new DozentAnsicht((Dozent) aktuellerBenutzer);
        } else {
            JOptionPane.showMessageDialog(this, "Fehler: Benutzer ist nicht als Dozent eingeloggt", "Falscher Benutzer", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

    private void erstellenButtonLogik() {
        //In meinem Branch noch nicht implementiert
        //new AufgabeErstellenStartView(aktuellerBenutzer);
        new AufgabeErstellenStartView(null);
        dispose();
    }

    private void loeschenButtonLogik() {
        if (aufgabenliste.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es gibt keine Aufgaben zum Löschen", "Keine Aufgaben", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedRow = tableAufgaben.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Es wurde keine Aufgabe zum Löschen ausgewählt", "Keine Aufgabe ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                boolean loeschenGewuenscht = false;
                Aufgabe aufgabe = aufgabenliste.get(selectedRow);
                if (!aufgabe.darfDozentAufgabeLoeschen(aktuellerBenutzer)) {
                    JOptionPane.showMessageDialog(this, "Sie sind nicht berechtigt, diese Aufgabe zu löschen", "Fehlende Berechtigung", JOptionPane.WARNING_MESSAGE);
                } else if (aufgabe.getVerwendungen().size() > 0) {
                    loeschenGewuenscht = (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Wollen Sie die Aufgabe wirklich löschen?\nAchtung! Sie ist in Trainings und/oder Testaten enthalten aus welchen sie beim Löschen entfernt wird.", "Aufgabe löschen", JOptionPane.WARNING_MESSAGE));
                } else {
                    loeschenGewuenscht = (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Wollen Sie die Aufgabe wirklich löschen?", "Aufgabe löschen", JOptionPane.WARNING_MESSAGE));
                }
                if(loeschenGewuenscht) {
                    aufgabenliste.remove(aufgabe);
                    aufgabeTableModel.fireTableDataChanged();
                    DatabaseService.getInstance().saveDeleteAufgabeFromDatabase(aufgabe);
                    JOptionPane.showMessageDialog(this, "Die ausgewählte Aufgabe wurde gelöscht", "Aufgabe gelöscht", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

}