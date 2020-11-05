/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import geometries.Intersectable.GeoPoint;
import org.junit.Test;
import primitives.*;
import geometries.*;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Devora & Sari
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Plane pl = new Plane(new Point3D(0, 0, 1), new Point3D(0, 0, 0), new Point3D(1, 0, 0));
		assertEquals("Bad normal to Plane", new Vector(0, 1, 0), pl.getNormal(new Point3D(0, 0, 0)));
	}

	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Plane plane = new Plane(new Vector(0, 1, 0), new Point3D(1, 0, 0));

		// ============ Equivalence Partitions Tests ==============
		// **** Group: Ray is not orthogonal \ parallel

		// TC01: Ray intersects the plane (1 points)
		Point3D p = new Point3D(1, 0, 0);
		List<GeoPoint> result = plane.findIntersections(new Ray(new Point3D(0, -1, 0), new Vector(1, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray's line goes through the plane", Arrays.asList(p), result);

		// TC02: Ray doesn't intersect the plane (0 points)
		assertEquals("Wrong number of points", null,
				plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 1, 0))));

		// =============== Boundary Values Tests ==================

		// **** Group: Ray is parallel to the plane
		// TC03: Ray is included in the plane (1 points)
		p = new Point3D(1, 0, 0);
		result = plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 1)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray's line goes through the plane", Arrays.asList(p), result);

		// TC04: Ray is not included in the plane (0 points)
		assertEquals("Wrong number of points", null,
				plane.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0, 0))));

		// **** Group: Ray is orthogonal to the plane
		// TC05: Ray starts after the plane(0 points)

		assertEquals("Wrong number of points", null,
				plane.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(0, 1, 0))));

		// TC06: Ray starts in the plane(0 points)
		assertEquals("Wrong number of points", null,
				plane.findIntersections(new Ray(new Point3D(1, 0, 1), new Vector(0, 1, 0))));

		// TC07: Ray starts before the plane(1 points)
		p = new Point3D(0, 0, 0);
		result = plane.findIntersections(new Ray(new Point3D(0, -1, 0), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray's line goes through the plane", Arrays.asList(p), result);

		// TC08: Ray starts at the plane(0 points)
		assertEquals("Wrong number of points", null,
				plane.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 1))));

		// TC09: Ray starts after the plane(0 points)

		assertEquals("Wrong number of points", null,
				plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 1))));

	}

}
