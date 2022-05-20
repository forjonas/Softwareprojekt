package entity.enums;

/**
 * Enum für die drei Schwierigkeitsgrade
 *
 * @author Jonas Herbst
 * @version 04.05.22
 */
public enum Schwierigkeitsgrad {

    Leicht("Leicht"), Mittel("Mittel"), Schwer("Schwer");

    private String code;

    private Schwierigkeitsgrad(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}