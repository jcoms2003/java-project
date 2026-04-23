public class Knight extends MOB {
    private Fortune activeFortune;
    protected final int ID;
    protected int xp;

    // Constructor 
    public Knight(int id, String name, int hp, int armor, int hitmodifier, DiceType damageDie, int xp) {
        super(name, hp, armor, hitmodifier, damageDie);
        this.ID = id;
    }

    // Adding XP
    public void addXP(int i) {
        xp += i;
    }

    // Getters
    @Override
    public int getArmor() {
        if(getActiveFortune() == null) {
             return super.getArmor();
        } else {
        return armor + getActiveFortune().getArmor(); 
        } 

    }
    @Override
    public DiceType getDamageDie() {
        if(getActiveFortune() == null) {
             return super.getDamageDie();
        } else {
            if(getActiveFortune().getDamageDie() == null) {
                return super.getDamageDie();
            } else {
                return getActiveFortune().getDamageDie();
                
            }
        } 
    } 
    @Override
    public int getHitModifier() {
        if(getActiveFortune() == null) {
            return super.getHitModifier();
        } else {
            return super.getHitModifier() + getActiveFortune().getHitModifier();
        } 
    }
    public Integer getID() {
        return ID;
    }
    @Override
    public int getMaxHP() {
        if(getActiveFortune() == null) {
            return super.getMaxHP();
        } else {
        return maxHP + getActiveFortune().getMaxHP();
        } 
    }
    public int getXP() {
        return xp;
    }
    public Integer getId() {
        return ID;
    }
    
    // Setter and Getter for ACTIVE FORTUNE
    public Fortune getActiveFortune() {
        return activeFortune;
    }
    public void setActiveFortune(Fortune f) {
        activeFortune = f;
    }

    // To CSV and String methods
    public String toCSV() {
        return getName() + "," + getMaxHP() + "," + getArmor() + "," + getHitModifier() + "," + getDamageDie() + "," + getXP();
    }
    @Override
    public String toString() {
        return "+============================+\n" +
          String.format("| %-27s|%n", getName()) +
          String.format("| id: %-23d|%n", getId()) +
          "|                            |\n" +
          String.format("| Health: %-6d  XP: %-7d|%n", getHP(), getXP())  +
          String.format("|  Power: %-6s  Armor: %-4d|%n", getDamageDie().toString(), getArmor()) +
          "|                            |\n" +
          "+============================+";
    }

    public static void main(String[] args) {
        Knight paul = new Knight(5, "Paul", 6, 6, 6, DiceType.D8, 0);
        paul.setActiveFortune(null);
        System.out.println(paul.toString());
        System.out.println(paul.toCSV());

    }
    
}
