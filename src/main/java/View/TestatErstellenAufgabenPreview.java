package View;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.MultipleChoiceAufgabe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/*
20.05 T.Joswig u. K.Kubisch Preview
 */
public class TestatErstellenAufgabenPreview extends JFrame implements ActionListener {
    private JTextField txtfAufgabenstellung;
    private JButton btnZurueckButton;
    private JLabel lblBild;
    private JLabel lblBearbeitungzeit;
    private JLabel lblPunktzahl;
    private JLabel lblBearbeitungszeitWert;
    private JLabel lblPunktzahlWert;
    private JPanel mainPanel;
    private JRadioButton antwort1;
    private JRadioButton antwort2;
    private JRadioButton antwort3;
    private JRadioButton antwort4;
    private JPanel mcPanel;

    private Aufgabe aufgabe;


    public TestatErstellenAufgabenPreview(Aufgabe aufgabe) {
        this.aufgabe = aufgabe;
        this.setContentPane(mainPanel);
        btnZurueckButton.addActionListener(this);

        setTitle(aufgabe.getName()); //Name der Aufgabe
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtfAufgabenstellung.setText(aufgabe.getTextbeschreibung());
        lblBearbeitungszeitWert.setText(aufgabe.getBearbeitungszeit() + " min");
        lblPunktzahlWert.setText(aufgabe.getPunktewert() + " P.");

        if (aufgabe.getAufgabenstellungsbild()!= null){
            lblBild.setIcon(new ImageIcon(aufgabe.getAufgabenstellungsbild()));
        }

        this.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == this.btnZurueckButton) {

            this.dispose();
        }
    }

    public void showMcPanel() {
        this.mcPanel.setVisible(true);

        MultipleChoiceAufgabe mCAufgabe = (MultipleChoiceAufgabe) this.aufgabe;
        List moeglichkeiten = mCAufgabe.getAntwortmoeglichkeiten();

        antwort1.setText((String) moeglichkeiten.get(0));
        antwort2.setText((String) moeglichkeiten.get(1));
        antwort3.setText((String) moeglichkeiten.get(2));
        antwort4.setText((String) moeglichkeiten.get(3));
        antwort1.setEnabled(false);
        antwort2.setEnabled(false);
        antwort3.setEnabled(false);
        antwort4.setEnabled(false);

        this.update(this.getGraphics());
    }

    public void hideMcPanel() {

        this.mcPanel.setVisible(false);
        this.update(this.getGraphics());
    }

}
