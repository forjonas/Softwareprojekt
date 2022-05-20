package View.AufgabenBearbeiten.Testat;

import app.TestatController;
import entity.aufgabe.MultipleChoiceAufgabe;
import entity.loesung.userloesung.Userloesung;
import entity.loesung.userloesung.UserloesungEinfachantwort;
import entity.loesung.userloesung.UserloesungMultipleChoiceAufgabe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
public class BearbeiteTestatMultipleChoiceAufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton btnAbbrechenTestat;
    private JButton btnLoesungshinweisTestat;
    private JButton btnVoherigeAufgabeTestat;
    private JButton btnNaechsteAufgabeTestat;
    private JButton btnTestatBeenden;
    private boolean hinweisVerwendet;

    JRadioButton button1;
    JRadioButton button2;
    JRadioButton button3;
    JRadioButton button4;

    private TestatController testatController;
    private MultipleChoiceAufgabe aufgabe;
    private UserloesungMultipleChoiceAufgabe userloesung;

    private String antwort1;
    private String antwort2;
    private String antwort3;
    private String antwort4;

    public BearbeiteTestatMultipleChoiceAufgabeView(TestatController testatController, MultipleChoiceAufgabe aufgabe) {
        this.hinweisVerwendet = false;
        this.aufgabe = aufgabe;
        this.testatController = testatController;

        setTitle(aufgabe.getName()); //Name der Aufgabe);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        JLabel lblTextbeschreibung = new JLabel(aufgabe.getTextbeschreibung()); //Text mit Textbeschreibung
        panelNorth.add(lblTextbeschreibung);

        JPanel panelCenter = new JPanel();
        contentPane.add(panelCenter, BorderLayout.CENTER);

        int mIndex = aufgabe.getAntwortmoeglichkeiten().size();

        for (int i = 0; i < mIndex; i++) { // läuft Listen Größe ab

            if (i == 0) {
                antwort1 = aufgabe.getAntwortmoeglichkeiten().get((0));
                button1 = new JRadioButton(antwort1);
                panelCenter.add(button1);
            } else if (i == 1) {
                antwort2 = aufgabe.getAntwortmoeglichkeiten().get((1));
                button2 = new JRadioButton(antwort2);
                panelCenter.add(button2);
            } else if (i == 2) {
                antwort3 = aufgabe.getAntwortmoeglichkeiten().get((2));
                button3 = new JRadioButton(antwort3);
                panelCenter.add(button3);

            } else if (i == 3) {
                antwort4 = aufgabe.getAntwortmoeglichkeiten().get((3));
                button4 = new JRadioButton(antwort4);
                panelCenter.add(button4);
            }
        }

        ButtonGroup bg = new ButtonGroup();
        bg.add(button1);
        bg.add(button2);
        bg.add(button3);
        bg.add(button4);

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
            } else {
                testatController.zurueckTestat();
            }
        }
        if (e.getSource() == this.btnNaechsteAufgabeTestat) {

            List<Boolean> userloesungBooleanArray = new LinkedList<Boolean>();

            userloesungBooleanArray.add(false);
            userloesungBooleanArray.add(false);
            userloesungBooleanArray.add(false);
            userloesungBooleanArray.add(false);

            if (button1.isSelected()) {
                userloesungBooleanArray.set(0, true);
            } else if (button2.isSelected()) {
                userloesungBooleanArray.set(1, true);
            } else if (button3.isSelected()) {
                userloesungBooleanArray.set(2, true);
            } else if (button4.isSelected()) {
                userloesungBooleanArray.set(3, true);
            }

            userloesung = new UserloesungMultipleChoiceAufgabe(aufgabe, hinweisVerwendet, userloesungBooleanArray, testatController.getAktuellerBenutzer(), testatController.getTestat());
            testatController.addUserloesung(userloesung); //antwort wird in UListe hinzugefügt und gehalten
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
        List<Boolean> geklickt = ((UserloesungMultipleChoiceAufgabe) userloesung).getUserloesung();


        for (int i = 0; i < geklickt.size(); i++) {
            Boolean wert = geklickt.get(i);
            if (i == 0) {
                button1.setSelected(wert);
            }
            if (i == 1) {
                button1.setSelected(wert);
            }
            if (i == 2) {
                button1.setSelected(wert);
            }
            if (i == 3) {
                button1.setSelected(wert);
            }
        }
    }

    public void hideButton() {
        this.btnTestatBeenden.setVisible(false);
        this.update(this.getGraphics());
    }
}