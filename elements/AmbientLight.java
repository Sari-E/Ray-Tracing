/**
 * A class that represents environmental theory
 */

package elements;

import primitives.*;

/**
 * @author Sari and Devora
 *
 */
public class AmbientLight extends Light{
	/**
	 * A constructor
	 * @param Color
	 * @param double
	 */
	public AmbientLight(Color intensity, double kA)
	{
		super( intensity.scale(kA));
	}
	/**
	 * A set_intensity function
	 * @param Color
	 */
	public void set_intensity(Color _intensity) {
		this._intensity = _intensity;
	}
}
