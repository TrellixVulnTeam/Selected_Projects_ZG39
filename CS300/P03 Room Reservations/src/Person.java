//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 Room Reservation_Person
// Course: CS 300 Spring 2020
//
// Author: Kaiwen Shi
// Email: kshi42@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: DOESN'T RECEIVE HELP FROM CLASSMATES
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

public class Person {
  //def private instance variables
  private String name;
  private boolean isWaiting;
  
  /**
   * initialize person object
   */
  public Person(String name) {
    this.name = name;
    isWaiting = true;
  }

  /**
   * Get the name of instance variables
   * @return name of Variables
   */
  public String getName() {
    return name;
  }

  /**
   * get waiting status
   * @return waiting status
   */
  public boolean isWaiting() {
    return isWaiting;
  }

  /**
   * reverse waiting status as required in textbook
   */
  public void toggleWaiting() {
    isWaiting = !isWaiting;
  }

  /**
   * check if it is an instance variable of person
   */
  public boolean equals(Object o) {
    if (o instanceof Person) {
      return this.name.equals(((Person) o).name);
      }
      return false;
    }
}
