import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.JFrame;


public class Ansicht implements ActionListener {

    public JFrame homeFrame ;
    public JFrame hauptmenueStudent;
    public JFrame hauptmenueAdmin;
    public JFrame hauptmenueDozent;

    public JButton studentAnmelden;
    public JButton dozentAnmelden;
    public JButton adminAnmelden;
    public JButton zurueckGehen;//erstmal nur immer zurück zum Hauptmenü

    JPanel generalPanel;
    JPanel studentPanel;
    JPanel adminPanel;
    JPanel dozentPanel;

    JDialog generalDialog;
    JDialog studentDialog;
    JDialog adminDialog;
    JDialog dozentDialog;


    public static void main(String[] args)
    {
        new Ansicht();
    }
    public Ansicht(){
        homeFrame = new JFrame("Home");
        hauptmenueStudent = new JFrame("Hauptmenü");
        hauptmenueAdmin = new JFrame("Hauptmenü");
        hauptmenueDozent = new JFrame("Hauptmenü");


        studentAnmelden = new JButton("Student");studentAnmelden.addActionListener(this);studentAnmelden.setPreferredSize(new Dimension(160, 80));
        dozentAnmelden = new JButton("Dozent");dozentAnmelden.addActionListener(this);dozentAnmelden.setPreferredSize(new Dimension(160, 80));
        adminAnmelden = new JButton("Administrator");adminAnmelden.addActionListener(this);adminAnmelden.setPreferredSize(new Dimension(160, 80));
        zurueckGehen = new JButton("Zurück");zurueckGehen.addActionListener(this);zurueckGehen.setPreferredSize(new Dimension(160, 80));

        fuelleHomeFrame();

        homeFrame.setVisible(true);
    }


    //Framelebensfüllung
    public void fuelleHomeFrame()
    {
        generalPanel = new JPanel();
        JPanel tempPanel=new JPanel();

        generalPanel.setLayout(new java.awt.BorderLayout());
        tempPanel.setLayout(new java.awt.GridLayout(1,3));


        tempPanel.add(studentAnmelden);
        tempPanel.add(dozentAnmelden);
        tempPanel.add(adminAnmelden);

        generalPanel.add(tempPanel, BorderLayout.CENTER);

        homeFrame.add(generalPanel);
        homeFrame.setSize(600,600);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
    }

    public void fuelleDozentFrame()
    {
        dozentPanel = new JPanel();
        //dozentDialog = new JDialog();
        //dozentDialog.setTitle("Dozentenansicht");

        dozentPanel.add(zurueckGehen);
        //dozentPanel.add(dozentDialog);

        hauptmenueDozent.add(dozentPanel);
        hauptmenueDozent.setSize(600,600);
        hauptmenueDozent.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.

    }

    public void fuelleAdminFrame()
    {
        adminPanel = new JPanel();
        //adminDialog = new JDialog();
        //adminDialog.setTitle("Administratorenansicht");

        adminPanel.add(zurueckGehen);
        //adminPanel.add(adminDialog);

        hauptmenueAdmin.add(adminPanel);
        hauptmenueAdmin.setSize(600,600);
        hauptmenueAdmin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
    }

    public void fuelleStudentFrame()
    {
        studentPanel = new JPanel();
        studentDialog = new JDialog();
        studentDialog.setTitle("Studentenansicht");// KA warum hier nichts funktioniert

        studentPanel.add(zurueckGehen);
        studentPanel.add(studentDialog);

        hauptmenueStudent.add(studentPanel);
        hauptmenueStudent.setSize(600,600);
        hauptmenueStudent.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
    }

    //Actions für Buttons
    public void anmeldungDozent()
    {
        homeFrame.dispose();
        fuelleDozentFrame();
        hauptmenueDozent.setVisible(true);

    }

    public void anmeldungStudent()
    {
        homeFrame.dispose();
        fuelleStudentFrame();
        hauptmenueStudent.setVisible(true);

    }

    public void anmeldungAdmin()
    {
        homeFrame.dispose();
        fuelleAdminFrame();
        hauptmenueAdmin.setVisible(true);

    }

    public void zurueckHauptmenue()
    {
        hauptmenueAdmin.dispose();
        hauptmenueDozent.dispose();
        hauptmenueStudent.dispose();
        fuelleHomeFrame();
        homeFrame.setVisible(true);

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

    }
}
