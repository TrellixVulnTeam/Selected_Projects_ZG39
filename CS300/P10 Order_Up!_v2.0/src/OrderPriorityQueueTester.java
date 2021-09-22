//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Order Up! v2.0 Order Piority Queue Tester
// Course: CS 300 Fall 2020
//
// Author: Kaiwen Shi
// Email: kshi42@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NO HELP FROM OTHER PERSON
// Online Sources: NO HELP FROM ONLINE SOURCE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * OrderPriorityQueue.
 * 
 * You MAY add additional public static boolean methods to this class if you like, and any private
 * static helper methods you need.
 */
public class OrderPriorityQueueTester {

  /**
   * Checks the correctness of the isEmpty method of OrderPriorityQueue.
   * 
   * You should, at least: (1) create a new OrderPriorityQueue and verify that it is empty (2) add a
   * new Order to the queue and verify that it is NOT empty (3) remove that Order from the queue and
   * verify that it is empty again
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testIsEmpty() {
    try {
      OrderPriorityQueue testQueue = new OrderPriorityQueue(3);
      Order.resetIDGenerator();
      Boolean case1 = false;
      Boolean case2 = false;
      Boolean case3 = false;

      // Case 1
      if (testQueue.isEmpty()) {
        case1 = true;
      }

      // Case 2
      Order o1 = new Order("oprder", 20);
      testQueue.insert(o1);
      if (!testQueue.isEmpty()) {
        case2 = true;
      }

      // Case 3
      testQueue.removeBest();
      if (testQueue.isEmpty()) {
        case3 = true;
      }

      return case1 && case2 && case3;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks the correctness of the insert method of OrderPriorityQueue.
   * 
   * You should, at least: (1) create a new OrderPriorityQueue and add a single order with a large
   * prepTime to it (2) use the OrderPriorityQueue toString method to verify that the queue's
   * internal structure is a valid heap (3) add at least three more orders with DECREASING prepTimes
   * to the queue and repeat step 2.
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testInsertBasic() {
    try {
      OrderPriorityQueue testQueue = new OrderPriorityQueue(3);
      Order.resetIDGenerator();
      Boolean case1 = false;
      Boolean case2 = false;
      Boolean case3 = false;

      // Case 1
      Order o1 = new Order("a", 2000);
      testQueue.insert(o1);
      if (testQueue.peekBest() == o1) {
        case1 = true;
      }

      // Case 2
      if (testQueue.toString().equals("1001(2000)")) {
        case2 = true;
      }

      // Case 3
      Order o2 = new Order("a", 15);
      Order o3 = new Order("a", 10);
      Order o4 = new Order("a", 5);
      testQueue.insert(o2);
      testQueue.insert(o3);
      testQueue.insert(o4);

      if (testQueue.toString().equals("1001(2000), 1002(15), 1003(10), 1004(5)")) {
        case3 = true;
      }

      return case1 && case2 && case3;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks the correctness of the insert method of OrderPriorityQueue.
   * 
   * You should, at least: (1) create an array of at least four Orders that represents a valid heap
   * (2) add a fifth order at the next available index that is NOT in a valid heap position (3) pass
   * this array to OrderPriorityQueue.percolateUp() (4) verify that the resulting array is a valid
   * heap
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testPercolateUp() {
    try {
      // Step 1
      Order.resetIDGenerator();
      Order[] testHeap = new Order[5];

      Order o1 = new Order("a", 2000);
      Order o2 = new Order("wed", 15);
      Order o3 = new Order("qwdcs", 10);
      Order o4 = new Order("cdvgr", 5);
      testHeap[0] = o1;
      testHeap[1] = o2;
      testHeap[2] = o3;
      testHeap[3] = o4;

      // Step 2 & 3
      Order o5 = new Order("wdfvr", 16);
      testHeap[4] = o5;
      OrderPriorityQueue.percolateUp(testHeap, 4);

      return testHeap[1] == o5 && testHeap[2] == o3 && testHeap[3] == o4 && testHeap[4] == o2;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks the correctness of the insert method of OrderPriorityQueue.
   * 
   * You should, at least: (1) create a new OrderPriorityQueue with at least 6 orders of varying
   * prepTimes, adding them to the queue OUT of order (2) use the OrderPriorityQueue toString method
   * to verify that the queue's internal structure is a valid heap
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testInsertAdvanced() {
    try {
      Order.resetIDGenerator();
      // Step 1
      OrderPriorityQueue testQueue = new OrderPriorityQueue(10);

      Order o1 = new Order("af", 2000);
      Order o2 = new Order("aw", 13);
      Order o3 = new Order("aff", 15);
      Order o4 = new Order("avv", 21);
      Order o5 = new Order("awww", 16);
      Order o6 = new Order("dyfughj", 12);
      testQueue.insert(o1);
      testQueue.insert(o2);
      testQueue.insert(o3);
      testQueue.insert(o4);
      testQueue.insert(o5);
      testQueue.insert(o6);

      // Step 2
      if (testQueue.toString()
          .equals("1001(2000), 1004(21), 1003(15), 1002(13), 1005(16), 1006(12)")) {
        return true;
      } else {
        return false;
      }

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks the correctness of the insert method of OrderPriorityQueue.
   * 
   * You should, at least: (1) create an array of at least five Orders where the Order at index 0 is
   * NOT in valid heap position (2) pass this array to OrderPriorityQueue.percolateDown() (3) verify
   * that the resulting array is a valid heap
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testPercolateDown() {
    try {
      Order.resetIDGenerator();

      // Step 1
      Order o1 = new Order("aw", 1);
      Order o2 = new Order("aff", 20);
      Order o3 = new Order("awf", 9);
      Order o4 = new Order("awww", 8);
      Order o5 = new Order("bdwbc", 7);

      Order[] testHeap = new Order[5];
      testHeap[0] = o1;
      testHeap[1] = o2;
      testHeap[2] = o3;
      testHeap[3] = o4;
      testHeap[4] = o5;

      // Step 2
      OrderPriorityQueue.percolateDown(testHeap, 0, 5);

      // Step 3: judge
      return testHeap[0] == o2 && testHeap[1] == o4 && testHeap[2] == o3 && testHeap[3] == o1
          && testHeap[4] == o5;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks the correctness of the removeBest and peekBest methods of OrderPriorityQueue.
   * 
   * You should, at least: (1) create a new OrderPriorityQueue with at least 6 orders of varying
   * prepTimes, adding them to the queue in whatever order you like (2) remove all but one of the
   * orders, verifying that each order has a SHORTER prepTime than the previously-removed order (3)
   * peek to see that the only order left is the one with the SHORTEST prepTime (4) check isEmpty to
   * verify that the queue has NOT been emptied (5) remove the last order and check isEmpty to
   * verify that the queue HAS been emptied
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testPeekRemove() {
    try {
      Order.resetIDGenerator();
      Boolean case1 = false;
      Boolean case2 = false;
      Boolean case3 = false;

      // Step 1
      OrderPriorityQueue testQueue = new OrderPriorityQueue(6);
      Order o1 = new Order("aw", 200);
      Order o2 = new Order("aff", 100);
      Order o3 = new Order("awf", 10);
      Order o4 = new Order("awww", 9);
      Order o5 = new Order("bdebc", 8);
      Order o6 = new Order("bdebc", 7);
      testQueue.insert(o1);
      testQueue.insert(o2);
      testQueue.insert(o3);
      testQueue.insert(o4);
      testQueue.insert(o5);
      testQueue.insert(o6);

      // Step 2
      testQueue.removeBest();
      testQueue.removeBest();
      testQueue.removeBest();
      testQueue.removeBest();
      testQueue.removeBest();

      // Step 3
      if (testQueue.peekBest().equals(o6)) {
        case1 = true;
      }

      // Step 4
      if (!testQueue.isEmpty()) {
        case2 = true;
      }

      // Step 5
      testQueue.removeBest();
      if (testQueue.isEmpty()) {
        case3 = true;
      }

      return case1 && case2 && case3;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks the correctness of the removeBest and peekBest methods, as well as the constructor of
   * the OrderPriorityQueue class for erroneous inputs and/or states
   * 
   * You should, at least: (1) create a new OrderPriorityQueue with an invalid capacity argument,
   * and verify that the correct exception is thrown (2) call peekBest() on an OrderPriorityQueue
   * with an invalid state for peeking, and verify that the correct exception is thrown (3) call
   * removeBest() on an OrderPriorityQueue with an invalid state for removing, and verify that the
   * correct exception is thrown
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testErrors() {
    try {
      Boolean case1 = false;
      Boolean case2 = false;
      Boolean case3 = false;
      Order.resetIDGenerator();

      // Step 1; also case1: invalid capacity
      try {
        OrderPriorityQueue tq1 = new OrderPriorityQueue(-1);
      } catch (IllegalArgumentException e) {
        case1 = true;
      }

      // Step 2; also case2: invalid peek
      try {
        OrderPriorityQueue tq2 = new OrderPriorityQueue(1);
        tq2.peekBest();
      } catch (NoSuchElementException e) {
        case2 = true;
      }

      // Step 3: also case3: invalid removal
      try {
        OrderPriorityQueue tq3 = new OrderPriorityQueue(1);
        tq3.removeBest();
      } catch (NoSuchElementException e) {
        case3 = true;
      }

      // only return true when pass all 3 cases
      return case1 && case2 && case3;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Calls the test methods individually and displays their output
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("isEmpty: " + testIsEmpty());
    System.out.println("insert basic: " + testInsertBasic());
    System.out.println("percolate UP: " + testPercolateUp());
    System.out.println("insert advanced: " + testInsertAdvanced());
    System.out.println("percolate DOWN: " + testPercolateDown());
    System.out.println("peek/remove valid: " + testPeekRemove());
    System.out.println("error: " + testErrors());
  }

}
