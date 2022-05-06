package View.AufgabenErstellen;

import View.DozentAnsicht;
import View.ImageFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


/**
 * Die View zur Erstellung einer Code Aufgabe
 *
 * @author Jannik Oehme
 * @version 05.05.2022
 */
public class AufgabeErstellenCodeView implements ActionListener {
    JFrame AufgabeErstellenCodeViewFrame;

    //Panels
    JPanel AufgabeErstellenCodePnl;
    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;
    //Layouts
    BorderLayout bl = new BorderLayout();
    //Buttons
    JButton zurueckBtn;
    JButton speichernBtn;
    JButton codeHochladenBtn;
    JButton musterloesungHochladenBtn;
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
    //Filechooser
    JFileChooser FCC;
    File file;

    public static void main(String[] args) {
        new AufgabeErstellenCodeView();
    }

    AufgabeErstellenCodeView(){
         AufgabeErstellenCodeViewFrame= new JFrame("MultipleChoice");
        AufgabeErstellenCodeViewFuellen();
        AufgabeErstellenCodeViewFrame.pack();
        AufgabeErstellenCodeViewFrame.setSize(500, 500);
        AufgabeErstellenCodeViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AufgabeErstellenCodeViewFrame.setVisible(true);
    }
    private void AufgabeErstellenCodeViewFuellen() {
        centerPnl = new JPanel();
        northPnl = new JPanel();
        southPnl = new JPanel();
        AufgabeErstellenCodePnl = new JPanel();
        AufgabeErstellenCodePnl.setLayout(bl);

        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        speichernBtn = new JButton("Speichern");
        speichernBtn.addActionListener(this);

        codeHochladenBtn = new JButton("Code Hochladen");
        codeHochladenBtn.addActionListener(this);

        musterloesungHochladenBtn = new JButton("Musterlösung Hochladen");
        musterloesungHochladenBtn.addActionListener(this);

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

        titelLbl = new JLabel("Aufgaben Titel");
        loesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        loesungLbl= new JLabel("Lösung");
        aufgabenTxtLbl = new JLabel("Aufgaben Text");

        centerPnl.add(titelLbl);
        centerPnl.add(titelTA);
        centerPnl.add(aufgabenTxtLbl);
        centerPnl.add(aufgabenTextTA);
        centerPnl.add(loesungLbl);
        centerPnl.add(codeHochladenBtn);
        centerPnl.add(musterloesungHochladenBtn);
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

        AufgabeErstellenCodePnl.add(centerPnl,BorderLayout.CENTER);
        AufgabeErstellenCodePnl.add(northPnl,BorderLayout.NORTH);
        AufgabeErstellenCodePnl.add(southPnl,BorderLayout.SOUTH);
        AufgabeErstellenCodeViewFrame.add(AufgabeErstellenCodePnl);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckBtn) {
            zurueck();
        } else if (e.getSource() == this.speichernBtn) {
            speichern();
        }
        else if(e.getSource()== this.codeHochladenBtn){
            codeHochladen();
        }
        else if(e.getSource() == this.musterloesungHochladenBtn){
            codeHochladen(); // Theoretisch geht das ist nicht ganz so schön aber wenn man den output differenziert müsste das gehen
        }
    }
    private File codeHochladen() { //HIER MUSS NOCH NE MENGE PASSIEREN https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
        FCC = new JFileChooser((String) null);
        FCC.setAcceptAllFileFilterUsed(false);
        FCC.setFileFilter(new ImageFilter());
        int returnVal = FCC.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            file = FCC.getSelectedFile();
            System.out.println(file.getName());
            return file;
        }
        return null;
    }
    private void zurueck() {
        AufgabeErstellenCodeViewFrame.dispose();
        AufgabeErstellenStartView.main(null);
    }
    private File speichern() {
        AufgabeErstellenCodeViewFrame.dispose();
        DozentAnsicht.main(null);
        return file;
    }
}
