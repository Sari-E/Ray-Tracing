/**
 * 
 */
package elements;

import primitives.*;

/**
 * class DirectionalLight - light that is far away, has direction
 * 
 * @author Devora & Sari
 *
 */
public class DirectionalLight extends Light implements LightSource {
	private Vector _direction;

	/**
	 * C-tor with parameters	 * 
	 * @param Color _intensity, Vector _direction	 *
	 */
	public DirectionalLight(Color _intensity, Vector _direction) {
		super(_intensity);
		this._direction = _direction.normalize();
	}

	/**
	 * Getter
	 * 
	 * @param Point3D p
	 * @param Color
	 */
	public Color getIntensity(Point3D p) {
		return _intensity;
	}

	/**
	 * Getter
	 * 
	 * @param Point3D p
	 * @param Vector
	 */
	public Vector getL(Point3D p) {
		return _direction;
	}
	/**
	 * Getter
	 * 
	 * @param Point3D p
	 * @param Vector
	 */
	public double getDistance(Point3D point)
	{
		return Double.POSITIVE_INFINITY;
	}
}
