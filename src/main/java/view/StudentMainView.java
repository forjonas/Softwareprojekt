package view;

import view.aufgabenBearbeiten.einzelAufgaben.BearbeiteEinzelneAufgabeKatalogView;
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
    private Student student;
    private JPanel pnlStudent;
    private JButton btnMeinteTestate;
    private JButton btnDurchfuerenTraining;
    private JButton btnEinzelneAufgabe;
    private JButton btnAbmelden;

    /**
     * Konstruktor der Klasse StudentMainView, die ein Fenster und die ausgewählten Bausteine erstellt und verbindet.
     *
     * @param student Student, der sich in der EinloggenView angemeldet hat und in der Klasse gespeichert wird, damit bestimmte Funktionalitäten gewährleistet werden
     */
    public StudentMainView(Student student) {
        this.student = student;
        pnlStudent = new JPanel();
        pnlStudent.setLayout(new BorderLayout());

        JLabel textLbl = new JLabel("Willkommen " + student.getVorname() + " " + student.getNachname() + " Matrikelnummer:" + student.getMatrikelnummer());
        JPanel tempNorthPnl = new JPanel(new FlowLayout());
        JPanel tempCenterPnl = new JPanel(new FlowLayout());
        JPanel tempSouthPnl = new JPanel(new FlowLayout());

        btnDurchfuerenTraining = new JButton("Training durchführen");
        btnDurchfuerenTraining.addActionListener(this);
        btnDurchfuerenTraining.setPreferredSize(new Dimension(160, 80));
        btnMeinteTestate = new JButton("Meine Testate");
        btnMeinteTestate.addActionListener(this);
        btnMeinteTestate.setPreferredSize(new Dimension(160, 80));
        btnEinzelneAufgabe = new JButton("Einzelne Aufgabe bearbeiten");
        btnEinzelneAufgabe.addActionListener(this);
        btnEinzelneAufgabe.setPreferredSize(new Dimension(160, 80));

        btnAbmelden = new JButton("Abmelden");
        btnAbmelden.addActionListener(this);

        tempCenterPnl.add(btnDurchfuerenTraining);
        tempCenterPnl.add(btnMeinteTestate);
        tempCenterPnl.add(btnEinzelneAufgabe);
        tempNorthPnl.add(textLbl);
        tempSouthPnl.add(btnAbmelden);
        pnlStudent.add(tempNorthPnl, BorderLayout.NORTH);
        pnlStudent.add(tempCenterPnl, BorderLayout.CENTER);
        pnlStudent.add(tempSouthPnl, BorderLayout.SOUTH);
        this.add(pnlStudent);

        this.setSize(600, 600);
        this.setMinimumSize(this.getSize());
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
        if (e.getSource() == this.btnMeinteTestate) {
            new MeineTestateKatalogView(this, student);
            this.setVisible(false);
        } else if (e.getSource() == this.btnAbmelden) {
            this.dispose();
            new LoginView();
        } else if (e.getSource() == this.btnDurchfuerenTraining) {
            new GenerierenTrainingView(this, student);
            this.setVisible(false);
        } else if (e.getSource() == this.btnEinzelneAufgabe) {
            new BearbeiteEinzelneAufgabeKatalogView(this, student);
            this.setVisible(false);
        }
    }
}
