package View.AufgabenErstellen;

import View.DozentAnsicht;
import entity.benutzer.Dozent;

import javax.swing.*;

public class Tester {
        public static void main(String[] args)
        {
            new DozentAnsicht(new JFrame("test"),new Dozent("ay","123","mm","gg"));
        }
}
