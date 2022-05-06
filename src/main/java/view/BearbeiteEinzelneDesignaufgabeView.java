package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BearbeiteEinzelneDesignaufgabeView extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton btnBeenden;
    private JButton btnLoesungshinweis;
    private JButton btnAufgabeBeenden;
    private JButton btnUpload;
    ImageIcon icon = new ImageIcon ("C:\\BspSoftwareProjekt\\BspDiagram.jpg");


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BearbeiteEinzelneDesignaufgabeView frame = new BearbeiteEinzelneDesignaufgabeView();
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
    public BearbeiteEinzelneDesignaufgabeView() {
        setTitle("Einzel Design Aufgabe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 674, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);
        JLabel lblNewLabel1 = new JLabel("Aufgabe: Erstellen Sie das Diagramm in Enterprise Architect!");
        panelNorth.add(lblNewLabel1);

        JLabel lblNewLabel_2 = new JLabel (icon);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnBeenden) {
            JOptionPane.showMessageDialog(this,"Button Beenden");

        }
        if (e.getSource() == this.btnLoesungshinweis) {
            JOptionPane.showMessageDialog(this,"Button Loesungshinweis");
        }
        if (e.getSource() == this.btnAufgabeBeenden) {
            JOptionPane.showMessageDialog(this,"Button Aufgabe Beenden");
        }
        if (e.getSource() == this.btnUpload) {
            JOptionPane.showMessageDialog(this,"Upload Button");
        }
    }
}
