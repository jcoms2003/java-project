import java.io.Console;
import java.util.Scanner;

public class ConsoleView  {
    private final Scanner in = new Scanner(System.in);

    public ConsoleView() {

    }
    
    public void splashScreen() {
        System.out.println("Welcome to the Game!");
    }
    public void endGame() {
        System.out.println("Thanks for playing!");
    }
    public String displayMainMenu() {
        System.out.println("What would you like to do?");
        String userInput = in.nextLine();
        return userInput;
    }
    public static void printHelp() {
        System.out.println("Unsure what to do, here are some options:\r\n" + //
                        "            ls or list all  - listing the knights\r\n" + //
                        "            list active  - list the active knights knights only\r\n" + //
                        "            show name or id - show the knight details card\r\n" + //
                        "            set active name or id - set knight as active (note: only 4 knights can be active) \r\n" + //
                        "            remove active name or id - remove a knight from active status (heals knight)\r\n" + //
                        "            explore or adventure or quest - find random monsters to fight\r\n" + //
                        "            save filename - save the game to the file name (default: saveData.csv)\r\n" + //
                        "            exit or goodbye - to leave the game\r\n" + //

                        "Game rules: You can have four active knights. As long as they are active, they won't heal, " + //
                        "but they can gain XP by going on adventures. " + //
                        "When you make a knight inactive, they will heal. How many monsters can you defeat " + //
                        "before, you have to heal?");
    }


    public static void main(String[] args) {
        printHelp();
    }
}