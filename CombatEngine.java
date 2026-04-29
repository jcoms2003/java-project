/******************** Joshua C CS162 ********************/
/******************* FIRST DRAFT DONE ****************/

import java.util.Random;
import java.util.List;

public class CombatEngine {
    private final GameData data;
    private final Random rnd;
    private final GameView view; 

    // Constructor
    public CombatEngine(GameData data, GameView view) {
        this.data = data;
        this.view = view;
        this.rnd = new Random();
    }
    // Methods
    public void initialize() {
        List<Knight> knights = data.getActiveKnights();
        for(Knight knight : knights) {
            knight.setActiveFortune(data.getRandomFortune());
        }
        view.printFortunes(knights);
    }
    /**********************************FOR TESTING ONLY************************************* */
    // public void initialize() {
    //     List<Knight> knights = data.knights;
    //     for(Knight knight : knights) {
    //         knight.setActiveFortune(data.getRandomFortune());
    //     }
    //     view.printFortunes(knights);
    // }

    public void runCombat() {  /* Optional Method */  
        // Check if there are active knights
        if (data.activeKnights.size() == 0) {
            System.out.println("No active knights available for combat.");
            return;
    }
        // Generates random monsters for battle 
        int max = data.activeKnights.size();
        int count = rnd.nextInt(max) + 1;  
        List<MOB> rndMobs = data.getRandomMonsters(count);
        // Runs while monsters and knights are not 0
        while(data.activeKnights.size() != 0 && rndMobs.size() != 0) {
            view.printBattleText(rndMobs, data.activeKnights);
            int interate = 0;
        MOB m = rndMobs.get(interate);
        Knight k = data.activeKnights.get(interate);
        for(int i = 0; i <= data.activeKnights.size(); i++) {
            int roll = DiceType.D20.Roll();
            int totalM = roll + m.getHitModifier();
            int totalK = roll + k.getHitModifier();
            
            // Check mob win
            if(totalM > k.getArmor()) {
                int newRoll = m.getDamageDie().Roll();
                k.addDamage(newRoll);
            }
            // Check Knight win
            else if(totalK > m.getArmor()) {
                int newRoll = k.getDamageDie().Roll();
                m.addDamage(newRoll);
            }
            // Check if mob defeated
            if(m.getHP() <= 0) {
                view.printBattleText(m);
                for(Knight knight : data.activeKnights) {
                    knight.addXP(1);
                }
            }
            // Check if knight defeated
            else if(k.getHP() <+ 0) {
                view.printBattleText(k);
                data.removeActive(k);
            }

        }
        }
        // If mobs go to 0, user will be prompted to continue or quit
        if(rndMobs.size() == 0) {
                // User choice
                view.checkContinue();
                // If the choose T it runs again
                if(view.checkContinue()){
                    runCombat();
                }
            }
    }
    
    // private int doBattle(List<MOB> attackers, List<Knight> defenders) {
    //     // naming index for one on one combat
    //     int interate = 0;
    //     MOB m = attackers.get(interate);
    //     Knight k = defenders.get(interate);
    //     for(int i = 0; i <= defenders.size(); i++) {
    //         int roll = DiceType.D20.Roll();
    //         int totalM = roll + m.getHitModifier();
    //         int totalK = roll + k.getHitModifier();
            
    //         // Check mob win
    //         if(totalM > k.getArmor()) {
    //             int newRoll = m.getDamageDie().Roll();
    //             k.addDamage(newRoll);
    //         }
    //         // Check Knight win
    //         else if(totalK > m.getArmor()) {
    //             int newRoll = k.getDamageDie().Roll();
    //             m.addDamage(newRoll);
    //         }
    //         // Check if mob defeated
    //         if(m.getHP() <= 0) {
    //             view.printBattleText(m);
    //             for(Knight knight : defenders) {
    //                 knight.addXP(1);
    //             }
    //         }
    //         // Check if knight defeated
    //         else if(k.getHP() <+ 0) {
    //             view.printBattleText(k);
    //             data.removeActive(k);
    //         }

    //     }
        
    // }

    // Cycles through all the knights and sets fortune to "null" 
    public void clear() {
        List<Knight> knights = data.knights;
        for(Knight knight : knights) {
            knight.setActiveFortune(null);
        }
    }

    public static void main(String[] args) {
        // Create game data and view
        GameData gameData = new CSVGameData("gamedata.csv", "knights.csv");
        GameView gameView = new ConsoleView();

        // Create combat engine
        CombatEngine engine = new CombatEngine(gameData, gameView);

                // Test setting active knights
        System.out.println("Setting active knights...");
        List<Knight> knights = gameData.getKnights();
        for (int i = 0; i < Math.min(knights.size(), 4); i++) {
            Knight knight = knights.get(i);
            boolean success = gameData.setActive(knight);
            if (success) {
                System.out.println("Knight " + knight.getName() + " is now active.");
            } else {
                System.out.println("Failed to set knight " + knight.getName() + " as active.");
            }
        }

        // Initialize and run combat
        System.out.println("Initializing combat...");
        engine.initialize();
        System.out.println("Running combat...");
        engine.runCombat();


        // // Print active knights
        // System.out.println("\nActive knights:");
        // for (Knight activeKnight : gameData.getActiveKnights()) {
        //     System.out.println(activeKnight);
        // }
    }
}