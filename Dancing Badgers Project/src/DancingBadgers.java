
// Import all useful libraries
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

public class DancingBadgers extends PApplet {
  public static void main(String[] args) {
    PApplet.main("DancingBadgers");
  }


  // array storing badgers dance show steps
  private static DanceStep[] badgersDanceSteps = new DanceStep[] {DanceStep.LEFT, DanceStep.RIGHT,
      DanceStep.RIGHT, DanceStep.LEFT, DanceStep.DOWN, DanceStep.LEFT, DanceStep.RIGHT,
      DanceStep.RIGHT, DanceStep.LEFT, DanceStep.UP};
  // array storing the positions of the dancing badgers at the start of the dance show

  private static float[][] startDancePositions =
      new float[][] {{300, 250}, {364, 250}, {428, 250}, {492, 250}, {556, 250}};

  private boolean danceShowOn; // Boolean value showing whether danceShow is on
  private static processing.core.PImage backgroundImage; // Background image field
  private static ArrayList<Thing> things = new ArrayList<Thing>(); // ArrayList of Things
  private static Random randGen; // random number generator
  private static int badgersCountMax; // Maximum number of badgers allowed

  /**
   * Sets the size of the display window of this graphic application
   */
  @Override
  public void settings() {
    this.size(800, 600);
  }

  /**
   * Defines initial environment properties of this graphic application. This method initializes all
   * the data fields defined in this class.
   */
  @Override
  public void setup() {

    this.getSurface().setTitle("P5 Dancing Badgers"); // displays the title of the screen
    this.textAlign(3, 3); // sets the alignment of the text
    this.imageMode(3); // interprets the x and y position of an image to its center
    this.focused = true; // confirms that this screen is "focused", meaning
                         // it is active and will accept mouse and keyboard input.

    randGen = new Random(); // Initialize random number generator
    backgroundImage = loadImage("images" + File.separator + "background.png"); // load background
                                                                               // image
    this.badgersCountMax = 5; // set the maximum number of badgers allowed

    Thing.setProcessing(this); // sets the Thing processing to the processing of the Badger class.


    // Create all objects of Thing to be put in the ArrayList of things to display
    Thing thing1 = new Thing(50, 50, "target.png");
    Thing thing2 = new Thing(750, 550, "target.png");
    Thing thing3 = new Thing(750, 50, "shoppingCounter.png");
    Thing thing4 = new Thing(50, 550, "shoppingCounter.png");

    // Add all of the created object Thing to the ArrayList of thing
    things.add(thing1);
    things.add(thing2);
    things.add(thing3);
    things.add(thing4);

    // Create both of the star-ships to be put in the ArrayList of things to display
    StarshipRobot robot1 = new StarshipRobot(thing1, thing3, 3);
    StarshipRobot robot2 = new StarshipRobot(thing2, thing4, 5);

    // Add all of the created object StarshipRobot to the ArrayList of things
    things.add(robot1);
    things.add(robot2);

    // Create both of the basketballs to be put in the ArrayList of things to display
    Basketball ball1 = new Basketball(50, 300);
    Basketball ball2 = new Basketball(750, 300);

    // Add both of the basketball objects to the Arraylist of things to display
    things.add(ball1);
    things.add(ball2);

  }

  /**
   * Callback method that draws and updates the application display window. This method runs in an
   * infinite loop until the program exits.
   */
  @Override
  public void draw() {
    background(color(255, 218, 185)); // Draw the background color

    // Draw background image
    image(backgroundImage, width / 2, height / 2);

    // Draw all the things in the Arraylist
    for (int i = 0; i < things.size(); i++) {
      things.get(i).draw();
    }

  }

  /**
   * Callback method called each time the user presses the mouse. This method iterates through the
   * list of things. If the mouse is over a Clickable object, it calls its mousePressed method, then
   * returns.
   */
  public void mousePressed() {

    // Loop through all the objects in the list of things
    for (int i = 0; i < things.size(); i++) {

      // If the object is an instance of Clickable, execute the mouse press function
      if (things.get(i) instanceof Clickable)
        ((Clickable) things.get(i)).mousePressed();
    }
  }

