package View.AufgabenBearbeiten.Testat;

import app.TestatController;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import entity.aufgabe.Designaufgabe;
import entity.loesung.userloesung.Userloesung;
import entity.loesung.userloesung.UserloesungDesignaufgabe;
import entity.loesung.userloesung.UserloesungEinfachantwort;

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
 * @version4: 18.05.22
 * @version5: 20.05.22 Beenden Button versteckt
 */
public class BearbeiteTestatDesignaufgabeView2 extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JLabel lblAufgabenText;
    private JLabel lblBild;
    private JLabel lblBearbeitungszeitWert;
    private JLabel lblBearbeitungszeit;
    private JLabel lblPunktzahl;
    private JLabel lblPunktzahlWert;
    private JLabel lblAufgabentyp;
    private JLabel lblAufgabentypWert;
    private JButton btnAbbrechenTestat;
    private JButton btnLoesungshinweisTestat;
    private JButton btnVoherigeAufgabeTestat;
    private JButton btnNaechsteAufgabeTestat;
    private JButton btnTestatBeenden;
    private JButton btnUpload;

    private File fileBild = new File("Test.txt");
    byte[] eingabe;
    private boolean hinweisVerwendet;

    private TestatController testatController;
    private Designaufgabe aufgabe;
    private UserloesungDesignaufgabe userloesung;


    public BearbeiteTestatDesignaufgabeView2(TestatController testatController, Designaufgabe aufgabe) {

        this.setContentPane($$$getRootComponent$$$());
        this.hinweisVerwendet = false;
        this.aufgabe = aufgabe;
        this.testatController = testatController;

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

        btnAbbrechenTestat.addActionListener(this);
        btnLoesungshinweisTestat.addActionListener(this);
        btnVoherigeAufgabeTestat.addActionListener(this);
        btnNaechsteAufgabeTestat.addActionListener(this);
        btnTestatBeenden.addActionListener(this);
        btnUpload.addActionListener(this);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAbbrechenTestat) {
            JOptionPane.showMessageDialog(this, "Aufgaben werden nicht gespeichert");
            this.dispose();
            testatController.setNewTestatKatalog();
        } else if (e.getSource() == this.btnLoesungshinweisTestat) {
            if (aufgabe.getMusterloesung().getLoesungshinweis() != null) {
                JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen//
                hinweisVerwendet = true;
            } else {
                JOptionPane.showMessageDialog(this, "Kein Lösungshinweis vorhanden.", "Lösungshinweis", JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == this.btnVoherigeAufgabeTestat) {



            eingabe = convertFileToByteArray(fileBild, this);
            userloesung = new UserloesungDesignaufgabe(aufgabe, hinweisVerwendet, eingabe, testatController.getAktuellerBenutzer(), testatController.getTestat());
            testatController.addUserloesung(userloesung);
            //if (testatController.isIndexNotLast()) {
            testatController.zurueckTestat();
            /*

            // if (testatController.isIndexNotFirst()) {
            testatController.zurueckTestat();
            this.dispose();
            //getaktuelleLoesung();
            // }
            // else {
            testatController.zurueckTestat();
             */

        } else if (e.getSource() == this.btnNaechsteAufgabeTestat) {
            //ToDo: Userlösung in Form von ByteArray bekommen (über FileChooserDialog und Convert der File in ByteArray, dafür wird eine eine DBService Methode geben


            eingabe = convertFileToByteArray(fileBild, this);
            userloesung = new UserloesungDesignaufgabe(aufgabe, hinweisVerwendet, eingabe, testatController.getAktuellerBenutzer(), testatController.getTestat());
            testatController.addUserloesung(userloesung);
            //if (testatController.isIndexNotLast()) {
            testatController.weiter();

            /*
            //Wenn eingabe 1= null dann convert und add byte anonsten add null
            if (eingabe == null && testatController.halten.exists()) { // Wenn eingaben = 0 und file existiert dann convert, add und gehe weiter ansonsten gehe nur weiter
                byte[] userloesungByte = convertFileToByteArray(fileBild, this);
                userloesung = new UserloesungDesignaufgabe(aufgabe, hinweisVerwendet, userloesungByte, testatController.getAktuellerBenutzer(), testatController.getTestat());
                testatController.addUserloesung(userloesung);
                //if (testatController.isIndexNotLast()) {
                testatController.weiter();
                this.dispose();
                //} else {
                // testatController.weiter();
                // }
            } else {
                testatController.weiter();
                this.dispose();
            }
             */



        } else if (e.getSource() == this.btnUpload) {

            fileBild = dateiOeffnen(this);
            testatController.setFile(fileBild);

            //JFileChooser --> Bild oeffnen --> File in Liste hinzufuegen -->"Beenden" --> File zu Bild convertieren

        } else if (e.getSource() == this.btnTestatBeenden) {
            JOptionPane.showMessageDialog(this, "Testat ist abgeschickt");
            testatController.persistTestat();
            this.dispose();
            //BearbeiteTestatKatalogView.main(null);

        }
    }
    public void setUserloesungNull() {
        eingabe =  new byte[0];
    }

    public void setUserloesung(Userloesung userloesung) {

        eingabe = ((UserloesungDesignaufgabe) userloesung).getUserloesung();

    }

    /*
    public void setUserloesung(Userloesung userloesung) {

        if (eingabe == null) {
            eingabe = new byte[0];
        } else {
            eingabe = ((UserloesungDesignaufgabe) userloesung).getUserloesung();
            //this.fileBild.setUserloesung(eingabe);
        }


    }
     */

    public void setNaechsteZuSpeichern() {

        btnNaechsteAufgabeTestat.setText("Eingabe Speichern");

    }

    public void hideButton() {
        this.btnTestatBeenden.setVisible(false);
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
        mainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(5, 8, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblAufgabenText = new JLabel();
        lblAufgabenText.setText("Aufgabentext");
        panel1.add(lblAufgabenText, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(250, 150), new Dimension(250, 150), null, 0, false));
        lblBild = new JLabel();
        lblBild.setText("Bild");
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
        panel7.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel7, new GridConstraints(3, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnAbbrechenTestat = new JButton();
        btnAbbrechenTestat.setText("Abbrechen");
        panel7.add(btnAbbrechenTestat, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnLoesungshinweisTestat = new JButton();
        btnLoesungshinweisTestat.setText("Loesungshinweis");
        panel7.add(btnLoesungshinweisTestat, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnVoherigeAufgabeTestat = new JButton();
        btnVoherigeAufgabeTestat.setText("Voherige Aufgabe");
        panel7.add(btnVoherigeAufgabeTestat, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnNaechsteAufgabeTestat = new JButton();
        btnNaechsteAufgabeTestat.setText("Nächste Aufgabe");
        panel7.add(btnNaechsteAufgabeTestat, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnTestatBeenden = new JButton();
        btnTestatBeenden.setText("Testat Beenden");
        panel7.add(btnTestatBeenden, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
