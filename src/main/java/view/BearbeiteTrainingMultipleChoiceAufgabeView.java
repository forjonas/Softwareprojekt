package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BearbeiteTrainingMultipleChoiceAufgabeView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnBeendenTraining;
	private JButton btnLoesungshinweisTraining;
	private JButton btnVoherigeAufgabeTraining;
	private JButton btnNaechsteAufgabeTraining;
	private JButton btnTrainingBeenden;

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
		setTitle("Training Multiple Choice");
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

		btnBeendenTraining = new JButton("Beenden");
		panelSouth.add(btnBeendenTraining);
		btnLoesungshinweisTraining = new JButton("Loesungshinweis");
		panelSouth.add(btnLoesungshinweisTraining);
		btnVoherigeAufgabeTraining = new JButton("Vorherige Aufgabe");
		panelSouth.add(btnVoherigeAufgabeTraining);
		btnNaechsteAufgabeTraining = new JButton("N\u00E4chste Aufgabe");
		panelSouth.add(btnNaechsteAufgabeTraining);
		btnTrainingBeenden = new JButton("Training Beenden");
		panelSouth.add(btnTrainingBeenden);

		this.btnBeendenTraining.addActionListener(this);
		this.btnLoesungshinweisTraining.addActionListener(this);
		this.btnVoherigeAufgabeTraining.addActionListener(this);
		this.btnNaechsteAufgabeTraining.addActionListener(this);
		this.btnTrainingBeenden.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnBeendenTraining) {
			JOptionPane.showMessageDialog(this,"Button Beenden");

		}
		if (e.getSource() == this.btnLoesungshinweisTraining) {
			JOptionPane.showMessageDialog(this,"Button Loesungshinweis");
		}
		if (e.getSource() == this.btnVoherigeAufgabeTraining) {
			JOptionPane.showMessageDialog(this,"Button Vorherige");
		}
		if (e.getSource() == this.btnNaechsteAufgabeTraining) {
			JOptionPane.showMessageDialog(this,"Button Nächste");

		}
		if (e.getSource() == this.btnTrainingBeenden) {
			JOptionPane.showMessageDialog(this,"Button Beenden");

		}

	}
}
