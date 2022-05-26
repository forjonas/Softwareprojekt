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
public class AufgabentypConverter implements AttributeConverter<Aufgabentyp, String> {

    /**
     * Konvertiert den übergebenen Aufgabentyp in eine Datenbankspalte.
     * Gibt den passenden String-Code zum übergebenen Aufgabentyp zurück.
     *
     * @param aufgabentyp Aufgabentyp, für den der passende String-Code gefunden werden soll
     * @return Zum Aufgabentyp passender String-Code
     */
    @Override
    public String convertToDatabaseColumn(Aufgabentyp aufgabentyp) {
        if (aufgabentyp == null) {
            return null;
        }
        return aufgabentyp.getCode();
    }

    /**
     * Konvertiert den übergebenen Code in einen Aufgabentyp.
     * Gibt den passenden Aufgabentyp zum übergebenen String-Code zurück
     *
     * @param code String-Code, für den der passende Aufgabentyp gefunden werden soll
     * @return Zum String-Code passender Aufgabentyp
     */
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