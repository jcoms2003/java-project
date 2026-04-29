/******************** Joshua C CS162 ********************/
/******************* FIRST DRAFT NotDONEE *******************/
/* Need to finish processCommand and Start */

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
            if(choice.equalsIgnoreCase("Exit") || choice.equalsIgnoreCase("Bye")) {
                view.endGame();
                
            } 
            else if(choice.equalsIgnoreCase("ls") || choice.equalsIgnoreCase("list all")) {
                data.getKnights();
                startQ();
            }
            else if(choice.startsWith("show")) {
                System.out.println("GOOD!");
                startQ();
            } 
        } 
    }
    /*protected boolean processCommand(String command) {
        String choice = command; 
        if(choice.equalsIgnoreCase("Exit") || choice.equalsIgnoreCase("Bye")) {
            view.endGame();
            return false;
        } 
        else if(choice.equalsIgnoreCase("ls") || choice.equalsIgnoreCase("list all")) {
            data.getKnights();
            start();
        }
        else if(choice.startsWith("show")) {

        } return true;
    }*/
    private void processRemoveActive(String remove) {
        String nameOrID = remove;
        List<Knight> actKt = data.getActiveKnights();
        for(Knight knight : actKt) {
            if(knight.getName().equalsIgnoreCase(nameOrID) || knight.getID().toString().equalsIgnoreCase(nameOrID)) {
                data.removeActive(knight);
            } else {view.knightNotFound();}
        }
    }
    private void processSetActive(String activeKt) {
        Knight kt = data.getKnight(activeKt);
        if(kt != null) {
            data.setActive(kt);
            if(data.setActive(kt) == false) {
                view.setActiveFailed();
            }
        } else {view.knightNotFound();}
    }

    private void processShowKnight(String nameOrID) {
        Knight kt = data.getKnight(nameOrID);
        if(kt != null) {
            view.showKnight(kt);
        } else {view.knightNotFound();}
    }

    public static void main(String[] args) {
        // Mock data for GameData
        List<Fortune> fortunes = new ArrayList<>();
        List<MOB> monsters = new ArrayList<>();
        List<Knight> knights = new ArrayList<>();
        List<Knight> activeKnights = new ArrayList<>();

        // Initialize GameData
        GameData data = new GameData(fortunes, monsters, knights, activeKnights) {
            @Override
            public void save(String s) {
                    // TODO Auto-generated method stub

            }
            // Provide any necessary overrides if GameData has abstract methods
        };

        // Initialize ConsoleView
        GameView view = new ConsoleView();

        // Initialize CombatEngine
        CombatEngine engine = new CombatEngine(data, view);

        // Create GameController instance
        GameController controller = new GameController(data, view, engine);

        // Call the start method to test interactively
        controller.start();
    }
}