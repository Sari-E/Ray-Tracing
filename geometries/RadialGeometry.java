package geometries;
import primitives.*;

/**
 * @author Sari and Devora RadialGeometry is an abstract class that defines all
 *         radial geometries.
 */

public abstract class RadialGeometry extends Geometry {
	double _radius;

	/**
	 * Copy constructor RadialGeometry object
	 * 
	 * @param other RadialGeometry
	 */

	public RadialGeometry(RadialGeometry other) {
		super();
		this._radius = other._radius;
	}

	/**
	 * constructor RadialGeometry object
	 * 
	 * @param radius double
	 */

	public RadialGeometry(double radius) {
		super();
		this._radius = radius;
	}
	/**
	 * constructor RadialGeometry object
	 * 
	 * @param radius double
	 */

	public RadialGeometry(Color emmission, double radius) {
		super(emmission);
		this._radius = radius;
	}
	/**
	 * constructor RadialGeometry object
	 * 
	 * @param radius double
	 */

	public RadialGeometry(Material material, Color emmission, double radius) {
		super(material, emmission);
		this._radius = radius;
	}

	/**
	 * A get radius
	 * 
	 * @return double
	 */

	public double get_radius() {
		return _radius;
	}

	@Override
	public String toString() {
		return "RadialGeometry [_radius=" + _radius + "]";
	}

}
