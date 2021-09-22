//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 LinkedBox
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
 * LinkedBoxrepresents a linked box node. Every instance of LinkedBox must define the 2 following
 * private instance fields, only
 */
public class LinkedBox {
  // initialize instance fields
  private Box box = null;
  private LinkedBox next = null;

  /**
   * Creates a new LinkedBox with a specified box and null as next field
   * 
   * @param box
   */
  public LinkedBox(Box box) {
    this.box = box;
  }

  /**
   * Creates a new LinkedBox node with given box and next fields
   * 
   * @param box  the box
   * @param next next node of box
   */
  public LinkedBox(Box box, LinkedBox next) {
    this.box = box;
    this.next = next;
  }

  /**
   * returns the data field box
   * 
   * @return Box data field
   */
  public Box getBox() {
    return this.box;
  }

  /**
   * return the next linked box node
   * 
   * @return next Linked box node
   */
  public LinkedBox getNext() {
    return this.next;
  }

  /**
   * sets link to the next linked box node
   * 
   * @param next next linked box node
   */
  public void setNext(LinkedBox next) {
    this.next = next;
  }

  /**
   * returns a string representation of the linked Box
   */
  @Override
  public String toString() {
    return (this.getNext() != null) ? box.toString() + " -> " : box.toString() + " -> END";
  }
}
