package View.AufgabenBearbeiten.Training;

import app.TestatApp;
import app.TrainingApp;
import entity.Designaufgabe;
import entity.MultipleChoiceAufgabe;
import entity.Programmieraufgabe;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BearbeiteTrainingProgrammieraufgabeView extends JFrame implements ActionListener {


	private JPanel contentPane;
	private JButton btnBeendenTraining;
	private JButton btnLoesungshinweisTraining;
	private JButton btnVoherigeAufgabeTraining;
	private JButton btnNaechsteAufgabeTraining;
	private JButton btnTrainingBeenden;
	private JButton btnUpload;
	ImageIcon icon = new ImageIcon ("C:\\BspSoftwareProjekt\\JavaCode.png");
	private TrainingApp trainingApp;
	private Programmieraufgabe aufgabe;  //Im Frame die Aufgabe
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BearbeiteTrainingProgrammieraufgabeView frame = new BearbeiteTrainingProgrammieraufgabeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BearbeiteTrainingProgrammieraufgabeView(TrainingApp trainingApp, Programmieraufgabe aufgabe) {
		this.aufgabe = aufgabe;
		setTitle(aufgabe.getName()); //Name der Aufgabe
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 435);
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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnBeendenTraining) {
			JOptionPane.showMessageDialog(this,"Button Beenden");

		}
		if (e.getSource() == this.btnLoesungshinweisTraining) {
			//JOptionPane.showMessageDialog(this,"Button Loesungshinweis");
			JOptionPane.showMessageDialog(this,aufgabe.getLoesungshinweis());
		}
		if (e.getSource() == this.btnVoherigeAufgabeTraining) {
			JOptionPane.showMessageDialog(this,"Button Vorherige");
		}
		if (e.getSource() == this.btnNaechsteAufgabeTraining) {//angepasst
			//Lese Datei und speicher diese in (Userlösung) //HIER NICHT????

			String textFieldValue = docUpload; //übergebe den docUpload vom Upload Button
			File DName = new File("AntwortAufgabe1.txt");
			fw = new FileWriter(DName);
			bw = new BufferedWriter(fw);
			bw.write(textFieldValue);  //bw schreibt txt Datei --> eig. Bild
			this.trainingApp.weiter(); //Waren z.b. bei Aufgabe 3 gehen weiter zu 4

		}
		if (e.getSource() == this.btnTrainingBeenden) {
			JOptionPane.showMessageDialog(this,"Button Beenden");

		}
		if (e.getSource() == this.btnUpload) {//angepasst
			//Lese Datei und speicher diese in (Userlösung)
			// Wenn ich auf Button klicke: öffne Dateifile *Ich wähle Bild aus*
			// lade das DocCode
			// String docUpload = textArea.getText(); // lese den input eig. Bild
			JOptionPane.showMessageDialog(this, "Upload Button");
		}

	}
}
