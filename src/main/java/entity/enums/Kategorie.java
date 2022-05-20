package entity.enums;

/**
 * Enum für die verschiedenen thematischen Kategorien von Aufgaben
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
public enum Kategorie {

    Software_Engineering("Software Engineering"), Datenbanken("Datenbanken"), Java_Programmierung("Java-Programmierung"), Java_Grundlagen("Java-Grundlagen"), Klassendiagramme("Klassendiagramme");

    private String code;

    private Kategorie(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}