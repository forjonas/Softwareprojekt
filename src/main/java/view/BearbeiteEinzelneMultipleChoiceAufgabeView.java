package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BearbeiteEinzelneMultipleChoiceAufgabeView extends JFrame implements ActionListener {

	private JPanel contentPane;
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
		setTitle("Einzelne Multiple Choice");
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
