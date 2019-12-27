package view;

import controller.Controller;
import model.Hus;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class View {
    private Scanner scanner;
    private Controller controller;

    public View(Scanner scanner, Controller controller) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void run() throws SQLException {
        Command command = Command.from(scanner.next());
        while (!command.equals(Command.EXIT)) {

            switch (command) {
                case FORENING:
                    listForeningar();
                    break;
                case HUS:
                    getHouses();
                    break;
                case LEGTILL:
                    addHus();
                    break;
                case HELP:
                    writeHelp();
                    break;
                case EXIT:
                    System.exit(1);
                    break;
                case NOTRECOGNIZED:
                    System.out.println("write a correct command or write help to get help.");
                    break;
            }


            System.out.println("write a command.");

            command = Command.from(scanner.next());
        }


    }

    private void listForeningar() throws SQLException {
        controller.getAllForening().forEach(System.out::println);
    }


    private void addHus() throws SQLException {
         scanner.nextLine();
        System.out.println("postadress:");
        String postadress = scanner.nextLine();

        System.out.println("gatadress:");
        String gatadress = scanner.nextLine();

        System.out.println("forening:");
        String forening = scanner.nextLine();

        System.out.println("byggar:");
        String bygar = scanner.nextLine();

        controller.addNewHus(postadress, gatadress, forening, bygar);
    }

    private void writeHelp() {
        //todo: write a little documentation on how to call all these commands.
        Arrays.stream(Command.values()).filter(cc -> !cc.equals(Command.NOTRECOGNIZED)).forEach(System.out::println);
    }

    private void getHouses() throws SQLException {
        scanner.nextLine();
        System.out.println("orgnr: ");
        String orgnr = scanner.nextLine();
        List<Hus> husArray = controller.getHusByForening(orgnr);
        if (husArray.size() < 1) {
            System.out.println("ingen hus hittades");
        } else {
            husArray.forEach(System.out::println);
        }
    }


}
