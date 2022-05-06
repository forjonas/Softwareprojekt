package View.AufgabenErstellen;

import View.DozentAnsicht;
import View.ImageFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Die View zur Erstellung einer UML Aufgabe
 *
 * @author Jannik Oehme
 * @version 05.05.2022
 */
public class AufgabeErstellenUmlView implements ActionListener {
    JFrame AufgabeErstellenUMLViewFrame;
    //Panels
    JPanel AufgabeErstellenUMLPnl;
    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;
    //Layouts
    BorderLayout bl = new BorderLayout();
    //Buttons
    JButton zurueckBtn;
    JButton speichernBtn;
    JButton UMLHochladenBtn;
    JButton musterloesungBtn;
    //Labels
    JLabel titelLbl;
    JLabel aufgabenTxtLbl;
    JLabel loesungsHinweisLbl;
    JLabel schwierigketiLbl;
    JLabel bearbeitungszeitLbl;
    JLabel punkteLbl;
    JLabel loesungLbl;
    //TextAreas
    JTextArea titelTA;
    JTextArea aufgabenTextTA;
    JTextArea loesungshinwTA;
    JTextArea schwierigkeitTA;
    JTextArea bearbeitungsZeitTA;
    JTextArea punkteTA;
    //Files
    JFileChooser FC;
    File file;

    public static void main(String[] args) {
        new AufgabeErstellenUmlView();
    }

    AufgabeErstellenUmlView(){
        AufgabeErstellenUMLViewFrame= new JFrame("MultipleChoice");
        AufgabeErstellenUMLViewFuellen();
        AufgabeErstellenUMLViewFrame.pack();
        AufgabeErstellenUMLViewFrame.setSize(500, 500);
        AufgabeErstellenUMLViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AufgabeErstellenUMLViewFrame.setVisible(true);
    }
    private void AufgabeErstellenUMLViewFuellen() {
        //Panels
        centerPnl = new JPanel();
        northPnl = new JPanel();
        southPnl = new JPanel();
        AufgabeErstellenUMLPnl = new JPanel();
        AufgabeErstellenUMLPnl.setLayout(bl);
        //Buttons
        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        speichernBtn = new JButton("Speichern");
        speichernBtn.addActionListener(this);

        UMLHochladenBtn = new JButton("Code Hochladen");
        UMLHochladenBtn.addActionListener(this);

        musterloesungBtn = new JButton("Musterlösung Hochladen");
        musterloesungBtn.addActionListener(this);
        //TextAreas
        titelTA = new JTextArea();
        titelTA.setLineWrap(true);
        titelTA.setBounds(20,75,250,200);

        aufgabenTextTA = new JTextArea();
        aufgabenTextTA.setLineWrap(true);
        aufgabenTextTA.setBounds(20,75,250,200);

        loesungshinwTA = new JTextArea();
        loesungshinwTA.setBounds(20,75,250,200);
        loesungshinwTA.setLineWrap(true);

        schwierigkeitTA = new JTextArea();
        schwierigkeitTA.setBounds(20,75,250,200);
        schwierigkeitTA.setLineWrap(true);

        bearbeitungsZeitTA = new JTextArea();
        bearbeitungsZeitTA.setBounds(20,75,250,200);
        bearbeitungsZeitTA.setLineWrap(true);

        punkteTA = new JTextArea();
        punkteTA.setBounds(20,75,250,200);
        punkteTA.setLineWrap(true);
        //Labels
        titelLbl = new JLabel("Aufgaben Titel");
        loesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        loesungLbl= new JLabel("Lösung");
        aufgabenTxtLbl = new JLabel("Aufgaben Text");
        //ComponentsAdden
        centerPnl.add(titelLbl);
        centerPnl.add(titelTA);
        centerPnl.add(aufgabenTxtLbl);
        centerPnl.add(aufgabenTextTA);
        centerPnl.add(loesungLbl);
        centerPnl.add(UMLHochladenBtn);
        centerPnl.add(musterloesungBtn);
        centerPnl.add(schwierigketiLbl);
        centerPnl.add(schwierigkeitTA);
        centerPnl.add(bearbeitungszeitLbl);
        centerPnl.add(bearbeitungsZeitTA);
        centerPnl.add(punkteLbl);
        centerPnl.add(punkteTA);
        centerPnl.add(loesungsHinweisLbl);
        centerPnl.add(loesungshinwTA);

        northPnl.add(zurueckBtn);
        southPnl.add(speichernBtn);

        AufgabeErstellenUMLPnl.add(centerPnl,BorderLayout.CENTER);
        AufgabeErstellenUMLPnl.add(northPnl,BorderLayout.NORTH);
        AufgabeErstellenUMLPnl.add(southPnl,BorderLayout.SOUTH);
        AufgabeErstellenUMLViewFrame.add(AufgabeErstellenUMLPnl);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckBtn) {
            zurueck();
        } else if (e.getSource() == this.speichernBtn) {
            speichern();
        }
        else if(e.getSource()== this.UMLHochladenBtn){
            UMLHochladen();
        }
        else if(e.getSource() == this.musterloesungBtn){
            UMLHochladen();
        }
    }
    private File UMLHochladen() { //HIER MUSS NOCH NE MENGE PASSIEREN https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
        FC = new JFileChooser((String) null);
        FC.setAcceptAllFileFilterUsed(false);
        FC.setFileFilter(new ImageFilter());
        int returnVal = FC.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            file = FC.getSelectedFile();
            System.out.println(file.getName());
            return file;
        }
        return null;
    }
    private void zurueck() {
        AufgabeErstellenUMLViewFrame.dispose();
        AufgabeErstellenStartView.main(null);
    }
    private void speichern() {
        //FUNKTIONALITÄT MISSING
        AufgabeErstellenUMLViewFrame.dispose();
        DozentAnsicht.main(null);
    }
}

