import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainingGenerierenView implements ActionListener {

    /**------Attribute------ */
    private JFrame trainingGenerierenView;
    private JPanel trainingGenerierenPnl;

    private String[] dauerArray=            {"5 Minuten","10 Minuten","15 Minuten","20 Minuten"};
    private String[] schwierigkeitArray=    {"Leicht","Mittel","Schwer"};
    private String[] aufgabenTypArray=      {"Multiple Choice","UML","Code","Einfach Antwort"};
    private String[] anzahlAufgabenArray=   {"1-3","3-5","5-7","7-10"};
    private String[] kategorieArray=        {"Software Architektur","Programmierung","Grundlagen"};

    private JComboBox<String> dauerCBox;
    private JComboBox<String> schwierigkeitCBox;
    private JComboBox<String> aufgabenTypCBox;
    private JComboBox<String> anzahlAufgabenCBox;
    private JComboBox<String> kategorieCBox;

    private JButton zurueckStudentViewBtn;
    private JButton trainingStartenBtn;
    JFrame studentMainViewTest;

    /**------Attribute Ende------ */


    public TrainingGenerierenView(JFrame studentMainView)
    {
        studentMainViewTest=studentMainView;
        trainingGenerierenView=new JFrame();
        trainingGenerierenPnl=new JPanel(new BorderLayout());
        JPanel tempCenterPnl= new JPanel(new GridLayout(7,2,16,16));

        dauerCBox=          new JComboBox<>(dauerArray);
        schwierigkeitCBox=  new JComboBox<>(schwierigkeitArray);
        aufgabenTypCBox=    new JComboBox<>(aufgabenTypArray);
        anzahlAufgabenCBox= new JComboBox<>(anzahlAufgabenArray);
        kategorieCBox=      new JComboBox<>(kategorieArray);


        trainingStartenBtn = new JButton("Training Generieren");
        trainingStartenBtn.addActionListener(this);
        trainingStartenBtn.setPreferredSize(new Dimension(80,40));
        zurueckStudentViewBtn =new JButton("Zurück");
        zurueckStudentViewBtn.addActionListener(this);
        zurueckStudentViewBtn.setPreferredSize(new Dimension(80,40));

        tempCenterPnl.add(zurueckStudentViewBtn);       tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(dauerCBox);                   tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(schwierigkeitCBox);           tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(aufgabenTypCBox);             tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(anzahlAufgabenCBox);          tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(kategorieCBox);               tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(new JLabel(""));          tempCenterPnl.add(trainingStartenBtn);

        trainingGenerierenPnl.add(tempCenterPnl,BorderLayout.CENTER);
        trainingGenerierenView.add(trainingGenerierenPnl);

        trainingGenerierenView.setSize(600,600);
        trainingGenerierenView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
        trainingGenerierenView.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.zurueckStudentViewBtn){
            trainingGenerierenView.dispose();
            studentMainViewTest.setVisible(true);
        }else if(e.getSource()==this.trainingStartenBtn){
            trainingGenerierenView.setVisible(false);
        }
    }
}
