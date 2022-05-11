package entity.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

/**
 * Converter-Klasse um den Enum Kategorie in JPA zu persistieren
 *
 * @author Jonas Herbst
 * @version 09.05.22
 */
@Converter(autoApply = true)
public class KategorieConverter implements AttributeConverter<Kategorie, String> {

    @Override
    public String convertToDatabaseColumn(Kategorie kategorie) {
        if (kategorie == null) {
            return null;
        }
        return kategorie.getCode();
    }

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
