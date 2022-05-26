package entity.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

/**
 * Converter-Klasse um den Enum Kategorie in JPA zu persistieren
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
@Converter(autoApply = true)
public class KategorieConverter implements AttributeConverter<Kategorie, String> {

    /**
     * Konvertiert die übergebene Kategorie in eine Datenbankspalte.
     * Gibt den passenden String-Code zur übergebenen Kategorie zurück.
     *
     * @param kategorie Kategorie, für die der passende String-Code gefunden werden soll
     * @return Zur Kategorie passender String-Code
     */
    @Override
    public String convertToDatabaseColumn(Kategorie kategorie) {
        if (kategorie == null) {
            return null;
        }
        return kategorie.getCode();
    }

    /**
     * Konvertiert den übergebenen Code in eine Kategorie.
     * Gibt die passende Kategorie zum übergebenen String-Code zurück
     *
     * @param code String-Code, für den die passende Kategorie gefunden werden soll
     * @return Zum String-Code passende Kategorie
     */
    @Override
    public Kategorie convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Kategorie.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}