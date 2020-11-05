/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import primitives.*;
import org.junit.Test;
import static primitives.Util.isZero;

/**
 * @author Sari & Devora
 *
 */
public class VectorTests {

	Vector v1 = new Vector(1, 2, 3);
	Vector v2 = new Vector(-2, -4, -6);
	Vector v3 = new Vector(0, 3, -2);

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {

		// ============ Equivalence Partitions Tests ==============

		Vector vr = v1.crossProduct(v3);

		// Test that length of cross-product is proper (orthogonal vectors taken for
		// simplicity)
		assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

		// Test cross-product result orthogonality to its operands
		assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
		assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

		// =============== Boundary Values Tests ==================
		// test zero vector from cross-productof co-lined vectors
		try {
			v1.crossProduct(v2);
			fail("crossProduct() for parallel vectors does not throw an exception");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Point3D p1 = new Point3D(1, 2, 3);
		if (!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
			System.out.println("ERROR: Point + Vector does not work correctly");
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		Point3D p1 = new Point3D(1, 2, 3);
		if (!new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1)))
			System.out.println("ERROR: Point - Point does not work correctly");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		// test Dot-Product
		if (!isZero(v1.dotProduct(v3)))
			System.out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
		if (!isZero(v1.dotProduct(v2) + 28))
			System.out.println("ERROR: dotProduct() wrong value");
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		// test length..
		if (!isZero(v1.lengthSquared() - 14))
			System.out.println("ERROR: lengthSquared() wrong value");
		if (!isZero(new Vector(0, 3, 4).length() - 5))
			System.out.println("ERROR: length() wrong value");

	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		Vector v = new Vector(1, 2, 3);
		Vector vCopy = new Vector(v);
		Vector vCopyNormalize = vCopy.normalize();
		if (vCopy != vCopyNormalize)
			System.out.println("ERROR: normalize() function creates a new vector");
		if (!isZero(vCopyNormalize.length() - 1))
			System.out.println("ERROR: normalize() result is not a unit vector");
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v = new Vector(1, 2, 3);
		Vector u = v.normalized();
		if (u == v)
			System.out.println("ERROR: normalizated() function does not create a new vector");
	}

}
