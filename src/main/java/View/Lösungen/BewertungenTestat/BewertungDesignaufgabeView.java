package View.Lösungen.BewertungenTestat;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import entity.aufgabe.Designaufgabe;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.userloesung.UserloesungDesignaufgabe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BewertungDesignaufgabeView extends JFrame implements ActionListener {
    private ControllerBewertungenTestate cont;
    private final Designaufgabe aufgabe;
    private UserloesungDesignaufgabe uLD;
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
    private JLabel lblDash;
    private JLabel lblMaximalPunktzahl;
    private JPanel panelBearbeitungszeit;
    private JLabel lblBearbeitungszeitString;
    private JLabel lblBearbeitungszeit;
    private JTextField txtfUserPunktzahl;
    private JButton btnBewertungSpeichern;


    public BewertungDesignaufgabeView(Designaufgabe aufgabe, ControllerBewertungenTestate cont) {
        this.cont = cont;
        this.aufgabe = aufgabe;
        this.setContentPane(mainPanel);
        this.setTitle(aufgabe.getName());
        btnBeenden.addActionListener(this);
        btnHinweis.addActionListener(this);
        btnVorherigeAufgabe.addActionListener(this);
        btnNaechsteAufgabe.addActionListener(this);
        btnBewertungSpeichern.addActionListener(this);

        //Setzen der Daten
        txtfAufgabentext.setText(aufgabe.getTextbeschreibung());
        if (aufgabe.getAufgabenstellungsbild() != null) {
            //lblAufgabenstellungsbild.setIcon(aufgabe.getAufgabenstellungsbild());                             //verwendet Objekt vom Typ ImageIcon, welches selbst wiederum eine File verwendet
        }
        lblMaximalPunktzahl.setText(aufgabe.getPunktewert() + "");
        lblBearbeitungszeit.setText(aufgabe.getBearbeitungszeit() + " min");
        MusterloesungDesignaufgabe mLD = (MusterloesungDesignaufgabe) aufgabe.getMusterloesung();       //Beschaffen der Musterlösung über die Aufgabe
        //lblMusterloesung.setIcon(mLD.getMusterloesung());
        UserloesungDesignaufgabe uLD = (UserloesungDesignaufgabe) cont.getUserloesung(aufgabe);         //Beschaffen der Userlösung aus der DB über die Aufgabe
        this.uLD = uLD;
        //lblUserloesung.setIcon(uLD.getUserloesung());
        txtfUserPunktzahl.setText(uLD.getErreichtePunkte() + "");                                         //Die vom Studenten erreichten Punkte

        this.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    @Override
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
            uLD.setErreichtePunkte(bewertung);
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
            if (input == JOptionPane.YES_OPTION) {
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(8, 7, new Insets(10, 10, 15, 10), -1, -1));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnBeenden = new JButton();
        btnBeenden.setText("Beenden");
        mainPanel.add(btnBeenden, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnHinweis = new JButton();
        btnHinweis.setText("Lösungshinweis anzeigen");
        mainPanel.add(btnHinweis, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblUserloesung = new JLabel();
        lblUserloesung.setText("Placeholder Userlösung");
        mainPanel.add(lblUserloesung, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMusterloesung = new JLabel();
        lblMusterloesung.setText("Placeholder Musterlösung");
        mainPanel.add(lblMusterloesung, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        mainPanel.add(spacer3, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnVorherigeAufgabe = new JButton();
        btnVorherigeAufgabe.setText("Vorherige Aufgabe");
        mainPanel.add(btnVorherigeAufgabe, new GridConstraints(6, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(120, -1), new Dimension(160, -1), 0, false));
        btnNaechsteAufgabe = new JButton();
        btnNaechsteAufgabe.setText("Nächste Aufgabe");
        mainPanel.add(btnNaechsteAufgabe, new GridConstraints(7, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(160, -1), 0, false));
        final Spacer spacer4 = new Spacer();
        mainPanel.add(spacer4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        mainPanel.add(spacer5, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txtfAufgabentext = new JTextField();
        txtfAufgabentext.setEditable(false);
        txtfAufgabentext.setText("");
        txtfAufgabentext.setToolTipText("Aufgabentext");
        mainPanel.add(txtfAufgabentext, new GridConstraints(1, 1, 3, 3, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(150, 120), new Dimension(150, 120), null, 0, false));
        lblAufgabenstellungsbild = new JLabel();
        lblAufgabenstellungsbild.setText("");
        mainPanel.add(lblAufgabenstellungsbild, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 200), null, null, 0, false));
        panelPunktzahl = new JPanel();
        panelPunktzahl.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panelPunktzahl, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, 1, 1, null, new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
        lblPunktzahlString = new JLabel();
        lblPunktzahlString.setText("Punktzahl:");
        panelPunktzahl.add(lblPunktzahlString, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblDash = new JLabel();
        lblDash.setText(" / ");
        panelPunktzahl.add(lblDash, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMaximalPunktzahl = new JLabel();
        lblMaximalPunktzahl.setText("10");
        panelPunktzahl.add(lblMaximalPunktzahl, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtfUserPunktzahl = new JTextField();
        txtfUserPunktzahl.setEditable(false);
        txtfUserPunktzahl.setHorizontalAlignment(4);
        panelPunktzahl.add(txtfUserPunktzahl, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(25, -1), new Dimension(25, -1), 0, false));
        final Spacer spacer6 = new Spacer();
        mainPanel.add(spacer6, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        panelBearbeitungszeit = new JPanel();
        panelBearbeitungszeit.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panelBearbeitungszeit, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblBearbeitungszeitString = new JLabel();
        lblBearbeitungszeitString.setText("Bearbeitungszeit:");
        panelBearbeitungszeit.add(lblBearbeitungszeitString, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblBearbeitungszeit = new JLabel();
        lblBearbeitungszeit.setText("10 min");
        panelBearbeitungszeit.add(lblBearbeitungszeit, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnBewertungSpeichern = new JButton();
        btnBewertungSpeichern.setText("Bewertung speichern");
        btnBewertungSpeichern.setVisible(false);
        mainPanel.add(btnBewertungSpeichern, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
