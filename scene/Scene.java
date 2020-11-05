/**
 * class Scene - includes all the things that scene needs
 */
package scene;

import primitives.*;
import elements.*;
import geometries.*;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Sari and Devora
 *
 */
public class Scene {
	String _sceneName;
	Color _background;
	AmbientLight _ambientLight;
	Geometries _geometries;
	Camera _camera;
	double _screenDistance;

	List<LightSource> _lights = new LinkedList<LightSource>();

	/**
	 * A constractor
	 * 
	 * @param sceneName String
	 */
	public Scene(String sceneName) {
		_sceneName = sceneName;
		_geometries = new Geometries();

	}

	/**
	 * Set function
	 * 
	 * @param _sceneName String
	 */
	public void set_sceneName(String _sceneName) {
		this._sceneName = _sceneName;
	}

	/**
	 * addLights function, append between 2 lists of lights
	 * 
	 * @param Lights LightSource...
	 */
	public void addLights(LightSource... lights) {

		_lights.addAll(Arrays.asList(lights));

	}

	/**
	 * Get function
	 * 
	 * @return Color
	 */
	public Color get_background() {
		return _background;
	}

	/**
	 * Set function
	 * 
	 * @param _background Color
	 */
	public void set_background(Color _background) {
		this._background = _background;
	}

	/**
	 * Get function
	 * 
	 * @return AmbientLight
	 */
	public AmbientLight get_ambientLight() {
		return _ambientLight;
	}

	/**
	 * Set function
	 * 
	 * @param _ambientLight AmbientLight
	 */
	public void set_ambientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}

	/**
	 * Get function
	 * 
	 * @return Geometries
	 */
	public Geometries get_geometries() {
		return _geometries;
	}

	/**
	 * Get function
	 * 
	 * @return Camera
	 */
	public Camera get_camera() {
		return _camera;
	}

	/**
	 * Set function
	 * 
	 * @param _camera Camera
	 */
	public void set_camera(Camera _camera) {
		this._camera = _camera;
	}

	/**
	 * Get function
	 * 
	 * @return double
	 */
	public double get_screenDistance() {
		return _screenDistance;
	}

	/**
	 * Set function
	 * 
	 * @param _screenDistance double
	 */

	public void set_screenDistance(double _screenDistance) {
		this._screenDistance = _screenDistance;
	}

	/**
	 * addGeometry function, append between 2 lists of geometries
	 * 
	 * @param geometries Geometry...
	 */
	public void addGeometry(Geometry... geometries) {
		_geometries.add(geometries);
	}

	/**
	 * Get function
	 * 
	 * @return List<LightSource>
	 */
	public List<LightSource> get_lights() {
		return _lights;
	}
}
