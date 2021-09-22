//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Movie Catalog MovieTree Tester
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
 * MovieTree.
 *
 */
public class MovieTreeTester {

  /**
   * Checks the correctness of the implementation of both addMovie() and toString() methods
   * implemented in the MovieTree class. This unit test considers at least the following scenarios.
   * (1) Create a new empty MovieTree, and check that its size is 0, it is empty, and that its
   * string representation is an empty string "". (2) try adding one movie and then check that the
   * addMovie() method call returns true, the tree is not empty, its size is 1, and the .toString()
   * called on the tree returns the expected output. (3) Try adding another movie which is smaller
   * that the movie at the root, (4) Try adding a third movie which is greater than the one at the
   * root, (5) Try adding at least two further movies such that one must be added at the left
   * subtree, and the other at the right subtree. For all the above scenarios, and more, double
   * check each time that size() method returns the expected value, the add method call returns
   * true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * movie with respect to year, rating, and then name. (6) Try adding a movie already stored in the
   * tree. Make sure that the addMovie() method call returned false, and that the size of the tree
   * did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddMovieToStringSize() {
    try {
      Boolean case1Pass, case2Pass, case3Pass, case4Pass, case5Pass, case6Pass = false;
      Boolean movie1Added, movie2Added, movie3Added, movie4Added, movie5Added = false;

      // Case 1: Empty tree
      MovieTree testCase = new MovieTree();
      if (testCase.size() == 0 && testCase.toString() == null && testCase.isEmpty()) {
        case1Pass = true;
      } else {
        case1Pass = false;
      }

      // Case 2: Add 1 movie into tree
      MovieTree testCaseTwo = new MovieTree();
      movie1Added = testCaseTwo.addMovie(new Movie(1990, 7.0, "As Tears Go By"));
      case2Pass = movie1Added && !testCaseTwo.isEmpty() && testCaseTwo.size() == 1 && testCaseTwo
          .toString().equals("[(Year: 1990) (Rate: 7.0) (Name: As Tears Go By)]" + "\n");

      // Case 3: Add another movie smaller than movie at root
      MovieTree testCaseThree = new MovieTree();
      movie1Added = testCaseThree.addMovie(new Movie(1990, 7.0, "As Tears Go By"));
      movie2Added = testCaseThree.addMovie(new Movie(1989, 8.0, "In the Heat of Sun"));
      case3Pass = movie1Added && !testCaseThree.isEmpty() && testCaseThree.size() == 2
          && testCaseThree.toString().equals("[(Year: 1989) (Rate: 8.0) (Name: In the Heat of Sun)]"
              + "\n" + "[(Year: 1990) (Rate: 7.0) (Name: As Tears Go By)]" + "\n");
      // Case 4: Add a movie greater than movie at root
      MovieTree testCaseFour = new MovieTree();
      movie1Added = testCaseFour.addMovie(new Movie(1990, 7.0, "As Tears Go By"));
      movie2Added = testCaseFour.addMovie(new Movie(1989, 8.0, "In the Heat of Sun"));
      movie3Added = testCaseFour.addMovie(new Movie(1990, 9.0, "Gambling God"));
      case4Pass = movie1Added && movie2Added && movie3Added && !testCaseFour.isEmpty()
          && testCaseFour.size() == 3
          && testCaseFour.toString()
              .equals("[(Year: 1989) (Rate: 8.0) (Name: In the Heat of Sun)]" + "\n"
                  + "[(Year: 1990) (Rate: 7.0) (Name: As Tears Go By)]" + "\n"
                  + "[(Year: 1990) (Rate: 9.0) (Name: Gambling God)]" + "\n");

      // Case 5: 2 more movies, one at left subtree, 1 at right subtree
      MovieTree testCaseFive = new MovieTree();
      movie1Added = testCaseFive.addMovie(new Movie(1990, 7.0, "As Tears Go By"));
      movie2Added = testCaseFive.addMovie(new Movie(1989, 8.0, "In the Heat of Sun"));
      movie3Added = testCaseFive.addMovie(new Movie(1990, 9.0, "Gambling God"));
      movie4Added = testCaseFive.addMovie(new Movie(1977, 7.0, "Plan A"));
      movie5Added = testCaseFive.addMovie(new Movie(1994, 8.0, "Chungking Express"));
      case5Pass = movie1Added && movie2Added && movie3Added && movie4Added && movie5Added
          && !testCaseFive.isEmpty() && testCaseFive.size() == 5
          && testCaseFive.toString()
              .equals("[(Year: 1977) (Rate: 7.0) (Name: Plan A)]" + "\n"
                  + "[(Year: 1989) (Rate: 8.0) (Name: In the Heat of Sun)]" + "\n"
                  + "[(Year: 1990) (Rate: 7.0) (Name: As Tears Go By)]" + "\n"
                  + "[(Year: 1990) (Rate: 9.0) (Name: Gambling God)]" + "\n"
                  + "[(Year: 1994) (Rate: 8.0) (Name: Chungking Express)]" + "\n");


      // Case 6: Add a Movie already stored in tree
      // where the first message in printout "ERROR! SAME MOVIE RE-ADDING!" come from
      movie1Added = testCaseFive.addMovie(new Movie(1994, 8.0, "Chungking Express"));
      case6Pass = !movie1Added && !testCaseFive.isEmpty() && testCaseFive.size() == 5
          && testCaseFive.toString()
              .equals("[(Year: 1977) (Rate: 7.0) (Name: Plan A)]" + "\n"
                  + "[(Year: 1989) (Rate: 8.0) (Name: In the Heat of Sun)]" + "\n"
                  + "[(Year: 1990) (Rate: 7.0) (Name: As Tears Go By)]" + "\n"
                  + "[(Year: 1990) (Rate: 9.0) (Name: Gambling God)]" + "\n"
                  + "[(Year: 1994) (Rate: 8.0) (Name: Chungking Express)]" + "\n");

      // Only return true when all 6 tests PASSED.
      if (case1Pass && case2Pass && case3Pass && case4Pass && case5Pass && case6Pass) {
        System.out.println("testAddMovieToStringSize() PASSED!");
        return true;
      } else {
        System.out.println("FAILED! in one of the cases!");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * This method checks mainly for the correctness of the MovieTree.contains() method. It must
   * consider at least the following test scenarios. (1) Create a new MovieTree. Then, check that
   * calling the contains() method on an empty MovieTree returns false. (2) Consider a MovieTree of
   * height 3 which contains at least 5 movies. Then, try to call contains() method to search for
   * the movie having a match at the root of the tree. (3) Then, search for a movie at the right and
   * left subtrees at different levels considering successful and unsuccessful search operations.
   * Make sure that the contains() method returns the expected output for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testContains() {
    try {
      MovieTree testContains = new MovieTree();
      Boolean case1Pass, case2Pass, case3Pass = false;
      Boolean case31Pass, case32Pass, case33Pass, case34Pass = false;

      // Case 1: when movie tree is empty
      case1Pass = testContains.contains(1999, 3.0, "Bad Movie");

      // Case 2: when movie tree have at least 5 movies and height 3
      // searched movie equal to tree movie
      testContains.addMovie(new Movie(1992, 8.0, "Wang Kar Wai"));
      testContains.addMovie(new Movie(1990, 7.0, "As Tears Go By"));
      testContains.addMovie(new Movie(1994, 8.0, "Chungking Express"));
      testContains.addMovie(new Movie(1989, 6.0, "Jacky Chan"));
      testContains.addMovie(new Movie(1991, 7.5, "The Devils is coming"));
      testContains.addMovie(new Movie(1993, 5.2, "Wish you become rich"));
      testContains.addMovie(new Movie(1995, 9.5, "To Live"));

      case2Pass = testContains.contains(1992, 8.0, "Wang Kar Wai");

      // Case 3: search for 1 movie at left subtree and 1 in right subtree
      // at different level. In total will be 4 cases.

      // Case 3.1: both successful contain
      case31Pass = testContains.contains(1990, 7.0, "As Tears Go By")
          && testContains.contains(1995, 9.5, "To Live");

      // Case 3.2 both unsuccessful contain
      case32Pass = testContains.contains(1990, 7.6, "As Tears Go By")
          && testContains.contains(1995, 9.2, "To Live");

      // Case 3.3 left subtree contain, right subtree NOT contain
      case33Pass = testContains.contains(1990, 7.0, "As Tears Go By")
          && testContains.contains(1995, 9.2, "To Live");

      // Case 3.4 left subtree NOT contain, right subtree contain
      case34Pass = testContains.contains(1990, 7.6, "As Tears Go By")
          && testContains.contains(1995, 9.5, "To Live");

      case3Pass = case31Pass && !case32Pass && !case33Pass && !case34Pass;

      if (!case1Pass && case2Pass && case3Pass) {
        System.out.println("testContains() PASSED!");
        return true;
      } else {
        System.out.println("ERROR! 1 test case in testContains() FAILED!");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of MovieTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty movie tree is zero. (2) ensures that
   * the height of a tree which consists of only one node is 1. (3) ensures that the height of a
   * MovieTree with the following structure for instance, is 4. (*) / \ (*) (*) \ / \ (*) (*) (*) /
   * (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    try {
      MovieTree testHeight = new MovieTree();

      // Case 1: Empty tree. Tree height should be zero
      if (testHeight.height() != 0) {
        System.out.println("ERROR! Empty tree have non-ZERO height.");
        return false;
      }

      // Case 2: Only 1 movie in tree. Tree Height must be 1.
      testHeight.addMovie(new Movie(1992, 8.0, "Wang Kar Wai"));
      if (testHeight.height() != 1) {
        System.out.println("ERROR! Tree with only root node have non-ONE height");
        return false;
      }

      // Case 3: Ensured tree height with 4
      testHeight.addMovie(new Movie(1990, 7.0, "As Tears Go By"));
      testHeight.addMovie(new Movie(1994, 8.0, "Chungking Express"));
      testHeight.addMovie(new Movie(1991, 7.5, "The Devils is coming"));
      testHeight.addMovie(new Movie(1993, 5.2, "Wish you become rich"));
      testHeight.addMovie(new Movie(1996, 7.0, "Midnight Alarm"));
      testHeight.addMovie(new Movie(1995, 9.5, "To Live"));

      if (testHeight.height() != 4) {
        System.out.println("ERROR! Tree Height should be 4, but is not the case here");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of MovieTree.getBestMovie() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestMovie() {
    try {
      MovieTree testGetBestMovie = new MovieTree();

      testGetBestMovie.addMovie(new Movie(1992, 8.0, "Wang Kar Wai"));
      testGetBestMovie.addMovie(new Movie(1990, 7.0, "As Tears Go By"));
      testGetBestMovie.addMovie(new Movie(1994, 8.0, "Chungking Express"));
      testGetBestMovie.addMovie(new Movie(1989, 6.0, "Jacky Chan"));
      testGetBestMovie.addMovie(new Movie(1991, 7.5, "The Devils is coming"));
      testGetBestMovie.addMovie(new Movie(1993, 5.2, "Wish you become rich"));
      testGetBestMovie.addMovie(new Movie(1995, 9.5, "To Live"));
      testGetBestMovie.addMovie(new Movie(1977, 7.0, "Plan A"));

      // the best movie according to the definition should be newest or with
      // highest rate in the latest year
      if (testGetBestMovie.getBestMovie().toString()
          .equals("[(Year: 1995) (Rate: 9.5) (Name: To Live)]")) {
        System.out.println("testGetBestMovie() PASSED!");
        return true;
      } else {
        System.out.println("ERROR! getBestMovie() not functioning CORRECTLY!");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of MovieTree.lookup() method. This test must consider at least 3
   * test scenarios. (1) Ensures that the MovieTree.lookup() method throws a NoSuchElementException
   * when called on an empty tree. (2) Ensures that the MovieTree.lookup() method returns an array
   * list which contains all the movies satisfying the search criteria of year and rating, when
   * called on a non empty movie tree with one match, and two matches and more. Vary your search
   * criteria such that the lookup() method must check in left and right subtrees. (3) Ensures that
   * the MovieTree.lookup() method throws a NoSuchElementException when called on a non-empty movie
   * tree with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    try {
      MovieTree testLookup = new MovieTree();
      Boolean case1Pass = false;
      Boolean case2Pass = false;
      Boolean case3Pass = false;

      try {
        // Case 1: trying to search movies in an empty tree
        // NoSuchElementException should be caught in this part
        testLookup.lookup(1999, 6.0);

      } catch (NoSuchElementException e) {
        case1Pass = false;
        e.printStackTrace();
      }

      // Case 2: when trying to search out all movies given year and rating
      testLookup.addMovie(new Movie(1996, 8.0, "Chungking Express"));
      testLookup.addMovie(new Movie(1989, 6.0, "Jacky Chan"));
      testLookup.addMovie(new Movie(1995, 7.5, "Let the Bullets Fly"));
      testLookup.addMovie(new Movie(1995, 5.2, "Hello, You"));
      testLookup.addMovie(new Movie(1995, 9.5, "To Live"));
      case2Pass = testLookup.lookup(1995, 6.0).toString().equals(
          "[[(Year: 1995) (Rate: 7.5) (Name: Let the Bullets Fly)], [(Year: 1995) (Rate: 9.5) (Name: To Live)]]");

      // Case 3: when trying to search for a non-existing movie in tree
      try {
        testLookup.lookup(2020, 3.0);
      } catch (NoSuchElementException e) {
        case3Pass = false;
        e.printStackTrace();
      }

      if (!case1Pass && case2Pass && !case3Pass) {
        System.out.println("testLookUp() PASSED!");
        return true;
      } else {
        System.out.println("ERROR! MovieTree.lookup() implemented INCORRECTLY!");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    testAddMovieToStringSize();
    testContains();
    testHeight();
    testGetBestMovie();
    testLookup();
  }
}
