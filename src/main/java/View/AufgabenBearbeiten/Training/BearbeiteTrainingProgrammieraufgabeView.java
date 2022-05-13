package View.AufgabenBearbeiten.Training;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatKatalogView;
import app.TestatApp;
import app.TrainingApp;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.MultipleChoiceAufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.aufgabensammlung.TestatBearbeitung;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BearbeiteTrainingProgrammieraufgabeView extends JFrame implements ActionListener {


	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnBeendenTraining;
	private JButton btnLoesungshinweisTraining;
	private JButton btnVoherigeAufgabeTraining;
	private JButton btnNaechsteAufgabeTraining;
	private JButton btnTrainingBeenden;
	private JButton btnUpload;

	private TrainingApp trainingApp;
	private Programmieraufgabe aufgabe;
	private String antwort;


	/**
	 * Create the frame.
	 */
	public BearbeiteTrainingProgrammieraufgabeView(TrainingApp trainingApp, Programmieraufgabe aufgabe) {
		this.aufgabe = aufgabe;
		this.trainingApp = trainingApp;

		setTitle(aufgabe.getName()); //Name der Aufgabe
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 674, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		JLabel lblNewLabel1 = new JLabel(aufgabe.getTextbeschreibung()); //Text mit Textbeschreibung//angepasst
		panelNorth.add(lblNewLabel1);

		/**
		 * Optionales Bild hinzufügen
		 */

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

		btnBeendenTraining = new JButton("Beenden");
		panelSouth.add(btnBeendenTraining);
		btnLoesungshinweisTraining = new JButton("Loesungshinweis");
		panelSouth.add(btnLoesungshinweisTraining);
		btnVoherigeAufgabeTraining = new JButton("Vorherige Aufgabe");
		panelSouth.add(btnVoherigeAufgabeTraining);
		btnNaechsteAufgabeTraining = new JButton("N\u00E4chste Aufgabe");
		panelSouth.add(btnNaechsteAufgabeTraining);
		btnTrainingBeenden = new JButton("Testat Beenden");
		panelSouth.add(btnTrainingBeenden);

		this.btnBeendenTraining.addActionListener(this);
		this.btnLoesungshinweisTraining.addActionListener(this);
		this.btnVoherigeAufgabeTraining.addActionListener(this);
		this.btnNaechsteAufgabeTraining.addActionListener(this);
		this.btnTrainingBeenden.addActionListener(this);
		this.btnUpload.addActionListener(this);

		super.pack();
		Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
		super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnBeendenTraining) {
			this.dispose();
			BearbeiteTestatKatalogView.main(null);

		}
		if (e.getSource() == this.btnLoesungshinweisTraining) {
			JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen
		}
		if (e.getSource() == this.btnVoherigeAufgabeTraining) {
			JOptionPane.showMessageDialog(this, "Button Vorherige");
		}
		if (e.getSource() == this.btnNaechsteAufgabeTraining) {

			antwort = textArea.getText();
			trainingApp.usereingaben.add(antwort); //antwort wird in Liste hinzugefügt und gehalten
			trainingApp.weiter(); //testatApp.testat
			/**
			 * speichern in testatApp und am Ende Testat an TestatBearbeiten übergenen --> erstellen und persetieren
			 */
			this.trainingApp.weiter();

		}
		if (e.getSource() == this.btnTrainingBeenden) {
			this.dispose();
			trainingApp.printTest();
			BearbeiteTestatKatalogView.main(null);

		}

	}
}
