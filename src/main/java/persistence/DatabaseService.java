package persistence;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.aufgabensammlung.Training;
import entity.benutzer.Benutzer;
import entity.benutzer.Dozent;
import entity.benutzer.Student;
import entity.aufgabe.MultipleChoiceAufgabe;
import entity.loesung.musterloesung.Musterloesung;
import entity.loesung.userloesung.Userloesung;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import entity.enums.*;

import javax.swing.*;

/**
 * Klasse, die sich um das Persistieren und Laden von Entitäten kümmert
 *
 * @author Jonas Herbst
 * @version 26.05.22
 */
public class DatabaseService<T> {

    private static DatabaseService ds;
    private static EntityManager em;
    private static EntityManagerFactory emf;
    private static final String PERSISTENCE_UNIT_NAME = "default";

    /**
     * Privater Konstruktor der Klasse DatabaseService (gemäß Singleton-Pattern)
     */
    private DatabaseService() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    /**
     * Gibt die statische Instanz des DatabaseService zurück (gemäß Singleton-Pattern)
     *
     * @return
     */
    public static synchronized DatabaseService getInstance() {
        if (ds == null) {
            ds = new DatabaseService();
        }
        return ds;
    }

    /**
     * Setzt die Datenbank auf den Testdatenbestand zurück.
     */
    public static void main(String[] args) {
        DatabaseService.getInstance().clearDatabase();
        new CreateBetaData();
    }


    /**
     * Persistiert alle in der übergebenen Liste enthaltenen Objekte
     *
     * @param list Liste mit Objekte, die persistiert werden sollen
     */
    public synchronized void persistObjects(List<T> list) {
        em.getTransaction().begin();
        for (Object o : list) {
            em.persist(o);
        }
        em.getTransaction().commit();
    }

