/**
 * 
 */
package primitives;

/**
 * @author Sari
 *
 */
public class Material {

	private double _kD, _kS;
	private int _nShiness;
	private double _kT;
	private double _kR;
	/**
	 * @param _kD
	 * @param _kS
	 * @param _nShiness
	 * @param _kT
	 * @param _kR
	 */
	public Material(double _kD, double _kS, int _nShiness, double _kT, double _kR) {
		super();
		this._kD = _kD;
		this._kS = _kS;
		this._nShiness = _nShiness;
		this._kT = _kT;
		this._kR = _kR;
	}
	
	
	/**
	 * @param _kD
	 * @param _kS
	 * @param _nShiness
	 */
	public Material(double _kD, double _kS, int _nShiness) {
			this(_kD, _kS, _nShiness, 0,0);
	}
	/**
	 * @return the _kD
	 */
	public double get_kD() {
		return _kD;
	}
	/**
	 * @return the _kS
	 */
	public double get_kS() {
		return _kS;
	}
	/**
	 * @return the _nShiness
	 */
	public int get_nShiness() {
		return _nShiness;
	}
	/**
	 * @return the _kT
	 */
	public double get_kT() {
		return _kT;
	}
	/**
	 * @return the _kR
	 */
	public double get_kR() {
		return _kR;
	}
	

	
}
