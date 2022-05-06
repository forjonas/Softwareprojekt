package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BearbeiteTestatEinfachantwortAufgabeView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnBeendenTestat;
	private JButton btnLoesungshinweisTestat;
	private JButton btnVoherigeAufgabeTestat;
	private JButton btnNaechsteAufgabeTestat;
	private JButton btnTestatBeenden;



	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BearbeiteTestatEinfachantwortAufgabeView frame = new BearbeiteTestatEinfachantwortAufgabeView();
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
	public BearbeiteTestatEinfachantwortAufgabeView() {
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
		JLabel lblNewLabel_1 = new JLabel("Antwort:");
		panelWest.add(lblNewLabel_1);


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
