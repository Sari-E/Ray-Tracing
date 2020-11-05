package geometries;
import primitives.*;
import java.util.ArrayList;
import static primitives.Util.*;

/**
 * A sphere class
 *
 * @author Sari and Devora
 */

public class Sphere extends RadialGeometry {
	Point3D _center;

	/**
	 * constructor for a new sphere object.
	 *
	 * @param radius, center
	 * @param center  Point3D
	 */

	public Sphere(double radius, Point3D center) {
		super(radius);
		this._center = center;
	}

	/**
	 * constructor for a new sphere object.
	 *
	 * @param radius, center, emmission
	 * @param center  Point3D
	 */

	public Sphere(Color emmission, double radius, Point3D center) {
		super(emmission, radius);
		this._center = center;

	}

	/**
	 * constructor for a new sphere object.
	 *
	 * @param radius, center, material, emmission
	 * @param center  Point3D
	 */

	public Sphere(Material material, Color emmission, double radius, Point3D center) {
		super(material, emmission, radius);
		this._center = center;

	}

	/**
	 * A get normal function
	 *
	 * @param point
	 * @return Vector
	 */

	public Vector getNormal(Point3D point) {
		Vector n = new Vector(point.subtract(_center));
		return n.normalize();
	}

	/**
	 * A get center function
	 *
	 * @return Point3D
	 */

	public Point3D get_center() {
		return _center;
	}

	@Override
	public String toString() {
		return "Sphere [_point=" + _center + "]";
	}

	/**
	 * A findIntersections function
	 *
	 * @param ray
	 * @return ArrayList<GeoPoint>
	 */

	@Override
	public ArrayList<GeoPoint> findIntersections(Ray ray) {
		ArrayList<GeoPoint> intersectionsPoints = null;
		Vector u = new Vector(_center.subtract(ray.get_p0())); // u = O - p0
		double tm = ray.get_dir().dotProduct(u); //// t_m = v * u
		double d = Math.sqrt(u.lengthSquared() - tm * tm); // d = sqrt(|u|^2 - t_m^2)
		double t = (alignZero(_radius - d));
		if ((d < _radius) && t != 0.0 && !((tm < 0) && (u.length() > _radius))) {
			double th = Math.sqrt(_radius * _radius - d * d); //// th = sqrt(r^2 - d^2)
			double t1 = tm + th;
			Point3D p1 = new Point3D(ray.getPoint(t1));
			if (!p1.equals(ray.get_p0())) {
				intersectionsPoints = new ArrayList<GeoPoint>();
				intersectionsPoints.add(new GeoPoint(this, p1));
			}
			if (!isZero(tm) && !(tm < th)) {
				double t2 = tm - th;
				Point3D p2 = new Point3D(ray.getPoint(t2));
				if (!p2.equals(ray.get_p0())) {
					if (intersectionsPoints.isEmpty()) {
						intersectionsPoints = new ArrayList<GeoPoint>();
					}
					intersectionsPoints.add(new GeoPoint(this, p2));
				}
			}
		}
		return intersectionsPoints;
	}
}
