/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import geometries.Intersectable.GeoPoint;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import geometries.*;
import primitives.*;


/**
 * @author Devora & Sari
 *
 */
public class TriangleTests {

	/**
	 * Test method for {@link geometries.Traingle#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {

		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Triangle pl = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 0, 0));
		assertEquals("Bad normal to Plane", new Vector(0, -1, 0), pl.getNormal(new Point3D(0, 1, 0)));
	}

	/**
	 * Test method for
	 * {@link geometries.Traingle#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Triangle triangle = new Triangle(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 0, 1));
		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray intersects the plane (1 points)

		Point3D p = new Point3D(0.25, 0, 0.25);
		List<GeoPoint> result = triangle.findIntersections(new Ray(new Point3D(0.25, -1, 0.25), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses traingle", Arrays.asList(p), result);

		// TC02: Ray doesn't intersect the plane - outside against edge (0 points)
		assertEquals("Wrong number of points", null,
				triangle.findIntersections(new Ray(new Point3D(2, -1, 2), new Vector(0, 1, 0))));

		// TC03: Ray intersects the plane - outside against vertex (0 points)
		assertEquals("Wrong number of points", null,
				triangle.findIntersections(new Ray(new Point3D(-1, -1, -1), new Vector(0, 1, 0))));

		// =============== Boundary Values Tests ==================

		// **** Group: Ray begins "before" the plane
		// TC04: Ray hits on edge (0 points)
		assertEquals("Wrong number of points", null,
				triangle.findIntersections(new Ray(new Point3D(0.5, -1, 0), new Vector(0, 1, 0))));

		// TC04: Ray hits in vertex (0 points)
		assertEquals("Wrong number of points", null,
				triangle.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, 1, 0))));

		// TC04: Ray hits on edges continuation (0 points)
		assertEquals("Wrong number of points", null,
				triangle.findIntersections(new Ray(new Point3D(2, -1, 0), new Vector(0, 1, 0))));

	}

}
