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
    private JPanel pnlRegistrieren;
    private JButton btnRegistrieren;
    private JButton btnZurueck;
    private JLabel lblRegistriere = new JLabel("Registrieren", SwingConstants.CENTER);
    private JLabel lblVorname = new JLabel("Vorname:");
    private JLabel lblNachname = new JLabel("Nachname:");
    private JLabel lblMatrikelnummer = new JLabel("Matrikelnummer:");
    private JLabel lblUsername = new JLabel("Benutzername:");
    private JLabel lblPasswort = new JLabel("Kennwort:");
    private JLabel lblPasswortWiederholen = new JLabel("Wiederhole das Kennwort:");
    private JTextField txtfUsername = new JTextField(40);
    private JTextField txtfVorname = new JTextField(40);
    private JTextField txtfNachname = new JTextField(40);
    private JFormattedTextField formtxtfMatrikelnummer;
    private JPasswordField pwfPasswort = new JPasswordField(40);
    private JPasswordField pwfPasswortWiederholen = new JPasswordField(40);

    /**
     * Konstruktor der Klasse RegistrierenView, die ein Fenster und die ausgewählten Bausteine verbindet und erstellt.
     *
     * @param hauptmenueView JFrame der EinloggenView, dient zur Logik des Zurück-Buttons
     */
    public RegistrierenView(JFrame hauptmenueView) {
        this.setTitle("Registrieren");
        this.setName("Home");
        this.getContentPane().add(pnlRegistrieren = new JPanel(new BorderLayout()));

        einloggenView = hauptmenueView;

        btnRegistrieren = new JButton("Registrieren");
        btnRegistrieren.addActionListener(this);
        btnRegistrieren.setPreferredSize(new Dimension(130, 30));
        btnZurueck = new JButton("Zurück");
        btnZurueck.addActionListener(this);
        btnZurueck.setPreferredSize(new Dimension(130, 30));

        fuelleRegistrierenFrame();


        this.setSize(600, 600);
        this.setMinimumSize(new Dimension(600, 500));
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

        formtxtfMatrikelnummer = new JFormattedTextField(formatter);
        pnlRegistrieren.add(lblRegistriere, BorderLayout.NORTH);

        JPanel tempPanel2 = new JPanel(new GridLayout(6, 2, 12, 12));
        tempPanel2.setBorder(BorderFactory.createEmptyBorder(30, 5, 25, 5));

        tempPanel2.add(lblVorname);
        tempPanel2.add(txtfVorname);
        tempPanel2.add(lblNachname);
        tempPanel2.add(txtfNachname);
        tempPanel2.add(lblUsername);
        tempPanel2.add(txtfUsername);
        tempPanel2.add(lblMatrikelnummer);
        tempPanel2.add(formtxtfMatrikelnummer);
        tempPanel2.add(lblPasswort);
        tempPanel2.add(pwfPasswort);
        tempPanel2.add(lblPasswortWiederholen);
        tempPanel2.add(pwfPasswortWiederholen);

        pnlRegistrieren.add(tempPanel2, BorderLayout.CENTER);
        pnlRegistrieren.add(new JLabel(""), BorderLayout.EAST);
        pnlRegistrieren.add(new JLabel(""), BorderLayout.WEST);

        JPanel tempButtonPnl = new JPanel(new FlowLayout());
        tempButtonPnl.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        tempButtonPnl.add(btnRegistrieren);
        tempButtonPnl.add(btnZurueck);

        pnlRegistrieren.add(tempButtonPnl, BorderLayout.SOUTH);
        pnlRegistrieren.setBorder(BorderFactory.createEmptyBorder(20, 80, 30, 80));

        this.add(pnlRegistrieren);
    }

    /**
     * Methode zum überprüfen, ob das Passwort und die Wiederholung identisch sind.
     *
     * @return boolean-Wert
     */
    public boolean isPasswortIdentisch() {
        if (Arrays.equals(pwfPasswort.getPassword(), pwfPasswortWiederholen.getPassword())) {
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
            if (txtfUsername.getText().equals(b.getBenutzername())) {
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
                Student student = new Student(txtfUsername.getText(), new String(pwfPasswort.getPassword()), txtfVorname.getText(), txtfNachname.getText(), Integer.parseInt(formtxtfMatrikelnummer.getText()));
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
        if (txtfUsername.getText() == null || txtfVorname.getText() == null || txtfNachname.getText() == null || formtxtfMatrikelnummer.getText() == null || pwfPasswort.getPassword() == null || pwfPasswortWiederholen.getPassword() == null) {
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
        if (txtfUsername.getText().equals("") || txtfVorname.getText().equals("") || txtfNachname.getText().equals("") || formtxtfMatrikelnummer.getText().equals("") || pwfPasswort.getPassword().equals("") || pwfPasswortWiederholen.getPassword().equals("")) {
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
        if (e.getSource() == this.btnRegistrieren) {
            addStudent();
        } else if (e.getSource() == this.btnZurueck) {
            einloggenView.setVisible(true);
            this.dispose();
        }
    }
}
