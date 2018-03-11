package luad.eggs.gui.layouts;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.List;

import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.css.StyleablePropertyFactory;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Custom layout that lays out its children as tiles in a transformed grid.
 * 
 * This class has been created specially for the 'Eggs' game in the Linux
 * User and Developer 'Making a Game in Java' series.
 * 
 * This class lays out its children in a grid pattern, where each child has
 * a 2D coordinate consisting of a row number and a column number.  In order
 * to work out the correct size of the pane, {@code GameMapPane} needs to know
 * the maximum and minimum row and column numbers that you are expecting to use.
 * For example:
 * 
 * {@code
 * Pane mapPane = new GameMapPane(0, 0, 4, 5);
 * }
 * will create a pane that is just large enough to hold a 5x6 grid, with row 
 * numbers ranging from 0 to 4 and column numbers ranging from 0 to 5.
 * 
 * Note that there is nothing to stop you placing children outside these specified
 * bounds; this will cause them to be placed outside the boundary of the pane.
 * 
 * Row and column numbers should be specified in the same way as they are in the
 * {@link GridPane} class.  That is, you should set row numbers using the method
 * {@link GameMapPane.setRowIndex(Node, Integer)} and column numbers using the method
 * {@link GameMapPane.setColumnIndex(Node, Integer)}.  For example:
 * 
 * {@code
 * Pane tile = new Pane();
 * GameMapPane.setRowIndex(tile, 2);
 * GameMapPane.setColumnIndex(tile, 3);
 * mapPane.getChildren().add(tile);
 * }
 * 
 * Note that children will be rendered in the order that they are added to the pane, 
 * which you might want to take into account in the case of overlapping 3D map tiles.
 * 
 * There are further properties of this class, which are intended to be set using CSS.
 * 
 * {@code -cell-height}    - the height of each cell on the map in pixels.
 * {@code -cell-width}     - the width of each cell on the map in pixels.
 * {@code -grid-transform} - a 2x2 matrix that transforms the grid into points in space.
 *  The CSS code
 *  {@code
 *  -grid-transform: 48 47 -28 29;
 *  }
 *  corresponds to the matrix
 *  <pre>
 *  / 48 47 \
 *  |       |
 *  \-28 29 /
 *  </pre>
 *  As a special case, {@code -grid-transform: 100 0 0 100;} will stretch the grid by 100
 *  in both directions.  
 *  
 *  If you are working through the 'Making a Game in Java' series and using the provided
 *  artwork, you should use the values provided in the style sheet map-3d.css, which uses
 *  {@code -grid-transform 48 47 -28 29;}. If you want to use the small tiles (e.g.,
 *  land_33px.png) then you should divide these four values by 3.
 *  
 * As long as you use the CSS and artwork provided, it should be no more difficult to use
 * this class than it would be to use a {@link GridPane}.  
 */
public class GameMapPane extends Pane
{
  /**
   * Factory for creating the CSS-modifiable fields.
   */
  private static final StyleablePropertyFactory<GameMapPane> FACTORY =
      new StyleablePropertyFactory<>(Pane.getClassCssMetaData());
  
  /**
   * Registers this (Java) class with CSS.
   * 
   * @param minRowIndex    The minimum expected row index.
   * @param maxRowIndex    The maximum expected row index.
   * @param minColumnIndex The minimum expected column index.
   * @param maxColumnIndex The maximum expected column index.
   */
  public GameMapPane(int minRowIndex,
                     int minColumnIndex,
                     int maxRowIndex,
                     int maxColumnIndex)
  {
    setMinRowIndex(minRowIndex);
    setMinColumnIndex(minColumnIndex);
    setMaxRowIndex(maxRowIndex);
    setMaxColumnIndex(maxColumnIndex);

    getStyleClass().add("game-map-pane");
  }
  
  /**
   * Registers this (Java) class with CSS.
   */
  public GameMapPane()
  {
    this(0, 0, 0, 0);
  }
  
