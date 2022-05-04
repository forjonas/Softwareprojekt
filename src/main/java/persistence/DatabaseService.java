package persistence;

import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.LinkedList;
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
        List<Benutzer> resultList = new LinkedList<Benutzer>();
        resultList.addAll(readStudentenFromDatabase());
        resultList.addAll(readDozentenFromDatabase());
        resultList.addAll(readAdministratorenFromDatabase());
        return resultList;
    }

    public synchronized List<Student> readStudentenFromDatabase() {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        List<Student> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<Dozent> readDozentenFromDatabase() {
        TypedQuery<Dozent> query = em.createQuery("SELECT d FROM Dozent d", Dozent.class);
        List<Dozent> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<Administrator> readAdministratorenFromDatabase() {
        TypedQuery<Administrator> query = em.createQuery("SELECT a FROM Administrator a", Administrator.class);
        List<Administrator> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<Aufgabe> readAufgabenFromDatabase() {
        List<Aufgabe> resultList = new LinkedList<Aufgabe>();
        resultList.addAll(readDesignaufgabenFromDatabase());
        resultList.addAll(readEinfachantwortAufgabenFromDatabase());
        resultList.addAll(readMultipleChoiceAufgabenFromDatabase());
        resultList.addAll(readProgrammieraufgabenFromDatabase());
        return resultList;
    }

    public synchronized List<Programmieraufgabe> readProgrammieraufgabenFromDatabase() {
        TypedQuery<Programmieraufgabe> query = em.createQuery("SELECT a FROM Programmieraufgabe a", Programmieraufgabe.class);
        List<Programmieraufgabe> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<Designaufgabe> readDesignaufgabenFromDatabase() {
        TypedQuery<Designaufgabe> query = em.createQuery("SELECT a FROM Designaufgabe a", Designaufgabe.class);
        List<Designaufgabe> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<EinfachantwortAufgabe> readEinfachantwortAufgabenFromDatabase() {
        TypedQuery<EinfachantwortAufgabe> query = em.createQuery("SELECT a FROM EinfachantwortAufgabe a", EinfachantwortAufgabe.class);
        List<EinfachantwortAufgabe> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<MultipleChoiceAufgabe> readMultipleChoiceAufgabenFromDatabase() {
        TypedQuery<MultipleChoiceAufgabe> query = em.createQuery("SELECT a FROM MultipleChoiceAufgabe a", MultipleChoiceAufgabe.class);
        List<MultipleChoiceAufgabe> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<Aufgabensammlung> readAufgabensammlungFromDatabase() {
        List<Aufgabensammlung> resultList = new LinkedList<Aufgabensammlung>();
        resultList.addAll(readTestateFromDatabase());
        resultList.addAll(readTrainingsFromDatabase());
        return resultList;
    }

    public synchronized List<Training> readTrainingsFromDatabase() {
        TypedQuery<Training> query = em.createQuery("SELECT a FROM Training a", Training.class);
        List<Training> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<Testat> readTestateFromDatabase() {
        TypedQuery<Testat> query = em.createQuery("SELECT a FROM Testat a", Testat.class);
        List<Testat> resultList = query.getResultList();
        return resultList;
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