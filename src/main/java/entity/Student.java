package entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Benutzer vom Typ Student
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
public class Student extends Benutzer {

    private int matrikelnummer;

    /**
     * Leerer Konstruktor für Klasse Student
     */
    public Student() {
        super();
        //Nothing to do
    }

    /**
     * Konstruktor für Klasse Student
     *
     * @param benutzername   Benutzername des Studenten
     * @param passwort       Passwort des Studenten
     * @param vorname        Vorname des Studenten
     * @param nachname       Nachname des Studenten
     * @param matrikelnummer Matrikelnummer des Studenten
     */
    public Student(String benutzername, String passwort, String vorname, String nachname, int matrikelnummer) {
        super(benutzername, passwort, 1, vorname, nachname);
        this.matrikelnummer = matrikelnummer;
    }

    /**
     * Gibt die Matrikelnummer des Studenten zurück
     *
     * @return Matrikelnummer des Studenten
     */
    public int getMatrikelnummer() {
        return matrikelnummer;
    }

    /**
     * Setzt die Matrikelnummer des Studenten
     *
     * @param matrikelnummer Matrikelnummer des Studenten
     */
    public void setMatrikelnummer(int matrikelnummer) {
        this.matrikelnummer = matrikelnummer;
    }

    /**
     * Gibt den Studenten als String aus
     *
     * @return Student als String
     */
    @Override
    public String toString() {
        return "Student"+super.toString()+", matrikelnummer='" + matrikelnummer + '\''+"}";
    }
}