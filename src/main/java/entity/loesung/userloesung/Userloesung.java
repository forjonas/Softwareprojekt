package entity.loesung.userloesung;

import entity.aufgabe.Aufgabe;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.benutzer.Benutzer;
import entity.loesung.Loesung;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

/**
 * Abstrakte Superklasse für Userlösungen
 *
 * @author Timo Joswig
 * @version 26.05.22
 */
@Entity
public abstract class Userloesung extends Loesung {
    private boolean hinweisVerwendet;
    private int erreichtePunkte = 0;
    @ManyToOne(cascade = CascadeType.PERSIST)
    protected Aufgabe aufgabe;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Benutzer userloesungErsteller;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Aufgabensammlung aufgabensammlung;

    /**
     * Leerer Konstruktor für Klasse Userlösung
     */
    public Userloesung() {
        super();
    }

    /**
     * Konstruktor für Klasse Userlösung
     *
     * @param aufgabe              zur Lösung gehörende Aufgabe
     * @param hinweisVerwendet     gibt an, ob der Lösungshinweis verwendet wurde
     * @param userloesungErsteller Ersteller der Userlösung
     * @param aufgabensammlung     Aufgabensammlung, in deren Kontext die Userlösung erstellt wurde
     */
    public Userloesung(Aufgabe aufgabe, boolean hinweisVerwendet, Benutzer userloesungErsteller, Aufgabensammlung aufgabensammlung) {
        super();
        this.aufgabe = aufgabe;
        this.hinweisVerwendet = hinweisVerwendet;
        this.userloesungErsteller = userloesungErsteller;
        this.aufgabensammlung = aufgabensammlung;
    }

    /**
     * Gibt zurück, ob der Lösungshinweis verwendet wurde
     *
     * @return Wahrheitswert, der angibt, ob der Lösungshinweis verwendet wurde
     */
    public boolean isHinweisVerwendet() {
        return hinweisVerwendet;
    }

    /**
     * Setzt den Wahrheitswert, der angibt, ob der Lösungshinweis verwendet wurde
     *
     * @param hinweisVerwendet gibt an, ob der Lösungshinweis verwendet wurde
     */
    public void setHinweisVerwendet(boolean hinweisVerwendet) {
        this.hinweisVerwendet = hinweisVerwendet;
    }

    /**
     * Gibt die zur Lösung gehörende Aufgabe zurück
     *
     * @return zur Lösung gehörende Aufgabe
     */
    public Aufgabe getAufgabe() {
        return aufgabe;
    }

    /**
     * Setzt die zur Lösung gehörende Aufgabe
     *
     * @return zur Lösung gehörende Aufgabe
     */
    public abstract void setAufgabe(Aufgabe aufgabe) throws Exception;

    /**
     * Gibt den Benutzer, der die Userlösung erstellt hat, zurück
     *
     * @return Benutzer, der die Userlösung erstellt hat
     */
    public Benutzer getUserloesungErsteller() {
        return userloesungErsteller;
    }

    /**
     * Setzt den Benutzer, der die Userlösung erstellt hat
     *
     * @param userloesungErsteller Benutzer, der die Userlösung erstellt hat
     */
    public void setUserloesungErsteller(Benutzer userloesungErsteller) {
        this.userloesungErsteller = userloesungErsteller;
    }

    /**
     * Gibt die Aufabensammlung, in deren Kontext die Userlösung erstellt wurde, zurück
     *
     * @return Aufabensammlung, in deren Kontext die Userlösung erstellt wurde
     */
    public Aufgabensammlung getAufgabensammlung() {
        return aufgabensammlung;
    }

    /**
     * Setzt die Aufabensammlung, in deren Kontext die Userlösung erstellt wurde
     *
     * @param aufgabensammlung Aufabensammlung, in deren Kontext die Userlösung erstellt wurde
     */
    public void setAufgabensammlung(Aufgabensammlung aufgabensammlung) {
        this.aufgabensammlung = aufgabensammlung;
    }

    /**
     * Setzt die vom User bei dieser Aufgabe erreichten Punkte
     *
     * @param punkte Die vom User erreichten Punkte
     */
    public void setErreichtePunkte(int punkte) {
        this.erreichtePunkte = punkte;
    }

    /**
     * Gibt die vom User bei dieser Aufgabe erreichten Punkte zurück
     *
     * @return erreichtePunkte des Users
     */
    public int getErreichtePunkte() {
        return erreichtePunkte;
    }

}