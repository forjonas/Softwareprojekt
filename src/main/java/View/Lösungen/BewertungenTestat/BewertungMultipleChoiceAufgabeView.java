package View.Lösungen.BewertungenTestat;

import View.LoesungsHinweisView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BewertungMultipleChoiceAufgabeView extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JTextField txtfAufgabentext;
    private JPanel panelUserChoices;
    private JRadioButton btnMoeglichkeit1;
    private JRadioButton btnMoeglichkeit2;
    private JRadioButton btnMoeglichkeit3;
    private JRadioButton btnMoeglichkeit4;
    private JPanel panelMusterChoices;
    private JRadioButton btnLoesung1;
    private JRadioButton btnLoesung2;
    private JRadioButton btnLoesung3;
    private JRadioButton btnLoesung4;
    private JButton btnVorherigeAufgabe;
    private JButton btnNaechsteAufgabe;
    private JButton btnHinweis;
    private JButton btnBeenden;

    public BewertungMultipleChoiceAufgabeView(){
        this.setContentPane(mainPanel);
        btnBeenden.addActionListener(this);
        btnHinweis.addActionListener(this);
        btnVorherigeAufgabe.addActionListener(this);
        btnNaechsteAufgabe.addActionListener(this);
        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeenden) {
            beenden();
        } else if (e.getSource() == this.btnHinweis) {
            //LoesungsHinweisView hinweisView = new LoesungsHinweisView();
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
