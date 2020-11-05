/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import primitives.*;
import geometries.*;
import org.junit.Test;

/**
 * @author Devora & Sari
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Tube tl = new Tube(1, new Ray(new Point3D(0, 0, 0), new Vector(0, 1, 0)));
		assertEquals("Bad normal to Tube", new Vector(1, 0, 0), tl.getNormal(new Point3D(1, 1, 0)));
	}
}
