/******************** Joshua C CS162 ********************/
/******************* FIRST DRAFT DONE *******************/

import java.util.List;
import java.util.ArrayList;

public class GameController {
    private final GameData data;
    private final GameView view;
    private final CombatEngine engine;

    // Constructor
    public GameController(GameData data, GameView view, CombatEngine engine) {
        this.data = data;
        this.view = view;
        this.engine = engine;
    }
    public void start() {
        view.splashScreen();
        startQ();
    }
    public void startQ() {
        boolean end = true;
        while (end) {
            String choice = view.displayMainMenu();
            end = processCommand(choice);
        }
    }

    protected boolean processCommand(String command) {
        // Map numeric inputs to corresponding commands
        switch (command) {
            case "1":
                command = "list all";
                break;
            case "2":
                command = "list active";
                break;
            case "3":
                command = "show"; // Placeholder for specific knight details
                break;
            case "4":
                command = "explore";
                break;
            default:
                break;
        }

        if(command.equalsIgnoreCase("Exit") || command.equalsIgnoreCase("Bye")) {
            view.endGame();
            return false;
        } 
        else if(command.equalsIgnoreCase("ls") || command.equalsIgnoreCase("list all") || command.equalsIgnoreCase("list")) {
            view.listKnights(data.getKnights());
        }
        else if(command.equalsIgnoreCase("list active")) {
            view.listKnights(data.getActiveKnights());
        }
        else if(command.startsWith("show")) {
            String nameOrID = command.substring(4).trim();
            processShowKnight(nameOrID);
        }
        else if(command.startsWith("set active")) {
            String nameOrID = command.substring(10).trim();
            processSetActive(nameOrID);
        }
        else if(command.startsWith("remove active")) {
            String nameOrID = command.substring(13).trim();
            processRemoveActive(nameOrID);
        }
        else if(command.equalsIgnoreCase("explore") || command.equalsIgnoreCase("adventure") || command.equalsIgnoreCase("quest")) {
            engine.initialize();
            engine.runCombat();
            engine.clear();
        }
        else if(command.startsWith("save")) {
            String filename = command.substring(4).trim();
            if(filename.isEmpty()) {
                filename = "saveData.csv";
            }
            data.save(filename);
        }
        else {
            view.printHelp();
        }
        return true;
    }
    private void processRemoveActive(String remove) {
        Knight knight = data.getActive(remove);
        if(knight != null) {
            data.removeActive(knight);
        } else {
            view.knightNotFound();
        }
    }
    private void processSetActive(String activeKt) {
        Knight kt = data.getKnight(activeKt);
        if(kt != null) {
            if(!data.setActive(kt)) {
                view.setActiveFailed();
            }
        } else {
            view.knightNotFound();
        }
    }

    private void processShowKnight(String nameOrID) {
        Knight kt = data.getKnight(nameOrID);
        if(kt != null) {
            view.showKnight(kt);
        } else {view.knightNotFound();}
    }

    public static void main(String[] args) {
        /********************************************************************************
        ================================TESTING=========================================|
        // Mock data for GameData                                                       |
        List<Fortune> fortunes = new ArrayList<>();                                     |
        List<MOB> monsters = new ArrayList<>();                                         |
        List<Knight> knights = new ArrayList<>();                                       |
        List<Knight> activeKnights = new ArrayList<>();                                 |
                                                                                        |
        // Populate fortunes with test data                                             |
        fortunes.add(new Fortune("Lucky Strike", 5, 2, 1, DiceType.D12));               |
        fortunes.add(new Fortune("Iron Skin", 0, 5, 0));                                |
        fortunes.add(new Fortune("Swift Blade", 0, 0, 2, DiceType.D10));                |
                                                                                        |
        // Populate monsters with test data                                             |
        monsters.add(new MOB("Goblin", 15, 10, 1, DiceType.D8));                        |
        monsters.add(new MOB("Orc", 25, 12, 2, DiceType.D10));                          |
        monsters.add(new MOB("Troll", 40, 14, 3, DiceType.D12));                        |
                                                                                        |
        // Populate knights with test data                                              |
        knights.add(new Knight(1, "Arthur", 50, 16, 3, DiceType.D10, 0));               |
        knights.add(new Knight(2, "Lancelot", 45, 15, 4, DiceType.D10, 0));             |
        knights.add(new Knight(3, "Galahad", 40, 14, 2, DiceType.D8, 0));               |
        knights.add(new Knight(4, "Mordred", 55, 17, 5, DiceType.D12, 0));              |
                                                                                        |
        // Initialize GameData                                                          |
        GameData data = new GameData(fortunes, monsters, knights, activeKnights) {      |
            @Override                                                                   |
            public void save(String s) {                                                |
                                                                                        |
            }                                                                           |
            // Provide any necessary overrides if GameData has abstract methods         |
        };                                                                              |
                                                                                        |
        // Initialize ConsoleView                                                       |
        GameView view = new ConsoleView();                                              |
                                                                                        |
        // Initialize CombatEngine                                                      |
        CombatEngine engine = new CombatEngine(data, view);                             |
                                                                                        |
        // Create GameController instance                                               |
        GameController controller = new GameController(data, view, engine);             |
                                                                                        |
        // Call the start method to test interactively                                  |
        controller.start();                                                             |
        ********************************************************************************/      
    }
}