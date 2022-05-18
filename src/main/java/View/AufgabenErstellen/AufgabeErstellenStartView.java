package View.AufgabenErstellen;

import entity.benutzer.Dozent;

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
 *  @version 15.05.2022 Dozentübergabe eingerichtet, Frame parameter eingefügt, inputcleaner eingefügt, change zu extends JFrame gemacht
 */
public class AufgabeErstellenStartView extends JFrame implements ActionListener {
    private JPanel centerPnl;
    private JPanel northPnl;
    private JPanel southPnl;
    private JButton zurueckBtn;
    private JButton weiterBtn;
    private GridLayout gl = new GridLayout(3,1);
    private JComboBox <String> DDM;
    private JFrame dozentAnsichtFrame;
   private Dozent doz;

    public AufgabeErstellenStartView(JFrame dozentAnsichtFrame,Dozent doz) {
        this.doz = doz;
        this.dozentAnsichtFrame = dozentAnsichtFrame;
        this.setName("Aufgabe Erstellen");
        AufgabeErstellenFrameFuellen();
        this.pack();
        this.setMinimumSize(new Dimension(1500,900));
        this.setSize(1500,900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }
    private void AufgabeErstellenFrameFuellen() {
        //Panels
        centerPnl =new JPanel();
        centerPnl.setLayout(gl);
        centerPnl.setBorder(BorderFactory.createEmptyBorder(300, 400, 300, 400));
        //Buttons
        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);
        weiterBtn = new JButton("Weiter");
        weiterBtn.addActionListener(this);
        //ComboBox
        String[] AufgabenTypen = {"Designaufgabe","Programmieraufgabe","Einfachantwort", "MultipleChoiceaufgabe"};
        DDM = new JComboBox<>(AufgabenTypen);
        //Components Adden
        northPnl = new JPanel();
        northPnl.add(zurueckBtn);

        southPnl = new JPanel();
        southPnl.add(weiterBtn);

        centerPnl.add(DDM);
        this.add(centerPnl,BorderLayout.CENTER);
        this.add(northPnl,BorderLayout.NORTH);
        this.add(southPnl,BorderLayout.SOUTH);
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
            case "Designaufgabe":this.setVisible(false);
                        new AufgabeErstellenUmlView(this,doz);
                        break;
            case "Programmieraufgabe":this.setVisible(false);
                        new AufgabeErstellenCodeView(this,doz);
                        break;
            case "MultipleChoiceaufgabe":this.setVisible(false);
                        new AufgabeErstellenMultipleChoiceView(this,doz);
                        break;
            case "Einfachantwort": this.setVisible(false); ;
                        new AufgabeErstellenEinfachAntwortView(this,doz);
                        break;
            default:    this.setVisible(false);
        }
    }
    private void zurueck() {
        this.dispose();
        dozentAnsichtFrame.setVisible(true);
    }

    public static boolean inputcleaner(int bearbeitungsZeit, int punkte,Frame testFrame) {
        if(bearbeitungsZeit >= 60 || bearbeitungsZeit<=1 ||punkte >=100 || punkte <= 0){
            JOptionPane.showMessageDialog(testFrame,
                    "Bearbeitungszeit liegt nicht zwischen 60 und 1 Minuten. Oder die Punktezahl liegt nicht zwischen 100 oder 0",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        else{
            return true;
        }
    }
}
