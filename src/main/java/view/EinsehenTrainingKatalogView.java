package view;

import controller.controllerLoesungenTraining;
import view.tableModel.TrainingTableModel;
import entity.aufgabensammlung.Training;
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
 * Ansicht, die eine tabellarische Übersicht der bearbeiteten Trainings zeigt,
 * aus der Trainings zur Einsicht ausgewählt werden können.
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public class EinsehenTrainingKatalogView extends JFrame implements ActionListener {

    private Dozent aktuellerBenutzer;
    private List<Training> trainingsliste;
    private JFrame jframe;
    private JPanel contentPane;
    private JTable tableTrainings;
    private TrainingTableModel trainingsTableModel;
    private JButton btnZurueck;
    private JButton btnEinsehen;

    /**
     * Konstruktor, der den Frame öffnet
     *
     * @param jframe            Hauptmenü-Frame, auf den beim Drücken des Zurück-Buttons zurückgekehrt werden soll
     * @param aktuellerBenutzer aktuell angemeldeter Benutzer
     */
    public EinsehenTrainingKatalogView(JFrame jframe, Dozent aktuellerBenutzer) {
        this.jframe = jframe;
        this.aktuellerBenutzer = aktuellerBenutzer;
        trainingsliste = DatabaseService.getInstance().readTrainingsFromDatabase();
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

    /**
     * Wird ausgeführt, wenn ein ActionEvent auftritt
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnZurueck) {
            zurueckButtonLogik();
        }
        if (e.getSource() == this.btnEinsehen) {
            einsehenButtonLogik();
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
     * Beinhaltet die Logik des Einsehen-Buttons
     */
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
                    new controllerLoesungenTraining(training, aktuellerBenutzer, jframe);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Das gewählte Training enthält keine Aufgaben", "Keine Aufgaben", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}