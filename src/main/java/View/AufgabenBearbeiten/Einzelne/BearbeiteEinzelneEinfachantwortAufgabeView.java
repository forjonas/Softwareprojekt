package View.AufgabenBearbeiten.Einzelne;

import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneEinfachantwortaufgabeView;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.benutzer.Benutzer;
import entity.loesung.userloesung.UserloesungEinfachantwort;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version3: 16.05.22
 * @version4: 18.05.22
 */
public class BearbeiteEinzelneEinfachantwortAufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextArea textArea;
    private JButton btnAbbrechen;
    private JButton btnLoesungshinweisEinzel;
    private JButton btnAufgabeBeendenEinzel;
    private final EinfachantwortAufgabe aufgabe;  //Im Frame die Aufgabe
    private UserloesungEinfachantwort u1;
    private Benutzer benutzer;
    private JFrame frame;

    /**
     * public static void main(String[] args) {
     *
     *         EinfachantwortAufgabe a1 = new EinfachantwortAufgabe();
     *         Designaufgabe a2 = new Designaufgabe();
     *         Programmieraufgabe a3 = new Programmieraufgabe();
     *         MultipleChoiceAufgabe a4 = new MultipleChoiceAufgabe();
     *         Benutzer benutzer = new Student();
     *         EventQueue.invokeLater(new Runnable() {
     *             public void run() {
     *                 try {
     *                     BearbeiteEinzelneEinfachantwortAufgabeView frame = new BearbeiteEinzelneEinfachantwortAufgabeView(a1, benutzer);
     *                     frame.setVisible(true);
     *                 } catch (Exception e) {
     *                     e.printStackTrace();
     *                 }
     *             }
     *         });
     *     }
     */


    /**
     * Create the frame.
     */
    public BearbeiteEinzelneEinfachantwortAufgabeView(EinfachantwortAufgabe aufgabe, Benutzer benutzer, JFrame frame) {
        this.aufgabe = aufgabe;
        this.benutzer = benutzer;
        this.frame = frame;
        setTitle(aufgabe.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
// Frame festlegen

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        JLabel lblNewLabel1 = new JLabel(aufgabe.getTextbeschreibung());
        panelNorth.add(lblNewLabel1);
// Textbeschreibung

        /**
         * Optionales Bild hinzufügen
         */

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

        btnAbbrechen = new JButton("Abbrechen");
        panelSouth.add(btnAbbrechen);
        btnLoesungshinweisEinzel = new JButton("Loesungshinweis");
        panelSouth.add(btnLoesungshinweisEinzel);
        btnAufgabeBeendenEinzel = new JButton("Aufgabe Beenden");
        panelSouth.add(btnAufgabeBeendenEinzel);
//im Panel Panel die Buttons

        this.btnAbbrechen.addActionListener(this);
        this.btnLoesungshinweisEinzel.addActionListener(this);
        this.btnAufgabeBeendenEinzel.addActionListener(this);
//addActionListener

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAbbrechen) {
            /**
             * if (textArea.getText().isEmpty()) {
             *                 // int i1 = 0;
             *                 JOptionPane.showConfirmDialog(null, "Sollen alle Daten verschwinden?", "Daten gehen verloren", JOptionPane.YES_NO_OPTION);
             *                 int i1 = 0;
             *                 if (i1 == JOptionPane.YES_OPTION) {
             *                     this.dispose();
             *                     BearbeiteEinzelneAufgabeKatalogView.main(null);
             *                 } else {
             *
             *                 }
             *             }
             */
            new BearbeiteEinzelneAufgabeKatalogView(frame, benutzer);
            dispose();
        }
        if (e.getSource() == this.btnLoesungshinweisEinzel) {
            if (aufgabe.getMusterloesung().getLoesungshinweis() != null) {
                JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen//
            } else {
                JOptionPane.showMessageDialog(this, "Kein Lösungshinweis vorhanden.", "Lösungshinweis", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == this.btnAufgabeBeendenEinzel) {

            u1 = new UserloesungEinfachantwort();
            String u2 = textArea.getText();
            u1.setUserloesung(u2);
            this.dispose();
            new LoesungEinzelneEinfachantwortaufgabeView(aufgabe, u1, benutzer, frame);

        }

    }
}
