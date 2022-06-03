package view;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Hilfsklasse die einen Filter für den Filechooser anlegt
 *
 * @author Jannik Oehme
 * @version 05.05.2022
 */
public class ImageFilter extends FileFilter {
    private String JPG = "JPG";
    private String PNG = "PNG";
    private String JPEG = "JPEG";
    private char DotIndex = '.';
    /**
     * überprüft ob ein File vom richtigen Typen ist
     * @param f
     */
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        if (extension(f).equalsIgnoreCase(JPG) || extension(f).equalsIgnoreCase(PNG) || extension(f).equalsIgnoreCase(JPEG)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Extrahiert die Filextension
     * @param f
     */
    private String extension(File f) {
        String FileName = f.getName();

        int IndexFile = FileName.lastIndexOf(DotIndex);
        if (IndexFile > 0 && IndexFile < FileName.length() - 1) {
            return FileName.substring(IndexFile + 1);
        } else {
            return "";
        }
    }
    /**
     * Gibt den Text zurück welcher dem User als einschränkung mitgeteilt werden soll
     */
    @Override
    public String getDescription() {
        return "Jpeg oder PNG oder JPG";
    }
}