package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Testentität, die von nichts erbt oder vererbt
 *
 * @author Jonas Herbst
 * @version 22.04.22
 */
@Entity
public class Testentitaet {
    @Id
    @GeneratedValue
    private long id;
    private int attribut1;
    private String attribut2;

    public Testentitaet() {
    }

    public int getAttribut1() {
        return attribut1;
    }

    public void setAttribut1(int attribut1) {
        this.attribut1 = attribut1;
    }

    public String getAttribut2() {
        return attribut2;
    }

    public void setAttribut2(String attribut2) {
        this.attribut2 = attribut2;
    }

    @Override
    public String toString() {
        return "Testinstanz{" +
                "id=" + id +
                ", attribut1=" + attribut1 +
                ", attribut2='" + attribut2 + '\'' +
                '}';
    }
}
