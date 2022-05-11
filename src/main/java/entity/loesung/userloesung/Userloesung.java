package entity.loesung.userloesung;

import entity.aufgabe.Aufgabe;
import entity.loesung.Loesung;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

/**
 * Abstrakte Superklasse für Userlösungen
 *
 * @author Timo Joswig
 * @version 09.05.22
 */
@Entity
public abstract class Userloesung extends Loesung {
    private boolean hinweisVerwendet;
    @ManyToOne(cascade = CascadeType.PERSIST)
    //Aus irgend einem Grund findet er diese Zeile nicht, obwohl sie in der Datenbank existiert
    //Es funktioniert aber auch ohne
    //@JoinColumn(name = "aufgabe_aufgabenid")
    protected Aufgabe aufgabe;

    /**
     * Leerer Konstruktor für Klasse Userlösung
     */
    public Userloesung() {
        super();
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse Userlösung
     *
     * @param aufgabe          zur Lösung gehörende Aufgabe
     * @param hinweisVerwendet gibt an, ob der Lösungshinweis verwendet wurde
     */
    public Userloesung(Aufgabe aufgabe, boolean hinweisVerwendet) {
        super();
        this.aufgabe = aufgabe;
        this.hinweisVerwendet = hinweisVerwendet;
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

}