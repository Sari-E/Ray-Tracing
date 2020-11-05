/**
 * class Render - creates the scene
 */
package renderer;

import scene.*;
import primitives.*;
import geometries.Intersectable.GeoPoint;
import java.util.List;
import elements.*;
import static primitives.Util.*;
import java.math.*;
import java.text.DecimalFormat;

/**
 * @author Sari and Devora
 * @author Dan
 *
 */
public class Render {
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final int MAX_ACCELERATION_LEVEL = 3;

	private static final double MIN_CALC_COLOR_K = 0.001;
	private final Scene _scene;
	private final ImageWriter _imageWriter;

	private int _threads = 1;
	private final int SPARE_THREADS = 2;
	private boolean _print = false;

	/**
	 * Pixel is an internal helper class whose objects are associated with a Render
	 * object that they are generated in scope of. It is used for multithreading in
	 * the Renderer and for follow up its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each
	 * thread.
	 * 
	 * @author Dan
	 *
	 */
	private class Pixel {
		private long _maxRows = 0;
		private long _maxCols = 0;
		private long _pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long _counter = 0;
		private int _percents = 0;
		private long _nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * 
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			_maxRows = maxRows;
			_maxCols = maxCols;
			_pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print)
				System.out.printf("\r %02d%%", _percents);
		}

		/**
		 * Default constructor for secondary Pixel objects
		 */
		public Pixel() {
		}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object
		 * - this function is critical section for all the threads, and main Pixel
		 * object data is the shared data of this critical section.<br/>
		 * The function provides next pixel number each call.
		 * 
		 * @param target target secondary Pixel object to copy the row/column of the
		 *               next pixel
		 * @return the progress percentage for follow up: if it is 0 - nothing to print,
		 *         if it is -1 - the task is finished, any other value - the progress
		 *         percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target) {
			++col;
			++_counter;
			if (col < _maxCols) {
				target.row = this.row;
				target.col = this.col;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			++row;
			if (row < _maxRows) {
				col = 0;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			return -1;
		}

		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * 
		 * @param target target secondary Pixel object to copy the row/column of the
		 *               next pixel
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percents = nextP(target);
			if (percents > 0)
				if (Render.this._print)
					System.out.printf("\r %02d%%", percents);
			if (percents >= 0)
				return true;
			if (Render.this._print)
				System.out.printf("\r %02d%%", 100);
			return false;
		}
	}

	/**
	 * Render Constructor
	 *
	 * @param Scene
	 * @param ImageWriter
	 */

	public Render(ImageWriter imageWriter, Scene scene) {
		_imageWriter = imageWriter;

		_scene = scene;
	}

	/**
	 * This function renders image's pixel color map from the scene included with
	 * the Renderer object
	 */
	public void renderImage() {

		final int nX = _imageWriter.getNx();
		final int nY = _imageWriter.getNy();
		final double dist = _scene.get_screenDistance();
		final double width = _imageWriter.getWidth();
		final double height = _imageWriter.getHeight();
		final Camera camera = _scene.get_camera();

		final Pixel thePixel = new Pixel(nY, nX);

// Generate threads
		Thread[] threads = new Thread[_threads];
		for (int i = _threads - 1; i >= 0; --i) {
			threads[i] = new Thread(() -> {
				Pixel pixel = new Pixel();

				while (thePixel.nextPixel(pixel)) {
					if (_imageWriter.getAcceleration()) {

						Color generalColor = renderMini2(pixel.col, pixel.row);
						_imageWriter.writePixel(pixel.row, pixel.col, generalColor.getColor());

					} else {
						Color avgColor = renderHelp(pixel.col, pixel.row);
						_imageWriter.writePixel(pixel.row, pixel.col, avgColor.getColor());
					}
				}
			});
		}
// Start threads
		for (Thread thread : threads)
			thread.start();

// Wait for all threads to finish
		for (Thread thread : threads)
			try {
				thread.join();
			} catch (Exception e) {
			}
		if (_print)
			System.out.printf("\r100%%\n");
	}

	/**
	 * This function is a wrapper func for the funtion that improves the sharpness
	 * of the picture with performances
	 */
	private Color renderMini2(int i, int j) {
		return renderAcceleration(i, j, MAX_ACCELERATION_LEVEL);
	}

	/**
	 * This function i improves the sharpness of the picture with performances (for
	 * mini project 2)
	 */
	private Color renderAcceleration(int i, int j, int level) {

		double width = _imageWriter.getWidth();
		double height = _imageWriter.getHeight();
		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();

		Color ru, rd, lu, ld;
		Color ru1, rd1, lu1, ld1;
		Color ru2, rd2, lu2, ld2;
		int a = 10;
		int b = 2;

		// check 4 points
		ru = pixelColor(i * a, (j + 1) * a - 1, (int) (a * Math.pow(b, MAX_ACCELERATION_LEVEL - level)));
		rd = pixelColor((i + 1) * a - 1, (j + 1) * a - 1, (int) (a * Math.pow(b, MAX_ACCELERATION_LEVEL - level)));
		lu = pixelColor(i * a, j * a, (int) (a * Math.pow(b, MAX_ACCELERATION_LEVEL - level)));
		ld = pixelColor((i + 1) * a - 1, j * a, (int) (a * Math.pow(b, MAX_ACCELERATION_LEVEL - level)));

		if (level == 1) {
			// send 4 quarters
			ru1 = pixelColor(i * b, (j + 1) * b - 1, b * MAX_ACCELERATION_LEVEL);
			rd1 = pixelColor((i + 1) * b - 1, (j + 1) * b - 1, b * MAX_ACCELERATION_LEVEL);
			lu1 = pixelColor(i * b, j * b, b * MAX_ACCELERATION_LEVEL);
			ld1 = pixelColor((i + 1) * b - 1, j * b, b * MAX_ACCELERATION_LEVEL);
			return ru.add(rd.add(lu.add(ld))).reduce(b * b);

		}

		if ((ru.equals(rd) && rd.equals(lu) && lu.equals(ld))) {
			return ru;
		}

		else {

			ru2 = renderAcceleration(i * b, (j + 1) * b - 1, level - 1);
			rd2 = renderAcceleration((i + 1) * b - 1, (j + 1) * b - 1, level - 1);
			lu2 = renderAcceleration(i * b, j * b, level - 1);
			ld2 = renderAcceleration((i + 1) * b - 1, j * b, level - 1);

			return ru.add(rd.add(lu.add(ld))).reduce(b * b);

		}

	}

	/**
	 * This function calculates the color in certain pixel
	 */
	private Color pixelColor(int i, int j, int res) {
		Camera camera = _scene.get_camera();
		Color background = _scene.get_background();
		double distance = _scene.get_screenDistance();
		double width = _imageWriter.getWidth();
		double height = _imageWriter.getHeight();

		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();

		Ray ray = camera.constructRayThroughPixel((int) (nX * res), (int) (nY * res), j, i, distance, width, height);
		GeoPoint closestPoint = findClosestIntersection(ray);
		if (closestPoint == null)
			return background;

		else
			return calcColor(closestPoint, ray);

	}

	/**
	 * This function helps rendering each pixel color map by calculating the color
	 * as average of the rays sent from each pixel
	 */
	private Color renderHelp(int i, int j) {
		Camera camera = _scene.get_camera();
		Color background = _scene.get_background();
		double distance = _scene.get_screenDistance();
		double width = _imageWriter.getWidth();
		double height = _imageWriter.getHeight();
		Color avgColor = new Color(0, 0, 0);

		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();

		int resolution = _imageWriter.getResolution();
		double focus1 = 1.0 / 3, focus2 = 1 - focus1;
		for (int k = 0; k < resolution; k++) // i is pixel row number and j is pixel in the row number
		{
			for (int l = 0; l < resolution; l++) {
				Ray ray = camera.constructRayThroughPixel(nX * resolution, nY * resolution, j * resolution + k,
						i * resolution + l, distance, width, height);
				GeoPoint closestPoint = findClosestIntersection(ray);
				if (closestPoint == null) {
					avgColor = avgColor.add(background);
					// pixels in the middle have more weight than the sides
					if (resolution > 1)
						if (k >= (resolution * focus1) && k <= (resolution * focus2) && l >= (resolution * focus1)
								&& l <= (resolution * focus2))
							avgColor = avgColor.add(background);
				}

				else {
					avgColor = avgColor.add(calcColor(closestPoint, ray));
					// pixels in the middle have more weight than the sides
					if (resolution > 1)
						if (k >= (resolution * focus1) && k <= (resolution * focus2) && l >= (resolution * focus1)
								&& l <= (resolution * focus2))
							avgColor = avgColor.add(calcColor(closestPoint, ray));
				}
			}
		}

		avgColor = avgColor.reduce(resolution * resolution * (1 + (1 - 2 * focus1) * (1 - 2 * focus1)));
		return avgColor;

	}

	/**
	 * Set multithreading <br>
	 * - if the parameter is 0 - number of coress less 2 is taken
	 *
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads) {
		if (threads < 0)
			throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
		if (threads != 0)
			_threads = threads;
		else {
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			if (cores <= 2)
				_threads = 1;
			else
				_threads = cores;
		}
		return this;
	}

	/**
	 * Set debug printing on
	 *
	 * @return the Render object itself
	 */
	public Render setDebugPrint() {
		_print = true;
		return this;
	}

	/**
	 * Shell function
	 *
	 * @param p   GeoPoint
	 * @param ray
	 * @return color of point
	 */
	private Color calcColor(GeoPoint gp, Ray ray) {
		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.get_ambientLight().get_intensity());

	}

	/**
	 * A function that calculates the color at a point
	 *
	 * @param GeoPoint
	 * @param ray
	 * @param level
	 * @param k
	 * @return color of point
	 */

	private primitives.Color calcColor(GeoPoint p, Ray inRay, int level, double k) {

		Color _color = p.geometry.get_emmission(); // remove Ambient Light
		Vector v = p.point.subtract(_scene.get_camera().get_p0()).normalize();// vector camera-> geometry
		Vector n = p.geometry.getNormal(p.point); // normal of geometry
		Material material = p.geometry.get_material();

		int nShininess = material.get_nShiness();
		double kd = material.get_kD();
		double ks = material.get_kS();

		for (LightSource lightSource : _scene.get_lights()) {

			Vector l = lightSource.getL(p.point);// vector light->geometry
			// sign of l*n = sign of v*n
			if (Math.signum(n.dotProduct(l)) == Math.signum(n.dotProduct(v))) {
				double ktr = transparency(lightSource, l, n, p);
				if (ktr * k > MIN_CALC_COLOR_K) {
					Color lightIntensity = lightSource.getIntensity(p.point).scale(ktr);
					_color = _color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}
		if (level == 1)
			return Color.BLACK;
		double kr = p.geometry.get_material().get_kR(), kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(n, p.point, inRay);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null)
				_color = _color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
		}

		double kt = p.geometry.get_material().get_kT(), kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(n, p.point, inRay);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null)
				_color = _color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));

		}

		return _color;
	}

	/**
	 * In the intersectionPoints - find the point with minimal distance from the ray
	 * begin point and return it
	 *
	 * @param points list of the intersectionPoints
	 * @return close point
	 */
	private GeoPoint getClosestPoint(List<GeoPoint> points, Ray ray) {
		GeoPoint close = points.get(0);
		double dist;
		for (GeoPoint p : points) {
			dist = ray.get_p0().distance(p.point);
			if (dist < ray.get_p0().distance(close.point))
				close = p;
		}
		return close;
	}

	/**
	 * the func gets a ray and finds the intersection
	 *
	 * @param Ray
	 * @return GeoPoint
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> temp = _scene.get_geometries().findIntersections(ray);
		if (temp == null)
			return null;
		return getClosestPoint(temp, ray);
	}

	/**
	 * the func gets a ray and returns the reflected ray
	 *
	 * @param Vector, Point3D, Ray
	 * @return Ray
	 */
	private Ray constructReflectedRay(Vector n, Point3D p, Ray l) { // hish
		Vector _n = n;// normal at point
		Vector _v = l.get_dir(); // direction of vector camera ->geometry
		double vn = _v.dotProduct(_n);
		if (vn == 0)
			return null;

		Vector _r = _v.add(_n.scale(-2 * vn));
		return new Ray(p, _r, _n);
	}

	/**
	 * the func gets a ray and returns the refracted ray
	 *
	 * @param Vector, Point3D, Ray
	 * @return Ray
	 */
	private Ray constructRefractedRay(Vector n, Point3D p, Ray l) {
		return new Ray(p, l.get_dir(), n);
	}

	/**
	 * A helper function that calculates the diffuse at a point
	 *
	 * @param double kd,Vector l, Vector n,Color lightIntensity
	 * @return primitives.Color
	 *
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity)

	{
		return lightIntensity.scale(Math.abs(l.dotProduct(n)) * kd);
	}

	/**
	 * A helper function that calculates the specular at a point
	 *
	 * @param double ks,Vector l,Vector n,Vector v,double nShininess,Color
	 *               lightIntensity
	 * @return primitives.Color
	 *
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, double nShininess, Color lightIntensity)

	{
		Vector r = l.subtract(n.scale(2 * l.dotProduct(n))).normalize();
		return lightIntensity.scale(ks * Math.max(0, Math.pow(v.scale(-1).dotProduct(r), nShininess)));
	}

	/**
	 * A function that calculates the closest point
	 *
	 * @param List<GeoPoint> points
	 * @return GeoPoint
	 *
	 */
	private GeoPoint getClosestPoint(List<GeoPoint> points) {
		GeoPoint closestPoint = null;
		double distance = Double.MAX_VALUE;

		Point3D P0 = _scene.get_camera().get_p0();

		for (GeoPoint i : points) {
			double tempDistance = i.point.distance(P0);
			if (tempDistance < distance) {
				closestPoint = i;
				distance = tempDistance;
			}
		}
		return closestPoint;

	}

	/**
	 * A function that prints the grid
	 *
	 * @param int , java.awt.Color color
	 *
	 */
	public void printGrid(int interval, java.awt.Color color) {

		for (int j = 0; j < _imageWriter.getWidth(); j++) {
			for (int i = 0; i < _imageWriter.getHeight(); i++) {
				if (i % interval == 0 || j % interval == 0)
					_imageWriter.writePixel(j, i, color);

			}
		}
		_imageWriter.writeToImage();
	}

	/**
	 * A function that prints the image
	 *
	 */
	public void writeToImage() {
		_imageWriter.writeToImage();
	}

	/**
	 * @param light    LightSource
	 * @param l        vector
	 * @param n        vector
	 * @param geopoint GeoPoint
	 * @return if the point unshaded
	 */

	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp) {

		Vector lightDirection = l.scale(-1); // from point to light source

		Ray lightRay = new Ray(gp.point, lightDirection, n);

		List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);

		if (intersections == null)
			return true;
		double lightDistance = light.getDistance(gp.point);
		for (GeoPoint _gp : intersections) {
			if (alignZero(_gp.point.distance(gp.point) - lightDistance) <= 0
					&& gp.geometry.get_material().get_kT() == 0)
				return false;
		}
		return true;
	}

	/**
	 * @param light    LightSource
	 * @param l        vector
	 * @param n        vector
	 * @param geopoint GeoPoint
	 * @return the point transparency
	 */
	private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geopoint.point, lightDirection, n);
		List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
		if (intersections == null)
			return 1.0;
		double lightDistance = light.getDistance(geopoint.point);
		double ktr = 1.0;
		for (GeoPoint gp : intersections) {
			if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
				ktr *= gp.geometry.get_material().get_kT();
				if (ktr < MIN_CALC_COLOR_K)
					return 0.0;
			}
		}
		return ktr;
	}
}