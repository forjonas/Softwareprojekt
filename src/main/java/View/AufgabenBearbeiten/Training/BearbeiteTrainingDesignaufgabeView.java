package View.AufgabenBearbeiten.Training;

import app.TrainingController;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import entity.aufgabe.Designaufgabe;
import entity.loesung.userloesung.Userloesung;
import entity.loesung.userloesung.UserloesungDesignaufgabe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static persistence.DatabaseService.convertFileToByteArray;
import static persistence.DatabaseService.dateiOeffnen;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version2: 13.05.22
 * @version3: 16.05.22
 * @version5: 20.05.22 Beenden Button versteckt, Views angepasst
 * @version6: 23.05.22 Kommentare + weitere Anpassungen
 */
public class BearbeiteTrainingDesignaufgabeView extends JFrame implements ActionListener {
    private JLabel lblAufgabenText;
    private JLabel lblBild;
    private JLabel lblBearbeitungszeitWert;
    private JLabel lblBearbeitungszeit;
    private JLabel lblPunktzahl;
    private JLabel lblPunktzahlWert;
    private JLabel lblAufgabentyp;
    private JLabel lblAufgabentypWert;
    private JButton btnAbbrechenTraining;
    private JButton btnLoesungshinweisTraining;
    private JButton btnVoherigeAufgabeTraining;
    private JButton btnNaechsteAufgabeTraining;
    private JButton btnUpload;
    private JPanel mainPanel;

    private File fileBild = null;// = new File("Test");
    byte[] eingabe;
    private boolean hinweisVerwendet;

    private TrainingController trainingController;
    private Designaufgabe aufgabe;
    private UserloesungDesignaufgabe userloesung;

