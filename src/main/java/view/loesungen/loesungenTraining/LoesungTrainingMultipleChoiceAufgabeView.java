package view.loesungen.loesungenTraining;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import controller.LoesungenTrainingController;
import entity.aufgabe.MultipleChoiceAufgabe;
import entity.loesung.musterloesung.MusterloesungMultipleChoiceAufgabe;
import entity.loesung.userloesung.UserloesungMultipleChoiceAufgabe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ansicht zur Darstellung einer MultipleChoice-Aufgabe und dazugehöriger Lösungen im Kontext eines Trainings.
 *
 * @author Timo Joswig
 * @version 29.05.2022
 */
public class LoesungTrainingMultipleChoiceAufgabeView extends JFrame implements ActionListener {
    private final LoesungenTrainingController LoesungenTrainingController;
    private final MultipleChoiceAufgabe aufgabe;
    private JPanel mainPanel;
    private JTextArea txtaAufgabentext;
    private JButton btnBeenden;
    private JButton btnHinweis;
    private JButton btnVorherigeAufgabe;
    private JButton btnNaechsteAufgabe;
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
    private JLabel lblAufgabenstellungsbild;
    private JPanel panelBearbeitungszeit;
    private JLabel lblBearbeitungszeitString;
    private JLabel lblBearbeitungszeit;
    private JPanel panelPunktzahl;
    private JLabel lblPunktzahlString;
    private JLabel lblMaximalPunktzahl;
    private JLabel lblAufgabeBildString;
    private JLabel lblUserloesungString;
    private JLabel lblMusterloesungString;

    /**
     * Konstruktor für die benötigte View.
     *
     * @param aufgabe Die darzustellende Aufgabe
     * @param LoesungenTrainingController der Trainingscontroller, der diesen Konstruktor aufgerufen hat
     */
    public LoesungTrainingMultipleChoiceAufgabeView(MultipleChoiceAufgabe aufgabe, LoesungenTrainingController LoesungenTrainingController) {
        this.LoesungenTrainingController = LoesungenTrainingController;
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
        MusterloesungMultipleChoiceAufgabe musterloesungMultipleChoiceAufgabe = (MusterloesungMultipleChoiceAufgabe) aufgabe.getMusterloesung();
        int musterloesung = musterloesungMultipleChoiceAufgabe.getMusterloesung();
        UserloesungMultipleChoiceAufgabe userloesungMultipleChoiceAufgabe = (UserloesungMultipleChoiceAufgabe) LoesungenTrainingController.getUserloesung(aufgabe);
        int userloesung = userloesungMultipleChoiceAufgabe.getUserloesung();
        if (aufgabe.getAntwortmoeglichkeiten().size() == 4) {
            btnMusterloesung1.setSelected(musterloesung == 1);
            btnMusterloesung2.setSelected(musterloesung == 2);
            btnMusterloesung3.setSelected(musterloesung == 3);
            btnMusterloesung4.setSelected(musterloesung == 4);
            btnUserloesung1.setSelected(userloesung == 1);
            btnUserloesung2.setSelected(userloesung == 2);
            btnUserloesung3.setSelected(userloesung == 3);
            btnUserloesung4.setSelected(userloesung == 4);
        } else if (aufgabe.getAntwortmoeglichkeiten().size() == 3) {
            btnMusterloesung1.setSelected(musterloesung == 1);
            btnMusterloesung2.setSelected(musterloesung == 2);
            btnMusterloesung3.setSelected(musterloesung == 3);
            btnMusterloesung4.setVisible(false);
            btnUserloesung1.setSelected(userloesung == 1);
            btnUserloesung2.setSelected(userloesung == 2);
            btnUserloesung3.setSelected(userloesung == 3);
            btnUserloesung4.setVisible(false);
        } else if (aufgabe.getAntwortmoeglichkeiten().size() == 2) {
            btnMusterloesung1.setSelected(musterloesung == 1);
            btnMusterloesung2.setSelected(musterloesung == 2);
            btnMusterloesung3.setVisible(false);
            btnMusterloesung4.setVisible(false);
            btnUserloesung1.setSelected(userloesung == 1);
            btnUserloesung2.setSelected(userloesung == 2);
            btnUserloesung3.setVisible(false);
            btnUserloesung4.setVisible(false);
        }

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
        LoesungenTrainingController.beendeLoesungTraining();
    }

    /**
     * Methode die aufgerufen wird, wenn dem Controller mitgeteilt werden soll, dass die nächste Aufgabe angezeigt werden soll.
     */
    private void naechsteAufgabe() {
        LoesungenTrainingController.naechsteAufgabe();
    }

