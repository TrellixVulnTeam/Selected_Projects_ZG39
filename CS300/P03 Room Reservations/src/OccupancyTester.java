//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 Room Reservation_ROOM
// Course: CS 300 Spring 2020
//
// Author: Kaiwen Shi
// Email: kshi42@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: DOESN'T RECEIVE HELP FROM CLASSMATES
// Online Sources : (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

public class OccupancyTester {
  private static Person ps1 = new Person("Franklin");
  private static Person ps2 = new Person("Mike");
  private static Person ps3 = new Person("Mike");
  /**
   * The method is used for testing fuctionality of Person.java
   * @return true is functioning correctly, false if not.
   */
  public static boolean testPerson() {
    Person ps1 = new Person("Franklin");
    Person ps2 = new Person("Mike");
    Person ps3 = new Person("Mike");
    String ps4 = "Mike";

    //test person getName()
    if (!ps1.getName().equals("Franklin")) {
      System.out.println("ERROR! getName() method problem.");
    }

    //test whether giving same feedback for different people
    if (ps1.equals(ps2)) {
      System.out.println("ERROR! DIFFERENT PEOPLE NOT RETURNING CORRECT VALUE");
    }

    //test initial waiting status
    if (ps1.isWaiting() == false) {
      System.out.println("ERROR! WRONG INITIAL WAITING STATUS.");
    }

    //case 1: person with different name
    if (ps1.getName().equals(ps2.getName())) {
      System.out.println("ERROR! Different Name giving same feedback.");
      return false;
    }

    //case 2: person with same name
    if (!ps2.getName().equals(ps3.getName())) {
      System.out.println("ERROR! Same Name giving different feedback.");
      return false;
    }

    //case 3: person compare with string
    if (ps2.equals(ps4)) {
      System.out.println("ERROR! person compare with a string, but returning true.");
      return false;
    }

    /*toggleWaiting() test: since default waiting status is true, should be false 
      after togglewaiting
    */
    ps1.toggleWaiting();
    if (ps1.isWaiting() != false) {
      System.out.println("ERROR! BUG report on togglewaiting method.");
      return false;
    }
    return true;
  }
  
  /**
   * Below is the method testing Room constructor functionality.
   * @return only true when pass all tests.
   */
  public static boolean testRoomConstructor() {
    boolean rmcscrctor = false;
    int allTestPass = 0;
    
    //test negative capacity
    try{
      Room rm1 = new Room("Ford", -1);
      return rmcscrctor;
    } catch (IllegalArgumentException e) {   
      System.out.println("CONGRAT! CAPACITY TEST PASSED.");
      allTestPass++;
    } 
      
    //test creating rooms with same name
    try {
      Room rm1 = new Room("Ford", 2);
      Room rm2 = new Room("Ford", 3);
      return rmcscrctor;
    } catch (IllegalArgumentException e) {
      System.out.println("CONGRAT! SAME_NAME_ROOM TEST PASSED.");
      allTestPass++;
    }

    //test 2 room give different value
    Room rm1 = new Room("Benz", 2);
    Room rm2 = new Room("SKODA", 3);
    if (rm1.equals(rm2)) {
      System.out.println("ERROR! Different room return same value.");
      return false;
    }

    if(allTestPass == 2){
      rmcscrctor = true;
    } else{
      rmcscrctor = false;
    }
    return rmcscrctor;
  }
  
  /**
   * Below import an example to check Room Accessor functionality
   * @return only true when pass all test, otherwise return false.
   */
  public static boolean testRoomAccessors() {
    Room.clearRooms();
    boolean functioning = true;
    Room rm1 = new Room("Toyota", 5);
    rm1.checkIn(ps1);
    rm1.checkIn(ps2);

    //test getName() method
    if (!rm1.getName().equals("Toyota")) {
      System.out.println("ERROR! BUG REPORT ON getName().");
      functioning = false;
    }

    //test getCapacity() method
    if (rm1.getCapacity() != 5) {
      System.out.println("ERROR! BUG REPORT ON getCapacity().");
      functioning = false;
    }

    //test getCovidCapacity() method
    if (rm1.getCOVIDCapacity() != 3) {
      System.out.println("ERROR! BUG REPORT ON getCOVIDCapacity().");
      functioning = false;
    }

    //test getOccupancy() method
    if (rm1.getOccupancy() != 2) {
      System.out.println("ERROR! BUG REPORT ON getOccupancy().");
      System.out.println(rm1.getOccupancy());
      functioning = false;
    }

    return functioning;
  }

