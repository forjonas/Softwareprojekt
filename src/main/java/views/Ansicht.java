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

    public void fuelleDozentFrame()
    {
        JButton testatEinsehen = new JButton("Testat Einsehen");testatEinsehen.addActionListener(this);testatEinsehen.setPreferredSize(new Dimension(160, 80));
        JButton trainingsEinsehen = new JButton("Trainings Einsehen");trainingsEinsehen.addActionListener(this);trainingsEinsehen.setPreferredSize(new Dimension(160, 80));
        JButton testateErstellen = new JButton("Testate Erstellen");testateErstellen.addActionListener(this);testateErstellen.setPreferredSize(new Dimension(160, 80));
        JButton testatuebersicht = new JButton("Testat Übersicht");testatuebersicht.addActionListener(this);testatuebersicht.setPreferredSize(new Dimension(160, 80));
        JButton aufgabeErstellen = new JButton("Aufgabe Erstellen");aufgabeErstellen.addActionListener(this);aufgabeErstellen.setPreferredSize(new Dimension(160, 80));
        JButton aufgabenuebersicht = new JButton("Aufgaben Übersicht");aufgabenuebersicht.addActionListener(this);aufgabenuebersicht.setPreferredSize(new Dimension(160, 80));
        JButton trainingsDurchfuehren = new JButton("Trainings Durchführen");trainingsDurchfuehren.addActionListener(this);trainingsDurchfuehren.setPreferredSize(new Dimension(160, 80));
        JButton testateDurchfuehren = new JButton("Testate Durchführen");testateDurchfuehren.addActionListener(this);testateDurchfuehren.setPreferredSize(new Dimension(160, 80));

        FlowLayout fl = new FlowLayout();
        dozentPnl = new JPanel();
        dozentPnl.setLayout(fl);
        dozentPnl.add(zurueckGehenBtn);
        dozentPnl.add(testatEinsehen);
        dozentPnl.add(testateDurchfuehren);
        dozentPnl.add(trainingsDurchfuehren);
        dozentPnl.add(aufgabenuebersicht);
        dozentPnl.add(aufgabeErstellen);
        dozentPnl.add(testatuebersicht);
        dozentPnl.add(testateErstellen);
        dozentPnl.add(trainingsEinsehen);
        homeFrame.add(dozentPnl);
        homeFrame.setSize(1000,1000);
        homeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.

    }
    public void testatEinsehen(){
        System.out.println("test");
        homeFrame.dispose();
        testatErgebnisse();
        //hauptmenueDozent.setVisible(true);
    }

    public void testatErgebnisse(){
        System.out.println("test");
    }

    public void fuelleAdminFrame()
    {

    }

    public void fuelleStudentFrame()
    {
        studentPnl.setLayout(new BorderLayout());
        JLabel textLbl = new JLabel("Willkommen ");//+Benutzer.getStudentenID());
        JPanel tempNorthPnl= new JPanel(new FlowLayout());
        JPanel tempCenterPnl= new JPanel(new FlowLayout());


        //Drei Buttons laut Mock-Up
        trainingStartenBtn = new JButton("Trainings");
        trainingStartenBtn.addActionListener(this);
        trainingStartenBtn.setPreferredSize(new Dimension(160,80));
        testateOeffnenBtn = new JButton("Testate");
        testateOeffnenBtn.addActionListener(this);
        testateOeffnenBtn.setPreferredSize(new Dimension(160,80));
        ergebnisTestateBtn = new JButton("Ergebnisse");
        ergebnisTestateBtn.addActionListener(this);
        ergebnisTestateBtn.setPreferredSize(new Dimension(160,80));

        tempCenterPnl.add(trainingStartenBtn);
        tempCenterPnl.add(testateOeffnenBtn);
        tempCenterPnl.add(ergebnisTestateBtn);
        tempNorthPnl.add(zurueckGehenBtn);
        tempNorthPnl.add(textLbl);
        studentPnl.add(tempNorthPnl, BorderLayout.NORTH);
        studentPnl.add(tempCenterPnl, BorderLayout.CENTER);
        homeFrame.add(studentPnl);
    }

    public void fuelleTrainingAuswahl()
    {
        trainingAuswahlPnl.setLayout(new BorderLayout());
        JPanel tempCenterPnl= new JPanel(new GridLayout(7,2));

        String[] dauerStrings={"5 Minuten","10 Minuten","15 Minuten","20 Minuten"};
        JComboBox<String> dauerAuswahlCBox=new JComboBox<>(dauerStrings);

        String[] schwierigkeitStrings={"Leicht","Mittel","Schwer"};
        JComboBox<String>schwierigkeitAuswahlCBox=new JComboBox<>(schwierigkeitStrings);

        String[] aufgabenTypStrings={"Multiple Choice","UML","Code","Einfach Antwort"};
        JComboBox<String>aufgabenTypAuswahlCBox=new JComboBox<>(aufgabenTypStrings);

        String[] aufgabenMengeStrings={"1-3","3-5","5-7","7-10"};
        JComboBox<String>aufgabenMengeAuswahlCBox=new JComboBox<>(aufgabenMengeStrings);

        String[] kategorieStrings={"Software Architektur","Programmierung","Grundlagen"};
        JComboBox<String>kategorieAuswahlCBox=new JComboBox<>(kategorieStrings);

        generiereTrainingBtn = new JButton("Training Generieren");
        generiereTrainingBtn.addActionListener(this);
        generiereTrainingBtn.setPreferredSize(new Dimension(80,40));
        zurueckStudentMenueBtn =new JButton("Zurück");
        zurueckStudentMenueBtn.addActionListener(this);
        zurueckStudentMenueBtn.setPreferredSize(new Dimension(80,40));

        tempCenterPnl.add(zurueckStudentMenueBtn);    tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(dauerAuswahlCBox);           tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(schwierigkeitAuswahlCBox);   tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(aufgabenTypAuswahlCBox);     tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(aufgabenMengeAuswahlCBox);   tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(kategorieAuswahlCBox);       tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(new JLabel(""));     tempCenterPnl.add(generiereTrainingBtn);

        trainingAuswahlPnl.add(tempCenterPnl,BorderLayout.CENTER);
        homeFrame.add(trainingAuswahlPnl);

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
        homeFrame.getContentPane().remove(generalPnl);
        homeFrame.getContentPane().add(studentPnl =new JPanel());
        homeFrame.getContentPane().revalidate(); //IMPORTANT
        homeFrame.getContentPane().repaint();    //IMPORTANT
        fuelleStudentFrame();
    }


    public void trainingStarten()
    {
        homeFrame.getContentPane().remove(studentPnl);
        homeFrame.getContentPane().add(trainingAuswahlPnl =new JPanel());
        homeFrame.getContentPane().revalidate(); //IMPORTANT
        homeFrame.getContentPane().repaint();    //IMPORTANT
        fuelleTrainingAuswahl();
    }

    public void zurueckHauptmenue()
    {
        homeFrame.getContentPane().remove(studentPnl);
        homeFrame.getContentPane().add(generalPnl);
        homeFrame.getContentPane().revalidate(); //IMPORTANT
        homeFrame.getContentPane().repaint();    //IMPORTANT

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
