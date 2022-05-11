package View.Lösungen.LoesungenTraining;

import View.LoesungsHinweisView;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import entity.EinfachantwortAufgabe;
import entity.Training;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoesungTrainingEinfachantwortaufgabeView extends JFrame implements ActionListener {
    private JTextField txtfAufgabentext;
    private JButton btnBeenden;
    private JButton btnHinweis;
    private JTextField txtfUserLoesung;
    private JTextField txtfMusterloesung;
    private JButton btnNaechsteAufgabe;
    private JButton btnVorherigeAufgabe;
    private JPanel panelMain;

    public LoesungTrainingEinfachantwortaufgabeView(EinfachantwortAufgabe aufgabe) {
        this.setContentPane($$$getRootComponent$$$());
        btnBeenden.addActionListener(this);
        btnHinweis.addActionListener(this);
        btnVorherigeAufgabe.addActionListener(this);
        btnNaechsteAufgabe.addActionListener(this);
        txtfAufgabentext.setText(aufgabe.getTextbeschreibung());
        txtfMusterloesung.setText(aufgabe.getMusterloesung());
        txtfUserLoesung.setText(aufgabe.getUserloesung());
        this.pack();
        this.setVisible(true);
    }

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
        panelMain.setLayout(new GridLayoutManager(5, 7, new Insets(10, 10, 15, 10), -1, -1));
        txtfAufgabentext = new JTextField();
        txtfAufgabentext.setEditable(false);
        txtfAufgabentext.setToolTipText("Aufgabentext:");
        panelMain.add(txtfAufgabentext, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(150, 150), new Dimension(200, 250), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelMain.add(spacer2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btnBeenden = new JButton();
        btnBeenden.setText("Beenden");
        panelMain.add(btnBeenden, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelMain.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panelMain.add(spacer4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panelMain.add(spacer5, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnHinweis = new JButton();
        btnHinweis.setText("Lösungshinweis anzeigen");
        panelMain.add(btnHinweis, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtfUserLoesung = new JTextField();
        panelMain.add(txtfUserLoesung, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 150), new Dimension(200, 350), null, 0, false));
        txtfMusterloesung = new JTextField();
        panelMain.add(txtfMusterloesung, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 150), new Dimension(200, 350), null, 0, false));
        final Spacer spacer6 = new Spacer();
        panelMain.add(spacer6, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnNaechsteAufgabe = new JButton();
        btnNaechsteAufgabe.setText("Nächste Aufgabe");
        panelMain.add(btnNaechsteAufgabe, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(160, -1), 0, false));
        btnVorherigeAufgabe = new JButton();
        btnVorherigeAufgabe.setText("Vorherige Aufgabe");
        panelMain.add(btnVorherigeAufgabe, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(160, -1), new Dimension(160, -1), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
