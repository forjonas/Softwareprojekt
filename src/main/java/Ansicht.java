import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.JFrame;


public class Ansicht implements ActionListener {

    public JFrame homeFrame ;

    public JButton studentAnmelden;
    public JButton dozentAnmelden;
    public JButton adminAnmelden;
    public JButton zurueckGehen;//erstmal nur immer zurück zum Hauptmenü
    public JButton trainingStarten;
    public JButton testateOeffnen;
    public JButton ergebnisTestate;

    JPanel generalPanel;
    JPanel studentPanel;
    JPanel adminPanel;
    JPanel dozentPanel;



    public static void main(String[] args)
    {
        new Ansicht();
    }
    public Ansicht()
    {
        homeFrame = new JFrame("Home");
        homeFrame.getContentPane().add(generalPanel=new JPanel());

        studentAnmelden = new JButton("Student");studentAnmelden.addActionListener(this);studentAnmelden.setPreferredSize(new Dimension(160, 80));
        dozentAnmelden = new JButton("Dozent");dozentAnmelden.addActionListener(this);dozentAnmelden.setPreferredSize(new Dimension(160, 80));
        adminAnmelden = new JButton("Administrator");adminAnmelden.addActionListener(this);adminAnmelden.setPreferredSize(new Dimension(160, 80));
        zurueckGehen = new JButton("Zurück zum Hauptmenü");zurueckGehen.addActionListener(this);zurueckGehen.setPreferredSize(new Dimension(60, 30));
        zurueckGehen.setFont(new Zeichenstruktur().schriftKleinerButton());

        fuelleHomeFrame();

        homeFrame.setSize(600,600);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
        homeFrame.setVisible(true);
    }


    //Framelebensfüllung
    public void fuelleHomeFrame()
    {
        JPanel tempPanel=new JPanel();

        generalPanel.setLayout(new java.awt.BorderLayout());
        tempPanel.setLayout(new java.awt.GridLayout(1,3));


        tempPanel.add(studentAnmelden);
        tempPanel.add(dozentAnmelden);
        tempPanel.add(adminAnmelden);

        generalPanel.add(tempPanel, BorderLayout.CENTER);

        homeFrame.add(generalPanel);
    }

    public void fuelleDozentFrame() {

    }

    public void fuelleAdminFrame() {

    }

    public void fuelleStudentFrame()
    {
        studentPanel.setLayout(new BorderLayout());
        JLabel text = new JLabel("Willkommen ");//+Benutzer.getStudentenID());
        JPanel tempNorth= new JPanel(new FlowLayout());
        JPanel tempCenter= new JPanel(new FlowLayout());


        //Drei Buttons laut Mock-Up
        trainingStarten = new JButton("Trainings");trainingStarten.addActionListener(this);trainingStarten.setPreferredSize(new Dimension(160,80));
        testateOeffnen = new JButton("Testate");testateOeffnen.addActionListener(this);testateOeffnen.setPreferredSize(new Dimension(160,80));
        ergebnisTestate = new JButton("Ergebnisse");ergebnisTestate.addActionListener(this);ergebnisTestate.setPreferredSize(new Dimension(160,80));

        tempCenter.add(trainingStarten);
        tempCenter.add(testateOeffnen);
        tempCenter.add(ergebnisTestate);
        tempNorth.add(zurueckGehen);
        tempNorth.add(text);
        studentPanel.add(tempNorth, BorderLayout.NORTH);
        studentPanel.add(tempCenter, BorderLayout.CENTER);
        homeFrame.add(studentPanel);
    }

    //Actions für Buttons
    public void anmeldungDozent()
    {

    }

    public void anmeldungAdmin()
    {

    }

    public void anmeldungStudent()
    {
        homeFrame.getContentPane().remove(generalPanel);
        homeFrame.getContentPane().add(studentPanel=new JPanel());
        homeFrame.getContentPane().revalidate(); //IMPORTANT
        homeFrame.getContentPane().repaint();    //IMPORTANT
        fuelleStudentFrame();
    }


    public void zurueckHauptmenue()
    {
        homeFrame.getContentPane().remove(studentPanel);
        homeFrame.getContentPane().add(generalPanel);
        homeFrame.getContentPane().revalidate(); //IMPORTANT
        homeFrame.getContentPane().repaint();    //IMPORTANT

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == this.studentAnmelden){
            anmeldungStudent();
        }
        else if(e.getSource() == this.dozentAnmelden){
            anmeldungDozent();
        }
        else if(e.getSource() == this.adminAnmelden){
            anmeldungAdmin();
        }
        else if(e.getSource() == this.zurueckGehen){
            zurueckHauptmenue();
        }
        else if(e.getSource() == this.ergebnisTestate) {
            //dd
        }
        else if(e.getSource() == this.trainingStarten){
            //d
        }
        else if(e.getSource() == this.testateOeffnen){
            //d
        }

    }
}
