package view;

import java.util.Arrays;

public enum Command {
    FORENING("forening"),
    HUS("hus"),
    EXIT("exit"),
    HELP("help"),
    LEGTILL("legtill"),
    NOTRECOGNIZED("notrecognized");

    private String name;

    Command(String forening) {
        this.name = forening;
    }

    public String getName() {
        return name;
    }


    public static Command from(String v){
        return Arrays.stream(Command.values()).filter(command -> command.name.equalsIgnoreCase(v)).findFirst().orElse(NOTRECOGNIZED);
    }
}
