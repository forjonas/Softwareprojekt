package View;

import View.aufgabenBearbeiten.einzelAufgaben.BearbeiteEinzelneAufgabeKatalogView;
import entity.benutzer.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ansicht, in der ein Fenster für einen Studenten erstellt wird, wo der Student unterschiedliche Funktionen aufrufen kann.
 *
 * @author Martin Bergen
 * @version 23.05.2022
 */
public class StudentMainView extends JFrame implements ActionListener {

    /**
     * ------Attribute------
     */
    private JPanel studentPnl;
    private JButton meineTestateBtn;
    private JButton generiereTrainingBtn;
    private JButton einzelneAufgabeBtn;
    private JButton abmeldenBtn;
    private Student student;

    /**
     * Konstruktor der Klasse StudentMainView, die ein Fenster und die ausgewählten Bausteine erstellt und verbindet.
     *
     * @param student Student, der sich in der EinloggenView angemeldet hat und in der Klasse gespeichert wird, damit bestimmte Funktionalitäten gewährleistet werden
     */
    public StudentMainView(Student student) {
        this.student = student;
        studentPnl = new JPanel();
        studentPnl.setLayout(new BorderLayout());

        JLabel textLbl = new JLabel("Willkommen " + student.getVorname() + " " + student.getNachname() + " Matrikelnummer:" + student.getMatrikelnummer());
        JPanel tempNorthPnl = new JPanel(new FlowLayout());
        JPanel tempCenterPnl = new JPanel(new FlowLayout());
        JPanel tempSouthPnl = new JPanel(new FlowLayout());

        generiereTrainingBtn = new JButton("Training generieren");
        generiereTrainingBtn.addActionListener(this);
        generiereTrainingBtn.setPreferredSize(new Dimension(160, 80));
        meineTestateBtn = new JButton("Meine Testate");
        meineTestateBtn.addActionListener(this);
        meineTestateBtn.setPreferredSize(new Dimension(160, 80));
        einzelneAufgabeBtn = new JButton("Einzelne Aufgabe bearbeiten");
        einzelneAufgabeBtn.addActionListener(this);
        einzelneAufgabeBtn.setPreferredSize(new Dimension(160, 80));

        abmeldenBtn = new JButton("Abmelden");
        abmeldenBtn.addActionListener(this);
        abmeldenBtn.setPreferredSize(new Dimension(70, 30));
        abmeldenBtn.setFont(new Zeichenstruktur().schriftKleinerButton());

        tempCenterPnl.add(generiereTrainingBtn);
        tempCenterPnl.add(meineTestateBtn);
        tempCenterPnl.add(einzelneAufgabeBtn);
        tempNorthPnl.add(textLbl);
        tempSouthPnl.add(abmeldenBtn);
        studentPnl.add(tempNorthPnl, BorderLayout.NORTH);
        studentPnl.add(tempCenterPnl, BorderLayout.CENTER);
        studentPnl.add(tempSouthPnl, BorderLayout.SOUTH);
        this.add(studentPnl);

        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    /**
     * Überprüft ob einer der Buttons betätigt wurde und führt dementsprechend, die gesetze Methode aus
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.meineTestateBtn) {
            new MeineTestateKatalogView(this, student);
            this.setVisible(false);
        } else if (e.getSource() == this.abmeldenBtn) {
            this.dispose();
            new LoginView();
        } else if (e.getSource() == this.generiereTrainingBtn) {
            new CreateFrageView(this, student);
            this.setVisible(false);
        } else if (e.getSource() == this.einzelneAufgabeBtn) {
            new BearbeiteEinzelneAufgabeKatalogView(this, student);
            this.setVisible(false);
        }
    }
}
