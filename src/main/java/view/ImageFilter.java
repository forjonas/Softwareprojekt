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
    private String ImageFormat = "JPG";
    private char DotIndex = '.';

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        return extension(f).equalsIgnoreCase(ImageFormat);
    }

    private String extension(File f) {
        String FileName = f.getName();

        int IndexFile = FileName.lastIndexOf(DotIndex);
        if (IndexFile > 0 && IndexFile < FileName.length() - 1) {
            return FileName.substring(IndexFile + 1);
        } else {
            return "";
        }
    }

    @Override
    public String getDescription() {
        return "Jpeg";
    }
}