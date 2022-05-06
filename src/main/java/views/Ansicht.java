package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.JFrame;


public class Ansicht implements ActionListener {

    private JFrame homeFrame ;

    private JButton studentAnmeldenBtn;
    private JButton dozentAnmeldenBtn;
    private JButton adminAnmeldenBtn;
    private JButton zurueckGehenBtn;//erstmal nur immer zurück zum Hauptmenü
    private JButton trainingStartenBtn;
    private JButton testateOeffnenBtn;
    private JButton ergebnisTestateBtn;
    private JButton generiereTrainingBtn;
    private JButton zurueckStudentMenueBtn;

    private JPanel generalPnl;
    private JPanel studentPnl;
    private JPanel trainingAuswahlPnl;
    private JPanel testatAuswahlPnl;
    private JPanel testatErgebnissePnl;


    private JPanel adminPnl;
    private JPanel dozentPnl;



    public static void main(String[] args)
    {
        new Ansicht();
    }
    public Ansicht()
    {
        homeFrame = new JFrame("Home");
        homeFrame.getContentPane().add(generalPnl =new JPanel());

        studentAnmeldenBtn = new JButton("Student");
        studentAnmeldenBtn.addActionListener(this);
        studentAnmeldenBtn.setPreferredSize(new Dimension(160, 80));
        dozentAnmeldenBtn = new JButton("Dozent");
        dozentAnmeldenBtn.addActionListener(this);
        dozentAnmeldenBtn.setPreferredSize(new Dimension(160, 80));
        adminAnmeldenBtn = new JButton("Administrator");
        adminAnmeldenBtn.addActionListener(this);
        adminAnmeldenBtn.setPreferredSize(new Dimension(160, 80));
        zurueckGehenBtn = new JButton("Zurück zum Hauptmenü");
        zurueckGehenBtn.addActionListener(this);
        zurueckGehenBtn.setPreferredSize(new Dimension(60, 30));
        zurueckGehenBtn.setFont(new Zeichenstruktur().schriftKleinerButton());

        fuelleHomeFrame();

        homeFrame.setSize(600,600);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
        homeFrame.setVisible(true);
    }


    //Framelebensfüllung
    public void fuelleHomeFrame()
    {
        JPanel tempPanel=new JPanel();

        generalPnl.setLayout(new java.awt.BorderLayout());
        tempPanel.setLayout(new java.awt.GridLayout(1,3));


        tempPanel.add(studentAnmeldenBtn);
        tempPanel.add(dozentAnmeldenBtn);
        tempPanel.add(adminAnmeldenBtn);

        generalPnl.add(tempPanel, BorderLayout.CENTER);

        homeFrame.add(generalPnl);
    }



    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == this.studentAnmeldenBtn){
            anmeldungStudent();
        }
        else if(e.getSource() == this.dozentAnmeldenBtn){
            anmeldungDozent();
        }
        else if(e.getSource() == this.adminAnmeldenBtn){
            anmeldungAdmin();
        }
        else if(e.getSource() == this.zurueckGehenBtn){
            zurueckHauptmenue();
        }
        else if(e.getSource() == this.ergebnisTestateBtn){
            //dd
        }
        else if(e.getSource() == this.trainingStartenBtn){
            trainingStarten();
        }
        else if(e.getSource() == this.testateOeffnenBtn){
            //d
        }
        else if(e.getSource() == this.zurueckStudentMenueBtn){
            //d
        }
        else if(e.getSource() == this.generiereTrainingBtn){
            //d
        }

    }
}
