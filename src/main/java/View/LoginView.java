package View;

import entity.Benutzer;
import entity.Dozent;
import entity.Student;
import persistence.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class LoginView extends JFrame implements ActionListener {


    persistence.DatabaseService ds = DatabaseService.getInstance();
    Benutzer user = new Student("jhirsch","1337","Jonah","Hirsch",123654);

    Student student = new Student("abc","1337","Jonah","Hirsch",123654);
    Dozent dozent = new Dozent("def","1337","a","def");
    private JFrame homeFrame ;
    private JButton studentAnmeldenBtn;
    private JButton dozentAnmeldenBtn;
    private JButton registrierenBtn;
    private JButton einloggenBtn;
    private JPanel generalPnl;

    private JLabel einloggenLbl =new JLabel("Einloggen",SwingConstants.CENTER);
    private JLabel usernameLbl=new JLabel("Benutzername:",SwingConstants.LEADING);
    private JLabel passwortLbl=new JLabel("Kennwort:",SwingConstants.LEADING);

    private JTextField usernameTxt=new JTextField("Benutzername",40);
    private JPasswordField passwortTxt=new JPasswordField(40);

    public static void main(String[] args)
    {
        new LoginView();
    }
    public LoginView()
    {
        homeFrame = new JFrame("Home");
        homeFrame.getContentPane().add(generalPnl =new JPanel());

        /** Deaktivierung der Buttons aufgrund von Anmeldestruktur
        studentAnmeldenBtn = new JButton("Student");
        studentAnmeldenBtn.addActionListener(this);
        studentAnmeldenBtn.setPreferredSize(new Dimension(70, 30));
        dozentAnmeldenBtn = new JButton("Dozent");
        dozentAnmeldenBtn.addActionListener(this);
        dozentAnmeldenBtn.setPreferredSize(new Dimension(70, 30));
         */
        registrierenBtn = new JButton("Registrieren");
        registrierenBtn.addActionListener(this);
        registrierenBtn.setPreferredSize(new Dimension(70, 30));
        einloggenBtn = new JButton("Einloggen");
        einloggenBtn.addActionListener(this);
        einloggenBtn.setPreferredSize(new Dimension(70, 30));

        fuelleHomeFrame();

        homeFrame.setSize(600,600);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        homeFrame.setLocation((display.getSize().width - homeFrame.getSize().width) / 2, (display.getSize().height - homeFrame.getSize().height) / 2);
        homeFrame.setVisible(true);
        ds.persistObject(user);
        ds.persistObject(student);
        ds.persistObject(dozent);
    }


    public void fuelleHomeFrame()
    {
        //JPanel tempPanel=new JPanel();
        JPanel tempPanel2=new JPanel();
        JPanel tempUsernamePanel=new JPanel(new FlowLayout());
        JPanel tempPasswortPanel=new JPanel(new FlowLayout());

        generalPnl.setLayout(new java.awt.BorderLayout());
        //tempPanel.setLayout(new java.awt.GridLayout(1,2));
        tempPanel2.setLayout(new java.awt.GridLayout(5,1,16,16));

        tempPanel2.add(einloggenLbl);

        tempUsernamePanel.add(usernameLbl);
        tempUsernamePanel.add(usernameTxt);
        tempPanel2.add(tempUsernamePanel);

        tempPasswortPanel.add(passwortLbl);
        tempPasswortPanel.add(passwortTxt);
        tempPanel2.add(tempPasswortPanel);
        tempPanel2.add(einloggenBtn);
        tempPanel2.add(registrierenBtn);

        //tempPanel.add(studentAnmeldenBtn);
        //tempPanel.add(dozentAnmeldenBtn);

        generalPnl.add(tempPanel2,BorderLayout.CENTER);
        //generalPnl.add(tempPanel, BorderLayout.SOUTH);

        homeFrame.add(generalPnl);
    }

    public void einloggenSequenz()
    {
        List list = ds.readBenutzerFromDatabase();
        for(int i=0;i<list.size();i++)
        {
            String[] benutzername=list.get(i).toString().split(", ");
            //System.out.println(list.get(i));
            System.out.println(benutzername[1]);
            String k= "benutzername='"+usernameTxt.getText()+"'";
            System.out.println(k);
            if (k.equals(benutzername[1]))
            {
                if(userIstStudent(usernameTxt.getText()))
                {
                    checkPasswort(ds.readStudentnachBenutzernamen(usernameTxt.getText()));
                    new StudentMainView();return;
                }
                else if(userIstDozent(usernameTxt.getText()))
                {
                    checkPasswort(ds.readDozentnachBenutzernamen(usernameTxt.getText()));
                    new DozentAnsicht();return;
                }else{
                    System.out.println("Fehler 702");System.out.println(usernameTxt.getText());
                }
            }else{System.out.println("Fehler 701");System.out.println(usernameTxt.getText());System.out.println(String.valueOf(passwortTxt.getPassword()));}
        }
    }

    public boolean userIstStudent(String name)
    {
        List list = ds.readStudentenFromDatabase();
        int size = list.size();
        for (int i = 0; i <= size; i++)
        {
            if (name.equalsIgnoreCase(student.getBenutzername()))
            {
                return true;
            }
        }
        return false;
    }

    public boolean userIstDozent(String name)
    {
        List list = ds.readDozentenFromDatabase();
        for (int i = 0; i <= list.size(); i++)
        {
            if (name.equalsIgnoreCase(dozent.getBenutzername()))
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkPasswort(Benutzer user)
    {
        if(String.valueOf(passwortTxt.getPassword()) == user.getPasswort())
        {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == this.studentAnmeldenBtn){
            new StudentMainView();//anmeldungStudent();
            homeFrame.setVisible(false);
        }
        else if(e.getSource() == this.dozentAnmeldenBtn){
            //d
        }
        else if(e.getSource() == this.registrierenBtn){
            new RegistrierenView(homeFrame);
        }
        else if(e.getSource() == this.einloggenBtn){
            einloggenSequenz();
            homeFrame.setVisible(false);
        }

    }
}
