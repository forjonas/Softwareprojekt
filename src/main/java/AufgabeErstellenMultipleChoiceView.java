import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AufgabeErstellenMultipleChoiceView implements ActionListener {

    JFrame AufgabeErstellenMultipleChoiceViewFrame;

    /**Panels-----------------------------------------------------------------------------**/
    JPanel AufgabeErstellenMultipleChoicePnl;

    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;
    /**-------------------------------------------------------------------------------------**/
    BorderLayout bl = new BorderLayout();

    /**Buttons-----------------------------------------------------------------------------**/
    JButton zurueckBtn;
    JButton speichernBtn;
    /**-------------------------------------------------------------------------------------**/

    /**Labels-----------------------------------------------------------------------------**/
    JLabel aufgabenTxtLbl;
    JLabel LoesungsHinweisLbl;
    JLabel schwierigketiLbl;
    JLabel bearbeitungszeitLbl;
    JLabel punkteLbl;
    JLabel loesungLbl;
    JLabel Antwort1Lbl;
    JLabel Antwort2Lbl;
    JLabel Antwort3Lbl;
    JLabel Antwort4Lbl;
    /**-------------------------------------------------------------------------------------**/

    /**TextAreas-----------------------------------------------------------------------------**/
    JTextArea aufgabenTextTA;
    JTextArea loesungshinwTA;
    JTextArea schwierigkeitTA;
    JTextArea bearbeitungsZeitTA;
    JTextArea punkteTA;
    JTextArea loesungTA;
    JTextArea Antwort1TA;
    JTextArea Antwort2TA;
    JTextArea Antwort3TA;
    JTextArea Antwort4TA;
    /**-------------------------------------------------------------------------------------**/





    public static void main(String[] args) {
        new AufgabeErstellenMultipleChoiceView();
    }

    AufgabeErstellenMultipleChoiceView(){
        AufgabeErstellenMultipleChoiceViewFrame = new JFrame("MultipleChoice");
        AufgabeErstellenEinfachAntwortViewFuellen();
        AufgabeErstellenMultipleChoiceViewFrame.setSize(500, 500);
        AufgabeErstellenMultipleChoiceViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AufgabeErstellenMultipleChoiceViewFrame.setVisible(true);
    }

    private void AufgabeErstellenEinfachAntwortViewFuellen() {
        centerPnl = new JPanel();
        northPnl = new JPanel();
        southPnl = new JPanel();
        AufgabeErstellenMultipleChoicePnl = new JPanel();
        AufgabeErstellenMultipleChoicePnl.setLayout(bl);

        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        speichernBtn = new JButton("Speichern");
        speichernBtn.addActionListener(this);

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

        loesungTA = new JTextArea();
        loesungTA.setBounds(20,75,250,200);
        loesungTA.setLineWrap(true);

        Antwort1TA = new JTextArea();
        Antwort1TA.setBounds(20,75,250,200);
        Antwort1TA.setLineWrap(true);

        Antwort2TA = new JTextArea();
        Antwort2TA.setBounds(20,75,250,200);
        Antwort2TA.setLineWrap(true);

        Antwort3TA = new JTextArea();
        Antwort3TA.setBounds(20,75,250,200);
        Antwort3TA.setLineWrap(true);

        Antwort4TA = new JTextArea();
        Antwort4TA.setBounds(20,75,250,200);
        Antwort4TA.setLineWrap(true);



        LoesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        loesungLbl= new JLabel("Lösung");
        aufgabenTxtLbl = new JLabel("Aufgaben Text");
        Antwort1Lbl = new JLabel("Antwort 1:");
        Antwort2Lbl = new JLabel("Antwort 2:");
        Antwort3Lbl = new JLabel("Antwort 3:");
        Antwort4Lbl = new JLabel("Antwort 4:");

        centerPnl.add(aufgabenTxtLbl);
        centerPnl.add(aufgabenTextTA);
        centerPnl.add(loesungLbl);
        centerPnl.add(loesungTA);
        centerPnl.add(schwierigketiLbl);
        centerPnl.add(schwierigkeitTA);
        centerPnl.add(bearbeitungszeitLbl);
        centerPnl.add(bearbeitungsZeitTA);
        centerPnl.add(punkteLbl);
        centerPnl.add(punkteTA);
        centerPnl.add(LoesungsHinweisLbl);
        centerPnl.add(loesungshinwTA);
        centerPnl.add(Antwort1Lbl);
        centerPnl.add(Antwort1TA);
        centerPnl.add(Antwort2Lbl);
        centerPnl.add(Antwort2TA);
        centerPnl.add(Antwort3Lbl);
        centerPnl.add(Antwort3TA);
        centerPnl.add(Antwort4Lbl);
        centerPnl.add(Antwort4TA);



        northPnl.add(zurueckBtn);
        southPnl.add(speichernBtn);

        AufgabeErstellenMultipleChoicePnl.add(centerPnl,BorderLayout.CENTER);
        AufgabeErstellenMultipleChoicePnl.add(northPnl,BorderLayout.NORTH);
        AufgabeErstellenMultipleChoicePnl.add(southPnl,BorderLayout.SOUTH);
        AufgabeErstellenMultipleChoiceViewFrame.add(AufgabeErstellenMultipleChoicePnl);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckBtn) {
            zurueck();
        } else if (e.getSource() == this.speichernBtn) {
            speichern();

        }

    }

    private void zurueck() {
        AufgabeErstellenMultipleChoiceViewFrame.dispose();
        AufgabeErstellenStartView.main(null);
    }

    private void speichern() {

        //FUNKTIONALITÄT MISSING

        AufgabeErstellenMultipleChoiceViewFrame.dispose();
        DozentAnsicht.main(null);

    }
}
