public class Fortune implements Attributes {
    private final int armor;
    private final int hpBonus;
    private final int hitModifier;
    private final DiceType dtype;
    private final String name;

    public Fortune(String name, int hpBonus, int armor, int hitModifier) {
        this(name, hpBonus, armor, hitModifier, null);
    }

    public Fortune(String name, int hpBonus, int armor, int hitModifier, DiceType type) {
        this.armor = armor;
        this.hpBonus = hpBonus;
        this.name = name;
        this.hitModifier = hitModifier;
        dtype = type;
    }
    @Override
    public int getArmor() {
        return armor;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getMaxHP() {
        return hpBonus;
    }

    @Override
    public DiceType getDamageDie() {
        return dtype;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    @Override
    public String toString() {
        return "+======================+\n" +
                String.format("|%-22s|%n", getName()) +
                String.format("|HP Bonus: %12s|%n", "+" + getMaxHP()) +
                String.format("|AC Bonus: %12s|%n", "+" + getArmor()) +
                String.format("|Hit Bonus: %11s|%n", "+" + getHitModifier()) +
                String.format("|Damage Adj: %10s|%n", getDamageDie() != null ? getDamageDie() : "-") +
                "+======================+";
    }

    public static void main(String[] args) {
        Fortune ftn = new Fortune("Merlin Luck", 10, 5, 2, DiceType.D12);
        System.out.println("TESTING Armor in fortune " + ftn.getArmor());
        System.out.println("TESTING Armor in fortune " + ftn.getMaxHP());

    }
    
}
