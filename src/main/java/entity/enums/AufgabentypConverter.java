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
public class AufgabentypConverter implements AttributeConverter<Aufgabentyp, String> {

    @Override
    public String convertToDatabaseColumn(Aufgabentyp aufgabentyp) {
        if (aufgabentyp == null) {
            return null;
        }
        return aufgabentyp.getCode();
    }

    @Override
    public Aufgabentyp convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Aufgabentyp.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}