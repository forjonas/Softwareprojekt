package View.Lösungen.BewertungenTestat;

import View.LoesungsHinweisView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BewertungDesignaufgabeView extends JFrame implements ActionListener{
    private JPanel mainPanel;
    private JTextField txtfAufgabentext;
    private JButton btnBeenden;
    private JButton btnHinweis;
    private JLabel lblPlaceholderUserloesung;
    private JLabel lblPlaceholderMusterloesung;
    private JButton btnVorherigeAufgabe;
    private JButton btnNaechsteAufgabe;

    public BewertungDesignaufgabeView(){
        this.setContentPane(mainPanel);
        btnBeenden.addActionListener(this);
        btnHinweis.addActionListener(this);
        btnVorherigeAufgabe.addActionListener(this);
        btnNaechsteAufgabe.addActionListener(this);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeenden) {
            beenden();
        } else if (e.getSource() == this.btnHinweis) {
            LoesungsHinweisView hinweisView = new LoesungsHinweisView();
        } else if (e.getSource() == this.btnNaechsteAufgabe) {
            naechsteAufgabe();
        } else if (e.getSource() == this.btnVorherigeAufgabe) {
            vorherigeAufgabe();
        }
    }


    private void beenden(){

    }

    private void naechsteAufgabe(){

    }

    private void vorherigeAufgabe(){

    }


}
