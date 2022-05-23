package View.AufgabenBearbeiten.Testat;

import app.TestatController;
import entity.aufgabe.*;
import entity.loesung.userloesung.Userloesung;
import entity.loesung.userloesung.UserloesungDesignaufgabe;
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
 * @version5: 20.05.22 Beenden Button versteckt
 */
public class BearbeiteTestatEinfachantwortAufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextArea textArea;
    private JButton btnAbbrechenTestat;
    private JButton btnLoesungshinweisTestat;
    private JButton btnVoherigeAufgabeTestat;
    private JButton btnNaechsteAufgabeTestat;
    private JButton btnTestatBeenden;
    private boolean hinweisVerwendet;

    private TestatController testatController;
    private EinfachantwortAufgabe aufgabe;
    private UserloesungEinfachantwort userloesung;

    public BearbeiteTestatEinfachantwortAufgabeView(TestatController testatController, EinfachantwortAufgabe aufgabe) {
        this.hinweisVerwendet = false;
        this.aufgabe = aufgabe;
        this.testatController = testatController;

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
            JOptionPane.showMessageDialog(this, "Aufgaben werden nicht gespeichert");
            this.dispose();
            testatController.setNewTestatKatalog();

        }
        if (e.getSource() == this.btnLoesungshinweisTestat) {
            if (aufgabe.getMusterloesung().getLoesungshinweis() != null) {
                JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen//
                hinweisVerwendet = true;
            } else {
                JOptionPane.showMessageDialog(this, "Kein Lösungshinweis vorhanden.", "Lösungshinweis", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == this.btnVoherigeAufgabeTestat) {
            if (testatController.isIndexNotFirst()) {
                testatController.zurueckTestat();
                this.dispose();
                //getaktuelleLoesung();
            } else {
                testatController.zurueckTestat();
            }
        }

        if (e.getSource() == this.btnNaechsteAufgabeTestat) {
            String userloesungString = textArea.getText();
            userloesung = new UserloesungEinfachantwort(aufgabe, hinweisVerwendet, userloesungString, testatController.getAktuellerBenutzer(), testatController.getTestat());
            testatController.addUserloesung(userloesung);
            if (testatController.isIndexNotLast()) {
                testatController.weiter();
                this.dispose();
            } else {
                testatController.weiter();
            }
        }
        if (e.getSource() == this.btnTestatBeenden) { //Abfrage wenn nicht letzte Aufgabe noch hinzufuegen
            JOptionPane.showMessageDialog(this, "Testat ist abgeschickt");
            testatController.persistTestat();
            this.dispose();
            //BearbeiteTestatKatalogView.main(null);

        }
    }

    public void setUserloesung(Userloesung userloesung) {
        String eingabeText = ((UserloesungEinfachantwort) userloesung).getUserloesung();
        this.textArea.setText(eingabeText);

    }

    public void hideButton() {
        this.btnTestatBeenden.setVisible(false);
        this.update(this.getGraphics());
    }

}