  /**
   * Callback method called each time the mouse is released. This method calls the mouseReleased()
   * method on every Clickable object stored in the things list.
   */
  public void mouseReleased() {

    // Loop through all of the things in the Arraylist
    for (int i = 0; i < things.size(); i++) {

      // If the object is an instance of Clickable, execute the mouse released function
      if (things.get(i) instanceof Clickable) {
        ((Clickable) things.get(i)).mouseReleased();
      }
    }
  }

  /**
   * Gets the number of Badger objects present in the basketball arena
   * 
   * @return the number of Badger objects present in the basketball arena
   */
  public int badgersCount() {

    int numBadgers = 0; // initialize local variable for the number of badgers

    // Loop through all of the things in the Arraylist of things
    for (int i = 0; i < things.size(); i++) {

      // If the thing is a Badger
      if (things.get(i) instanceof Badger) {

        numBadgers++; // Increase the number of badgers by one
      }
    }
    return numBadgers; // Return the total number of badgers
  }

  /**
   * Sets the badgers start dance positions.
   */
  private void setStartDancePositions() {
    int j = 0; // Initialize helper variable

    // Loop through the Arraylist of things
    for (int i = 0; i < things.size(); i++) {

      // If the thing is an instance of Badger object
      if (things.get(i) instanceof Badger) {

        j++; // Increment j by one
        danceShowOn = true; // Turn danceShowOn
        things.get(i).x = startDancePositions[j - 1][0]; // Set the x coordinate of the badger to
                                                         // the starting dance position
        things.get(i).y = startDancePositions[j - 1][1]; // Set the y coordinate of the badger to
                                                         // the starting dance position

      }
    }
  }

  /**
   * Callback method called each time the user presses a key.
   */
  public void keyPressed() {

    switch (Character.toUpperCase(key)) {
      case 'B': // add new badger as long as the maximum numbers of badgers allowed to be
                // present in the field is not reached
        if (badgersCount() < badgersCountMax && !(danceShowOn)) {
          things.add(new Badger((float) randGen.nextInt(width), (float) randGen.nextInt(height),
              badgersDanceSteps));
        }
        break;

      case 'C': // If the c-key is pressed, the danceShow is terminated (danceShowOn set to false),
                // and ALL MovingThing objects are removed from the list of things. This key removes
                // MovingThing objects ONLY.
        danceShowOn = false;
        for (int i = 0; i < things.size(); i++) {
          if (things.get(i) != null && things.get(i) instanceof MovingThing) {
            things.remove(i);
            i--;
          }
        }
      case 'D': // This key starts the dance show if the danceShowOn was false, and there is at
                // least one Badger object in
                // the basketball arena. Starting the dance show, sets the danceShowOn to true, sets
                // the start dance positions
                // of the Badger objects, and calls the startDancing() method on every Badger object
                // present in the list of things.
                // Pressing the d-key when the danceShowOn is true or when there are no Badger
                // objects present in the basketball arena has no effect.

        // If danceShowOn was previously false,
        if (danceShowOn == false) {
          setStartDancePositions(); // Set the starting dance positions of each badger

          for (int i = 0; i < things.size(); i++) {

            // If the thing object is a Badger
            if (things.get(i) instanceof Badger) {
              ((Badger) things.get(i)).startDancing(); // Make this badger start dancing
            }
          }
        }

        break;

      case 'R': // delete the badger being pressed
        for (int i = 0; i < things.size(); i++) {

          // If the thing object isn't null, the mouse is over the object, and the thing is a badger
          if (things.get(i) != null && Thing.isMouseOver(things.get(i))
              && things.get(i) instanceof Badger) {
            things.remove(i); // Remove this badger from the display window
            break; // Break out of the loop so only one badger is removed
          }
        }
        break;

      case 'S': // When S is pressed, stop all the badgers from dancing and allow for the badgers to
                // be dragged

        // If the badgers are dancing, stop that
        if (danceShowOn == true) {
          danceShowOn = false;
        }
        for (int i = 0; i < things.size(); i++) {
          if (things.get(i) instanceof Badger) {
            ((Badger) things.get(i)).stopDancing(); // Make each badger stop dancing
          }
        }
    }
  }
}
