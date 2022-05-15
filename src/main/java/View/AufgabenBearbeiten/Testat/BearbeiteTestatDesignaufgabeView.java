package View.AufgabenBearbeiten.Testat;

import app.TestatApp;
import entity.aufgabe.Designaufgabe;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.loesung.userloesung.UserloesungDesignaufgabe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version2: 13.05.22
 * @version3: 16.05.22
 */
public class BearbeiteTestatDesignaufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton btnAbbrechenTestat;
    private JButton btnLoesungshinweisTestat;
    private JButton btnVoherigeAufgabeTestat;
    private JButton btnNaechsteAufgabeTestat;
    private JButton btnTestatBeenden;

    private JButton btnUpload;

    private JTextArea textArea;

    private File geuploadet;

    ImageIcon icon = new ImageIcon("C:\\BspSoftwareProjekt\\BspDiagram.jpg");

    private TestatApp testatApp;
    private Designaufgabe aufgabe;  //Im Frame die Aufgabe
    private TestatBearbeitung bearbeitet;
    private UserloesungDesignaufgabe u1; //noch String

    /**
     * Create the frame.
     */
    public BearbeiteTestatDesignaufgabeView(TestatApp testatApp, Designaufgabe aufgabe) {

        this.aufgabe = aufgabe;
        this.testatApp = testatApp;
        //FESTE ANGABEN WEG!!!
        setTitle(aufgabe.getName()); //Name der Aufgabe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        JLabel lblNewLabel1 = new JLabel(aufgabe.getTextbeschreibung()); //Text mit Textbeschreibung//angepasst
        panelNorth.add(lblNewLabel1);

        /**
         * Optionales Bild hinzufügen
         */

        JPanel panelCenter = new JPanel();
        contentPane.add(panelCenter, BorderLayout.CENTER);
        textArea = new JTextArea(18, 50);
        panelCenter.add(textArea);

        /**
         JPanel panelCenter = new JPanel();
         contentPane.add(panelCenter, BorderLayout.CENTER);
         JLabel lblNewLabel_2 = new JLabel(); //Button daraus machen
         panelCenter.add(lblNewLabel_2);
         //Wenn auf Button-- Bild auswählen-- Bild anzeigen
         */
        JPanel panelWest = new JPanel();
        contentPane.add(panelWest, BorderLayout.WEST);
        btnUpload = new JButton("Upload");
        panelWest.add(btnUpload);


        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(panelSouth, BorderLayout.SOUTH);

        btnAbbrechenTestat = new JButton("Abbrechen");
        panelSouth.add(btnAbbrechenTestat);
        btnLoesungshinweisTestat = new JButton("Loesungshinweis");
        panelSouth.add(btnLoesungshinweisTestat);
        btnVoherigeAufgabeTestat = new JButton("Vorherige Aufgabe");
        panelSouth.add(btnVoherigeAufgabeTestat);
        btnNaechsteAufgabeTestat = new JButton("N\u00E4chste Aufgabe");
        panelSouth.add(btnNaechsteAufgabeTestat);
        btnTestatBeenden = new JButton("Testat Beenden");
        panelSouth.add(btnTestatBeenden);

        this.btnAbbrechenTestat.addActionListener(this);
        this.btnLoesungshinweisTestat.addActionListener(this);
        this.btnVoherigeAufgabeTestat.addActionListener(this);
        this.btnNaechsteAufgabeTestat.addActionListener(this);
        this.btnTestatBeenden.addActionListener(this);
        this.btnUpload.addActionListener(this);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAbbrechenTestat) {
            testatApp.printPersistenz();
            this.dispose();
            BearbeiteTestatKatalogView.main(null);
        }
        if (e.getSource() == this.btnLoesungshinweisTestat) {
            JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis());//Lösungshinweis eingefügt
        }

        if (e.getSource() == this.btnVoherigeAufgabeTestat) {
            JOptionPane.showMessageDialog(this, "Button Vorherige");
            testatApp.zrueck();
        }
        if (e.getSource() == this.btnNaechsteAufgabeTestat) { //angepasst

            u1 = new UserloesungDesignaufgabe();
            String u2 = textArea.getText();
            u1.setUserloesung(u2);
            testatApp.usereingaben.add(u1); //antwort wird in UListe hinzugefügt und gehalten
            testatApp.weiter(); //testatApp.testat

            /**
             u1 = new UserloesungDesignaufgabe();
             Icon u2 = icon.getImage();
             u1.setUserloesung(u2);
             testatApp.usereingaben.add(u1); //antwort wird in UListe hinzugefügt und gehalten
             testatApp.weiter(); //testatApp.testat
             */
        }

        if (e.getSource() == this.btnTestatBeenden) {
            testatApp.printPersistenz();
            this.dispose();
            BearbeiteTestatKatalogView.main(null);
        }

        if (e.getSource() == this.btnUpload) { //angepasst
            JOptionPane.showMessageDialog(this, "Upload Button");

            /**
             private File codeBspHochladen() {
             FC = new JFileChooser((String) null);
             FC.setAcceptAllFileFilterUsed(false);
             FC.setFileFilter(new ImageFilter());
             int returnVal = FC.showOpenDialog(null);
             if (returnVal == JFileChooser.APPROVE_OPTION) {
             codeBspFile = FC.getSelectedFile();
             System.out.println(codeBspFile.getName());
             return codeBspFile;
             }
             return null;
             }
             */

            /**
             Wenn ich auf Button klicke: öffne Dateifile *Ich wähle Bild aus*
             lade das DocCode
             Lese Datei und speicher diese in antwort
             antwort = getDatei();
             String docUpload = textArea.getText(); // lese den input eig. Bild
             lblNewLabel_2.setIcon(docUpload);  //Oder austauschen mit File
             */
        }

    }
}