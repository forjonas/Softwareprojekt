package entity.aufgabe;

import entity.aufgabensammlung.Aufgabensammlung;
import entity.benutzer.Dozent;
import entity.enums.Aufgabentyp;
import entity.enums.Kategorie;
import entity.enums.Schwierigkeitsgrad;
import entity.loesung.musterloesung.Musterloesung;
import entity.loesung.userloesung.Userloesung;
import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstrakte Superklasse für Aufgaben
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Aufgabe {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long aufgabenId;
    private int bearbeitungszeit;
    @Lob
    private byte[] aufgabenstellungsbild;
    private Kategorie kategorie;
    private int punktewert;
    private Schwierigkeitsgrad schwierigkeitsgrad;
    private String textbeschreibung;
    private String name;
    protected Aufgabentyp aufgabentyp;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "aufgabenErsteller_benutzerid")
    private Dozent aufgabenErsteller;
    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "aufgaben")
    private List<Aufgabensammlung> verwendungen;
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "aufgabe")
    protected Musterloesung musterloesung;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "aufgabe")
    protected List<Userloesung> userloesungen;

    /**
     * Leerer Konstruktor für Klasse Aufgabe
     */
    public Aufgabe() {
        verwendungen = new LinkedList<Aufgabensammlung>();
        userloesungen = new LinkedList<Userloesung>();
    }

    /**
     * Konstruktor für Klasse Aufgabe
     *
     * @param bearbeitungszeit      Bearbeitungszeit der Aufgabe
     * @param aufgabenstellungsbild Bild, das Teil der Aufgabenstellung ist
     * @param kategorie             Kategorie der Aufgabe
     * @param punktewert            Punktewert der Aufgabe
     * @param schwierigkeitsgrad    Schwierigkeitsgrad der Aufgabe
     * @param textbeschreibung      Textbeschreibung der Aufgabe
     * @param aufgabenErsteller     Dozent, der die Aufgabe erstellt hat
     */
    public Aufgabe(int bearbeitungszeit, byte[] aufgabenstellungsbild, Kategorie kategorie, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, Dozent aufgabenErsteller, Musterloesung musterloesung) {
        this.bearbeitungszeit = bearbeitungszeit;
        this.aufgabenstellungsbild = aufgabenstellungsbild;
        this.kategorie = kategorie;
        this.punktewert = punktewert;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.textbeschreibung = textbeschreibung;
        this.name = name;
        this.aufgabenErsteller = aufgabenErsteller;
        this.musterloesung = musterloesung;
        verwendungen = new LinkedList<Aufgabensammlung>();
        this.userloesungen = new LinkedList<Userloesung>();
    }

    /**
     * Konstruktor für Klasse Aufgabe (ohne Musterlösung, da bei der 1:1 Beziehung zwischen Aufgabe und Musterlösung
     * ein Objekt zuerst, ohne Beziehungspartner, erstellt werden muss)
     *
     * @param bearbeitungszeit      Bearbeitungszeit der Aufgabe
     * @param aufgabenstellungsbild Bild, das Teil der Aufgabenstellung ist
     * @param kategorie             Kategorie der Aufgabe
     * @param punktewert            Punktewert der Aufgabe
     * @param schwierigkeitsgrad    Schwierigkeitsgrad der Aufgabe
     * @param textbeschreibung      Textbeschreibung der Aufgabe
     * @param aufgabenErsteller     Dozent, der die Aufgabe erstellt hat
     */
    public Aufgabe(int bearbeitungszeit, byte[] aufgabenstellungsbild, Kategorie kategorie, int punktewert, Schwierigkeitsgrad schwierigkeitsgrad, String textbeschreibung, String name, Dozent aufgabenErsteller) {
        this.bearbeitungszeit = bearbeitungszeit;
        this.aufgabenstellungsbild = aufgabenstellungsbild;
        this.kategorie = kategorie;
        this.punktewert = punktewert;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.textbeschreibung = textbeschreibung;
        this.name = name;
        this.aufgabenErsteller = aufgabenErsteller;
        verwendungen = new LinkedList<Aufgabensammlung>();
        this.userloesungen = new LinkedList<Userloesung>();
    }

    /**
     * Gibt die Bearbeitungszeit der Aufgabe zurück
     *
     * @return Bearbeitungszeit der Aufgabe
     */
    public int getBearbeitungszeit() {
        return bearbeitungszeit;
    }

    /**
     * Setzt die Bearbeitungszeit der Aufgabe
     *
     * @param bearbeitungszeit Bearbeitungszeit der Aufgabe
     */
    public void setBearbeitungszeit(int bearbeitungszeit) {
        this.bearbeitungszeit = bearbeitungszeit;
    }

    /**
     * Gibt das Bild, das Teil der Aufgabenstellung ist zurück
     *
     * @return Bild, das Teil der Aufgabenstellung ist
     */
    public byte[] getAufgabenstellungsbild() {
        return aufgabenstellungsbild;
    }

    /**
     * Setzt das Bild, das Teil der Aufgabenstellung ist
     *
     * @param aufgabenstellungsbild Bild, das Teil der Aufgabenstellung ist
     */
    public void setAufgabenstellungsbild(byte[] aufgabenstellungsbild) {
        this.aufgabenstellungsbild = aufgabenstellungsbild;
    }

    /**
     * Gibt die Kategorie der Aufgabe zurück
     *
     * @return Kategorie der Aufgabe
     */
    public Kategorie getKategorie() {
        return kategorie;
    }

    /**
     * Setzt die Kategorie der Aufgabe
     *
     * @param kategorie Kategorie der Aufgabe
     */
    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    /**
     * Gibt den Punktewert der Aufgabe zurück
     *
     * @return Punktewert der Aufgabe
     */
    public int getPunktewert() {
        return punktewert;
    }

    /**
     * Setzt den Punktewert der Aufgabe
     *
     * @param punktewert Punktewert der Aufgabe
     */
    public void setPunktewert(int punktewert) {
        this.punktewert = punktewert;
    }

    /**
     * Gibt den Schwierigkeitsgrad der Aufgabe zurück
     *
     * @return Schwierigkeitsgrad der Aufgabe
     */
    public Schwierigkeitsgrad getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    /**
     * Setzt den Schwierigkeitsgrad der Aufgabe
     *
     * @param schwierigkeitsgrad Schwierigkeitsgrad der Aufgabe
     */
    public void setSchwierigkeitsgrad(Schwierigkeitsgrad schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    /**
     * Gibt die Textbeschreibung der Aufgabe zurück
     *
     * @return Textbeschreibung der Aufgabe
     */
    public String getTextbeschreibung() {
        return textbeschreibung;
    }

    /**
     * Setzt die Textbeschreibung der Aufgabe
     *
     * @param textbeschreibung Textbeschreibung der Aufgabe
     */
    public void setTextbeschreibung(String textbeschreibung) {
        this.textbeschreibung = textbeschreibung;
    }

    /**
     * Gibt den Aufgabentyp der Aufgabe zurück
     *
     * @return Aufgabentyp der Aufgabe
     */
    public Aufgabentyp getAufgabentyp() {
        return aufgabentyp;
    }

    /**
     * Gibt den Namen der Aufgabe zurück
     *
     * @return Name der Aufgabe
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen der Aufgabe
     *
     * @param name Name der Aufgabe
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt den Dozenten, der die Aufgabe erstellt hat, zurück
     *
     * @return Dozent, der die Aufgabe erstellt hat
     */
    public Dozent getAufgabenErsteller() {
        return aufgabenErsteller;
    }

    /**
     * Setzt den Dozenten, der die Aufgabe erstellt hat
     *
     * @param aufgabenErsteller Dozent, der die Aufgabe erstellt hat
     */
    public void setAufgabenErsteller(Dozent aufgabenErsteller) {
        this.aufgabenErsteller = aufgabenErsteller;
    }

    /**
     * Gibt die Aufgabensammlungen, in denen die Aufgabe verwendet wird, zurück
     *
     * @return Aufgabensammlungen, in denen die Aufgabe verwendet wird
     */
    public List<Aufgabensammlung> getVerwendungen() {
        return verwendungen;
    }

    /**
     * Fügt eine Aufgabensammlung zur Liste der Aufgabensammlungen, in denen die Aufgabe verwendet wird, hinzu
     *
     * @param verwendung Aufgabensammlung in der die Aufgabe verwendet wird
     */
    public void addVerwendung(Aufgabensammlung verwendung) {
        verwendungen.add(verwendung);
    }

    /**
     * Entfernt eine Aufgabensammlung aus der Liste der Aufgabensammlungen, in denen die Aufgabe verwendet wird
     *
     * @param verwendung Aufgabensammlung in der die Aufgabe verwendet wird
     */
    public void removeVerwendung(Aufgabensammlung verwendung) {
        verwendungen.remove(verwendung);
    }

    /**
     * Setzt die Aufgabensammlungen, in denen die Aufgabe verwendet wird
     *
     * @param verwendungen Aufgabensammlungen, in denen die Aufgabe verwendet wird
     */
    public void setVerwendungen(List<Aufgabensammlung> verwendungen) {
        this.verwendungen = verwendungen;
    }

    /**
     * Gibt die Musterlösung der Aufgabe zurück
     *
     * @return Musterlösung der Aufgabe
     */
    public Musterloesung getMusterloesung() {
        return this.musterloesung;
    }

    /**
     * Setzt die Musterlösung der Aufgabe
     *
     * @param musterloesung Musterlösung der Aufgabe
     */
    public abstract void setMusterloesung(Musterloesung musterloesung) throws Exception;

    /**
     * Gibt die Liste der Userlösungen der Aufgabe zurück
     *
     * @return Liste der Userlösungen der Aufgabe
     */
    public List<Userloesung> getUserloesungen() {
        return this.userloesungen;
    }

    /**
     * Fügt eine Userlösung zur Liste der Userlösungen der Aufgabe hinzu
     *
     * @param userloesung eine Userlösung der Aufgabe
     */
    public abstract void addUserloesung(Userloesung userloesung) throws Exception;

    /**
     * Entfernt eine Userlösung aus der Liste der Userlösungen der Aufgabe
     *
     * @param userloesung eine Userlösung der Aufgabe
     */
    public void removeUserloesung(Userloesung userloesung) {
        this.userloesungen.remove(userloesung);
    }

    /**
     * Setzt die Liste der Userlösungen der Aufgabe
     *
     * @param userloesungen Liste der Userlösungen der Aufgabe
     */
    public abstract void setUserloesungen(List<Userloesung> userloesungen) throws Exception;

    /**
     * Gibt zurück, ob der übergebene Dozent die Aufgabe löschen darf
     *
     * @param dozent Dozent, für den überprüft wird, ob er die Aufgabe löschen darf
     * @return Wahrheitswert, der angibt, ob der übergebene Dozent die Aufgabe löschen darf
     */
    public boolean darfDozentAufgabeLoeschen(Dozent dozent) {
        boolean selbstErstellt = (this.aufgabenErsteller == dozent);
        boolean leereAufgabe = (this.aufgabenErsteller == null);
        boolean isAdmin = (dozent.getBenutzername().equals("admin"));
        return (selbstErstellt || leereAufgabe || isAdmin);
    }

}