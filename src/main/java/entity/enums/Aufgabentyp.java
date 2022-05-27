package entity.enums;

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

}