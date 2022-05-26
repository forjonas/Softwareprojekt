package persistence;

import jakarta.persistence.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

//Wird gelöscht
@Entity(name = "ImageTest")
public class ImageTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Lob
    @Column(name = "image")
    private byte[] image;

    public ImageTest() {

    }

    public ImageTest(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}

class ImagePersistence {

    //Nur Test
    public static void main(String[] args) {
        //removeImagesFromDatabase();
        //writeTestImagesToDatabase();
        writeTestImagesToFiles();
    }

    //Nur Test
    private static void removeImagesFromDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        TypedQuery<ImageTest> query = em.createQuery("SELECT i FROM ImageTest i", ImageTest.class);
        List<ImageTest> resultList = query.getResultList();
        em.getTransaction().begin();
        for (ImageTest img : resultList) {
            em.remove(img);
        }
        em.getTransaction().commit();
    }

    //Nur Test
    private static void writeTestImagesToFiles() {
        for (ImageTest img : readImageTestsFromDatabase()) {
            File file = new File("C:\\Users\\Jonas\\Dropbox\\Projektdokumente\\ImageTest\\loadedImages\\" + img.getName() + ".txt");
            convertByteArrayToFile(img.getImage(), file);
        }
    }

    //Nur Test
    public static List<ImageTest> readImageTestsFromDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        TypedQuery<ImageTest> query = em.createQuery("SELECT i FROM ImageTest i", ImageTest.class);
        List<ImageTest> resultList = query.getResultList();
        return resultList;
    }

    //Nur Test
    private static void writeTestImagesToDatabase() {
        File file1 = new File("C:\\Users\\Jonas\\Dropbox\\Projektdokumente\\ImageTest\\newImages\\img1.png");
        File file2 = new File("C:\\Users\\Jonas\\Dropbox\\Projektdokumente\\ImageTest\\newImages\\img2.png");
        File file3 = new File("C:\\Users\\Jonas\\Dropbox\\Projektdokumente\\ImageTest\\newImages\\img3.png");
        File file4 = new File("C:\\Users\\Jonas\\Dropbox\\Projektdokumente\\ImageTest\\newImages\\img4.jpg");
        ImageTest img1 = new ImageTest("Peter", convertFileToByteArray(file1, null));
        ImageTest img2 = new ImageTest("Karl", convertFileToByteArray(file2, null));
        ImageTest img3 = new ImageTest("Dieter", convertFileToByteArray(file3, null));
        ImageTest img4 = new ImageTest("Otto", convertFileToByteArray(file4, null));
        List<ImageTest> imgListe = Arrays.asList(new ImageTest[]{img1, img2, img3, img4});
        DatabaseService.getInstance().persistObjects(imgListe);
    }

    //Nur Test
    private static void writeImageToDatabaseWithDialog() {
        ImageTest img = new ImageTest();
        img.setName("Dieter");
        File file = dateiOeffnen(null);
        byte[] imgInBytes = convertFileToByteArray(file, null);
        img.setImage(imgInBytes);
        DatabaseService.getInstance().persistObject(img);
    }

    //In DatabaseService hinzufügen
    private static File dateiOeffnen(JFrame ueberdeckterFrame) {
        File file = null;
        JFileChooser jfc = new JFileChooser();
        int retVal = jfc.showOpenDialog(ueberdeckterFrame);
        if (retVal == JFileChooser.APPROVE_OPTION) {
            file = jfc.getSelectedFile();
        }
        return file;
    }

    //In DatabaseService hinzufügen
    private static byte[] convertFileToByteArray(File file, JFrame ueberdeckterFrame) {
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

    //In DatabaseService hinzufügen --> wird nicht benötigt, das wir für die ImageIcons nur das ByteArray brauchen
    private static File convertByteArrayToFile(byte[] byteArray, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(byteArray);
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, "Du hast keine Datei ausgewählt", "NullPointerException", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null, "Die gewählte Datei wurde nicht gefunden", "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Fehler beim Lesen der Datei", "IOException", JOptionPane.ERROR_MESSAGE);
        }
        return file;
    }
}

//Nur Test, der demonstriert, wie man ein ImageIcon aus einem ByteArray erstellt (Problem: Auflösung des Bilds)
class ImageJFrame extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        ImageTest img = ImagePersistence.readImageTestsFromDatabase().get(2);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ImageJFrame frame = new ImageJFrame(img.getImage());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ImageJFrame(byte[] byteArray) {
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ImageTestJFrame");
        contentPane = new JPanel();
        setContentPane(contentPane);

        ImageIcon imageIcon = new ImageIcon(byteArray);
        JLabel imageLabel = new JLabel(imageIcon);
        contentPane.add(imageLabel);

        super.pack();
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation((display.getSize().width - super.getSize().width) / 2, (display.getSize().height - super.getSize().height) / 2);
        super.setVisible(true);
    }

}