//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Order Up! v2.0 Order Priority Queue
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

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A max-heap implementation of a priority queue for Orders, where the Order with the LONGEST prep
 * time is returned first instead of the strict first-in-first-out queue as in P08.
 * 
 * @author Shi Kaiwen
 */
public class OrderPriorityQueue implements PriorityQueueADT<Order> {

  // Data fields; do not modify
  private Order[] queueHeap;
  private int size;

  /**
   * Constructs a PriorityQueue for Orders with the given capacity
   * 
   * @param capacity the initial capacity for the queue
   * @throws IllegalArgumentException if the given capacity is 0 or negative
   */
  public OrderPriorityQueue(int capacity) {
    // throw IllegalArgumentException if capacity is invalid
    if (capacity <= 0) {
      throw new IllegalArgumentException("Wrong initial Capacity!");
    }

    // initialize data fields appropriately
    queueHeap = new Order[capacity];
    size = 0;
  }

  /**
   * Inserts a new Order into the queue in the appropriate position using a heap's add logic.
   * 
   * @param newOrder the Order to be added to the queue
   */
  @Override
  public void insert(Order newOrder) {
    // If the queue is empty, insert the new order at the root of the heap
    if (isEmpty()) {
      queueHeap[0] = newOrder;
      size++;
    } else if (queueHeap[size - 1] != null) {
      // If the queue is FULL, create a new Order array of double the current heap's size,
      // copy all elements of the current heap over and update the queueHeap reference
      Order[] copy = Arrays.copyOf(queueHeap, size * 2);
      copy[size] = newOrder;
      percolateUp(copy, size);

      queueHeap = Arrays.copyOf(copy, copy.length);
      size++;

    } else {
      // normal case when queue neither null or full
      // add neworder to the end of queue, than percolate up to make it satisfy max-heap
      // nature
      queueHeap[size] = newOrder;
      percolateUp(queueHeap, size);
      size++;
    }
  }

  /**
   * A utility method to percolate Order values UP through the heap; see figure 13.3.1 in zyBooks
   * for a pseudocode algorithm.
   * 
   * @param heap       an array containing the Order values to be percolated into a valid heap
   * @param orderIndex the index of the Order to be percolated up
   */
  protected static void percolateUp(Order[] heap, int orderIndex) {
    while (orderIndex > 0) {
      int parentIndex = (orderIndex - 1) / 2;
      if (heap[orderIndex].getPrepTime() <= heap[parentIndex].getPrepTime()) {
        return;
      } else {
        // swap nodeIndex with parent Index
        Order temp = heap[parentIndex];
        heap[parentIndex] = heap[orderIndex];
        heap[orderIndex] = temp;

        orderIndex = parentIndex;
      }
    }
  }

  /**
   * Return the Order with the longest prep time from the queue and adjust the queue accordingly
   * 
   * @return the Order with the current longest prep time from the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public Order removeBest() throws NoSuchElementException {
    // If the queue is empty, throw a NoSuchElementException
    if (isEmpty()) {
      throw new NoSuchElementException("Empty Queue!");
    }

    // Remove the root Order of the heap and re-structure the heap to ensure that its ordering
    // is valid, then return the previous root
    Order removedBest = queueHeap[0];
    if (size == 1) {
      queueHeap[0] = null;
      size = 0;
      return removedBest;
    }

    queueHeap[0] = queueHeap[size - 1];
    queueHeap[size - 1] = null;

    size--;
    percolateDown(queueHeap, 0, size);

    return removedBest;
  }

  /**
   * A utility method to percolate Order values DOWN through the heap; see figure 13.3.2 in zyBooks
   * for a pseudocode algorithm.
   * 
   * @param heap       an array containing the Order values to be percolated into a valid heap
   * @param orderIndex the index of the Order to be percolated down
   * @param size       the number of initialized elements in the heap
   */
  protected static void percolateDown(Order[] heap, int orderIndex, int size) {
    // heap type: max-heap
    int childIndex = 2 * orderIndex + 1;
    int value = heap[orderIndex].getPrepTime();

    while (childIndex < size) {
      // search max node
      int maxVal = value;
      int maxIndex = -1;
      for (int i = 0; i < 2 && i + childIndex < size; i++) {
        if (heap[i + childIndex].getPrepTime() > maxVal) {
          maxVal = heap[i + childIndex].getPrepTime();
          maxIndex = i + childIndex;
        }
      }

      if (maxVal == value) {
        return;
      } else {
        Order temp = heap[maxIndex];
        heap[maxIndex] = heap[orderIndex];
        heap[orderIndex] = temp;
        orderIndex = maxIndex;
        childIndex = 2 * orderIndex + 1;
      }
    }
  }

  /**
   * Return the Order with the highest prep time from the queue without altering the queue
   * 
   * @return the Order with the current longest prep time from the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public Order peekBest() {
    // If the queue is empty, throw a NoSuchElementException
    if (isEmpty()) {
      throw new NoSuchElementException("Empty Order!");
    }
    // Return the Order with the longest prep time currently in the queue
    return queueHeap[0];
  }

  /**
   * Returns true if the queue contains no Orders, false otherwise
   * 
   * @return true if the queue contains no Orders, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the number of elements currently in the queue
   * 
   * @return the number of elements currently in the queue
   */
  public int size() {
    return size;
  }

  /**
   * Creates a String representation of this PriorityQueue. Do not modify this implementation; this
   * is the version that will be used by all provided OrderPriorityQueue implementations that your
   * tester code will be run against.
   * 
   * @return the String representation of this PriorityQueue, primarily for testing purposes
   */
  public String toString() {
    String toReturn = "";
    for (int i = 0; i < this.size; i++) {
      toReturn += queueHeap[i].getID() + "(" + queueHeap[i].getPrepTime() + ")";
      if (i < this.size - 1)
        toReturn += ", ";
    }
    return toReturn;
  }

}
