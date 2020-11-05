package geometries;

import primitives.*;

/**
 * interface Geometry is the basic interface for all geometric objects who are
 * implementing getNormal method.
 *
 * @author Sari and Devora
 */

public abstract class Geometry implements Intersectable {

	protected Color _emmission;
	protected Material _material;

	/**
	 * C-tor with parameters
	 * 
	 * @param _emmission
	 */
	public Geometry(Color _emmission) {
		super();
		this._emmission = _emmission;
		this._material = new Material(0, 0, 0);
	}

	/**
	 * C-tor with parameters
	 * 
	 * @param _emmission
	 * @param _material
	 */
	public Geometry(Material _material, Color _emmission) {
		super();
		this._emmission = _emmission;
		this._material = _material;
	}

	/**
	 * default C-tor
	 */
	public Geometry() {
		super();

		this._emmission = Color.BLACK;
		this._material = new Material(0, 0, 0);

	}

	/**
	 * Getter function
	 * 
	 * @return Color
	 */
	public Color get_emmission() {
		return _emmission;

	}

	/**
	 *
	 * @param p Point3D
	 * @return Vector
	 */

	public abstract Vector getNormal(Point3D _point);

	/**
	 * @return Material _material
	 */
	public Material get_material() {
		return this._material;
	}

}
