public class MOB implements Attributes {
    protected int armor;
    protected int damage;
    protected DiceType damageDie;
    protected int hitModifier;
    protected int maxHP;
    private final String name;
    

    // MOB Constructor
    public MOB(String name, int hp, int armor, int hitModifier, DiceType damageDie) {
        this.name = name;
        maxHP = hp;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.damageDie = damageDie;
    }

    // Adding Damage
    public void addDamage(int damage) {
        this.damage += damage;
    }

    // MOB copy method
    public MOB copy() {
        return new MOB(name, maxHP, armor, hitModifier, damageDie);
    }

    /* GETTERS */
    public int getDamage() {
        return damage;
    }

    public int getHP() {
        return maxHP - damage;
    }

    public String getName() {
        return name;
    }

    public void resetDamage() {
        damage = 0;
    }

    /* MOB Card To String */
    public String toString() {
        return "+============================+\n" +
                String.format("| %-27s|%n", getName()) +
                "|                            |\n" +
                String.format("|         Health: %-10d |%n", getHP())  +
                String.format("|  Power: %-6s  Armor: %-4d|%n", getDamageDie().toString(), getArmor()) +
                "|                            |\n" +
                "+============================+";
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    @Override
    public DiceType getDamageDie() {
        return damageDie;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }
    
    public static void main(String[] args) {
        MOB mob1 = new MOB("bull", 5, 3, 3, DiceType.D8);
        System.out.println(mob1.toString());
    }
}
