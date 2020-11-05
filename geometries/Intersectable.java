package geometries;

import primitives.*;
import java.util.*;

/**
 * Interface that contains findIntersections function and a class that helps to
 * get the color of a point
 */

public interface Intersectable {
	ArrayList<GeoPoint> findIntersections(Ray ray);

	/**
	 * class that contains fields for the points on geometries
	 */
	public static class GeoPoint {
		public Geometry geometry;
		public Point3D point;

		/**
		 * C-tor with parameters
		 * 
		 * @param Geometry _geometry, Point3D _point
		 */
		public GeoPoint(Geometry _geometry, Point3D _point) {
			geometry = _geometry;
			point = _point;
		}

		/**
		 * Getter function
		 * 
		 * @return point
		 */
		public Point3D getPoint() {
			return point;
		}

		/**
		 * Getter function
		 * 
		 * @return Geometry
		 */
		public Geometry getGeometry() {
			return geometry;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (geometry == null) {
				if (other.geometry != null)
					return false;
			} else if (!geometry.equals(other.geometry))
				return false;
			if (point == null) {
				if (other.point != null)
					return false;
			} else if (!point.equals(other.point))
				return false;
			return true;
		}

	}

}
