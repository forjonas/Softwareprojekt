package testateK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.FIRST_LINE_START;
import static java.awt.GridBagConstraints.CENTER;

public class EinfachantwortView2 extends JFrame {

    private Container container;
    private GridBagLayout gbLayout;
    GridBagConstraints gbc;
    private JButton btnAbbrechenTestat;
    private JButton btnLoesungshinweisTestat;
    private JButton btnVoherigeAufgabeTestat;
    private JButton btnNaechsteAufgabeTestat;
    private JButton btnTestatBeenden;

    public EinfachantwortView2() {
        super.setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setBounds(100, 100, 450, 300);

        container = getContentPane();
        gbLayout = new GridBagLayout();
        container.setLayout(gbLayout);
        gbc = new GridBagConstraints();

        JLabel label = new JLabel("LabelTextAufgabenstellung");
        JLabel label2 = new JLabel("LabelTextBild",  JLabel.LEFT);
        JTextField tf = new JTextField(10);

       //JTextArea ta = new JTextArea("TextArea1", 5, 6);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        //gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;  // can grow wider
        gbc.weighty = 1;
        gbc.anchor= FIRST_LINE_START;
        addComponent(label, 1, 1, 3, 3);

        addComponent(label2, 1, 6, 3, 3);

        gbc.weightx = 1;  // can grow wider
        gbc.weighty = 1;
        gbc.anchor= CENTER;
        addComponent(tf, 2, 1, 5, 1);

        btnAbbrechenTestat = new JButton("Abbrechen");
        panel.add(btnAbbrechenTestat);

        btnLoesungshinweisTestat = new JButton("Loesungshinweis");
        panel.add(btnLoesungshinweisTestat);

        btnVoherigeAufgabeTestat = new JButton("Vorherige Aufgabe");
        panel.add(btnVoherigeAufgabeTestat);
        btnNaechsteAufgabeTestat = new JButton("N\u00E4chste Aufgabe");
        panel.add(btnNaechsteAufgabeTestat);
        btnTestatBeenden = new JButton("Testat Beenden");
        panel.add(btnTestatBeenden);
        gbc.anchor= CENTER;
        addComponent(panel, 4, 1, 10, 3);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);

        /**
         * gbc.fill = GridBagConstraints.VERTICAL;
         *         gbc.insets = new Insets(5, 5, 5, 5);
         *         gbc.gridx = 2;
         *         gbc.gridy = 2;
         *
         *         gbc.gridwidth = 1;
         *         gbc.gridheight = 1;
         *         textField = new JTextField();
         *         this.textField.addActionListener(this);
         *         contentPane.add(textField, gbc);
         *         add(textField);//???
         */

        /**
         *  JLabel aufgabenstellungLabel = new JLabel("Die FRage ist");
         *         JLabel bildLabel = new JLabel("Hier ist ein Bild");
         *
         *         JLabel AntwortLabel = new JLabel("Die Antwort ist");
         *
         *         JLabel ButtonLabel = new JLabel("Die Buttons");
         *
         *         gbc.gridx = 2;
         *         gbc.gridy = 2;
         *
         *         add(aufgabenstellungLabel, gbc);
         *
         *         gbc.gridx = 2;
         *         gbc.gridy = 5;
         *
         *         add(AntwortLabel, gbc);
         *
         *         gbc.gridx = 2;
         *         gbc.gridy = 8;
         *
         *         add(ButtonLabel, gbc);
         */


    }

    private void addComponent(Component c, int row, int column, int width, int height) {
        // set gridx and gridy
        gbc.gridx = column;
        gbc.gridy = row;

        // set gridwidth and gridheight
        gbc.gridwidth = width;
        gbc.gridheight = height;

        // set constraints
        gbLayout.setConstraints(c, gbc);
        container.add(c);      // add component
    }

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
}