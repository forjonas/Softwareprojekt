package TestateK;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TestateK.Aufgabe;
import TestateK.Designaufgabe;
import TestateK.EinfachantwortAufgabe;
import TestateK.MultipleChoiceAufgabe;
import TestateK.Programmieraufgabe;
import TestateK.Testat;
import app.TestatApp;
import entity.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class TestateMainGui extends JFrame implements ActionListener {

	private JPanel contentPane;
	
	private JButton btnTestat1;
	private JButton btnTestat;
	private JButton btnTestat_2;
	private JButton btnTestat_3;
	private Testat tt1;
	//private ArrayList<Testat> testatliste;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Aufgabe a1 = new EinfachantwortAufgabe();
		Aufgabe a2 = new Designaufgabe();
		Aufgabe a3 = new Programmieraufgabe();
		Aufgabe a4 = new MultipleChoiceAufgabe();
		List<Aufgabe> aufgabenListe1 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4});
		List<Aufgabe> aufgabenListe2 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a2, a2, a3});
		List<Aufgabe> aufgabenListe3 = Arrays.asList(new Aufgabe[]{a1, a2, a3, a4, a4, a1, a2, a3});
		Testat t1 = new Testat(aufgabenListe1, null, "Hallo1234", "Sommertestat");
		Testat t2 = new Testat(aufgabenListe2, null, "asdf", "Wintertestat");
		Testat t3 = new Testat(aufgabenListe3, null, "qwertz", "Herbsttestat");
		List<Testat> testatliste1 = new ArrayList<Testat>();
		testatliste1.add(new Testat(t1, t2, t3, null));

		//Arrays.asList(new Testat[]{t1, t2, t3}); //(new Testat[]{t1, t2, t3, t1, t2, t3, t1, t2, t3, t1, t2, t3});


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestateMainGui frame = new TestateMainGui(testatliste1);// Liste mit Testaten(und Aufgaben) an Konstruktor TestateMainGui() uebergeben
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
	public TestateMainGui(ArrayList<Testat> tliste) { //Gui nimmnt TestatListe[4]

		this.tt1 = tliste.get(0); //Testat an der Stelle 0
		//this.testatliste = sliste;
		setTitle("");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Hier sehen Sie vier Testate");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		btnTestat1 = new JButton("Testat1");
		panel.add(btnTestat1);
		
		btnTestat = new JButton("Testat2");
		panel.add(btnTestat);
		
		btnTestat_2 = new JButton("Testat3");
		panel.add(btnTestat_2);
		
		btnTestat_3 = new JButton("Testat4");
		panel.add(btnTestat_3);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Abbrechen");
		panel_1.add(btnNewButton_1);
		
		
		 this.btnTestat1.addActionListener(this);
		 this.btnTestat.addActionListener(this);
		 this.btnTestat_2.addActionListener(this);
		 this.btnTestat_3.addActionListener(this);
				
		 super.pack();
		 
		 
		// Fenster mittig auf dem Display anzeigen lassen
			Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
			super.setLocation((display.getSize().width - super.getSize().width) / 2,
					(display.getSize().height - super.getSize().height) / 2);

			super.setVisible(true);
		
	}

	@Override
	 public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnTestat1) {
        	
        	
        	//oeffne die View mit dem 1. Testat1
        	//Testat testat = new Testat(aufgabenListe1, null, "1234", "Somtestat");//neues Testat
        	
        	//this.tt1 = testatliste.get(0); //Testat an der Stelle 0-->IN MAIN INITALISIERT
            TestatApp app = new TestatApp(tt1); //TestatApp Testat uebergeben
            app.zeigeAktuelleAufgabe(); //Die App soll die erste Aufgabe Zeigen usw. //!!! Butto wenn Testat 1 geklickt wird Testat App aufgerufen und die App zeigt testat1: Aufgabe1
            
        }
      
        if (e.getSource() == this.btnTestat) {
            JOptionPane.showMessageDialog(this, "Button btnTestat");
        }
        if (e.getSource() == this.btnTestat_2) {
            JOptionPane.showMessageDialog(this, "Button btnTestat_2");

        }
        if (e.getSource() == this.btnTestat_3) {
            JOptionPane.showMessageDialog(this, "Button btnTestat_3");

        }
	}
}
