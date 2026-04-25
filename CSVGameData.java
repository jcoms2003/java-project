/******************** Joshua C CS162 ********************/
/******************* FIRST DRAFT DONE *******************/

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CSVGameData extends GameData {
       
    // Constructor
    public CSVGameData(String gamedata, String saveData) {
    super(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    loadGameData(gamedata);
    loadSaveData(saveData);
}

    void loadSaveData(String saveData) {
        int counter = 0;
        Scanner file = readFile(saveData);
        if(file == null) return;
        while(file.hasNextLine()) {
            Scanner line = new Scanner(file.nextLine());
            line.useDelimiter(","); 
            Knight kt = new Knight(
                    ++counter,
                    line.next().trim(),
                    line.nextInt(),
                    line.nextInt(),
                    line.nextInt(),
                    DiceType.valueOf(line.next()),
                    line.nextInt());
            knights.add(kt);
        }
    }
    void loadGameData(String gamedata) {
        Scanner file = readFile(gamedata);
        if (file == null) return;

        while (file.hasNextLine()) {
            String dataLine = file.nextLine().trim();
            if (dataLine.isEmpty()) continue;

            Scanner line = new Scanner(dataLine);
            line.useDelimiter(",");

            String type = line.next().trim();
            if (type.equals("MOB")) {
                String name = line.next().trim();
                int hp = line.nextInt();
                int armor = line.nextInt();
                int hitModifier = line.nextInt();
                DiceType damage = DiceType.valueOf(line.next().trim());
                MOB mob = new MOB(name, hp, armor, hitModifier, damage);
                monsters.add(mob);
            } else if (type.equals("FORTUNE")) {
                String name = line.next().trim();
                int hp = line.nextInt();
                int armor = line.nextInt();
                int hitModifier = line.nextInt();
                String damageStr = line.next().trim();
                DiceType damage = null;
                if (!damageStr.equals("-")) {
                    damage = DiceType.valueOf(damageStr);
                }
                Fortune fortune = new Fortune(name, hp, armor, hitModifier, damage);
                fortunes.add(fortune);
            }
        }
    }
    private void parseGameDataLine(Scanner line) {
        String type = line.next().trim();
        if (type.equals("MOB")) {
            String name = line.next().trim();
            int hp = line.nextInt();
            int armor = line.nextInt();
            int hitModifier = line.nextInt();
            DiceType damage = DiceType.valueOf(line.next().trim());
            MOB mob = new MOB(name, hp, armor, hitModifier, damage);
            monsters.add(mob);
        } else if (type.equals("FORTUNE")) {
            String name = line.next().trim();
            int hp = line.nextInt();
            int armor = line.nextInt();
            int hitModifier = line.nextInt();
            String damageStr = line.next().trim();
            DiceType damage = null;
            if (!damageStr.equals("-")) {
                damage = DiceType.valueOf(damageStr);
            }
            Fortune fortune = new Fortune(name, hp, armor, hitModifier, damage);
            fortunes.add(fortune);
        }
    }
    public void save(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Knight knight : knights) {
                writer.println(knight.toCSV());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error saving knights to file: " + e.getMessage());
        }
    }

    private Scanner readFile(String fileName) {
        try {
            return new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            return null;
        }
    }

    public static void main(String[] args) {
        // Test file paths
        String gameDataFile = "gamedata.csv";
        String saveDataFile = "knights.csv";

        // Create an instance of CSVGameData
        CSVGameData gameData = new CSVGameData(gameDataFile, saveDataFile);

        // Test loading game data
        System.out.println("Monsters loaded:");
        for (MOB mob : gameData.monsters) {
            System.out.println(mob);
        }

        System.out.println("\nFortunes loaded:");
        for (Fortune fortune : gameData.fortunes) {
            System.out.println(fortune);
        }

        // Test saving knights data
        System.out.println("\nSaving knights to file...");
        gameData.save(saveDataFile);
        System.out.println("Knights saved to " + saveDataFile);
    }
}