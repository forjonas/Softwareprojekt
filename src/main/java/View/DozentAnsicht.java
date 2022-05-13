package View;

import View.AufgabenErstellen.AufgabeErstellenStartView;
import entity.benutzer.Dozent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Die Hauptansicht des Dozenten
 *
 * @author Jannik Oehme
 * @version 04.05.2022
 */
public class DozentAnsicht extends JFrame implements ActionListener {

    //Buttons
    private JButton testatEinsehenBtn;
    private JButton trainingsEinsehenBtn;
    private JButton testateErstellenBtn;
    private JButton testatuebersichtBtn;
    private JButton aufgabeErstellenBtn;
    private JButton aufgabenuebersichtBtn;
    private JButton trainingsDurchfuehrenBtn;
    private JButton testateDurchfuehrenBtn;
    //Panels
    private JPanel centerPnl;
    private JPanel dozentPnl;
    GridLayout gl = new GridLayout(2,4);
    Dozent doz;

    public static void main(String[] args)
    {
        new DozentAnsicht();
    }


    public DozentAnsicht(){
        this.setName("Home");
        fuelleDozentFrame();
        this.setMinimumSize(new Dimension(1500,900));
        this.setSize(1500,900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }
    public void fuelleDozentFrame(){
        doz = new Dozent();
        //Buttons
        testatEinsehenBtn = new JButton("Testat Einsehen");
        testatEinsehenBtn.addActionListener(this);

        trainingsEinsehenBtn = new JButton("Trainings Einsehen");
        trainingsEinsehenBtn.addActionListener(this);

        testateErstellenBtn = new JButton("Testate Erstellen");
        testateErstellenBtn.addActionListener(this);

        testatuebersichtBtn = new JButton("Testat Übersicht");
        testatuebersichtBtn.addActionListener(this);

        aufgabeErstellenBtn = new JButton("Aufgabe Erstellen");
        aufgabeErstellenBtn.addActionListener(this);

        aufgabenuebersichtBtn = new JButton("Aufgaben Übersicht");
        aufgabenuebersichtBtn.addActionListener(this);

        trainingsDurchfuehrenBtn = new JButton("Trainings Durchführen");
        trainingsDurchfuehrenBtn.addActionListener(this);

        testateDurchfuehrenBtn = new JButton("Testate Durchführen");
        testateDurchfuehrenBtn.addActionListener(this);
        //Panels
        dozentPnl = new JPanel(gl);
        centerPnl = new JPanel(gl);

        //Components Adden

        dozentPnl.add(testatEinsehenBtn);
        dozentPnl.add(testateDurchfuehrenBtn);
        dozentPnl.add(testatuebersichtBtn);
        dozentPnl.add(testateErstellenBtn);
        dozentPnl.add(trainingsDurchfuehrenBtn);
        dozentPnl.add(trainingsEinsehenBtn);
        dozentPnl.add(aufgabenuebersichtBtn);
        dozentPnl.add(aufgabeErstellenBtn);
        dozentPnl.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));

        this.add(dozentPnl);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.testatEinsehenBtn){
            testatEinsehen();
        }
        else if(e.getSource()==this.testateDurchfuehrenBtn){
            testateDurchfuehren();
        }
        else if(e.getSource()==this.trainingsDurchfuehrenBtn){
            trainingsDurchfuehren();
        }
        else if(e.getSource()==this.aufgabenuebersichtBtn){
            aufgabenuebersicht();
        }
        else if(e.getSource()==this.aufgabeErstellenBtn){
            aufgabeErstellen();
        }
        else if(e.getSource()==this.testatuebersichtBtn){
            testatuebersicht();
        }
        else if(e.getSource()==this.testateErstellenBtn){
            testateErstellen();
        }
        else if(e.getSource()==this.trainingsEinsehenBtn){
            trainingsEinsehen();
        }
    }
    private void trainingsEinsehen() {
        //Huh?
    }

    private void testateErstellen() {
        this.setVisible(false);
        //new TestatErstellenView(this);
    }

    private void testatuebersicht() {
        this.setVisible(false);
        //new TestatKatalogView(this);
    }

    private void aufgabeErstellen() {
        this.setVisible(false);
        new AufgabeErstellenStartView(this);
    }

    private void aufgabenuebersicht() {
        this.setVisible(false);
        //new AufgabenKatalogView(this);
    }

    private void trainingsDurchfuehren() {
        this.setVisible(false);
        new TrainingGenerierenView(this);
    }

    private void testateDurchfuehren() {
        this.setVisible(false);
       // new TestatKatalogView(this);
    }
    private void testatEinsehen(){
        this.setVisible(false);
       // new KorrigiereTestatKatalogView(this);
    }
}
