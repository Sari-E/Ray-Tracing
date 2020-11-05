package primitives;

/**
 * Class Ray is the basic class representing a ray
 * 
 * @author Devora & Sari
 */
public class Ray {
	/**
	 * Ray value, intentionally "package-friendly" due to performance constraints
	 */
	private static final double DELTA = 0.1;

	Point3D _p0;
	Vector _dir;

	/**
	 * Ray constructor receiving ray values
	 * 
	 * @param point Point3D
	 * @param vector Vector
	 * 
	 */
	public Ray(Point3D point, Vector vector) {
		super();
		this._p0 = point;
		if (!vector.checkIfNormal())
			vector = vector.setNormal();
		this._dir = vector;
	}

	/**
	 * Ray constructor receiving ray values
	 * 
	 * @param point Point3D
	 * @param lightDirection Vector
	 * @param n Vector
	 * 
	 */

	public Ray(Point3D _point, Vector lightDirection, Vector n) {
				
		this._dir = lightDirection.normalize();		
		double nv = n.dotProduct(lightDirection);
		if(nv == 0)
			_p0 = new Point3D(_point);
		else
		{
		Vector delta = n.scale(nv >0 ? DELTA : -DELTA);
		_p0 = _point.add(delta);
		}
		
	}

	/**
	 * Ray constructor receiving ray values
	 * 
	 * @param other
	 */
	public Ray(Ray other) {// 
		super();
		this._p0 = other._p0;
		if (!this._dir.checkIfNormal())
			this._dir = other.get_dir();
		this._dir = other._dir;
	}

	/**
	 * Check equality function
	 * 
	 * @return boolean value
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (_dir == null) {
			if (other._dir != null)
				return false;
		} else if (!_dir.equals(other._dir))
			return false;
		if (_p0 == null) {
			if (other._p0 != null)
				return false;
		} else if (!_p0.equals(other._p0))
			return false;
		return true;
	}

	/**
	 * toString function
	 * 
	 * @return string value
	 */
	@Override
	public String toString() {
		return "Ray [_p0=" + _p0 + ", _dir=" + _dir + "]";
	}

	/**
	 * Point3D value getter
	 * 
	 * @return Point3D 
	 */
	public Point3D get_p0() {
		return _p0;
	}

	/**
	 * Point3D value getter
	 * 
	 * @return Vector 
	 */
	public Vector get_dir() {
		return _dir;
	}
	/**
	 * Point3D value getter
	 * 
	 * @return Point3D
	 */
	public Point3D getPoint(double t) {
		return _p0.add(_dir.scale(t));
	}
}
