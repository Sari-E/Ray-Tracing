package primitives;

/**
 * Class Point3D is the basic class representing a point
 * 
 * @author Devora & Sari
 */
public final class Point3D {
	/**
	 * Point value, intentionally "package-friendly" due to performance constraints
	 */
	public static Point3D ZERO = new Point3D(0, 0, 0);
	Coordinate _x;
	Coordinate _y;
	Coordinate _z;

	/**
	 * Point constructor receiving coordinate values
	 * 
	 * @param x coordinate value
	 * @param y coordinate value
	 * @param z coordinate value
	 * 
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super();
		this._x = x;
		this._y = y;
		this._z = z;
	}

	/**
	 * Point constructor receiving coordinate values
	 * 
	 * @param x double values
	 * @param y double values
	 * @param z double values
	 * 
	 */
	public Point3D(double x, double y, double z) {
		super();
		Coordinate _x_ = new Coordinate(x);
		Coordinate _y_ = new Coordinate(y);
		Coordinate _z_ = new Coordinate(z);
		this._x = _x_;
		this._y = _y_;
		this._z = _z_;
	}

	/**
	 * copy constructor receiving coordinate values
	 * 
	 * @param other Point values
	 * 
	 */
	public Point3D(Point3D other) {
		super();
		this._x = other._x;
		this._y = other._y;
		this._z = other._z;
	}

	/**
	 * Add function
	 * 
	 * @param other
	 * @return y Point3D value
	 * 
	 */
	public Point3D add(Point3D other) {
		Point3D temp = new Point3D(this._x._coord + other._x._coord, this._y._coord + other._y._coord,
				this._z._coord + other._z._coord);
		return temp;
	}

	/**
	 * Add function
	 * 
	 * @param other
	 * @return y Point3D value
	 * 
	 */
	public Point3D add(Vector other) {
		Point3D temp = new Point3D(this._x._coord + other._head._x._coord, this._y._coord + other._head._y._coord,
				this._z._coord + other._head._z._coord);
		return temp;
	}

	/**
	 * Squared distance calculate function
	 * 
	 * @param other
	 * @return double value
	 * 
	 */
	public double distanceSquared(Point3D other) {
		double distance = ((this._x._coord - other._x._coord) * (this._x._coord - other._x._coord)
				+ (this._y._coord - other._y._coord) * (this._y._coord - other._y._coord)
				+ (this._z._coord - other._z._coord) * (this._z._coord - other._z._coord));
		return distance;
	}

	/**
	 * Distance calculate function
	 * 
	 * @param other
	 * @return double value
	 * 
	 */
	public double distance(Point3D other) {
		double distance = Math.sqrt(distanceSquared(other));
		return distance;
	}

	/**
	 * Subtract function
	 * 
	 * @param other
	 * @return Vector value
	 * 
	 */
	public Vector subtract(Point3D other) {
		Point3D temp = new Point3D(this._x._coord - other._x._coord, this._y._coord - other._y._coord,
				this._z._coord - other._z._coord);
		Vector temp_v = new Vector(temp);
		return temp_v;
	}

	/**
	 * Point3D value getter
	 * 
	 * @return Coordinate value
	 */
	public Coordinate get_x() {
		return _x;
	}

	/**
	 * Point3D value setter
	 * 
	 * @param x
	 */
	public void set_x(Coordinate x) {
		this._x = x;
	}

	/**
	 * Point3D value getter
	 * 
	 * @return Coordinate value
	 */
	public Coordinate get_y() {
		return _y;
	}

	/**
	 * Point3D value setter
	 * 
	 * @param y
	 */
	public void set_y(Coordinate y) {
		this._y = y;
	}

	/**
	 * Point3D value getter
	 * 
	 * @return Coordinate value
	 */
	public Coordinate get_z() {
		return _z;
	}

	/**
	 * Point3D value setter
	 * 
	 * @param z
	 */
	public void set_z(Coordinate z) {
		this._z = z;
	}

	/**
	 * toString function
	 * 
	 * @return string value
	 */
	@Override
	public String toString() {
		return "Point3D [_x=" + _x + ", _y=" + _y + ", _z=" + _z + "]";
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
		Point3D other = (Point3D) obj;
		if (_x == null) {
			if (other._x != null)
				return false;
		} else if (!_x.equals(other._x))
			return false;
		if (_y == null) {
			if (other._y != null)
				return false;
		} else if (!_y.equals(other._y))
			return false;
		if (_z == null) {
			if (other._z != null)
				return false;
		} else if (!_z.equals(other._z))
			return false;
		return true;
	}

}
