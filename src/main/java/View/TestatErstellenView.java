package View;

import View.tableModel.AufgabenAuswaehlenTableModel;
import entity.aufgabe.Aufgabe;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.aufgabensammlung.Testat;
import entity.benutzer.Dozent;
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
    private JTextField txtName;
    private List<Aufgabe> aufgabenliste;
    private Dozent aktuellerBenutzer;
    private JFrame jframe;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TestatErstellenView frame = new TestatErstellenView(null, dozent1);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<Aufgabe> getTestdata() {
        Dozent dozent1 = new Dozent("PZwegat", "asdf", "Peter", "Zwegat");
        Aufgabe a1 = new EinfachantwortAufgabe(10, "umlDesign", Kategorie.Software_Engineering, 12, Schwierigkeitsgrad.Leicht, "Wie heißt der Datentyp für Text?", "Datentyp Text", dozent1);
        Aufgabe a2 = new Designaufgabe(15, "umlDesign", Kategorie.Datenbanken, 23, Schwierigkeitsgrad.Mittel, "Erstellen sie ein ER-Diagramm.", "ER-Diagramm", dozent1);
        Aufgabe a3 = new Programmieraufgabe(5, null, Kategorie.Java_Programmierung, 10, Schwierigkeitsgrad.Schwer, "Programmieren Sie eine for-Schleife", "for-Schleife", dozent1);
        Aufgabe a4 = new MultipleChoiceAufgabe(2, "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", dozent1, Arrays.asList(new String[]{"char", "int", "double"}));
        Aufgabe a5 = new EinfachantwortAufgabe();
        List<Aufgabe> aufgabenListe = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a1, a2, a3, a4, a1, a2, a3, a4, a1, a2, a3, a4, a5});
        return aufgabenListe;
    }

    /**
     * Create the frame.
     */
    public TestatErstellenView(JFrame jframe, Dozent aktuellerBenutzer) {
        this.jframe = jframe;
        this.aktuellerBenutzer = aktuellerBenutzer;
        aufgabenliste = DatabaseService.getInstance().readAufgabenFromDatabase();
        //Test
        //aufgabenliste = new LinkedList<>();
        //aufgabenliste = getTestdata();
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
        if (e.getSource() == this.btnFreigeben) {
            freigebenButtonLogik();
        }
    }

    private void zurueckButtonLogik() {
        boolean schliessenGewuenscht = (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Achtung! Nicht gespeicherte Eingaben gehen verloren.\nWollen Sie die Ansicht wirklich verlassen?", "Ansicht verlassen", JOptionPane.WARNING_MESSAGE));
        if(schliessenGewuenscht){
            jframe.setVisible(true);
            dispose();
        }
    }

    private void freigebenButtonLogik() {
        List<Aufgabe> ausgewaehlteAufgaben = aufgabenAuswaehlenLogik();
        if(ausgewaehlteAufgaben != null) {
            String passwort = txtPasswort.getText().trim();
            String name = txtName.getText().trim();
            if(name == null || name.equals("")) {
                JOptionPane.showMessageDialog(this, "Bitte Namen für das Testat eingeben", "Keine Name eingegeben", JOptionPane.WARNING_MESSAGE);
            } else if(passwort == null || passwort.equals("")) {
                JOptionPane.showMessageDialog(this, "Bitte Passwort für das Testat eingeben", "Kein Passwort eingegeben", JOptionPane.WARNING_MESSAGE);
            } else {
                Testat testat = new Testat(ausgewaehlteAufgaben, passwort, name, aktuellerBenutzer);
                for(Aufgabe a: ausgewaehlteAufgaben) {
                    a.addVerwendung(testat);
                }
                DatabaseService.getInstance().persistObject(testat);
                JOptionPane.showMessageDialog(this, "Testat wurde erstellt", "Testat erstellt", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private List<Aufgabe> aufgabenAuswaehlenLogik() {
        if (aufgabenliste.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es sind keine Aufgaben zum Erstellen des Testats verfügbar", "Keine Aufgaben", JOptionPane.WARNING_MESSAGE);
        } else {
            List<Aufgabe> ausgewaehlteAufgaben = new LinkedList<Aufgabe>();
            int gesamtzeit = 0;
            for (int i = 0; i < aufgabenliste.size(); i++) {
                boolean ausgewaehlt = (boolean) aufgabenAuswaehlenTableModel.getValueAt(i, 6);
                if(ausgewaehlt) {
                    ausgewaehlteAufgaben.add(aufgabenliste.get(i));
                    gesamtzeit += aufgabenliste.get(i).getBearbeitungszeit();
                }
            }
            if (ausgewaehlteAufgaben.size() <= 0) {
                JOptionPane.showMessageDialog(this, "Es wurden keine Aufgaben für das Testat ausgewählt", "Keine Aufgaben ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                if(gesamtzeit < 10) {
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

}