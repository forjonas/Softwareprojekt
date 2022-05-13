package View.Lösungen.BewertungenTestat;

import View.LoesungsHinweisView;
import entity.aufgabe.MultipleChoiceAufgabe;
import entity.loesung.musterloesung.MusterloesungMultipleChoiceAufgabe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
    private final MultipleChoiceAufgabe aufgabe;

    public BewertungMultipleChoiceAufgabeView(MultipleChoiceAufgabe aufgabe) {
        this.aufgabe = aufgabe;
        MusterloesungMultipleChoiceAufgabe mLMCA = (MusterloesungMultipleChoiceAufgabe) aufgabe.getMusterloesung();
        this.setContentPane(mainPanel);
        this.setTitle(aufgabe.getName());
        btnBeenden.addActionListener(this);
        btnHinweis.addActionListener(this);
        btnVorherigeAufgabe.addActionListener(this);
        btnNaechsteAufgabe.addActionListener(this);
        txtfAufgabentext.setText(aufgabe.getTextbeschreibung());
        List<Boolean> musterLoesungen = mLMCA.getMusterloesung();       //Zunächst NUR 4 Optionen unterstützt
        btnLoesung1.setSelected(musterLoesungen.get(0));
        btnLoesung2.setSelected(musterLoesungen.get(1));
        btnLoesung3.setSelected(musterLoesungen.get(2));
        btnLoesung4.setSelected(musterLoesungen.get(3));
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
