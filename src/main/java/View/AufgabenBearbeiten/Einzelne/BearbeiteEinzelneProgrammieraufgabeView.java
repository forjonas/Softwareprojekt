package View.AufgabenBearbeiten.Einzelne;

import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.MultipleChoiceAufgabe;
import entity.aufgabe.Programmieraufgabe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BearbeiteEinzelneProgrammieraufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton btnBeenden;
    private JButton btnLoesungshinweis;
    private JButton btnAufgabeBeenden;
    private JButton btnUpload;
    private ImageIcon icon = new ImageIcon("C:\\BspSoftwareProjekt\\BspDiagram.jpg");
    private Programmieraufgabe aufgabe;  //Im Frame die Aufgabe

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
                    BearbeiteEinzelneProgrammieraufgabeView frame = new BearbeiteEinzelneProgrammieraufgabeView(a3);
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
    public BearbeiteEinzelneProgrammieraufgabeView(Programmieraufgabe aufgabe) {
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


        btnBeenden = new JButton("Beenden");
        panelSouth.add(btnBeenden);
        btnLoesungshinweis = new JButton("Loesungshinweis");
        panelSouth.add(btnLoesungshinweis);
        btnAufgabeBeenden = new JButton("Aufgabe Beenden");
        panelSouth.add(btnAufgabeBeenden);

        this.btnBeenden.addActionListener(this);
        this.btnLoesungshinweis.addActionListener(this);
        this.btnAufgabeBeenden.addActionListener(this);
        this.btnUpload.addActionListener(this);

        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeenden) {
            JOptionPane.showMessageDialog(this, "Button Beenden");

        }
        if (e.getSource() == this.btnLoesungshinweis) {
            //JOptionPane.showMessageDialog(this,"Button Loesungshinweis");
            JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis());
        }
        if (e.getSource() == this.btnAufgabeBeenden) {
            JOptionPane.showMessageDialog(this, "Button Aufgabe Beenden");


        }
        if (e.getSource() == this.btnUpload) {
            JOptionPane.showMessageDialog(this, "Upload Button");
        }
    }
}

	