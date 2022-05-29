package view;

import view.aufgabenErstellen.AufgabeErstellenStartView;
import view.aufgabenBearbeiten.einzelAufgaben.BearbeiteEinzelneAufgabeKatalogView;
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

    private JPanel pnlDozentMain;
    private JPanel pnlCenter;
    private JButton btnTestateBewerten;
    private JButton btnTrainingsEinsehen;
    private JButton btnTestateErstellen;
    private JButton btnTestatuebersicht;
    private JButton btnAufgabeErstellen;
    private JButton btnAufgabenuebersicht;
    private JButton btnAufgabeBearbeiten;
    private JButton btnTrainingsDurchfuehren;
    private JButton btnMeineTestate;
    private JButton btnAbmelden;
    GridLayout gridLayout = new GridLayout(3, 3);
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

        btnAbmelden = new JButton("Abmelden");
        btnAbmelden.addActionListener(this);

        btnTestateBewerten = new JButton("Testate bewerten");
        btnTestateBewerten.addActionListener(this);

        btnTrainingsEinsehen = new JButton("Trainings einsehen");
        btnTrainingsEinsehen.addActionListener(this);

        btnTestateErstellen = new JButton("Testate erstellen");
        btnTestateErstellen.addActionListener(this);

        btnTestatuebersicht = new JButton("Testatübersicht");
        btnTestatuebersicht.addActionListener(this);

        btnAufgabeErstellen = new JButton("Aufgabe erstellen");
        btnAufgabeErstellen.addActionListener(this);

        btnAufgabenuebersicht = new JButton("Aufgabenübersicht");
        btnAufgabenuebersicht.addActionListener(this);

        btnAufgabeBearbeiten = new JButton("Einzelne Aufgabe bearbeiten");
        btnAufgabeBearbeiten.addActionListener(this);

        btnTrainingsDurchfuehren = new JButton("Trainings durchführen");
        btnTrainingsDurchfuehren.addActionListener(this);

        btnMeineTestate = new JButton("Meine Testate");
        btnMeineTestate.addActionListener(this);
        JPanel tempSouthPanel = new JPanel(new FlowLayout());

        pnlCenter = new JPanel(gridLayout);
        pnlDozentMain = new JPanel(new BorderLayout());

        pnlCenter.add(btnTestateBewerten);
        pnlCenter.add(btnMeineTestate);
        pnlCenter.add(btnTestatuebersicht);
        pnlCenter.add(btnTestateErstellen);
        pnlCenter.add(btnTrainingsDurchfuehren);
        pnlCenter.add(btnTrainingsEinsehen);
        pnlCenter.add(btnAufgabenuebersicht);
        pnlCenter.add(btnAufgabeErstellen);
        pnlCenter.add(btnAufgabeBearbeiten);
        tempSouthPanel.add(btnAbmelden);
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2));
        pnlDozentMain.add(pnlCenter, BorderLayout.CENTER);
        pnlDozentMain.add(tempSouthPanel, BorderLayout.SOUTH);
        pnlDozentMain.add(welcomeMsgLbl, BorderLayout.NORTH);

        this.add(pnlDozentMain);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnTestateBewerten) {
            testatEinsehen();
        } else if (e.getSource() == this.btnMeineTestate) {
            testateDurchfuehren();
        } else if (e.getSource() == this.btnTrainingsDurchfuehren) {
            trainingsDurchfuehren();
        } else if (e.getSource() == this.btnAufgabenuebersicht) {
            aufgabenuebersicht();
        } else if (e.getSource() == this.btnAufgabeErstellen) {
            aufgabeErstellen();
        } else if (e.getSource() == this.btnAufgabeBearbeiten) {
            aufgabeBearbeiten();
        } else if (e.getSource() == this.btnTestatuebersicht) {
            testatuebersicht();
        } else if (e.getSource() == this.btnTestateErstellen) {
            testateErstellen();
        } else if (e.getSource() == this.btnTrainingsEinsehen) {
            trainingsEinsehen();
        } else if (e.getSource() == this.btnAbmelden) {
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
