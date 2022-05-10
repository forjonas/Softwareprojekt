package entity.enums;

/**
 * Enum für die vier Aufgabentypen
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
public enum Aufgabentyp {
    MultipleChoice("Multiple-Choice"), Einfachantwort("Einfachantwort"), Design("Design"), Programmieren("Programmieren");

    private String code;

    private Aufgabentyp(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}