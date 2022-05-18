package View.AufgabenBearbeiten.Training;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatKatalogView;
import app.TrainingApp;
import entity.aufgabe.Designaufgabe;
import entity.loesung.userloesung.UserloesungDesignaufgabe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 * @author Kristin Kubisch
 * @version: 10.05.22
 * @version2: 13.05.22
 * @version3: 16.05.22
 * @version4: 18.05.22
 */
public class BearbeiteTrainingDesignaufgabeView extends JFrame implements ActionListener {


	private JPanel contentPane;
	private JButton btnAbbrechenTraining;
	private JButton btnLoesungshinweisTraining;
	private JButton btnVoherigeAufgabeTraining;
	private JButton btnNaechsteAufgabeTraining;
	private JButton btnTrainingBeenden;

	private JButton btnUpload;

	private JTextArea textArea;

	private File geuploadet;

	ImageIcon icon = new ImageIcon ("C:\\BspSoftwareProjekt\\BspDiagram.jpg");

	private TrainingApp trainingApp;
	private Designaufgabe aufgabe;  //Im Frame die Aufgabe
	private UserloesungDesignaufgabe u1;

	/**
	 * Create the frame.
	 */
	public BearbeiteTrainingDesignaufgabeView(TrainingApp trainingApp, Designaufgabe aufgabe) {
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

		/**
		 JPanel panelCenter = new JPanel();
		 contentPane.add(panelCenter, BorderLayout.CENTER);
		 JLabel lblNewLabel_2 = new JLabel(); //Button daraus machen
		 panelCenter.add(lblNewLabel_2);
		 //Wenn auf Button-- Bild auswählen-- Bild anzeigen
		 */
		JPanel panelWest = new JPanel();
		contentPane.add(panelWest, BorderLayout.WEST);
		btnUpload = new JButton("Upload");
		panelWest.add(btnUpload);


		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(panelSouth, BorderLayout.SOUTH);

		btnAbbrechenTraining = new JButton("Abbrechen");
		panelSouth.add(btnAbbrechenTraining);
		btnLoesungshinweisTraining = new JButton("Loesungshinweis");
		panelSouth.add(btnLoesungshinweisTraining);
		btnVoherigeAufgabeTraining = new JButton("Vorherige Aufgabe");
		panelSouth.add(btnVoherigeAufgabeTraining);
		btnNaechsteAufgabeTraining = new JButton("N\u00E4chste Aufgabe");
		panelSouth.add(btnNaechsteAufgabeTraining);
		btnTrainingBeenden = new JButton("Testat Beenden");
		panelSouth.add(btnTrainingBeenden);

		this.btnAbbrechenTraining.addActionListener(this);
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
		if (e.getSource() == this.btnAbbrechenTraining) {
			trainingApp.printPersistenz();
			this.dispose();
			BearbeiteTestatKatalogView.main(null);//FÜR TRAINING NOCH ANPASSEN
		}
		if (e.getSource() == this.btnLoesungshinweisTraining) {
			JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis());//Lösungshinweis eingefügt
		}
		if (e.getSource() == this.btnVoherigeAufgabeTraining) {
			JOptionPane.showMessageDialog(this,"Button Vorherige");
			trainingApp.zurueckTrainig();

		}
		if (e.getSource() == this.btnNaechsteAufgabeTraining) {//angepasst
			u1 = new UserloesungDesignaufgabe();
			String u2 = textArea.getText();
			u1.setUserloesung(u2);
			trainingApp.usereingaben.add(u1); //antwort wird in UListe hinzugefügt und gehalten
			trainingApp.weiter(); //testatApp.testat

			/**
			 u1 = new UserloesungDesignaufgabe();
			 Icon u2 = icon.getImage();
			 u1.setUserloesung(u2);
			 testatApp.usereingaben.add(u1); //antwort wird in UListe hinzugefügt und gehalten
			 testatApp.weiter(); //testatApp.testat
			 */
		}
		if (e.getSource() == this.btnTrainingBeenden) {
			trainingApp.printPersistenz();
			this.dispose();
			// BearbeiteTestatKatalogView.main(null);
		}
		if (e.getSource() == this.btnUpload) {
			JOptionPane.showMessageDialog(this, "Upload Button");
		}

	}
}
