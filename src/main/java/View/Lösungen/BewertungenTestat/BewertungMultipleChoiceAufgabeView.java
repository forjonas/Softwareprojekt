package View.Lösungen.BewertungenTestat;

import entity.aufgabe.MultipleChoiceAufgabe;
import entity.loesung.musterloesung.MusterloesungMultipleChoiceAufgabe;
import entity.loesung.userloesung.UserloesungMultipleChoiceAufgabe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BewertungMultipleChoiceAufgabeView extends JFrame implements ActionListener {
    private ControllerBewertungenTestate cont;
    private JPanel mainPanel;
    private JTextField txtfAufgabentext;
    private JPanel panelUserChoices;
    private JRadioButton btnUserloesung1;
    private JRadioButton btnUserloesung2;
    private JRadioButton btnUserloesung3;
    private JRadioButton btnUserloesung4;
    private JPanel panelMusterChoices;
    private JRadioButton btnMusterloesung1;
    private JRadioButton btnMusterloesung2;
    private JRadioButton btnMusterloesung3;
    private JRadioButton btnMusterloesung4;
    private JButton btnVorherigeAufgabe;
    private JButton btnNaechsteAufgabe;
    private JButton btnHinweis;
    private JButton btnBeenden;
    private JLabel lblAufgabenstellungsbild;
    private final MultipleChoiceAufgabe aufgabe;

    public BewertungMultipleChoiceAufgabeView(MultipleChoiceAufgabe aufgabe, ControllerBewertungenTestate cont) {
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
        MusterloesungMultipleChoiceAufgabe mLMC = (MusterloesungMultipleChoiceAufgabe) aufgabe.getMusterloesung(); //Beschaffen der Musterlösung über die Aufgabe
        List<Boolean> musterLoesungen = mLMC.getMusterloesung();
        UserloesungMultipleChoiceAufgabe uLMC = (UserloesungMultipleChoiceAufgabe) cont.getUserloesung(aufgabe);   //Beschaffen der Userlösung aus der DB über die Aufgabe
        List<Boolean> userLoesungen = uLMC.getUserloesung();
        btnMusterloesung1.setSelected(musterLoesungen.get(0));
        btnUserloesung1.setSelected(userLoesungen.get(0));
        if (userLoesungen.size() == 4) {
            btnMusterloesung2.setSelected(musterLoesungen.get(1));
            btnMusterloesung3.setSelected(musterLoesungen.get(2));
            btnMusterloesung4.setSelected(musterLoesungen.get(3));
            btnUserloesung2.setSelected(userLoesungen.get(1));
            btnUserloesung3.setSelected(userLoesungen.get(2));
            btnUserloesung4.setSelected(userLoesungen.get(3));
        } else if (userLoesungen.size() == 3) {
            btnMusterloesung2.setSelected(musterLoesungen.get(1));
            btnMusterloesung3.setSelected(musterLoesungen.get(2));
            btnUserloesung2.setSelected(userLoesungen.get(1));
            btnUserloesung3.setSelected(userLoesungen.get(2));
        } else if (userLoesungen.size() == 2) {
            btnMusterloesung2.setSelected(musterLoesungen.get(1));
            btnUserloesung2.setSelected(userLoesungen.get(1));
        }

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
