package entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Userlösung eines Benutzers für eine Aufgabe des Typs Multiple-Choice.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserloesungMultipleChoiceAufgabe extends Loesung{

    @ElementCollection
    private List<Boolean> loesung; //ToDo: MultipleChoiceAufgaben Lösung Datentyp

    public UserloesungMultipleChoiceAufgabe(){
        super();
    }

    /**
     * Konstruktor für eine Userlösung vom Typ Multiple-Choice
     *
     * @param loesung Userlösung in Form einer Boolean-Liste
     */
    public UserloesungMultipleChoiceAufgabe(List<Boolean> loesung) {
        super();
        this.loesung = loesung;
    }
}
