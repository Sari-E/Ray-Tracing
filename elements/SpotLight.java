/**
 * 
 */
package elements;

import primitives.*;

/**
 * class SpotLight - for light that has position, direction and Attenuation
 * 
 * @author Devora and Sari
 *
 */
public class SpotLight extends PointLight {
	private Vector _direction;

	/**
	 * C-tor with params
	 * 
	 * @param intensity
	 * @param position
	 * @param _direction
	 * @param kC
	 * @param kL
	 * @param kQ
	 * 
	 */

	public SpotLight(Color intensity, Point3D position, Vector _direction, double kC, double kL, double kQ) {
		super(intensity, position, kC, kL, kQ);
		this._direction = _direction.normalize();
	}

	/**
	 * Getter
	 * 
	 * @param p
	 * @return Color
	 */
	public Color getIntensity(Point3D p) {

		Color mechane = super.getIntensity(p);
		double t = Math.max(0, _direction.dotProduct(this.getL(p)));
		Color temp = mechane.scale(t);

		return temp;
	}

	/**
	 * Getter
	 * 
	 * @param point
	 */
	public double getDistance(Point3D point) {
		// return _position.distance(point);
		return super.getDistance(point);
	}

}
