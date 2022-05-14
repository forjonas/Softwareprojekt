package View;

import View.AufgabenBearbeiten.Einzelne.BearbeiteEinzelneDesignaufgabeView;
import View.AufgabenBearbeiten.Einzelne.BearbeiteEinzelneEinfachantwortAufgabeView;
import View.AufgabenBearbeiten.Einzelne.BearbeiteEinzelneMultipleChoiceAufgabeView;
import View.AufgabenBearbeiten.Einzelne.BearbeiteEinzelneProgrammieraufgabeView;
import View.Lösungen.LoesungenTraining.ControllerLoesungenTraining;
import View.tableModel.TrainingTableModel;
import entity.aufgabe.Aufgabe;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.aufgabensammlung.Training;
import entity.benutzer.Dozent;
import entity.benutzer.Student;
import entity.enums.Aufgabentyp;
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
 * Ansicht, die eine tabellarische Übersicht der bearbeiteten Trainings zeigt,
 * aus der Trainings zur Einsicht ausgewählt werden können.
 *
 * @author Jonas Herbst
 * @version 09.05.22
 */
public class EinsehenTrainingKatalogView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable tableTrainings;
    private TrainingTableModel trainingsTableModel;
    private JButton btnZurueck;
    private JButton btnEinsehen;
    private Dozent aktuellerBenutzer;
    private List<Training> trainingsliste;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EinsehenTrainingKatalogView frame = new EinsehenTrainingKatalogView(dozent1);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<Training> getTestData() {
        Aufgabe a1 = new EinfachantwortAufgabe(10, "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", null);
        Aufgabe a2 = new Designaufgabe(15, "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", null);
        Aufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", null);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, Arrays.asList(new String[]{"char", "int", "double"}));
        List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});
        List<Aufgabe> aufgabenListe2 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a2, a2, a3});
        List<Aufgabe> aufgabenListe3 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a4, a1, a2, a3});
        Student student1 = new Student("CClown", "ccc", "Chris", "Clown", 3333);
        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Dozent dozent2 = new Dozent("PPanzer", "jklö", "Paul", "Panzer");
        Training t1 = new Training(aufgabenListe1, 60, Kategorie.Java_Programmierung, Schwierigkeitsgrad.Leicht, Arrays.asList(new Aufgabentyp[] {Aufgabentyp.Einfachantwort, Aufgabentyp.Programmieren}), student1);
        Training t2 = new Training(aufgabenListe2, 70, Kategorie.Datenbanken, Schwierigkeitsgrad.Mittel, Arrays.asList(new Aufgabentyp[] {Aufgabentyp.Einfachantwort, Aufgabentyp.Programmieren}), dozent1);
        Training t3 = new Training(aufgabenListe3, 50, Kategorie.Software_Engineering, Schwierigkeitsgrad.Schwer, Arrays.asList(new Aufgabentyp[] {Aufgabentyp.Einfachantwort, Aufgabentyp.Programmieren}), dozent2);
        Training t4 = new Training();
        List<Training> trainingsliste = Arrays.asList(new Training[]{t1, t2, t3, t1, t2, t3, t1, t2, t3, t1, t2, t3, t4});
        return  trainingsliste;
    }

    /**
     * Create the frame.
     */
    public EinsehenTrainingKatalogView(Dozent aktuellerBenutzer) {
        this.aktuellerBenutzer = aktuellerBenutzer;
        trainingsliste = DatabaseService.getInstance().readTrainingsFromDatabase();
        //Test
        //trainingsliste = new LinkedList<Training>();
        //trainingsliste = getTestData();
        trainingsliste = new LinkedList<Training>(trainingsliste);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trainings einsehen");
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

        tableTrainings = new JTable();
        trainingsTableModel = new TrainingTableModel(trainingsliste);
        tableTrainings.setModel(trainingsTableModel);
        tableTrainings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tableTrainings);

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
        if (aktuellerBenutzer.getClass() == Dozent.class) {
            new DozentAnsicht();
            //Noch nicht implementiert in meinem Branch
            //new DozentAnsicht((Dozent) aktuellerBenutzer);
        } else {
            JOptionPane.showMessageDialog(this, "Fehler: Benutzer ist nicht als Dozent eingeloggt", "Falscher Benutzer", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

    private void einsehenButtonLogik() {
        if (trainingsliste.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es gibt keine Trainings zum Einsehen", "Keine Trainings", JOptionPane.WARNING_MESSAGE);
        } else {
            int selectedRow = tableTrainings.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Es wurde kein Training zum Einsehen ausgewählt", "Keine Training ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                Training training = trainingsliste.get(selectedRow);
                if(training.getAnzahlAufgaben() > 0) {
                    //In meinem Branch noch ohne Option, um den aktuellen Benutzter zu übergeben
                    ControllerLoesungenTraining controller = ControllerLoesungenTraining.getInstance();
                    controller.setTraining(training);
                    controller.startLoesungTraining();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Das gewählte Training enthält keine Aufgaben", "Keine Aufgaben", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}