  /**
   * Below is the test method for check in, check every function of checkin method
   * working properly: include current occupancy, copy_to_array and updated togglewaiting
   * condition test.
   * @return only TRUE when all test passed.
   */
  public static boolean testRoomCheckIn() {
    Room rm1 = new Room("GTA6", 2);
    Person[] occupants = new Person[rm1.getCapacity()];

    rm1.checkIn(ps1);
    boolean roomCheckedIn = true;

    // Below are testing checkin if add first person successfully
    int expectCurrOccupant = 1;
    Person[] expectedOccupant = new Person[] { ps1, null };

    if (rm1.getOccupancy() != expectCurrOccupant) {
      System.out.println("ERROR! testRoomCheckin report wrong occupant Num." + "Expected: " + expectCurrOccupant
          + ", actual: " + rm1.getOccupancy());
      roomCheckedIn = false;
    }

    //test copy_to_array methods functionality
    if (occupants[0] != null && expectedOccupant[0] != null) {
      if (!expectedOccupant[0].equals(occupants[0].getName())) {
        System.out.println("ERROR! BUG in testRoomCheckin array.");
        roomCheckedIn = false;
      }
    }

    //test togglewaiting
    if (ps1.isWaiting() != true) {
      System.out.println("ERROR! person waiting status not Updated.");
    }
    return roomCheckedIn;
  }

  /**
   * Below is testing checkout method, check every function of Checkout working
   * properly: include current occupancy, copy_to_array and updated togglewaiting
   * update conditions
   * @return only TRUE when all test passed.
   */
  public static boolean testRoomCheckOut() {
    Room rm1 = new Room("GTA7", 2);
    Person[] occupants = new Person[rm1.getCapacity()];
    rm1.checkOut(ps1);

    boolean roomCheckedOut = true;
    
    int expectCurrOccupant = 0;
    Person[] expectedOccupant = new Person[] { null, null };

    if (rm1.getOccupancy() != expectCurrOccupant) {
      System.out.println("ERROR! testRoomCheckin report wrong occupant Num." + "Expected: " + expectCurrOccupant
          + ", actual: " + rm1.getOccupancy());
      roomCheckedOut = false;
    }

    //test copy_to_array methods functionality
    if (occupants[0] != null && expectedOccupant[0] != null) {
      System.out.println("ERROR! CheckOut Unsuccessfully.");
      roomCheckedOut = false;
    }

    //test toggleWaiting
    if (ps1.isWaiting() != true) {
      System.out.println("ERROR! Person waiting status NOT updated.");
      roomCheckedOut = false;
    }
    return roomCheckedOut;
  }

  /**
   * Below is testing whether toString() is functioning correctly.
   * whether it is return back desired print out String of room name + guest name
   * list.
   * @return true if 2 string ideally satisfied, otherwise return false;
   */
  public static boolean testToString() {
    boolean toString = false;

    Room room1 = new Room("GTA5", 5);
    Person person1 = new Person("Mike");
    Person person2 = new Person("Trevor");
    Person person3 = new Person("Franklin");
    room1.checkIn(person1);
    room1.checkIn(person2);
    room1.checkIn(person3);

    String separatedList = "GTA5\n===\nMike\n-\nTrevor\n-\nFranklin\n";
    if(!separatedList.equals(room1.toString())){
      System.out.println("ERROR! Problem with ROOM_toString Method.");
      toString = false;
    } else{
      toString = true;
    }

    return toString;
  }
  
  /**
   * display test method result.
   */
  public static void main(String[] args) {
    System.out.println("testPerson:" + testPerson());
    System.out.println("testRoomConstructor:" + testRoomConstructor());
    System.out.println("testRoomAccessors:" + testRoomAccessors());
    System.out.println("testRoomCheckIn:" + testRoomCheckIn());
    System.out.println("testRoomCheckOut:" + testRoomCheckOut());
    System.out.println("testToString:" + testToString());
  }
}
