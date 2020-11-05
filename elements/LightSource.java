
package elements;

import primitives.*;

/**
 * interface LightSource - for lights that has variable intensity in different
 * points
 * 
 * @author Sari & Devora
 *
 */
public interface LightSource {
	/**
	 * A function that returns the intensity in a point
	 * 
	 * @param p 
	 * @return Color
	 */
	public Color getIntensity(Point3D p);

	/**
	 * A function that returns the light vector in a point
	 * 
	 * @param p 
	 * @return Vector
	 * 
	 */
	public Vector getL(Point3D p);
	/**
	 * A function that returns the light vector in a point
	 * 
	 * @param point 
	 * @return double
	 * 
	 */
	public double getDistance(Point3D point);
}
