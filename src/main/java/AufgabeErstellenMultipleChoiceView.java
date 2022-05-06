import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Die View zur Erstellung einer Multiple Choice Aufgabe
 *
 * @author Jannik Oehme
 * @version 05.05.2022
 */
public class AufgabeErstellenMultipleChoiceView implements ActionListener {

    JFrame AufgabeErstellenMultipleChoiceViewFrame;
    //Panels
    JPanel AufgabeErstellenMultipleChoicePnl;
    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;
    //Layouts
    BorderLayout bl = new BorderLayout();
    //Buttons
    JButton zurueckBtn;
    JButton speichernBtn;
    //Labels
    JLabel titelLbl;
    JLabel aufgabenTxtLbl;
    JLabel loesungsHinweisLbl;
    JLabel schwierigketiLbl;
    JLabel bearbeitungszeitLbl;
    JLabel punkteLbl;
    JLabel loesungLbl;
    JLabel antwort1Lbl;
    JLabel antwort2Lbl;
    JLabel antwort3Lbl;
    JLabel antwort4Lbl;
    //TextAreas
    JTextArea titelTA;
    JTextArea aufgabenTextTA;
    JTextArea loesungshinwTA;
    JTextArea schwierigkeitTA;
    JTextArea bearbeitungsZeitTA;
    JTextArea punkteTA;
    JTextArea loesungTA;
    JTextArea antwort1TA;
    JTextArea antwort2TA;
    JTextArea antwort3TA;
    JTextArea antwort4TA;

    public static void main(String[] args) {
        new AufgabeErstellenMultipleChoiceView();
    }
    AufgabeErstellenMultipleChoiceView(){
        Dimension test = new Dimension(500,500);
        AufgabeErstellenMultipleChoiceViewFrame = new JFrame("MultipleChoice");
        AufgabeErstellenEinfachAntwortViewFuellen();
        AufgabeErstellenMultipleChoiceViewFrame.setSize(500, 500);
        AufgabeErstellenMultipleChoiceViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AufgabeErstellenMultipleChoiceViewFrame.setMinimumSize(test);
        AufgabeErstellenMultipleChoiceViewFrame.pack();
        AufgabeErstellenMultipleChoiceViewFrame.setVisible(true);
    }
    private void AufgabeErstellenEinfachAntwortViewFuellen() {
        //Panels
        centerPnl = new JPanel();
        FlowLayout fl = new FlowLayout();
        centerPnl.setLayout(fl);
        northPnl = new JPanel();
        southPnl = new JPanel();
        AufgabeErstellenMultipleChoicePnl = new JPanel();
        AufgabeErstellenMultipleChoicePnl.setLayout(bl);
        //Buttons
        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        speichernBtn = new JButton("Speichern");
        speichernBtn.addActionListener(this);
        //TextAreas
        titelTA = new JTextArea();
        titelTA.setLineWrap(true);

        aufgabenTextTA = new JTextArea();
        aufgabenTextTA.setLineWrap(true);


        loesungshinwTA = new JTextArea();
        loesungshinwTA.setBounds(20,75,100,100);
        loesungshinwTA.setLineWrap(true);

        schwierigkeitTA = new JTextArea();
        schwierigkeitTA.setBounds(20,75,100,100);
        schwierigkeitTA.setLineWrap(true);


        bearbeitungsZeitTA = new JTextArea();
        bearbeitungsZeitTA.setBounds(20,75,100,100);
        bearbeitungsZeitTA.setLineWrap(true);

        punkteTA = new JTextArea();
        punkteTA.setBounds(20,75,100,100);
        punkteTA.setLineWrap(true);

        loesungTA = new JTextArea();
        loesungTA.setBounds(20,75,100,100);
        loesungTA.setLineWrap(true);

        antwort1TA = new JTextArea();
        antwort1TA.setBounds(20,75,100,100);
        antwort1TA.setLineWrap(true);

        antwort2TA = new JTextArea();
        antwort2TA.setBounds(20,75,100,100);
        antwort2TA.setLineWrap(true);

        antwort3TA = new JTextArea();
        antwort3TA.setBounds(20,75,100,100);
        antwort3TA.setLineWrap(true);

        antwort4TA = new JTextArea();
        antwort4TA.setBounds(20,75,100,100);
        antwort4TA.setLineWrap(true);
        //Label
        titelLbl = new JLabel("Aufgaben Titel");
        loesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        loesungLbl= new JLabel("Lösung");
        aufgabenTxtLbl = new JLabel("Aufgaben Text: ");
        antwort1Lbl = new JLabel("Antwort 1:");
        antwort2Lbl = new JLabel("Antwort 2:");
        antwort3Lbl = new JLabel("Antwort 3:");
        antwort4Lbl = new JLabel("Antwort 4:");
        //ComponentsAdden
        centerPnl.add(titelLbl);
        centerPnl.add(titelTA);
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
        centerPnl.add(loesungsHinweisLbl);
        centerPnl.add(loesungshinwTA);
        centerPnl.add(antwort1Lbl);
        centerPnl.add(antwort1TA);
        centerPnl.add(antwort2Lbl);
        centerPnl.add(antwort2TA);
        centerPnl.add(antwort3Lbl);
        centerPnl.add(antwort3TA);
        centerPnl.add(antwort4Lbl);
        centerPnl.add(antwort4TA);

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
