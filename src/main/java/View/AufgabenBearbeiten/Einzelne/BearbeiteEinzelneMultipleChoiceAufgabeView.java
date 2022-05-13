package View.AufgabenBearbeiten.Einzelne;

import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.MultipleChoiceAufgabe;
import entity.aufgabe.Programmieraufgabe;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BearbeiteEinzelneMultipleChoiceAufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton btnBeendenEinzel;
    private JButton btnLoesungshinweisEinzel;
    private JButton btnAufgabeBeendenEinzel;
    private MultipleChoiceAufgabe aufgabe;  //Im Frame die Aufgabe

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
                    BearbeiteEinzelneMultipleChoiceAufgabeView frame = new BearbeiteEinzelneMultipleChoiceAufgabeView(a4);
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
    public BearbeiteEinzelneMultipleChoiceAufgabeView(MultipleChoiceAufgabe aufgabe) {
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
        JLabel lblNewLabel = new JLabel(aufgabe.getTextbeschreibung());
        panelNorth.add(lblNewLabel);


        JPanel panelCenter = new JPanel();
        contentPane.add(panelCenter, BorderLayout.CENTER);

        JRadioButton button1 = new JRadioButton(aufgabe.getAntwortmoeglichkeiten().get(0));
        panelCenter.add(button1);
        JRadioButton button2 = new JRadioButton(aufgabe.getAntwortmoeglichkeiten().get(1));
        panelCenter.add(button2);
        JRadioButton button3 = new JRadioButton(aufgabe.getAntwortmoeglichkeiten().get(2));
        panelCenter.add(button3);
        JRadioButton button4 = new JRadioButton(aufgabe.getAntwortmoeglichkeiten().get(3));
        panelCenter.add(button4);


        ButtonGroup bg = new ButtonGroup();
        bg.add(button1);
        bg.add(button2);
        bg.add(button3);
        bg.add(button4);


        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(panelSouth, BorderLayout.SOUTH);

        btnBeendenEinzel = new JButton("Beenden");
        panelSouth.add(btnBeendenEinzel);
        btnLoesungshinweisEinzel = new JButton("Loesungshinweis");
        panelSouth.add(btnLoesungshinweisEinzel);
        btnAufgabeBeendenEinzel = new JButton("Aufgabe Beenden");
        panelSouth.add(btnAufgabeBeendenEinzel);

        this.btnBeendenEinzel.addActionListener(this);
        this.btnLoesungshinweisEinzel.addActionListener(this);
        this.btnAufgabeBeendenEinzel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeendenEinzel) {
            JOptionPane.showMessageDialog(this, "Button Beenden");

        }
        if (e.getSource() == this.btnLoesungshinweisEinzel) {
            //JOptionPane.showMessageDialog(this,"Button Loesungshinweis");
            JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis());
        }
        if (e.getSource() == this.btnAufgabeBeendenEinzel) {
            JOptionPane.showMessageDialog(this, "Button Aufgabe Beenden");
        }

    }
}
