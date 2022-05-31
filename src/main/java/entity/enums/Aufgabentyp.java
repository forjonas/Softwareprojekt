package entity.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum für die vier Aufgabentypen
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public enum Aufgabentyp {

    MultipleChoice("Multiple-Choice"),
    Einfachantwort("Einfachantwort"),
    Design("Design"),
    Programmieren("Programmieren");

    private final String code;
    private static final int SIZE = Aufgabentyp.values().length;

    /**
     * Konstruktor des Enums Aufgabentyp
     *
     * @param code String, der zum Enum gehört
     */
    Aufgabentyp(String code) {
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
        for(Aufgabentyp a : Aufgabentyp.values()) {
            codes.add(a.getCode());
        }
        return codes.toArray(new String[codes.size()]);
    }

}