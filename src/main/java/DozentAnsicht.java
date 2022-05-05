import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DozentAnsicht implements ActionListener {

    /**---------------------Buttons Hauptmenü--------------------**/
    private JButton testatEinsehenBtn;
    private JButton trainingsEinsehenBtn;
    private JButton testateErstellenBtn;
    private JButton testatuebersichtBtn;
    private JButton aufgabeErstellenBtn;
    private JButton aufgabenuebersichtBtn;
    private JButton trainingsDurchfuehrenBtn;
    private JButton testateDurchfuehrenBtn;
    /**----------------------------------------------------------**/
    private JPanel dozentPnl;
    private JFrame homeFrame ;

    public static void main(String[] args)
    {
        new DozentAnsicht();
    }

    public DozentAnsicht()
    {
        homeFrame = new JFrame("Home");
        fuelleDozentFrame();
        homeFrame.setSize(1000,250);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setVisible(true);
    }

    public void fuelleDozentFrame()
    {
        testatEinsehenBtn = new JButton("Testat Einsehen");
        testatEinsehenBtn.addActionListener(this);
        testatEinsehenBtn.setPreferredSize(new Dimension(160, 80));
        trainingsEinsehenBtn = new JButton("Trainings Einsehen");
        trainingsEinsehenBtn.addActionListener(this);
        trainingsEinsehenBtn.setPreferredSize(new Dimension(160, 80));
        testateErstellenBtn = new JButton("Testate Erstellen");
        testateErstellenBtn.addActionListener(this);
        testateErstellenBtn.setPreferredSize(new Dimension(160, 80));
        testatuebersichtBtn = new JButton("Testat Übersicht");
        testatuebersichtBtn.addActionListener(this);
        testatuebersichtBtn.setPreferredSize(new Dimension(160, 80));
        aufgabeErstellenBtn = new JButton("Aufgabe Erstellen");
        aufgabeErstellenBtn.addActionListener(this);
        aufgabeErstellenBtn.setPreferredSize(new Dimension(160, 80));
        aufgabenuebersichtBtn = new JButton("Aufgaben Übersicht");
        aufgabenuebersichtBtn.addActionListener(this);
        aufgabenuebersichtBtn.setPreferredSize(new Dimension(160, 80));
        trainingsDurchfuehrenBtn = new JButton("Trainings Durchführen");
        trainingsDurchfuehrenBtn.addActionListener(this);
        trainingsDurchfuehrenBtn.setPreferredSize(new Dimension(160, 80));
        testateDurchfuehrenBtn = new JButton("Testate Durchführen");
        testateDurchfuehrenBtn.addActionListener(this);
        testateDurchfuehrenBtn.setPreferredSize(new Dimension(160, 80));

        dozentPnl = new JPanel();
        JPanel North = new JPanel();
        JPanel Center = new JPanel();

        dozentPnl.setLayout(new BorderLayout());
        dozentPnl.setSize(700,700);

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

        homeFrame.add(dozentPnl);

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

    }

    private void testateErstellen() {
        System.out.println("b");
        //Hier Link zu der Main Deiner Klasse
    }

    private void testatuebersicht() {
        System.out.println("c");
        //Hier Link zu der Main Deiner Klasse
    }

    private void aufgabeErstellen() {
        homeFrame.dispose();
        AufgabeErstellenStartView.main(null);
    }

    private void aufgabenuebersicht() {
        System.out.println("e");
        //Hier Link zu der Main Deiner Klasse
    }

    private void trainingsDurchfuehren() {
        System.out.println("f");
        //Hier Link zu der Main Deiner Klasse
    }

    private void testateDurchfuehren() {
        System.out.println("g");
        //Hier Link zu der Main Deiner Klasse
    }
    private void testatEinsehen(){
        System.out.println("h");
        //Hier Link zu der Main Deiner Klasse
    }
}