    /**
     * Methode die aufgerufen wird, wenn dem Controller mitgeteilt werden soll, dass die vorherige Aufgabe angezeigt werden soll.
     */
    private void vorherigeAufgabe() {
        LoesungenTrainingController.vorherigeAufgabe();
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
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(8, 7, new Insets(10, 10, 15, 10), -1, -1));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        mainPanel.add(spacer3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        mainPanel.add(spacer4, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        mainPanel.add(spacer5, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        mainPanel.add(spacer6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btnBeenden = new JButton();
        btnBeenden.setText("Beenden");
        mainPanel.add(btnBeenden, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnHinweis = new JButton();
        btnHinweis.setText("Lösungshinweis anzeigen");
        mainPanel.add(btnHinweis, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelUserChoices = new JPanel();
        panelUserChoices.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panelUserChoices, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnUserloesung1 = new JRadioButton();
        btnUserloesung1.setEnabled(false);
        btnUserloesung1.setText("Userlösung1");
        panelUserChoices.add(btnUserloesung1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnUserloesung2 = new JRadioButton();
        btnUserloesung2.setEnabled(false);
        btnUserloesung2.setText("Userlösung2");
        panelUserChoices.add(btnUserloesung2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnUserloesung3 = new JRadioButton();
        btnUserloesung3.setEnabled(false);
        btnUserloesung3.setText("Userlösung3");
        panelUserChoices.add(btnUserloesung3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnUserloesung4 = new JRadioButton();
        btnUserloesung4.setEnabled(false);
        btnUserloesung4.setText("Userlösung4");
        panelUserChoices.add(btnUserloesung4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnVorherigeAufgabe = new JButton();
        btnVorherigeAufgabe.setText("Vorherige Aufgabe");
        mainPanel.add(btnVorherigeAufgabe, new GridConstraints(6, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(160, -1), new Dimension(160, -1), 0, false));
        btnNaechsteAufgabe = new JButton();
        btnNaechsteAufgabe.setText("Nächste Aufgabe");
        mainPanel.add(btnNaechsteAufgabe, new GridConstraints(7, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(160, -1), 0, false));
        panelMusterChoices = new JPanel();
        panelMusterChoices.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panelMusterChoices, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnMusterloesung1 = new JRadioButton();
        btnMusterloesung1.setEnabled(false);
        btnMusterloesung1.setText("Musterlösung1");
        panelMusterChoices.add(btnMusterloesung1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnMusterloesung2 = new JRadioButton();
        btnMusterloesung2.setEnabled(false);
        btnMusterloesung2.setText("Musterlösung2");
        panelMusterChoices.add(btnMusterloesung2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnMusterloesung3 = new JRadioButton();
        btnMusterloesung3.setEnabled(false);
        btnMusterloesung3.setText("Musterlösung3");
        panelMusterChoices.add(btnMusterloesung3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnMusterloesung4 = new JRadioButton();
        btnMusterloesung4.setEnabled(false);
        btnMusterloesung4.setText("Musterlösung4");
        panelMusterChoices.add(btnMusterloesung4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtaAufgabentext = new JTextArea();
        txtaAufgabentext.setEditable(false);
        txtaAufgabentext.setToolTipText("Aufgabentext:");
        mainPanel.add(txtaAufgabentext, new GridConstraints(1, 1, 2, 3, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(150, 120), new Dimension(150, 120), null, 0, false));
        lblAufgabenstellungsbild = new JLabel();
        lblAufgabenstellungsbild.setText("");
        mainPanel.add(lblAufgabenstellungsbild, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 200), null, null, 0, false));
        panelPunktzahl = new JPanel();
        panelPunktzahl.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panelPunktzahl, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, 1, 1, null, new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
        lblPunktzahlString = new JLabel();
        lblPunktzahlString.setText("erreichbare Punktzahl:");
        panelPunktzahl.add(lblPunktzahlString, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMaximalPunktzahl = new JLabel();
        lblMaximalPunktzahl.setText("10");
        panelPunktzahl.add(lblMaximalPunktzahl, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelBearbeitungszeit = new JPanel();
        panelBearbeitungszeit.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panelBearbeitungszeit, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblBearbeitungszeitString = new JLabel();
        lblBearbeitungszeitString.setText("Bearbeitungszeit:");
        panelBearbeitungszeit.add(lblBearbeitungszeitString, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblBearbeitungszeit = new JLabel();
        lblBearbeitungszeit.setText("10 min");
        panelBearbeitungszeit.add(lblBearbeitungszeit, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblAufgabeBildString = new JLabel();
        lblAufgabeBildString.setText("Aufgabenstellungsbild:");
        mainPanel.add(lblAufgabeBildString, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblUserloesungString = new JLabel();
        lblUserloesungString.setText("Userlösung:");
        mainPanel.add(lblUserloesungString, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMusterloesungString = new JLabel();
        lblMusterloesungString.setText("Musterlösung:");
        mainPanel.add(lblMusterloesungString, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(btnUserloesung1);
        buttonGroup.add(btnUserloesung2);
        buttonGroup.add(btnUserloesung3);
        buttonGroup.add(btnUserloesung4);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(btnMusterloesung1);
        buttonGroup.add(btnMusterloesung2);
        buttonGroup.add(btnMusterloesung3);
        buttonGroup.add(btnMusterloesung4);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
