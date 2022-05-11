package View.AufgabenBearbeiten.Testat;

import app.TestatApp;
import entity.Aufgabe;
import entity.Designaufgabe;
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
public class BearbeiteTestatDesignaufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton btnBeendenTestst;
    private JButton btnLoesungshinweisTestat;
    private JButton btnVoherigeAufgabeTestat;
    private JButton btnNaechsteAufgabeTestat;
    private JButton btnTestatBeenden;
    private JButton btnUpload;
    ImageIcon icon = new ImageIcon("C:\\BspSoftwareProjekt\\BspDiagram.jpg");

    private TestatApp testatApp;
    private Designaufgabe aufgabe;  //Im Frame die Aufgabe




    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BearbeiteTestatDesignaufgabeView frame = new BearbeiteTestatDesignaufgabeView(TestatApp testatApp, Designaufgabe aufgabe); //angepasst--> nötig?? Warum
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


    //public BearbeiteTestatDesignaufgabeView(TestatApp testatApp, Aufgabe aufgabe) { }
    public BearbeiteTestatDesignaufgabeView(TestatApp testatApp, Designaufgabe aufgabe) {

        this.aufgabe = aufgabe;
        setTitle(aufgabe.getName()); //Name der Aufgabe

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        JLabel lblNewLabel1 = new JLabel(aufgabe.getTextbeschreibung()); //Text mit Textbeschreibung//angepasst
        panelNorth.add(lblNewLabel1);

        JLabel lblNewLabel_2 = new JLabel(icon);
        contentPane.add(lblNewLabel_2, BorderLayout.CENTER);

        JPanel panelWest = new JPanel();
        contentPane.add(panelWest, BorderLayout.WEST);
        btnUpload = new JButton("Upload");
        panelWest.add(btnUpload);


        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(panelSouth, BorderLayout.SOUTH);

        btnBeendenTestst = new JButton("Beenden");
        panelSouth.add(btnBeendenTestst);
        btnLoesungshinweisTestat = new JButton("Loesungshinweis");
        panelSouth.add(btnLoesungshinweisTestat);
        btnVoherigeAufgabeTestat = new JButton("Vorherige Aufgabe");
        panelSouth.add(btnVoherigeAufgabeTestat);
        btnNaechsteAufgabeTestat = new JButton("N\u00E4chste Aufgabe");
        panelSouth.add(btnNaechsteAufgabeTestat);
        btnTestatBeenden = new JButton("Testat Beenden");
        panelSouth.add(btnTestatBeenden);

        this.btnBeendenTestst.addActionListener(this);
        this.btnLoesungshinweisTestat.addActionListener(this);
        this.btnVoherigeAufgabeTestat.addActionListener(this);
        this.btnNaechsteAufgabeTestat.addActionListener(this);
        this.btnTestatBeenden.addActionListener(this);
        this.btnUpload.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeendenTestst) {
            JOptionPane.showMessageDialog(this, "Button Beenden");

        }
        if (e.getSource() == this.btnLoesungshinweisTestat) {
            JOptionPane.showMessageDialog(this, aufgabe.getLoesungshinweis());//Lösungshinweis eingefügt
        }
        if (e.getSource() == this.btnVoherigeAufgabeTestat) {
            JOptionPane.showMessageDialog(this, "Button Vorherige");
        }
        if (e.getSource() == this.btnNaechsteAufgabeTestat) { //angepasst
            //Lese Datei und speicher diese in (Userlösung) //HIER NICHT????

            String textFieldValue = docUpload; //übergebe den docUpload vom Upload Button
            File DName = new File("AntwortAufgabe1.txt");
            fw = new FileWriter(DName);
            bw = new BufferedWriter(fw);
            bw.write(textFieldValue);  //bw schreibt txt Datei --> eig. Bild
            this.testatApp.weiter(); //Waren z.b. bei Aufgabe 3 gehen weiter zu 4

        }
        if (e.getSource() == this.btnTestatBeenden) {
            JOptionPane.showMessageDialog(this, "Button Testat Beenden");
        }
        if (e.getSource() == this.btnUpload) { //angepasst
            //Lese Datei und speicher diese in (Userlösung)
            // Wenn ich auf Button klicke: öffne Dateifile *Ich wähle Bild aus*
            // lade das DocCode
            // String docUpload = textArea.getText(); // lese den input eig. Bild
            JOptionPane.showMessageDialog(this, "Upload Button");
        }

    }
}