package entity.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum für die verschiedenen thematischen Kategorien von Aufgaben
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public enum Kategorie {

    Software_Engineering("Software Engineering"),
    Datenbanken("Datenbanken"),
    Java_Programmierung("Java-Programmierung"),
    Java_Grundlagen("Java-Grundlagen"),
    Klassendiagramme("Klassendiagramme");

    private final String code;
    private static final int SIZE = Kategorie.values().length;

    /**
     * Konstruktor des Enums Kategorie
     *
     * @param code String, der zum Enum gehört
     */
    Kategorie(String code) {
        this.code = code;
    }

    /**
     * Gibt den String, der zum Enum gehört, zurück
     *
     * @return String, der zum Enum gehört
     */
    public String getCode() {
        return code;
    }

    /**
     * Gibt die Anzahl der Werte des Enums zurück;
     *
     * @return Anzahl der Werte des Enums
     */
    public static int getSize() {
        return SIZE;
    }

    /**
     * Gibt ein Array aller Codes des Enums zurück
     *
     * @return Array aller Codes des Enums
     */
    public static String[] getCodeArray() {
        List<String> codes = new ArrayList<String>();
        for(Kategorie k : Kategorie.values()) {
            codes.add(k.getCode());
        }
        return codes.toArray(new String[codes.size()]);
    }

}