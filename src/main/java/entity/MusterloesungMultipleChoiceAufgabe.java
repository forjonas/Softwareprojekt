package entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Musterlösung des Dozenten für eine Aufgabe des Typs Multiple-Choice.
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MusterloesungMultipleChoiceAufgabe extends Loesung{

    @ElementCollection
    private List<Boolean> loesung;  //ToDo: MultipleChoiceAufgaben Lösung Datentyp

    public MusterloesungMultipleChoiceAufgabe(){
        super();
    }

    /**
     * Konstruktor für eine Musterlösung vom Typ Multiple-Choice
     *
     * @param loesung Musterlösung in Form einer Boolean-Liste
     */
    public MusterloesungMultipleChoiceAufgabe(List<Boolean> loesung) {
        super();
        this.loesung = loesung;
    }



}
