package View;

import entity.aufgabensammlung.Testat;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Die Ansicht zur Passwort Abfrage
 *
 * @author Jannik Oehme
 * @version 04.05.2022
 * @version 09.05.2022 Layout gefixed funktionalität geadded es fehlt noch die übergabe des testat objekts damit die pw abfrage
 * richtig ist und der übergang in das TEstat gemacht werden kann
 */

public class PasswortabfrageView implements ActionListener {
    Testat ausgewTestat;

    JFrame PasswortabfrageFrame;
    JPanel PasswortabfragePnl;
    JPasswordField passwordField;
    JButton sendenBtn;
    JButton zurückBtn;
    JLabel promptLbl;
    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;
    BorderLayout bl = new BorderLayout();
    GridLayout gl = new GridLayout(1,2);

    public static void main(String [] args) {
        Testat test = new Testat();
        test.setPasswort("asd");
        new PasswortabfrageView(test);
    }

    public PasswortabfrageView(Testat test) {
        ausgewTestat = test;
        PasswortabfrageFrame = new JFrame("Passwortabfrage");
        passwortabfragefüllen();
        PasswortabfrageFrame.setSize(300, 135);
        PasswortabfrageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PasswortabfrageFrame.setVisible(true);
    }

    private void passwortabfragefüllen() {
        centerPnl = new JPanel(gl);
        southPnl = new JPanel();
        northPnl = new JPanel();
        PasswortabfragePnl = new JPanel(bl);
        centerPnl.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));

        promptLbl = new JLabel("Passwort: ");

        zurückBtn = new JButton("Zurück");
        zurückBtn.addActionListener(this);

        passwordField = new JPasswordField(15);

        sendenBtn = new JButton("Senden");
        sendenBtn.addActionListener(this);

        centerPnl.add(promptLbl);
        centerPnl.add(passwordField);

        southPnl.add(sendenBtn);

        northPnl.add(zurückBtn);

        PasswortabfragePnl.add(centerPnl, BorderLayout.CENTER);
        PasswortabfragePnl.add(southPnl,BorderLayout.SOUTH);
        PasswortabfragePnl.add(northPnl,BorderLayout.NORTH);
        PasswortabfrageFrame.add(PasswortabfragePnl);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurückBtn) {
            zurück();
        } else if (e.getSource() == this.sendenBtn) {
            senden();
        }

    }
    private void zurück() {
        PasswortabfrageFrame.dispose();
        //Testat keine ahnung welche ansicht das ist
    }

    private void senden() {
        char[] pwdvalue = passwordField.getPassword();
        String pwdvalString = new String(pwdvalue);

        if (pwdvalString.equalsIgnoreCase(ausgewTestat.getPasswort())){
            //WeiterMachen
        }
        else{
             JDialog jd = new JDialog(PasswortabfrageFrame,"Falsches Passwort");
            JLabel wPWLbl = new JLabel("Falsches Passwort");
            jd.setSize(100,100);
            jd.add(wPWLbl);
            jd.setVisible(true);
        }
    }
}