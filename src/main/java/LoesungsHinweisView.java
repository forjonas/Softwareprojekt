import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Die View des Lösungshinweises
 *
 * @author Jannik Oehme
 * @version 04.05.2022
 */

public class LoesungsHinweisView implements ActionListener {

    JFrame LoesungshinwFrame;
    JPanel LoesungshinwPnl;
    JButton zurueckAufgBtn;
    BorderLayout bl;
    JLabel hinweisText;




    public static void main(String[] args)
    {
        new LoesungsHinweisView();
    }
    LoesungsHinweisView(){

        LoesungshinwFrame = new JFrame("Lösungshinweis");
        fuelleLoesungshinweisFrame();
        LoesungshinwFrame.setSize(1000,500);
        LoesungshinwFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoesungshinwFrame.setVisible(true);

    }
    void fuelleLoesungshinweisFrame(){

        hinweisText = new JLabel();
        hinweisText.setText(getLabelText());
        LoesungshinwFrame = new JFrame();
        LoesungshinwPnl = new JPanel();
        zurueckAufgBtn = new JButton("Zurück");
        zurueckAufgBtn.addActionListener(this);
        zurueckAufgBtn.setPreferredSize(new Dimension(160, 80));

        bl = new BorderLayout();
        LoesungshinwPnl.setLayout(bl);
        LoesungshinwPnl.add(zurueckAufgBtn, BorderLayout.NORTH);
        LoesungshinwPnl.add(hinweisText, BorderLayout.CENTER);
        LoesungshinwFrame.add(LoesungshinwPnl);
    }

    private String getLabelText() {
        return "abv";
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
