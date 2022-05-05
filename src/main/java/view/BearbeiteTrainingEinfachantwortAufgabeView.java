package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class BearbeiteTrainingEinfachantwortAufgabeView extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BearbeiteTrainingEinfachantwortAufgabeView frame = new BearbeiteTrainingEinfachantwortAufgabeView();
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
	public BearbeiteTrainingEinfachantwortAufgabeView() {
		setTitle("Einfachantwort");
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
		JLabel lblNewLabel_1 = new JLabel("Antwort");
		panelWest.add(lblNewLabel_1);



		JPanel panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		JButton btnNewButton1 = new JButton("Beenden");
		panelSouth.add(btnNewButton1);
		JButton btnNewButton2 = new JButton("Loesungshinweis");
		panelSouth.add(btnNewButton2);
		JButton btnNewButton3 = new JButton("Vorherige Aufgabe");
		panelSouth.add(btnNewButton3);
		JButton btnNewButton4 = new JButton("N\u00E4chste Aufgabe");
		panelSouth.add(btnNewButton4);
		JButton btnNewButton5 = new JButton("Training Beenden");
		panelSouth.add(btnNewButton5);

	}
}
