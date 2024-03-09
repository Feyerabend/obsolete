
/**
 * Undantagsklass f&ouml;r matriser.
 * NonInvertableMatrixException
 *
 * Undantaget intr&auml;der d&aring; matrisen &auml;r singul&auml;r.
 * @author Set Lonnert
 * @version 1.0, 10 april 97
 */

package matrix;

public class NonInvertableMatrixException extends Exception {
    public NonInvertableMatrixException() {
        super();
    }
    public NonInvertableMatrixException(String s) {
        super(s);
    }
}
