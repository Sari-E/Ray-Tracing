package geometries;

import java.util.ArrayList;
import primitives.*;

/**
 * Cylinder class
 * 
 * @author Sari and Devora
 */

public class Cylinder extends Tube {
	double _height;

	/**
	 * constructor for a new Cylinder object
	 *
	 * @param radius  double
	 * @param axisRay Ray
	 * @param height  double
	 */

	public Cylinder(double radius, Ray axisRay, double height) {
		super(radius, axisRay);
		this._height = height;
	}
	
	/**
	 * constructor for a new Cylinder object
	 *
	 * @param radius  double
	 * @param axisRay Ray
	 * @param height  double
	 */

	public Cylinder(Color emmission, double radius, Ray axisRay, double height) {
		super(emmission, radius, axisRay);
		this._height = height;

	}
	/**
	 * constructor for a new Cylinder object
	 *
	 * @param radius  double
	 * @param axisRay Ray
	 * @param height  double
	 */

	public Cylinder(Material material, Color emmission, double radius, Ray axisRay, double height) {
		super(material,emmission, radius, axisRay);
		this._height = height;

	}

	/**
	 * a get height function
	 * 
	 * @return _height
	 */

	public double get_height() {
		return _height;
	}

	@Override
	public String toString() {
		return "Cylinder [_height=" + _height + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cylinder other = (Cylinder) obj;
		if (Double.doubleToLongBits(_height) != Double.doubleToLongBits(other._height))
			return false;
		return true;
	}

	/**
	 * A get normal function
	 *
	 * @param p point3D
	 * @return Vector, the normal to this cylinder in a given point
	 */

	@Override
	public Vector getNormal(Point3D p) {

		Point3D o1 = new Point3D(_axisRay.get_p0());
		Point3D o2 = new Point3D(o1.add(_axisRay.get_dir().scale(_height)));

		if (p.distance(o1) <= this._radius || p.distance(o2) <= this._radius) {
			return _axisRay.get_dir();
		} else {
			return super.getNormal(p);
		}

	}

	/**
	 * @param ray
	 * @return ArrayList<GeoPoint> 
	 */

	@Override
	public ArrayList<GeoPoint> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
