package view;

import entity.benutzer.Benutzer;
import entity.benutzer.Student;
import persistence.DatabaseService;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Ansicht in der ein neuer Benutzer sich Registrieren kann
 *
 * @author Martin Bergen
 * @version 23.05.2022
 */
public class RegistrierenView extends JFrame implements ActionListener {

    DatabaseService ds = DatabaseService.getInstance();
    private JFrame einloggenView;
    private JPanel registrierePnl;
    private JButton registrierenBtn;
    private JButton zurueckBtn;
    private JLabel registriereLbl = new JLabel("Registrieren", SwingConstants.CENTER);
    private JLabel vornameLbl = new JLabel("Vorname:");
    private JLabel nachnameLbl = new JLabel("Nachname:");
    private JLabel matrikelnummerLbl = new JLabel("Matrikelnummer:");
    private JLabel usernameLbl = new JLabel("Benutzername:");
    private JLabel passwortLbl = new JLabel("Kennwort:");
    private JLabel passwortWiederholenLbl = new JLabel("Wiederhole das Kennwort:");

    private JTextField usernameTxt = new JTextField(40);
    private JTextField vornameTxt = new JTextField(40);
    private JTextField nachnameTxt = new JTextField(40);
    private JFormattedTextField matrikelTxt;
    private JPasswordField passwortTxt = new JPasswordField(40);
    private JPasswordField passwortWiederholenTxt = new JPasswordField(40);

    /**
     * Konstruktor der Klasse RegistrierenView, die ein Fenster und die ausgewählten Bausteine verbindet und erstellt.
     *
     * @param hauptmenueView JFrame der EinloggenView, dient zur Logik des Zurück-Buttons
     */
    public RegistrierenView(JFrame hauptmenueView) {
        this.setTitle("Registrieren");
        this.setName("Home");
        this.getContentPane().add(registrierePnl = new JPanel(new BorderLayout()));

        einloggenView = hauptmenueView;

        registrierenBtn = new JButton("Registrieren");
        registrierenBtn.addActionListener(this);
        registrierenBtn.setPreferredSize(new Dimension(130, 30));
        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);
        zurueckBtn.setPreferredSize(new Dimension(130, 30));

        fuelleRegistrierenFrame();


        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
        einloggenView.setVisible(false);
    }

    /**
     * Methode um Bausteine der View zu erstellen und zu verbinden, Methode wird im Konstruktor aufgerufen.
     */
    public void fuelleRegistrierenFrame() {
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);

        matrikelTxt = new JFormattedTextField(formatter);
        registrierePnl.add(registriereLbl, BorderLayout.NORTH);

        JPanel tempPanel2 = new JPanel(new GridLayout(6, 2, 12, 12));
        tempPanel2.setBorder(BorderFactory.createEmptyBorder(30, 5, 25, 5));

        tempPanel2.add(vornameLbl);
        tempPanel2.add(vornameTxt);
        tempPanel2.add(nachnameLbl);
        tempPanel2.add(nachnameTxt);
        tempPanel2.add(usernameLbl);
        tempPanel2.add(usernameTxt);
        tempPanel2.add(matrikelnummerLbl);
        tempPanel2.add(matrikelTxt);
        tempPanel2.add(passwortLbl);
        tempPanel2.add(passwortTxt);
        tempPanel2.add(passwortWiederholenLbl);
        tempPanel2.add(passwortWiederholenTxt);

        registrierePnl.add(tempPanel2, BorderLayout.CENTER);
        registrierePnl.add(new JLabel(""), BorderLayout.EAST);
        registrierePnl.add(new JLabel(""), BorderLayout.WEST);

        JPanel tempButtonPnl = new JPanel(new FlowLayout());
        tempButtonPnl.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        tempButtonPnl.add(registrierenBtn);
        tempButtonPnl.add(zurueckBtn);

        registrierePnl.add(tempButtonPnl, BorderLayout.SOUTH);
        registrierePnl.setBorder(BorderFactory.createEmptyBorder(20, 80, 30, 80));

        this.add(registrierePnl);
    }

    /**
     * Methode zum überprüfen, ob das Passwort und die Wiederholung identisch sind.
     *
     * @return boolean-Wert
     */
    public boolean isPasswortIdentisch() {
        if (Arrays.equals(passwortTxt.getPassword(), passwortWiederholenTxt.getPassword())) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Passwort ist nicht identisch.");
            return false;
        }
    }

    /**
     * Methode zum Überprüfen, ob der gewählte Benutzername bereits existiert.
     *
     * @return boolean-Wert
     */
    public boolean isUsernameTaken() {
        List<Benutzer> list = ds.readBenutzerFromDatabase();
        for (Benutzer b : list) {
            if (usernameTxt.getText().equals(b.getBenutzername())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Methode zum Hinzufügen eines Studenten, der sich registriert hat. Benötigt die Methode isUsernameTaken() und isPasswortIdentisch().
     */
    public void addStudent() {
        if (anyFieldNull() || anyFieldEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte alle Felder füllen", "Felder leer", JOptionPane.WARNING_MESSAGE);
        } else if (isUsernameTaken()) {
            JOptionPane.showMessageDialog(this, "Benutzer existiert bereits.", "Benutzername vergeben", JOptionPane.WARNING_MESSAGE);
        } else if (!isPasswortIdentisch()) {
            JOptionPane.showMessageDialog(this, "Passwort ist nicht identisch.", "Passwort identisch", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                Student student = new Student(usernameTxt.getText(), new String(passwortTxt.getPassword()), vornameTxt.getText(), nachnameTxt.getText(), Integer.parseInt(matrikelTxt.getText()));
                ds.persistObject(student);
                new StudentMainView(student);
                dispose();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Bitte Zahl bei der Matrikelnummer eingeben", "Falsche Matrikelnummer", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Methode die überprüft, ob ein Eingabefeld null ist.
     *
     * @return boolean-Wert
     */
    public boolean anyFieldNull() {
        if (usernameTxt.getText() == null || vornameTxt.getText() == null || nachnameTxt.getText() == null || matrikelTxt.getText() == null || passwortTxt.getPassword() == null || passwortWiederholenTxt.getPassword() == null) {
            return true;
        }
        return false;
    }

    /**
     * Methode die überprüft, ob ein Eingabefeld nicht gefüllt ist.
     *
     * @return boolean-Wert
     */
    public boolean anyFieldEmpty() {
        if (usernameTxt.getText().equals("") || vornameTxt.getText().equals("") || nachnameTxt.getText().equals("") || matrikelTxt.getText().equals("") || passwortTxt.getPassword().equals("") || passwortWiederholenTxt.getPassword().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * Überprüft ob einer der Buttons betätigt wurde und führt dementsprechend, die gesetze Methode aus
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.registrierenBtn) {
            addStudent();
        } else if (e.getSource() == this.zurueckBtn) {
            einloggenView.setVisible(true);
            this.dispose();
        }
    }
}