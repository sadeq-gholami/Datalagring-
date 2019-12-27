import controller.Controller;
import view.View;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        new View(new Scanner(System.in),new Controller()).run();
    }
}
