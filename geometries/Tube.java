package geometries;

import java.util.ArrayList;
import primitives.*;

/**
 * Tube class represents a round geometry with no edges system
 * 
 * @author Devora & Sari
 */
public class Tube extends RadialGeometry {
	Ray _axisRay;

	/**
	 * C-tor with parameters
	 * 
	 * @param double radius, Ray ray
	 */
	public Tube(double radius, Ray ray) {
		super(radius);
		this._axisRay = ray;
	}

	/**
	 * C-tor with parameters
	 * 
	 * @param Color emmission, double radius, Ray ray
	 */
	public Tube(Color emmission, double radius, Ray ray) {
		super(emmission, radius);
		this._axisRay = ray;
	}

	/**
	 * C-tor with parameters
	 * 
	 * @param Material material, Color emmission, double radius, Ray ray
	 */
	public Tube(Material material, Color emmission, double radius, Ray ray) {
		super(material, emmission, radius);
		this._axisRay = ray;
	}

	/**
	 * Getter
	 * 
	 * @return Ray
	 */
	public Ray get_axisRay() {
		return _axisRay;
	}

	@Override
	public String toString() {
		return "Tube [ray=" + _axisRay + "]";
	}

	/**
	 * A function that returns the normal at point on the geometry
	 * 
	 * @param Point3D
	 * @return Vector
	 */
	@Override
	public Vector getNormal(Point3D p) {
		// n = normalize(P - O)
		// O is projection of P on cylinder's ray:
		// t = v (P – P0)
		// O = P0 + tv

		Point3D p0 = this._axisRay.get_p0();
		Vector v = this._axisRay.get_dir();
		// t = v (P – P0)
		double t = p.subtract(p0).dotProduct(v);
		// O = P0 + tv
		Point3D o = null;
		if (!Util.isZero(t))// if it's close to 0, we'll get ZERO vector exception
			o = p0.add(v.scale(t));
		Vector n = new Vector(p.subtract(o).normalize());
		return n;

	}

	/**
	 * A findIntersections function
	 * 
	 * @param ray
	 * @return ArrayList<GeoPoint>
	 */
	@Override
	public ArrayList<GeoPoint> findIntersections(Ray ray) {
		return null;
	}
}
