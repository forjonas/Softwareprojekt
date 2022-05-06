package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrierenView implements ActionListener {

    private JFrame registrierenView;
    private JFrame einlogenView;
    private JPanel registrierePnl;
    private JButton registrierenBtn;
    private JButton zurueckBtn;
    private JLabel registriereLbl= new JLabel("Registrieren");
    private JLabel usernameLbl=new JLabel("Benutzername:");
    private JLabel passwortLbl=new JLabel("Kennwort:");

    private JTextField usernameTxt=new JTextField("Benutzername",40);
    private JPasswordField passwortTxt=new JPasswordField(40);



    public RegistrierenView(JFrame hauptmenueView)
    {
        registrierenView = new JFrame("Home");
        registrierenView.getContentPane().add(registrierePnl =new JPanel());

        einlogenView=hauptmenueView;

        registrierenBtn = new JButton("Registrieren");
        registrierenBtn.addActionListener(this);
        registrierenBtn.setPreferredSize(new Dimension(70, 30));
        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);
        zurueckBtn.setPreferredSize(new Dimension(70,30));

        fuelleRegistrierenFrame();

        einlogenView.setVisible(false);
        registrierenView.setSize(600,600);
        registrierenView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
        registrierenView.setVisible(true);
    }

    public void fuelleRegistrierenFrame()
    {
        JPanel tempPanel2=new JPanel();
        JPanel tempUsernamePanel=new JPanel(new FlowLayout());
        JPanel tempPasswortPanel=new JPanel(new FlowLayout());

        registrierePnl.setLayout(new BorderLayout());
        tempPanel2.setLayout(new GridLayout(5,1,16,16));

        tempPanel2.add(registriereLbl);

        tempUsernamePanel.add(usernameLbl);
        tempUsernamePanel.add(usernameTxt);
        tempPanel2.add(tempUsernamePanel);

        tempPasswortPanel.add(passwortLbl);
        tempPasswortPanel.add(passwortTxt);
        tempPanel2.add(tempPasswortPanel);
        tempPanel2.add(registrierenBtn);

        registrierePnl.add(zurueckBtn,BorderLayout.SOUTH);
        registrierePnl.add(tempPanel2,BorderLayout.CENTER);

        registrierenView.add(registrierePnl);
    }

    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == this.registrierenBtn){
            //d
        }
        else if(e.getSource() == this.zurueckBtn){
            einlogenView.setVisible(true);
            registrierenView.dispose();
        }

    }
}
