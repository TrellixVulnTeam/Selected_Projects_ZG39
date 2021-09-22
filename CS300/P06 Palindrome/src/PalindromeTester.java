//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 Palindrome
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
public class PalindromeTester {
  /**
   * test whether mirrorA(char start) is working
   * 
   * @return only true when pass all test
   */
  public static boolean testMirrorA() {
    // test case 1: valid input
    if (!Palindrome.mirrorA('E').equals("E D C B A B C D E")) {
      System.out.println("ERROR! testMirrorA FAIL");
      return false;
    }

    // test case 2: invalid input
    try {
      Palindrome.mirrorA('e');
      Palindrome.mirrorA('@');
    } catch (IllegalArgumentException e) {
    }

    // when all test passed, return true
    System.out.println("GOOD! testMirrorA PASSED!");
    return true;
  }

  /**
   * test whether MirrorA(char Start, int step) is working
   * 
   * @return only true when pass all tests
   */
  public static boolean testMirrorAStep() {
    // case 1: step length = 3
    if (!Palindrome.mirrorA('E', 3).equals("E B B E")) {
      System.out.println("ERROR! output not match");
      return false;
    }

    // case 2: step length = 2
    if (!Palindrome.mirrorA('E', 2).equals("E C A C E")) {
      System.out.println("ERROR! output not Match");
      return false;
    }

    // case 3: step length = 1
    if (!Palindrome.mirrorA('E', 1).equals("E D C B A B C D E")) {
      System.out.println("ERROR! output not match");
      return false;
    }

    // case 4: when there is invalid input: catch the exception
    try {
      Palindrome.mirrorA('e', -1);
      Palindrome.mirrorA('@', 0);
    } catch (IllegalArgumentException e) {
    }

    // when all test passed, return true
    System.out.println("GOOD! testmirrorAStep PASSED!");
    return true;
  }

  /**
   * test whether mirrorZ(char start) is working
   * 
   * @return only true when pass all test
   */
  public static boolean testMirrorZ() {
    // test case 1: valid input
    if (!Palindrome.mirrorZ('V').equals("V W X Y Z Y X W V")) {
      System.out.println("ERROR! testMirrorA FAIL");
      return false;
    }

    // test case 2: invalid input
    try {
      Palindrome.mirrorZ('v');
      Palindrome.mirrorZ('@');
    } catch (IllegalArgumentException e) {
    }

    // when all test passed, return true
    System.out.println("GOOD! testMirrorZ PASSED!");
    return true;
  }

  /**
   * test whether MirrorZ(char Start, int step) is working
   * 
   * @return only true when pass all tests
   */
  public static boolean testMirrorZStep() {
    // case 1: step length = 3
    if (!Palindrome.mirrorZ('V', 3).equals("V Y Y V")) {
      System.out.println("ERROR! output not match");
      return false;
    }

    // case 2: step length = 2
    if (!Palindrome.mirrorZ('V', 2).equals("V X Z X V")) {
      System.out.println("ERROR! output not Match");
      return false;
    }

    // case 3: step length = 1
    if (!Palindrome.mirrorZ('V', 1).equals("V W X Y Z Y X W V")) {
      System.out.println("ERROR! output not match");
      return false;
    }

    // case 4: when there is invalid input: catch the exception
    try {
      Palindrome.mirrorZ('V', -1);
      Palindrome.mirrorZ('@', 0);
    } catch (IllegalArgumentException e) {
    }

    // when all test passed, return true
    System.out.println("GOOD! testmirrorZStep PASSED!");
    return true;
  }

  /**
   * call ALL of test methods
   * 
   * @return true if and only if allmethods return true
   */
  public static boolean runAllTests() {
    if (testMirrorA() && testMirrorAStep() && testMirrorZ() && testMirrorZStep()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * only line in this method should be a call to the runAllTests method.
   * 
   * @param args
   */
  public static void main(String[] args) {
    runAllTests();
  }
}
