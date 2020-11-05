/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import geometries.*;
import primitives.*;
import geometries.Intersectable.GeoPoint;
/**
 * @author Sari
 *
 */

public class GeometriesTests {
	/**
	 * Test method for
	 * {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Triangle traingle = new Triangle(new Point3D(-1, -1, -1), new Point3D(-1, -1, 1), new Point3D(-1, 1, 0));
		Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));
		Plane plane = new Plane(new Vector(1, 0, 0), new Point3D(3, 0, 0));

		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray intersect some of the geometries (3 points)
		Geometries geometry = new Geometries(sphere, traingle, plane);
		List<GeoPoint> result = geometry.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 0, 0)));
		assertEquals("Wrong number of points", 3, result.size());

		// =============== Boundary Values Tests ==================
		// TC11: List of geometries is empty (0 points)
		geometry = new Geometries();
		assertEquals("Wrong number of points", null,
				geometry.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 0, 0))));

		// TC12: Ray doesn't any of the geometries (0 points)
		geometry = new Geometries(sphere, traingle, plane);
		assertEquals("Wrong number of points", null,
				geometry.findIntersections(new Ray(new Point3D(10, 0, 0), new Vector(1, 0, 0))));

		// TC13: Ray intersects one geometry (1 points)
		result = geometry.findIntersections(new Ray(new Point3D(2.5, 0, 0), new Vector(1, 0, 0)));
		assertEquals("Wrong number of points", 1, result.size());

		// TC13: Ray intersects all the geometries (4 points)
		result = geometry.findIntersections(new Ray(new Point3D(-2, 0, 0), new Vector(1, 0, 0)));
		assertEquals("Wrong number of points", 4, result.size());

	}

}
