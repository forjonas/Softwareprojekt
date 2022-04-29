package persistence;

import entity.Aufgabe;
import entity.Aufgabensammlung;
import entity.Benutzer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Set;

/**
 * Klasse, die sich um das Persistieren von Entitäten kümmer
 *
 * @author Jonas Herbst
 * @version 29.04.22
 */
public class DatabaseService<T> {
    private static DatabaseService ds;
    private static EntityManager em;
    private static EntityManagerFactory emf;
    private static final String PERSISTENCE_UNIT_NAME = "default";

    private DatabaseService() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    public static synchronized DatabaseService getInstance() {
        if (ds == null) {
            ds = new DatabaseService();
        }
        return ds;
    }

    public synchronized void persistObjects(List<T> list) {
        em.getTransaction().begin();
        for (Object o : list) {
            em.persist(o);
        }
        em.getTransaction().commit();
    }

    public synchronized void persistObject(T t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    public synchronized void removeObjectFromDatabase(List<T> list) {
        em.getTransaction().begin();
        for (Object o : list) {
            em.remove(o);
        }
        em.getTransaction().commit();
    }

    public synchronized void removeObjectsFromDatabase(T t) {
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }

    public synchronized List<Benutzer> readBenutzerFromDatabase() {
        TypedQuery<Benutzer> query = em.createQuery("SELECT b FROM Benutzer b", Benutzer.class);
        List<Benutzer> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<Aufgabe> readAufgabenFromDatabase() {
        TypedQuery<Aufgabe> query = em.createQuery("SELECT a FROM Aufgabe a", Aufgabe.class);
        List<Aufgabe> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<Aufgabensammlung> readAufgabensammlungFromDatabase() {
        TypedQuery<Aufgabensammlung> query = em.createQuery("SELECT a FROM Aufgabensammlung a", Aufgabensammlung.class);
        List<Aufgabensammlung> resultList = query.getResultList();
        return resultList;
    }

    public synchronized Benutzer readBenutzerFromDatabaseById(long id) {
        TypedQuery<Benutzer> query = em.createQuery("SELECT b FROM Benutzer b WHERE b.benutzerId = " + id, Benutzer.class);
        Benutzer result = query.getResultList().get(0);
        return result;
    }

    public synchronized Aufgabe readAufgabeFromDatabaseById(long id) {
        TypedQuery<Aufgabe> query = em.createQuery("SELECT a FROM Aufgabe a WHERE a.aufgabenId = " + id, Aufgabe.class);
        Aufgabe result = query.getResultList().get(0);
        return result;
    }

    public synchronized Aufgabensammlung readAufgabensammlungFromDatabaseById(long id) {
        TypedQuery<Aufgabensammlung> query = em.createQuery("SELECT a FROM Aufgabensammlung a WHERE a.aufgabensammlungId = " + id, Aufgabensammlung.class);
        Aufgabensammlung result = query.getResultList().get(0);
        return result;
    }

    public void printResults(List<T> resultList) {
        for (T obj : resultList) {
            System.out.println(obj.toString());
        }
    }

    public synchronized void clearDatabase() {
        em.getTransaction().begin();
        for(Benutzer b: readBenutzerFromDatabase())
            em.remove(b);
        for(Aufgabe a: readAufgabenFromDatabase())
            em.remove(a);
        for(Aufgabensammlung as: readAufgabensammlungFromDatabase())
            em.remove(as);
        em.getTransaction().commit();
    }

}