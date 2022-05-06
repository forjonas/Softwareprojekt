package entity;

import jakarta.persistence.*;

import java.lang.annotation.Target;

/**
 * Abstrakte Superklasse für Loesungen
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Loesung {

    @Id
    private long loesungsID;

    private long aufgabenID;

    private boolean hinweisVerwendet = false;

    public Loesung(){

    }

    /**
     * Allgemeiner Konstruktor für Aufgaben
     *
     * @param aufgabenID AufgabenID der zugehoerigen Aufgabe
     */
    public Loesung(long aufgabenID){
        this.aufgabenID = aufgabenID;
    }

    /**
     * Gibt die eindeutige ID der Loesung zurück
     *
     * @return ID der Loesung
     */
    public long getLoesungsID() {
        return loesungsID;
    }

    /**
     * Gibt die ID der zugehörigen Aufgabe zurück
     *
     * @return AufgabenID der zugehörigen Aufgabe
     */
    public long getAufgabenID() {
        return aufgabenID;
    }

    /**
     * Setzt die AufgabenID der zugehörigen Aufgabe
     *
     * @param aufgabenID AufgabenID der zugehörigen Aufgabe
     */
    public void setAufgabenID(long aufgabenID) {
        this.aufgabenID = aufgabenID;
    }
}
