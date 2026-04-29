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

    public List<Knight> getActiveKnights() {
        return activeKnights;
    }

    public Knight getKnight(String nameOrID) {
        for(Knight knight : knights) {
            if(knight.getName().equals(nameOrID) || knight.getID().toString().equals(nameOrID)) {
                return knight;
            }
        }
        return null;
    }
    protected Knight findKnight(String nameOrID, List<Knight> list) {
        for(Knight knight : list) {
            if(knight.getName().equals(nameOrID) || knight.getID().toString().equals(nameOrID)) {
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

    /* Main method for testing getKnight and findKnight */
    public static void main(String[] args) {
        // Create ArrayLists for test data
        List<Fortune> testFortunes = new ArrayList<>();
        List<MOB> testMonsters = new ArrayList<>();
        List<Knight> testKnights = new ArrayList<>();
        List<Knight> activeKnights = new ArrayList<>();

        // Create test Knight objects
        Knight knight1 = new Knight(1, "Aragorn", 100, 15, 5, DiceType.D8, 0);
        Knight knight2 = new Knight(2, "Legolas", 85, 12, 6, DiceType.D6, 0);
        Knight knight3 = new Knight(3, "Gimli", 120, 18, 4, DiceType.D10, 0);

        // Add knights to the list
        testKnights.add(knight1);
        testKnights.add(knight2);
        testKnights.add(knight3);

        // Create a concrete GameData instance for testing
        TestGameData testData = new TestGameData(testFortunes, testMonsters, testKnights, activeKnights);

        // Test getKnight method
        System.out.println("=== Testing getKnight ===");
        Knight found1 = testData.getKnight("Aragorn");
        System.out.println("getKnight(\"Aragorn\"): " + (found1 != null ? found1.getName() : "Not Found"));

        Knight found2 = testData.getKnight("1");
        System.out.println("getKnight(\"1\"): " + (found2 != null ? found2.getName() : "Not Found"));

        Knight notFound = testData.getKnight("NonExistent");
        System.out.println("getKnight(\"NonExistent\"): " + (notFound != null ? notFound.getName() : "Not Found"));

        // Test findKnight method
        System.out.println("\n=== Testing findKnight ===");
        Knight found3 = testData.findKnight("Legolas", testKnights);
        System.out.println("findKnight(\"Legolas\", testKnights): " + (found3 != null ? found3.getName() : "Not Found"));

        Knight found4 = testData.findKnight("2", testKnights);
        System.out.println("findKnight(\"2\", testKnights): " + (found4 != null ? found4.getName() : "Not Found"));

        Knight notFound2 = testData.findKnight("Nonexistent", testKnights);
        System.out.println("findKnight(\"Nonexistent\", testKnights): " + (notFound2 != null ? notFound2.getName() : "Not Found"));

        System.out.println("\n=== All Knights in List ===");
        for (Knight knight : testKnights) {
            System.out.println("ID: " + knight.getID() + ", Name: " + knight.getName());
        }
    }

    /* Helper test class */
    static class TestGameData extends GameData {
        public TestGameData(List<Fortune> fortunes, List<MOB> monsters, List<Knight> knights, List<Knight> activeKnights) {
            super(fortunes, monsters, knights, activeKnights);
        }

        @Override
        public void save(String filename) {
            // Dummy implementation for testing
        }
    }

}