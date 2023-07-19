
public class Badger extends MovingThing implements Clickable {

  private DanceStep[] danceSteps; // array storing this Badger's dance show steps
  private boolean isDancing; // indicates whether this badger is dancing or not
  private boolean isDragging; // indicates whether this badger is being dragged or not
  private float[] nextDancePosition; // stores the next dance (x, y) position of this Badger.
  private static int oldMouseX; // old x-position of the mouse
  private static int oldMouseY; // old y-position of the mouse
  private int stepIndex; // index position of the current dance step of this badger

  /**
   * Creates a new Badger object positioned at a specific position of the display window and whose
   * moving speed is 2. When created, a new badger is not dragging and is not dancing. This
   * constructor also sets the danceSteps of the created Badger to the one provided as input and
   * initializes stepIndex to 1.
   * 
   * @param x
   * @param y
   * @param danceSteps
   */
  public Badger(float x, float y, DanceStep[] danceSteps) {
    super(x, y, 2, "Badger.png");
    this.isDancing = false;
    this.isDragging = false;
    this.danceSteps = danceSteps;
    this.stepIndex = 1;
  }

  /**
   * Draws this badger to the display window.
   */
  public void draw() {
    super.draw();

    // If the badger is dragged, start dragging it on the screen
    if (this.isDragging) {
      drag();
    }
    if (isDancing == true) {
      this.dance();
    }
  }

  /**
   * Checks whether this badger is being dragged
   * 
   * @return true if the badger is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Helper method to drag this Badger object to follow the mouse moves
   */
  private void drag() {

    // Calculate the distance to be shifted
    int dx = processing.mouseX - oldMouseX;
    int dy = processing.mouseY - oldMouseY;
    this.x += dx;
    this.y += dy;
    if (this.x > 0)
      this.x = Math.min(x, processing.width);
    else
      this.x = 0;
    if (this.y > 0)
      this.y = Math.min(y, processing.height);
    else
      this.y = 0;
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
  }

  /**
   * Starts dragging this badger
   */
  public void startDragging() {

    // Change the oldMouse coordinates
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
    this.isDragging = true;
    this.drag(); // Drag the object
  }

  /**
   * Stops dragging this Badger object
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Defines the behavior of this Badger when it is clicked. If the mouse is over this badger and
   * this badger is NOT dancing, this method starts dragging this badger.
   */
  @Override
  public void mousePressed() {
    // If the mouse is over the one of the badgers, start dragging it
    if (isMouseOver(this) && this.isDancing == false) {
      this.startDragging();
    }
  }

  /**
   * Defines the behavior of this Badger when the mouse is released. If the mouse is released, this
   * badger stops dragging.
   */
  @Override
  public void mouseReleased() {
    this.stopDragging(); // Stop dragging this object
  }


  /**
   * This helper method moves this badger one speed towards its nextDancePosition. Then, it checks
   * whether this Badger is facing right and updates the isFacingRight data field accordingly. After
   * making one move dance, a badger is facing right if the x-move towards its next dance position
   * is positive, otherwise, it is facing left.
   * 
   * @return true if this Badger almost reached its next dance position, meaning that the distance
   *         to its next dance position is less than 2 times its speed. Otherwise, return false.
   */
  private boolean makeMoveDance() {
    // Calculate the distance to be moved
    float dx = this.nextDancePosition[0] - this.x;
    float dy = this.nextDancePosition[1] - this.y;
    float d = (float) Math.sqrt((dx * dx) + (dy * dy));

    // Replace this x and y with the new calculated variables after one move
    this.x = this.x + (this.speed * dx) / d;
    this.y = this.y + (this.speed * dy) / d;
    // If the distance to be moved is more than zero, let the badger face right
    if (dx > 2 * this.speed) {
      this.isFacingRight = true;
    }

    // If the distance to be moved is less than zero, let the badger face left
    else if (dx < 2 * this.speed) {
      this.isFacingRight = false;
    }

    // If the distance is less than two times the speed of the badger, return true
    if (d < (2 * this.speed)) {
      return true;
    }

    // Return false if the disntance is more or equal to two times the speed of the badger
    else {
      return false;
    }

  }

  /**
   * Implements the dance behavior of this Badger. This method prompts the Badger to make one move
   * dance.
   */
  private void dance() {

    // If this badger's makeMoveDance returns as true
    while (this.makeMoveDance()) {

      // Set the next dance positions as the following sequence of rules
      this.nextDancePosition = danceSteps[this.stepIndex]
          .getPositionAfter(this.nextDancePosition[0], this.nextDancePosition[1]);
      stepIndex = (stepIndex + 1) % danceSteps.length; // Set the step index to circular
      // indexing
    }
  }


  /**
   * Prompts this badger to start dancing.
   */
  public void startDancing() {
    this.isDancing = true; // Start the dancing sequence for this badger
    this.stopDragging(); // Stop dragging the badger
    stepIndex = 0; // Reset the stepIndex

    // Reset the next Dance position so the badger can start dancing
    this.nextDancePosition = danceSteps[0].getPositionAfter(this.x, this.y);
  }


  /**
   * Prompts this badger to stop dancing. Sets the isDancing data field to false.
   */
  public void stopDancing() {
    this.isDancing = false;
  }
}
