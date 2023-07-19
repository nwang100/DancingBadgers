
// Import all useful libraries
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

public class Thing {
  protected static processing.core.PApplet processing; // PApplet object that represents the
  // display window of this graphic application
  private processing.core.PImage image; // image of this graphic thing of type PImage
  protected float x; // x-position of this thing in the display window
  protected float y; // y-position of this thing in the display window


  /**
   * Creates a new graphic Thing located at a specific (x, y) position of the display window
   * 
   * @param x
   * @param y
   * @param imageFilename
   */
  public Thing(float x, float y, String imageFilename) {

    // Load the image into the file
    this.image = processing.loadImage(("images" + File.separator + imageFilename));

    // Set this image's x and y coordinates
    this.x = x;
    this.y = y;
  }

  /**
   * Draws this thing to the display window at its current (x,y) position
   */
  public void draw() {
    processing.image(this.image, x, y);
  }

  /**
   * Sets the PApplet object display window where this Thing object will be drawn
   * 
   * @param processing
   */
  public static void setProcessing(processing.core.PApplet processing) {
    Thing.processing = processing;
  }

  /**
   * Returns a reference to the image of this thing
   * 
   * @return the image of type PImage of the thing object
   */
  public processing.core.PImage image() {
    return this.image;
  }

  /**
   * 
   * @param Checks if the mouse is over this Thing object
   * @return true if the mouse is over this Thing, otherwise returns false.
   */
  public static boolean isMouseOver(Thing thing) {

    // Set local variables of the thing's Width and Height
    int thingWidth = thing.image().width;
    int thingHeight = thing.image().height;

    // checks if the mouse is over the badger
    return processing.mouseX >= thing.x - thingWidth / 2
        && processing.mouseX <= thing.x + thingWidth / 2
        && processing.mouseY >= thing.y - thingHeight / 2
        && processing.mouseY <= thing.y + thingHeight / 2;
  }
}
