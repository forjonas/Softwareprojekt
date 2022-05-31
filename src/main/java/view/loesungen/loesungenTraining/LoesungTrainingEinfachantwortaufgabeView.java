package view.loesungen.loesungenTraining;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.loesung.musterloesung.MusterloesungEinfachantwort;
import entity.loesung.userloesung.UserloesungEinfachantwort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ansicht zur Darstellung einer Einfachantwort-Aufgabe und dazugehöriger Lösungen im Kontext eines Trainings.
 *
 * @author Timo Joswig
 * @version 29.05.2022
 */
public class LoesungTrainingEinfachantwortaufgabeView extends JFrame implements ActionListener {
    private final controller.controllerLoesungenTraining controllerLoesungenTraining;
    private final EinfachantwortAufgabe aufgabe;
    private JPanel panelMain;
    private JButton btnBeenden;
    private JButton btnHinweis;
    private JButton btnNaechsteAufgabe;
    private JButton btnVorherigeAufgabe;
    private JTextArea txtaAufgabentext;
    private JTextArea txtaUserLoesung;
    private JTextArea txtaMusterloesung;
    private JLabel lblAufgabenstellungsbild;
    private JPanel pnlPunktzahl;
    private JLabel lblPunktzahlString;
    private JLabel lblMaximalPunktzahl;
    private JLabel lblBearbeitungszeitString;
    private JLabel lblBearbeitungszeit;
    private JPanel pnlBearbeitungszeit;
    private JLabel lblMusterloesungString;
    private JLabel lblUserloesungString;
    private JLabel lblAufgabeBildString;

