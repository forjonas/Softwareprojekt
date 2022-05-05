package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BearbeiteEinzelneEinfachantwortAufgabeView extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;

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
		JButton btnNewButton3 = new JButton("Aufgabe Beenden");
		panelSouth.add(btnNewButton3);
		
	}
}
