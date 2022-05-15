package testateK;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EinfachantwortView2 extends JFrame implements ActionListener {



    public static void main(String[] args) {


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    EinfachantwortView2 frame = new EinfachantwortView2();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private JPanel contentPane;
    private JTextField textField;

    /**
     * Create the frame.
     */
    public EinfachantwortView2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel aufgabenstellungLabel = new JLabel("Die FRage ist");
        JLabel bildLabel = new JLabel("Hier ist ein Bild");

        JLabel AntwortLabel = new JLabel("Die Antwort ist");

        JLabel ButtonLabel = new JLabel("Die Buttons");

        gbc.gridx = 2;
        gbc.gridy = 2;

        add(aufgabenstellungLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;

        add(AntwortLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 8;

        add(ButtonLabel, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}