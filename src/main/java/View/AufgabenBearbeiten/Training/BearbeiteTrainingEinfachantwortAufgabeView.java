package View.AufgabenBearbeiten.Training;

import View.DozentAnsicht;
import View.LoesungsHinweisView;
import app.TestatApp;
import app.TrainingApp;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.EinfachantwortAufgabe;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Kristin Kubisch
 * @version 10.05.22
 */
public class BearbeiteTrainingEinfachantwortAufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextArea textArea;
    private JButton btnBeendenTraining;
    private JButton btnLoesungshinweisTraining;
    private JButton btnVoherigeAufgabeTraining;
    private JButton btnNaechsteAufgabeTraining;
    private JButton btnTrainingBeenden;

    private TrainingApp trainingApp;
    private EinfachantwortAufgabe aufgabe;  //Im Frame die Aufgabe

    //private TestatApp testatApp;

    /**
     * Launch the application.
     */

    /**
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BearbeiteTrainingEinfachantwortAufgabeView frame = new BearbeiteTrainingEinfachantwortAufgabeView(TrainingApp trainingApp, EinfachantwortAufgabe aufgabe);//angepasst
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    /**
     * Create the frame.
     */
    public BearbeiteTrainingEinfachantwortAufgabeView(TrainingApp trainingApp, EinfachantwortAufgabe aufgabe) {
        this.aufgabe = aufgabe;
        setTitle(aufgabe.getName()); //Name der Aufgabe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));


        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        JLabel lblNewLabel1 = new JLabel(aufgabe.getTextbeschreibung());
        panelNorth.add(lblNewLabel1);


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

        btnBeendenTraining = new JButton("Beenden");
        panelSouth.add(btnBeendenTraining);
        btnLoesungshinweisTraining = new JButton("Loesungshinweis");
        panelSouth.add(btnLoesungshinweisTraining);
        btnVoherigeAufgabeTraining = new JButton("Vorherige Aufgabe");
        panelSouth.add(btnVoherigeAufgabeTraining);
        btnNaechsteAufgabeTraining = new JButton("weiter");
        panelSouth.add(btnNaechsteAufgabeTraining);
        btnTrainingBeenden = new JButton("Training Beenden");
        panelSouth.add(btnTrainingBeenden);

        this.btnBeendenTraining.addActionListener(this);
        this.btnLoesungshinweisTraining.addActionListener(this);
        this.btnVoherigeAufgabeTraining.addActionListener(this);
        this.btnNaechsteAufgabeTraining.addActionListener(this);
        this.btnTrainingBeenden.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeendenTraining) {
           // this.dispose();
           // DozentAnsicht.main(null); //eigentlich: studentView.main(null);
        }
        if (e.getSource() == this.btnLoesungshinweisTraining) {
            //this.dispose();
            //LoesungsHinweisView.main(null); //Alt von Jannik
            JOptionPane.showMessageDialog(this,aufgabe.getMusterloesung().getLoesungshinweis());
        }
        if (e.getSource() == this.btnVoherigeAufgabeTraining) {
            JOptionPane.showMessageDialog(this, "Button Vorherige");
        }
        if (e.getSource() == this.btnNaechsteAufgabeTraining) {
            //Button.nächste

            /** Die Buffered und FileWriter brauchen try Catch. Lies die mal genau durch wie du das mit denen machst
            //Lese antworten und speicher diese in einer Datei (Userlösung)
            String textFieldValue = textArea.getText(); // read den input TextArea
            // String DName = EAM.rString();
            File DName = new File("AntwortAufgabe1.txt");
            FileWriter fw = new FileWriter(DName);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(textFieldValue);  //bw schreibt txt Datei
             */

            this.trainingApp.weiter();

            JOptionPane.showMessageDialog(this, "Button Nächste");

        }
        if (e.getSource() == this.btnTrainingBeenden) {
           // this.dispose();
           // DozentAnsicht.main(null); //eigentlich: studentView.main(null);
        }

    }
}
