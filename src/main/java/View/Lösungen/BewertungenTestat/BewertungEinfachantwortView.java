package View.Lösungen.BewertungenTestat;

import entity.aufgabe.EinfachantwortAufgabe;
import entity.loesung.musterloesung.MusterloesungEinfachantwort;
import entity.loesung.userloesung.UserloesungEinfachantwort;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BewertungEinfachantwortView extends JFrame implements ActionListener {
    private ControllerBewertungenTestate cont;
    private final EinfachantwortAufgabe aufgabe;
    private UserloesungEinfachantwort uLE;
    private JPanel mainPanel;
    private JTextField txtfAufgabentext;
    private JTextField txtfUserLoesung;
    private JTextField txtfMusterloesung;
    private JButton btnVorherigeAufgabe;
    private JButton btnNaechsteAufgabe;
    private JButton btnHinweis;
    private JButton btnBeenden;
    private JLabel lblAufgabenstellungsbild;
    private JPanel panelBearbeitungszeit;
    private JLabel lblBearbeitungszeitString;
    private JLabel lblBearbeitungszeit;
    private JPanel panelPunktzahl;
    private JLabel lblPunktzahlString;
    private JLabel lblUserPunktzahl;
    private JLabel lblDash;
    private JLabel lblMaximalPunktzahl;
    private JTextField txtfUserPunktzahl;
    private JButton btnBewertungSpeichern;
    private JLabel lblAufgabeBildString;
    private JLabel lblUserBild;
    private JLabel lblMusterBild;


    public BewertungEinfachantwortView(EinfachantwortAufgabe aufgabe, ControllerBewertungenTestate cont) {
        this.cont = cont;
        this.aufgabe = aufgabe;
        this.setContentPane(mainPanel);
        this.setTitle(aufgabe.getName());
        btnBeenden.addActionListener(this);
        btnHinweis.addActionListener(this);
        btnVorherigeAufgabe.addActionListener(this);
        btnNaechsteAufgabe.addActionListener(this);
        btnBewertungSpeichern.addActionListener(this);
        lblAufgabeBildString.setVisible(false);

        //Setzen der Daten
        txtfAufgabentext.setText(aufgabe.getTextbeschreibung());
        if (aufgabe.getAufgabenstellungsbild() != null) {
            lblAufgabeBildString.setVisible(true);
            lblAufgabenstellungsbild.setIcon(new ImageIcon(aufgabe.getAufgabenstellungsbild()));
        }
        lblMaximalPunktzahl.setText(aufgabe.getPunktewert()+ "");
        lblBearbeitungszeit.setText(aufgabe.getBearbeitungszeit() + " min");
        MusterloesungEinfachantwort musterloesungEinfachantwort = (MusterloesungEinfachantwort) aufgabe.getMusterloesung();        //Beschaffen der Musterlösung über die Aufgabe
        txtfMusterloesung.setText(musterloesungEinfachantwort.getMusterloesung());
        UserloesungEinfachantwort userloesungEinfachantwort = (UserloesungEinfachantwort) cont.getUserloesung(aufgabe);          //Beschaffen der Userlösung aus der DB über die Aufgabe
        this.uLE = userloesungEinfachantwort;
        txtfUserLoesung.setText(uLE.getUserloesung());
        txtfUserPunktzahl.setText(uLE.getErreichtePunkte()+ "");                                            //Die vom Studenten erreichten Punkte

        this.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeenden) {
            beenden();
        } else if (e.getSource() == this.btnHinweis) {
            JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis());
        } else if (e.getSource() == this.btnNaechsteAufgabe) {
            this.dispose();
            naechsteAufgabe();
        } else if (e.getSource() == this.btnVorherigeAufgabe) {
            this.dispose();
            vorherigeAufgabe();
        } else if (e.getSource() == this.btnBewertungSpeichern) {
            try {
                Integer.parseInt(txtfUserPunktzahl.getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Diese Eingabe ist kein ganzzahliger Eintrag!");
                return;
            }
            int bewertung = Integer.parseInt(txtfUserPunktzahl.getText());
            if (!(0 <= bewertung && bewertung <= aufgabe.getPunktewert())) {
                JOptionPane.showMessageDialog(this, "Diese Eingabe ist hinsichtlich der erreichbaren Punkte ungültig!");
                return;
            }
            uLE.setErreichtePunkte(bewertung);
            cont.setBewertet();
        }
    }

    private void beenden() {
        if (cont.bewertungVollstaendig() || !cont.userIstDozent()) {
            this.dispose();
            cont.beendeBewertungTestat();
        } else {
            int input = JOptionPane.showConfirmDialog(this, "Wenn sie jetzt abbrechen, werden Ihre Eingaben nicht gespeichert.\n" +
                    "Möchten Sie die Bewertung trotzdem beenden?", "Die Bewertung ist noch nicht vollständig!", JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                this.dispose();
                cont.abbrechenBewertungTestat();
            }
        }
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

    public void bewertbar() {
        this.txtfUserPunktzahl.setEditable(true);
        this.btnBewertungSpeichern.setVisible(true);
        this.update(this.getGraphics());
    }
}
