public class Main {
    private static String gameData;
    private static String saveData;
    
    // Constructor
    public Main() {

    }

    private static void processArgs(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--data")) {
                // Extract the value after the = sign
                int equalsIndex = arg.indexOf('=');
                if (equalsIndex != -1) {
                    gameData = arg.substring(equalsIndex + 1);
                }
            } else {
                // Any other argument sets the saveData value
                saveData = arg;
            }
        }
    }

    public static void main(String[] args) {
        processArgs(args);
        
        // Set default file names if not provided
        if (gameData == null) {
            gameData = "gamedata.csv";
        }
        if (saveData == null) {
            saveData = "knights.csv";
        }
        
        GameData data = new CSVGameData(gameData, saveData);
        GameView view = new ConsoleView();
        CombatEngine engine = new CombatEngine(data, view);
        GameController controller = new GameController(data, view, engine);
        
        controller.start();
    }
}