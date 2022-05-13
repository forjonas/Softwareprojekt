package View.AufgabenBearbeiten.Testat;

import app.TestatApp;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.MultipleChoiceAufgabe;
import entity.aufgabensammlung.TestatBearbeitung;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Kristin Kubisch
 * @version 10.05.22
 * @version2 13.05.22
 */
public class BearbeiteTestatMultipleChoiceAufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton btnBeendenTestat;
    private JButton btnLoesungshinweisTestat;
    private JButton btnVoherigeAufgabeTestat;
    private JButton btnNaechsteAufgabeTestat;
    private JButton btnTestatBeenden;

    private TestatApp testatApp;
    private MultipleChoiceAufgabe aufgabe; //Im Frame die Aufgabe
    private TestatBearbeitung bearbeitet;
    private String antwort1;
    private String antwort2;
    private String antwort3;
    private String antwort4;

    /**
     * Create the frame.
     */
    public BearbeiteTestatMultipleChoiceAufgabeView(TestatApp testatApp, MultipleChoiceAufgabe aufgabe) {

        this.aufgabe = aufgabe;
        this.testatApp = testatApp;

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

        /**
         * Optionales Bild hinzufügen
         */

        JPanel panelCenter = new JPanel();
        contentPane.add(panelCenter, BorderLayout.CENTER);

        antwort1 = aufgabe.getAntwortmoeglichkeiten().get((0));
        JRadioButton button1 = new JRadioButton(antwort1);
        panelCenter.add(button1);

        antwort2 = aufgabe.getAntwortmoeglichkeiten().get((1));
        JRadioButton button2 = new JRadioButton(antwort2);
        panelCenter.add(button2);

        antwort3 = aufgabe.getAntwortmoeglichkeiten().get((2));
        JRadioButton button3 = new JRadioButton(antwort3);
        panelCenter.add(button3);

        antwort4 = aufgabe.getAntwortmoeglichkeiten().get((3));
        JRadioButton button4 = new JRadioButton(antwort4);
        panelCenter.add(button4);

        ButtonGroup bg = new ButtonGroup();
        bg.add(button1);
        bg.add(button2);
        bg.add(button3);
        bg.add(button4);

        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(panelSouth, BorderLayout.SOUTH);


        btnBeendenTestat = new JButton("Beenden");
        panelSouth.add(btnBeendenTestat);
        btnLoesungshinweisTestat = new JButton("Loesungshinweis");
        panelSouth.add(btnLoesungshinweisTestat);
        btnVoherigeAufgabeTestat = new JButton("Vorherige Aufgabe");
        panelSouth.add(btnVoherigeAufgabeTestat);
        btnNaechsteAufgabeTestat = new JButton("N\u00E4chste Aufgabe");
        panelSouth.add(btnNaechsteAufgabeTestat);
        btnTestatBeenden = new JButton("Testat Beenden");
        panelSouth.add(btnTestatBeenden);

        this.btnBeendenTestat.addActionListener(this);
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
        if (e.getSource() == this.btnBeendenTestat) {
            this.dispose();
            BearbeiteTestatKatalogView.main(null);

        }
        if (e.getSource() == this.btnLoesungshinweisTestat) {
            JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen
        }
        if (e.getSource() == this.btnTestatBeenden) {
            this.dispose();
            testatApp.printTest();
            BearbeiteTestatKatalogView.main(null);

        }
        if (e.getSource() == this.btnVoherigeAufgabeTestat) {
            JOptionPane.showMessageDialog(this, "Button Vorherige");

        }
        if (e.getSource() == this.btnNaechsteAufgabeTestat) {

            //antwort = textArea.getText();
            //testatApp.usereingaben.add(antwort); //antwort wird in Liste hinzugefügt und gehalten
            testatApp.weiter(); //testatApp.testat
            /**
             * speichern in testatApp und am Ende Testat an TestatBearbeiten übergenen --> erstellen und persetieren
             */

        }
    }
}
