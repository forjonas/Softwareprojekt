import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AufgabeErstellenEinfachAntwortView implements ActionListener {


    JFrame AufgabeErstellenEinfachAntwortFrame;
    JPanel AufgabeErstellenEinfachAntwortViewPnl;

    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;

    BorderLayout bl = new BorderLayout();

    JButton zurueckBtn;
    JButton speichernBtn;
    JLabel aufgabenTxtLbl;
    JLabel LoesungsHinweisLbl;
    JLabel schwierigketiLbl;
    JLabel bearbeitungszeitLbl;
    JLabel punkteLbl;
    JLabel loesungLbl;

    JTextArea aufgabenTextTA;
    JTextArea loesungshinwTA;
    JTextArea schwierigkeitTA;
    JTextArea bearbeitungsZeitTA;
    JTextArea punkteTA;
    JTextArea loesungTA;





    public static void main(String[] args) {
        new AufgabeErstellenEinfachAntwortView();
    }

    AufgabeErstellenEinfachAntwortView(){
        AufgabeErstellenEinfachAntwortFrame = new JFrame("Einfach Antwort");
        AufgabeErstellenEinfachAntwortViewFuellen();
        AufgabeErstellenEinfachAntwortFrame.setSize(500, 500);
        AufgabeErstellenEinfachAntwortFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AufgabeErstellenEinfachAntwortFrame.setVisible(true);
    }

    private void AufgabeErstellenEinfachAntwortViewFuellen() {
        centerPnl = new JPanel();
        northPnl = new JPanel();
        southPnl = new JPanel();
        AufgabeErstellenEinfachAntwortViewPnl = new JPanel();
        AufgabeErstellenEinfachAntwortViewPnl.setLayout(bl);

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


        LoesungsHinweisLbl = new JLabel("Lösungshinweis: ");
        schwierigketiLbl = new JLabel("Schwierigkeit: ");
        bearbeitungszeitLbl = new JLabel("BearbeitungsZeit: ");
        punkteLbl = new JLabel("Punkte: ");
        loesungLbl= new JLabel("Lösung");
        aufgabenTxtLbl = new JLabel("Aufgaben Text");


        JLabel spacer;
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

        northPnl.add(zurueckBtn);
        southPnl.add(speichernBtn);

        AufgabeErstellenEinfachAntwortViewPnl.add(centerPnl,BorderLayout.CENTER);
        AufgabeErstellenEinfachAntwortViewPnl.add(northPnl,BorderLayout.NORTH);
        AufgabeErstellenEinfachAntwortViewPnl.add(southPnl,BorderLayout.SOUTH);
        AufgabeErstellenEinfachAntwortFrame.add(AufgabeErstellenEinfachAntwortViewPnl);


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
        AufgabeErstellenEinfachAntwortFrame.dispose();
        AufgabeErstellenStartView.main(null);
    }

    private void speichern() {

        //FUNKTIONALITÄT MISSING

        AufgabeErstellenEinfachAntwortFrame.dispose();
        DozentAnsicht.main(null);

    }
}
