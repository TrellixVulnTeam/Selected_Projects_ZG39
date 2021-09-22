//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 Room Reservation_Room
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
import java.util.ArrayList;

public class Room {
  //def private class variable BELOW
  //Room Name ArrayList
  private static ArrayList<String> names = new ArrayList<String>();
  
  //def private instance variable BELOW
  //Unique Room Name
  private String name;

  //Array contain persons currently in Room (with "social distance enforced")
  private Person[] occupants;

  //Person in room Count
  private int currentOccupancy;
  
  /**
   * The method is use for copying room name list from arraylist "names"
   * @return an Array of current list of Room Names
   */
  public static String[] getNames() {
    //initialize equal-length array for filling current Room name list
    String[] roomNameLists = new String[names.size()];

    //copy room Name list IN ORDER and return
    for (int i = 0; i < names.size(); i++) {
      roomNameLists[i] = names.get(i);
    }
    return roomNameLists;
  }
  
  /**
   * Construct a Room object.
   * @param name name of the Room
   * @param capacity the max num of people allow inside in NORMAL CONDITION
   */
  public Room(String name, int capacity) {
    //ensure room capacity is valid
    if (capacity <= 0) {
      throw new IllegalArgumentException("ERROR! Invalid capacity.");
    }

    //ensure room names unique & valid
    for (int i = 0; i < names.size(); i++) {
      if (name == null) {
        throw new IllegalArgumentException("ERROR! Room name invalid.");
      } else if (name.equals(names.get(i))) {
        throw new IllegalArgumentException("ERROR! Room name already used.");
      }
    }

    //initialize intance variables
    occupants = new Person[capacity];

    //initialize room name and add to "name" arraylist
    this.name = name;
    names.add(name);

  }
  
  /**
   * get name of the room
   * @return name of this room
   */
  public String getName() {
    return name;
  }
  
  /**
   * get current occupancy
   * @return current number of people in the room
   */
  public int getOccupancy() {
    return currentOccupancy;
  }

  /**
   * get the covid capacity of the room
   * @return number of people allowed in theRoom under COVID protocols
   */
  public int getCOVIDCapacity() {
    int covidCapacity = (occupants.length + 1) / 2;
    return covidCapacity;
  }
  
  /**
   * get normal capacity of room
   * @return number of people allowed in the Room under normal conditions
   */
  public int getCapacity() {
    return occupants.length;
  }
  
  /**
   * check whether person p is checked in
   * @param p person object checking in
   * @return true only when the providedPerson is present in the Room’s occupants array
   */
  public boolean contains(Person p) {
    boolean containPerson = false;
    if (p == null) {
      return false;
    }
    
    //check person's status in room
    for (int i = 0; i < occupants.length; i++) {
      if (occupants[i] == null) {
        continue;
      } 
      if(occupants[i].getName().equals(p.getName())) {
        containPerson = true;
        break;
      }
    }
    return containPerson;
  }
  
  /**
   * The method works for updating currentOccupancy list(array) when 
   * guests checking in
   * 
   * @param in a person object currently checking in 
   * @return true only when provided person successfully added to room
   * @throws illegalArgumentException when Input is null or guests already
   * in the room
   */
  public boolean checkIn(Person in) {
    boolean checkedIn = false;
    
    //check requirenment listed on assignment requirenment PDF
    if (currentOccupancy == getCOVIDCapacity()) {
      return false;
    }

    if (in == null || this.contains(in)) {
      throw new IllegalArgumentException("Input ERROR! Invalid input.");
    }

    //check through occupants array, add person increment currentOccupancy
    for (int i = 0; i < occupants.length; i += 2) {
      if (occupants[i] == null) {
        occupants[i] = in;
        occupants[i].toggleWaiting();
        currentOccupancy++;
 
        checkedIn = true;
        break;
      }
    }
    
    return checkedIn;
  }
  
  /**
   * The method is working for updating occupants array when guests check
   * out 
   * @param out a person object checking out
   * @return true only when provided person removed from room successfully
   * @throws IllegalArgumenException when input is null;
   */
  public boolean checkOut(Person out) {
    boolean checkedOut = false;
    if (out == null) {
      throw new IllegalArgumentException("ERROR! Invalid Person.");
    }
    
    //checkout process: compare personName with the one in Array
    if (this.contains(out)) {
      for (int i = 0; i < occupants.length; i++) {
        if (occupants[i] == null) {
          continue;
        }

        if (occupants[i].getName().equals(out.getName())) {
          occupants[i] = null;
        }
      }
      currentOccupancy--;
      out.toggleWaiting();
      checkedOut = true;
    } else {
      checkedOut = false;
    }

    return checkedOut;
  }
  
  /**
   * return a string of room and recipients
   * @return a string containing room and person name
   */
  public String toString() {
    String output = "";
    output = name + "\n===\n";

    for (int i = 0; i < occupants.length; i++) {
      if (occupants[i] == null) {
        output += "-\n";
      } else {
        output += (occupants[i].getName() + "\n");
      }
    }
    return output;
  }
  
  /**
   * optional methods help clear existing rooms
   */
  public static void clearRooms() {
    names.clear();
  }
}
