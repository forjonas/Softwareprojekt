package View.AufgabenBearbeiten.Einzelne;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatKatalogView;
import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneEinfachantwortaufgabeView;
import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneMultipleChoiceAufgabeView;
import app.TestatApp;
import entity.aufgabe.*;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.benutzer.Benutzer;
import entity.benutzer.Student;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.userloesung.UserloesungEinfachantwort;
import entity.loesung.userloesung.UserloesungMultipleChoiceAufgabe;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version2: 13.05.22
 * @version3: 16.05.22
 */
public class BearbeiteEinzelneMultipleChoiceAufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton btnAbbrechen;
    private JButton btnLoesungshinweisEinzel;
    private JButton btnAufgabeBeendenEinzel;
    private MultipleChoiceAufgabe aufgabe;  //Im Frame die Aufgabe

    JRadioButton button1;
    JRadioButton button2;
    JRadioButton button3;
    JRadioButton button4;
    private UserloesungMultipleChoiceAufgabe u1;
    private Benutzer benutzer;
    private String antwort1;
    private String antwort2;
    private String antwort3;
    private String antwort4;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        Aufgabe a4 = new MultipleChoiceAufgabe(2, "umlDesign", Kategorie.Java_Programmierung, 5, Schwierigkeitsgrad.Leicht, "Welcher Datentyp ist für Ganzzahlen?", "Datentyp Ganzzahlen", null, Arrays.asList(new String[]{"char", "int", "double","Test"}));
        Benutzer benutzer = new Student();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BearbeiteEinzelneMultipleChoiceAufgabeView frame = new BearbeiteEinzelneMultipleChoiceAufgabeView((MultipleChoiceAufgabe) a4,benutzer);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public BearbeiteEinzelneMultipleChoiceAufgabeView(MultipleChoiceAufgabe aufgabe,Benutzer benutzer) {
        this.aufgabe = aufgabe;
        this.benutzer = benutzer;
        setTitle(aufgabe.getName()); //Name der Aufgabe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));


        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        JLabel lblNewLabel = new JLabel(aufgabe.getTextbeschreibung());
        panelNorth.add(lblNewLabel);
/**
 * Optionales Bild hinzufügen
 */

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

        btnAbbrechen = new JButton("Abbrechen");
        panelSouth.add(btnAbbrechen);
        btnLoesungshinweisEinzel = new JButton("Loesungshinweis");
        panelSouth.add(btnLoesungshinweisEinzel);
        btnAufgabeBeendenEinzel = new JButton("Aufgabe Beenden");
        panelSouth.add(btnAufgabeBeendenEinzel);

        this.btnAbbrechen.addActionListener(this);
        this.btnLoesungshinweisEinzel.addActionListener(this);
        this.btnAufgabeBeendenEinzel.addActionListener(this);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAbbrechen) {
            this.dispose();
            BearbeiteEinzelneAufgabeKatalogView.main(null);
        }
        if (e.getSource() == this.btnLoesungshinweisEinzel) {
            JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis());
        }
        if (e.getSource() == this.btnAufgabeBeendenEinzel) {

            u1 = new UserloesungMultipleChoiceAufgabe();
            List<Boolean> u3 = new ArrayList<>();

            u3.add(false);
            u3.add(false);
            u3.add(false);
            u3.add(false);

            if (button1.isSelected()) {
                u3.set(0, true);
            } else if (button2.isSelected()) {
                u3.set(1, true);
            } else if (button3.isSelected()) {
                u3.set(2, true);
            } else if (button4.isSelected()) {
                u3.set(3, true);
            }

            u1.setUserloesung(u3);
            this.dispose();
            new LoesungEinzelneMultipleChoiceAufgabeView(aufgabe, u1, benutzer);

        }

    }
}
