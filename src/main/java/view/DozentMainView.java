package view;

import view.aufgabenErstellen.ErstelleAufgabeStartView;
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
public class DozentMainView extends JFrame implements ActionListener {

    private JPanel pnlDozentMain;
    private JPanel pnlCenter;
    private JButton btnTestateKorrigieren;
    private JButton btnTrainingsEinsehen;
    private JButton btnTestatErstellen;
    private JButton btnTestatuebersicht;
    private JButton btnAufgabeErstellen;
    private JButton btnAufgabenuebersicht;
    private JButton btnAufgabeBearbeiten;
    private JButton btnTrainingDurchfuehren;
    private JButton btnMeineTestate;
    private JButton btnAbmelden;
    GridLayout gridLayout = new GridLayout(3, 3);
    Dozent dozent;
    /**
     * Konstruktor der Klasse, benötigt einen Dozenten.
     * Setzt Parameter des JFrames und ruft fuelleDozentFrame() auf.
     */
    public DozentMainView(Dozent doz) {
        this.dozent = doz;
        this.setName("Home");
        fuelleDozentFrame();
        this.setMinimumSize(new Dimension(1000, 600));
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }
    /**
     * Erstellt die Komponenten des JFrames sowie JPanels welche später eingefügt werden. Und fügt die Componenten in den Frame ein.
     */
    public void fuelleDozentFrame() {
        JLabel welcomeMsgLbl = new JLabel("Willkommen " + dozent.getVorname() + " " + dozent.getNachname());

        btnAbmelden = new JButton("Abmelden");
        btnAbmelden.addActionListener(this);

        btnTestateKorrigieren = new JButton("Testate korrigieren");
        btnTestateKorrigieren.addActionListener(this);
        btnTestateKorrigieren.setPreferredSize(new Dimension(160, 80));

        btnTrainingsEinsehen = new JButton("Trainings einsehen");
        btnTrainingsEinsehen.addActionListener(this);
        btnTrainingsEinsehen.setPreferredSize(new Dimension(160, 80));

        btnTestatErstellen = new JButton("Testat erstellen");
        btnTestatErstellen.addActionListener(this);
        btnTestatErstellen.setPreferredSize(new Dimension(160, 80));

        btnTestatuebersicht = new JButton("Testatübersicht");
        btnTestatuebersicht.addActionListener(this);
        btnTestatuebersicht.setPreferredSize(new Dimension(160, 80));

        btnAufgabeErstellen = new JButton("Aufgabe erstellen");
        btnAufgabeErstellen.addActionListener(this);
        btnAufgabeErstellen.setPreferredSize(new Dimension(160, 80));

        btnAufgabenuebersicht = new JButton("Aufgabenübersicht");
        btnAufgabenuebersicht.addActionListener(this);
        btnAufgabenuebersicht.setPreferredSize(new Dimension(160, 80));

        btnAufgabeBearbeiten = new JButton("Einzelne Aufgabe bearbeiten");
        btnAufgabeBearbeiten.addActionListener(this);
        btnAufgabeBearbeiten.setPreferredSize(new Dimension(160, 80));

        btnTrainingDurchfuehren = new JButton("Training durchführen");
        btnTrainingDurchfuehren.addActionListener(this);
        btnTrainingDurchfuehren.setPreferredSize(new Dimension(160, 80));

        btnMeineTestate = new JButton("Meine Testate");
        btnMeineTestate.addActionListener(this);
        btnMeineTestate.setPreferredSize(new Dimension(160, 80));
        JPanel tempSouthPanel = new JPanel(new FlowLayout());

        pnlCenter = new JPanel(gridLayout);
        pnlDozentMain = new JPanel(new BorderLayout());

        pnlCenter.add(btnTestateKorrigieren);
        pnlCenter.add(btnMeineTestate);
        pnlCenter.add(btnTestatuebersicht);
        pnlCenter.add(btnTestatErstellen);
        pnlCenter.add(btnTrainingDurchfuehren);
        pnlCenter.add(btnTrainingsEinsehen);
        pnlCenter.add(btnAufgabenuebersicht);
        pnlCenter.add(btnAufgabeErstellen);
        pnlCenter.add(btnAufgabeBearbeiten);
        tempSouthPanel.add(btnAbmelden);
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        pnlDozentMain.add(pnlCenter, BorderLayout.CENTER);
        pnlDozentMain.add(tempSouthPanel, BorderLayout.SOUTH);
        pnlDozentMain.add(welcomeMsgLbl, BorderLayout.NORTH);

        this.add(pnlDozentMain);
    }
    /**
     * Führt Aktionen der JButtons aus und ruft ggf. weitere Methoden auf.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnTestateKorrigieren) {
            testatEinsehen();
        } else if (e.getSource() == this.btnMeineTestate) {
            testateDurchfuehren();
        } else if (e.getSource() == this.btnTrainingDurchfuehren) {
            trainingsDurchfuehren();
        } else if (e.getSource() == this.btnAufgabenuebersicht) {
            aufgabenuebersicht();
        } else if (e.getSource() == this.btnAufgabeErstellen) {
            aufgabeErstellen();
        } else if (e.getSource() == this.btnAufgabeBearbeiten) {
            aufgabeBearbeiten();
        } else if (e.getSource() == this.btnTestatuebersicht) {
            testatuebersicht();
        } else if (e.getSource() == this.btnTestatErstellen) {
            testateErstellen();
        } else if (e.getSource() == this.btnTrainingsEinsehen) {
            trainingsEinsehen();
        } else if (e.getSource() == this.btnAbmelden) {
            abmelden();
        }
    }
    /**
     * Disposed diesen Frame und ruft die login view wieder auf.
     */
    private void abmelden() {
        this.dispose();
        new LoginView();
    }
    /**
     * Setzt diesen Frame auf invisible und ruft EinsehenTrainingKatalogView auf.
     */
    private void trainingsEinsehen() {
        this.setVisible(false);
        new EinsehenTrainingKatalogView(this, dozent);
    }
    /**
     * Setzt diesen Frame auf invisible und ruft TestatErstellenView auf.
     */
    private void testateErstellen() {
        this.setVisible(false);
        new TestatErstellenView(this, dozent);
    }
    /**
     * Setzt diesen Frame auf invisible und ruft testatuebersicht auf.
     */
    private void testatuebersicht() {
        this.setVisible(false);
        new TestatKatalogView(this, dozent);
    }
    /**
     * Setzt diesen Frame auf invisible und ruft ErstelleAufgabeStartView auf.
     */
    private void aufgabeErstellen() {
        this.setVisible(false);
        new ErstelleAufgabeStartView(this, dozent);
    }
    /**
     * Setzt diesen Frame auf invisible und ruft BearbeiteEinzelneAufgabeKatalogView auf.
     */
    private void aufgabeBearbeiten() {
        this.setVisible(false);
        new BearbeiteEinzelneAufgabeKatalogView(this, dozent);
    }
    /**
     * Setzt diesen Frame auf invisible und ruft AufgabenKatalogView auf.
     */
    private void aufgabenuebersicht() {
        this.setVisible(false);
        new AufgabenKatalogView(this, dozent);
    }
    /**
     * Setzt diesen Frame auf invisible und ruft GenerierenTrainingView auf.
     */
    private void trainingsDurchfuehren() {
        this.setVisible(false);
        new GenerierenTrainingView(this, dozent);
    }
    /**
     * Setzt diesen Frame auf invisible und ruft MeineTestateKatalogView auf.
     */
    private void testateDurchfuehren() {
        this.setVisible(false);
        new MeineTestateKatalogView(this, dozent);
    }
    /**
     * Setzt diesen Frame auf invisible und ruft KorrigiereTestatKatalogView auf.
     */
    private void testatEinsehen() {
        this.setVisible(false);
        new KorrigiereTestatKatalogView(this, dozent);
    }
}
