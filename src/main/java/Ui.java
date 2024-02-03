public class Ui {
    public static final String LINE = "---------------";
    public static final String LOGO = "       __                 __ \n"
            + "      / /___ _____  ___  / /_\n"
            + " __  / / __ `/ __ \\/ _ \\/ __/\n"
            + "/ /_/ / /_/ / / / /  __/ /_  \n"
            + "\\____/\\__,_/_/ /_/\\___/\\__/  \n"
            + "                             \n";
    public static void printGoodbyeMessage() {
        System.out.println("Goodbye! See you again!");
        System.out.println(LINE);
    }
    public static void printWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println("Hi! I'm Janet, your personal assistant.");
        System.out.println("What is your request?");
        System.out.println(LINE);
    }
}
