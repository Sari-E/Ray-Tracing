/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import geometries.Intersectable.GeoPoint;
import geometries.*;
import primitives.*;
import org.junit.Test;
import java.util.List;
import java.util.Arrays;

/**
 * @author Devora & Sari
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Sphere sl = new Sphere(1, new Point3D(0, 0, 0));
		assertEquals("Bad normal to Sphere", new Vector(1, 0, 0), sl.getNormal(new Point3D(1, 0, 0)));
	}

	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray's line is outside the sphere (0 points)
		assertEquals("Ray's line out of sphere", null,
				sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

		// TC02: Ray starts before and crosses the sphere (2 points)
		Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
		Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
		List<GeoPoint> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getPoint().get_x().get() > result.get(1).getPoint().get_x().get())
			result = Arrays.asList(result.get(1), result.get(0));
		assertEquals("Ray crosses sphere", Arrays.asList(p1, p2), result);

		// TC03: Ray starts inside the sphere (1 point)
		Point3D p = new Point3D(2, 0, 0);
		result = sphere.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere", Arrays.asList(p), result);

		// TC04: Ray starts after the sphere (0 points)
		assertEquals("Ray's line out of sphere", null,
				sphere.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(1, 0, 0))));

		// =============== Boundary Values Tests ==================

		// **** Group: Ray's line crosses the sphere (but not the center)
		// TC11: Ray starts at sphere and goes inside (1 points)
		p = new Point3D(1, 0, 1);
		result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, 0, 1)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere", Arrays.asList(p), result);

		// TC12: Ray starts at sphere and goes outside (0 points)
		assertEquals("Ray's line out of sphere", null,
				sphere.findIntersections(new Ray(new Point3D(1, 0, 1), new Vector(-1, 0, 0))));

		// **** Group: Ray's line goes through the center
		// TC13: Ray starts before the sphere (2 points)
		p1 = new Point3D(0, 0, 0);
		p2 = new Point3D(2, 0, 0);
		result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 0, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getPoint().get_x().get() > result.get(1).getPoint().get_x().get())
			result = Arrays.asList(result.get(1), result.get(0));
		assertEquals("Ray crosses sphere", Arrays.asList(p1, p2), result);
		// TC14: Ray starts at sphere and goes inside (1 points)
		p = new Point3D(0, 0, 0);
		result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, 0, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere", Arrays.asList(p), result);
		// TC15: Ray starts inside (1 points)
		p = new Point3D(2, 0, 0);
		result = sphere.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere", Arrays.asList(p), result);
		// TC16: Ray starts at the center (1 points)
		p = new Point3D(2, 0, 0);
		result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere", Arrays.asList(p), result);
		// TC17: Ray starts at sphere and goes outside (0 points)
		assertEquals("Ray's line out of sphere", null,
				sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0))));

		// TC18: Ray starts after sphere (0 points)
		assertEquals("Ray's line out of sphere", null,
				sphere.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(1, 0, 0))));

		// **** Group: Ray's line is tangent to the sphere (all tests 0 points)
		// TC19: Ray starts before the tangent point
		assertEquals("Ray's line out of sphere", null,
				sphere.findIntersections(new Ray(new Point3D(0, -1, 0), new Vector(0, 1, 0))));

		// TC20: Ray starts at the tangent point
		assertEquals("Ray's line out of sphere", null,
				sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(0, 1, 0))));

		// TC21: Ray starts after the tangent point
		assertEquals("Ray's line out of sphere", null,
				sphere.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(0, 1, 0))));

		// **** Group: Special cases
		// TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's
		// center line
		assertEquals("Ray's line out of sphere", null,
				sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(0, 1, 0))));
	}
}