package View;

import View.tableModel.AufgabenAuswaehlenAufgabensammlungTableModel;
import entity.aufgabe.Aufgabe;
import entity.aufgabensammlung.Testat;
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
 * Ansicht in der ein Testat erstellt werden kann, indem Aufgaben aus einer
 * Tabelle ausgewählt werden und ein Passwort gesetzt wird.
 *
 * @author Jonas Herbst
 * @version 26.05.22
 * 20.05: Preview angepasst(Timo u. Kristin)
 */
public class TestatErstellenView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable tableAufgaben;
    private AufgabenAuswaehlenAufgabensammlungTableModel aufgabenAuswaehlenAufgabensammlungTableModel;
    private JButton btnZurueck;
    private JButton btnPreview;

    private JButton btnFreigeben;
    private JTextField txtPasswort;
    private JTextField txtName;
    private List<Aufgabe> aufgabenliste;
    private Dozent aktuellerBenutzer;
    private JFrame jframe;

    /**
     * Main-Methode, welche den Frame öffnet
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TestatErstellenView frame = new TestatErstellenView(null, new Dozent());
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
     * @param jframe            Hauptmenü-Frame, auf den beim Drücken des Zurück-Buttons zurückgekehrt werden soll
     * @param aktuellerBenutzer aktuell angemeldeter Benutzer
     */
    public TestatErstellenView(JFrame jframe, Dozent aktuellerBenutzer) {
        this.jframe = jframe;
        this.aktuellerBenutzer = aktuellerBenutzer;
        aufgabenliste = DatabaseService.getInstance().readAufgabenFromDatabase();
        aufgabenliste = new LinkedList<>(aufgabenliste);
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

        JPanel panelRightNorth = new JPanel();
        GridBagConstraints gbc_panelRightNorth = new GridBagConstraints();
        gbc_panelRightNorth.anchor = GridBagConstraints.EAST;
        gbc_panelRightNorth.fill = GridBagConstraints.VERTICAL;
        gbc_panelRightNorth.gridx = 2;
        gbc_panelRightNorth.gridy = 0;
        panelNorth.add(panelRightNorth, gbc_panelRightNorth);

        btnPreview = new JButton("Preview");
        panelRightNorth.add(btnPreview);


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
        aufgabenAuswaehlenAufgabensammlungTableModel = new AufgabenAuswaehlenAufgabensammlungTableModel(aufgabenliste);
        tableAufgaben.setModel(aufgabenAuswaehlenAufgabensammlungTableModel);
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

        JLabel lblName = new JLabel("Name:");
        panelLeftSouth.add(lblName);

        txtName = new JTextField();
        panelLeftSouth.add(txtName);
        txtName.setColumns(10);

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
        btnPreview.addActionListener(this);//verändert

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
        if (e.getSource() == this.btnPreview) {
            previewButtonLogik();
        }
        if (e.getSource() == this.btnFreigeben) {
            freigebenButtonLogik();
        }
    }

    /**
     * Beinhaltet die Logik des Zurück-Buttons
     */
    private void zurueckButtonLogik() {
        boolean schliessenGewuenscht = (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Achtung! Nicht gespeicherte Eingaben gehen verloren.\nWollen Sie die Ansicht wirklich verlassen?", "Ansicht verlassen", JOptionPane.WARNING_MESSAGE));
        if (schliessenGewuenscht) {
            jframe.setVisible(true);
            dispose();
        }
    }

    /**
     * Beinhaltet die Logik des Freigeben-Buttons
     */
    private void freigebenButtonLogik() {
        List<Aufgabe> ausgewaehlteAufgaben = aufgabenAuswaehlenLogik();
        if (ausgewaehlteAufgaben != null) {
            String passwort = txtPasswort.getText().trim();
            String name = txtName.getText().trim();
            if (name == null || name.equals("")) {
                JOptionPane.showMessageDialog(this, "Bitte Namen für das Testat eingeben", "Keine Name eingegeben", JOptionPane.WARNING_MESSAGE);
            } else if (passwort == null || passwort.equals("")) {
                JOptionPane.showMessageDialog(this, "Bitte Passwort für das Testat eingeben", "Kein Passwort eingegeben", JOptionPane.WARNING_MESSAGE);
            } else {
                Testat testat = new Testat(ausgewaehlteAufgaben, passwort, name, aktuellerBenutzer);
                for (Aufgabe a : ausgewaehlteAufgaben) {
                    a.addVerwendung(testat);
                }
                DatabaseService.getInstance().persistObject(testat);
                JOptionPane.showMessageDialog(this, "Testat wurde erstellt", "Testat erstellt", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Beinhaltet die Logik des Auswählens von Aufgaben für das Testat aus der Tabellen
     * Gibt null zurück, wenn keine Aufgaben ausgewählt wurden
     *
     * @return Liste mit ausgewählten Aufgaben
     */
    private List<Aufgabe> aufgabenAuswaehlenLogik() {
        if (aufgabenliste.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es sind keine Aufgaben zum Erstellen des Testats verfügbar", "Keine Aufgaben", JOptionPane.WARNING_MESSAGE);
        } else {
            List<Aufgabe> ausgewaehlteAufgaben = new LinkedList<Aufgabe>();
            int gesamtzeit = 0;
            for (int i = 0; i < aufgabenliste.size(); i++) {
                boolean ausgewaehlt = (boolean) aufgabenAuswaehlenAufgabensammlungTableModel.getValueAt(i, 6);
                if (ausgewaehlt) {
                    ausgewaehlteAufgaben.add(aufgabenliste.get(i));
                    gesamtzeit += aufgabenliste.get(i).getBearbeitungszeit();
                }
            }
            if (ausgewaehlteAufgaben.size() <= 0) {
                JOptionPane.showMessageDialog(this, "Es wurden keine Aufgaben für das Testat ausgewählt", "Keine Aufgaben ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                if (gesamtzeit < 10) {
                    JOptionPane.showMessageDialog(this, "Die gesamte Bearbeitungszeit des Testats muss mindestens 10 Minuten betragen.\nBitte mehr Aufgaben auswählen.", "Zu wenige Aufgaben", JOptionPane.WARNING_MESSAGE);
                } else if (gesamtzeit > 90) {
                    JOptionPane.showMessageDialog(this, "Die gesamte Bearbeitungszeit des Testats darf höchstens 90 Minuten betragen.\nBitte weniger Aufgaben auswählen.", "Zu viele Aufgaben", JOptionPane.WARNING_MESSAGE);
                } else {
                    return ausgewaehlteAufgaben;
                }
            }
        }
        return null;
    }

    /**
     * Beinhaltet die Logik des Preview-Buttons
     */
    private void previewButtonLogik() {
        int selectedRow = tableAufgaben.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Es wurde keine Aufgabe für die Preview ausgewählt", "Keine Aufgabe ausgewählt", JOptionPane.WARNING_MESSAGE);
        } else {
            Aufgabe aufgabe = aufgabenliste.get(selectedRow);
            new TestatErstellenAufgabenPreview(aufgabe);
        }
    }

}