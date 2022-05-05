package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JRadioButton;

public class BearbeiteEinzelneMultipleChoiceAufgabeView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BearbeiteEinzelneMultipleChoiceAufgabeView frame = new BearbeiteEinzelneMultipleChoiceAufgabeView();
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
	public BearbeiteEinzelneMultipleChoiceAufgabeView() {
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


		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Beenden");
		panel_3.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("L\u00F6sungshinweis");
		panel_3.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Aufgabe Beenden");
		panel_3.add(btnNewButton_2);
	}

}
