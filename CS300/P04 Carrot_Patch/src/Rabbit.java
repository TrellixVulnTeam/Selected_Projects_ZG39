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
import java.io.File;

public class Rabbit extends Animal {
  private static final String RABBIT = "images" + File.separator + "rabbit.png";
  private static final String TYPE = "R"; // A String that represents the rabbit type
  private static int hopStep = 70; // one hop step
  private static int scanRange = 175; // scan range to watch out for threats
  private static int nextID = 1; // class variable that represents the identifier
  // of the next rabbit to be created
  private final int ID; // positive number that represents the order of this rabbit

  /**
   * Creates a new rabbit object located at a random position of the display window
   */
  public Rabbit() {
    // Set rabbit drawing parameters
    super(RABBIT);
    // Set rabbit identification fields
    ID = nextID;
    this.label = TYPE + ID; // String that identifies the current rabbit
    nextID++;
  }

  // getter of Rabbit.scanRange static field
  public static int getScanRange() {
    return scanRange;
  }

  // getter of Rabbit.hopStep static field
  public static int getHopStep() {
    return hopStep;
  }

  /**
   * Gets the first carrot in the patch. If the carrot is in the Rabbit hopStep range, the rabbit
   * eats it. It sets its position to the (x,y) position of the carrot and the carrot will be
   * removed from the Carrot Patch. Otherwise, the rabbit moves one hopStep towards that carrot. If
   * no carrot found (meaning Carrots.getFirstCarrot() returns false), the rabbit does nothing.
   */
  public void hopTowardsCarrot() {
    // get the first carrot
    int[] carrot = Carrots.getFirstCarrot();
    if (carrot != null) {
      // calculate carrot jump distance
      // variable name follow the PDF guide
      int destinationX = carrot[1];
      int destinationY = carrot[2];
      int oldX = this.getX();
      int oldY = this.getY();
      int dx = destinationX - oldX;
      int dy = destinationY - oldY;
      int d = (int) Math.sqrt(dx * dx + dy * dy);
      int newX = oldX + hopStep * dx / d;
      int newY = oldY + hopStep * dy / d;

      if (isClose(carrot[1], carrot[2], hopStep)) {
        this.setX(carrot[1]);
        this.setY(carrot[2]);
        Carrots.remove(carrot[0]);
      } else {
        this.setX(newX);
        this.setY(newY);
      }
    }
  }

  /**
   * This method watches out for wolves. Checks if there is a wolf in the Rabbit.scanRange of this
   * Rabbit.
   *
   * @return true if the current rabbit is close to at least one wolf
   */
  public boolean watchOutForWolf() {
    // Traverse the processing.objects arraylist checking
    // whether there is a wolf which is close by Rabbit.scanRange
    // of this rabbit.
    boolean wolfNearBy = false;
    for (int i = 0; i < processing.objects.size(); i++) {
      if (processing.objects.get(i) instanceof Wolf) {
        if (isClose((Wolf) processing.objects.get(i), getScanRange())) {
          wolfNearBy = true;
        }
      }
    }
    return wolfNearBy;
  }

  /**
   * Watches out for a wolf and display a Warning message "WOLF!" if there is any wolf in its
   * neighborhood.
   */
  @Override
  public void action() {
    if (watchOutForWolf()) {
      // this.setScaredImage();
      processing.fill(0); // specify font color: black
      processing.text("WOLF!", this.getX(), this.getY() - this.image.height / 2 - 6);
    }
  }

  /**
   * override: when mouse Press a rabbit.
   */
  @Override
  public void mousePressed() {
    super.mousePressed();
    hopTowardsCarrot();
  }
}
