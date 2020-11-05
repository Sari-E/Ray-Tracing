/**
 * 
 */
package elements;

import primitives.*;

/**
 * class PointLight - for light that has position and Attenuation
 * 
 * @author Devora and Sari
 *
 */
public class PointLight extends Light implements LightSource {
	protected Point3D _position;
	protected double _kC, _kL, _kQ;

	/**
	 * Ctor with params
	 * 
	 * @param _intensity
	 * @param _position
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 */
	public PointLight(Color _intensity, Point3D _position, double _kC, double _kL, double _kQ) {
		super(_intensity);
		this._position = _position;
		this._kC = _kC;
		this._kL = _kL;
		this._kQ = _kQ;
	}

	/**
	 * 
	 * Getter
	 * 
	 * @param p 
	 * @return Color
	 */
	public Color getIntensity(Point3D p) {
		double d = _position.distance(p);
		return _intensity.scale(1 / (_kC + _kL * d + _kQ * d * d));
	}

	/**
	 * Getter
	 * 
	 * @param p
	 * @return Vector
	 * 
	 */
	public Vector getL(Point3D p) {
		return p.subtract(_position).normalize();
	}

	/**
	 * Getter
	 * 
	 * @param point
	 * 
	 * @return double
	 */
	public double getDistance(Point3D point) {
		return _position.distance(point);
	}
}
