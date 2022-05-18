package View.AufgabenBearbeiten.Training;

import app.TrainingController;
import entity.aufgabe.Programmieraufgabe;
import entity.loesung.userloesung.UserloesungEinfachantwort;
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
    private boolean hinweisVerwendet;
    private TrainingController trainingController;
    private Programmieraufgabe aufgabe;
    private UserloesungProgrammieraufgabe userloesung;

    /**
     * Create the frame.
     */
    public BearbeiteTrainingProgrammieraufgabeView(TrainingController trainingController, Programmieraufgabe aufgabe) {
        this.hinweisVerwendet = false;
        this.aufgabe = aufgabe;
        this.trainingController = trainingController;

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
        btnNaechsteAufgabeTraining = new JButton("Nächste Aufgabe");
        panelSouth.add(btnNaechsteAufgabeTraining);
        btnTrainingBeenden = new JButton("Training Beenden");
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
            trainingController.setUserFrameVisible();

        }
        if (e.getSource() == this.btnLoesungshinweisTraining) {
            if (aufgabe.getMusterloesung().getLoesungshinweis() != null) {
                JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen//
                hinweisVerwendet = true;
            } else {
                JOptionPane.showMessageDialog(this, "Kein Lösungshinweis vorhanden.", "Lösungshinweis", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == this.btnVoherigeAufgabeTraining) {
            if (trainingController.isIndexNotFirst()) {
                trainingController.zurueckTraining();
                this.dispose();
            } else {
                trainingController.zurueckTraining();
            }
        }
        if (e.getSource() == this.btnNaechsteAufgabeTraining) {
            String userloesungString = textArea.getText();
            userloesung = new UserloesungProgrammieraufgabe(aufgabe, hinweisVerwendet, userloesungString, trainingController.getAktuellerBenutzer(), trainingController.getTraining());
            trainingController.addUserloesung(userloesung);
            if (trainingController.isIndexNotLast()) {
                trainingController.weiter();
                this.dispose();
            } else {
                trainingController.weiter();
            }
        }
        if (e.getSource() == this.btnTrainingBeenden) {
            JOptionPane.showMessageDialog(this, "Training ist abgeschickt");
            trainingController.persistTraining();
            this.dispose();
        }

    }
}
