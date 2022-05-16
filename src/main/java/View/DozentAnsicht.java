package View;

import View.AufgabenErstellen.AufgabeErstellenStartView;
import entity.benutzer.Dozent;
import persistence.DatabaseService;

import javax.swing.*;
import javax.swing.border.Border;
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
    private JButton abmeldenBtn;
    DatabaseService ds=DatabaseService.getInstance();
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
    private JPanel dozentMainPanel;
    GridLayout gl = new GridLayout(2,4);
    Dozent doz;

    public DozentAnsicht(Dozent doz){
        this.doz = doz;
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
        //JLabel
        JLabel welcomeMsgLbl = new JLabel("Willkommen "+doz.getVorname()+" "+doz.getNachname());
        //Buttons
        abmeldenBtn = new JButton("Abmelden");
        abmeldenBtn.addActionListener(this);

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
        centerPnl = new JPanel(gl);
        dozentMainPanel = new JPanel(new BorderLayout());

        //Components Adden
        centerPnl.add(testatEinsehenBtn);
        centerPnl.add(testateDurchfuehrenBtn);
        centerPnl.add(testatuebersichtBtn);
        centerPnl.add(testateErstellenBtn);
        centerPnl.add(trainingsDurchfuehrenBtn);
        centerPnl.add(trainingsEinsehenBtn);
        centerPnl.add(aufgabenuebersichtBtn);
        centerPnl.add(aufgabeErstellenBtn);
        centerPnl.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));
        dozentMainPanel.add(centerPnl, BorderLayout.CENTER);
        dozentMainPanel.add(abmeldenBtn, BorderLayout.SOUTH);
        dozentMainPanel.add(welcomeMsgLbl,BorderLayout.NORTH);

        this.add(dozentMainPanel);
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
        else if(e.getSource() == this.abmeldenBtn){
            abmelden();
    }
    }

    private void abmelden() {
        this.dispose();
        new LoginView();
    }

    private void trainingsEinsehen() {
        //Huh?
    }

    private void testateErstellen() {
        this.setVisible(false);
        new TestatErstellenView(this, doz);
    }

    private void testatuebersicht() {
        this.setVisible(false);
        new TestatKatalogView(this, doz);
    }

    private void aufgabeErstellen() {
        this.setVisible(false);
        new AufgabeErstellenStartView(this,doz);
    }

    private void aufgabenuebersicht() {
        this.setVisible(false);
        new AufgabenKatalogView(this, doz);
    }

    private void trainingsDurchfuehren() {
        this.setVisible(false);
        new TrainingGenerierenView(this, doz);
    }

    private void testateDurchfuehren() {
        this.setVisible(false);
        new TestatKatalogView(this, doz);
    }
    private void testatEinsehen(){
        this.setVisible(false);
       new KorrigiereTestatKatalogView(this, doz);
    }
}
