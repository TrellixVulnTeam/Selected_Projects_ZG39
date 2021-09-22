//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Movie Catalog MovieTree
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
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This method set up the Movie Tree for any related Use.
 * 
 * @author Shi Kaiwen
 */
public class MovieTree {
  private BSTNode<Movie> root; // root of this movie BST
  private int size; // size of this movie tree

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this MovieTree is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the number of movies stored in this BST.
   * 
   * @return the size of this MovieTree
   */
  public int size() {
    return size;
  }

  /**
   * Adds a new movie to this MovieTree
   * 
   * @param newMovie a new movie to add to this BST.
   * @return true if the newMovie was successfully added to this BST, and returns false if there is
   *         a match with this movie already stored in this BST.
   */
  public boolean addMovie(Movie newMovie) {
    Boolean movieAdded = false;
    if (isEmpty()) { // Add newMovie to an empty MovieTree
      root = new BSTNode<>(newMovie);
      size++;
      movieAdded = true;
    } else { // Add newMovie to an non-empty MovieTree
      // if newMovie CAN added before
      if (addMovieHelper(newMovie, root)) {
        size++;
        movieAdded = true;
      } else {
        // when newMovie CANNOT added before root
        movieAdded = false;
      }
    }
    return movieAdded;
  }

  /**
   * Recursive helper method to add a new movie to a MovieTree rooted at current.
   * 
   * @param current  The "root" of the subtree we are inserting new movie into.
   * @param newMovie The movie to be added to a BST rooted at current.
   * @return true if the newMovie was successfully added to this MovieTree, false otherwise
   */
  protected static boolean addMovieHelper(Movie newMovie, BSTNode<Movie> current) {
    Boolean movieAdded = false;
    // JUDGE CONDITION:
    // Case 1: If node null, simply write data in.
    if (current == null) {
      current = new BSTNode<>(newMovie);
      movieAdded = true;
    } else if (newMovie.compareTo(current.getData()) == 0) {
      // Case 2: if same content node re-adding
      System.out.println("ERROR! " + newMovie.getName() + " RE-ADDING!");
      movieAdded = false;
    } else {
      // Case 3: real "new" movie adding in and current node non-empty
      if (newMovie.compareTo(current.getData()) < 0) {
        /// Case 3.1: when movie is elder // rate worse // smaller alphabetic ascii
        if (current.getLeft() == null) {
          // Case 3.1.1: when leftNode is null: no recursion required
          current.setLeft(new BSTNode<>(newMovie));
          movieAdded = true;
        } else {
          // Case 3.1.2: when left child node is non-null: do recursion
          movieAdded = addMovieHelper(newMovie, current.getLeft());
        }
      } else if (newMovie.compareTo(current.getData()) > 0) {
        // Case 3.2: When movie is younger // rate higher // larger alphabetic ascii
        if (current.getRight() == null) {
          // Case 3.2.1: When current node's right child is null
          current.setRight(new BSTNode<>(newMovie));
          movieAdded = true;
        } else {
          // Case 3.2.2: When current node's right node is non-null; do recursion
          movieAdded = addMovieHelper(newMovie, current.getRight());
        }
      }
    }
    return movieAdded;
  }

