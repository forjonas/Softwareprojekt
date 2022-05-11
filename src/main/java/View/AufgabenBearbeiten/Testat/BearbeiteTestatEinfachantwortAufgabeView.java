package View.AufgabenBearbeiten.Testat;

import app.TestatApp;
import entity.EinfachantwortAufgabe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Kristin Kubisch
 * @version 10.05.22
 */
public class BearbeiteTestatEinfachantwortAufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextArea textArea;
    private JButton btnBeendenTestat;
    private JButton btnLoesungshinweisTestat;
    private JButton btnVoherigeAufgabeTestat;
    private JButton btnNaechsteAufgabeTestat;
    private JButton btnTestatBeenden;
    private TestatApp testatApp;
    private EinfachantwortAufgabe aufgabe;  //Im Frame die Aufgabe

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BearbeiteTestatEinfachantwortAufgabeView frame = new BearbeiteTestatEinfachantwortAufgabeView(TestatApp testatApp, EinfachantwortAufgabe aufgabe);//angepasst
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
    public BearbeiteTestatEinfachantwortAufgabeView(TestatApp testatApp, EinfachantwortAufgabe aufgabe) {

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
        JLabel lblTextbeschreibung = new JLabel(aufgabe.getTextbeschreibung()); //Text mit Textbeschreibung
        panelNorth.add(lblTextbeschreibung);


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
            JOptionPane.showMessageDialog(this, aufgabe.getLoesungshinweis()); //Lösungshinweis bekommen
        }
        if (e.getSource() == this.btnTestatBeenden) {
            JOptionPane.showMessageDialog(this, "Button Testat Beenden");
        }
        if (e.getSource() == this.btnVoherigeAufgabeTestat) {
            JOptionPane.showMessageDialog(this, "Button Vorherige");

        }
        if (e.getSource() == this.btnNaechsteAufgabeTestat) {
            JOptionPane.showMessageDialog(this, "Button Nächste");

            //Lese antworten und speicher diese in einer Datei (Userlösung)
            String textFieldValue = textArea.getText(); // read den input TextArea
            // String DName = EAM.rString();
            File DName = new File("AntwortAufgabe1.txt");
            fw = new FileWriter(DName);
            bw = new BufferedWriter(fw);
            bw.write(textFieldValue);  //bw schreibt txt Datei

            this.testatApp.weiter(); //Waren z.b. bei Aufgabe 1 gehen weiter zu 2

        }
    }
}

