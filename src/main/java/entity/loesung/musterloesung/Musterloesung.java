package entity.loesung.musterloesung;

import entity.aufgabe.Aufgabe;
import entity.loesung.Loesung;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

/**
 * Abstrakte Superklasse für Musterlösungen
 *
 * @author Timo Joswig
 * @version 09.05.22
 */
@Entity
public abstract class Musterloesung extends Loesung {

    @OneToOne(cascade = CascadeType.PERSIST)
    protected Aufgabe aufgabe;
    private String loesungshinweis;

    /**
     * Leerer Konstruktor für Klasse Musterlösung
     */
    public Musterloesung() {
        super();
        //Noting to do
    }

    /**
     * Konstruktor für Klasse Musterlösung
     *
     * @param aufgabe         zur Lösung gehörende Aufgabe
     * @param loesungshinweis zur Lösung gehörender Lösungshinweis
     */
    public Musterloesung(Aufgabe aufgabe, String loesungshinweis) {
        this.aufgabe = aufgabe;
        this.loesungshinweis = loesungshinweis;
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
     * Gibt den zur Lösung gehörender Lösungshinweis zurück
     *
     * @return zur Lösung gehörender Lösungshinweis
     */
    public String getLoesungshinweis() {
        return loesungshinweis;
    }

    /**
     * Setzt den zur Lösung gehörender Lösungshinweis
     *
     * @return zur Lösung gehörender Lösungshinweis
     */
    public void setLoesungshinweis(String loesungshinweis) {
        this.loesungshinweis = loesungshinweis;
    }

}