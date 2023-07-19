
public class MovingThing extends Thing implements Comparable<MovingThing> {

  protected int speed; // movement speed of this MovingThing
  protected boolean isFacingRight; // indicates whether this MovingThing is facing right or not


  /**
   * Creates a new MovingThing and sets its speed, image file, and initial x and y position. A
   * MovingThing object is initially facing right.
   * 
   * @param x
   * @param y
   * @param speed
   * @param imageFilename
   */
  public MovingThing(float x, float y, int speed, String imageFilename) {
    super(x, y, imageFilename); // Inherit from superclass
    this.speed = speed; // Set this object's speed to the argument speed
    this.isFacingRight = true; // Set this object to face right
  }

  /**
   * Draws this MovingThing at its current position. The implementation details of this method is
   * fully provided in the write-up of p05.
   */
  @Override
  public void draw() {

    // draw this MovingThing at its current position
    processing.pushMatrix();
    processing.rotate(0.0f);
    processing.translate(x, y);
    if (!isFacingRight) {
      processing.scale(-1.0f, 1.0f);
    }
    processing.image(image(), 0.0f, 0.0f);
    processing.popMatrix();

  }

  /**
   * Compares this object with the specified MovingThing for order, in the increasing order of their
   * speeds
   * 
   * @return zero if this object and other have the same speed, a negative integer if the speed of
   *         this moving object is less than the speed of other, and a positive integer otherwise.
   */
  @Override
  public int compareTo(MovingThing other) {

    // If the other moving thing's speed is equal to the moving thing's speed return 0
    if (other.speed == speed) {
      return 0;
    }

    // If the other moving thing's speed is less than the moving thing's speed return a negative
    // integer
    else if (other.speed > speed) {
      return -1;
    }

    // In all other cases, return a positive integer
    else {
      return 1;
    }
  }
}
