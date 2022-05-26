package entity.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

/**
 * Converter-Klasse um den Enum Schwierigkeitsgrad in JPA zu persistieren
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
@Converter(autoApply = true)
public class SchwierigkeitsgradConverter implements AttributeConverter<Schwierigkeitsgrad, String> {

    /**
     * Konvertiert den übergebenen Schwierigkeitsgrad in eine Datenbankspalte.
     * Gibt den passenden String-Code zum übergebenen Schwierigkeitsgrad zurück.
     *
     * @param schwierigkeitsgrad Schwierigkeitsgrad, für den der passende String-Code gefunden werden soll
     * @return Zum Schwierigkeitsgrad passender String-Code
     */
    @Override
    public String convertToDatabaseColumn(Schwierigkeitsgrad schwierigkeitsgrad) {
        if (schwierigkeitsgrad == null) {
            return null;
        }
        return schwierigkeitsgrad.getCode();
    }

    /**
     * Konvertiert den übergebenen Code in einen Schwierigkeitsgrad.
     * Gibt den passenden Schwierigkeitsgrad zum übergebenen String-Code zurück
     *
     * @param code String-Code, für den der passende Schwierigkeitsgrad gefunden werden soll
     * @return Zum String-Code passender Schwierigkeitsgrad
     */
    @Override
    public Schwierigkeitsgrad convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Schwierigkeitsgrad.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}