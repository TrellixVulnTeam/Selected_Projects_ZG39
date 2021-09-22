//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04 Carrot Patch
// Course: CS 300 Spring 2021
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
import java.util.Random;
import processing.core.PImage;

public class Animal implements GUIListener {
  private static Random randGen = new Random(); // Generator of random numbers
  protected static CarrotPatch processing; // PApplet object representing the display window
  protected PImage image; // image of this
  protected String label; // represents the name/identifier of this animal
  private int x; // x-position of this animal in the display window
  private int y; // y-position of this animal in the display window
  private boolean isDragging; // indicates whether the animal is being dragged or not
  private int oldMouseX; // old mouse x-position
  private int oldMouseY; // old mouse-y position

  /**
   * Creates a new Animal object positioned at a given position of the display window
   * 
   * @param processing    PApplet object that represents the display window
   * @param x             x-position of the image of this animal in the display window
   * @param y             y-position of the image of this animal in the display window
   * @param imageFileName filename of the animal image
   */
  public Animal(int x, int y, String imageFileName) {

    // Set Animal drawing parameters
    this.x = x; // sets the x-position of this animal
    this.y = y; // sets the y-position of this animal
    this.image = processing.loadImage(imageFileName);
    isDragging = false; // initially the animal is not dragging
  }

  /**
   * Creates a new Animal object positioned at a random position of the display window
   * 
   * @param processing    PApplet object that represents the display window
   * @param imageFileName filename of the animal image
   */
  public Animal(String imageFileName) {
    this(randGen.nextInt(processing.width), Math.max(randGen.nextInt(processing.height), 200),
        imageFileName);
  }

  /**
   * Sets the CarrotPatch PApplet object where this animal is drawn and operates
   * 
   * @param processing processing to set
   */
  public static void setProcessing(CarrotPatch processing) {
    Animal.processing = processing;
  }

  /**
   * Moves the object on the display window using the displacements dx and dy
   * 
   * @param dx displacement on the x-axis
   * @param dy displacement on the y-axis
   */
  public void move(int dx, int dy) {
    x += dx;
    y += dy;
  }

  /**
   * Draws the animal to the display window. This animal must follow the mouse moves if it is being
   * dragged (i.e. if its isDragging field is set to true).
   */
  @Override
  public void draw() {
    // set oldMouseX and oldMouseY if this animal is clicked
    if (!this.isDragging && isMouseOver()) {
      oldMouseX = processing.mouseX;
      oldMouseY = processing.mouseY;
    }
    // If this animal is dragging, it must follow the mouse moves
    if (this.isDragging) {
      move(processing.mouseX - oldMouseX, processing.mouseY - oldMouseY);
      oldMouseX = processing.mouseX;
      oldMouseY = processing.mouseY;
    }

    // draw this animal at its current position
    processing.image(this.image, x, y);
    // display label
    displayLabel();
    // call to the action which must be continuously performed by this animal
    action();
  }

  /**
   * display's this animal's object label on the application window screen
   */
  private void displayLabel() {
    processing.fill(0); // specify font color: black
    // display label
    processing.text(label, this.x, this.y + this.image.height / 2 + 4); // text
  }

  /**
   * Returns the label of this Animal
   * 
   * @return the label that represents this animal's identifier
   */
  public String getLabel() {
    return label;
  }


  /**
   * Returns the image of this animal
   * 
   * @return the image of type PImage of the tiger object
   */
  public PImage getImage() {
    return image;
  }


  /**
   * Gets the y-position of this animal
   * 
   * @return the X-position of this animal
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the x position of this animal
   * 
   * @return the Y-position of this animal
   */
  public int getY() {
    return y;
  }


  /**
   * Sets the x-position of this animal
   * 
   * @param x the XPosition to set
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Sets the y-position of this animal
   * 
   * @param y the YPosition to set
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Checks whether this animal is being dragged
   * 
   * @return true if the animal is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Computes the euclidean distance between this animal and given (x,y) position
   * 
   * @param x an x-position
   * @param y a y-position
   * @return distance between this animal and otherAnimal
   */
  public double distance(int x, int y) {
    return Math.sqrt(Math.pow(this.getX() - x, 2) + Math.pow(this.getY() - y, 2));
  }

  /**
   * checks whether the mouse is over this animal.
   * 
   * @return
   */
  @Override
  public boolean isMouseOver() {
    int xLow = (int) (x - image.width / 2);
    int xHigh = (int) (x + image.width / 2);
    int yLow = (int) (y - image.height / 2);
    int yHigh = (int) (y + image.height / 2);

    if (processing.mouseX >= xLow && processing.mouseX <= xHigh && processing.mouseY >= yLow
        && processing.mouseY <= yHigh) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * starts dragging this animal by setting its isDragging field to true if the mouse is pressed and
   * is over this animal
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      this.isDragging = true;
    }
  }

  /**
   * stops dragging this animal by setting its isDragging field to false
   */
  @Override
  public void mouseReleased() {
    this.isDragging = false;
  }

  /**
   * Checks whether this animal is within a distance range with respect to a given (x,y) position.
   * It returns TRUE if this animal is located within a range distance [0 .. range] around the
   * provided position and FALSE otherwise.
   *
   * @param x     a given x-position
   * @param y     a given y-position
   * @param range radius of the neighborhood range from the given (x,y) position
   * @return true if otherAnimal is close to this animal with respect to range
   */
  public boolean isClose(int x, int y, int range) {
    return (this.distance(x, y) <= range);
  }

  /**
   * Checks whether this animal is within a distance range with respect to anotherAnimal. It returns
   * TRUE if otherAnimal is located within a range distance [0 .. range] around the current animal
   * and FALSE otherwise.
   *
   * @param otherAnimal an animal to check if it is close to this animal
   * @param range       radius of the neighborhood range from the position of this Animal
   * @return true if otherAnimal is close to the current tiger
   */
  public boolean isClose(Animal otherAnimal, int range) {
    return (this.distance(otherAnimal.getX(), otherAnimal.getY()) <= range);
  }

  /**
   * Continuous behavior performed by this animal in the carrot patch
   */
  public void action() {
    // This method should be overridden by a subclasses
  }

}
