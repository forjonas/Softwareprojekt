package View.AufgabenBearbeiten.Testat;

import View.AufgabenBearbeiten.Einzelne.BearbeiteEinzelneAufgabeKatalogView;
import app.TestatApp;
import entity.aufgabe.*;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.loesung.userloesung.UserloesungEinfachantwort;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version2: 13.05.22
 * @version3: 16.05.22
 * @version4: 18.05.22
 */
public class BearbeiteTestatEinfachantwortAufgabeView extends JFrame implements ActionListener {

    //Frame Elemente
    private JPanel contentPane;
    private JTextArea textArea;
    private JButton btnAbbrechenTestat;
    private JButton btnLoesungshinweisTestat;
    private JButton btnVoherigeAufgabeTestat;
    private JButton btnNaechsteAufgabeTestat;
    private JButton btnTestatBeenden;

    private TestatApp testatApp;
    private EinfachantwortAufgabe aufgabe;  //Im Frame die Aufgabe
    // private TestatBearbeitung bearbeitet;
    private UserloesungEinfachantwort u1;

    public BearbeiteTestatEinfachantwortAufgabeView(TestatApp testatApp, EinfachantwortAufgabe aufgabe) {

        this.aufgabe = aufgabe;
        this.testatApp = testatApp;

        setTitle(aufgabe.getName()); //Name der Aufgabe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);

        JLabel lblTextbeschreibung = new JLabel(aufgabe.getTextbeschreibung()); //Text mit Textbeschreibung
        panelNorth.add(lblTextbeschreibung);


        JPanel panelCenter = new JPanel();
        contentPane.add(panelCenter, BorderLayout.CENTER);
        textArea = new JTextArea(18, 50);
        panelCenter.add(textArea);


        JPanel panelWest = new JPanel();
        contentPane.add(panelWest, BorderLayout.WEST);
        JLabel lblNewLabel_1 = new JLabel("Antwort:");
        panelWest.add(lblNewLabel_1);


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

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAbbrechenTestat) {
            JOptionPane.showMessageDialog(this, "Aufgabe wird nicht gespeichert");
            this.dispose();
            //BearbeiteTestatKatalogView.main(null);

        }
        if (e.getSource() == this.btnLoesungshinweisTestat) {
            JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen
        }
        if (e.getSource() == this.btnVoherigeAufgabeTestat) {
            testatApp.zurueckTestat();

        }
        if (e.getSource() == this.btnNaechsteAufgabeTestat) {

            u1 = new UserloesungEinfachantwort();
            String u2 = textArea.getText();
            u1.setUserloesung(u2);
            testatApp.usereingaben.add(u1); //antwort wird in UListe hinzugefügt und gehalten
            testatApp.weiter(); //testatApp.testat

        }
        if (e.getSource() == this.btnTestatBeenden) { //Abfrage wenn nicht letzte Aufgabe noch hinzufuegen
            JOptionPane.showMessageDialog(this, "Testat ist abgeschickt");
            testatApp.persistUser();
            this.dispose();
            //BearbeiteTestatKatalogView.main(null);

        }
    }
}