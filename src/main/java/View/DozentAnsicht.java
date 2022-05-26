package View;

import View.aufgabenBearbeiten.einzelAufgaben.BearbeiteEinzelneAufgabeKatalogView;
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

    private JButton abmeldenBtn;
    private JButton testateBerwertenBtn;
    private JButton trainingsEinsehenBtn;
    private JButton testateErstellenBtn;
    private JButton testatuebersichtBtn;
    private JButton aufgabeErstellenBtn;
    private JButton aufgabenuebersichtBtn;
    private JButton aufgabeBearbeitenBtn;
    private JButton trainingsDurchfuehrenBtn;
    private JButton meineTestateBtn;
    private JPanel centerPnl;
    private JPanel dozentMainPanel;
    GridLayout gl = new GridLayout(3, 3);
    Dozent dozent;

    public DozentAnsicht(Dozent doz) {
        this.dozent = doz;
        this.setName("Home");
        fuelleDozentFrame();
        this.setMinimumSize(new Dimension(1500, 900));
        this.setSize(1500, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    public void fuelleDozentFrame() {
        JLabel welcomeMsgLbl = new JLabel("Willkommen " + dozent.getVorname() + " " + dozent.getNachname());

        abmeldenBtn = new JButton("Abmelden");
        abmeldenBtn.addActionListener(this);

        testateBerwertenBtn = new JButton("Testate bewerten");
        testateBerwertenBtn.addActionListener(this);

        trainingsEinsehenBtn = new JButton("Trainings einsehen");
        trainingsEinsehenBtn.addActionListener(this);

        testateErstellenBtn = new JButton("Testate erstellen");
        testateErstellenBtn.addActionListener(this);

        testatuebersichtBtn = new JButton("Testatübersicht");
        testatuebersichtBtn.addActionListener(this);

        aufgabeErstellenBtn = new JButton("Aufgabe erstellen");
        aufgabeErstellenBtn.addActionListener(this);

        aufgabenuebersichtBtn = new JButton("Aufgabenübersicht");
        aufgabenuebersichtBtn.addActionListener(this);

        aufgabeBearbeitenBtn = new JButton("Einzelne Aufgabe bearbeiten");
        aufgabeBearbeitenBtn.addActionListener(this);

        trainingsDurchfuehrenBtn = new JButton("Trainings durchführen");
        trainingsDurchfuehrenBtn.addActionListener(this);

        meineTestateBtn = new JButton("Meine Testate");
        meineTestateBtn.addActionListener(this);
        JPanel tempSouthPanel = new JPanel(new FlowLayout());

        centerPnl = new JPanel(gl);
        dozentMainPanel = new JPanel(new BorderLayout());

        centerPnl.add(testateBerwertenBtn);
        centerPnl.add(meineTestateBtn);
        centerPnl.add(testatuebersichtBtn);
        centerPnl.add(testateErstellenBtn);
        centerPnl.add(trainingsDurchfuehrenBtn);
        centerPnl.add(trainingsEinsehenBtn);
        centerPnl.add(aufgabenuebersichtBtn);
        centerPnl.add(aufgabeErstellenBtn);
        centerPnl.add(aufgabeBearbeitenBtn);
        tempSouthPanel.add(abmeldenBtn);
        centerPnl.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));
        dozentMainPanel.add(centerPnl, BorderLayout.CENTER);
        dozentMainPanel.add(tempSouthPanel, BorderLayout.SOUTH);
        dozentMainPanel.add(welcomeMsgLbl, BorderLayout.NORTH);

        this.add(dozentMainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.testateBerwertenBtn) {
            testatEinsehen();
        } else if (e.getSource() == this.meineTestateBtn) {
            testateDurchfuehren();
        } else if (e.getSource() == this.trainingsDurchfuehrenBtn) {
            trainingsDurchfuehren();
        } else if (e.getSource() == this.aufgabenuebersichtBtn) {
            aufgabenuebersicht();
        } else if (e.getSource() == this.aufgabeErstellenBtn) {
            aufgabeErstellen();
        } else if (e.getSource() == this.aufgabeBearbeitenBtn) {
            aufgabeBearbeiten();
        } else if (e.getSource() == this.testatuebersichtBtn) {
            testatuebersicht();
        } else if (e.getSource() == this.testateErstellenBtn) {
            testateErstellen();
        } else if (e.getSource() == this.trainingsEinsehenBtn) {
            trainingsEinsehen();
        } else if (e.getSource() == this.abmeldenBtn) {
            abmelden();
        }
    }

    private void abmelden() {
        this.dispose();
        new LoginView();
    }

    private void trainingsEinsehen() {
        this.setVisible(false);
        new EinsehenTrainingKatalogView(this, dozent);
    }

    private void testateErstellen() {
        this.setVisible(false);
        new TestatErstellenView(this, dozent);
    }

    private void testatuebersicht() {
        this.setVisible(false);
        new TestatKatalogView(this, dozent);
    }

    private void aufgabeErstellen() {
        this.setVisible(false);
        new AufgabeErstellenStartView(this, dozent);
    }

    private void aufgabeBearbeiten() {
        this.setVisible(false);
        new BearbeiteEinzelneAufgabeKatalogView(this, dozent);
    }

    private void aufgabenuebersicht() {
        this.setVisible(false);
        new AufgabenKatalogView(this, dozent);
    }

    private void trainingsDurchfuehren() {
        this.setVisible(false);
        new CreateFrageView(this, dozent);
    }

    private void testateDurchfuehren() {
        this.setVisible(false);
        new MeineTestateKatalogView(this, dozent);
    }

    private void testatEinsehen() {
        this.setVisible(false);
        new KorrigiereTestatKatalogView(this, dozent);
    }
}
