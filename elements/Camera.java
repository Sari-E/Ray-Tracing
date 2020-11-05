/**
 * 
 */
package elements;

import primitives.*;
import static primitives.Util.*;

/**
 * Camera class
 *
 * @author Sari and Devora
 */
public class Camera {
	Point3D _p0;
	Vector _vUp;
	Vector _vTo;
	Vector _vRight;

	/**
	 * constructor for a new Camera object
	 *
	 * @param Point3D, Vector, Vector
	 */
	public Camera(Point3D _p0, Vector _vTo, Vector _vUp) {
		super();
		if (alignZero(_vUp.dotProduct(_vTo)) != 0) {
			throw new IllegalArgumentException("vectors must be orthogonals");
		}
		this._p0 = _p0;
		this._vUp = _vUp.normalize();
		this._vTo = _vTo.normalize();
		this._vRight = _vTo.crossProduct(_vUp).normalize();
	}

	public Point3D get_p0() {
		return _p0;
	}


	/**
	 * function to construct a ray through a pixel
	 *
	 * @param int, int, int, int, double, double, double
	 * @return Ray
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth,
			double screenHeight) {
		Point3D pc = _p0.add(_vTo.scale(screenDistance));
		double ry = screenHeight / nY;
		double rx = screenWidth / nX;
		double yi = (i - nY / 2.0) * ry + ry / 2;
		double xj = (j - nX / 2.0) * rx + rx / 2;

		Point3D pij = pc;
		if (alignZero(xj) != 0)
			pij = pij.add(_vRight.scale(xj));
		if (alignZero(yi) != 0)
			pij = pij.add(_vUp.scale(-yi));

		Vector vij = pij.subtract(_p0);

		Ray ray = new Ray(_p0, vij.normalize());
		return ray;
	}

}
