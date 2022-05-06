package View.AufgabenBearbeiten.Testat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BearbeiteTestatProgrammieraufgabeView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnBeendenTestst;
	private JButton btnLoesungshinweisTestat;
	private JButton btnVoherigeAufgabeTestat;
	private JButton btnNaechsteAufgabeTestat;
	private JButton btnTestatBeenden;
	private JButton btnUpload;
	ImageIcon icon = new ImageIcon ("C:\\BspSoftwareProjekt\\JavaCode.png");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BearbeiteTestatProgrammieraufgabeView frame = new BearbeiteTestatProgrammieraufgabeView();
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
	public BearbeiteTestatProgrammieraufgabeView() {
		setTitle("Testat Programmier Aufgabe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		JLabel lblNewLabel1 = new JLabel("Aufgabe: programmieren Sie den Code in eclipse!");
		panelNorth.add(lblNewLabel1);

		JLabel lblNewLabel_2 = new JLabel (icon);
		contentPane.add(lblNewLabel_2, BorderLayout.CENTER);

		JPanel panelWest = new JPanel();
		contentPane.add(panelWest, BorderLayout.WEST);
		btnUpload = new JButton("Upload");
		panelWest.add(btnUpload);


		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(panelSouth, BorderLayout.SOUTH);

		btnBeendenTestst = new JButton("Beenden");
		panelSouth.add(btnBeendenTestst);
		btnLoesungshinweisTestat = new JButton("Loesungshinweis");
		panelSouth.add(btnLoesungshinweisTestat);
		btnVoherigeAufgabeTestat = new JButton("Vorherige Aufgabe");
		panelSouth.add(btnVoherigeAufgabeTestat);
		btnNaechsteAufgabeTestat = new JButton("N\u00E4chste Aufgabe");
		panelSouth.add(btnNaechsteAufgabeTestat);
		btnTestatBeenden = new JButton("Testat Beenden");
		panelSouth.add(btnTestatBeenden);

		this.btnBeendenTestst.addActionListener(this);
		this.btnLoesungshinweisTestat.addActionListener(this);
		this.btnVoherigeAufgabeTestat.addActionListener(this);
		this.btnNaechsteAufgabeTestat.addActionListener(this);
		this.btnTestatBeenden.addActionListener(this);
		this.btnUpload.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnBeendenTestst) {
			JOptionPane.showMessageDialog(this,"Button Beenden");

		}
		if (e.getSource() == this.btnLoesungshinweisTestat) {
			JOptionPane.showMessageDialog(this,"Button Loesungshinweis");
		}
		if (e.getSource() == this.btnVoherigeAufgabeTestat) {
			JOptionPane.showMessageDialog(this,"Button Vorherige");
		}
		if (e.getSource() == this.btnNaechsteAufgabeTestat) {
			JOptionPane.showMessageDialog(this,"Button Nächste");

		}
		if (e.getSource() == this.btnTestatBeenden) {
			JOptionPane.showMessageDialog(this,"Button Testat Beenden");

		}
		if (e.getSource() == this.btnUpload) {
			JOptionPane.showMessageDialog(this,"Upload Button");
		}

	}
}
