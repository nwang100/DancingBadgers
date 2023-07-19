
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

public class Basketball extends Thing implements Clickable {

  private int rotations; // Total number of rotations this Basketball object has made since it
  // was created.
  public float rotation; // Defines the rotation angle in radians that this Basketball object
  // make when clicked.

  /**
   * Creates a new Basketball object located at (x,y) position whose image filename is
   * "basketball.png", and sets its rotation angle to PApplet.PI/2.
   * 
   * @param x
   * @param y
   */
  public Basketball(float x, float y) {
    super(x, y, "basketball.png");
    rotation = PApplet.PI / 2;
  }


  /**
   * Draws this rotating Basketball object to the display window.
   */
  public void draw() {

    // draw this rotating Basketball object at its current position
    processing.pushMatrix();
    processing.translate(x, y);
    processing.rotate(this.rotations * rotation);
    processing.image(image(), 0.0f, 0.0f);
    processing.popMatrix();
  }

  /**
   * Defines the behavior of this basketball when the mouse is pressed. The basketball rotates when
   * it is clicked (the mouse is over it when pressed).
   */
  @Override
  public void mousePressed() {

    // If the mouse is over the basketball, rotate the object
    if (isMouseOver(this)) {
      this.rotate();
    }
  }

  /**
   * Called when the mouse is released. A basketball object does nothing when the mouse is released.
   * This is a method with an empty body.
   */
  @Override
  public void mouseReleased() {
    // Do nothing
  }


  /**
   * This method rotates this basketball object by incrementing the number of its rotations by one.
   */
  public void rotate() {
    this.rotations++; // Increment the number of rotations by one
  }
}
