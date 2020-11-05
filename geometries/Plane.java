package geometries;

import java.util.ArrayList;

import primitives.*;
import static primitives.Util.*;

/**
 * Plane class
 * 
 * @author Sari and Devora
 */

public class Plane extends Geometry {
	Vector _normal;
	Point3D _q;

	/**
	 * constructor for a new Plane object
	 *
	 * @param normal Vector
	 * @param q      Point3D
	 */

	public Plane(Vector normal, Point3D q) {
		super();
		this._normal = normal.normalize();
		this._q = q;
	}

	/**
	 * constructor for a new Plane object
	 *
	 * @param normal Vector
	 * @param q      Point3D
	 */

	public Plane(Color emmission, Vector normal, Point3D q) {
		super(emmission);
		this._normal = normal.normalize();
		this._q = q;
	}

	/**
	 * constructor for a new Plane object
	 *
	 * @param normal Vector
	 * @param q      Point3D
	 */

	public Plane(Material material, Color emmission, Vector normal, Point3D q) {
		super(material, emmission);
		this._normal = normal.normalize();
		this._q = q;
	}

	/**
	 * constructor for a new Plane object
	 * 
	 * @paramColor emmission
	 * @param u Point3D
	 * @param v Point3D
	 * @param w Point3D
	 */

	public Plane(Color emmission, Point3D u, Point3D v, Point3D w) {
		this(u, v, w);
		this._emmission = emmission;
	}

	/**
	 * constructor for a new Plane object
	 *
	 * @param u Point3D
	 * @param v Point3D
	 * @param w Point3D
	 */

	public Plane(Point3D u, Point3D v, Point3D w) {
		super();
		Vector vector1 = new Vector(u.subtract(v)).normalize();
		Vector vector2 = new Vector(w.subtract(v)).normalize();
		this._normal = new Vector(vector1.crossProduct(vector2)).normalize();
		this._q = u;
	}

	/**
	 * A get normal (the vector of the plane) function
	 * 
	 * @return Vector
	 */

	public Vector get_normal() {
		return _normal;
	}

	/**
	 * A get q function
	 * 
	 * @return Point3D
	 */

	public Point3D get_q() {
		return _q;
	}

	/**
	 * Getter
	 * 
	 * @param _point
	 * @return Vector, the normal to this plane in a given point p
	 */

	public Vector getNormal(Point3D _point) {
		return _normal;
	}

	/**
	 *
	 * function that checks if a point is on the geometry
	 * 
	 * @param Ray ray
	 * @return boolean
	 */
	public boolean isIncluded(Ray ray) {
		return _normal.dotProduct(new Vector(_q)) == _normal.dotProduct(new Vector(ray.get_p0()));
	}

	@Override
	public String toString() {
		return "Plane [_normal=" + _normal + ", _q=" + _q + "]";
	}

	/**
	 * a func that finds the intersections with the plane
	 * 
	 * @param ray
	 * @return ArrayList<GeoPoint>
	 */

	@Override
	public ArrayList<GeoPoint> findIntersections(Ray ray) {
		ArrayList<GeoPoint> intersectionsPoints = null;
		double numerator = _normal.dotProduct(_q.subtract(ray.get_p0())); // N * (q0 - p0)
		double denominator = _normal.dotProduct(ray.get_dir()); // N*v

		if (alignZero(ray.get_dir().dotProduct(_normal)) == 0 && isIncluded(ray)) {

			Point3D p = new Point3D(_q);
			intersectionsPoints = new ArrayList<GeoPoint>();
			intersectionsPoints.add(new GeoPoint(this, p));
		}
		if ((alignZero(denominator)) != 0) {

			double t = alignZero(numerator / denominator); // (N * (q0 - p0)) / (N*v)
			if (t > 0) {
				Point3D p = new Point3D(ray.getPoint(t)); // p = p0 + t*v

				intersectionsPoints = new ArrayList<GeoPoint>();
				intersectionsPoints.add(new GeoPoint(this, p));
			}
		}
		return intersectionsPoints;
	}
}
