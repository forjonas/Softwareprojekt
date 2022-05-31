package view.aufgabenErstellen;

import entity.benutzer.Dozent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Die Start View zur aufgaben Erstellung
 *
 * @author Jannik Oehme
 * @version 15.05.2022 Dozentübergabe eingerichtet, Frame parameter eingefügt, inputcleaner eingefügt, change zu extends JFrame gemacht
 */
public class ErstelleAufgabeStartView extends JFrame implements ActionListener {
    private Dozent doz;
    private GridLayout gridLayout = new GridLayout(3, 1);
    private JPanel pnlCenter;
    private JPanel pnlNorth;
    private JPanel pnlSouth;
    private JButton btnZurueck;
    private JButton btnWeiter;
    private JComboBox<String> cbAufgabentypen;
    private JFrame dozentAnsichtFrame;

    /**
     * Setzt Parameter des JFrames und ruft AufgabeErstellenEinfachAntwortViewFuellen() auf.
     *
     * @param doz
     * @param dozentAnsichtFrame Konstruktor der Klasse, benötigt einen Dozenten und den vorherigen JFrame
     */
    public ErstelleAufgabeStartView(JFrame dozentAnsichtFrame, Dozent doz) {
        this.doz = doz;
        this.dozentAnsichtFrame = dozentAnsichtFrame;
        this.setName("Aufgabe Erstellen");
        AufgabeErstellenFrameFuellen();
        this.pack();
        this.setMinimumSize(new Dimension(1500, 900));
        this.setSize(1500, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((display.getSize().width - this.getSize().width) / 2, (display.getSize().height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    /**
     * Erstellt die Komponenten des JFrames sowie JPanels welche später eingefügt werden. Und fügt die Komponenten in den Frame ein.
     */
    private void AufgabeErstellenFrameFuellen() {

        pnlCenter = new JPanel();
        pnlCenter.setLayout(gridLayout);
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(300, 400, 300, 400));

        btnZurueck = new JButton("Zurück");
        btnZurueck.addActionListener(this);
        btnWeiter = new JButton("Weiter");
        btnWeiter.addActionListener(this);

        String[] AufgabenTypen = {"Designaufgabe", "Programmieraufgabe", "Einfachantwort", "MultipleChoiceaufgabe"};
        cbAufgabentypen = new JComboBox<>(AufgabenTypen);

        pnlNorth = new JPanel();
        pnlNorth.add(btnZurueck);

        pnlSouth = new JPanel();
        pnlSouth.add(btnWeiter);

        pnlCenter.add(cbAufgabentypen);
        this.add(pnlCenter, BorderLayout.CENTER);
        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlSouth, BorderLayout.SOUTH);
    }

    /**
     * Führt Aktionen der JButtons aus und ruft ggf. weitere Methoden auf.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnZurueck) {
            zurueck();
        } else if (e.getSource() == this.btnWeiter) {
            weiter();
        }
    }

    private void weiter() {
        String switcher = (String) cbAufgabentypen.getSelectedItem();
        switch (switcher) {
            case "Designaufgabe":
                this.setVisible(false);
                new ErstelleDesignaufgabeView(this, doz);
                break;
            case "Programmieraufgabe":
                this.setVisible(false);
                new ErstelleProgrammieraufgabeView(this, doz);
                break;
            case "MultipleChoiceaufgabe":
                this.setVisible(false);
                new ErstelleMultipleChoiceAufgabeView(this, doz);
                break;
            case "Einfachantwort":
                this.setVisible(false);
                ;
                new ErstelleEinfachantwortAufgabeView(this, doz);
                break;
            default:
                this.setVisible(false);
        }
    }

    /**
     * disposed diesen frame und ruft den vorherigen frame auf. Bzw setzt diesen wieder Visible.
     */
    private void zurueck() {
        this.dispose();
        dozentAnsichtFrame.setVisible(true);
    }

    /**
     * @param bearbeitungsZeit bearbeitungsZeit der gegebenen aufgabe
     * @param punkte           maximale punkte der gegebenen Aufgabe
     * @param testFrame        Frame der Aufrufenden Klasse
     *                         überprüft den input und gibt einen boolean zurück
     */
    public static boolean inputcleaner(int bearbeitungsZeit, int punkte, Frame testFrame) {
        if (bearbeitungsZeit >= 60 || bearbeitungsZeit <= 1 || punkte >= 100 || punkte <= 1) {
            JOptionPane.showMessageDialog(testFrame,
                    "Bearbeitungszeit liegt nicht zwischen 60 und 1 Minuten. Oder die Punktezahl liegt nicht zwischen 100 oder 0",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
}
