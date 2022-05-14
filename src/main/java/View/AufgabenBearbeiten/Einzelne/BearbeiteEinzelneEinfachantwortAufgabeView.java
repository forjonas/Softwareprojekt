package View.AufgabenBearbeiten.Einzelne;

import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.MultipleChoiceAufgabe;
import entity.aufgabe.Programmieraufgabe;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Kristin Kubisch
 * @version 10.05.22
 */
public class BearbeiteEinzelneEinfachantwortAufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextArea textArea;
    private JButton btnBeendenEinzel;
    private JButton btnLoesungshinweisEinzel;
    private JButton btnAufgabeBeendenEinzel;
    private EinfachantwortAufgabe aufgabe;  //Im Frame die Aufgabe


    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EinfachantwortAufgabe a1 = new EinfachantwortAufgabe();
        Designaufgabe a2 = new Designaufgabe();
        Programmieraufgabe a3 = new Programmieraufgabe();
        MultipleChoiceAufgabe a4 = new MultipleChoiceAufgabe();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BearbeiteEinzelneEinfachantwortAufgabeView frame = new BearbeiteEinzelneEinfachantwortAufgabeView(a1);
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
    public BearbeiteEinzelneEinfachantwortAufgabeView(EinfachantwortAufgabe aufgabe) {

        this.aufgabe = aufgabe;
        setTitle(aufgabe.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
// Frame festlegen

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        JLabel lblNewLabel1 = new JLabel("Hier steht ein Text");//(aufgabe.getTextbeschreibung()); //eingetragen
        panelNorth.add(lblNewLabel1);
// Textbeschreibung

        JPanel panelCenter = new JPanel();
        contentPane.add(panelCenter, BorderLayout.CENTER);
        textArea = new JTextArea(18, 50);
        panelCenter.add(textArea);
//Antwortmöglichkeit

        JPanel panelWest = new JPanel();
        contentPane.add(panelWest, BorderLayout.WEST);
        JLabel lblNewLabel_1 = new JLabel("Antwort:");
        panelWest.add(lblNewLabel_1);
//Antwort Label

        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(panelSouth, BorderLayout.SOUTH);
// im BorderLayout Panel mit FlowLayout

        btnBeendenEinzel = new JButton("Beenden");
        panelSouth.add(btnBeendenEinzel);
        btnLoesungshinweisEinzel = new JButton("Loesungshinweis");
        panelSouth.add(btnLoesungshinweisEinzel);
        btnAufgabeBeendenEinzel = new JButton("Aufgabe Beenden");
        panelSouth.add(btnAufgabeBeendenEinzel);
//im Panel Panel die Buttons

        this.btnBeendenEinzel.addActionListener(this);
        this.btnLoesungshinweisEinzel.addActionListener(this);
        this.btnAufgabeBeendenEinzel.addActionListener(this);
//addActionListener

        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeendenEinzel) {
            JOptionPane.showMessageDialog(this, "Button Beenden");

        }
        if (e.getSource() == this.btnLoesungshinweisEinzel) {
            JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis());
        }
        if (e.getSource() == this.btnAufgabeBeendenEinzel) {
            JOptionPane.showMessageDialog(this, "Button Aufgabe Beenden");

        }

    }
}
