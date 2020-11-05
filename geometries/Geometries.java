/**
 * class Geometry  - list of geometries
 */
package geometries;

import java.util.*;

import primitives.Ray;

/**
 * @author Sari & Devora
 *
 */
public class Geometries implements Intersectable {
	List<Geometry> _geometries;

	/**
	 * defult constractor
	 */
	public Geometries() {
		super();
		this._geometries = new ArrayList<Geometry>();
	}

	/**
	 * constractor
	 * 
	 * @param geometries list
	 */
	public Geometries(Geometry... geometries) {
		super();

		_geometries = Arrays.asList(geometries);

	}

	/**
	 * A adding function (append lists)
	 * 
	 * @param geometries list
	 */
	public void add(Geometry... geometries) {
		_geometries.addAll(Arrays.asList(geometries));
	}

	/**
	 * A function that find intersection points between the ray and the geometries
	 * 
	 * @param ray Ray
	 * @return ArrayList<GeoPoint>
	 */
	public ArrayList<GeoPoint> findIntersections(Ray ray) {
		ArrayList<GeoPoint> intersections = new ArrayList<GeoPoint>();
		List<GeoPoint> temp = new ArrayList<GeoPoint>();
		for (Geometry i : _geometries) {
			temp = i.findIntersections(ray);
			if (temp != null)
				intersections.addAll(temp);

		}
		if (intersections.isEmpty())
			return null;
		return intersections;
	}

}