  /**
   * Lays out the children in the grid pattern.
   * Each child is assigned a row index and a column index, which givesrise to
   * a 2D coordinate.  We then apply the specified 2D affine transformation to
   * each of these points to get the final position of the child on the pane.
   */
  @Override
  protected void layoutChildren()
  {
    double cellWidth = getCellWidth();
    double cellHeight = getCellHeight();
    
    for (int i = 0 ; i < getChildren().size() ; ++i) {
      Node child = getChildren().get(i);
      Point2D position = getPosition(child);
      
      // Draw the child on the pane.
      layoutInArea(child,
                   position.getX(),
                   position.getY(),
                   cellWidth,
                   cellHeight,
                   0,
                   HPos.CENTER,
                   VPos.CENTER);
    }
  }
  
  /**
   * @return The minimum width of the pane required to hold cells within the 
   * given bounds.
   */
  @Override
  public double computeMinWidth(double height)
  {
    Rectangle.Double boundingRectangle = getBoundingRectangle();
    return boundingRectangle.getWidth() + getCellWidth();
  }
  
  /**
   * @return The preferred width of the pane required to hold cells within the 
   * given bounds.
   */
  @Override
  public double computePrefWidth(double height)
  {
    return computeMinWidth(height);
  }
  
  /**
   * @return The minimum height of the pane required to hold cells within the 
   * given bounds.
   */
  @Override
  public double computeMinHeight(double width)
  {
    Rectangle.Double boundingRectangle = getBoundingRectangle();
    return boundingRectangle.getHeight() + getCellHeight();
  }
  
  /**
   * @return The preferred height of the pane required to hold cells within the 
   * given bounds.
   */
  @Override
  public double computePrefHeight(double width)
  {
    return computeMinHeight(width);
  }
  
  /// Normal fields.
  
  /**
   * The minimum expected row index.  Used for sizing only: there is no
   * mechanism in place to force cells to stick to this minimum.
   */
  private int minRowIndex;
  
  /**
   * The minimum expected column index.  Used for sizing only: there is no
   * mechanism in place to force cells to stick to this minimum.
   */
  private int minColumnIndex;
  
  /**
   * The maximum expected row index.  Used for sizing only: there is no
   * mechanism in place to force cells to stick to this maximum.
   */
  private int maxRowIndex;
  
  /**
   * The maximum expected column index.  Used for sizing only: there is no
   * mechanism in place to force cells to stick to this maximum.
   */
  private int maxColumnIndex;
  
  /// Constraints for cells (i.e., their grid coordinates)
  
  private static final String ROW_INDEX_CONSTRAINT = "game-map-pane-row";
  private static final String COLUMN_INDEX_CONSTRAINT = "game-map-pane-column";
  
  /**
   * Sets the row index for the child when contained in the game map pane so
   * so that it will be positioned in that row in the grid.
   * If a game map pane child has no row index set, it will be placed in the
   * first row.
   * Setting the value to null will remove the constraint.
   * @param child The child node to set the row index for.
   * @param index The new row index for the child.
   */
  public static void setRowIndex(Node child, Integer index)
  {
    setConstraint(child, ROW_INDEX_CONSTRAINT, index);
  }
  
  /**
   * Returns the child's row index constraint if set.
   * @param child The child to return the row index for.
   * @return The row index for the child or null if the row index is not set.
   */
  public static Integer getRowIndex(Node child) {
    return (Integer) getConstraint(child, ROW_INDEX_CONSTRAINT);
  }
  
  /**
   * Returns the child's row index or 0 if not set.
   * @param child The child to return the row index for.
   * @return The child's row index or 0 if this is not set.
   */
  public static int getNodeRowIndex(Node node)
  {
    Integer rowIndex = getRowIndex(node);
    return (rowIndex != null) ? rowIndex : 0;
  }
  
  /**
   * Sets the column index for the child when contained in the game map pane so
   * so that it will be positioned in that column in the grid.
   * If a game map pane child has no column index set, it will be placed in the
   * first column.
   * Setting the value to null will remove the constraint.
   * @param child The child node to set the column index for.
   * @param index The new column index for the child.
   */
  public static void setColumnIndex(Node child, Integer index)
  {
    setConstraint(child, COLUMN_INDEX_CONSTRAINT, index);
  }
  
  /**
   * Returns the child's column index constraint if set.
   * @param child The child to return the column index for.
   * @return The column index for the child or null if the column index is not
   *         set.
   */
  public static Integer getColumnIndex(Node child) {
    return (Integer) getConstraint(child, COLUMN_INDEX_CONSTRAINT);
  }
  
