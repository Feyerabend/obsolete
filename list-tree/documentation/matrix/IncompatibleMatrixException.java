
/**
 * Undantagsklass f&ouml;r matriser.
 * IncompatibleMatrixException.java
 *
 * Undantaget intr&auml;der d&aring; tv&auml; inkompatibla matriser
 * opereras p&aring;.
 * @author Set Lonnert
 * @version 1.0, 10 april 97
 */

package matrix;

public class IncompatibleMatrixException extends Exception {
    public IncompatibleMatrixException() {
        super();
    }
    public IncompatibleMatrixException(String s) {
        super(s);
    }
}
