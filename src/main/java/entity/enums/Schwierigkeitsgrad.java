package entity.enums;

/**
 * Enum für die drei Schwierigkeitsgrade
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public enum Schwierigkeitsgrad {

    Leicht("Leicht"),
    Mittel("Mittel"),
    Schwer("Schwer");

    private String code;

    /**
     * Konstruktor des Enums Schwierigkeitsgrad
     *
     * @param code String, der zum Enum gehört
     */
    private Schwierigkeitsgrad(String code) {
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