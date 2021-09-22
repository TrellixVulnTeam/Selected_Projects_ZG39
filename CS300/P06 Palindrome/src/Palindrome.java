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
public class Palindrome {
  /**
   * Recursively create a simple alphabet pattern, starting at the provided character, moving
   * backward to the beginning of the alphabet, and then forward again to the provided letter,
   * separating each letter with a space. only valid for capital letter input; everything else
   * require throw exception
   *
   * @param start start Alphabet
   * @return return formatted string
   * @throws IllegalArgumentException
   */
  public static String mirrorA(char start) throws IllegalArgumentException {
    if (!Character.isUpperCase(start) || !Character.isAlphabetic(start)) {
      throw new IllegalArgumentException("ERROR! Invalid input; input must be ALPHABETIC");
    }

    int alphaetASCII = start;
    String returnStr = "";
    if (start == 'A') {
      returnStr = "A";
    } else {
      returnStr = start + " " + mirrorA((char) (alphaetASCII - 1)) + " " + start;
    }

    return returnStr;
  }

  /**
   * Recursively create an alphabet pattern, starting at the provided character, and movingback and
   * forth to the beginning of the alphabet by steps of size method is only valid for capital letter
   * input and strictly positive(not zero or negative) step sizes. For invalid input, throw an
   * IllegalArgumentException with a descriptive error message
   *
   * @param start start alphabet
   * @param step  step length that the desired string want
   * @return formatted output
   * @throws IllegalArgumentException
   */
  public static String mirrorA(char start, int step) throws IllegalArgumentException {
    if (!Character.isUpperCase(start) || !Character.isAlphabetic(start)) {
      throw new IllegalArgumentException("ERROR! Invalid input; input must be ALPHABETIC");
    }

    int alphabetASCII = start;
    String returnStr = "";
    if (start == 'A') {
      returnStr = "A";
    } else if (start - step < 'A') {
      returnStr = start + " " + start;
    } else {
      returnStr = start + " " + mirrorA((char) (alphabetASCII - step), step) + " " + start;
    }
    return returnStr;
  }

  /**
   * Recursively create a simple alphabet pattern, starting the provided character, and moving
   * forward to the end of the alphabet, and then backward again to the provided letter, separating
   * each letter with a space. only valid for capital letter input; if anything other than a capital
   * letter is provided as an argument, throw an IllegalArgumentException with a descriptive error
   * message.
   *
   * @param start start character
   * @return formatted string
   * @throws IllegalArgumentException
   */
  public static String mirrorZ(char start) throws IllegalArgumentException {
    if (!Character.isUpperCase(start) || !Character.isAlphabetic(start)) {
      throw new IllegalArgumentException("ERROR! Invalid input; input must be ALPHABETIC");
    }

    int alphaetASCII = start;
    String returnStr = "";
    if (start == 'Z') {
      returnStr = "Z";
    } else {
      returnStr = start + " " + mirrorZ((char) (alphaetASCII + 1)) + " " + start;
    }

    return returnStr;
  }

  /**
   * Recursively create an alphabet pattern, starting at the provided character, and moving forward
   * and back to the end of the alphabet by steps of size.
   *
   * @param start start character
   * @param step  step length formatted string desired
   * @return formatted string
   * @throws IllegalArgumentException
   */
  public static String mirrorZ(char start, int step) throws IllegalArgumentException {
    if (!Character.isUpperCase(start) || !Character.isAlphabetic(start)) {
      throw new IllegalArgumentException("ERROR! Invalid input; input must be ALPHABETIC");
    }

    int alphabetASCII = start;
    String returnStr = "";
    if (start == 'Z') {
      returnStr = "Z";
    } else if (start + step > 'Z') {
      returnStr = start + " " + start;
    } else {
      returnStr = start + " " + mirrorZ((char) (alphabetASCII + step), step) + " " + start;
    }

    return returnStr;
  }
}
