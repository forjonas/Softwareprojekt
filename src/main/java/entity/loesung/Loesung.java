package entity.loesung;

import jakarta.persistence.*;

/**
 * Abstrakte Superklasse für Lösungen
 *
 * @author Timo Joswig
 * @version 09.05.22
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Loesung {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long loesungsID;

    /**
     * Allgemeiner Konstruktor für Lösungen
     */
    public Loesung() {
        //Nothing to do
    }

}