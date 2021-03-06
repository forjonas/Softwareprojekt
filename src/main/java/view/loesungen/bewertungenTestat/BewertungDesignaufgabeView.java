package view.loesungen.bewertungenTestat;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import controller.BewertungenTestateController;
import entity.aufgabe.Designaufgabe;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.userloesung.UserloesungDesignaufgabe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ansicht zur Darstellung und Bewertung einer Designaufgabe und dazugehöriger Lösungen im Kontext eines Testates.
 *
 * @author Timo Joswig
 * @version 29.05.2022
 */
public class BewertungDesignaufgabeView extends JFrame implements ActionListener {
    private final BewertungenTestateController BewertungenTestateController;
    private final Designaufgabe aufgabe;
    private final UserloesungDesignaufgabe userloesungDesignaufgabe;
    private JPanel mainPanel;
    private JButton btnBeenden;
    private JButton btnHinweis;
    private JButton btnVorherigeAufgabe;
    private JButton btnNaechsteAufgabe;
    private JButton btnBewertungSpeichern;
    private JTextArea txtaAufgabentext;
    private JLabel lblUserloesung;
    private JLabel lblMusterloesung;
    private JLabel lblAufgabenstellungsbild;
    private JPanel pnlPunktzahl;
    private JLabel lblPunktzahlString;
    private JTextField txtfUserPunktzahl;
    private JLabel lblDash;
    private JLabel lblMaximalPunktzahl;
    private JPanel pnlBearbeitungszeit;
    private JLabel lblBearbeitungszeitString;
    private JLabel lblBearbeitungszeit;
    private JLabel lblAufgabeBildString;
    private JLabel lblUserBild;
    private JLabel lblMusterBild;

