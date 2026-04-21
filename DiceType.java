/******************** Joshua C CS162 ********************/
/******************* FIRST DRAFT DONE *******************/

import java.util.Random;
public enum DiceType {
    D4(4), D6(6), D8(8), D10(10), D12(12), D20(20);

	private final int size;
	public static Random random_machine;

    /********************* Initializing the Random Machine  *********************/
    static {
        random_machine = new Random();
    }

	private DiceType(int size) {
		this.size = size;
	}

	/***
	 * Roll
	 * 
	 * @return int representing a random roll between 1 to size
	 */
	public int Roll() {
		return random_machine.nextInt(this.size) + 1;
	}

	public static void main(String[] args) {

    /***************************** TESTING BLOCK *****************************

    // Initialize the random machine
    DiceType.random_machine = new Random();

    // Test the DiceType enum
    System.out.println("Testing Dice Rolls:");

    for (DiceType dice : DiceType.values()) {
        System.out.println("Rolling a " + dice.name() + ": " + dice.Roll());
    }
    *************************************************************************/
}
}
