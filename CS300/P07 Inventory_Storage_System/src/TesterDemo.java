import java.util.NoSuchElementException;
public class TesterDemo{
    public static void main(String[] args) {
        demo();
    }

    /**
     * Helper method to display the size and the count of different boxes stored in a list of boxes
     * 
     * @param list a reference to an InventoryList object
     * @throws NullPointerException if list is null
     */
    private static void displaySizeCounts(InventoryList list) {
        System.out.println("  Size: " + list.size() + ", yellowCount: " + list.getYellowCount()
            + ", blueCount: " + list.getBlueCount() + ", brownCount: " + list.getBrownCount());
    }

    /**
     * Demo method showing how to use the implemented classes in P07 Inventory Storage System
     * 
     * @param args input arguments
     */
    public static void demo() {
        // Create a new empty InventoryList object
        InventoryList list = new InventoryList();
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        // Add a blue box to an empty list
        list.addBlue(new Box(Color.BLUE)); // adds BLUE 1
        System.out.println(list); // prints list's content
        list.addYellow(new Box(Color.YELLOW)); // adds YELLOW 2 at the head of the list
        System.out.println(list); // prints list's content
        list.addBlue(new Box(Color.BLUE)); // adds BLUE 3
        System.out.println(list); // prints list's content
        list.addYellow(new Box(Color.YELLOW)); // adds YELLOW 4
        System.out.println(list); // prints list's content
        list.addYellow(new Box(Color.YELLOW)); // adds YELLOW 5 at the head of the list
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        // Add more boxes to list and display its contents
        list.addBrown(new Box(Color.BROWN)); // adds BROWN 6 at the end of the list
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        list.addBrown(new Box(Color.BROWN)); // adds BROWN 7 at the end of the list
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        list.removeBrown(); // removes BROWN 7 from the list
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        list.addBlue(new Box(Color.BLUE)); // adds BLUE 8
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        list.removeBrown(); // removes BROWN 6 from the list
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        list.removeYellow(); // removes YELLOW 5
        System.out.println(list); // prints list's content
        list.removeBox(3); // removes BLUE 3 from the list
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        
        try {
        list.removeBox(25); // tries to remove box #25
    } catch (NoSuchElementException e) {
        System.out.println(e.getMessage());
    }
        
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        // remove all yellow boxes
        while (list.getYellowCount() != 0) {
        list.removeYellow();
        }
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        list.removeBox(1); // removes BLUE 1 from the list -> empty list
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        list.addBrown(new Box(Color.BROWN)); // adds BROWN 9 to the list
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        list.removeBox(8); // removes BLUE 8 from the list
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        list.removeBrown(); // removes BROWN 9 from the list
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        list.addYellow(new Box(Color.YELLOW)); // adds YELLOW 10 to the list
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
        list.removeBox(10); // removes YELLOW 10 from the list
        System.out.println(list); // prints list's content
        displaySizeCounts(list); // displays list's size and counts
    }
}