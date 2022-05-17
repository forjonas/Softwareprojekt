package View.Lösungen.BewertungenTestat;

import entity.aufgabe.Designaufgabe;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.userloesung.UserloesungDesignaufgabe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BewertungDesignaufgabeView extends JFrame implements ActionListener {
    private ControllerBewertungenTestate cont;
    private JPanel mainPanel;
    private JTextField txtfAufgabentext;
    private JButton btnBeenden;
    private JButton btnHinweis;
    private JLabel lblUserloesung;
    private JLabel lblMusterloesung;
    private JButton btnVorherigeAufgabe;
    private JButton btnNaechsteAufgabe;
    private JLabel lblAufgabenstellungsbild;
    private JPanel panelPunktzahl;
    private JLabel lblPunktzahlString;
    private JLabel lblUserPunktzahl;
    private JLabel lblDash;
    private JLabel lblMaximalPunktzahl;
    private JPanel panelBearbeitungszeit;
    private JLabel lblBearbeitungszeitString;
    private JLabel lblBearbeitungszeit;
    private final Designaufgabe aufgabe;

    public BewertungDesignaufgabeView(Designaufgabe aufgabe, ControllerBewertungenTestate cont) {
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
        lblMaximalPunktzahl.setText(aufgabe.getPunktewert()+ "");
        lblBearbeitungszeit.setText(aufgabe.getBearbeitungszeit() + " min");
        /*
        MusterloesungDesignaufgabe mLD = (MusterloesungDesignaufgabe) aufgabe.getMusterloesung();       //Beschaffen der Musterlösung über die Aufgabe
        lblMusterloesung.setIcon(mLD.getMusterloesung());
        UserloesungDesignaufgabe uLD = (UserloesungDesignaufgabe) cont.getUserloesung(aufgabe);         //Beschaffen der Userlösung aus der DB über die Aufgabe
        lblUserloesung.setIcon(uLD.getUserloesung());
        */

        this.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    @Override
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
