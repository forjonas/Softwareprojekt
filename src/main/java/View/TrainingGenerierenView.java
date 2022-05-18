package View;

import app.TrainingApp;
import entity.aufgabe.Aufgabe;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import persistence.DatabaseService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainingGenerierenView extends JFrame implements ActionListener {

    /**
     * ------Attribute------
     */

    persistence.DatabaseService ds = DatabaseService.getInstance();
    private JPanel trainingGenerierenPnl;
    Training training;

    private String[] dauerArray = {"10", "20", "30", "40", "50", "60", "70", "80", "90"};
    private String[] schwierigkeitArray = {"Leicht", "Mittel", "Schwer"};
    private String[] aufgabenTypArray = {"Multiple Choice", "UML", "Code", "Einfach Antwort"};
    private String[] kategorieArray = {"Software Engineering", "Java Programmierung", "Java Grundlagen", "Klassendiagramme", "Datenbanken"};

    private JComboBox<String> dauerCBox;
    private JComboBox<String> schwierigkeitCBox;
    private JComboBox<String> aufgabenTypCBox;


    private JCheckBox aufgabenTypMCCBox = new JCheckBox("Multiple Choice");
    private JCheckBox aufgabenTypUMLCBox = new JCheckBox("UML");
    private JCheckBox aufgabenTypCodeCBox = new JCheckBox("Code");
    private JCheckBox aufgabenTypEACBox = new JCheckBox("Einfach Antwort");

    private JComboBox<String> kategorieCBox;

    private JButton zurueckStudentViewBtn;
    private JButton trainingStartenBtn;
    private JFrame studentMainViewTest;
    private Benutzer benutzer;

    /**
     * ------Attribute Ende------
     */


    public TrainingGenerierenView(JFrame studentMainView, Benutzer benutzer) {
        this.benutzer = benutzer;
        JPanel tempAufgabenTyp = new JPanel((new GridLayout(4, 1, 4, 4)));
        studentMainViewTest = studentMainView;
        trainingGenerierenPnl = new JPanel(new BorderLayout());
        JPanel tempCenterPnl = new JPanel(new GridLayout(7, 2, 16, 16));

        dauerCBox = new JComboBox<>(dauerArray);
        schwierigkeitCBox = new JComboBox<>(schwierigkeitArray);
        aufgabenTypCBox = new JComboBox<>(aufgabenTypArray);
        kategorieCBox = new JComboBox<>(kategorieArray);

        tempAufgabenTyp.add(aufgabenTypCodeCBox);
        tempAufgabenTyp.add(aufgabenTypEACBox);
        tempAufgabenTyp.add(aufgabenTypMCCBox);
        tempAufgabenTyp.add(aufgabenTypUMLCBox);

        //createTestAufgaben();

        trainingStartenBtn = new JButton("Training Generieren");
        trainingStartenBtn.addActionListener(this);
        trainingStartenBtn.setPreferredSize(new Dimension(80, 40));
        zurueckStudentViewBtn = new JButton("Zurück");
        zurueckStudentViewBtn.addActionListener(this);
        zurueckStudentViewBtn.setPreferredSize(new Dimension(80, 40));

        tempCenterPnl.add(zurueckStudentViewBtn);
        tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(dauerCBox);
        tempCenterPnl.add(new JLabel("Die Dauer des Trainings in Minuten wählen."));
        tempCenterPnl.add(schwierigkeitCBox);
        tempCenterPnl.add(new JLabel("Die Schwierigkeit der Aufgaben wählen."));
        tempCenterPnl.add(tempAufgabenTyp);
        tempCenterPnl.add(new JLabel("Die Aufgabentypen wählen."));
        tempCenterPnl.add(kategorieCBox);
        tempCenterPnl.add(new JLabel("Die Kategorie der Aufgaben wählen."));
        tempCenterPnl.add(new JLabel(""));
        tempCenterPnl.add(trainingStartenBtn);

        trainingGenerierenPnl.add(tempCenterPnl, BorderLayout.CENTER);
        this.add(trainingGenerierenPnl);

        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//WindowConstants bezieht sich explizit nur auf das Window, nicht auf JFrame.
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }


    public String getValueCBox(JComboBox combo) {
        return (String) combo.getSelectedItem();
    }

    public void createNewTraining() {
        List<Aufgabe> aufgabenTraining= new LinkedList<Aufgabe>();
        int trainingsdauer = 0;
        List<Aufgabe> aufgabenList = ds.readAufgabenmitKatSchwierigkeit(readKategorie(), schwierigkeitsgradSetzen());
        if (aufgabenList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Es gibt keine Aufgabe die diesen Parametern entspricht.", "ERROR", JOptionPane.WARNING_MESSAGE);
        } else {
            for (int i = 0; i < aufgabenList.size(); i++) {
                if (roundUp10(trainingsdauer) == Integer.parseInt(getValueCBox(dauerCBox))) {
                    training = new Training(aufgabenTraining, trainingsdauer, readKategorie(), schwierigkeitsgradSetzen(), aufgabenTypenSetzen(), benutzer);
                    TrainingApp trainingApp = new TrainingApp(training, benutzer, studentMainViewTest);
                    trainingApp.zeigeAktuelleAufgabe();
                    this.setVisible(false);
                } else if (roundUp10(trainingsdauer) > Integer.parseInt(getValueCBox(dauerCBox))) {
                    JOptionPane.showMessageDialog(this, "Es gibt keine Aufgabe die diesen Parametern entspricht.", "ERROR", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (i == aufgabenList.size() - 1) {
                        JOptionPane.showMessageDialog(this, "Es gibt nicht genug Aufgaben, um ein Training mit den gesetzten Parametern zu  erstellen.", "ERROR", JOptionPane.WARNING_MESSAGE);
                    } else {
                        switch (aufgabenList.get(i).getAufgabentyp()) {
                            case MultipleChoice: {
                                if (aufgabenTypMCCBox.isSelected() || unSelected()) {//if(Selected==true oder unSelected==true) damit alle Typen ausgewählt werden wenn keine checkbox gesetzt ist
                                    aufgabenTraining.add(aufgabenList.get(i));
                                    trainingsdauer += aufgabenList.get(i).getBearbeitungszeit();
                                    System.out.println("MultipleChoice gewählt");
                                    System.out.println(aufgabenList.get(i));
                                    break;
                                }
                            }
                            case Einfachantwort: {
                                if (aufgabenTypEACBox.isSelected() || unSelected()) {//if(Selected==true oder unSelected==true) damit alle Typen ausgewählt werden wenn keine checkbox gesetzt ist
                                    aufgabenTraining.add(aufgabenList.get(i));
                                    trainingsdauer += aufgabenList.get(i).getBearbeitungszeit();
                                    System.out.println(aufgabenList.get(i));
                                    break;
                                }
                            }
                            case Programmieren: {
                                if (aufgabenTypCodeCBox.isSelected() || unSelected()) {//if(Selected==true oder unSelected==true) damit alle Typen ausgewählt werden wenn keine checkbox gesetzt ist
                                    aufgabenTraining.add(aufgabenList.get(i));
                                    trainingsdauer += aufgabenList.get(i).getBearbeitungszeit();
                                    System.out.println(aufgabenList.get(i));
                                    break;
                                }
                            }
                            case Design: {
                                if (aufgabenTypUMLCBox.isSelected() || unSelected()) {//if(Selected==true oder unSelected==true) damit alle Typen ausgewählt werden wenn keine checkbox gesetzt ist
                                    aufgabenTraining.add(aufgabenList.get(i));
                                    trainingsdauer += aufgabenList.get(i).getBearbeitungszeit();
                                    System.out.println(aufgabenList.get(i));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean unSelected()//gibt true zurück wenn keine checkbox ausgewählt wurde
    {
        if (!aufgabenTypMCCBox.isSelected() && !aufgabenTypUMLCBox.isSelected() && !aufgabenTypEACBox.isSelected() && !aufgabenTypCodeCBox.isSelected()) {
            System.out.println("Multi ausgewählt");
            return true;
        }
        return false;
    }

    public int roundUp10(int zahl) {
        int tempZahl = zahl / 10;
        Math.ceil(tempZahl);
        return tempZahl * 10;
    }


    public Kategorie readKategorie() {
        switch (getValueCBox(kategorieCBox)) {
            case "Software Engineering":
                return Kategorie.Software_Engineering;
            case "Java Programmierung":
                return Kategorie.Java_Programmierung;
            case "Java Grundlagen":
                return Kategorie.Java_Grundlagen;
            case "Klassendiagramme":
                return Kategorie.Klassendiagramme;
            case "Datenbanken":
                return Kategorie.Datenbanken;
        }
        return null;
    }

    public Schwierigkeitsgrad schwierigkeitsgradSetzen() {
        switch (getValueCBox(schwierigkeitCBox)) {
            case "leicht":
                return Schwierigkeitsgrad.Leicht;
            case "mittel":
                return Schwierigkeitsgrad.Mittel;
            case "schwer":
                return Schwierigkeitsgrad.Schwer;
        }
        return Schwierigkeitsgrad.Leicht;
    }

    public List<Aufgabentyp> aufgabenTypenSetzen() {
        List<Aufgabentyp> list = new ArrayList<>();
        if (aufgabenTypCodeCBox.isSelected()) {
            list.add(Aufgabentyp.Programmieren);
        } else if (aufgabenTypEACBox.isSelected()) {
            list.add(Aufgabentyp.Einfachantwort);
        } else if (aufgabenTypMCCBox.isSelected()) {
            list.add(Aufgabentyp.MultipleChoice);
        } else if (aufgabenTypUMLCBox.isSelected()) {
            list.add(Aufgabentyp.Design);
        } else if (!aufgabenTypCodeCBox.isSelected() && !aufgabenTypEACBox.isSelected() && !aufgabenTypMCCBox.isSelected() && !aufgabenTypUMLCBox.isSelected()) {
            list.add(Aufgabentyp.MultipleChoice);
            list.add(Aufgabentyp.Design);
            list.add(Aufgabentyp.Programmieren);
            list.add(Aufgabentyp.Einfachantwort);
        }
        return list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zurueckStudentViewBtn) {
            this.dispose();
            studentMainViewTest.setVisible(true);
        } else if (e.getSource() == this.trainingStartenBtn) {
            createNewTraining();
        }
    }
}
