package view;

import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.benutzer.Student;
import persistence.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;


public class LoginView extends JFrame implements ActionListener, KeyListener {

    persistence.DatabaseService ds = DatabaseService.getInstance();
    private JButton btnRegistrieren;
    private JButton btnEinloggen;
    private JLabel lblEinloggen = new JLabel("Einloggen", SwingConstants.CENTER);
    private JLabel lblUsername = new JLabel("Benutzername:", SwingConstants.LEADING);
    private JLabel lblPasswort = new JLabel("Kennwort:", SwingConstants.LEADING);
    private JTextField txtfUsername = new JTextField(30);
    private JPasswordField pwfPasswort = new JPasswordField(30);

    public static void main(String[] args) {
        new LoginView();
    }

    /**
     * Konstruktor der Klasse LoginView, die ein Fenster und die ausgewählten Bausteine verbindet und erstellt.
     */
    public LoginView() {
        this.setName("Home");
        this.setTitle("Log-In");

        btnRegistrieren = new JButton("Registrieren");
        btnRegistrieren.addActionListener(this);
        btnRegistrieren.setPreferredSize(new Dimension(70, 30));
        btnEinloggen = new JButton("Einloggen");
        btnEinloggen.addActionListener(this);
        btnEinloggen.setPreferredSize(new Dimension(70, 30));

        fuelleHomeFrame();

        this.setSize(600, 600);
        this.setMinimumSize(new Dimension(500, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    /**
     * Methode um Bausteine der View zu erstellen und zu verbinden, Methode wird im Konstruktor aufgerufen.
     */
    public void fuelleHomeFrame() {
        JPanel tempPanel = new JPanel(new BorderLayout());

        JPanel tempPanel3 = new JPanel(new GridBagLayout());
        tempPanel3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        //1. Reihe 1.Spalte
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 0);
        tempPanel3.add(lblEinloggen, c);

        //2. Reihe 1.Spalte
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 1;
        c.insets = new Insets(25, 7, 0, 10);
        tempPanel3.add(lblUsername, c);

        //2. Reihe 2. Spalte
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 1;
        c.insets = new Insets(25, 10, 0, 7);
        tempPanel3.add(txtfUsername, c);

        //3. Reihe 1. Spalte
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 2;
        c.insets = new Insets(25, 7, 0, 10);
        tempPanel3.add(lblPasswort, c);

        //3. Reihe 2. Spalte
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 2;
        c.insets = new Insets(25, 10, 0, 7);
        tempPanel3.add(pwfPasswort, c);

        //4. Reihe 1. u. 2. Spalte
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 3;
        c.insets = new Insets(30, 7, 0, 7);
        tempPanel3.add(btnEinloggen, c);

        //5. Reihe 1. u. 2. Spalte
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 4;
        c.insets = new Insets(20, 0, 0, 0);
        tempPanel3.add(new JLabel("-oder-", SwingConstants.CENTER), c);

        //6. Reihe 1. u. 2. Spalte
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 5;
        c.insets = new Insets(20, 7, 0, 7);
        tempPanel3.add(btnRegistrieren, c);

        tempPanel.add(tempPanel3, BorderLayout.CENTER);
        this.add(tempPanel3);
    }

    /**
     * Methode, die die Funktionalität des Einloggen bildet.
     */
    public void einloggenSequenz() {
        if (txtfUsername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte Benutzernamen eingeben.");
        } else if (pwfPasswort.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Bitte Passwort eingeben.");
        } else {
            if (userIstStudent(txtfUsername.getText())) {
                Student student = (Student) ds.readStudentnachBenutzernamen(txtfUsername.getText());
                if (checkPasswort(student)) {
                    new StudentMainView(student);
                    this.dispose();
                    return;
                }
            } else if (userIstDozent(txtfUsername.getText())) {
                Dozent dozent = (Dozent) ds.readDozentnachBenutzernamen(txtfUsername.getText());
                if (checkPasswort(dozent)) {
                    new DozentMainView(dozent);
                    this.dispose();
                    return;
                }
            } else JOptionPane.showMessageDialog(this, "Benutzername ist nicht bekannt.");
        }
    }

    /**
     * Methode die überprüft, ob der sich anmeldende Benutzer Student ist
     *
     * @param username Username des anmeldenden Benutzers
     * @return boolean-Wert
     */
    public boolean userIstStudent(String username) {
        List<Student> list = ds.readStudentenFromDatabase();
        for (Student student : list) {
            if (username.equals(student.getBenutzername())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Methode die überprüft, ob der sich anmeldende Benutzer Dozent ist
     *
     * @param username Username des anmeldenden Benutzers
     * @return boolean-Wert
     */
    public boolean userIstDozent(String username) {
        List<Dozent> list = ds.readDozentenFromDatabase();
        for (Dozent dozent : list) {
            if (username.equals(dozent.getBenutzername())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Methode die überprüft, ob das eingegebene Passwort dem User-Passwort übergibt
     *
     * @param user Benutzer dessen Passwort überprüft wird
     * @return boolean-Wert
     */
    public boolean checkPasswort(Benutzer user) {
        if (Arrays.equals(pwfPasswort.getPassword(), user.getPasswort().toCharArray())) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Bitte richtiges Passwort eingeben");
            return false;
        }
    }

    /**
     * Überprüft ob einer der Bottuns betätigt wurde und führt dementsprechen, die gesetze Methode aus
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.btnRegistrieren) {
            new RegistrierenView(this);
        } else if (e.getSource() == this.btnEinloggen) {
            einloggenSequenz();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            einloggenSequenz();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
