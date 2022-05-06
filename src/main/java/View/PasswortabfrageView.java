package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Die Ansicht zur Passwort Abfrage
 *
 * @author Jannik Oehme
 * @version 04.05.2022
 */

public class PasswortabfrageView implements ActionListener {

    JFrame PasswortabfrageFrame;
    JPanel PasswortabfragePnl;
    JPasswordField passwordField;
    JButton sendenBtn;
    JButton zurückBtn;
    JLabel promptLbl;

    public static void main(String[] args) {
        new PasswortabfrageView();
    }

    public PasswortabfrageView() {
        PasswortabfrageFrame = new JFrame("Passwortabfrage");
        passwortabfragefüllen();
        PasswortabfrageFrame.setSize(1000, 250);
        PasswortabfrageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PasswortabfrageFrame.setVisible(true);
    }

    private void passwortabfragefüllen() {
        promptLbl = new JLabel("Passwort: ");

        zurückBtn = new JButton("Zurück");
        zurückBtn.addActionListener(this);

        PasswortabfragePnl = new JPanel();
        passwordField = new JPasswordField(15);
        char[] pwdvalue = passwordField.getPassword();

        sendenBtn = new JButton("Senden");
        sendenBtn.addActionListener(this);

        PasswortabfragePnl.add(zurückBtn);
        PasswortabfrageFrame.add(promptLbl);
        PasswortabfragePnl.add(passwordField);
        PasswortabfragePnl.add(sendenBtn);

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

    }

    private void senden() {
    }
}