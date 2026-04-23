import java.util.Random;
import java.util.List;
public class CombatEngine {
    private final GameData data;
    private Random rnd;
    private final GameView view;

    // Constructor
    public CombatEngine(GameData data, GameView view) {
        this.data = data;
        this.view = view;
    }
    // Methods
    public void initialize() {

    }
    public void runCombat() {  /* Optional Method */

    }
    private int doBattle(List<MOB> attackers, List<MOB> defenders) {
        return 0;
    }
    public void clear() {
        for(Knight knight : knights) {

        }
    }

}