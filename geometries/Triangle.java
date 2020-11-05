package geometries;

import java.util.ArrayList;

import primitives.*;

/**
 * A Triangle class
 * 
 * @author Sari and Devora
 */

public class Triangle extends Polygon {
	/**
	 * constructor for a new Triangle object
	 *
	 * @param _x
	 * @param _y
	 * @param _z
	 */

	public Triangle(Point3D _x, Point3D _y, Point3D _z) {
		super(new Point3D[] { _x, _y, _z });
	}

	/**
	 * constructor for a new Triangle object
	 *
	 * @param Color emmission
	 * @param _x
	 * @param _y
	 * @param _z
	 */

	public Triangle(Color emmission, Point3D _x, Point3D _y, Point3D _z) {
		super(emmission, new Point3D[] { _x, _y, _z });
	}

	/**
	 * constructor for a new Triangle object
	 *
	 * @param _x
	 * @param _y
	 * @param _z
	 */

	public Triangle(Material material, Color emmission, Point3D _x, Point3D _y, Point3D _z) {
		super(material, emmission, new Point3D[] { _x, _y, _z });
	}

	@Override
	public String toString() {
		return "Triangle [_vertices=" + _vertices + ", _plane=" + _plane + "]";
	}

	/**
	 * A findIntersections function
	 * 
	 * @param ray
	 * @return ArrayList<GeoPoint>
	 */

	public ArrayList<GeoPoint> findIntersections(Ray ray) {
		ArrayList<GeoPoint> intersection = new ArrayList<GeoPoint>();
		intersection = super.findIntersections(ray);
		if (intersection != null) // to change the geometry type to this
			for (GeoPoint k : intersection)
				k.geometry = this;
		return intersection;
	}

}
