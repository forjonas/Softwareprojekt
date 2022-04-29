package entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Benutzer vom Typ Dozent
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
public class Dozent extends Benutzer implements BenutzerMitDozentenrechten {

    /**
     * Leerer Konstruktor für Klasse Dozent
     */
    public Dozent() {
        super();
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse Dozent
     *
     * @param benutzername Benutzername des Dozenten
     * @param passwort     Passwort des Dozenten
     * @param vorname      Vorname des Dozenten
     * @param nachname     Nachname des Dozenten
     */
    public Dozent(String benutzername, String passwort, String vorname, String nachname) {
        super(benutzername, passwort, 2, vorname, nachname);
    }

    /**
     * Gibt den Dozenten als String aus
     *
     * @return Dozent als String
     */
    @Override
    public String toString() {
        return "Dozent"+super.toString()+"}";
    }
}