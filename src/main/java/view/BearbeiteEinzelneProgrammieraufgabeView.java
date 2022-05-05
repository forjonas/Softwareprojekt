package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BearbeiteEinzelneProgrammieraufgabeView extends JFrame {

	
		private JPanel contentPane;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						BearbeiteEinzelneProgrammieraufgabeView frame = new BearbeiteEinzelneProgrammieraufgabeView();
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
		public BearbeiteEinzelneProgrammieraufgabeView() {
			setTitle("Programmier Aufgabe");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 674, 435);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);

			JPanel panelNorth = new JPanel();
			contentPane.add(panelNorth, BorderLayout.NORTH);
			JLabel lblNewLabel1 = new JLabel("Frage: Wer ist der Erfinder des World Wide Webs? Schreiben Sie ihre Antwort auf!");
			panelNorth.add(lblNewLabel1);

			JLabel lblNewLabel_2 = new JLabel("Hier ist ein Bild");
			contentPane.add(lblNewLabel_2, BorderLayout.CENTER);


			JPanel panelWest = new JPanel();
			contentPane.add(panelWest, BorderLayout.WEST);
			JButton btnNewButton_3 = new JButton("Upload");
			panelWest.add(btnNewButton_3);


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

	