  /**
   * Returns a String representation of all the movies stored within this BST in the increasing
   * order, separatingd by a newline "\n". For instance
   * 
   * "[(Year: 1988) (Rate: 7.0) (Name: Light Years)]" + "\n" + "[(Year: 2015) (Rate: 6.5) (Name:
   * Cinderella)]" + "\n"
   * 
   * @return a String representation of all the movies stored within this BST sorted in an
   *         increasing order with respect to the result of Movie.compareTo() method (year, rating,
   *         name). Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a MovieTree is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current movie within this BST (root of a subtree)
   * @return a String representation of all the movies stored in the sub-tree rooted at current in
   *         increasing order with respect to the result of Movie.compareTo() method (year, rating,
   *         name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Movie> current) {
    String toStringHelper = "";
    // base case: when current is Null
    if (current == null) {
      return null;
    }

    // recursively print left subtree
    if (current.getLeft() != null) {
      toStringHelper += toStringHelper(current.getLeft());
    }

    // print root node
    toStringHelper += current.getData().toString() + "\n";

    // recursively print right subtree
    if (current.getRight() != null) {
      toStringHelper += toStringHelper(current.getRight());
    }
    return toStringHelper;
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf
   * 
   * @param current pointer to the current BSTNode within a MovieTree (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Movie> current) {
    if (current == null) {
      return 0;
    }

    int leftHeight = heightHelper(current.getLeft());
    int rightHeight = heightHelper(current.getRight());

    if (leftHeight > rightHeight) {
      return leftHeight + 1;
    } else {
      return rightHeight + 1;
    }
  }

  /**
   * Checks whether this MovieTree contains a movie given its name, production year, and rating.
   * 
   * @param year   year of production of the movie to search
   * @param rating rating of the movie to search
   * @param name   name of the movie to search
   * @return true if there is a match with this movie in this BST, and false otherwise
   */
  public boolean contains(int year, double rating, String name) {
    Movie target = new Movie(year, rating, name);
    if (containsHelper(target, root)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Recursive helper method to search wether there is a match with a given movie in the subtree
   * rooted at current
   * 
   * @param target  a reference to a movie we are searching for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected boolean containsHelper(Movie target, BSTNode<Movie> current) {
    Boolean containsHelper = false;
    if (current == null) {
      return false;
    }

    if (target.compareTo(current.getData()) == 0) {
      containsHelper = true;
    } else {
      if (target.compareTo(current.getData()) > 0) {
        containsHelper = containsHelper(target, current.getRight());
      } else if (target.compareTo(current.getData()) < 0) {
        containsHelper = containsHelper(target, current.getLeft());
      } else {
        containsHelper = false;
      }
    }
    return containsHelper;
  }

  /**
   * Gets the best (maximum) movie in this BST
   * 
   * @return the best (largest) movie (the most recent, highest rated, and having the largest name)
   *         in this MovieTree, and null if this tree is empty.
   */
  public Movie getBestMovie() {
    // import root to a temp node
    // track through the tree to find the best movie
    BSTNode<Movie> bestMovie = root;
    while (bestMovie.getRight() != null) {
      bestMovie = bestMovie.getRight();
    }

    return bestMovie.getData();
  }

  /**
   * Search for movies given the year and minimum rating as lookup key.
   * 
   * @param year   production year of a movie
   * @param rating rating value of a movie
   * @return a list of movies whose year equals our lookup year key and having a rating greater or
   *         equal to a given rating.
   * @throws a NoSuchElementException with a descriptive error message if there is no Movie found in
   *           this BST having the provided year and a rating greater or equal to the provided
   *           rating
   */
  public ArrayList<Movie> lookup(int year, double rating) {
    // call lookupHelper given the year, rating, the root of this MovieTree and an empty arrayList
    ArrayList<Movie> movieList = new ArrayList<>(size);
    lookupHelper(year, rating, root, movieList);


    // If no match found by the lookupHelper throw a NoSuchElementException with a descriptive error
    // message
    if (movieList.isEmpty()) {
      throw new NoSuchElementException("ERROR! EMPTY MOVIE LIST / No matched Movie found.");
    } else {
      return movieList;
    }
  }

  /**
   * Recursive helper method to lookup the list of movies given their year of production and a
   * minimum value of rating
   * 
   * @param year      the year we would like to search for a movie
   * @param rating    the minimum rating we would like to search for a movie
   * @param movieList an arraylist which stores the list of movies matching our search criteria
   *                  (exact year of production and having at least the provided rating) within the
   *                  subtree rooted at current
   * @param current   "root" of the subtree we are looking for a match to find within it.
   */
  protected void lookupHelper(int year, double rating, BSTNode<Movie> current,
      ArrayList<Movie> movieList) {
    // If the BST rooted at current is null, do nothing and return
    if (current == null) {
      return;
    } else {
      // the BST rooted at current is not empty, perform a pre-order traversal of the subtree
      // starting from current looking for movies satisfying our search criteria, and add each of
      // them
      // to the movieList
      // Base case
      if (current.getData().getYear() == year && current.getData().getRating() >= rating) {
        movieList.add(current.getData());
      }
      // Recursion Part
      lookupHelper(year, rating, current.getLeft(), movieList);
      lookupHelper(year, rating, current.getRight(), movieList);
    }
  }
}
