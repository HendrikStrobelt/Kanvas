package jkanvas.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Utility methods for painting.
 * 
 * @author Joschi <josua.krause@googlemail.com>
 */
public final class PaintUtil {

  /** Hidden default constructor. */
  private PaintUtil() {
    throw new AssertionError();
  }

  /**
   * Creates a rectangle to draw a point.
   * 
   * @param pos The point.
   * @param size The size of the point.
   * @return The rectangle.
   */
  public static Rectangle2D pixel(final Point2D pos, final double size) {
    final double s2 = size * 0.5;
    return new Rectangle2D.Double(pos.getX() - s2, pos.getY() - s2, size, size);
  }

  /**
   * Creates a rectangle to draw a point.
   * 
   * @param pos The point.
   * @return The rectangle.
   */
  public static Rectangle2D pixel(final Point2D pos) {
    return pixel(pos, 1);
  }

  /**
   * Adds padding to a rectangle.
   * 
   * @param rect The rectangle.
   * @param padding The padding.
   * @return The new rectangle.
   */
  public static Rectangle2D addPadding(final Rectangle2D rect, final double padding) {
    final double p2 = padding * 2;
    return new Rectangle2D.Double(rect.getX() - padding, rect.getY() - padding,
        rect.getWidth() + p2, rect.getHeight() + p2);
  }

  /**
   * Scales a rectangle so that the center point stays the same.
   * 
   * @param rect The rectangle to scale.
   * @param scale The scaling factor.
   * @return The scaled rectangle.
   */
  public static Rectangle2D scaleCenter(final Rectangle2D rect, final double scale) {
    final double w = rect.getWidth() * scale;
    final double h = rect.getHeight() * scale;
    return new Rectangle2D.Double(rect.getCenterX() - w * 0.5, rect.getCenterY() - h
        * 0.5, w, h);
  }

  /**
   * Fits a rectangle into the given rectangle. The aspect ratio of the
   * resulting rectangle is given by <code>w</code> and <code>h</code>.
   * 
   * @param rect The rectangle.
   * @param w The width for defining the aspect ratio.
   * @param h The height for defining the aspect ratio.
   * @return A rectangle with the given aspect ratio that fits into the given
   *         rectangle.
   */
  public static Rectangle2D fitInto(final Rectangle2D rect, final double w, final double h) {
    final double rw = rect.getWidth();
    final double rh = rect.getHeight();
    final double verWidth = w * rh / h;
    final boolean vertical = verWidth <= rw;
    final double px;
    final double py;
    final double nw;
    final double nh;
    if(vertical) {
      nw = verWidth;
      nh = rh;
      px = (rw - nw) * 0.5;
      py = 0;
    } else {
      nw = rw;
      nh = h * rw / w;
      px = 0;
      py = (rh - nh) * 0.5;
    }
    return new Rectangle2D.Double(rect.getX() + px, rect.getY() + py, nw, nh);
  }

  /**
   * Sets the alpha value of a color. Hint: Consider using
   * <code>AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha)</code> and
   * passing it via {@link Graphics2D#setComposite(java.awt.Composite)}.
   * 
   * @param col The color.
   * @param alpha The new alpha value.
   * @return The color with transparency.
   */
  public static Color setAlpha(final Color col, final double alpha) {
    final float[] comp = col.getRGBComponents(null);
    return new Color(comp[0], comp[1], comp[2], (float) alpha);
  }

  /**
   * Multiplies the alpha value of a color. Hint: Consider using
   * <code>AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha)</code> and
   * passing it via {@link Graphics2D#setComposite(java.awt.Composite)}.
   * 
   * @param col The color.
   * @param alpha The alpha value to multiply to the previous value.
   * @return The color with new transparency.
   */
  public static Color mulAlpha(final Color col, final double alpha) {
    final float[] comp = col.getRGBComponents(null);
    return new Color(comp[0], comp[1], comp[2], (float) (alpha * comp[3]));
  }

  /**
   * Creates a color with transparency. Hint: Consider using
   * <code>AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha)</code> and
   * passing it via {@link Graphics2D#setComposite(java.awt.Composite)}.
   * 
   * @param h The hue value. <code>1</code> represents <code>360</code> degrees.
   * @param s The saturation value.
   * @param b The brightness value.
   * @param alpha The alpha value.
   * @return The color.
   */
  public static Color hsbaColor(final double h, final double s, final double b,
      final double alpha) {
    return setAlpha(Color.getHSBColor((float) h, (float) s, (float) b), alpha);
  }

}