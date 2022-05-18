package View;

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


    public RegistrierenView(JFrame hauptmenueView) {
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
        einloggenView.setVisible(false);
    }

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

    public boolean checkPasswort() {
        if (Arrays.equals(passwortTxt.getPassword(), passwortWiederholenTxt.getPassword())) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Passwort ist nicht identisch.");
            return false;
        }
    }

    public boolean istBereitsStudent() {
        List<Student> list = ds.readStudentenFromDatabase();
        for (int i = 0; i <= list.size(); i++) {
            if (usernameTxt.getText().equals(list.get(i).getBenutzername())) {
                JOptionPane.showMessageDialog(this, "Benutzer existiert bereits.");
                return true;
            }
        }
        return false;
    }

    public void addStudent() {
        if (fieldsEmpty()||anyFieldEmpty())
        {
            JOptionPane.showMessageDialog(this,"Bitte alle Felder füllen");
        } else if (usernameTxt.getText()==null) {
            JOptionPane.showMessageDialog(this,"Bitte Benutzernamen eingeben.");

        } else if (!istBereitsStudent() && checkPasswort()) {
                Student student = new Student(usernameTxt.getText(), new String(passwortTxt.getPassword()), vornameTxt.getText(), nachnameTxt.getText(), Integer.parseInt(matrikelTxt.getText()));
                ds.persistObject(student);
                new StudentMainView(student);
                this.dispose();
            }

    }

    public boolean fieldsEmpty() {
        if (usernameTxt.getText() == "" && vornameTxt.getText() == null && nachnameTxt.getText() == null && matrikelTxt.getText() == null && passwortTxt.getPassword() == null && passwortWiederholenTxt.getPassword() == null) {
            return true;
        }
        return false;
    }
    public boolean anyFieldEmpty(){
        if (usernameTxt.getText() == null || vornameTxt.getText() == null || nachnameTxt.getText() == null || matrikelTxt.getText() == null || passwortTxt.getPassword() == null || passwortWiederholenTxt.getPassword() == null) {
            return true;
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.registrierenBtn) {
            addStudent();
        } else if (e.getSource() == this.zurueckBtn) {
            einloggenView.setVisible(true);
            this.dispose();
        }
    }
}
