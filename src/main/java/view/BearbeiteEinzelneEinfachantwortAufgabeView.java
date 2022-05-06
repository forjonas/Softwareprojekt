package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BearbeiteEinzelneEinfachantwortAufgabeView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnBeendenEinzel;
	private JButton btnLoesungshinweisEinzel;
	private JButton btnAufgabeBeendenEinzel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BearbeiteEinzelneEinfachantwortAufgabeView frame = new BearbeiteEinzelneEinfachantwortAufgabeView();
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
	public BearbeiteEinzelneEinfachantwortAufgabeView() {
		setTitle("Einzelne Einfachantwort");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));


		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		JLabel lblNewLabel1 = new JLabel("Frage: Wer ist der Erfinder des World Wide Webs? Schreiben Sie ihre Antwort auf!");
		panelNorth.add(lblNewLabel1);


		JPanel panelCenter= new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		textArea = new JTextArea(18, 50);
		panelCenter.add(textArea);


		JPanel panelWest = new JPanel();
		contentPane.add(panelWest, BorderLayout.WEST);
		JLabel lblNewLabel_1 = new JLabel("Antwort:");
		panelWest.add(lblNewLabel_1);


		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(panelSouth, BorderLayout.SOUTH);


		btnBeendenEinzel = new JButton("Beenden");
		panelSouth.add(btnBeendenEinzel);
		btnLoesungshinweisEinzel = new JButton("Loesungshinweis");
		panelSouth.add(btnLoesungshinweisEinzel);
		btnAufgabeBeendenEinzel = new JButton("Aufgabe Beenden");
		panelSouth.add(btnAufgabeBeendenEinzel);

		this.btnBeendenEinzel.addActionListener(this);
		this.btnLoesungshinweisEinzel.addActionListener(this);
		this.btnAufgabeBeendenEinzel.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnBeendenEinzel) {
			JOptionPane.showMessageDialog(this,"Button Beenden");

		}
		if (e.getSource() == this.btnLoesungshinweisEinzel) {
			JOptionPane.showMessageDialog(this,"Button Loesungshinweis");
		}
		if (e.getSource() == this.btnAufgabeBeendenEinzel) {
			JOptionPane.showMessageDialog(this,"Button Aufgabe Beenden");
		}

	}
}
