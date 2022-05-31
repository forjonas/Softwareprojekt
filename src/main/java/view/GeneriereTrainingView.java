package view;

import entity.aufgabe.Aufgabe;
import entity.benutzer.Benutzer;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import persistence.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ansicht in der ein Schwierigkeitsgrad und eine Kategorie gewählt werden kann, um dann eine AufgabenAuswaehlenTrainingView mit diesen Werten zu erstellen
 *
 * @author Martin Bergen
 * @version 23.05.22
 */
public class GeneriereTrainingView extends JFrame implements ActionListener {

    private JFrame jframe;
    private Benutzer benutzer;
    private JButton btnWaehlen;
    private JButton btnAbbrechen;
    private JComboBox<String> cbSchwierigkeit = new JComboBox<>(Schwierigkeitsgrad.getCodeArray());
    private JComboBox<String> cbKategorie = new JComboBox<>(Kategorie.getCodeArray());

    /**
     * Konstruktor der Klasse GeneriereTrainingView, der das Fenster und die ausgewählten Bausteine miteinander verbindet und erstellt.
     *
     * @param jframe   JFrame, aus dem dieser Konstruktor aufgerufen wurde, wird an die AufgabenAuswaehlenTrainingView weitergegeben
     * @param benutzer Benutzer, der diesen Konstruktor/diese Funktion aufruft, wird an die AufgabenAuswaehlenTrainingView weitergegeben
     */
    public GeneriereTrainingView(JFrame jframe, Benutzer benutzer) {
        setTitle("Kategorie und Schwierigkeit wählen.");
        this.jframe = jframe;
        this.benutzer = benutzer;
        JPanel tempPanel = new JPanel();
        JPanel waehlenPnl = new JPanel(new BorderLayout());
        JPanel tempSouthPanel = new JPanel(new FlowLayout());
        tempPanel.add(cbKategorie);
        tempPanel.add(cbSchwierigkeit);
        btnWaehlen = new JButton("OK");
        btnWaehlen.addActionListener(this);
        btnAbbrechen = new JButton("Abbrechen");
        btnAbbrechen.addActionListener(this);
        tempPanel.add(btnWaehlen);
        tempSouthPanel.add(btnAbbrechen);
        waehlenPnl.add(tempPanel, BorderLayout.CENTER);
        waehlenPnl.add(tempSouthPanel, BorderLayout.SOUTH);
        this.add(waehlenPnl);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 150));
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    /**
     * Eine Methode, die den Wert der JComboBox als Kategorie-Enum zurückgibt.
     *
     * @return Kategorie
     */
    public Kategorie readKategorie() {
//        switch (getValueCBox(cbKategorie)) {
//            case "Software Engineering":
//                return Kategorie.Software_Engineering;
//            case "Java Programmierung":
//                return Kategorie.Java_Programmierung;
//            case "Java Grundlagen":
//                return Kategorie.Java_Grundlagen;
//            case "Klassendiagramme":
//                return Kategorie.Klassendiagramme;
//            case "Datenbanken":
//                return Kategorie.Datenbanken;
//            default:
//                return null;
//        }
        String[] kategorieCodes = Kategorie.getCodeArray();
        String cbKategorieWert = getValueCBox(cbKategorie);
        for(int i = 0; i<kategorieCodes.length; i++) {
            if(cbKategorieWert.equals(kategorieCodes[i])) {
                return Kategorie.values()[i];
            }
        }
        return null;
    }

    /**
     * Eine Methode, die den Wert der JComboBox als Schwierigkeitsgrad-Enum zurückgibt.
     *
     * @return Schwierigkeitsgrad
     */
    public Schwierigkeitsgrad schwierigkeitsgradSetzen() {
//        switch (getValueCBox(cbSchwierigkeit)) {
//            case "leicht":
//                return Schwierigkeitsgrad.Leicht;
//            case "mittel":
//                return Schwierigkeitsgrad.Mittel;
//            case "schwer":
//                return Schwierigkeitsgrad.Schwer;
//            default:
//                return null;
//        }
        String[] schwierigkeitsgradCodes = Schwierigkeitsgrad.getCodeArray();
        String cbSchwierigkeitsgradWert = getValueCBox(cbSchwierigkeit);
        for(int i = 0; i<schwierigkeitsgradCodes.length; i++) {
            if(cbSchwierigkeitsgradWert.equals(schwierigkeitsgradCodes[i])) {
                return Schwierigkeitsgrad.values()[i];
            }
        }
        return null;
    }

    /**
     * Eine Methode die den ausgewählten Wert der JComboBox als String ausgibt.
     *
     * @param combo Die JComboBox, dessen Wert wir benötigen
     * @return gibt den gewählten Wert als String zurück
     */
    public String getValueCBox(JComboBox combo) {
        return (String) combo.getSelectedItem();
    }

    /**
     * Überprüft ob einer der Bottuns betätigt wurde und führt dementsprechen, die gesetze Methode aus
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnWaehlen) {
            Kategorie kategorie = readKategorie();
            Schwierigkeitsgrad schwierigkeitsgrad = schwierigkeitsgradSetzen();
            System.out.println("actionPerformed Kategorie:"+kategorie+" Schwierigkeitsgrad: "+schwierigkeitsgrad);
            List<Aufgabe> aufgabenliste = DatabaseService.getInstance().readAufgabenmitKatSchwierigkeit(kategorie, schwierigkeitsgradSetzen());
            if (aufgabenliste.size() > 0) {
                new AufgabenAuswaehlenTrainingView(jframe, benutzer, aufgabenliste, kategorie, schwierigkeitsgrad);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Es gibt keine Aufgaben mit diesen Parametern", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == this.btnAbbrechen) {
            jframe.setVisible(true);
            this.dispose();
        }
    }
}
