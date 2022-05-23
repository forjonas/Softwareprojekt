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
    private final MultipleChoiceAufgabe aufgabe;
    private UserloesungMultipleChoiceAufgabe uLMC;
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
    private JPanel panelBearbeitungszeit;
    private JLabel lblBearbeitungszeitString;
    private JLabel lblBearbeitungszeit;
    private JPanel panelPunktzahl;
    private JLabel lblPunktzahlString;
    private JLabel lblDash;
    private JLabel lblMaximalPunktzahl;
    private JTextField txtfUserPunktzahl;
    private JButton btnBewertungSpeichern;
    private JLabel lblAufgabeBildString;
    private JLabel lblUserBild;
    private JLabel lblMusterBild;


    public BewertungMultipleChoiceAufgabeView(MultipleChoiceAufgabe aufgabe, ControllerBewertungenTestate cont) {
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
        MusterloesungMultipleChoiceAufgabe mLMC = (MusterloesungMultipleChoiceAufgabe) aufgabe.getMusterloesung(); //Beschaffen der Musterlösung über die Aufgabe
        List<Boolean> musterLoesungen = mLMC.getMusterloesung();
        UserloesungMultipleChoiceAufgabe uLMC = (UserloesungMultipleChoiceAufgabe) cont.getUserloesung(aufgabe);   //Beschaffen der Userlösung aus der DB über die Aufgabe
        List<Boolean> userLoesungen = uLMC.getUserloesung();
        this.uLMC = uLMC;
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
        txtfUserPunktzahl.setText(uLMC.getErreichtePunkte()+ "");                                            //Die vom Studenten erreichten Punkte

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
            uLMC.setErreichtePunkte(bewertung);
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