  /**
   * Returns the child's column index or 0 if not set.
   * @param child The child to return the column index for.
   * @return The child's column index or 0 if this is not set.
   */
  public static int getNodeColumnIndex(Node node)
  {
    Integer columnIndex = getColumnIndex(node);
    return (columnIndex != null) ? columnIndex : 0;
  }
  
  /// CSS-modifiable properties
  
  /**
   * Cell width property.
   */
  public final double getCellWidth()
  { return cellWidth.getValue().doubleValue(); }
  public final void setCellWidth(double cellWidth)
  { this.cellWidth.setValue(cellWidth); } 
  
  private final StyleableProperty<Number> cellWidth =
      FACTORY.createStyleableNumberProperty(this, 
                                            "cellWidth", 
                                            "-cell-width", 
                                            s -> s.cellWidth);
  
  /**
   * Cell height property.
   */
  public final double getCellHeight()
  { return cellHeight.getValue().doubleValue(); }
  public final void setCellHeight(double cellHeight)
  { this.cellHeight.setValue(cellHeight); } 
  
  private final StyleableProperty<Number> cellHeight =
      FACTORY.createStyleableNumberProperty(this, 
                                            "cellHeight", 
                                            "-cell-height", 
                                            s -> s.cellHeight);
  
  /**
   * Grid transform property.
   */
  public final AffineTransform getGridTransform()
  { return insetsToMatrix(gridTransform.getValue()); }
  
  private final StyleableProperty<Insets> gridTransform =
      FACTORY.createStyleableInsetsProperty(this,
                                            "gridTransform",
                                            "-grid-transform",
                                            s -> s.gridTransform);
  
  // Register the CSS metadata for the new properties.
  
  @Override
  public List<CssMetaData<? extends Styleable, ?>> getCssMetaData()
  {
    return getClassCssMetaData();
  }
  
  public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData()
  {
    return FACTORY.getCssMetaData();
  }

  public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData()
  {
    return FACTORY.getCssMetaData();
  }
  
  /// PRIVATE METHODS
  
  /**
   * Converts from an {@link Insets} object to a 2D linear transformation
   * matrix (see {@link AffineTransform}).  Both are given by four double
   * values, but they represent different things.  We use an {@code Insets}
   * property because JavaFX CSS has no built-in support for matrix-valued
   * properties, but we use this method to translate between the two.
   * <pre>
   *     a          / a  b \
   *  d     b  -->  |      |
   *     c          \ c  d /
   * </pre>
   * @param insets The insets to convert into a matrix.
   * @return The matrix with the same coordinates as the insets.
   */
  private static AffineTransform insetsToMatrix(Insets insets)
  {
    double[] flatMatrix = new double[] {
       insets.getTop()   ,  insets.getBottom(),
       insets.getRight() ,  insets.getLeft()
    };
    
    return new AffineTransform(flatMatrix);
  }
  
  /**
   * Sets a constraint on a child node.
   * @param node The node to set the constraint for.
   * @param key The constraint to set.
   * @param value The value to set the constraint to.  If this value is null,
   *              then we remove the constraint.
   *              
   * @see {@link Pane.setConstraint()}, which is identical to this method,
   * but is package private to {@link javafx.scene.layout}.
   */
  static void setConstraint(Node node, Object key, Object value) {
      if (value == null) {
          node.getProperties().remove(key);
      } else {
          node.getProperties().put(key, value);
      }
      if (node.getParent() != null) {
          node.getParent().requestLayout();
      }
  }
  
  /**
   * Gets a constraint on a child node.
   * @param node The node to get the constraint from.
   * @param key The constraint to retrieve.  
   * @return The specified constraint, or null if this constraint has not
   *         been set.
   *         
   * @see {@link Pane.getConstraint()}, which is identical to this method,
   * but is package private to {@link javafx.scene.layout}.
   */
  static Object getConstraint(Node node, Object key) {
      if (node.hasProperties()) {
          Object value = node.getProperties().get(key);
          if (value != null) {
              return value;
          }
      }
      return null;
  }

