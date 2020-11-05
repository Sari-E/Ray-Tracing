
package elements;

import primitives.*;

/**
 * abstract class Light, includes the basic properties of light
 * 
 * @author Sari
 *
 */
abstract class Light {

	protected Color _intensity;

	/**
	 * C-tor with parameters
	 * 
	 * @param Color _intensity
	 *
	 */
	public Light(Color _intensity) {
		super();
		this._intensity = _intensity;
	}

	/**
	 * Getter
	 * 
	 * @return Color
	 *
	 */

	public Color get_intensity() {
		return _intensity;
	}

}
