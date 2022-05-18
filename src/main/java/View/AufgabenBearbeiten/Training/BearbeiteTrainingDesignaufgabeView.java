package View.AufgabenBearbeiten.Training;

import app.TrainingController;
import entity.aufgabe.Designaufgabe;
import entity.loesung.userloesung.UserloesungDesignaufgabe;
import entity.loesung.userloesung.UserloesungEinfachantwort;

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
 */
public class BearbeiteTrainingDesignaufgabeView extends JFrame implements ActionListener {


	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnAbbrechenTraining;
	private JButton btnLoesungshinweisTraining;
	private JButton btnVoherigeAufgabeTraining;
	private JButton btnNaechsteAufgabeTraining;
	private JButton btnTrainingBeenden;
	private JButton btnUpload;
	private boolean hinweisVerwendet;
	private File geuploadet;
	ImageIcon icon = new ImageIcon ("C:\\BspSoftwareProjekt\\BspDiagram.jpg");
	private TrainingController trainingController;
	private Designaufgabe aufgabe;  //Im Frame die Aufgabe
	private UserloesungDesignaufgabe userloesung;


	public BearbeiteTrainingDesignaufgabeView(TrainingController trainingController, Designaufgabe aufgabe) {
		this.hinweisVerwendet = false;
		this.aufgabe = aufgabe;
		this.trainingController = trainingController;

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
		btnNaechsteAufgabeTraining = new JButton("Nächste Aufgabe");
		panelSouth.add(btnNaechsteAufgabeTraining);
		btnTrainingBeenden = new JButton("Training Beenden");
		panelSouth.add(btnTrainingBeenden);

		this.btnAbbrechenTraining.addActionListener(this);
		this.btnLoesungshinweisTraining.addActionListener(this);
		this.btnVoherigeAufgabeTraining.addActionListener(this);
		this.btnNaechsteAufgabeTraining.addActionListener(this);
		this.btnTrainingBeenden.addActionListener(this);
		//this.btnUpload.addActionListener(this);

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
			trainingController.setUserFrameVisible();
		}
		if (e.getSource() == this.btnLoesungshinweisTraining) {
			if (aufgabe.getMusterloesung().getLoesungshinweis() != null) {
				JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis()); //Lösungshinweis bekommen//
				hinweisVerwendet = true;
			} else {
				JOptionPane.showMessageDialog(this, "Kein Lösungshinweis vorhanden.", "Lösungshinweis", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (e.getSource() == this.btnVoherigeAufgabeTraining) {
			if(trainingController.isIndexNotFirst()) {
				trainingController.zurueckTraining();
				this.dispose();
			} else {
				trainingController.zurueckTraining();
			}
		}
		if (e.getSource() == this.btnNaechsteAufgabeTraining) {
			String userloesungString = textArea.getText();
			userloesung = new UserloesungDesignaufgabe(aufgabe, hinweisVerwendet, userloesungString, trainingController.getAktuellerBenutzer(), trainingController.getTraining());
			trainingController.addUserloesung(userloesung);
			if(trainingController.isIndexNotLast()) {
				trainingController.weiter();
				this.dispose();
			} else {
				trainingController.weiter();
			}
			/**
			 u1 = new UserloesungDesignaufgabe();
			 Icon u2 = icon.getImage();
			 u1.setUserloesung(u2);
			 testatApp.usereingaben.add(u1); //antwort wird in UListe hinzugefügt und gehalten
			 testatApp.weiter(); //testatApp.testat
			 */
		}
		if (e.getSource() == this.btnTrainingBeenden) {
			JOptionPane.showMessageDialog(this, "Training ist abgeschickt");
			trainingController.persistTraining();
			this.dispose();
		}
		if (e.getSource() == this.btnUpload) {
			JOptionPane.showMessageDialog(this, "Upload Button");
		}

	}
}
