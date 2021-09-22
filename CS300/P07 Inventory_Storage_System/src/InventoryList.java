//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 InventoryList
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
import java.util.NoSuchElementException;

/**
 * This class models the singly linked list data structure that stores elementsof typeBox.
 */
public class InventoryList {
  // initialize instance field
  // head and tail of list
  private LinkedBox head = null;
  private LinkedBox tail = null;

  // list size and color box counter
  private int size = 0;
  private int yellowCount = 0;
  private int blueCount = 0;
  private int brownCount = 0;

  /**
   * Adds a new blue box at the top of blue boxes if the list contains any blue box. Blue boxes must
   * be added at the buttom of yellow boxes and at the top of all the brown boxes if any. This means
   * that a new blue box must be added at index yellowCount
   * 
   * @param blueBox new box to be added to this list
   * @throws IllegalArugmentException with a descriptive error message if blueBox is null or if it
   *                                  does not have a Color.BLUE color
   */
  public void addBlue(Box blueBox) {
    // check if blueBox is null or any blueBox has inequal color
    // to the color.BLUE
    if (blueBox == null && blueBox.getColor() != Color.BLUE) {
      throw new IllegalArgumentException(
          "Null Blue box OR a blue box has" + "non-identical color to color.BLUE!");
    }

    // if checked with no error blueBox / check with non-null blueBox
    // get the box want to adding in
    LinkedBox currBlue = new LinkedBox(blueBox);
    // initialize a curr Box for traverse
    LinkedBox curr = head;

    // check if the list is empty
    if (head == null && tail == null) {
      head = currBlue;
      tail = currBlue;
    } else {
      // when there is only blue & yellow Boxes there.
      // just find the last yellowBox regardless of its next Node
      for (int i = 0; i < yellowCount - 1; i++) {
        curr = curr.getNext();
      }

      // when only yellow box inside, then change the tail to the blue
      // box that want to add in
      if (size == yellowCount) {
        tail = currBlue;
      }

      // set blueBox's (adding in) as currentBox.next and set currentBox.next = blueBox
      currBlue.setNext(curr.getNext());
      curr.setNext(currBlue);
    }

    // increment size and blueCount finally
    size++;
    blueCount++;
  }

  /**
   * Adds a brown box at the end of this inventory list
   * 
   * @param brownBox new brown box to be added to this list
   * @throws IllegalArgumentException with a descriptive error message if brownBox is null or if the
   *                                  color of the specific brownBox is not equal to Color.BROWN
   */
  public void addBrown(Box brownBox) {
    // first check if brownBox is null or if color of any brownBox
    // not equal to color.BROWN
    if (brownBox == null && !brownBox.getColor().equals(Color.BROWN)) {
      throw new IllegalArgumentException(
          "Null Brown box OR a brown box has" + "non-identical color to color.BROWN!");
    }

    // if checked with no error brownBox / check with non-null
    // brownBox get the box want to adding in
    LinkedBox currBrown = new LinkedBox(brownBox);

    // check if the list is empty
    if (head == null && tail == null) {
      head = currBrown;
      tail = currBrown;
    } else {
      // if the list is not empty, simply add to the last one
      tail.setNext(currBrown);
      tail = currBrown;
    }

    // increment brownCount and list size finally
    size++;
    brownCount++;
  }

  /**
   * Adds a new yellow box at the head of this list
   * 
   * @param yellowBox new box to be added to this list
   * @throws IllegalArgumentException with a descriptive error message if yellowBox is null or if
   *                                  its color does not equal to Color.YELLOW
   */
  public void addYellow(Box yellowBox) {
    // first check if yellow box is null or if color of any yellowBox
    // not equal to color.YELLOW
    if (yellowBox == null && !yellowBox.getColor().equals(Color.YELLOW)) {
      throw new IllegalArgumentException(
          "Null Brown box OR a brown box has" + "non-identical color to color.BROWN!");
    }

    // if checked with no error brownBox / check with non-null brownBox
    // get the box want to adding in
    LinkedBox currYellow = new LinkedBox(yellowBox);

    if (head == null && tail == null) {
      // when list is empty
      head = currYellow;
      tail = currYellow;
    } else {
      // when list is not empty: just added it at the head of list
      currYellow.setNext(head);
      head = currYellow;
    }

    // increment size and yellowCount finally
    size++;
    yellowCount++;
  }

  /**
   * Removes all of the elements from this list. The list will be empty after this call returns.
   */
  public void clear() {
    LinkedBox curr = head;
    // reset sizes, head & tails and all 3 counts to default value (0 / null)
    head = null;
    tail = null;
    size = 0;
    blueCount = 0;
    brownCount = 0;
    yellowCount = 0;

    // traverse list and set all element equal to null
    for (int i = 0; i < size; i++) {
      curr.setNext(null);
      curr = curr.getNext();
    }
  }

  /**
   * Returns the element stored at position index of this list without removing it.
   * 
   * @param index position within this list
   * @return the box stored at position index of this list
   * @throws IndexOutOfBoundsException with a descriptive error message if the index is out of
   *                                   bounds
   */
  public Box get(int index) {
    int count = 0;
    LinkedBox curr = head;
    // traverse list to find the box at given position index
    while (curr.getNext() != null && count < index) {
      count++;
      curr = curr.getNext();
    }

    // if jump out while loop: 1. box found; 2. box not in the list
    if (count == index) {
      // Case 1: when box is found
      return curr.getBox();
    } else {
      // Case 2: when box is not found: throw exception.
      throw new IndexOutOfBoundsException(
          "ERROR! Element not store in list" + "throw IndexOutOfBoundsException!");
    }
  }