  /**
   * Gets the position of a node after applying the matrix transform.
   * @param child The child to get the position of.
   * @return The position of the child.
   */
  private Point2D getPosition(Node child)
  {
    AffineTransform gridTransform = getCentredGridTransform();
    int row = getNodeRowIndex(child);
    int column = getNodeColumnIndex(child);
    Point2D coordinates = new Point2D.Double(row, column);
    Point2D newCoordinates = new Point2D.Double();
    gridTransform.transform(coordinates, newCoordinates);
    return newCoordinates;
  }
  
  /**
   * Gets the bounding rectangle for the positions of all the possible
   * row-column pairs that fit within the bounds specified by 
   * get(Min|Max)(Row|Column)Index().  
   */
  private Rectangle.Double getBoundingRectangle()
  {
    // Get the grid coordinates of the points at the four most extreme
    // possible corners of the rectangle.
    Point2D.Double[] extremePoints = new Point2D.Double[] {
        new Point2D.Double(getMinRowIndex(), getMinColumnIndex()),
        new Point2D.Double(getMinRowIndex(), getMaxColumnIndex()),
        new Point2D.Double(getMaxRowIndex(), getMinColumnIndex()),
        new Point2D.Double(getMaxRowIndex(), getMaxColumnIndex())
    };
    
    // Apply the grid transform to the corners of the grid to get the actual
    // positions.
    Point2D.Double[] extremePositions = extremePoints.clone();
    AffineTransform gridTransform = getGridTransform();
    gridTransform.transform(extremePoints, 0, extremePositions, 0, 4);
    
    // Get the maximum and minimum x and y coordinates
    double[] yCoordinates = new double[4], xCoordinates = new double[4];
    Arrays.setAll(xCoordinates, i -> extremePositions[i].getX());
    Arrays.setAll(yCoordinates, i -> extremePositions[i].getY());
    Arrays.sort(xCoordinates);
    Arrays.sort(yCoordinates);
    double minX = xCoordinates[0];
    double maxX = xCoordinates[3];
    double minY = yCoordinates[0];
    double maxY = yCoordinates[3];
    
    return new Rectangle.Double(minX,
                                minY,
                                maxX - minX,
                                maxY - minY);
  }
  
  /**
   * Returns the grid transform, composed with a translation that will place
   * the map in the centre of the region.
   */
  private AffineTransform getCentredGridTransform()
  {
    // Construct a translation that will move the top left corner of the
    // bounding rectangle to point (0, 0).
    Rectangle2D.Double boundingRectangle = getBoundingRectangle();
    AffineTransform centredGridTransform =
        AffineTransform.getTranslateInstance(-boundingRectangle.getX(),
                                             -boundingRectangle.getY());
    
    // Pre-compose this translation with the original grid transform to get the
    // corrected grid transform.
    centredGridTransform.concatenate(getGridTransform());
    
    return centredGridTransform;
  }
  
  /// PUBLIC METHODS

  /**
   * @return the minimum expected row index.
   */
  public int getMinRowIndex()
  {
    return minRowIndex;
  }

  /**
   * @param minRowIndex the minimum expected row index
   */
  public void setMinRowIndex(int minRowIndex)
  {
    this.minRowIndex = minRowIndex;
  }

  /**
   * @return the minimum expected column index.
   */
  public int getMinColumnIndex()
  {
    return minColumnIndex;
  }

  /**
   * @param minColumnIndex the minimum expected column index
   */
  public void setMinColumnIndex(int minColumnIndex)
  {
    this.minColumnIndex = minColumnIndex;
  }

  /**
   * @return the maximum expected row index.
   */
  public int getMaxRowIndex()
  {
    return maxRowIndex;
  }

  /**
   * @param maxRowIndex the maximum expected row index
   */
  public void setMaxRowIndex(int maxRowIndex)
  {
    this.maxRowIndex = maxRowIndex;
  }

  /**
   * @return the maximum expected column index.
   */
  public int getMaxColumnIndex()
  {
    return maxColumnIndex;
  }

  /**
   * @param maxColumnIndex the maximum expected column index
   */
  public void setMaxColumnIndex(int maxColumnIndex)
  {
    this.maxColumnIndex = maxColumnIndex;
  }
}
