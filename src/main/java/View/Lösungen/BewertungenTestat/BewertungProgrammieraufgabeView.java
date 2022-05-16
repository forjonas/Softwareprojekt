package View.Lösungen.BewertungenTestat;

import View.LoesungsHinweisView;
import entity.aufgabe.Programmieraufgabe;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;
import entity.loesung.userloesung.UserloesungMultipleChoiceAufgabe;
import entity.loesung.userloesung.UserloesungProgrammieraufgabe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BewertungProgrammieraufgabeView extends JFrame implements ActionListener {
    private ControllerBewertungenTestate cont;
    private JPanel mainPanel;
    private JTextField txtfAufgabentext;
    private JTextField txtfUserLoesung;
    private JTextField txtfMusterloesung;
    private JButton btnBeenden;
    private JButton btnVorherigeAufgabe;
    private JButton btnHinweis;
    private JButton btnNaechsteAufgabe;
    private JLabel lblAufgabenstellungsbild;
    private Programmieraufgabe aufgabe;

    public BewertungProgrammieraufgabeView(Programmieraufgabe aufgabe, ControllerBewertungenTestate cont) {
        this.cont = cont;
        this.aufgabe = aufgabe;
        this.setContentPane(mainPanel);
        this.setTitle(aufgabe.getName());
        btnBeenden.addActionListener(this);
        btnHinweis.addActionListener(this);
        btnVorherigeAufgabe.addActionListener(this);
        btnNaechsteAufgabe.addActionListener(this);

        //Setzen der Daten
        txtfAufgabentext.setText(aufgabe.getTextbeschreibung());
        if (aufgabe.getAufgabenstellungsbild() != null) {
            //lblAufgabenstellungsbild.setIcon(aufgabe.getAufgabenstellungsbild());                             //verwendet Objekt vom Typ ImageIcon, welches selbst wiederum eine File verwendet
        }
        MusterloesungProgrammieraufgabe mLP = (MusterloesungProgrammieraufgabe) aufgabe.getMusterloesung();      //Beschaffen der Musterlösung über die Aufgabe
        txtfMusterloesung.setText(mLP.getMusterloesung());
        UserloesungProgrammieraufgabe uLP = (UserloesungProgrammieraufgabe) cont.getUserloesung(aufgabe);        //Beschaffen der Userlösung aus der DB über die Aufgabe
        txtfUserLoesung.setText(uLP.getUserloesung());

        this.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeenden) {
            this.dispose();
            beenden();
        } else if (e.getSource() == this.btnHinweis) {
            JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis());
        } else if (e.getSource() == this.btnNaechsteAufgabe) {
            this.dispose();
            naechsteAufgabe();
        } else if (e.getSource() == this.btnVorherigeAufgabe) {
            this.dispose();
            vorherigeAufgabe();
        }
    }

    private void beenden() {
        cont.beendeBewertungTestat();
    }

    private void naechsteAufgabe() {
        cont.naechsteAufgabe();
    }

    private void vorherigeAufgabe() {
        cont.vorherigeAufgabe();
    }

    public void versteckeNaechsteAufgabe() {
        this.btnNaechsteAufgabe.setVisible(false);
        this.update(this.getGraphics());
    }

    public void versteckeVorherigeAufgabe() {
        this.btnVorherigeAufgabe.setVisible(false);
        this.update(this.getGraphics());
    }
}
