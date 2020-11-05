package primitives;

/**
 * Class Vector is the basic class representing a vector
 * 
 * @author Devora & Sari
 */
public class Vector {
	
	Point3D _head;

	/**
	 * Vector constructor receiving Point3D values
	 * 
	 * @param head
	 */
	public Vector(Point3D head) {
		super();
		this._head = head;
	}

	/**
	 * Vector constructor receiving Vector values
	 * 
	 * @param other
	 * 
	 */
	public Vector(Vector other) {
		super();
		this._head = other._head;
	}

	/**
	 * Vector constructor receiving Coordinate values
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		super();
		Point3D temp_point = new Point3D(x, y, z);
		if (temp_point.equals(Point3D.ZERO)) {
			throw new IllegalArgumentException("value must not be zero");
		}
		this._head = new Point3D(temp_point);
	}

	/**
	 * Vector constructor receiving double values
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector(double x, double y, double z) {
		super();
		Coordinate _x = new Coordinate(x);
		Coordinate _y = new Coordinate(y);
		Coordinate _z = new Coordinate(z);
		Point3D temp_point = new Point3D(_x, _y, _z);
		if (temp_point.equals(Point3D.ZERO)) {
			throw new IllegalArgumentException("value must not be zero");
		}
		this._head = new Point3D(temp_point);

	}

	/**
	 * calculateLen function
	 * 
	 */
	public double calculateLen() {
		return Math.sqrt((_head._x._coord * _head._x._coord) + (_head._y._coord * _head._y._coord)
				+ (_head._z._coord * _head._z._coord));
	}

	/**
	 * A function that check if vector is normalized
	 * 
	 */
	public boolean checkIfNormal() {
		return (calculateLen() == 1);
	}

	/**
	 * A function that normalizes vector
	 * 
	 */
	public Vector setNormal() {
		double len = calculateLen();
		Coordinate temp1 = new Coordinate(_head._x._coord / len), temp2 = new Coordinate(_head._y._coord / len),
				temp3 = new Coordinate(_head._z._coord / len);
		Point3D temp = new Point3D(temp1, temp2, temp3);
		Vector normalizedVector = new Vector(temp);
		return normalizedVector;

	}

	/**
	 * toString function
	 * 
	 * @return string value
	 */
	@Override
	public String toString() {
		return "Vector [headOfVector=" + _head + "]";
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
		Vector other = (Vector) obj;
		if (_head == null) {
			if (other._head != null)
				return false;
		} else if (!_head.equals(other._head))
			return false;
		return true;
	}

	/**
	 * Vector value getter
	 * 
	 * @return Poin3D value
	 */
	public Point3D get_head() {
		return _head;
	}

	/**
	 * Vector value setter
	 * 
	 * @param Poin3D value
	 */
	public void set_head(Point3D head) {
		this._head = head;
	}

	/**
	 * Add function
	 * 
	 * @param other
	 * @return Vector value
	 */
	public Vector add(Vector other) {
		Vector added_vector = new Vector(this._head.add(other._head));
		return added_vector;
	}

	/**
	 * Subtract function
	 * 
	 * @param other
	 * @return Vector value
	 */
	public Vector subtract(Vector other) {
		Vector subtracted_vector = new Vector(this._head.subtract(other._head));
		return subtracted_vector;
	}

	/**
	 * Scale function
	 * 
	 * @param t
	 * @return Vector value
	 */
	public Vector scale(double t) {
		Point3D scaled_point = new Point3D(this._head._x._coord * t, this._head._y._coord * t,
				this._head._z._coord * t);
		Vector scaled_vector = new Vector(scaled_point);
		return scaled_vector;
	}

	/**
	 * Dot Product function
	 * 
	 * @param other
	 * @return double value
	 */
	public double dotProduct(Vector other) {
		double result = this._head._x._coord * other._head._x._coord + this._head._y._coord * other._head._y._coord
				+ this._head._z._coord * other._head._z._coord;
		return result;
	}

	/**
	 * Cross product function
	 * 
	 * @param other
	 * @return double value
	 */
	public Vector crossProduct(Vector other) {
		Coordinate _x = new Coordinate(
				this._head._y._coord * other._head._z._coord - this._head._z._coord * other._head._y._coord);
		Coordinate _y = new Coordinate(
				this._head._z._coord * other._head._x._coord - this._head._x._coord * other._head._z._coord);
		Coordinate _z = new Coordinate(
				this._head._x._coord * other._head._y._coord - this._head._y._coord * other._head._x._coord);
		Vector result = new Vector(_x, _y, _z);
		return result;
	}

	/**
	 * Length squared function
	 * 
	 * @return double value
	 */
	public double lengthSquared() {
		double result = this._head.distanceSquared(Point3D.ZERO);
		return result;
	}

	/**
	 * Length function
	 * 
	 * @return double value
	 */
	public double length() {
		double result = this.lengthSquared();
		result = Math.sqrt(result);
		return result;
	}

	/**
	 * Normalize function (changes the object)
	 * 
	 * @return Vector value
	 */
	public Vector normalize() {
		double len = length();
		Point3D temp = new Point3D(this._head._x._coord / len, this._head._y._coord / len, this._head._z._coord / len);
		this._head = temp;
		return this;
	}

	/**
	 * Normalize function (does not change the object)
	 * 
	 * @return Vector value
	 */
	public Vector normalized() {
		Vector temp = new Vector(this);
		return temp.normalize();
	}

}
