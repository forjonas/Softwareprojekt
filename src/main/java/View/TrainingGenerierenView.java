package View;

import View.tableModel.AufgabenAuswaehlenTableModel;
import app.TrainingController;
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
 * Ansicht in der ein Training erstellt werden kann, indem Aufgaben aus einer Tabelle ausgewählt werden.
 *
 * @author Martin Bergen
 * @version 23.05.22
 */
public class TrainingGenerierenView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable tableAufgaben;
    private AufgabenAuswaehlenTableModel aufgabenAuswahlTableModel;
    private JButton btnZurueck;
    private JButton erstellenBtn;
    private List<Aufgabe> aufgabenliste;
    private Benutzer aktuellerBenutzer;
    private JFrame jframe;
    private Kategorie kategorie;
    private Schwierigkeitsgrad schwierigkeitsgrad;
    private int gesamtzeit;


    /**
     * Konstruktor für die Klasse TrainingGenerierenView, der das Fenster und die ausgewählten Bausteine miteinander verbindet und erstellt.
     *
     * @param aktuellerBenutzer  Benutzer, der diese Fenster gerade Aufruft
     * @param jframe             JFrame, aus dem die TrainingGenerierenView gestartet wurde,bzw. das Benutzerfenster
     * @param kategorie          Kategorie der Aufgaben, die im Training ebenfalls gespeichert werden müssen
     * @param schwierigkeitsgrad Schwierigkeit der Aufgaben, die ebenfalls im Training gespeichert werden müssen
     * @param aufgabenliste      Eine Lister aller Aufgaben aus der Datenbank, die die angegebene Kategorie und Schwierigkeit haben
     */
    public TrainingGenerierenView(JFrame jframe, Benutzer aktuellerBenutzer, List<Aufgabe> aufgabenliste, Kategorie kategorie, Schwierigkeitsgrad schwierigkeitsgrad) {
        this.jframe = jframe;
        this.kategorie = kategorie;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.aktuellerBenutzer = aktuellerBenutzer;
        this.aufgabenliste = aufgabenliste;
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

    /**
     * Überprüft ob einer der Buttons betätigt wurde und führt dementsprechend, die gesetze Methode aus
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnZurueck) {
            zurueckButtonLogik();
        }
        if (e.getSource() == this.erstellenBtn) {
            erstellenButtonLogik();
        }
    }

    /**
     * Eine Methode zum Fenster schließen und zurückkehren zur vorherigen Ansicht, mit Überprüfung, ob das wirklich gewünscht ist.
     * Zugehörig zu dem Button @param btnZurueck
     */
    private void zurueckButtonLogik() {
        boolean schliessenGewuenscht = (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Achtung! Nicht gespeicherte Eingaben gehen verloren.\nWollen Sie die Ansicht wirklich verlassen?", "Ansicht verlassen", JOptionPane.WARNING_MESSAGE));
        if (schliessenGewuenscht) {
            jframe.setVisible(true);
            dispose();
        }
    }

    /**
     * Eine Methode zum Erstellen des Trainings und anschließender Anzeige der gewählten Aufgaben, mit Überprüfung, ob eine gewählt wurde.
     * Zugehörig zu dem Button @param erstellenBtn
     */
    private void erstellenButtonLogik() {
        List<Aufgabe> ausgewaehlteAufgaben = aufgabenAuswaehlenLogik();
        if (ausgewaehlteAufgaben != null) {
            Training training = new Training(ausgewaehlteAufgaben, gesamtzeit, kategorie, schwierigkeitsgrad, readAufgabentyp(), aktuellerBenutzer);
            for (Aufgabe a : ausgewaehlteAufgaben) {
                a.addVerwendung(training);
            }
            DatabaseService.getInstance().persistObject(training);
            new TrainingController(training, aktuellerBenutzer, jframe).zeigeAktuelleAufgabe();
            dispose();
        }
    }

    /**
     * Methode zum Auswählen der Aufgaben, die dem Training hinzugefügt werden sollen.
     * Methode gibt eine List<Aufgabe> zurück.
     *
     * @return ausgewaehlteAufgaben
     */
    private List<Aufgabe> aufgabenAuswaehlenLogik() {
        gesamtzeit = 0;
        if (aufgabenliste.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Es sind keine Aufgaben zum Erstellen des Trainings verfügbar", "Keine Aufgaben", JOptionPane.WARNING_MESSAGE);
        } else {
            List<Aufgabe> ausgewaehlteAufgaben = new LinkedList<Aufgabe>();

            for (int i = 0; i < aufgabenliste.size(); i++) {
                boolean ausgewaehlt = (boolean) aufgabenAuswahlTableModel.getValueAt(i, 6);
                if (ausgewaehlt) {
                    ausgewaehlteAufgaben.add(aufgabenliste.get(i));
                    gesamtzeit += aufgabenliste.get(i).getBearbeitungszeit();
                }
            }
            if (ausgewaehlteAufgaben.size() <= 0) {
                JOptionPane.showMessageDialog(this, "Es wurden keine Aufgaben für das Training ausgewählt", "Keine Aufgaben ausgewählt", JOptionPane.WARNING_MESSAGE);
            } else {
                if (gesamtzeit < 10) {
                    JOptionPane.showMessageDialog(this, "Die gesamte Bearbeitungszeit des Trainings muss mindestens 10 Minuten betragen.\nBitte mehr Aufgaben auswählen.", "Zu wenige Aufgaben", JOptionPane.WARNING_MESSAGE);
                } else if (gesamtzeit > 90) {
                    JOptionPane.showMessageDialog(this, "Die gesamte Bearbeitungszeit des Trainings darf höchstens 90 Minuten betragen.\nBitte weniger Aufgaben auswählen.", "Zu viele Aufgaben", JOptionPane.WARNING_MESSAGE);
                } else {
                    return ausgewaehlteAufgaben;
                }
            }
        }
        return null;
    }

    /**
     * Methode zum Speichern und wiedergeben der Aufgabentypen, die in der ausgewaehlteAufgaben List vertreten sind.
     * Methode gibt eine List<Aufgabentyp> zurück, die dann im Training gespeichert wird.
     *
     * @return aufgabentypList;
     */
    public List<Aufgabentyp> readAufgabentyp() {
        boolean code = false, design = false, multi = false, ea = false;
        List<Aufgabentyp> aufgabentypList = new ArrayList<>();
        List<Aufgabe> aufgabenList = aufgabenAuswaehlenLogik();
        for (int i = 0; i < aufgabenList.size(); i++) {
            if (aufgabenList.get(i).getAufgabentyp() == Aufgabentyp.MultipleChoice && !multi) {
                aufgabentypList.add(Aufgabentyp.MultipleChoice);
                multi = true;
            } else if (aufgabenList.get(i).getAufgabentyp() == Aufgabentyp.Design && !design) {
                aufgabentypList.add(Aufgabentyp.Design);
                design = true;
            } else if (aufgabenList.get(i).getAufgabentyp() == Aufgabentyp.Programmieren && !code) {
                aufgabentypList.add(Aufgabentyp.Programmieren);
                code = true;
            } else if (aufgabenList.get(i).getAufgabentyp() == Aufgabentyp.Einfachantwort && !ea) {
                aufgabentypList.add(Aufgabentyp.Einfachantwort);
                ea = true;
            }
        }
        return aufgabentypList;
    }
}