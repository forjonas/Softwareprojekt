package View.AufgabenErstellen;

import View.DozentAnsicht;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Die Start View zur aufgaben Erstellung
 *
 * @author Jannik Oehme
 * @version 05.05.2022
 *  @version 09.05.2022 Layout gefixed
 */
public class AufgabeErstellenStartView implements ActionListener {
    JFrame AufgabeErstellenStartFrame;
    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;
    JButton zurueckBtn;
    JButton weiterBtn;
    GridLayout gl = new GridLayout(3,1);
    JComboBox <String> DDM;


    public static void main(String[] args) {
        new AufgabeErstellenStartView();
    }

    public AufgabeErstellenStartView() {

        AufgabeErstellenStartFrame = new JFrame("Aufgabe Erstellen");
        AufgabeErstellenFrameFuellen();
        AufgabeErstellenStartFrame.setSize(100, 100);
        AufgabeErstellenStartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AufgabeErstellenStartFrame.pack();
        AufgabeErstellenStartFrame.setVisible(true);
    }
    private void AufgabeErstellenFrameFuellen() {
        //Panels
        centerPnl =new JPanel();
        centerPnl.setLayout(gl);
        centerPnl.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        //Buttons
        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        weiterBtn = new JButton("Weiter");
        weiterBtn.addActionListener(this);
        //DropDownMenu
        String[] AufgabenTypen = {"Designaufgabe","Programmieraufgabe","Einfachantwort", "MultipleChoiceaufgabe"};
        DDM = new JComboBox<>(AufgabenTypen);
        //Components Adden
        northPnl = new JPanel();
        northPnl.add(zurueckBtn);

        southPnl = new JPanel();
        southPnl.add(weiterBtn);

        centerPnl.add(DDM);
        AufgabeErstellenStartFrame.add(centerPnl,BorderLayout.CENTER);
        AufgabeErstellenStartFrame.add(northPnl,BorderLayout.NORTH);
        AufgabeErstellenStartFrame.add(southPnl,BorderLayout.SOUTH);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckBtn) {
            zurueck();
        } else if (e.getSource() == this.weiterBtn) {
            weiter();
        }
    }

    private void weiter() {
        String switcher = (String) DDM.getSelectedItem();
        switch(switcher) {
            case "Designaufgabe":AufgabeErstellenStartFrame.dispose();
                        AufgabeErstellenUmlView.main(null);
                        break;
            case "Programmieraufgabe":AufgabeErstellenStartFrame.dispose();
                        AufgabeErstellenCodeView.main(null);
                        break;
            case "MultipleChoiceaufgabe":AufgabeErstellenStartFrame.dispose();
                                    AufgabeErstellenMultipleChoiceView.main(null);
                        break;
            case "Einfachantwort": AufgabeErstellenStartFrame.dispose() ;
                                    AufgabeErstellenEinfachAntwortView.main(null);
                        break;
            default:    AufgabeErstellenStartFrame.dispose();
                        DozentAnsicht.main(null);
        }
    }
    private void zurueck() {
        AufgabeErstellenStartFrame.dispose();
        DozentAnsicht.main(null);
    }

    public static boolean inputcleaner(int bearbeitungsZeit, int punkte,Frame testFrame) {
        if(bearbeitungsZeit >= 60 || bearbeitungsZeit<=1 ||punkte >=100 || punkte <= 0){
            errorTest(testFrame);
            return false;

        }
        else{
            return true;

        }
    }
    static void errorTest(Frame test){
        JDialog jd = new JDialog();
        jd.toFront();
        JLabel errorLbl = new JLabel("Bearbeitungszeit liegt nicht zwischen 60 und 1 Minuten. Oder die Punktezahl liegt nicht zwischen 100 oder 0");
        jd.setSize(800,100);
        jd.add(errorLbl);
        jd.setVisible(true);
    }
}
