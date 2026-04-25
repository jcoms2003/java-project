/******************** Joshua C CS162 ********************/
/******************* FIRST DRAFT DONE *******************/

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public abstract class GameData {

    /* Instance Variables */
    private static final int MAX_ACTIVE = 4;
    private static final Random random = new Random();
    protected final List<Fortune> fortunes;
    protected final List<MOB> monsters;
    protected final List<Knight> knights;
    protected final List<Knight> activeKnights;

    /* Constructor */
    public GameData(List<Fortune> fortunes, List<MOB> monsters, List<Knight> knights, List<Knight> activeKnights) {
        this.fortunes = fortunes;
        this.monsters = monsters;
        this.knights = knights;
        this.activeKnights = activeKnights;
    }

    /* Methods */
    public List<Knight> getKnights() {
        return knights;
    }
    public Knight getActive(String nameOrID) {
        for(Knight knight : activeKnights) {
            if(knight.getName().equalsIgnoreCase(nameOrID) || knight.getID().toString().equals(nameOrID)) {
                return knight;
            }
        }
        return null;
    }
    public Knight getKnight(String nameOrID) {
        for(Knight knight : knights) {
            if(knight.getName().equalsIgnoreCase(nameOrID) || knight.getID().toString().equals(nameOrID)) {
                return knight;
            }
        }
        return null;
    }
    protected Knight findKnight(String nameOrID, List<Knight> list) {
        for(Knight knight : list) {
            if(knight.getName().contains(nameOrID) || knight.getID().toString().equals(nameOrID)) {
                return knight;
            }
        }
        return null;
    }
    public boolean setActive(Knight kt) {
        if(activeKnights.size() < MAX_ACTIVE) {
            activeKnights.add(kt);
            return true;
        } else {
            return false;
        }
    }
    public void removeActive(Knight kt) {
        kt.resetDamage();
        activeKnights.remove(kt);
    }
    public Fortune getRandomFortune() {
        return fortunes.get(random.nextInt(fortunes.size()));
    }
    public List<MOB> getRandomMonsters() {
        int max = activeKnights.size();
        if (max <= 0 || monsters.isEmpty()) {
            return List.of();
        }
        int count = random.nextInt(max) + 1;
        count = Math.min(count, monsters.size());

        List<MOB> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            result.add(monsters.get(random.nextInt(monsters.size())));
        }
        return result;
    }
    public List<MOB> getRandomMonsters(int number) {
        if (number <= 0 || monsters.isEmpty()) {
            return List.of();
        }

        List<MOB> result = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            MOB original = monsters.get(random.nextInt(monsters.size()));
            result.add(original.copy());
        }
        return result;
    }
    public abstract void save(String filename);

}