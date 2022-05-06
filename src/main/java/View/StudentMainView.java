package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMainView implements ActionListener {

    /**------Attribute------ */
    private JPanel studentPnl;


    public JFrame studentView;

    private JButton testateOeffnenBtn;
    private JButton ergebnisTestateBtn;
    private JButton generiereTrainingBtn;
    private JButton abmeldenBtn;

    /**------Attribute Ende------ */

    public StudentMainView()//ID für individuelle Angabe
    {
        studentView = new JFrame();
        studentPnl= new JPanel();
        studentPnl.setLayout(new BorderLayout());
        JLabel textLbl = new JLabel("Willkommen ");//+Benutzer.getStudentenID());
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
        studentView.add(studentPnl);

        studentView.setSize(600,600);
        studentView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
        studentView.setVisible(true);
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
            studentView.dispose();
            new LoginView();
        }
        else if(e.getSource() == this.generiereTrainingBtn){
            new TrainingGenerierenView(studentView);
            studentView.setVisible(false);
        }

    }
}
