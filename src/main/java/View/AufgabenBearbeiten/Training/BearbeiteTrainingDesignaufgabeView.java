package View.AufgabenBearbeiten.Training;

import app.TrainingApp;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.loesung.userloesung.UserloesungDesignaufgabe;
import entity.loesung.userloesung.UserloesungEinfachantwort;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version2: 13.05.22
 * @version3: 16.05.22
 */
public class BearbeiteTrainingDesignaufgabeView extends JFrame implements ActionListener {


	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnAbbrechenTraining;
	private JButton btnLoesungshinweisTraining;
	private JButton btnVoherigeAufgabeTraining;
	private JButton btnNaechsteAufgabeTraining;
	private JButton btnTrainingBeenden;

	private TrainingApp trainingApp;
	private Designaufgabe aufgabe;  //Im Frame die Aufgabe
	private UserloesungEinfachantwort u1;


	public BearbeiteTrainingDesignaufgabeView(TrainingApp trainingApp, Designaufgabe aufgabe) {
		this.aufgabe = aufgabe;
		this.trainingApp = trainingApp;

		setTitle(aufgabe.getName()); //Name der Aufgabe
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);

		JLabel lblTextbeschreibung = new JLabel(aufgabe.getTextbeschreibung()); //Text mit Textbeschreibung
		panelNorth.add(lblTextbeschreibung);

		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		textArea = new JTextArea(18, 50);
		panelCenter.add(textArea);


		JPanel panelWest = new JPanel();
		contentPane.add(panelWest, BorderLayout.WEST);
		JLabel lblNewLabel_1 = new JLabel("Antwort:");
		panelWest.add(lblNewLabel_1);


		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(panelSouth, BorderLayout.SOUTH);

		btnAbbrechenTraining = new JButton("Abbrechen");
		panelSouth.add(btnAbbrechenTraining);
		btnLoesungshinweisTraining = new JButton("Loesungshinweis");
		panelSouth.add(btnLoesungshinweisTraining);
		btnVoherigeAufgabeTraining = new JButton("Vorherige Aufgabe");
		panelSouth.add(btnVoherigeAufgabeTraining);
		btnNaechsteAufgabeTraining = new JButton("Naechste Aufgabe");
		panelSouth.add(btnNaechsteAufgabeTraining);
		btnTrainingBeenden = new JButton("Training Beenden");
		panelSouth.add(btnTrainingBeenden);

		this.btnAbbrechenTraining.addActionListener(this);
		this.btnLoesungshinweisTraining.addActionListener(this);
		this.btnVoherigeAufgabeTraining.addActionListener(this);
		this.btnNaechsteAufgabeTraining.addActionListener(this);
		this.btnTrainingBeenden.addActionListener(this);

		super.pack();
		Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
		super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
		super.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnAbbrechenTraining) {
			JOptionPane.showMessageDialog(this, "Aufgaben werden nicht gespeichert");
			this.dispose();

		}
		if (e.getSource() == this.btnLoesungshinweisTraining) {
			JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen
		}
		if (e.getSource() == this.btnVoherigeAufgabeTraining) {
			trainingApp.zurueckTraining();
		}
		if (e.getSource() == this.btnNaechsteAufgabeTraining) {
			u1 = new UserloesungEinfachantwort();
			String u2 = textArea.getText();
			u1.setUserloesung(u2);
			trainingApp.usereingaben.add(u1); //antwort wird in UListe hinzugefügt und gehalten
			trainingApp.weiter(); //testatApp.testat
		}
		if (e.getSource() == this.btnTrainingBeenden) {
			JOptionPane.showMessageDialog(this, "Training ist abgeschickt");
			this.dispose();
			trainingApp.persistTraining();
			trainingApp.setUserFrameVisible(); //von Martin
		}
	}
}