    /**
     * Konstruktor für Klasse BearbeiteTrainingDesignaufgabeView
     *
     * @param trainingController
     * @param aufgabe
     */
    public BearbeiteTrainingDesignaufgabeView(TrainingController trainingController, Designaufgabe aufgabe) {

        this.setContentPane($$$getRootComponent$$$());
        this.hinweisVerwendet = false;
        this.aufgabe = aufgabe;
        this.trainingController = trainingController;

        setTitle(aufgabe.getName()); //Name der Aufgabe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Setzen der Daten
        lblAufgabenText.setText(aufgabe.getTextbeschreibung());
        if (aufgabe.getAufgabenstellungsbild() != null) {
            lblBild.setIcon(new ImageIcon(aufgabe.getAufgabenstellungsbild()));//verwendet Objekt vom Typ ImageIcon, welches selbst wiederum eine File verwendet
        }
        lblBearbeitungszeitWert.setText(aufgabe.getBearbeitungszeit() + " min");
        lblPunktzahlWert.setText(aufgabe.getPunktewert() + ".P");
        lblAufgabentypWert.setText(aufgabe.getAufgabentyp().getCode());

        btnAbbrechenTraining.addActionListener(this);
        btnLoesungshinweisTraining.addActionListener(this);
        btnVoherigeAufgabeTraining.addActionListener(this);
        btnNaechsteAufgabeTraining.addActionListener(this);
        btnUpload.addActionListener(this);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);
    }
    /**
     * Funktionslogik hinter den Buttons
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAbbrechenTraining) {
            JOptionPane.showMessageDialog(this, "Aufgaben werden nicht gespeichert");
            this.dispose();
            //new ControllerLoesungenTraining(trainingController.getTraining(),trainingController.getAktuellerBenutzer(), hauptmenueFrame);
            //trainingController.setNewTrainingKatalog();
        } else if (e.getSource() == this.btnLoesungshinweisTraining) {
            if (aufgabe.getMusterloesung().getLoesungshinweis() != null) {
                JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen//
                hinweisVerwendet = true;
            } else {
                JOptionPane.showMessageDialog(this, "Kein Lösungshinweis vorhanden.", "Lösungshinweis", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == this.btnVoherigeAufgabeTraining) {

            userEingabenSpeichern();
            trainingController.zurueckTraining();
            //if (testatController.isIndexNotFirst())

        } else if (e.getSource() == this.btnNaechsteAufgabeTraining) {
            String buttonWechsel = btnNaechsteAufgabeTraining.getText();
            userEingabenSpeichern();

            if (buttonWechsel.equals("Training beenden")) {
               // trainingController.persistTraining();
                this.dispose();

            } else {
                trainingController.weiter();
            }
        } else if (e.getSource() == this.btnUpload) {

            fileBild = dateiOeffnen(this);
            if (fileBild == null) {
                btnUpload.setText("kein Bild");
            } else {
                String text = fileBild.getName();
                btnUpload.setText(text);
                this.update(this.getGraphics());
            }
        }
    }
    /**
     * verändert "Nächste" Button zu "Training Beenden" Button
     */
    public void setNaechsteZuSpeichern() {
        btnNaechsteAufgabeTraining.setText("Training beenden");
    }
    /**
     * Speichert Usereingaben in Userlösungsliste
     */
    public void userEingabenSpeichern() {
        if (fileBild != null) {
            eingabe = convertFileToByteArray(fileBild, this);
            userloesung = new UserloesungDesignaufgabe(aufgabe, hinweisVerwendet, eingabe, trainingController.getAktuellerBenutzer(), trainingController.getTraining());
            trainingController.addUserloesung(userloesung);
        } else if (fileBild == null) {
            userloesung = new UserloesungDesignaufgabe(aufgabe, hinweisVerwendet, eingabe, trainingController.getAktuellerBenutzer(), trainingController.getTraining());
            trainingController.addUserloesung(userloesung);
        }
    }
    /**
     * Setzt leere Usereingabe
     */
    public void setUserloesungNull() {
        eingabe = new byte[0];
        //fileBild = new File("Keine Lösung");
    }
    /**
     * Setzt eingegebene Userlösung
     *
     * @param userloesung
     */
    public void setUserloesung(Userloesung userloesung) {
        eingabe = ((UserloesungDesignaufgabe) userloesung).getUserloesung();
        if (eingabe.length <= 0) {
            String name = "Bild fehlt";   //String.valueOf(eingabe.getClass());
            btnUpload.setText(name);
            this.update(this.getGraphics());
        } else {
            String name = "Bild vorhanden";
            btnUpload.setText(name);
            this.update(this.getGraphics());
        }
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
        panel1.setLayout(new GridLayoutManager(5, 8, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblBild = new JLabel();
        lblBild.setText("");
        panel1.add(lblBild, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(250, 150), new Dimension(250, 150), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(1, 6, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel3.add(spacer1, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblBearbeitungszeitWert = new JLabel();
        lblBearbeitungszeitWert.setText("10");
        panel4.add(lblBearbeitungszeitWert, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(100, -1), null, 0, false));
        lblBearbeitungszeit = new JLabel();
        lblBearbeitungszeit.setText("Bearbeitungszeit:");
        panel4.add(lblBearbeitungszeit, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 150), null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel5, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblPunktzahl = new JLabel();
        lblPunktzahl.setText("Punktzahl:");
        panel5.add(lblPunktzahl, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 150), null, null, 0, false));
        lblPunktzahlWert = new JLabel();
        lblPunktzahlWert.setText("10");
        panel5.add(lblPunktzahlWert, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(100, 16), null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel6, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblAufgabentyp = new JLabel();
        lblAufgabentyp.setText("Aufgabentyp:");
        panel6.add(lblAufgabentyp, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 150), null, null, 0, false));
        lblAufgabentypWert = new JLabel();
        lblAufgabentypWert.setText("Einfachantwort");
        panel6.add(lblAufgabentypWert, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(100, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel7, new GridConstraints(3, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnAbbrechenTraining = new JButton();
        btnAbbrechenTraining.setText("Abbrechen");
        panel7.add(btnAbbrechenTraining, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnLoesungshinweisTraining = new JButton();
        btnLoesungshinweisTraining.setText("Loesungshinweis");
        panel7.add(btnLoesungshinweisTraining, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnVoherigeAufgabeTraining = new JButton();
        btnVoherigeAufgabeTraining.setText("Voherige Aufgabe");
        panel7.add(btnVoherigeAufgabeTraining, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnNaechsteAufgabeTraining = new JButton();
        btnNaechsteAufgabeTraining.setText("Nächste Aufgabe");
        panel7.add(btnNaechsteAufgabeTraining, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(1, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel1.add(spacer5, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel8, new GridConstraints(1, 2, 2, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnUpload = new JButton();
        btnUpload.setText("Bild hochladen");
        panel8.add(btnUpload, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(250, 150), new Dimension(250, 150), null, 0, false));
        lblAufgabenText = new JLabel();
        lblAufgabenText.setText("Aufgabentext");
        scrollPane1.setViewportView(lblAufgabenText);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}