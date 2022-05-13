package View.Lösungen.BewertungenTestat;

import View.LoesungsHinweisView;
import entity.aufgabe.Designaufgabe;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BewertungDesignaufgabeView extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JTextField txtfAufgabentext;
    private JButton btnBeenden;
    private JButton btnHinweis;
    private JLabel lblPlaceholderUserloesung;
    private JLabel lblPlaceholderMusterloesung;
    private JButton btnVorherigeAufgabe;
    private JButton btnNaechsteAufgabe;
    private final Designaufgabe aufgabe;

    public BewertungDesignaufgabeView(Designaufgabe aufgabe) {
        this.aufgabe = aufgabe;
        MusterloesungDesignaufgabe mLD = (MusterloesungDesignaufgabe) aufgabe.getMusterloesung();
        this.setContentPane(mainPanel);
        this.setTitle(aufgabe.getName());
        btnBeenden.addActionListener(this);
        btnHinweis.addActionListener(this);
        btnVorherigeAufgabe.addActionListener(this);
        btnNaechsteAufgabe.addActionListener(this);
        txtfAufgabentext.setText(aufgabe.getTextbeschreibung());
        //lblPlaceholderMusterloesung.setText(mLD.getMusterloesung());        //Placeholder
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
