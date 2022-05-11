package entity.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

/**
 * Converter-Klasse um den Enum Schwierigkeitsgrad in JPA zu persistieren
 *
 * @author Jonas Herbst
 * @version 09.05.22
 */
@Converter(autoApply = true)
public class SchwierigkeitsgradConverter implements AttributeConverter<Schwierigkeitsgrad, String> {

    @Override
    public String convertToDatabaseColumn(Schwierigkeitsgrad schwierigkeitsgrad) {
        if (schwierigkeitsgrad == null) {
            return null;
        }
        return schwierigkeitsgrad.getCode();
    }

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
