package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.BreakIterator;

/**
 * Die View des Lösungshinweises
 *
 * @author Jannik Oehme
 * @version 04.05.2022
 * @verion 9.05.2022 Layout gefixed
 */

public class LoesungsHinweisView implements ActionListener {
    JFrame LoesungshinwFrame;
    JPanel LoesungshinwPnl;
    JButton zurueckAufgBtn;
    JLabel hinweisTextLbL;
    GridLayout gl = new GridLayout(1,1);
    JPanel northPnl;
    BorderLayout bl = new BorderLayout();

    public static void main(String[] args)
    {
        new LoesungsHinweisView();
    }
    LoesungsHinweisView(){

        LoesungshinwFrame = new JFrame("Lösungshinweis");
        fuelleLoesungshinweisFrame();
        LoesungshinwFrame.setSize(500,500);
        LoesungshinwFrame.pack();
        LoesungshinwFrame.setVisible(true);

    }
    void fuelleLoesungshinweisFrame(){
        gl.setVgap(25);
        gl.setHgap(25);

        northPnl = new JPanel(gl);
        hinweisTextLbL = new JLabel(getLabelText());
        LoesungshinwFrame = new JFrame();
        LoesungshinwPnl = new JPanel(bl);
        zurueckAufgBtn = new JButton("Zurück");
        northPnl.add(zurueckAufgBtn);
        LoesungshinwPnl.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        zurueckAufgBtn.addActionListener(this);

        LoesungshinwPnl.add(northPnl, BorderLayout.NORTH);
        LoesungshinwPnl.add(hinweisTextLbL, BorderLayout.CENTER);
        LoesungshinwFrame.add(LoesungshinwPnl);
    }
    private String getLabelText() {
        return "aasaaa";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.zurueckAufgBtn){
            zurueckAufg();
        }
    }
    private void zurueckAufg() {
        //Link zu der Aufgabe Brauche noch AufgabenViewKlasse muss dann mit den <T> dingern sein theoretisch
    }
}
