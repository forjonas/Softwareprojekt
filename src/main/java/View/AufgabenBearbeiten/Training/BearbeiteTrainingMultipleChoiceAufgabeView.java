package View.AufgabenBearbeiten.Training;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatKatalogView;
import app.TestatApp;
import app.TrainingApp;
import entity.aufgabe.MultipleChoiceAufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.aufgabe.MultipleChoiceAufgabe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BearbeiteTrainingMultipleChoiceAufgabeView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnBeendenTraining;
	private JButton btnLoesungshinweisTraining;
	private JButton btnVoherigeAufgabeTraining;
	private JButton btnNaechsteAufgabeTraining;
	private JButton btnTrainingBeenden;


	private TrainingApp trainingApp;
	private MultipleChoiceAufgabe aufgabe; //Im Frame die Aufgabe
	private String antwort1;
	private String antwort2;
	private String antwort3;
	private String antwort4;

	/**
	 * Create the frame.
	 */
	public BearbeiteTrainingMultipleChoiceAufgabeView(TrainingApp trainingApp, MultipleChoiceAufgabe aufgabe) {

		this.aufgabe = aufgabe;
		this.trainingApp = trainingApp;

		setTitle(aufgabe.getName()); //Name der Aufgabe);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 674, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		JLabel lblTextbeschreibung = new JLabel(aufgabe.getTextbeschreibung()); //Text mit Textbeschreibung
		panelNorth.add(lblTextbeschreibung);

		/**
		 * Optionales Bild hinzufügen
		 */

		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);

		antwort1 = aufgabe.getAntwortmoeglichkeiten().get((0));
		JRadioButton button1 = new JRadioButton(antwort1);
		panelCenter.add(button1);

		antwort2 = aufgabe.getAntwortmoeglichkeiten().get((1));
		JRadioButton button2 = new JRadioButton(antwort2);
		panelCenter.add(button2);

		antwort3 = aufgabe.getAntwortmoeglichkeiten().get((2));
		JRadioButton button3 = new JRadioButton(antwort3);
		panelCenter.add(button3);

		antwort4 = aufgabe.getAntwortmoeglichkeiten().get((3));
		JRadioButton button4 = new JRadioButton(antwort4);
		panelCenter.add(button4);

		ButtonGroup bg = new ButtonGroup();
		bg.add(button1);
		bg.add(button2);
		bg.add(button3);
		bg.add(button4);

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
		if (e.getSource() == this.btnTrainingBeenden) {
			this.dispose();
			trainingApp.printTest();
			BearbeiteTestatKatalogView.main(null);

		}
		if (e.getSource() == this.btnVoherigeAufgabeTraining) {
			JOptionPane.showMessageDialog(this, "Button Vorherige");

		}
		if (e.getSource() == this.btnNaechsteAufgabeTraining) {

			//antwort = textArea.getText();
			//testatApp.usereingaben.add(antwort); //antwort wird in Liste hinzugefügt und gehalten
			trainingApp.weiter(); //testatApp.testat
			/**
			 * speichern in testatApp und am Ende Testat an TestatBearbeiten übergenen --> erstellen und persetieren
			 */

		}
	}
}
