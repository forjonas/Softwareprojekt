package entity.aufgabensammlung;

import entity.aufgabe.Aufgabe;
import entity.loesung.userloesung.Userloesung;
import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstrakte Superklasse für Sammlungen von Aufgaben
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Aufgabensammlung {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long aufgabensammlungId;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "aufgabensammlung_aufgabe",
            joinColumns = @JoinColumn(name = "aufgabensammlung_id"),
            inverseJoinColumns = @JoinColumn(name = "aufgabe_id"))
    private List<Aufgabe> aufgaben;
    private int gesamtzeit;
    private int gesamtpunktzahl;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "aufgabensammlung")
    private List<Userloesung> userloesungen;

    /**
     * Leerer Konstruktor für Klasse Aufgabensammlung
     */
    public Aufgabensammlung() {
        this.aufgaben = new LinkedList<Aufgabe>();
        this.userloesungen = new LinkedList<Userloesung>();
    }

    /**
     * Konstruktor für Klasse Aufgabensammlung
     *
     * @param aufgaben Aufgaben, die in der Aufgabensammlung enthalten sind
     */
    public Aufgabensammlung(List<Aufgabe> aufgaben) {
        this.aufgaben = new LinkedList<Aufgabe>();
        this.aufgaben.addAll(aufgaben);
        this.userloesungen = new LinkedList<Userloesung>();
        aktualisiereGesamtpunktzahl();
        aktualisierteGesamtzeit();
    }

    /**
     * Gibt die Liste der Aufgaben zurück
     *
     * @return Liste der Aufgaben
     */
    public List<Aufgabe> getAufgaben() {
        return aufgaben;
    }

    /**
     * Fügt eine Aufgabe zur Liste der Aufgaben hinzu
     *
     * @param aufgabe Aufgabe, die hinzugefügt werden soll
     */
    public void addAufgabe(Aufgabe aufgabe) {
        aufgaben.add(aufgabe);
        gesamtpunktzahl += aufgabe.getPunktewert();
        gesamtzeit += aufgabe.getBearbeitungszeit();
    }

    /**
     * Entfernt eine Aufgabe aus der Liste der Aufgaben
     *
     * @param aufgabe Aufgabe, die entfernt werden soll
     */
    public void removeAufgabe(Aufgabe aufgabe) {
        aufgaben.remove(aufgabe);
    }

    /**
     * Setzt die Liste der Aufgaben
     *
     * @param aufgaben Liste der Aufgaben
     */
    public void setAufgaben(List<Aufgabe> aufgaben) {
        this.aufgaben = aufgaben;
        aktualisiereGesamtpunktzahl();
        aktualisierteGesamtzeit();
    }

    /**
     * Gibt die Liste der Userlösungen, die im Kontext dieser Aufgabensammlung erstellt wurden, zurück
     *
     * @return Liste der Userlösungen, die im Kontext dieser Aufgabensammlung erstellt wurden
     */
    public List<Userloesung> getUserloesungen() {
        return userloesungen;
    }

    /**
     * Fügt eine Userlösung zur Liste der Userlösungen, die im Kontext dieser Aufgabensammlung erstellt wurden, hinzu
     *
     * @param userloesung Userlösung, die hinzugefügt werden soll
     */
    public void addUserloesung(Userloesung userloesung) {
        this.userloesungen.add(userloesung);
    }

    /**
     * Entfernt eine Userlösung aus der Liste der Userlösungen, die im Kontext dieser Aufgabensammlung erstellt wurden
     *
     * @param userloesung Userlösung, die entfernt werden soll
     */
    public void removeUserloesung(Userloesung userloesung) {
        this.userloesungen.remove(userloesung);
    }

    /**
     * Setzt die Liste der Userlösungen, die im Kontext dieser Aufgabensammlung erstellt wurden
     *
     * @param userloesungen Liste der Userlösungen, die im Kontext dieser Aufgabensammlung erstellt wurden
     */
    public void setUserloesungen(List<Userloesung> userloesungen) {
        this.userloesungen = userloesungen;
    }

    /**
     * Gibt die Anzahl der Aufgaben in der Aufgabensammlung zurück
     *
     * @return Anzahl der Aufgaben in der Aufgabensammlung
     */
    public int getAnzahlAufgaben() {
        if (aufgaben != null) {
            return aufgaben.size();
        } else {
            return 0;
        }
    }

    /**
     * Gibt die gesamte Bearbeitungszeit der Aufgabensammlung zurück
     *
     * @return gesamte Bearbeitungszeit der Aufgabensammlung
     */
    public int getGesamtzeit() {
        return gesamtzeit;
    }

    /**
     * Hilfsmethode zum Aktualisieren der Gesamtzeit der Aufgabensammlung
     */
    private void aktualisierteGesamtzeit() {
        gesamtzeit = 0;
        for (Aufgabe a : getAufgaben()) {
            gesamtzeit += a.getBearbeitungszeit();
        }
    }

    /**
     * Gibt gesamte Punktzahl der Aufgabensammlung zurück
     *
     * @return gesamte Punktzahl der Aufgabensammlung
     */
    public int getGesamtpunktzahl() {
        return gesamtpunktzahl;
    }

    /**
     * Hilfsmethode zum Aktualisieren der Gesamtpunktzahl der Aufgabensammlung
     */
    private void aktualisiereGesamtpunktzahl() {
        gesamtpunktzahl = 0;
        for (Aufgabe a : getAufgaben()) {
            gesamtpunktzahl += a.getPunktewert();
        }
    }

}