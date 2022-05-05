package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BearbeiteTrainingProgrammieraufgabeView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BearbeiteTrainingProgrammieraufgabeView frame = new BearbeiteTrainingProgrammieraufgabeView();
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
	public BearbeiteTrainingProgrammieraufgabeView() {
		setTitle("Programmier Aufgabe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Beenden");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("L\u00F6sungshinweis");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Vorherige Aufgabe");
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("N\u00E4chste Aufgabe");
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("Traning Beenden");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton_5);
		
				
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Aufgabentext");
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Programmier Aufgabe");
		contentPane.add(lblNewLabel_1, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("Hier ist ein Bild");
		contentPane.add(lblNewLabel_2, BorderLayout.CENTER);
		
		JButton btnNewButton_4 = new JButton("Datei Hochladen");
		contentPane.add(btnNewButton_4, BorderLayout.EAST);
	}

}
