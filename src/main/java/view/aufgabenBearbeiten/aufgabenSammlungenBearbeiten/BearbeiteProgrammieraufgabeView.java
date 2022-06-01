package view.aufgabenBearbeiten.aufgabenSammlungenBearbeiten;

import controller.BearbeitungsController;
import controller.TestatController;
import controller.TrainingController;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import entity.aufgabe.Programmieraufgabe;
import entity.loesung.userloesung.Userloesung;
import entity.loesung.userloesung.UserloesungProgrammieraufgabe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Kristin Kubisch
 * @version: 31.05.2022
 * Fenster um eine Programmieraufgabe zu beantworten
 */
public class BearbeiteProgrammieraufgabeView extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JLabel lblBild;
    private JLabel lblBearbeitungszeitWert;
    private JLabel lblBearbeitungszeit;
    private JLabel lblPunktzahl;
    private JLabel lblPunktzahlWert;
    private JLabel lblAufgabentyp;
    private JLabel lblAufgabentypWert;
    private JButton btnAbbrechen;
    private JButton btnLoesungshinweis;
    private JButton btnVorherigeAufgabe;
    private JButton btnNaechsteAufgabe;
    private JTextArea txtUsereingabe;
    private JTextArea txtaAufgabentext;

    private boolean hinweisVerwendet;
    private String eingabe;


    private TestatController testatController;
    private TrainingController trainingController;
    private Programmieraufgabe aufgabe;
    private UserloesungProgrammieraufgabe userloesung;

    /**
     *
     * @param aufgabe Controller der von der Aufgabensammlung mitgegeben wird
     * @param aufgabe Aufgabe die bearbeitet wird
     */
    public BearbeiteProgrammieraufgabeView(Programmieraufgabe aufgabe, BearbeitungsController controller) {

        setContentPane($$$getRootComponent$$$());
        this.hinweisVerwendet = false;
        this.aufgabe = aufgabe;

        if (controller.getClass() == TestatController.class) {
            this.testatController = (TestatController) controller;
        } else if (controller.getClass() == TrainingController.class) {
            this.trainingController = (TrainingController) controller;
        }

        setTitle(aufgabe.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtaAufgabentext.setText(aufgabe.getTextbeschreibung());
        txtaAufgabentext.setLineWrap(true);
        if (aufgabe.getAufgabenstellungsbild() != null) {
            lblBild.setIcon(new ImageIcon(aufgabe.getAufgabenstellungsbild()));
        }
        lblBearbeitungszeitWert.setText(aufgabe.getBearbeitungszeit() + " min");
        lblPunktzahlWert.setText(aufgabe.getPunktewert() + ".P");
        lblAufgabentypWert.setText(aufgabe.getAufgabentyp().getCode());


        btnAbbrechen.addActionListener(this);
        btnLoesungshinweis.addActionListener(this);
        btnVorherigeAufgabe.addActionListener(this);
        btnNaechsteAufgabe.addActionListener(this);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);

    }

    /**
     * Funktionslogik hinter den Buttons
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAbbrechen) {
            JOptionPane.showMessageDialog(this, "Aufgaben werden nicht gespeichert");
            if (testatController != null) {
                testatController.setNewTestatKatalog();
            } else {
                trainingController.setNewTrainingKatalog();
            }
            this.dispose();

        } else if (e.getSource() == this.btnLoesungshinweis) {
            if (aufgabe.getMusterloesung().getLoesungshinweis() != null) {
                JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis());
                hinweisVerwendet = true;
            } else {
                JOptionPane.showMessageDialog(this, "Kein Lösungshinweis vorhanden.", "Lösungshinweis", JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == this.btnVorherigeAufgabe) {

            userEingabenSpeichern();
            if (testatController != null) {
                testatController.zurueck();
            } else {
                trainingController.zurueck();
            }


        } else if (e.getSource() == this.btnNaechsteAufgabe) {
            String buttonWechsel = btnNaechsteAufgabe.getText();
            userEingabenSpeichern();

            if (testatController != null) {
                if (buttonWechsel.equals("Testat beenden")) {
                    JOptionPane.showMessageDialog(this, "Testat ist abgeschickt");
                    testatController.persistTestat();
                    testatController.setNewTestatKatalog();
                    this.dispose();
                } else {
                    testatController.weiter();
                }

            } else if (trainingController != null) {
                if (buttonWechsel.equals("Training beenden")) {
                    JOptionPane.showMessageDialog(this, "Training ist abgeschickt");
                    trainingController.persistTraining();
                    trainingController.zeigeTrainingLoesungView();
                    this.dispose();
                } else {
                    trainingController.weiter();

                }
            }
        }
    }

    /**
     * verändert "Nächste" Button zu "Testat Beenden" Button
     */
    public void setNaechsteZuSpeichern() {
        if (trainingController != null) {
            btnNaechsteAufgabe.setText("Training beenden");
        } else {
            btnNaechsteAufgabe.setText("Testat beenden");
        }
    }

    /**
     * Speichert Usereingaben in Userlösungsliste
     */
    public void userEingabenSpeichern() {
        eingabe = txtUsereingabe.getText();

        if (testatController != null) {
            userloesung = new UserloesungProgrammieraufgabe(aufgabe, hinweisVerwendet, eingabe, testatController.getAktuellerBenutzer(), testatController.getTestat());
            testatController.addUserloesung(userloesung);
        } else {
            userloesung = new UserloesungProgrammieraufgabe(aufgabe, hinweisVerwendet, eingabe, trainingController.getAktuellerBenutzer(), trainingController.getTraining());
            trainingController.addUserloesung(userloesung);
        }
    }

    /**
     * Setzt leere Usereingabe
     */
    public void setUserloesungNull() {
        eingabe = new String();
    }

    /**
     * Setzt eingegebene Userlösung ins Fenster
     *
     * @param userloesung eingetragende Antworten
     */
    public void setUserloesung(Userloesung userloesung) {
        eingabe = ((UserloesungProgrammieraufgabe) userloesung).getUserloesung();
        this.txtUsereingabe.setText(eingabe);
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
        mainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 8, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblBild = new JLabel();
        lblBild.setText("");
        panel2.add(lblBild, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(250, 150), null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(1, 6, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel4.add(spacer1, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel5, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblBearbeitungszeitWert = new JLabel();
        lblBearbeitungszeitWert.setText("10");
        panel5.add(lblBearbeitungszeitWert, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(100, -1), null, 0, false));
        lblBearbeitungszeit = new JLabel();
        lblBearbeitungszeit.setText("Bearbeitungszeit:");
        panel5.add(lblBearbeitungszeit, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 150), null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel6, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblPunktzahl = new JLabel();
        lblPunktzahl.setText("Punktzahl:");
        panel6.add(lblPunktzahl, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 150), null, null, 0, false));
        lblPunktzahlWert = new JLabel();
        lblPunktzahlWert.setText("10");
        panel6.add(lblPunktzahlWert, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(100, 16), null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel7, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblAufgabentyp = new JLabel();
        lblAufgabentyp.setText("Aufgabentyp:");
        panel7.add(lblAufgabentyp, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 150), null, null, 0, false));
        lblAufgabentypWert = new JLabel();
        lblAufgabentypWert.setText("Einfachantwort");
        panel7.add(lblAufgabentypWert, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(100, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel8, new GridConstraints(3, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnAbbrechen = new JButton();
        btnAbbrechen.setText("Abbrechen");
        panel8.add(btnAbbrechen, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnLoesungshinweis = new JButton();
        btnLoesungshinweis.setText("Loesungshinweis");
        panel8.add(btnLoesungshinweis, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnVorherigeAufgabe = new JButton();
        btnVorherigeAufgabe.setText("Voherige Aufgabe");
        panel8.add(btnVorherigeAufgabe, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnNaechsteAufgabe = new JButton();
        btnNaechsteAufgabe.setText("Nächste Aufgabe");
        panel8.add(btnNaechsteAufgabe, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel2.add(spacer3, new GridConstraints(1, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txtUsereingabe = new JTextArea();
        txtUsereingabe.setEditable(true);
        txtUsereingabe.setText("");
        panel2.add(txtUsereingabe, new GridConstraints(1, 2, 2, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(250, 150), new Dimension(250, 150), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel2.add(scrollPane1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(250, 150), new Dimension(250, 150), null, 0, false));
        txtaAufgabentext = new JTextArea();
        txtaAufgabentext.setEditable(false);
        scrollPane1.setViewportView(txtaAufgabentext);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