    /**
     * Konstruktor für die benötigte View.
     *
     * @param aufgabe Die darzustellende Aufgabe
     * @param controllerLoesungenTraining der Trainingscontroller, der diesen Konstruktor aufgerufen hat
     */
    public LoesungTrainingEinfachantwortaufgabeView(EinfachantwortAufgabe aufgabe, controller.controllerLoesungenTraining controllerLoesungenTraining) {
        this.controllerLoesungenTraining = controllerLoesungenTraining;
        this.aufgabe = aufgabe;
        this.setContentPane($$$getRootComponent$$$());
        this.setTitle(aufgabe.getName());
        btnBeenden.addActionListener(this);
        btnHinweis.addActionListener(this);
        btnVorherigeAufgabe.addActionListener(this);
        btnNaechsteAufgabe.addActionListener(this);
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
        MusterloesungEinfachantwort musterloesungEinfachantwort = (MusterloesungEinfachantwort) aufgabe.getMusterloesung();
        txtaMusterloesung.setText(musterloesungEinfachantwort.getMusterloesung());
        txtaMusterloesung.setLineWrap(true);
        UserloesungEinfachantwort userloesungEinfachantwort = (UserloesungEinfachantwort) controllerLoesungenTraining.getUserloesung(aufgabe);
        txtaUserLoesung.setText(userloesungEinfachantwort.getUserloesung());
        txtaUserLoesung.setLineWrap(true);

        this.pack();
        this.setMinimumSize(new Dimension(800 + this.lblAufgabenstellungsbild.getWidth(), 450 + this.lblAufgabenstellungsbild.getHeight()));
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeenden) {
            this.dispose();
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
        }
    }

    /**
     * Methode die aufgerufen wird, wenn dem Controller mitgeteilt werden soll, dass der User die Einsicht beenden möchte.
     */
    private void beenden() {
        controllerLoesungenTraining.beendeLoesungTraining();
    }

    /**
     * Methode die aufgerufen wird, wenn dem Controller mitgeteilt werden soll, dass die nächste Aufgabe angezeigt werden soll.
     */
    private void naechsteAufgabe() {
        controllerLoesungenTraining.naechsteAufgabe();
    }

    /**
     * Methode die aufgerufen wird, wenn dem Controller mitgeteilt werden soll, dass die vorherige Aufgabe angezeigt werden soll.
     */
    private void vorherigeAufgabe() {
        controllerLoesungenTraining.vorherigeAufgabe();
    }

    /**
     * Hiermit teilt der Controller der View mit, dass sie den Button zur vorherigen Aufgabe nicht anzeigen soll.
     */
    public void versteckeNaechsteAufgabe() {
        this.btnNaechsteAufgabe.setVisible(false);
        this.update(this.getGraphics());
    }

    /**
     * Methode, mit welcher der Controller dem View mitteilt, dass er im Kontext einer Bewertung angezeigt wird.
     */
    public void versteckeVorherigeAufgabe() {
        this.btnVorherigeAufgabe.setVisible(false);
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
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(8, 7, new Insets(10, 10, 15, 10), -1, -1));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnBeenden = new JButton();
        btnBeenden.setText("Beenden");
        panelMain.add(btnBeenden, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelMain.add(spacer2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelMain.add(spacer3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panelMain.add(spacer4, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnHinweis = new JButton();
        btnHinweis.setText("Lösungshinweis anzeigen");
        panelMain.add(btnHinweis, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtaUserLoesung = new JTextArea();
        txtaUserLoesung.setEditable(false);
        panelMain.add(txtaUserLoesung, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 150), new Dimension(200, 250), null, 0, false));
        txtaMusterloesung = new JTextArea();
        txtaMusterloesung.setEditable(false);
        panelMain.add(txtaMusterloesung, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 150), new Dimension(200, 250), null, 0, false));
        btnNaechsteAufgabe = new JButton();
        btnNaechsteAufgabe.setText("Nächste Aufgabe");
        panelMain.add(btnNaechsteAufgabe, new GridConstraints(7, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(160, -1), 0, false));
        btnVorherigeAufgabe = new JButton();
        btnVorherigeAufgabe.setText("Vorherige Aufgabe");
        panelMain.add(btnVorherigeAufgabe, new GridConstraints(6, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(160, -1), new Dimension(160, -1), 0, false));
        txtaAufgabentext = new JTextArea();
        txtaAufgabentext.setEditable(false);
        txtaAufgabentext.setToolTipText("Aufgabentext:");
        panelMain.add(txtaAufgabentext, new GridConstraints(1, 1, 2, 3, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(150, 120), new Dimension(150, 120), null, 0, false));
        lblAufgabenstellungsbild = new JLabel();
        lblAufgabenstellungsbild.setText("");
        panelMain.add(lblAufgabenstellungsbild, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 200), null, null, 0, false));
        pnlPunktzahl = new JPanel();
        pnlPunktzahl.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(pnlPunktzahl, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, 1, 1, null, new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
        lblPunktzahlString = new JLabel();
        lblPunktzahlString.setText("erreichbare Punktzahl:");
        pnlPunktzahl.add(lblPunktzahlString, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMaximalPunktzahl = new JLabel();
        lblMaximalPunktzahl.setText("10");
        pnlPunktzahl.add(lblMaximalPunktzahl, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panelMain.add(spacer5, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panelMain.add(spacer6, new GridConstraints(1, 6, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        pnlBearbeitungszeit = new JPanel();
        pnlBearbeitungszeit.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(pnlBearbeitungszeit, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblBearbeitungszeitString = new JLabel();
        lblBearbeitungszeitString.setText("Bearbeitungszeit:");
        pnlBearbeitungszeit.add(lblBearbeitungszeitString, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblBearbeitungszeit = new JLabel();
        lblBearbeitungszeit.setText("10 min");
        pnlBearbeitungszeit.add(lblBearbeitungszeit, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMusterloesungString = new JLabel();
        lblMusterloesungString.setText("Musterlösung:");
        panelMain.add(lblMusterloesungString, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblUserloesungString = new JLabel();
        lblUserloesungString.setText("Userlösung:");
        panelMain.add(lblUserloesungString, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblAufgabeBildString = new JLabel();
        lblAufgabeBildString.setText("Aufgabenstellungsbild:");
        panelMain.add(lblAufgabeBildString, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
