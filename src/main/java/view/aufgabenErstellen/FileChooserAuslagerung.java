package view.aufgabenErstellen;

import view.ImageFilter;

import javax.swing.*;
import java.io.File;

public class FileChooserAuslagerung {
    JFileChooser FC;
    File returnFile;

    public File fileChooser() {
        FC = new JFileChooser();
        FC.setAcceptAllFileFilterUsed(false);
        FC.setFileFilter(new ImageFilter());
        int returnVal = FC.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            returnFile = FC.getSelectedFile();
            return returnFile;
        }
        return null;
    }
}