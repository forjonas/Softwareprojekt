package View.AufgabenErstellen;

import View.DozentAnsicht;
import entity.benutzer.Dozent;

public class Tester {
        public static void main(String[] args)
        {
            new DozentAnsicht(new Dozent("ay","123","mm","gg"));
            //new StudentMainView(new Student());
        }
}
