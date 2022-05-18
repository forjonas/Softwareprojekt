package View.AufgabenErstellen;

import View.ImageFilter;

import javax.swing.*;
import java.io.File;

public class FileChooserAuslagerung {
    JFileChooser FC;
    File returnFile;
    public void FileChooserView(){

    }
    public File fileChooser() {
        FC = new JFileChooser((String) null);
        FC.setAcceptAllFileFilterUsed(false);
        FC.setFileFilter(new ImageFilter());
        int returnVal = FC.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            returnFile = FC.getSelectedFile();
            System.out.println(returnFile.getName());
            return returnFile;
        }
        return null;
    }
}
