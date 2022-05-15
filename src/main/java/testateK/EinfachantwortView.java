package testateK;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatKatalogView;
import app.TestatApp;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.loesung.userloesung.UserloesungEinfachantwort;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class EinfachantwortView extends JFrame implements ActionListener {

    //Frame Elemente
    private JPanel contentPane;
    private JTextArea textArea;
    private JButton btnAbbrechenTestat;
    private JButton btnLoesungshinweisTestat;
    private JButton btnVoherigeAufgabeTestat;
    private JButton btnNaechsteAufgabeTestat;
    private JButton btnTestatBeenden;

    private TestatApp testatApp;
    private EinfachantwortAufgabe aufgabe;  //Im Frame die Aufgabe
    private TestatBearbeitung bearbeitet;
    private UserloesungEinfachantwort u1;

    /**
     * Create the frame.
     */
    public EinfachantwortView() {

        setTitle("Test1"); //Name der Aufgabe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);

        JLabel lblTextbeschreibung = new JLabel("Test2"); //Text mit Textbeschreibung
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
            this.dispose();
            BearbeiteTestatKatalogView.main(null);

        }
        if (e.getSource() == this.btnLoesungshinweisTestat) {
            JOptionPane.showMessageDialog(this, "Test3"); //Lösungshinweis bekommen
        }
        if (e.getSource() == this.btnVoherigeAufgabeTestat) {
            JOptionPane.showMessageDialog(this, "Button Vorherige");

        }
        if (e.getSource() == this.btnNaechsteAufgabeTestat) {
            JOptionPane.showMessageDialog(this, "Test3");
        }
        if (e.getSource() == this.btnTestatBeenden) {
            JOptionPane.showMessageDialog(this, "Test3");
        }
    }




    public static void main(String[] args) {


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    EinfachantwortView testatApp = new EinfachantwortView();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

