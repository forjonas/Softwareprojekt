package View;

import entity.benutzer.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMainView extends JFrame implements ActionListener {

    /**------Attribute------ */
    private JPanel studentPnl;
    private JButton testateOeffnenBtn;
    private JButton ergebnisTestateBtn;
    private JButton generiereTrainingBtn;
    private JButton abmeldenBtn;

    /**------Attribute Ende------ */

    public static void main(String[] args)
    {new StudentMainView(new Student());}

    public StudentMainView(Student student)//ID für individuelle Angabe
    {
        studentPnl= new JPanel();
        studentPnl.setLayout(new BorderLayout());
        JLabel textLbl = new JLabel("Willkommen "+student.getVorname()+" "+student.getNachname());//+Benutzer.getStudentenID());
        JPanel tempNorthPnl= new JPanel(new FlowLayout());
        JPanel tempCenterPnl= new JPanel(new FlowLayout());
        JPanel tempSouthPnl= new JPanel(new FlowLayout());


        //Drei Buttons laut Mock-Up
        generiereTrainingBtn = new JButton("Trainings");
        generiereTrainingBtn.addActionListener(this);
        generiereTrainingBtn.setPreferredSize(new Dimension(160,80));
        testateOeffnenBtn = new JButton("Testate");
        testateOeffnenBtn.addActionListener(this);
        testateOeffnenBtn.setPreferredSize(new Dimension(160,80));
        ergebnisTestateBtn = new JButton("Ergebnisse");
        ergebnisTestateBtn.addActionListener(this);
        ergebnisTestateBtn.setPreferredSize(new Dimension(160,80));
        abmeldenBtn=new JButton("Abmelden");
        abmeldenBtn.addActionListener(this);
        abmeldenBtn.setPreferredSize(new Dimension(70,30));
        abmeldenBtn.setFont(new Zeichenstruktur().schriftKleinerButton());


        tempCenterPnl.add(generiereTrainingBtn);
        tempCenterPnl.add(testateOeffnenBtn);
        tempCenterPnl.add(ergebnisTestateBtn);
        tempNorthPnl.add(textLbl);
        tempSouthPnl.add(abmeldenBtn);
        studentPnl.add(tempNorthPnl, BorderLayout.NORTH);
        studentPnl.add(tempCenterPnl, BorderLayout.CENTER);
        studentPnl.add(tempSouthPnl, BorderLayout.SOUTH);
        this.add(studentPnl);

        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == this.ergebnisTestateBtn){
            //dd
        }
        else if(e.getSource() == this.testateOeffnenBtn){
            //d
        }
        else if(e.getSource() == this.abmeldenBtn){
            this.dispose();
            new LoginView();
        }
        else if(e.getSource() == this.generiereTrainingBtn){
            new TrainingGenerierenView(this);
            this.setVisible(false);
        }

    }
}
