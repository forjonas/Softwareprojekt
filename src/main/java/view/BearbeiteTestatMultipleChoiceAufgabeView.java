package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BearbeiteTestatMultipleChoiceAufgabeView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnBeendenTestat;
	private JButton btnLoesungshinweisTestat;
	private JButton btnVoherigeAufgabeTestat;
	private JButton btnNaechsteAufgabeTestat;
	private JButton btnTestatBeenden;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BearbeiteTestatMultipleChoiceAufgabeView frame = new BearbeiteTestatMultipleChoiceAufgabeView();
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
	public BearbeiteTestatMultipleChoiceAufgabeView() {
		setTitle("Testat Multiple Choice");
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


		btnBeendenTestat = new JButton("Beenden");
		panelSouth.add(btnBeendenTestat);
		btnLoesungshinweisTestat = new JButton("Loesungshinweis");
		panelSouth.add(btnLoesungshinweisTestat);
		btnVoherigeAufgabeTestat = new JButton("Vorherige Aufgabe");
		panelSouth.add(btnVoherigeAufgabeTestat);
		btnNaechsteAufgabeTestat = new JButton("N\u00E4chste Aufgabe");
		panelSouth.add(btnNaechsteAufgabeTestat);
		btnTestatBeenden = new JButton("Testat Beenden");
		panelSouth.add(btnTestatBeenden);

		this.btnBeendenTestat.addActionListener(this);
		this.btnLoesungshinweisTestat.addActionListener(this);
		this.btnVoherigeAufgabeTestat.addActionListener(this);
		this.btnNaechsteAufgabeTestat.addActionListener(this);
		this.btnTestatBeenden.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnBeendenTestat) {
			JOptionPane.showMessageDialog(this,"Button Beenden");

		}
		if (e.getSource() == this.btnLoesungshinweisTestat) {
			JOptionPane.showMessageDialog(this,"Button Loesungshinweis");
		}
		if (e.getSource() == this.btnTestatBeenden) {
			JOptionPane.showMessageDialog(this,"Button Testat Beenden");
		}
		if (e.getSource() == this.btnVoherigeAufgabeTestat) {
			JOptionPane.showMessageDialog(this,"Button Vorherige");

		}
		if (e.getSource() == this.btnNaechsteAufgabeTestat) {
			JOptionPane.showMessageDialog(this,"Button Nächste");

		}

	}
}
