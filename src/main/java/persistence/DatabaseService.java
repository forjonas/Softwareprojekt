package persistence;

import entity.aufgabe.Aufgabe;
import entity.aufgabe.Designaufgabe;
import entity.aufgabe.EinfachantwortAufgabe;
import entity.aufgabe.Programmieraufgabe;
import entity.aufgabensammlung.Aufgabensammlung;
import entity.aufgabensammlung.Testat;
import entity.aufgabensammlung.TestatBearbeitung;
import entity.aufgabensammlung.Training;
import entity.benutzer.Administrator;
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
 * @version 20.05.22
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

    private synchronized void deleteObjectsFromDatabase(List<T> list) {
        em.getTransaction().begin();
        for (Object o : list) {
            em.remove(o);
        }
        em.getTransaction().commit();
    }

    private synchronized void deleteObjectFromDatabase(T t) {
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

    //Kommt weg wenn der Administrator gelöscht wird
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
        TypedQuery<Training> query = em.createQuery("SELECT t FROM Training t", Training.class);
        List<Training> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<Testat> readTestateFromDatabase() {
        TypedQuery<Testat> query = em.createQuery("SELECT t FROM Testat t", Testat.class);
        List<Testat> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<TestatBearbeitung> readTestatBearbeitungenFromDatabase() {
        TypedQuery<TestatBearbeitung> query = em.createQuery("SELECT t FROM TestatBearbeitung t", TestatBearbeitung.class);
        List<TestatBearbeitung> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<Userloesung> readUserloesungenFromDatabse() {
        TypedQuery<Userloesung> query = em.createQuery("SELECT u FROM Userloesung u", Userloesung.class);
        List<Userloesung> resultList = query.getResultList();
        return resultList;
    }

    public synchronized List<Musterloesung> readMusterloesungenFromDatabse() {
        TypedQuery<Musterloesung> query = em.createQuery("SELECT m FROM Musterloesung m", Musterloesung.class);
        List<Musterloesung> resultList = query.getResultList();
        return resultList;
    }

    //CopyOnWriteArrayList ist hier sehr wichtig, da sonst eine ConcurrentModificationException
    //wegen Zugriff von verschiedenen Threads auf die Listen geworfen wird
    public synchronized void saveDeleteAufgabeFromDatabase(Aufgabe aufgabe) {
        if(aufgabe.getAufgabenErsteller() != null) {
            aufgabe.getAufgabenErsteller().removeErstellteAufgabe(aufgabe);
            aufgabe.setAufgabenErsteller(null);
        }
        CopyOnWriteArrayList<Aufgabensammlung> verwendungen = new CopyOnWriteArrayList<Aufgabensammlung>(aufgabe.getVerwendungen());
        for (Aufgabensammlung a : verwendungen) {
            a.removeAufgabe(aufgabe);
            aufgabe.removeVerwendung(a);
        }
        if(aufgabe.getMusterloesung() != null) {
            saveDeleteMusterloesungFromDatabase(aufgabe.getMusterloesung());
        }
        CopyOnWriteArrayList<Userloesung> userloesungen = new CopyOnWriteArrayList<Userloesung>(aufgabe.getUserloesungen());
        for (Userloesung u : userloesungen) {
            saveDeleteUserloesungFromDatabase(u);
        }
        deleteObjectFromDatabase((T) aufgabe);
    }

    public synchronized void saveDeleteMusterloesungFromDatabase(Musterloesung musterloesung) {
        try {
            if(musterloesung.getAufgabe() != null) {
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

    public synchronized void saveDeleteUserloesungFromDatabase(Userloesung userloesung) {
        try {
            if(userloesung.getAufgabe() != null) {
                userloesung.getAufgabe().removeUserloesung(userloesung);
                userloesung.setAufgabe(null);
            }
            if(userloesung.getUserloesungErsteller() != null) {
                userloesung.getUserloesungErsteller().removeErstellteLoesung(userloesung);
                userloesung.setUserloesungErsteller(null);
            }
            if(userloesung.getAufgabensammlung() != null) {
                userloesung.getAufgabensammlung().removeUserloesung(userloesung);
                userloesung.setAufgabensammlung(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        deleteObjectFromDatabase((T) userloesung);
    }

    //CopyOnWriteArrayList ist hier sehr wichtig, da sonst eine ConcurrentModificationException
    //wegen Zugriff von verschiedenen Threads auf die Listen geworfen wird
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

    public synchronized void saveDeleteTrainingFromDatabase(Training training) {
        removeRelationsFromAufgabensammlung(training);
        if(training.getTrainingsErsteller() != null) {
            training.getTrainingsErsteller().removeBearbeitetesTraining(training);
            training.setTrainingsErsteller(null);
        }
        deleteObjectFromDatabase((T) training);
    }

    //CopyOnWriteArrayList ist hier sehr wichtig, da sonst eine ConcurrentModificationException
    //wegen Zugriff von verschiedenen Threads auf die Listen geworfen wird
    public synchronized void saveDeleteTestatFromDatabase(Testat testat) {
        removeRelationsFromAufgabensammlung(testat);
        if(testat.getTestatErsteller() != null) {
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

    public synchronized void saveDeleteTestatBearbeitungFromDatabase(TestatBearbeitung testatBearbeitung) {
        if(testatBearbeitung.getTestat() != null) {
            testatBearbeitung.getTestat().removeBearbeitung(testatBearbeitung);
            testatBearbeitung.setTestat(null);
        }
        if(testatBearbeitung.getTestatBearbeiter() != null) {
            testatBearbeitung.getTestatBearbeiter().removeBearbeitetesTestat(testatBearbeitung);
            testatBearbeitung.setTestatBearbeiter(null);
        }
        if(testatBearbeitung.getTestatBewerter() != null) {
            testatBearbeitung.getTestatBewerter().removeBewertetesTestat(testatBearbeitung);
            testatBearbeitung.setTestatBewerter(null);
        }
        deleteObjectFromDatabase((T) testatBearbeitung);
    }

    //CopyOnWriteArrayList ist hier sehr wichtig, da sonst eine ConcurrentModificationException
    //wegen Zugriff von verschiedenen Threads auf die Listen geworfen wird
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

    public synchronized void saveDeleteStudentFromDatabase(Student student) {
        removeRelationsFromBenutzer(student);
        deleteObjectFromDatabase((T) student);
    }

    //CopyOnWriteArrayList ist hier sehr wichtig, da sonst eine ConcurrentModificationException
    //wegen Zugriff von verschiedenen Threads auf die Listen geworfen wird
    public synchronized void saveDeleteDozentFromDatabase(Dozent dozent) {
        removeRelationsFromBenutzer(dozent);
        CopyOnWriteArrayList<TestatBearbeitung> bewerteteTestate = new CopyOnWriteArrayList<TestatBearbeitung>(dozent.getBewerteteTestate());
        for(TestatBearbeitung testatBearbeitung : bewerteteTestate) {
            testatBearbeitung.setTestatBewerter(null);
            dozent.removeBewertetesTestat(testatBearbeitung);
        }
        CopyOnWriteArrayList<Testat> testate = new CopyOnWriteArrayList<Testat>(dozent.getErstellteTestate());
        for(Testat testat : testate) {
            testat.setTestatErsteller(null);
            dozent.removeErstelltesTestat(testat);
        }
        CopyOnWriteArrayList<Aufgabe> aufgaben = new CopyOnWriteArrayList<Aufgabe>(dozent.getErstellteAufgaben());
        for(Aufgabe aufgabe : aufgaben) {
            aufgabe.setAufgabenErsteller(null);
            dozent.removeErstellteAufgabe(aufgabe);
        }
        deleteObjectFromDatabase((T) dozent);
    }

    public void printResults(List<T> resultList) {
        for (T obj : resultList) {
            System.out.println(obj.toString());
        }
    }

    public synchronized void clearDatabase() {
        for (Benutzer b : readBenutzerFromDatabase()) {
            if(b.getClass() == Student.class) {
                saveDeleteStudentFromDatabase((Student) b);
            }
            if(b.getClass() == Dozent.class) {
                saveDeleteDozentFromDatabase((Dozent) b);
            }
        }
        for (Aufgabe a : readAufgabenFromDatabase()) {
            saveDeleteAufgabeFromDatabase(a);
        }
        for (Aufgabensammlung as : readAufgabensammlungFromDatabase()) {
            if(as.getClass() == Testat.class) {
                saveDeleteTestatFromDatabase((Testat) as);
            }
            if(as.getClass() == Training.class) {
                saveDeleteTrainingFromDatabase((Training) as);
            }
        }
        for(Userloesung ul : readUserloesungenFromDatabse()) {
            saveDeleteUserloesungFromDatabase(ul);
        }
        for(Musterloesung ml : readMusterloesungenFromDatabse()) {
            saveDeleteMusterloesungFromDatabase(ml);
        }
        for(TestatBearbeitung tb : readTestatBearbeitungenFromDatabase()) {
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
     * @param file (Bild-)Datei, die in ein ByteArray konvertiert werden soll
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

    /**
     * Hier bin ich am rumprobieren, Martin Bergen
     */

    public synchronized Benutzer readStudentnachBenutzernamen(String benutzername) {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.benutzername='" + benutzername + "'", Student.class);
        Student benutzer = query.getSingleResult();
        return benutzer;
    }

    public synchronized Benutzer readDozentnachBenutzernamen(String benutzername) {
        TypedQuery<Dozent> query = em.createQuery("SELECT s FROM Dozent s WHERE s.benutzername='" + benutzername + "'", Dozent.class);
        Benutzer benutzer = query.getSingleResult();
        return benutzer;
    }

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

    public synchronized List<Aufgabe> readAufgabenmitKatSchwierigkeit(Kategorie kategorie, Schwierigkeitsgrad schwierigkeitsgrad)
    {
        TypedQuery<Aufgabe> query = em.createQuery("SELECT s FROM Aufgabe s WHERE s.kategorie= :kategorie AND s.schwierigkeitsgrad= :schwierigkeitsgrad", Aufgabe.class).setParameter("kategorie",kategorie).setParameter("schwierigkeitsgrad",schwierigkeitsgrad);
        List<Aufgabe> aufgabenList = query.getResultList();
        return aufgabenList;
    }

    public synchronized List<Userloesung> readUserloesungVonTestat(Testat testat, Benutzer benutzer) {
        TypedQuery<Userloesung> query = em.createQuery("SELECT s FROM Userloesung s WHERE s.userloesungErsteller= :benutzer AND s.aufgabensammlung= :testat", Userloesung.class).setParameter("benutzer", benutzer).setParameter("testat", testat);
        List<Userloesung> userloesungList = query.getResultList();
        return userloesungList;
    }

    public synchronized List<Userloesung> readUserloesungVonTraining(Training training, Benutzer benutzer) {
        TypedQuery<Userloesung> query = em.createQuery("SELECT s FROM Userloesung s WHERE s.userloesungErsteller= :benutzer AND s.aufgabensammlung= :training", Userloesung.class).setParameter("benutzer", benutzer).setParameter("training", training);
        List<Userloesung> userloesungList = query.getResultList();
        return userloesungList;
    }

    public synchronized Testat readTestatMitTestatbearbeitung(TestatBearbeitung bearbeitung) {
        TypedQuery<Testat> query = em.createQuery("SELECT s FROM Testat s WHERE s.bearbeitungen= :bearbeitung", Testat.class).setParameter("bearbeitung", bearbeitung);
        Testat result = query.getSingleResult();
        return result;
    }

}