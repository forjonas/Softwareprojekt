import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AufgabeErstellenCodeView implements ActionListener {
    JFrame AufgabeErstellenCodeViewFrame;

    /**Panels-----------------------------------------------------------------------------**/
    JPanel AufgabeErstellenCodePnl;

    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;
    /**-------------------------------------------------------------------------------------**/
    BorderLayout bl = new BorderLayout();

    /**Buttons-----------------------------------------------------------------------------**/
    JButton zurueckBtn;
    JButton speichernBtn;
    JButton CodeHochladen;
    /**-------------------------------------------------------------------------------------**/

    /**Labels-----------------------------------------------------------------------------**/
    JLabel aufgabenTxtLbl;
    JLabel LoesungsHinweisLbl;
    JLabel schwierigketiLbl;
    JLabel bearbeitungszeitLbl;
    JLabel punkteLbl;
    JLabel loesungLbl;
    /**-------------------------------------------------------------------------------------**/

    /**TextAreas-----------------------------------------------------------------------------**/
    JTextArea aufgabenTextTA;
    JTextArea loesungshinwTA;
    JTextArea schwierigkeitTA;
    JTextArea bearbeitungsZeitTA;
    JTextArea punkteTA;
    /**-------------------------------------------------------------------------------------**/
    JFileChooser FC;




    public static void main(String[] args) {
        new AufgabeErstellenCodeView();
    }

    AufgabeErstellenCodeView(){
         AufgabeErstellenCodeViewFrame= new JFrame("MultipleChoice");
        AufgabeErstellenCodeViewFuellen();
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

        CodeHochladen = new JButton("Code Hochladen");
        CodeHochladen.addActionListener(this);

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





        LoesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        loesungLbl= new JLabel("Lösung");
        aufgabenTxtLbl = new JLabel("Aufgaben Text");

        centerPnl.add(aufgabenTxtLbl);
        centerPnl.add(aufgabenTextTA);
        centerPnl.add(loesungLbl);
        centerPnl.add(CodeHochladen);
        centerPnl.add(schwierigketiLbl);
        centerPnl.add(schwierigkeitTA);
        centerPnl.add(bearbeitungszeitLbl);
        centerPnl.add(bearbeitungsZeitTA);
        centerPnl.add(punkteLbl);
        centerPnl.add(punkteTA);
        centerPnl.add(LoesungsHinweisLbl);
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
        else if(e.getSource()== this.CodeHochladen){
            codeHochladen();
        }

    }

    private void codeHochladen() { //HIER MUSS NOCH NE MENGE PASSIEREN https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
        FC = new JFileChooser();


        JFrame Chooser = new JFrame("Choose File");
        Chooser.add(FC);
        Chooser.setSize(500, 500);
        Chooser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Chooser.setVisible(true);


    }

    private void zurueck() {
        AufgabeErstellenCodeViewFrame.dispose();
        AufgabeErstellenStartView.main(null);
    }

    private void speichern() {

        //FUNKTIONALITÄT MISSING

        AufgabeErstellenCodeViewFrame.dispose();
        DozentAnsicht.main(null);

    }


}
