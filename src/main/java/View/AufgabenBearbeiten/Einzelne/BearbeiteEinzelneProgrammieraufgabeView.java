package View.AufgabenBearbeiten.Einzelne;

import View.Lösungen.LoesungenEinzelaufgaben.LoesungEinzelneProgrammieraufgabeView;
import entity.aufgabe.Programmieraufgabe;
import entity.benutzer.Benutzer;
import entity.loesung.userloesung.UserloesungProgrammieraufgabe;

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
public class BearbeiteEinzelneProgrammieraufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton btnAbbrechen;
    private JButton btnLoesungshinweis;
    private JButton btnAufgabeBeenden;
    private JTextArea textArea;
    private Programmieraufgabe aufgabe;  //Im Frame die Aufgabe
    private UserloesungProgrammieraufgabe u1;
    private Benutzer benutzer;
    private JFrame frame;

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
     BearbeiteEinzelneProgrammieraufgabeView frame = new BearbeiteEinzelneProgrammieraufgabeView(a3, benutzer);
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
    public BearbeiteEinzelneProgrammieraufgabeView(Programmieraufgabe aufgabe, Benutzer benutzer, JFrame frame) {
        this.aufgabe = aufgabe;
        this.benutzer = benutzer;
        this.frame = frame;
        setTitle(aufgabe.getName()); //Name der Aufgabe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        JLabel lblNewLabel1 = new JLabel(aufgabe.getTextbeschreibung());//eingetragen
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


        btnAbbrechen = new JButton("Abbrechen");
        panelSouth.add(btnAbbrechen);
        btnLoesungshinweis = new JButton("Loesungshinweis");
        panelSouth.add(btnLoesungshinweis);
        btnAufgabeBeenden = new JButton("Aufgabe Beenden");
        panelSouth.add(btnAufgabeBeenden);

        this.btnAbbrechen.addActionListener(this);
        this.btnLoesungshinweis.addActionListener(this);
        this.btnAufgabeBeenden.addActionListener(this);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAbbrechen) {
            new BearbeiteEinzelneAufgabeKatalogView(frame, benutzer);
            dispose();
        }
        if (e.getSource() == this.btnLoesungshinweis) {
            if (aufgabe.getMusterloesung().getLoesungshinweis() != null) {
                JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen//
            } else {
                JOptionPane.showMessageDialog(this, "Kein Lösungshinweis vorhanden.", "Lösungshinweis", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == this.btnAufgabeBeenden) {
            u1 = new UserloesungProgrammieraufgabe();
            String u2 = textArea.getText();
            u1.setUserloesung(u2);
            this.dispose();
            new LoesungEinzelneProgrammieraufgabeView(aufgabe, u1, benutzer, frame);
        }
    }
}

	