    /**
     * Persistiert das übergebene Objekt
     *
     * @param t Objekt, das persistiert werden soll
     */
    public synchronized void persistObject(T t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    /**
     * Private Hilfsmethode, die alle in der übergebenen Liste enthaltenen Objekte aus der Datenbank löscht.
     *
     * @param list Liste mit Objekte, die aus der Datenbank gelöscht werden sollen
     */
    private synchronized void deleteObjectsFromDatabase(List<T> list) {
        em.getTransaction().begin();
        for (Object o : list) {
            em.remove(o);
        }
        em.getTransaction().commit();
    }

    /**
     * Private Hilfsmethode, die das übergebene Objekt aus der Datenbank löscht.
     *
     * @param t Objekt, das aus der Datenbank gelöscht werden soll
     */
    private synchronized void deleteObjectFromDatabase(T t) {
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }

    /**
     * Liest alle Benutzer aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Benutzer aus der Datenbank
     */
    public synchronized List<Benutzer> readBenutzerFromDatabase() {
        List<Benutzer> resultList = new LinkedList<Benutzer>();
        resultList.addAll(readStudentenFromDatabase());
        resultList.addAll(readDozentenFromDatabase());
        return resultList;
    }

    /**
     * Liest alle Studenten aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Studenten aus der Datenbank
     */
    public synchronized List<Student> readStudentenFromDatabase() {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        List<Student> resultList = query.getResultList();
        return resultList;
    }

    /**
     * Liest alle Dozenten aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Dozenten aus der Datenbank
     */
    public synchronized List<Dozent> readDozentenFromDatabase() {
        TypedQuery<Dozent> query = em.createQuery("SELECT d FROM Dozent d", Dozent.class);
        List<Dozent> resultList = query.getResultList();
        return resultList;
    }

    /**
     * Liest alle Aufgaben aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Aufgaben aus der Datenbank
     */
    public synchronized List<Aufgabe> readAufgabenFromDatabase() {
        List<Aufgabe> resultList = new LinkedList<Aufgabe>();
        resultList.addAll(readDesignaufgabenFromDatabase());
        resultList.addAll(readEinfachantwortAufgabenFromDatabase());
        resultList.addAll(readMultipleChoiceAufgabenFromDatabase());
        resultList.addAll(readProgrammieraufgabenFromDatabase());
        return resultList;
    }

    /**
     * Liest alle Programmieraufgaben aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Programmieraufgaben aus der Datenbank
     */
    public synchronized List<Programmieraufgabe> readProgrammieraufgabenFromDatabase() {
        TypedQuery<Programmieraufgabe> query = em.createQuery("SELECT a FROM Programmieraufgabe a", Programmieraufgabe.class);
        List<Programmieraufgabe> resultList = query.getResultList();
        return resultList;
    }

    /**
     * Liest alle Designaufgaben aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Designaufgaben aus der Datenbank
     */
    public synchronized List<Designaufgabe> readDesignaufgabenFromDatabase() {
        TypedQuery<Designaufgabe> query = em.createQuery("SELECT a FROM Designaufgabe a", Designaufgabe.class);
        List<Designaufgabe> resultList = query.getResultList();
        return resultList;
    }

    /**
     * Liest alle Einfachantwort-Aufgaben aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Einfachantwort-Aufgaben  aus der Datenbank
     */
    public synchronized List<EinfachantwortAufgabe> readEinfachantwortAufgabenFromDatabase() {
        TypedQuery<EinfachantwortAufgabe> query = em.createQuery("SELECT a FROM EinfachantwortAufgabe a", EinfachantwortAufgabe.class);
        List<EinfachantwortAufgabe> resultList = query.getResultList();
        return resultList;
    }

    /**
     * Liest alle Multiple-Choice-Aufgaben aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Multiple-Choice-Aufgaben aus der Datenbank
     */
    public synchronized List<MultipleChoiceAufgabe> readMultipleChoiceAufgabenFromDatabase() {
        TypedQuery<MultipleChoiceAufgabe> query = em.createQuery("SELECT a FROM MultipleChoiceAufgabe a", MultipleChoiceAufgabe.class);
        List<MultipleChoiceAufgabe> resultList = query.getResultList();
        return resultList;
    }

    /**
     * Liest alle Aufgabensammlungen aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Aufgabensammlungen aus der Datenbank
     */
    public synchronized List<Aufgabensammlung> readAufgabensammlungFromDatabase() {
        List<Aufgabensammlung> resultList = new LinkedList<Aufgabensammlung>();
        resultList.addAll(readTestateFromDatabase());
        resultList.addAll(readTrainingsFromDatabase());
        return resultList;
    }

    /**
     * Liest alle Trainings aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Trainings aus der Datenbank
     */
    public synchronized List<Training> readTrainingsFromDatabase() {
        TypedQuery<Training> query = em.createQuery("SELECT t FROM Training t", Training.class);
        List<Training> resultList = query.getResultList();
        return resultList;
    }

    /**
     * Liest alle Testate aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Testate aus der Datenbank
     */
    public synchronized List<Testat> readTestateFromDatabase() {
        TypedQuery<Testat> query = em.createQuery("SELECT t FROM Testat t", Testat.class);
        List<Testat> resultList = query.getResultList();
        return resultList;
    }

    /**
     * Liest alle Testatbearbeitungen aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Testatbearbeitungen aus der Datenbank
     */
    public synchronized List<TestatBearbeitung> readTestatBearbeitungenFromDatabase() {
        TypedQuery<TestatBearbeitung> query = em.createQuery("SELECT t FROM TestatBearbeitung t", TestatBearbeitung.class);
        List<TestatBearbeitung> resultList = query.getResultList();
        return resultList;
    }

    /**
     * Liest alle Userlösungen aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Userlösungen aus der Datenbank
     */
    public synchronized List<Userloesung> readUserloesungenFromDatabse() {
        TypedQuery<Userloesung> query = em.createQuery("SELECT u FROM Userloesung u", Userloesung.class);
        List<Userloesung> resultList = query.getResultList();
        return resultList;
    }

    /**
     * Liest alle Musterlösungen aus der Datenbank und gibt sie als Liste zurück
     *
     * @return Liste aller Musterlösungen aus der Datenbank
     */
    public synchronized List<Musterloesung> readMusterloesungenFromDatabse() {
        TypedQuery<Musterloesung> query = em.createQuery("SELECT m FROM Musterloesung m", Musterloesung.class);
        List<Musterloesung> resultList = query.getResultList();
        return resultList;
    }

    /**
     * Löscht die übergebene Aufgabe aus der Datenbank, ohne dass Foreign-Key-Beziehungen verletzt werden.
     * Dabei werden alle Beziehungen der Aufgabe beidseitig entfernt.
     * Alle mit der Aufgabe verbundenen Userlösungen und die Musterlösung werden aus der Datenbank gelöscht.
     * CopyOnWriteArrayList, eine thread-sichere Version der ArrayList wird hier verwendet, da sonst eine
     * ConcurrentModificationException wegen Zugriff von verschiedenen Threads auf die Listen geworfen wird.
     *
     * @param aufgabe Aufgabe, die sicher aus der Datenbank gelöscht werden soll
     */
    public synchronized void saveDeleteAufgabeFromDatabase(Aufgabe aufgabe) {
        if (aufgabe.getAufgabenErsteller() != null) {
            aufgabe.getAufgabenErsteller().removeErstellteAufgabe(aufgabe);
            aufgabe.setAufgabenErsteller(null);
        }
        CopyOnWriteArrayList<Aufgabensammlung> verwendungen = new CopyOnWriteArrayList<Aufgabensammlung>(aufgabe.getVerwendungen());
        for (Aufgabensammlung a : verwendungen) {
            a.removeAufgabe(aufgabe);
            aufgabe.removeVerwendung(a);
        }
        if (aufgabe.getMusterloesung() != null) {
            saveDeleteMusterloesungFromDatabase(aufgabe.getMusterloesung());
        }
        CopyOnWriteArrayList<Userloesung> userloesungen = new CopyOnWriteArrayList<Userloesung>(aufgabe.getUserloesungen());
        for (Userloesung u : userloesungen) {
            saveDeleteUserloesungFromDatabase(u);
        }
        deleteObjectFromDatabase((T) aufgabe);
    }

    /**
     * Löscht die übergebene Musterlösung aus der Datenbank, ohne dass Foreign-Key-Beziehungen verletzt werden.
     * Dabei werden alle Beziehungen der Musterlösung beidseitig entfernt.
     * Die mit der Musterlösung verbundene Aufgabe wird aus der Datenbank gelöscht.
     *
     * @param musterloesung Musterlösung, die sicher aus der Datenbank gelöscht werden soll
     */
    public synchronized void saveDeleteMusterloesungFromDatabase(Musterloesung musterloesung) {
        try {
            if (musterloesung.getAufgabe() != null) {
                Aufgabe aufgabe = musterloesung.getAufgabe();
                aufgabe.setMusterloesung(null);
                musterloesung.setAufgabe(null);
                saveDeleteAufgabeFromDatabase(aufgabe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        deleteObjectFromDatabase((T) musterloesung);
    }

    /**
     * Löscht die übergebene Userlösung aus der Datenbank, ohne dass Foreign-Key-Beziehungen verletzt werden.
     * Dabei werden alle Beziehungen der Userlösung beidseitig entfernt.
     *
     * @param userloesung Userlösung, die sicher aus der Datenbank gelöscht werden soll
     */
    public synchronized void saveDeleteUserloesungFromDatabase(Userloesung userloesung) {
        try {
            if (userloesung.getAufgabe() != null) {
                userloesung.getAufgabe().removeUserloesung(userloesung);
                userloesung.setAufgabe(null);
            }
            if (userloesung.getUserloesungErsteller() != null) {
                userloesung.getUserloesungErsteller().removeErstellteLoesung(userloesung);
                userloesung.setUserloesungErsteller(null);
            }
            if (userloesung.getAufgabensammlung() != null) {
                userloesung.getAufgabensammlung().removeUserloesung(userloesung);
                userloesung.setAufgabensammlung(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        deleteObjectFromDatabase((T) userloesung);
    }

    /**
     * Private Hilfsmethode, die alle Beziehungen von der übergebenen Aufgabensammlung entfernt.
     * Alle mit der Aufgabensammlung verbundenen Userlösungen werden aus der Datenbank gelöscht.
     * CopyOnWriteArrayList, eine thread-sichere Version der ArrayList wird hier verwendet, da sonst eine
     * ConcurrentModificationException wegen Zugriff von verschiedenen Threads auf die Listen geworfen wird.
     *
     * @param aufgabensammlung Aufgabensammlung, deren Beziehungen entfernt werden sollen
     */
    private synchronized void removeRelationsFromAufgabensammlung(Aufgabensammlung aufgabensammlung) {
        CopyOnWriteArrayList<Aufgabe> aufgaben = new CopyOnWriteArrayList<Aufgabe>(aufgabensammlung.getAufgaben());
        for (Aufgabe a : aufgaben) {
            a.removeVerwendung(aufgabensammlung);
            aufgabensammlung.removeAufgabe(a);
        }
        CopyOnWriteArrayList<Userloesung> userloesungen = new CopyOnWriteArrayList<Userloesung>(aufgabensammlung.getUserloesungen());
        for (Userloesung u : userloesungen) {
            u.setAufgabensammlung(null);
            aufgabensammlung.removeUserloesung(u);
            saveDeleteUserloesungFromDatabase(u);
        }
    }

    /**
     * Löscht das übergebene Training aus der Datenbank, ohne dass Foreign-Key-Beziehungen verletzt werden.
     * Dabei werden alle Beziehungen des Trainings beidseitig entfernt.
     * Alle mit dem Training verbundenen Userlösungen werden aus der Datenbank gelöscht.
     *
     * @param training Training, das sicher aus der Datenbank gelöscht werden soll
     */
    public synchronized void saveDeleteTrainingFromDatabase(Training training) {
        removeRelationsFromAufgabensammlung(training);
        if (training.getTrainingsErsteller() != null) {
            training.getTrainingsErsteller().removeBearbeitetesTraining(training);
            training.setTrainingsErsteller(null);
        }
        deleteObjectFromDatabase((T) training);
    }

    /**
     * Löscht das übergebene Testat aus der Datenbank, ohne dass Foreign-Key-Beziehungen verletzt werden.
     * Dabei werden alle Beziehungen des Testats beidseitig entfernt.
     * Alle mit dem Testat verbundenen Userlösungen und Testatbearbeitungen werden aus der Datenbank gelöscht.
     * CopyOnWriteArrayList, eine thread-sichere Version der ArrayList wird hier verwendet, da sonst eine
     * ConcurrentModificationException wegen Zugriff von verschiedenen Threads auf die Listen geworfen wird.
     *
     * @param testat Testat, das sicher aus der Datenbank gelöscht werden soll
     */
    public synchronized void saveDeleteTestatFromDatabase(Testat testat) {
        removeRelationsFromAufgabensammlung(testat);
        if (testat.getTestatErsteller() != null) {
            testat.getTestatErsteller().removeErstelltesTestat(testat);
            testat.setTestatErsteller(null);
        }
        CopyOnWriteArrayList<TestatBearbeitung> testatBearbeitungen = new CopyOnWriteArrayList<TestatBearbeitung>(testat.getBearbeitungen());
        for (TestatBearbeitung t : testatBearbeitungen) {
            t.setTestat(null);
            testat.removeBearbeitung(t);
            saveDeleteTestatBearbeitungFromDatabase(t);
        }
        deleteObjectFromDatabase((T) testat);
    }

    /**
     * Löscht die übergebene Testatbearbeitung aus der Datenbank, ohne dass Foreign-Key-Beziehungen verletzt werden.
     * Dabei werden alle Beziehungen der Testatbearbeitung beidseitig entfernt.
     *
     * @param testatBearbeitung Testatbearbeitung, die sicher aus der Datenbank gelöscht werden soll
     */
    public synchronized void saveDeleteTestatBearbeitungFromDatabase(TestatBearbeitung testatBearbeitung) {
        if (testatBearbeitung.getTestat() != null) {
            testatBearbeitung.getTestat().removeBearbeitung(testatBearbeitung);
            testatBearbeitung.setTestat(null);
        }
        if (testatBearbeitung.getTestatBearbeiter() != null) {
            testatBearbeitung.getTestatBearbeiter().removeBearbeitetesTestat(testatBearbeitung);
            testatBearbeitung.setTestatBearbeiter(null);
        }
        if (testatBearbeitung.getTestatBewerter() != null) {
            testatBearbeitung.getTestatBewerter().removeBewertetesTestat(testatBearbeitung);
            testatBearbeitung.setTestatBewerter(null);
        }
        deleteObjectFromDatabase((T) testatBearbeitung);
    }

    /**
     * Private Hilfsmethode, die alle Beziehungen von dem übergebenen Benutzer entfernt.
     * Alle mit dem Benutzer verbundenen Trainings, Testatbearbeitungen und Userlösungen werden aus der Datenbank gelöscht.
     * CopyOnWriteArrayList, eine thread-sichere Version der ArrayList wird hier verwendet, da sonst eine
     * ConcurrentModificationException wegen Zugriff von verschiedenen Threads auf die Listen geworfen wird.
     *
     * @param benutzer Benutzer, dessen Beziehungen entfernt werden sollen
     */
    private synchronized void removeRelationsFromBenutzer(Benutzer benutzer) {
        CopyOnWriteArrayList<Training> trainings = new CopyOnWriteArrayList<Training>(benutzer.getBearbeiteteTrainings());
        for (Training training : trainings) {
            training.setTrainingsErsteller(null);
            benutzer.removeBearbeitetesTraining(training);
            saveDeleteTrainingFromDatabase(training);
        }
        CopyOnWriteArrayList<TestatBearbeitung> testatbearbeitungen = new CopyOnWriteArrayList<TestatBearbeitung>(benutzer.getBearbeiteteTestate());
        for (TestatBearbeitung testatBearbeitung : testatbearbeitungen) {
            testatBearbeitung.setTestatBearbeiter(null);
            benutzer.removeBearbeitetesTestat(testatBearbeitung);
            saveDeleteTestatBearbeitungFromDatabase(testatBearbeitung);
        }
        CopyOnWriteArrayList<Userloesung> userloesungen = new CopyOnWriteArrayList<Userloesung>(benutzer.getErstellteLoesungen());
        for (Userloesung u : userloesungen) {
            u.setUserloesungErsteller(null);
            benutzer.removeErstellteLoesung(u);
            saveDeleteUserloesungFromDatabase(u);
        }
    }

    /**
     * Löscht den übergebenen Studenten aus der Datenbank, ohne dass Foreign-Key-Beziehungen verletzt werden.
     * Dabei werden alle Beziehungen des Studenten beidseitig entfernt.
     * Alle mit dem Studenten verbundenen Trainings, Testatbearbeitungen und Userlösungen werden aus der Datenbank gelöscht.
     *
     * @param student Student, der sicher aus der Datenbank gelöscht werden soll
     */
    public synchronized void saveDeleteStudentFromDatabase(Student student) {
        removeRelationsFromBenutzer(student);
        deleteObjectFromDatabase((T) student);
    }

    /**
     * Löscht den übergebenen Dozenten aus der Datenbank, ohne dass Foreign-Key-Beziehungen verletzt werden.
     * Dabei werden alle Beziehungen des Dozenten beidseitig entfernt.
     * Alle mit dem Dozenten verbundenen Trainings, Testatbearbeitungen und Userlösungen werden aus der Datenbank gelöscht.
     * CopyOnWriteArrayList, eine thread-sichere Version der ArrayList wird hier verwendet, da sonst eine
     * ConcurrentModificationException wegen Zugriff von verschiedenen Threads auf die Listen geworfen wird.
     *
     * @param dozent Dozenten, der sicher aus der Datenbank gelöscht werden soll
     */
    public synchronized void saveDeleteDozentFromDatabase(Dozent dozent) {
        removeRelationsFromBenutzer(dozent);
        CopyOnWriteArrayList<TestatBearbeitung> bewerteteTestate = new CopyOnWriteArrayList<TestatBearbeitung>(dozent.getBewerteteTestate());
        for (TestatBearbeitung testatBearbeitung : bewerteteTestate) {
            testatBearbeitung.setTestatBewerter(null);
            dozent.removeBewertetesTestat(testatBearbeitung);
        }
        CopyOnWriteArrayList<Testat> testate = new CopyOnWriteArrayList<Testat>(dozent.getErstellteTestate());
        for (Testat testat : testate) {
            testat.setTestatErsteller(null);
            dozent.removeErstelltesTestat(testat);
        }
        CopyOnWriteArrayList<Aufgabe> aufgaben = new CopyOnWriteArrayList<Aufgabe>(dozent.getErstellteAufgaben());
        for (Aufgabe aufgabe : aufgaben) {
            aufgabe.setAufgabenErsteller(null);
            dozent.removeErstellteAufgabe(aufgabe);
        }
        deleteObjectFromDatabase((T) dozent);
    }

    /**
     * Gibt die Objekte aus der übergebenen Liste auf der Konsole aus.
     * Dazu müssen diese das Interface Serializable implementieren und über eine toString-Methode verfügen.
     *
     * @param resultList Liste, deren beinhaltete Objekte auf der Konsole ausgegeben werden sollen
     */
    public void printResults(List<T> resultList) {
        for (T obj : resultList) {
            System.out.println(obj.toString());
        }
    }

    /**
     * Setz die Datenbank zurück indem alle Benutzer, Aufgaben, Aufgabensammlungen Userlösungen,
     * Musterlösungen und Testatbearbeitungen aus der Datenbank gelöscht werden.
     */
    public synchronized void clearDatabase() {
        for (Benutzer b : readBenutzerFromDatabase()) {
            if (b.getClass() == Student.class) {
                saveDeleteStudentFromDatabase((Student) b);
            }
            if (b.getClass() == Dozent.class) {
                saveDeleteDozentFromDatabase((Dozent) b);
            }
        }
        for (Aufgabe a : readAufgabenFromDatabase()) {
            saveDeleteAufgabeFromDatabase(a);
        }
        for (Aufgabensammlung as : readAufgabensammlungFromDatabase()) {
            if (as.getClass() == Testat.class) {
                saveDeleteTestatFromDatabase((Testat) as);
            }
            if (as.getClass() == Training.class) {
                saveDeleteTrainingFromDatabase((Training) as);
            }
        }
        for (Userloesung ul : readUserloesungenFromDatabse()) {
            saveDeleteUserloesungFromDatabase(ul);
        }
        for (Musterloesung ml : readMusterloesungenFromDatabse()) {
            saveDeleteMusterloesungFromDatabase(ml);
        }
        for (TestatBearbeitung tb : readTestatBearbeitungenFromDatabase()) {
            saveDeleteTestatBearbeitungFromDatabase(tb);
        }
    }

    /**
     * Führt einen FileChooser-Dialog durch und gibt die ausgewählte Datei zurück.
     * Achtung: Beinhaltet keinerlei Error-Handling bezüglich der zurückgegebenen Datei
     * --> Dieses wird von der Methode convertFileToByteArray übernommen
     *
     * @param ueberdeckterFrame JFrame, der vom FileChooser-Dialog überdeckt/blockiert werden soll
     * @return Datei, die im FileChooser-Dialog ausgewählt wurde
     */
    public static File dateiOeffnen(JFrame ueberdeckterFrame) {
        File file = null;
        JFileChooser jfc = new JFileChooser();
        int retVal = jfc.showOpenDialog(ueberdeckterFrame);
        if (retVal == JFileChooser.APPROVE_OPTION) {
            file = jfc.getSelectedFile();
        }
        return file;
    }

    /**
     * Konvertiert die übergebene (Bild-)Datei in ein ByteArray
     * Beinhaltet Error-Handling zur übergebenen Datei, bei Fehlern wird ein MessageDialog geöffnet
     *
     * @param file              (Bild-)Datei, die in ein ByteArray konvertiert werden soll
     * @param ueberdeckterFrame JFrame, der von bei Fehlern auftretenden MessageDialogen überdeckt/blockiert werden soll
     * @return ByteArray, in das die übergebene Datei konvertiert wurde
     */
    public static byte[] convertFileToByteArray(File file, JFrame ueberdeckterFrame) {
        byte[] imgInBytes = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(imgInBytes);
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(ueberdeckterFrame, "Sie haben keine Datei ausgewählt.", "Keine Datei ausgewählt", JOptionPane.WARNING_MESSAGE);
        } catch (FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(ueberdeckterFrame, "Die gewählte Datei wurde nicht gefunden.", "Datei nicht gefunden", JOptionPane.WARNING_MESSAGE);
        } catch (IOException io) {
            JOptionPane.showMessageDialog(ueberdeckterFrame, "Beim Lesen der Datei ist ein Fehler aufgetreten.", "Fehler beim Lesen der Datei\"", JOptionPane.WARNING_MESSAGE);
        }
        return imgInBytes;
    }

    /****************************************************************
     * Die weiteren Methoden wurden von Martin Bergen implementiert *
     ****************************************************************/

    /**
     * Liest den Studenten mit dem übergebenen Benutzernamen aus der Datenbank und gibt ihn zurück.
     * Für den Fall, dass kein Student mit dem Benutzernamen in der Datenbank liegt, wird null zurückgegeben
     *
     * @param benutzername Benutzername, für den der Student aus der Datenbank geladen werden soll
     * @return Student mit dem übergebenen Benutzernamen
     */
    public synchronized Benutzer readStudentnachBenutzernamen(String benutzername) {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.benutzername='" + benutzername + "'", Student.class);
        Student benutzer = query.getSingleResult();
        return benutzer;
    }

    /**
     * Liest den Dozenten mit dem übergebenen Benutzernamen aus der Datenbank und gibt ihn zurück.
     * Für den Fall, dass kein Dozent mit dem Benutzernamen in der Datenbank liegt, wird null zurückgegeben
     *
     * @param benutzername Benutzername, für den der Dozent aus der Datenbank geladen werden soll
     * @return Dozent mit dem übergebenen Benutzernamen
     */
    public synchronized Benutzer readDozentnachBenutzernamen(String benutzername) {
        TypedQuery<Dozent> query = em.createQuery("SELECT s FROM Dozent s WHERE s.benutzername='" + benutzername + "'", Dozent.class);
        Benutzer benutzer = query.getSingleResult();
        return benutzer;
    }

    /**
     * Liest eine Aufgabe aus der Datenbank, die dem übergebenen Typ entspricht und über die übergebene Kategorie und Schwierigkeitsgrad verfügt.
     * Für den Fall, dass keine Aufgabe, die den Kriterien entspricht, in der Datenbank liegt, wird null zurückgegeben
     *
     * @param aufgabenTyp        Aufgabentyp, dem die zu ladende Aufgabe entsprechen soll
     * @param kategorie          Kategorie, über die die zu ladende Aufgabe verfügen soll
     * @param schwierigkeitsgrad schwierigkeitsgrad, über den die zu ladende Aufgabe verfügen soll
     * @return Aufgabe, die den übergebenen Kriterien entspricht
     */
    public synchronized Aufgabe readAufgabeMitTyp(Aufgabentyp aufgabenTyp, Kategorie kategorie, Schwierigkeitsgrad schwierigkeitsgrad) {
        switch (aufgabenTyp) {
            case MultipleChoice: {
                TypedQuery<MultipleChoiceAufgabe> query = em.createQuery("SELECT s FROM MultipleChoiceAufgabe s WHERE s.kategorie= :kat AND s.schwierigkeitsgrad= :schwierigkeitsgrad", MultipleChoiceAufgabe.class).setParameter("kat", kategorie).setParameter("schwierigkeitsgrad", schwierigkeitsgrad);
                Aufgabe aufgabe = query.getSingleResult();
                return aufgabe;
            }
            case Design: {
                TypedQuery<Designaufgabe> query = em.createQuery("SELECT s FROM Designaufgabe s WHERE s.kategorie= :kat AND s.schwierigkeitsgrad= :schwierigkeitsgrad", Designaufgabe.class).setParameter("kat", kategorie).setParameter("schwierigkeitsgrad", schwierigkeitsgrad);
                Aufgabe aufgabe = query.getSingleResult();
                return aufgabe;
            }
            case Programmieren: {
                TypedQuery<Programmieraufgabe> query = em.createQuery("SELECT s FROM Programmieraufgabe s WHERE s.kategorie= :kat AND s.schwierigkeitsgrad= :schwierigkeitsgrad", Programmieraufgabe.class).setParameter("kat", kategorie).setParameter("schwierigkeitsgrad", schwierigkeitsgrad);
                Aufgabe aufgabe = query.getSingleResult();
                return aufgabe;
            }
            case Einfachantwort: {
                TypedQuery<EinfachantwortAufgabe> query = em.createQuery("SELECT s FROM EinfachantwortAufgabe s WHERE s.kategorie= :kat AND s.schwierigkeitsgrad= :schwierigkeitsgrad", EinfachantwortAufgabe.class).setParameter("kat", kategorie).setParameter("schwierigkeitsgrad", schwierigkeitsgrad);
                Aufgabe aufgabe = query.getSingleResult();
                return aufgabe;
            }
            default:
                return null;
        }
    }

    /**
     * Liest alle Aufgaben aus der Datenbank, die über die übergebene Kategorie und Schwierigkeitsgrad verfügen
     *
     * @param kategorie          Kategorie, zu der Aufgaben aus der Datenbank geladen werden sollen
     * @param schwierigkeitsgrad schwierigkeitsgrad, zu dem Aufgaben aus der Datenbank geladen werden sollen
     * @return Liste mit Aufgaben, die über die übergebenen Kategorie und Schwierigkeitsgrad verfügen
     */
    public synchronized List<Aufgabe> readAufgabenmitKatSchwierigkeit(Kategorie kategorie, Schwierigkeitsgrad schwierigkeitsgrad) {
        TypedQuery<Aufgabe> query = em.createQuery("SELECT s FROM Aufgabe s WHERE s.kategorie= :kategorie AND s.schwierigkeitsgrad= :schwierigkeitsgrad", Aufgabe.class).setParameter("kategorie", kategorie).setParameter("schwierigkeitsgrad", schwierigkeitsgrad);
        List<Aufgabe> aufgabenList = query.getResultList();
        return aufgabenList;
    }

    /**************************************************************
     * Die weiteren Methoden wurden von Timo Joswig implementiert *
     **************************************************************/

    /**
     * Liest alle Userlösungen aus der Datenbank, die vom übergebenen Benutzer zum übergebenen Testat erstellt wurden
     *
     * @param testat   Testat, für das Userlösungen vom übergebenen Benutzer geladen werden sollen
     * @param benutzer Benutzer, dessen Userlösungen zum übergebenen Testat geladen werden sollen
     * @return Liste mit Userlösungen, die vom übergebenen Benutzer zum übergebenen Testat erstellt wurden
     */
    public synchronized List<Userloesung> readUserloesungVonTestat(Testat testat, Benutzer benutzer) {
        TypedQuery<Userloesung> query = em.createQuery("SELECT s FROM Userloesung s WHERE s.userloesungErsteller= :benutzer AND s.aufgabensammlung= :testat", Userloesung.class).setParameter("benutzer", benutzer).setParameter("testat", testat);
        List<Userloesung> userloesungList = query.getResultList();
        return userloesungList;
    }

    /**
     * Liest alle Userlösungen aus der Datenbank, die vom übergebenen Benutzer zum übergebenen Training erstellt wurden
     *
     * @param training Training, für das Userlösungen vom übergebenen Benutzer geladen werden sollen
     * @param benutzer Benutzer, dessen Userlösungen zum übergebenen Training geladen werden sollen
     * @return Liste mit Userlösungen, die vom übergebenen Benutzer zum übergebenen Training erstellt wurden
     */
    public synchronized List<Userloesung> readUserloesungVonTraining(Training training, Benutzer benutzer) {
        TypedQuery<Userloesung> query = em.createQuery("SELECT s FROM Userloesung s WHERE s.userloesungErsteller= :benutzer AND s.aufgabensammlung= :training", Userloesung.class).setParameter("benutzer", benutzer).setParameter("training", training);
        List<Userloesung> userloesungList = query.getResultList();
        return userloesungList;
    }

    /**
     * Liest das Testat zur übergebenen Testatbearbeitung aus der Datenbank
     *
     * @param bearbeitung Testatbearbeitung, zu der das Testat geladen werden soll
     * @return Testat zur übergebenen Testatbearbeitung
     */
    public synchronized Testat readTestatMitTestatbearbeitung(TestatBearbeitung bearbeitung) {
        TypedQuery<Testat> query = em.createQuery("SELECT s FROM Testat s WHERE s.bearbeitungen= :bearbeitung", Testat.class).setParameter("bearbeitung", bearbeitung);
        Testat result = query.getSingleResult();
        return result;
    }

}