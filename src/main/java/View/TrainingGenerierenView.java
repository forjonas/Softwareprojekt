package View;

import View.tableModel.AufgabenAuswaehlenTableModel;
import View.tableModel.AufgabenTrainingTableModel;
import entity.aufgabe.Aufgabe;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import persistence.DatabaseService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Ansicht in der ein Testat erstellt werden kann, indem Aufgaben aus einer
 * Tabelle ausgewählt werden und ein Passwort gesetzt wird.
 *
 * @author Jonas Herbst
 * @version 04.05.22
 */
public class TrainingGenerierenView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable tableAufgaben;
    private AufgabenAuswaehlenTableModel aufgabenTrainingTableModel;
    private AufgabenAuswaehlenTableModel aufgabenAuswahlTableModel;
    private JButton btnZurueck;
    private JButton erstellenBtn;
    private JTextField txtPasswort;
    private JTextField txtName;
    private List<Aufgabe> aufgabenliste;
    private Benutzer aktuellerBenutzer;
    private JFrame jframe;
    private String[] schwierigkeitArray = {"Leicht", "Mittel", "Schwer"};
    private String[] kategorieArray = {"Software Engineering", "Java Programmierung", "Java Grundlagen", "Klassendiagramme", "Datenbanken"};

    private JComboBox<String> kategorieCBox = new JComboBox<>(kategorieArray);
    private JComboBox<String> schwierigkeitCBox = new JComboBox<>(schwierigkeitArray);
    ;
    private int gesamtzeit = 0;


    /**
     * Create the frame.
     */
    public TrainingGenerierenView(JFrame jframe, Benutzer aktuellerBenutzer, List<Aufgabe> aufgabenliste) {
        this.jframe = jframe;
        this.aktuellerBenutzer = aktuellerBenutzer;
        this.aufgabenliste=aufgabenliste;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Training erstellen");
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

        JLabel lblTrainingErstellen = new JLabel("Training erstellen");
        lblTrainingErstellen.setHorizontalAlignment(SwingConstants.CENTER);
        panelCenterNorth.add(lblTrainingErstellen);

        JPanel panelRightNorth = new JPanel();
        GridBagConstraints gbc_panelRightNorth = new GridBagConstraints();
        gbc_panelCenterNorth.anchor = GridBagConstraints.CENTER;
        gbc_panelCenterNorth.fill = GridBagConstraints.BOTH;
        gbc_panelCenterNorth.gridx = 2;
        gbc_panelCenterNorth.gridy = 0;
        panelNorth.add(panelRightNorth, gbc_panelRightNorth);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel pnlTrainingConfigSetzen = new JPanel();
        pnlTrainingConfigSetzen.add(schwierigkeitCBox);
        pnlTrainingConfigSetzen.add(kategorieCBox);
        panelCenterNorth.add(pnlTrainingConfigSetzen);

        tableAufgaben = new JTable();
        aufgabenAuswahlTableModel = new AufgabenAuswaehlenTableModel(aufgabenliste);
        tableAufgaben.setModel(aufgabenAuswahlTableModel);
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

        JPanel panelRightSouth = new JPanel();
        GridBagConstraints gbc_panelRightSouth = new GridBagConstraints();
        gbc_panelRightSouth.fill = GridBagConstraints.BOTH;
        gbc_panelRightSouth.gridx = 1;
        gbc_panelRightSouth.gridy = 0;
        panelSouth.add(panelRightSouth, gbc_panelRightSouth);

        erstellenBtn = new JButton("Erstellen");
        panelRightSouth.add(erstellenBtn);

        btnZurueck.addActionListener(this);
        erstellenBtn.addActionListener(this);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);
    }

    public String getValueCBox(JComboBox combo) {
        return (String) combo.getSelectedItem();
    }

    public void setAufgabenliste(List<Aufgabe> aufgabenliste)
    {
        this.aufgabenliste=aufgabenliste;
    }

    public Kategorie readKategorie() {
        switch (getValueCBox(kategorieCBox)) {
            case "Software Engineering":
                return Kategorie.Software_Engineering;
            case "Java Programmierung":
                return Kategorie.Java_Programmierung;
            case "Java Grundlagen":
                return Kategorie.Java_Grundlagen;
            case "Klassendiagramme":
                return Kategorie.Klassendiagramme;
            case "Datenbanken":
                return Kategorie.Datenbanken;
        }
        return null;
    }

    public Schwierigkeitsgrad schwierigkeitsgradSetzen() {
        switch (getValueCBox(schwierigkeitCBox)) {
            case "leicht":
                return Schwierigkeitsgrad.Leicht;
            case "mittel":
                return Schwierigkeitsgrad.Mittel;
            case "schwer":
                return Schwierigkeitsgrad.Schwer;
        }
        return Schwierigkeitsgrad.Leicht;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnZurueck) {
            zurueckButtonLogik();
        }
        if (e.getSource() == this.erstellenBtn) {
            erstellenButtonLogik();
        }
    }

    private void zurueckButtonLogik() {
        boolean schliessenGewuenscht = (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Achtung! Nicht gespeicherte Eingaben gehen verloren.\nWollen Sie die Ansicht wirklich verlassen?", "Ansicht verlassen", JOptionPane.WARNING_MESSAGE));
        if (schliessenGewuenscht) {
            jframe.setVisible(true);
            dispose();
        }
    }

    private void erstellenButtonLogik() {
        List<Aufgabe> ausgewaehlteAufgaben = aufgabenAuswaehlenLogik();
        if (ausgewaehlteAufgaben != null) {
            Training training = new Training(ausgewaehlteAufgaben, gesamtzeit, readKategorie(), schwierigkeitsgradSetzen(), readAufgabentyp(), aktuellerBenutzer);
            for (Aufgabe a : ausgewaehlteAufgaben) {
                a.addVerwendung(training);
            }
            DatabaseService.getInstance().persistObject(training);
            JOptionPane.showMessageDialog(this, "Testat wurde erstellt", "Testat erstellt", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private List<Aufgabe> aufgabenAuswaehlenLogik() {
        if (aufgabenliste.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es sind keine Aufgaben zum Erstellen des Testats verfügbar", "Keine Aufgaben", JOptionPane.WARNING_MESSAGE);
        } else {
            List<Aufgabe> ausgewaehlteAufgaben = new LinkedList<Aufgabe>();

            for (int i = 0; i < aufgabenliste.size(); i++) {
                boolean ausgewaehlt = (boolean) aufgabenTrainingTableModel.getValueAt(i, 6);
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

    public List<Aufgabentyp> readAufgabentyp() {
        boolean code = false, design = false, multi = false, ea = false;
        List<Aufgabentyp> aufgabentypList = new ArrayList<>();
        List<Aufgabe> aufgabenList = aufgabenAuswaehlenLogik();
        for (int i = 0; i < aufgabenList.size(); i++) {
            if (aufgabenList.get(i).getAufgabentyp() == Aufgabentyp.MultipleChoice && !multi) {
                aufgabentypList.add(Aufgabentyp.MultipleChoice);
                multi=true;
            } else if (aufgabenList.get(i).getAufgabentyp() == Aufgabentyp.Design && !design) {
                aufgabentypList.add(Aufgabentyp.Design);
                design=true;
            } else if (aufgabenList.get(i).getAufgabentyp() == Aufgabentyp.Programmieren && !code) {
                aufgabentypList.add(Aufgabentyp.Programmieren);
                code=true;
            } else if (aufgabenList.get(i).getAufgabentyp() == Aufgabentyp.Einfachantwort && !ea) {
                aufgabentypList.add(Aufgabentyp.Einfachantwort);
                ea=true;
            }
        }
        return aufgabentypList;
    }

}