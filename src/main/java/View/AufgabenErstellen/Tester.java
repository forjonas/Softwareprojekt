package View.AufgabenErstellen;

import View.DozentAnsicht;
import View.StudentMainView;
import entity.benutzer.Dozent;
import entity.benutzer.Student;

import javax.swing.*;

public class Tester {
        public static void main(String[] args)
        {
            new DozentAnsicht(new Dozent("ay","123","mm","gg"));
            //new StudentMainView(new Student());
        }
}
