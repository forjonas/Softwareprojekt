package view.aufgabenErstellen;

import view.ImageFilter;

import javax.swing.*;
import java.io.File;
/**
 * Auslagerung der erstellung eines Filechoosers
 * @author Jannik Oehme
 */
public class FileChooserAuslagerung {
    JFileChooser fileChooser;
    File returnFile;
    /**
     * Konstruktor der Klasse
     */
    public File fileChooser() {
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new ImageFilter());
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            returnFile = fileChooser.getSelectedFile();
            return returnFile;
        }
        return null;
    }
}
