import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AufgabeErstellenStartView implements ActionListener {
    JFrame AufgabeErstellenStartFrame;
    JPanel centerPnl;
    JPanel northPnl;
    JPanel southPnl;
    JButton zurueckBtn;
    JButton weiterBtn;
    BorderLayout bl;
    JComboBox <String> DDM;


    public static void main(String[] args) {
        new AufgabeErstellenStartView();
    }

    public AufgabeErstellenStartView() {
        AufgabeErstellenStartFrame = new JFrame("Aufgabe Erstellen");
        AufgabeErstellenFrameFuellen();
        AufgabeErstellenStartFrame.setSize(500, 500);
        AufgabeErstellenStartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AufgabeErstellenStartFrame.setVisible(true);
    }

    private void AufgabeErstellenFrameFuellen() {
        centerPnl =new JPanel();
        bl = new BorderLayout();
        centerPnl.setLayout(bl);
        centerPnl.setSize(50,50);

        zurueckBtn = new JButton("Zurück");
        zurueckBtn.addActionListener(this);

        weiterBtn = new JButton("Weiter");
        weiterBtn.addActionListener(this);

        String[] AufgabenTypen = {"UML","Code","Einfachantwort", "MultipleChoice"};
        DDM = new JComboBox<>(AufgabenTypen);

        northPnl = new JPanel();
        northPnl.add(zurueckBtn);

        southPnl = new JPanel();
        southPnl.add(weiterBtn);

        centerPnl.add(DDM);
        AufgabeErstellenStartFrame.add(centerPnl,BorderLayout.CENTER);
        AufgabeErstellenStartFrame.add(northPnl,BorderLayout.NORTH);
        AufgabeErstellenStartFrame.add(southPnl,BorderLayout.SOUTH);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckBtn) {
            zurueck();
        } else if (e.getSource() == this.weiterBtn) {
            weiter();

        }

    }

    private void weiter() {
        String switcher = (String) DDM.getSelectedItem();
        switch(switcher) {
            case "UML":AufgabeErstellenStartFrame.dispose();
                        AufgabeErstellenUmlView.main(null);
                        break;
            case "Code":AufgabeErstellenStartFrame.dispose();
                        AufgabeErstellenCodeView.main(null);
                        break;
            case "MultipleChoice":AufgabeErstellenStartFrame.dispose();
                                    AufgabeErstellenMultipleChoiceView.main(null);
                        break;
            case "Einfachantwort": AufgabeErstellenStartFrame.dispose() ;
                                    AufgabeErstellenEinfachAntwortView.main(null);
                        break;
        }


    }

    private void zurueck() {
        AufgabeErstellenStartFrame.dispose();
        DozentAnsicht.main(null);
    }
}
