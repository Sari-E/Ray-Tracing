/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import primitives.*;
import geometries.*;

/**
 * @author Devora & Sari
 *
 */
public class CylinderTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here - checked point is on one of the
		// bases
		Cylinder cl = new Cylinder(1, new Ray(new Point3D(0, 0, 0), new Vector(0, 1, 0)), 1);
		assertEquals("Bad normal to Cylinder", new Vector(0, 1, 0), cl.getNormal(new Point3D(0, 0, 0.5)));

		// TC02: There is a simple single test here - checked point is on one the side
		assertEquals("Bad normal to Cylinder", new Vector(1, 0, 0), cl.getNormal(new Point3D(1, 0.5, 0)));
	}

}