    /**
     * Konstruktor für die benötigte View.
     *
     * @param aufgabe die darzustellende Aufgabe vom Typ Design
     * @param BewertungenTestateController der Testatcontroller der diesen Konstruktor aufgerufen hat
     */
    public BewertungDesignaufgabeView(Designaufgabe aufgabe, BewertungenTestateController BewertungenTestateController) {
        this.BewertungenTestateController = BewertungenTestateController;
        this.aufgabe = aufgabe;
        this.setContentPane(mainPanel);
        this.setTitle(aufgabe.getName());
        btnBeenden.addActionListener(this);
        btnHinweis.addActionListener(this);
        btnVorherigeAufgabe.addActionListener(this);
        btnNaechsteAufgabe.addActionListener(this);
        btnBewertungSpeichern.addActionListener(this);
        lblAufgabeBildString.setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtaAufgabentext.setText(aufgabe.getTextbeschreibung());
        txtaAufgabentext.setLineWrap(true);
        if (aufgabe.getAufgabenstellungsbild() != null) {
            lblAufgabeBildString.setVisible(true);
            lblAufgabenstellungsbild.setIcon(new ImageIcon(aufgabe.getAufgabenstellungsbild()));
        }
        lblMaximalPunktzahl.setText(aufgabe.getPunktewert() + "");
        lblBearbeitungszeit.setText(aufgabe.getBearbeitungszeit() + " min");
        MusterloesungDesignaufgabe musterloesungDesignaufgabe = (MusterloesungDesignaufgabe) aufgabe.getMusterloesung();
        lblMusterloesung.setIcon(new ImageIcon(musterloesungDesignaufgabe.getMusterloesung()));
        UserloesungDesignaufgabe userloesungDesignaufgabe = (UserloesungDesignaufgabe) BewertungenTestateController.getUserloesung(aufgabe);
        this.userloesungDesignaufgabe = userloesungDesignaufgabe;
        lblUserloesung.setIcon(new ImageIcon(userloesungDesignaufgabe.getUserloesung()));
        txtfUserPunktzahl.setText(userloesungDesignaufgabe.getErreichtePunkte() + "");

        this.pack();
        this.setMinimumSize(new Dimension(400 + this.lblAufgabenstellungsbild.getWidth() + this.lblUserloesung.getWidth() + this.lblMusterloesung.getWidth(), 450 + this.lblMusterloesung.getHeight()));
        this.setSize(this.getMinimumSize());
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    /**
     * Die Funktion welche die Funktionalitäten des Actionlistener-Interfaces der Klasse implementiert.
     *
     * @param e eines der möglichen Button-Events
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeenden) {
            beenden();
        } else if (e.getSource() == this.btnHinweis) {
            if (aufgabe.getMusterloesung().getLoesungshinweis() != null) {
                JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis());
            } else {
                JOptionPane.showMessageDialog(this, "Kein Lösungshinweis vorhanden.", "Lösungshinweis", JOptionPane.WARNING_MESSAGE);
            }
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
            userloesungDesignaufgabe.setErreichtePunkte(bewertung);
            BewertungenTestateController.setBewertet();
        }
    }

    /**
     * Methode die aufgerufen wird, wenn dem Controller mitgeteilt werden soll, dass der User die Einsicht oder die Bewertung beenden möchte.
     */
    private void beenden() {
        if (BewertungenTestateController.bewertungVollstaendig() || !BewertungenTestateController.userIstDozent()) {
            this.dispose();
            BewertungenTestateController.beendeBewertungTestat();
        } else {
            int input = JOptionPane.showConfirmDialog(this, "Wenn sie jetzt abbrechen, werden Ihre Eingaben nicht gespeichert.\n" +
                    "Möchten Sie die Bewertung trotzdem beenden?", "Die Bewertung ist noch nicht vollständig!", JOptionPane.YES_NO_OPTION);
            if (input == JOptionPane.YES_OPTION) {
                this.dispose();
                BewertungenTestateController.abbrechenBewertungTestat();
            }
        }
    }

    /**
     * Methode die aufgerufen wird, wenn dem Controller mitgeteilt werden soll, dass die nächste Aufgabe angezeigt werden soll.
     */
    private void naechsteAufgabe() {
        BewertungenTestateController.naechsteAufgabe();
    }

    /**
     * Methode die aufgerufen wird, wenn dem Controller mitgeteilt werden soll, dass die vorherige Aufgabe angezeigt werden soll.
     */
    private void vorherigeAufgabe() {
        BewertungenTestateController.vorherigeAufgabe();
    }

    /**
     * Hiermit teilt der Controller der View mit, dass sie den Button zur nächsten Aufgabe nicht anzeigen soll.
     */
    public void versteckeNaechsteAufgabe() {
        this.btnNaechsteAufgabe.setVisible(false);
        this.update(this.getGraphics());
    }

    /**
     * Hiermit teilt der Controller der View mit, dass sie den Button zur vorherigen Aufgabe nicht anzeigen soll.
     */
    public void versteckeVorherigeAufgabe() {
        this.btnVorherigeAufgabe.setVisible(false);
        this.update(this.getGraphics());
    }

    /**
     * Methode, mit welcher der Controller dem View mitteilt, dass er im Kontext einer Bewertung angezeigt wird.
     */
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
        mainPanel.setLayout(new GridLayoutManager(9, 8, new Insets(10, 10, 15, 10), -1, -1));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnBeenden = new JButton();
        btnBeenden.setText("Beenden");
        mainPanel.add(btnBeenden, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnHinweis = new JButton();
        btnHinweis.setText("Lösungshinweis anzeigen");
        mainPanel.add(btnHinweis, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblUserloesung = new JLabel();
        lblUserloesung.setText("");
        mainPanel.add(lblUserloesung, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 200), null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        mainPanel.add(spacer3, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnVorherigeAufgabe = new JButton();
        btnVorherigeAufgabe.setText("Vorherige Aufgabe");
        mainPanel.add(btnVorherigeAufgabe, new GridConstraints(7, 5, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(120, -1), new Dimension(160, -1), 0, false));
        btnNaechsteAufgabe = new JButton();
        btnNaechsteAufgabe.setText("Nächste Aufgabe");
        mainPanel.add(btnNaechsteAufgabe, new GridConstraints(8, 5, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(160, -1), 0, false));
        final Spacer spacer4 = new Spacer();
        mainPanel.add(spacer4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        mainPanel.add(spacer5, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        lblAufgabenstellungsbild = new JLabel();
        lblAufgabenstellungsbild.setText("");
        mainPanel.add(lblAufgabenstellungsbild, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 200), null, null, 0, false));
        pnlPunktzahl = new JPanel();
        pnlPunktzahl.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(pnlPunktzahl, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, 1, 1, null, new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
        lblPunktzahlString = new JLabel();
        lblPunktzahlString.setText("Punktzahl:");
        pnlPunktzahl.add(lblPunktzahlString, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblDash = new JLabel();
        lblDash.setText(" / ");
        pnlPunktzahl.add(lblDash, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMaximalPunktzahl = new JLabel();
        lblMaximalPunktzahl.setText("10");
        pnlPunktzahl.add(lblMaximalPunktzahl, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtfUserPunktzahl = new JTextField();
        txtfUserPunktzahl.setEditable(false);
        txtfUserPunktzahl.setHorizontalAlignment(4);
        pnlPunktzahl.add(txtfUserPunktzahl, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(25, -1), new Dimension(25, -1), 0, false));
        final Spacer spacer6 = new Spacer();
        mainPanel.add(spacer6, new GridConstraints(5, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        pnlBearbeitungszeit = new JPanel();
        pnlBearbeitungszeit.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(pnlBearbeitungszeit, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblBearbeitungszeitString = new JLabel();
        lblBearbeitungszeitString.setText("Bearbeitungszeit:");
        pnlBearbeitungszeit.add(lblBearbeitungszeitString, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblBearbeitungszeit = new JLabel();
        lblBearbeitungszeit.setText("10 min");
        pnlBearbeitungszeit.add(lblBearbeitungszeit, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblAufgabeBildString = new JLabel();
        lblAufgabeBildString.setText("Aufgabenstellungsbild:");
        mainPanel.add(lblAufgabeBildString, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblUserBild = new JLabel();
        lblUserBild.setText("Userlösung:");
        mainPanel.add(lblUserBild, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMusterBild = new JLabel();
        lblMusterBild.setText("Musterlösung:");
        mainPanel.add(lblMusterBild, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMusterloesung = new JLabel();
        lblMusterloesung.setText("");
        mainPanel.add(lblMusterloesung, new GridConstraints(5, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 200), null, null, 0, false));
        btnBewertungSpeichern = new JButton();
        btnBewertungSpeichern.setText("Bewertung speichern");
        btnBewertungSpeichern.setVisible(false);
        mainPanel.add(btnBewertungSpeichern, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtaAufgabentext = new JTextArea();
        txtaAufgabentext.setEditable(false);
        txtaAufgabentext.setToolTipText("Aufgabentext");
        txtaAufgabentext.setVisible(true);
        mainPanel.add(txtaAufgabentext, new GridConstraints(1, 1, 3, 3, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(150, 120), new Dimension(150, 120), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
