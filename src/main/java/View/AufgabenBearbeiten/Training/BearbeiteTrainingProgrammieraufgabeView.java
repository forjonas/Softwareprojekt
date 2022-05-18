package View.AufgabenBearbeiten.Training;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatKatalogView;
import app.TrainingApp;
import entity.aufgabe.Programmieraufgabe;
import entity.loesung.userloesung.UserloesungProgrammieraufgabe;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BearbeiteTrainingProgrammieraufgabeView extends JFrame implements ActionListener {


    private JPanel contentPane;
    private JTextArea textArea;
    private JButton btnAbbrechenTraining;
    private JButton btnLoesungshinweisTraining;
    private JButton btnVoherigeAufgabeTraining;
    private JButton btnNaechsteAufgabeTraining;
    private JButton btnTrainingBeenden;

    private TrainingApp trainingApp;
    private Programmieraufgabe aufgabe;
    private UserloesungProgrammieraufgabe u1;

    /**
     * Create the frame.
     */
    public BearbeiteTrainingProgrammieraufgabeView(TrainingApp trainingApp, Programmieraufgabe aufgabe) {
        this.aufgabe = aufgabe;
        this.trainingApp = trainingApp;

        setTitle(aufgabe.getName()); //Name der Aufgabe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        JLabel lblTextbeschreibung = new JLabel(aufgabe.getTextbeschreibung()); //Text mit Textbeschreibung
        panelNorth.add(lblTextbeschreibung);

        /**
         * Optionales Bild hinzufügen
         */

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

        btnAbbrechenTraining = new JButton("Abbrechen");
        panelSouth.add(btnAbbrechenTraining);
        btnLoesungshinweisTraining = new JButton("Loesungshinweis");
        panelSouth.add(btnLoesungshinweisTraining);
        btnVoherigeAufgabeTraining = new JButton("Vorherige Aufgabe");
        panelSouth.add(btnVoherigeAufgabeTraining);
        btnNaechsteAufgabeTraining = new JButton("N\u00E4chste Aufgabe");
        panelSouth.add(btnNaechsteAufgabeTraining);
        btnTrainingBeenden = new JButton("Testat Beenden");
        panelSouth.add(btnTrainingBeenden);

        this.btnAbbrechenTraining.addActionListener(this);
        this.btnLoesungshinweisTraining.addActionListener(this);
        this.btnVoherigeAufgabeTraining.addActionListener(this);
        this.btnNaechsteAufgabeTraining.addActionListener(this);
        this.btnTrainingBeenden.addActionListener(this);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAbbrechenTraining) {
            this.dispose();
            BearbeiteTestatKatalogView.main(null);

        }
        if (e.getSource() == this.btnLoesungshinweisTraining) {
            JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen
        }
        if (e.getSource() == this.btnVoherigeAufgabeTraining) {
            JOptionPane.showMessageDialog(this, "Button Vorherige");
            trainingApp.zurueckTraining();
        }
        if (e.getSource() == this.btnNaechsteAufgabeTraining) {

            u1 = new UserloesungProgrammieraufgabe();
            String u2 = textArea.getText();
            u1.setUserloesung(u2);
            trainingApp.usereingaben.add(u1);
            trainingApp.weiter();

        }
        if (e.getSource() == this.btnTrainingBeenden) {
            this.dispose();
            trainingApp.printPersistenz();
            trainingApp.setUserFrameVisible();

        }

    }
}
