//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 Inventory System Tester
// Course: CS 300 Spring 2020
//
// Author: Kaiwen Shi
// Email: kshi42@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NOT GET HELP FROM OTHER PERSON
// Online Sources: NOT GET HELP FROM ONLINE SOURCES
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This is the test class to test if InventoryList.java functioning correctly.
 */
public class InventorySystemTester {
  /**
   * checks for the correctness of the InventoryList.clear() method
   * 
   * @return true when pass all tests
   */
  public static boolean testClear() {
    InventoryList list = new InventoryList();
    Box.restartNextInventoryNumber();

    Box box1 = new Box(Color.BLUE);
    Box box2 = new Box(Color.YELLOW);
    Box box3 = new Box(Color.BLUE);
    Box box4 = new Box(Color.BROWN);
    Box box5 = new Box(Color.BLUE);
    Box box6 = new Box(Color.YELLOW);
    list.addBlue(box1);
    list.addYellow(box2);
    list.addBlue(box3);
    list.addBrown(box4);
    list.addBlue(box5);
    list.addYellow(box6);

    list.clear();
    if (!list.isEmpty()) {
      System.out.println("ERROR! BUG in Clear METHOD");
      return false;
    } else {
      System.out.println("TestClear PASSED!");
      return true;
    }
  }

  /**
   * checks for the correctness of the InventoryList.addYellow(), InventoryList.addBlue(), and
   * InventoryList.addBrown() methods
   * 
   * @return true when pass all tests
   */
  public static boolean testAddBoxes() {
    InventoryList list = new InventoryList();
    Box.restartNextInventoryNumber();

    Box box1 = new Box(Color.BLUE);
    Box box2 = new Box(Color.YELLOW);
    Box box3 = new Box(Color.BLUE);
    Box box4 = new Box(Color.BROWN);
    Box box5 = new Box(Color.BLUE);
    Box box6 = new Box(Color.YELLOW);

    list.addBlue(box1);
    list.addYellow(box2);
    list.addBlue(box3);
    list.addBrown(box4);
    list.addBlue(box5);
    list.addYellow(box6);
    if (!list.toString()
        .equals("YELLOW 6 -> YELLOW 2 -> BLUE 5 -> BLUE 3 -> BLUE 1 -> BROWN 4 -> END")) {
      System.out.println("ERROR! AddBoxes method bug.");
      return false;
    } else {
      System.out.println("TestAddBoxes PASSED");
      return true;
    }
  }

  /**
   * checks for the correctness of the InventoryList.removeBox() InventoryList.removeYellow(), and
   * InventoryList.remove Brown() methods
   * 
   * @return true when pass all tests
   */
  public static boolean testRemoveBoxes() {
    InventoryList list = new InventoryList();
    Box.restartNextInventoryNumber();

    Box box1 = new Box(Color.BLUE);
    Box box2 = new Box(Color.YELLOW);
    Box box3 = new Box(Color.BLUE);
    Box box4 = new Box(Color.BROWN);
    Box box5 = new Box(Color.BLUE);
    Box box6 = new Box(Color.YELLOW);

    list.addBlue(box1);
    list.addYellow(box2);
    list.addBlue(box3);
    list.addBrown(box4);
    list.addBlue(box5);
    list.addYellow(box6);

    list.removeBox(3);
    list.removeBrown();
    list.removeYellow();
    if (!list.toString().equals("YELLOW 2 -> BLUE 5 -> BLUE 1 -> END")) {
      System.out.println("ERROR! removeBoxes/ removeYellow/ removeBrown method bug");
      return false;
    }
    System.out.println("TestRemoveBoxes PASSED!");
    return true;
  }

  /**
   * checks for the correctness of the InventoryList.get() method
   * 
   * @return true when pass all tests
   */
  public static boolean testGetBoxes() {
    InventoryList list = new InventoryList();
    Box.restartNextInventoryNumber();

    Box box1 = new Box(Color.BLUE);
    Box box2 = new Box(Color.YELLOW);
    Box box3 = new Box(Color.BLUE);
    Box box4 = new Box(Color.BROWN);
    Box box5 = new Box(Color.BLUE);
    Box box6 = new Box(Color.YELLOW);

    list.addBlue(box1);
    list.addYellow(box2);
    list.addBlue(box3);
    list.addBrown(box4);
    list.addBlue(box5);
    list.addYellow(box6);

    if (list.get(5) != box4) {
      System.out.println("ERROR! TestGetBoxes Fail.");
      return false;
    } else {
      System.out.println("TestGetBoxes PASSED!");
      return true;
    }
  }

  /**
   * a test suite method to run all your test methods
   * 
   * @return true when pass all tests
   */
  public static boolean runAllTests() {
    if (testAddBoxes() && testClear() && testGetBoxes() && testRemoveBoxes()) {
      System.out.println("ALL TESTS PASSED!");
      return true;
    } else {
      return false;
    }
  }

  /**
   * main method of Tester
   * 
   * @param args
   */
  public static void main(String[] args) {
    // run All tests here
    runAllTests();
  }
}
