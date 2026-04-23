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

    }
    protected boolean processCommand(String command) {
        return true;  // Need to fix!!!!!!!!!!!
    }
    private void processRemoveActive(String remove) {
        activeKnights.remove(ID remove);
    }
    private void processShowKnight(String nameOrID) {
        
    }

}