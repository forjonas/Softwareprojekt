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
    private JPanel North;
    private JPanel Center;
    private JPanel dozentPnl;
    private Dozent dozent;

    public DozentAnsicht(Dozent dozent){
        this.dozent=dozent;
        this.setName("Home");
        fuelleDozentFrame();
        this.setSize(1000,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void fuelleDozentFrame(){
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
        dozentPnl = new JPanel();
        North = new JPanel();
        Center = new JPanel();

        dozentPnl.setLayout(new BorderLayout());
        dozentPnl.setSize(700,700);
        //Components Adden
        North.add(testatEinsehenBtn);
        North.add(testateDurchfuehrenBtn);
        North.add(testatuebersichtBtn);
        North.add(testateErstellenBtn);

        Center.add(trainingsDurchfuehrenBtn);
        Center.add(trainingsEinsehenBtn);
        Center.add(aufgabenuebersichtBtn);
        Center.add(aufgabeErstellenBtn);

        dozentPnl.add(North,BorderLayout.NORTH);
        dozentPnl.add(Center,BorderLayout.CENTER);

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
        this.dispose();
        TestatErstellenView.main(null);
    }

    private void testatuebersicht() {
        this.dispose();
        TestatKatalogView.main(null);
    }

    private void aufgabeErstellen() {
        this.dispose();
        new AufgabeErstellenStartView(this);
    }

    private void aufgabenuebersicht() {
        this.dispose();
        AufgabenKatalogView.main(null);
    }

    private void trainingsDurchfuehren() {
        this.dispose();
        new TrainingGenerierenView(this);
    }

    private void testateDurchfuehren() {
        this.dispose();
        TestatKatalogView.main(null);
    }
    private void testatEinsehen(){
        this.dispose();
        KorrigiereTestatKatalogView.main(null);
    }
}
