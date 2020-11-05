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
 * Testing Polygons
 * @author Dan
 *
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(0, 0, 0), new Point3D(1, 0, 0),
                new Point3D(1,0 ,1 ));
        assertEquals("Bad normal to trinagle", new Vector(0, 1,0 ), pl.getNormal(new Point3D(0, 0, 0)));
    }
    public void testFindIntersections() {
    	Polygon polygon = new Polygon(new Point3D(-1, 0, 0), new Point3D(0, 0, -1),new Point3D(1, 0, 0), new Point3D(0, 0, 1));
        // ============ Equivalence Partitions Tests ==============
    	
        // TC01: Ray intersects the polygon (1 points)
    	Point3D p = new Point3D(0.25, 0, 0.25);
    	List<GeoPoint> result = polygon.findIntersections(new Ray(new Point3D(0.25, -1, 0.25), new Vector(0, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses traingle", Arrays.asList(p), result);
        
     // TC02: Ray doesn't intersect the polygon - outside against edge (0 points)
        assertEquals("Wrong number of points", null, polygon.findIntersections(new Ray(new Point3D(1, -1, 1), new Vector(0, 1, 0))));

        // TC03: Ray intersects the polygon - outside against vertex (0 points)
        assertEquals("Wrong number of points", null, polygon.findIntersections(new Ray(new Point3D(5, -1, 0), new Vector(0, 1, 0))));


    	
        // =============== Boundary Values Tests ==================

        // **** Group: Ray begins "before" the plane
        // TC04: Ray hits on edge (0 points)
		assertEquals("Wrong number of points", null,
				polygon.findIntersections(new Ray(new Point3D(0.5, -1, 0.5), new Vector(0, 1, 0))));

        // TC04: Ray hits in vertex (0 points)
        assertEquals("Wrong number of points", null, polygon.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, 1, 0))));


        // TC04: Ray hits on edges continuation (0 points)
        assertEquals("Wrong number of points", null, polygon.findIntersections(new Ray(new Point3D(3, -1, -2), new Vector(0, 1, 0))));
       
        
    }

}
