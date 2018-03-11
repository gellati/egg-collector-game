package luad.eggs;

public class Point2D
{
  /**
   * The x coordinate of the point.
   */
  private int x;
  
  /**
   * The y coordinate of the point.
   */
  private int y;
  
  /**
   * Creates a new point with given coordinates.
   * @param x The x coordinate of the point.
   * @param y The y coordinate of the point.  
   */
  public Point2D(int x, int y)
  {
    setX(x);
    setY(y);
  }
  
  /**
   * @return The x coordinate of the point.
   */
  public int getX()
  {
    return x;
  }
  
  /**
   * @return The y coordinate of the point.  
   */
  public int getY()
  {
    return y;
  }
  
  /**
   * @param x The new x coordinate of the point.  
   */
  public void setX(int x)
  {
    this.x = x;
  }
  
  /**
   * @param y The new y coordinate of the point.  
   */
  public void setY(int y)
  {
    this.y = y;
  }
  
  /**
   * Returns the square of the Euclidean distance from another point, 
   * calculated using the formula (x_1 - x_2)^2 + (y_1 - y_2)^2.  
   * @param otherPoint The point to calculate the distance from.
   * @return The square of the distance from the other point.  
   */
  public int distanceSquaredFrom(Point2D otherPoint)
  {
    int xDistance = this.getX() - otherPoint.getX();
    int yDistance = this.getY() - otherPoint.getY();
    
    return (xDistance * xDistance) + (yDistance * yDistance);
  }
  
  public Point2D add(int xDifference, int yDifference)
  {
    return new Point2D(x + xDifference, y + yDifference);
  }
}
