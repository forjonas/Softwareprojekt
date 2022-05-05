package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class BearbeiteTrainingMultipleChoiceAufgabeView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BearbeiteTrainingMultipleChoiceAufgabeView frame = new BearbeiteTrainingMultipleChoiceAufgabeView();
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
	public BearbeiteTrainingMultipleChoiceAufgabeView() {
		setTitle("Multiple Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		JLabel lblNewLabel = new JLabel("Frage: Wer ist der Erfinder des World Wide Webs?");
		panelNorth.add(lblNewLabel);
		
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		
		JRadioButton button1 = new JRadioButton("Elon Musk");
		panelCenter.add(button1);
		JRadioButton button2 = new JRadioButton("Logan Murphy");
		panelCenter.add(button2);
		JRadioButton button3 = new JRadioButton("Tim Bernes Lee");
		panelCenter.add(button3);
		JRadioButton button4 = new JRadioButton(":)");
		panelCenter.add(button4);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(button1);
		bg.add(button2);
		bg.add(button3);
		bg.add(button4);
		
		
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
