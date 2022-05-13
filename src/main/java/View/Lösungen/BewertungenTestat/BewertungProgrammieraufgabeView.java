package View.Lösungen.BewertungenTestat;

import View.LoesungsHinweisView;
import entity.aufgabe.Programmieraufgabe;
import entity.loesung.musterloesung.MusterloesungProgrammieraufgabe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BewertungProgrammieraufgabeView extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JTextField txtfAufgabentext;
    private JTextField txtfUserLoesung;
    private JTextField txtfMusterloesung;
    private JButton btnBeenden;
    private JButton btnVorherigeAufgabe;
    private JButton btnHinweis;
    private JButton btnNaechsteAufgabe;
    private Programmieraufgabe aufgabe;

    public BewertungProgrammieraufgabeView(Programmieraufgabe aufgabe) {
        this.aufgabe = aufgabe;
        MusterloesungProgrammieraufgabe mLPA = (MusterloesungProgrammieraufgabe) aufgabe.getMusterloesung();
        this.setContentPane(mainPanel);
        this.setTitle(aufgabe.getName());
        btnBeenden.addActionListener(this);
        btnHinweis.addActionListener(this);
        btnVorherigeAufgabe.addActionListener(this);
        btnNaechsteAufgabe.addActionListener(this);
        txtfAufgabentext.setText(aufgabe.getTextbeschreibung());
        txtfMusterloesung.setText(mLPA.getMusterloesung());
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
        ControllerBewertungenTestate.getInstance().beendeBewertungTestat();
    }

    private void naechsteAufgabe() {
        ControllerBewertungenTestate.getInstance().naechsteAufgabe();
    }

    private void vorherigeAufgabe() {
        ControllerBewertungenTestate.getInstance().vorherigeAufgabe();
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
