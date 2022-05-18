package View.AufgabenBearbeiten.Einzelne;

import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneDesignaufgabeView;
import entity.aufgabe.Designaufgabe;
import entity.benutzer.Benutzer;
import entity.loesung.userloesung.UserloesungDesignaufgabe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version3: 16.05.22
 * @version4: 18.05.22
 */

public class BearbeiteEinzelneDesignaufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton btnAbbrechen;
    private JButton btnLoesungshinweis;
    private JButton btnAufgabeBeenden;
    private JButton btnUpload;
    private Benutzer benutzer;
    private UserloesungDesignaufgabe u1;
    private JFrame frame;

    private ImageIcon icon = new ImageIcon("C:\\BspSoftwareProjekt\\BspDiagram.jpg");
    private Designaufgabe aufgabe;  //Im Frame die Aufgabe

    /**
     public static void main(String[] args) {

     EinfachantwortAufgabe a1 = new EinfachantwortAufgabe();
     Designaufgabe a2 = new Designaufgabe();
     Programmieraufgabe a3 = new Programmieraufgabe();
     MultipleChoiceAufgabe a4 = new MultipleChoiceAufgabe();
     Benutzer benutzer = new Student();
     EventQueue.invokeLater(new Runnable() {
     public void run() {
     try {
     BearbeiteEinzelneDesignaufgabeView frame = new BearbeiteEinzelneDesignaufgabeView(a2, benutzer);
     frame.setVisible(true);
     } catch (Exception e) {
     e.printStackTrace();
     }
     }
     });
     }     */


    /**
     * Create the frame.
     */
    public BearbeiteEinzelneDesignaufgabeView(Designaufgabe aufgabe, Benutzer benutzer, JFrame frame) {
        this.frame = frame;
        this.aufgabe = aufgabe;
        this.benutzer = benutzer;
        setTitle(aufgabe.getName()); //Name der Aufgabe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        JLabel lblNewLabel1 = new JLabel(aufgabe.getTextbeschreibung());//eingetragen
        panelNorth.add(lblNewLabel1);

        JLabel lblNewLabel_2 = new JLabel(icon);
        contentPane.add(lblNewLabel_2, BorderLayout.CENTER);

        JPanel panelWest = new JPanel();
        contentPane.add(panelWest, BorderLayout.WEST);
        btnUpload = new JButton("Upload");
        panelWest.add(btnUpload);


        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(panelSouth, BorderLayout.SOUTH);


        btnAbbrechen = new JButton("Abbrechen");
        panelSouth.add(btnAbbrechen);
        btnLoesungshinweis = new JButton("Loesungshinweis");
        panelSouth.add(btnLoesungshinweis);
        btnAufgabeBeenden = new JButton("Aufgabe Beenden");
        panelSouth.add(btnAufgabeBeenden);

        this.btnAbbrechen.addActionListener(this);
        this.btnLoesungshinweis.addActionListener(this);
        this.btnAufgabeBeenden.addActionListener(this);
        this.btnUpload.addActionListener(this);

        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAbbrechen) {
            JOptionPane.showMessageDialog(this, "Button Beenden");

        }
        if (e.getSource() == this.btnLoesungshinweis) {
            if (aufgabe.getMusterloesung().getLoesungshinweis() != null) {
                JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen//
            } else {
                JOptionPane.showMessageDialog(this, "Kein Lösungshinweis vorhanden.", "Lösungshinweis", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == this.btnAufgabeBeenden) {
            JOptionPane.showMessageDialog(this, "Button Aufgabe Beenden");
            this.dispose();
            new LoesungEinzelneDesignaufgabeView(aufgabe, u1, benutzer, frame);


            /**
             *  u1 = new UserloesungEinfachantwort();
             *             String u2 = textArea.getText();
             *             u1.setUserloesung(u2);
             *             this.dispose();
             *             //  LoesungEinzelneDesignaufgabeView(aufgabe, u1);
             */

        }
        if (e.getSource() == this.btnUpload) {
            JOptionPane.showMessageDialog(this, "Upload Button");
        }
    }
}