  /**
   * Returns the number of blue boxes stored in this list
   * 
   * @return blueCount
   */
  public int getBlueCount() {
    return blueCount;
  }

  /**
   * Returns the number of brown boxes stored in this list
   * 
   * @return brownCount
   */
  public int getBrownCount() {
    return brownCount;
  }

  /**
   * Returns the number of yellow boxes stored in this list
   * 
   * @return yellowCount
   */
  public int getYellowCount() {
    return yellowCount;
  }

  /**
   * Checks whether this LinkedBoxList is empty
   * 
   * @return true if this LinkedBoxList is empty, false otherwise
   */
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }

    return false;
  }

  /**
   * Removes and returns a box given its inventory number from this list
   * 
   * @param inventoryNumber inventory number of the box to be removed from this list
   * @return a reference to the removed element
   * @throws NoSuchElementException with a descriptive error message if no box is found with the
   *                                provided inventory number in the list.
   */
  public Box removeBox(int inventoryNumber) {
    LinkedBox curr = head;
    LinkedBox currPrev = null;
    LinkedBox removedBox = null;
    int count = 0;

    // Case 1: when list is empty;
    if (head == null && tail == null) {
      throw new NoSuchElementException("ERROR! Empty list");
    } else if (size == 1 && head.getBox().getInventoryNumber() == inventoryNumber) {
      // Case 2: when only 1 box in list but just fitting
      removedBox = head;
      head = null;
      tail = null;
    } else {
      // traverse list: list size > 1
      while (curr != null && curr.getBox().getInventoryNumber() != inventoryNumber) {
        currPrev = curr;
        curr = curr.getNext();
        count++;
      }

      // when find box with fit inventory Number, copy it into removedBox first
      if (curr != null && curr.getBox().getInventoryNumber() == inventoryNumber) {
        removedBox = curr;

        // Case 3: when find in the head of list
        if (count == 0) {
          head = head.getNext();
        } else if (count == size - 1) {
          // case 4: when find in the tail of list
          currPrev.setNext(null);
          tail = currPrev;
        } else {
          // Case 5: when find in the middle of list
          // "jump" to link to next one
          currPrev.setNext(curr.getNext());
        }
      }

    }

    // after previous 5 cases check, if removedBox still null, the box is not in the list
    if (removedBox == null) {
      throw new NoSuchElementException("ERROR! No fit inventoryNumber Box found.");
    }

    // decrement size and blueCount finally
    size--;
    if (removedBox.getBox().getColor().equals(Color.BLUE)) {
      blueCount--;
    } else if (removedBox.getBox().getColor().equals(Color.YELLOW)) {
      yellowCount--;
    } else if (removedBox.getBox().getColor().equals(Color.BROWN)) {
      brownCount--;
    }

    return removedBox.getBox();
  }

  /**
   * Removes and returns the element at the tail of this list if it has a brown color
   * 
   * @return a reference to the removed element
   * @throws NoSuchElementException with a descriptive error message if this list does not contain
   *                                any brown box
   */
  public Box removeBrown() {
    if (brownCount == 0 || !tail.getBox().getColor().equals(Color.BROWN)) {
      throw new NoSuchElementException(
          "ERROR! Invalid remove." + " There is No brown box in this list.");
    }

    LinkedBox curr = head;
    Box removedBrownBox = tail.getBox();

    // Case 1: when list is empty
    if (head == null && tail == null) {
      throw new NoSuchElementException("ERROR! Empty list.");
    } else {
      // Case 2: when list is not empty, then go to the tail of list
      while (curr != null && curr.getNext() != tail) {
        curr = curr.getNext();
      }
      // when reach the one before tail, set tail to before one
      tail = curr;
    }

    // decrement brownCount and size finally and return
    brownCount--;
    size--;
    return removedBrownBox;

  }

  /**
   * Removes and returns the box at the head of this list if its color is yellow
   * 
   * @return a reference to the removed box
   * @throws NoSuchElementException with a descriptive error message if this list does not contain
   *                                any yellow boxes
   */
  public Box removeYellow() {
    if (yellowCount == 0 || !head.getBox().getColor().equals(Color.YELLOW)) {
      throw new NoSuchElementException(
          "ERROR! Invalid remove." + " There is No yellow box in this list.");
    }

    Box removedYellowBox = null;

    // Case 1: when there is no box in list
    if (head == null && tail == null) {
      throw new NoSuchElementException("ERROR! Empty list");
    } else {
      // Case 2: when there is boxes already in the list
      removedYellowBox = head.getBox();
      head = head.getNext();
    }


    // decrement yellowCount and size finally and return
    yellowCount--;
    size--;
    return removedYellowBox;
  }

  /**
   * Returns the size of this list
   * 
   * @return size of this list
   */
  public int size() {
    return size;
  }

  /**
   * Returns a String representation of the contents of this list
   * 
   * @return String representation of the content of this list. If this list is empty, an empty
   *         String ("") will be returned.
   */
  @Override
  public String toString() {
    LinkedBox curr = head;
    String listString = "";
    if (size == 0) {
      return "";
    } else {
      // traverse list, print out boxes.
      for (int i = 0; i < size; i++) {
        listString += curr.getBox().toString() + " -> ";
        curr = curr.getNext();
      }
      listString += "END";
      return listString;
    }
  }
}
