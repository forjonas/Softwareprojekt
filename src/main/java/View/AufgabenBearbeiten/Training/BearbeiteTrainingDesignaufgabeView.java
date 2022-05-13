package View.AufgabenBearbeiten.Training;

import View.AufgabenBearbeiten.Testat.BearbeiteTestatKatalogView;
import app.TestatApp;
import app.TrainingApp;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.Programmieraufgabe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Kristin Kubisch
 * @version 10.05.22
 */
public class BearbeiteTrainingDesignaufgabeView extends JFrame implements ActionListener {


	private JPanel contentPane;
	private JButton btnBeendenTraining;
	private JButton btnLoesungshinweisTraining;
	private JButton btnVoherigeAufgabeTraining;
	private JButton btnNaechsteAufgabeTraining;
	private JButton btnTrainingBeenden;
	private JButton btnUpload;
	ImageIcon icon = new ImageIcon ("C:\\BspSoftwareProjekt\\BspDiagram.jpg");

	private TrainingApp trainingApp;
	private Designaufgabe aufgabe;  //Im Frame die Aufgabe
	private String antwort;

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

		JLabel lblNewLabel_2 = new JLabel (icon);
		contentPane.add(lblNewLabel_2, BorderLayout.CENTER);

		JPanel panelWest = new JPanel();
		contentPane.add(panelWest, BorderLayout.WEST);
		btnUpload = new JButton("Upload");
		panelWest.add(btnUpload);


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
			trainingApp.printTest();
			this.dispose();
			BearbeiteTestatKatalogView.main(null);//FÜR TRAINING NOCH ANPASSEN
		}
		if (e.getSource() == this.btnLoesungshinweisTraining) {
			JOptionPane.showMessageDialog(this, aufgabe.getMusterloesung().getLoesungshinweis());//Lösungshinweis eingefügt
		}
		if (e.getSource() == this.btnVoherigeAufgabeTraining) {
			JOptionPane.showMessageDialog(this,"Button Vorherige");
		}
		if (e.getSource() == this.btnNaechsteAufgabeTraining) {//angepasst
			trainingApp.weiter();
		}
		if (e.getSource() == this.btnTrainingBeenden) {
			trainingApp.printTest();
			this.dispose();
			BearbeiteTestatKatalogView.main(null);//FÜR TRAINING NOCH ANPASSEN
		}
		if (e.getSource() == this.btnUpload) {
			JOptionPane.showMessageDialog(this, "Upload Button");
		}

	}
}
