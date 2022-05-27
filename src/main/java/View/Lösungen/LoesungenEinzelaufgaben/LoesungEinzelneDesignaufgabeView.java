package View.Lösungen.LoesungenEinzelaufgaben;

import View.aufgabenBearbeiten.einzelAufgaben.BearbeiteEinzelneAufgabeKatalogView;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import entity.aufgabe.Designaufgabe;
import entity.benutzer.Benutzer;
import entity.loesung.musterloesung.MusterloesungDesignaufgabe;
import entity.loesung.userloesung.UserloesungDesignaufgabe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoesungEinzelneDesignaufgabeView extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JFrame frame;
    private JTextField txtfAufgabentext;
    private JButton btnBeenden;
    private JButton btnHinweis;
    private JLabel lblPlaceholderUserloesung;
    private JLabel lblPlaceholderMusterloesung;
    private JLabel lblAufgabenstellungsbild;
    private JPanel panelBearbeitungszeit;
    private JLabel lblBearbeitungszeitString;
    private JLabel lblBearbeitungszeit;
    private JPanel panelPunktzahl;
    private JLabel lblPunktzahlString;
    private JLabel lblMaximalPunktzahl;
    private JLabel lblAufgabeBildString;
    private JLabel lblUserBild;
    private JLabel lblMusterBild;
    private final Designaufgabe aufgabe;
    private Benutzer benutzer;

    public LoesungEinzelneDesignaufgabeView(Designaufgabe aufgabe, UserloesungDesignaufgabe userloesungDesignaufgabe, Benutzer benutzer, JFrame frame) {
        this.frame = frame;
        this.benutzer = benutzer;
        this.aufgabe = aufgabe;
        this.setContentPane($$$getRootComponent$$$());
        this.setTitle(aufgabe.getName());
        btnBeenden.addActionListener(this);
        btnHinweis.addActionListener(this);

        txtfAufgabentext.setText(aufgabe.getTextbeschreibung());
        if (aufgabe.getAufgabenstellungsbild() != null) {
            lblAufgabeBildString.setVisible(true);
            lblAufgabenstellungsbild.setIcon(new ImageIcon(aufgabe.getAufgabenstellungsbild()));
        }
        lblMaximalPunktzahl.setText(aufgabe.getPunktewert() + "");
        lblBearbeitungszeit.setText(aufgabe.getBearbeitungszeit() + " min");
        MusterloesungDesignaufgabe musterloesungDesignaufgabe = (MusterloesungDesignaufgabe) aufgabe.getMusterloesung();
        lblPlaceholderMusterloesung.setIcon(new ImageIcon(musterloesungDesignaufgabe.getMusterloesung()));
        lblPlaceholderUserloesung.setIcon(new ImageIcon(userloesungDesignaufgabe.getUserloesung()));

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
            if (aufgabe.getMusterloesung().getLoesungshinweis() != null) {
                JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis());
            } else {
                JOptionPane.showMessageDialog(this, "Kein Lösungshinweis vorhanden.", "Lösungshinweis", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void beenden() {
        new BearbeiteEinzelneAufgabeKatalogView(frame, benutzer);
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
        mainPanel.setLayout(new GridLayoutManager(7, 8, new Insets(10, 10, 15, 10), -1, -1));
        btnBeenden = new JButton();
        btnBeenden.setText("Beenden");
        mainPanel.add(btnBeenden, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btnHinweis = new JButton();
        btnHinweis.setText("Lösungshinweis anzeigen");
        mainPanel.add(btnHinweis, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        lblPlaceholderUserloesung = new JLabel();
        lblPlaceholderUserloesung.setText("Placeholder Userlösung");
        mainPanel.add(lblPlaceholderUserloesung, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        mainPanel.add(spacer3, new GridConstraints(4, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        lblPlaceholderMusterloesung = new JLabel();
        lblPlaceholderMusterloesung.setText("Placeholder Musterloesung");
        mainPanel.add(lblPlaceholderMusterloesung, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        mainPanel.add(spacer4, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        mainPanel.add(spacer5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        mainPanel.add(spacer6, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txtfAufgabentext = new JTextField();
        txtfAufgabentext.setEditable(false);
        txtfAufgabentext.setToolTipText("Aufgabentext:");
        mainPanel.add(txtfAufgabentext, new GridConstraints(1, 1, 2, 4, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(150, 120), new Dimension(150, 120), null, 0, false));
        lblAufgabenstellungsbild = new JLabel();
        lblAufgabenstellungsbild.setText("");
        mainPanel.add(lblAufgabenstellungsbild, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 200), null, null, 0, false));
        panelBearbeitungszeit = new JPanel();
        panelBearbeitungszeit.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panelBearbeitungszeit, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblBearbeitungszeitString = new JLabel();
        lblBearbeitungszeitString.setText("Bearbeitungszeit:");
        panelBearbeitungszeit.add(lblBearbeitungszeitString, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblBearbeitungszeit = new JLabel();
        lblBearbeitungszeit.setText("10 min");
        panelBearbeitungszeit.add(lblBearbeitungszeit, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelPunktzahl = new JPanel();
        panelPunktzahl.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panelPunktzahl, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, 1, 1, null, new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
        lblPunktzahlString = new JLabel();
        lblPunktzahlString.setText("erreichbare Punktzahl:");
        panelPunktzahl.add(lblPunktzahlString, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMaximalPunktzahl = new JLabel();
        lblMaximalPunktzahl.setText("10");
        panelPunktzahl.add(lblMaximalPunktzahl, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblAufgabeBildString = new JLabel();
        lblAufgabeBildString.setText("Aufgabenstellungsbild:");
        mainPanel.add(lblAufgabeBildString, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblUserBild = new JLabel();
        lblUserBild.setText("Userlösung:");
        mainPanel.add(lblUserBild, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMusterBild = new JLabel();
        lblMusterBild.setText("Musterlösung:");
        mainPanel.add(lblMusterBild, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
