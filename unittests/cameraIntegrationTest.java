package unittests;

import static org.junit.Assert.*;
import org.junit.Test;
import primitives.*;
import geometries.*;
import java.util.ArrayList;
import java.util.List;
import elements.Camera;
import geometries.Intersectable.GeoPoint;

/**
 * Testing Polygons
 * 
 * @author Devora & Sari
 *
 */
public class cameraIntegrationTest {

	List<GeoPoint> intersection = new ArrayList<GeoPoint>();
	Camera camera;
	int counter;

	/**
	 * Test method for {@link elements.Camera#constructRayThroughPixel(int.nX,
	 * int.nY, int.j, int.i, double.screenDistance, double.screenWidth,
	 * double.screenHeight)
	 */
	@Test
	public void testSphereIntegration() {
		Sphere sphere;
		// TC01: Sphere is in front of screen (2 points)
		counter = 0;
		camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
		sphere = new Sphere(1d, new Point3D(0, 0, 3));
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 3; i++) {
				intersection = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1d, 3, 3));
				if (intersection != null)
					counter += intersection.size();
			}
		assertEquals("Wrong number of points", 2, counter);
		// TC02: Sphere intersects the screen (18 points)
		counter = 0;
		camera = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
		sphere = new Sphere(2.5d, new Point3D(0, 0, 2.5));
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 3; i++) {
				intersection = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1d, 3, 3));
				if (intersection != null)
					counter += intersection.size();
			}
		assertEquals("Wrong number of points", 18, counter);
		// TC03: Sphere intersects the screen (10 points)
		counter = 0;
		sphere = new Sphere(2d, new Point3D(0, 0, 2));
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 3; i++) {
				intersection = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1d, 3, 3));
				if (intersection != null)
					counter += intersection.size();
			}
		assertEquals("Wrong number of points", 10, counter);
		// TC04: Sphere includes the screen and camera (9 points)
		counter = 0;
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		sphere = new Sphere(4d, new Point3D(0, 0, 2));
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 3; i++) {
				intersection = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1d, 3, 3));
				if (intersection != null)
					counter += intersection.size();
			}
		assertEquals("Wrong number of points", 9, counter);
		// TC05: Sphere is behind the camera (0 points)
		counter = 0;
		sphere = new Sphere(0.5d, new Point3D(0, 0, -1));
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 3; i++) {
				intersection = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1d, 3, 3));
				if (intersection != null)
					counter += intersection.size();
			}
		assertEquals("Wrong number of points", 0, counter);

	}

	/**
	 * Test method for {@link elements.Camera#constructRayThroughPixel(int.nX,
	 * int.nY, int.j, int.i, double.screenDistance, double.screenWidth,
	 * double.screenHeight)
	 */

	public void testPlaneIntegration() {
		Plane plane;
		// TC11: Plane is parallel to the screen (9 points)
		counter = 0;
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		plane = new Plane(new Vector(0, 1, 0), new Point3D(0, 0, 5));
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 3; i++) {
				intersection = plane.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1d, 3, 3));
				if (intersection != null)
					counter += intersection.size();
			}
		assertEquals("Wrong number of points", 9, counter);
		// TC12: Plane is not parallel\ orthogonal to the screen (9 points)
		counter = 0;
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		plane = new Plane(new Vector(0, 10, -5), new Point3D(0, 0, 5));
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 3; i++) {
				intersection = plane.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1d, 3, 3));
				if (intersection != null)
					counter += intersection.size();
			}
		assertEquals("Wrong number of points", 9, counter);
		// TC13: Plane is parallel to the ray that goes through the lowest pixel of the screen (6 points)
		counter = 0;
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		plane = new Plane(new Vector(1, 0, 0), new Point3D(0, 0, 5));
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 3; i++) {
				intersection = plane.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1d, 3, 3));
				if (intersection != null)
					counter += intersection.size();
			}
		assertEquals("Wrong number of points", 6, counter);

	}

	/**
	 * Test method for {@link elements.Camera#constructRayThroughPixel(int.nX,
	 * int.nY, int.j, int.i, double.screenDistance, double.screenWidth,
	 * double.screenHeight)
	 */

	public void testTriangleIntegration() {
		// TC21: Triangle is against the middle pixel the screen (1 points)
		counter = 0;
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		Triangle triangle = new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 3; i++) {
				intersection = triangle.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1d, 3, 3));
				if (intersection != null)
					counter += intersection.size();
			}
		assertEquals("Wrong number of points", 1, counter);

		// TC22: Triangle is in front of the screen (2 points)
		counter = 0;
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		triangle = new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 3; i++) {
				intersection = triangle.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1d, 3, 3));
				if (intersection != null)
					counter += intersection.size();
			}
		assertEquals("Wrong number of points", 2, counter);
	}
}
