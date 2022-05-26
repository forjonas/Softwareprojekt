package View;

import View.tableModel.AufgabeTableModel;
import entity.aufgabe.Aufgabe;
import entity.aufgabensammlung.Testat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Ansicht, die eine tabellarische Übersicht der Aufgaben in einem Testat zeigt
 *
 * @author Jonas Herbst
 * @version 26.05.22
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
     * Main-Methode, welche den Frame öffnet
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AufgabenEinesTestatsView frame = new AufgabenEinesTestatsView(new Testat());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Konstruktor, der den Frame erstellt
     *
     * @param testat Testat, dessen Aufgaben angezeigt werden sollen
     */
    public AufgabenEinesTestatsView(Testat testat) {
        this.testat = testat;
        aufgabenliste = testat.getAufgaben();
        aufgabenliste = new LinkedList<Aufgabe>(aufgabenliste);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Aufgaben des Testats \"" + testat.getName() + "\"");
        setModalityType(ModalityType.APPLICATION_MODAL);
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
        dispose();
    }

    /**
     * Beinhaltet die Logik des Einsehen-Buttons
     */
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