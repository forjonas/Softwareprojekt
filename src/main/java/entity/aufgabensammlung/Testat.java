package entity.aufgabensammlung;

import entity.aufgabe.Aufgabe;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Aufgabensammlung, die von einem Dozenten erstellt und bewertet und von einem Studenten bearbeitet wird
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
@Entity
public class Testat extends Aufgabensammlung {

    private String passwort;
    private String name;
    @ManyToOne(cascade = jakarta.persistence.CascadeType.PERSIST)
    @JoinColumn(name = "testatErsteller_benutzerid")
    private Dozent testatErsteller;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "testat")
    private List<TestatBearbeitung> bearbeitungen;

    /**
     * Leerer Konstruktor für Klasse Testat
     */
    public Testat() {
        super();
        bearbeitungen = new LinkedList<>();
    }

    /**
     * Konstruktor für Klasse Testat
     *
     * @param aufgaben        Aufgaben, die im Testat enthalten sind
     * @param passwort        Passwort des Testats
     * @param name            Name des Testats
     * @param testatErsteller Dozent, der das Testat erstellt hat
     */
    public Testat(List<Aufgabe> aufgaben, String passwort, String name, Dozent testatErsteller) {
        super(aufgaben);
        bearbeitungen = new LinkedList<>();
        this.passwort = passwort;
        this.name = name;
        this.testatErsteller = testatErsteller;
    }

    /**
     * Gibt das Passwort des Testats zurück
     *
     * @return Passwort des Testats
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Setzt das Passwort des Testats
     *
     * @param passwort Passwort des Testats
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    /**
     * Gibt den Namen des Testats zurück
     *
     * @return Name des Testats
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Testats
     *
     * @param name Bewertung des Testats
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt den Ersteller des Testats zurück
     *
     * @return Ersteller des Testats
     */
    public Dozent getTestatErsteller() {
        return testatErsteller;
    }

    /**
     * Setzt den Ersteller des Testats
     *
     * @param testatErsteller Ersteller des Testats
     */
    public void setTestatErsteller(Dozent testatErsteller) {
        this.testatErsteller = testatErsteller;
    }

    /**
     * Gibt die Testatbearbeitungen des Testats zurück
     *
     * @return Testatbearbeitungen des Testats
     */
    public List<TestatBearbeitung> getBearbeitungen() {
        return bearbeitungen;
    }

    /**
     * Fügt eine Testatbearbeitung zur Liste der Testatbearbeitungen des Testats hinzu
     *
     * @param bearbeitung Testatbearbeitung des Testats
     */
    public void addBearbeitung(TestatBearbeitung bearbeitung) {
        this.bearbeitungen.add(bearbeitung);
    }

    /**
     * Entfernt eine Testatbearbeitung aus der Liste der Testatbearbeitungen des Testats
     *
     * @param bearbeitung Testatbearbeitung des Testats
     */
    public void removeBearbeitung(TestatBearbeitung bearbeitung) {
        this.bearbeitungen.remove(bearbeitung);
    }

    /**
     * Setzt die Testatbearbeitungen des Testats
     *
     * @param bearbeitungen Testatbearbeitungen des Testats
     */
    public void setBearbeitungen(List<TestatBearbeitung> bearbeitungen) {
        this.bearbeitungen = bearbeitungen;
    }

    /**
     * Gibt zurück, ob das Testat bereits vom übergebenen Benutzer bearbeitet wurde
     *
     * @param benutzer Benutzer, für den überprüft wird, ob er das Testat bearbeitet hat
     * @return Wahrheitswert, der angibt, ob der übergebene Benutzer das Testat bearbeitet hat
     */
    public boolean isTestatVonUserBearbeitetWorden(Benutzer benutzer) {
        List<TestatBearbeitung> bearbeitungen = this.getBearbeitungen();
        for (TestatBearbeitung t : bearbeitungen) {
            if (t.getTestatBearbeiter() == benutzer) {
                return true;
            }
        }
        return false;
    }

    /**
     * Falls das Testat vom übergebenen Benutzer bearbeitet wurde, wird seine Testatbearbeitung des Testats
     * zurückgegeben. Hat er das Testat nicht bearbeitet, wird null zurückgegeben
     *
     * @param benutzer Benutzer, für den eine Testatbearbeitung des Testats gefunden werden soll
     * @return Testatbearbeitung des Testats vom übergebenen Benutzer
     */
    public TestatBearbeitung getBearbeitungVonBenutzer(Benutzer benutzer) {
        List<TestatBearbeitung> bearbeitungen = this.getBearbeitungen();
        for (TestatBearbeitung t : bearbeitungen) {
            if (t.getTestatBearbeiter() == benutzer) {
                return t;
            }
        }
        return null;
    }

    /**
     * Gibt zurück, ob der übergebene Dozent das Testat löschen darf
     *
     * @param dozent Dozent, für den überprüft wird, ob er das Testat löschen darf
     * @return Wahrheitswert, der angibt, ob der übergebene Dozent das Testat löschen darf
     */
    public boolean darfDozentTestatLoeschen(Dozent dozent) {
        boolean selbstErstellt = (this.testatErsteller == dozent);
        boolean leeresTestat = (this.testatErsteller == null);
        boolean isAdmin = (dozent.getBenutzername().equals("admin"));
        return (selbstErstellt || leeresTestat || isAdmin);
    }

}