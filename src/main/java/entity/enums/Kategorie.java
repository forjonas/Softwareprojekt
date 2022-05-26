package entity.enums;

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

    private String code;

    /**
     * Konstruktor des Enums Kategorie
     *
     * @param code String, der zum Enum gehört
     */
    private Kategorie(String code) {
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