package View.AufgabenBearbeiten.Testat;

import app.TestatApp;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.MultipleChoiceAufgabe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Kristin Kubisch
 * @version 10.05.22
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
    private String antwort1;
    private String antwort2;
    private String antwort3;
    private String antwort4;

    /**
     * Launch the application.
     */
    /**
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BearbeiteTestatMultipleChoiceAufgabeView frame = new BearbeiteTestatMultipleChoiceAufgabeView(TestatApp testatApp, MultipleChoiceAufgabe aufgabe);//angepasst
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
    public BearbeiteTestatMultipleChoiceAufgabeView(TestatApp testatApp, MultipleChoiceAufgabe aufgabe) {

        this.aufgabe = aufgabe;
        setTitle(aufgabe.getName()); //Name der Aufgabe);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        JLabel lblNewLabel = new JLabel(aufgabe.getTextbeschreibung()); //Text mit Textbeschreibung//angepasst
        panelNorth.add(lblNewLabel);

        JPanel panelCenter = new JPanel();
        contentPane.add(panelCenter, BorderLayout.CENTER);

        // antwort1 = aufgabe.getAntwortmoeglichkeiten(1)(0));
        JRadioButton button1 = new JRadioButton(antwort1);
        panelCenter.add(button1);

        // antwort2 = aufgabe.getAntwortmoeglichkeiten(1)(1));
        JRadioButton button2 = new JRadioButton(antwort2);
        panelCenter.add(button2);

        // antwort3 = aufgabe.getAntwortmoeglichkeiten(1)(2));
        JRadioButton button3 = new JRadioButton(antwort3);
        panelCenter.add(button3);

        // antwort4 = aufgabe.getAntwortmoeglichkeiten(1)(3));
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeendenTestat) {
            JOptionPane.showMessageDialog(this, "Button Beenden");

        }
        if (e.getSource() == this.btnLoesungshinweisTestat) {
            JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen
        }
        if (e.getSource() == this.btnTestatBeenden) {
            JOptionPane.showMessageDialog(this, "Button Testat Beenden");
        }
        if (e.getSource() == this.btnVoherigeAufgabeTestat) {
            JOptionPane.showMessageDialog(this, "Button Vorherige");

        }
        if (e.getSource() == this.btnNaechsteAufgabeTestat) {
            JOptionPane.showMessageDialog(this, "Button Nächste");

            //gucke welcher button ausgewählt ist und speicher diese in einer Datei (Userlösung)

            this.testatApp.weiter(); //Waren z.b. bei Aufgabe 1 gehen weiter zu 2

        }
    }
}
