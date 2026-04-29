/******************** Joshua C CS162 ********************/
/******************* FIRST DRAFT DONE ****************/

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements GameView{
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
    public void knightNotFound() {
        System.out.println("Knight not found!");
    }
    public void setActiveFailed() {
        // Called if set active failed
        System.out.println("Unable to set active knight. Only four can be active at a time.");
    }
    public void printHelp() {
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
    public void listKnights(List<Knight> knights) {
        if (knights.size() == 0) {
            System.out.println("No knights to list");
        } else {
            for(Knight knight : knights) {
                System.out.println(knight.getID() + ": " + knight.getName());
            }
        }
    }
    public void showKnight(Knight knight) {
        System.out.println(knight.toString() + "\n");
    }
    public void printBattleText(List<MOB> monsters, List<Knight> activeKnights) {
        System.out.println("Our heroes come across the following monsters. Prepare for battle!");
        System.out.printf("%-27s %s%n", "Knights", "Foes");
    
        // Find the maximum size to iterate through
        int maxSize = Math.max(monsters.size(), activeKnights.size());
    
        for (int i = 0; i < maxSize; i++) {
            String knightName = "";
            String monsterName = "";
        
            // Safely get knight name
            if (i < activeKnights.size()) {
                knightName = activeKnights.get(i).getName();
            }
        
            // Safely get monster name
            if (i < monsters.size()) {
                monsterName = monsters.get(i).getName();
        }
        
            // Print the row
            System.out.printf("%-27s %s%n", knightName, monsterName);
        }
    }
    public void printBattleText(MOB dead) {
        System.out.println(dead.getName() + " was defeated!");
    }
    public void printFortunes(List<Knight> activeKnights) {
        System.out.println("For this quest, our knights drew the following fortunes!");
        for(Knight knight : activeKnights) {
            if(knight.getActiveFortune() != null) {
                System.out.println(knight.getName() + "\n" + knight.getActiveFortune().toString());
            } else { continue; }
        }
    }
    public boolean checkContinue() {
        System.out.println("Would you like to continue? (y/n)");
        String userInput = in.nextLine();
        if(userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
            return true;
        } else {
            return false;
        }
    }
    public void printDefeated() {
        //called if all active knights are defeated
        System.out.println("All active knights have been defeated!" + "\n");
    }



    public static void main(String[] args) {

    }

}
