package entity.benutzer;

import jakarta.persistence.Entity;

import java.io.Serializable;

/**
 * Benutzer vom Typ Administrator
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
//Wird bald gelöscht werden
@Entity
public class Administrator extends Benutzer implements BenutzerMitDozentenrechten, Serializable {

    /**
     * Leerer Konstruktor für Klasse Administrator
     */
    public Administrator() {
        super();
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse Administrator
     *
     * @param benutzername Benutzername des Administrators
     * @param passwort     Passwort des Administrators
     * @param vorname      Vorname des Administrators
     * @param nachname     Nachname des Administrators
     */
    public Administrator(String benutzername, String passwort, String vorname, String nachname) {
        super(benutzername, passwort, 3, vorname, nachname);
    }

    /**
     * Gibt den Administrator als String aus
     *
     * @return Administrator als String
     */
    @Override
    public String toString() {
        return "Administrator: {"+super.toString()+"}";
    }
}