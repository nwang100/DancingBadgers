
public class StarshipRobot extends MovingThing {


  private Thing destination; // destination point of this StarshipRobot at its current journey
  // delivering food to badgers
  private Thing source; // source point of this StarshipRobot at its current journey delivering
  // food to badgers

  /**
   * Creates a new StarshipRobot and sets its source, destination, and speed. The start point of
   * this this StarshipRobot is set to the (x,y) position of source. When created, a StarshipRobot
   * object must face its destination.
   * 
   * @param source
   * @param destination
   * @param speed
   */
  public StarshipRobot(Thing source, Thing destination, int speed) {
    super(source.x, source.y, speed, "starshipRobot.png"); // Inherit superclass constructor
    this.source = source; // Set this source to the inputed source
    this.destination = destination; // Set this destination to the inputed destination

    // If the source's x coordinate is smaller than the destination's, the object is facing
    // right
    if (source.x < destination.x) {
      isFacingRight = true;
    }

    // If the source's x coordinate is bigger than the destination's, the object is facing left
    else if (source.x > destination.x) {
      isFacingRight = false;
    }

  }

  /**
   * Draws this StarshipRobot to the display window while it is in motion delivering food.
   * 
   * @Override draw in class MovingThing
   */
  @Override
  public void draw() {
    this.go(); // Tells the Star-ship robot to go and start moving
    super.draw(); // Inherited draw method to execute
  }

  /**
   * Checks whether this StarshipRobot is over a specific Thing
   * 
   * @param thing
   * @return true if this StarshipRobot is over the Thing object passed as input, otherwise, returns
   *         false.
   */
  public boolean isOver(Thing thing) {

    // Returns if any of the points are out of bounds of the image
    return (this.x - image().width / 2 < thing.x + thing.image().width / 2)
        && (thing.x - thing.image().width / 2 < this.x + image().width / 2)
        && (this.y - image().height / 2 < thing.y + thing.image().height / 2)
        && (thing.y - thing.image().height / 2 < this.y + image().height / 2);
  }

  /**
   * Helper method to move this StarshipRobot towards its destination
   */
  private void moveTowardsDestination() {

    // Calculate the distance to move
    float dx = this.destination.x - this.x;
    float dy = this.destination.y - this.y;
    float d = (float) Math.sqrt((dx * dx) + (dy * dy));

    // Replace this x and y with the new calculated variables after one move
    this.x = this.x + (this.speed * dx) / d;
    this.y = this.y + (this.speed * dy) / d;
  }

  /**
   * Implements the action of this StarshipRobot. By default, an StarshipRobot object moves
   * back-and-forth between its source and destination.
   */
  private void go() {

    // If the object is over the destination
    if (isOver(destination) == true) {
      Thing temp; // Initialize temp variable to swap source and destination

      // Swap source and destination objects
      temp = source;
      source = destination;
      destination = temp;
      this.isFacingRight = !(this.isFacingRight);
    }
    moveTowardsDestination(); // Move towards the destination

  }
}
