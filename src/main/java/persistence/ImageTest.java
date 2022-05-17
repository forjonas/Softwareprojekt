package persistence;

import jakarta.persistence.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {
        //removeImagesFromDatabase();
        //writeTestImagesToDatabase();
        writeTestImagesToFiles();
    }

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

    private static void writeTestImagesToFiles() {
        for (ImageTest img : readImageTestsFromDatabase()) {
            File file = new File("C:\\Users\\Jonas\\Dropbox\\Projektdokumente\\ImageTest\\loadedImages\\" + img.getName() + ".jpg");
            convertByteArrayToFile(img.getImage(), file);
        }
    }

    public static List<ImageTest> readImageTestsFromDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        TypedQuery<ImageTest> query = em.createQuery("SELECT i FROM ImageTest i", ImageTest.class);
        List<ImageTest> resultList = query.getResultList();
        return resultList;
    }

    private static void writeTestImagesToDatabase() {
        File file1 = new File("C:\\Users\\Jonas\\Dropbox\\Projektdokumente\\ImageTest\\newImages\\img1.png");
        File file2 = new File("C:\\Users\\Jonas\\Dropbox\\Projektdokumente\\ImageTest\\newImages\\img2.png");
        File file3 = new File("C:\\Users\\Jonas\\Dropbox\\Projektdokumente\\ImageTest\\newImages\\img3.png");
        File file4 = new File("C:\\Users\\Jonas\\Dropbox\\Projektdokumente\\ImageTest\\newImages\\img4.jpg");
        ImageTest img1 = new ImageTest("Peter", convertFileToByteArray(file1));
        ImageTest img2 = new ImageTest("Karl", convertFileToByteArray(file2));
        ImageTest img3 = new ImageTest("Dieter", convertFileToByteArray(file3));
        ImageTest img4 = new ImageTest("Otto", convertFileToByteArray(file4));
        List<ImageTest> imgListe = Arrays.asList(new ImageTest[]{img1, img2, img3, img4});
        DatabaseService.getInstance().persistObjects(imgListe);
    }

    private static void writeImageToDatabaseWithDialog() {
        ImageTest img = new ImageTest();
        img.setName("Dieter");
        File file = dateiOeffnen();
        byte[] imgInBytes = convertFileToByteArray(file);
        img.setImage(imgInBytes);
        DatabaseService.getInstance().persistObject(img);
    }

    private static File dateiOeffnen() {
        File file = null;
        JFileChooser jfc = new JFileChooser();
        int retVal = jfc.showOpenDialog(null);
        if (retVal == JFileChooser.APPROVE_OPTION)
            file = jfc.getSelectedFile();
        return file;
    }

    private static byte[] convertFileToByteArray(File file) {
        byte[] imgInBytes = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(imgInBytes);
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, "Du hast keine Datei ausgewählt", "NullPointerException", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null, "Die gewählte Datei wurde nicht gefunden", "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Fehler beim Lesen der Datei", "IOException", JOptionPane.ERROR_MESSAGE);
        }
        return imgInBytes;
    }

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

class ImageJFrame extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        ImageTest img = ImagePersistence.readImageTestsFromDatabase().get(0);
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