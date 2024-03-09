
/**
 * Klass f&ouml;r numeriska matriser och
 * ett slags enkla tableauer.
 * Matrix.java
 *
 * @author Set Lonnert
 * @version 1.0.1, 02:18 1997-05-14
 */

package matrix;

public class MatrixManipulate {

  /**
   * Intern representation av matris, index av index av double
   */
  protected Matrix matrix;

  /**
   * Storleken av matrisen i x och y
   */
  protected int x, y;

  /**
   * Tom matris
   */
  public MatrixManipulate() {
  }

  /**
   * Skapar ny matris
   *
   * @param x Storlek f&ouml;r matris i x-led.
   * @param y Storlek f&ouml;r matris i y-led.
   */
  public MatrixManipulate(Matrix _matrix) {
    matrix = _matrix;
    x = matrix.getX();
    y = matrix.getY();
  }

  /**
   * Matrisaddition
   *
   * @param m Matris som skall adderas
   * @return Resultatet av additionen
   * @exception IncompatibleMatrixException Matriserna inte kompatibla
   */
  public Matrix add(Matrix m)
  throws IncompatibleMatrixException {
    if ((y != m.getY()) || (x != m.getX()))
      throw new IncompatibleMatrixException();
    Matrix n = new Matrix(x, y);
    for (int i = 0; i < x; ++i) {
      for (int j = 0; j < y; ++j)
        n.setE(i, j, matrix.getE(i, j) + m.getE(i, j));
    }
    return n;
  }

  /**
   * Matrissubtraktion
   *
   * @param m Matris som skall subtraheras
   * @return Resultatet av subtraktionen
   * @exception IncompatibleMatrixException Matriserna inte kompatibla
   */
  public Matrix subtract(Matrix m)
  throws IncompatibleMatrixException {
    if ((y != m.getY()) || (x != m.getX()))
      throw new IncompatibleMatrixException();
    Matrix n = new Matrix(x, y);
    for (int i = 0; i < x; ++i) {
      for (int j = 0; j < y; ++j)
        n.setE(i, j, matrix.getE(i, j) - m.getE(i, j));
    }
    return n;
  }

  /**
   * Matrismultiplikation
   *
   * @param m Matris som skall multipliceras
   * @return Resultatet av multiplikationen
   * @exception IncompatibleMatrixException Matriserna inte kompatibla
   */
  public Matrix multiply(Matrix m)
  throws IncompatibleMatrixException {
    if (y != m.getX())
      throw new IncompatibleMatrixException();
    int mY = m.getY();
    Matrix n = new Matrix(x, mY);
    for (int i = 0; i < matrix.getLength(); ++i) {
      for (int j = 0; j < mY; ++j) {
        n.setE(i, j, ZERO);
        for (int k = 0; k < y; ++k)
          n.setE(i, j, (n.getE(i, j) +
          matrix.getE(i, k) * m.getE(k, j)));
      }
    }
    return n;
  }

  /**
   * Matriskvadrering
   *
   * @return Resultatet av kvadreringen
   * @exception IncompatibleMatrixException Matriserna inte kompatibla
   */
  public Matrix square()
  throws IncompatibleMatrixException {
    Matrix m = new Matrix(x, y);
    m = matrix.copy();
    return m.multiply(m);
  }

  /**
   * Matrisinvertering
   * <p>F&ouml;r att invertera en matris f&aring;r en
   * enhetsmatris skapas. Generellt g&auml;ller att en
   * matris A: <code>A * ~A = E</code>,
   * d&auml;r ~A betecknar den inverterade matrisen,
   * och E enhetsmatrisen.
   * <p>En enhetsmatris har antalet rader och kolumner lika.
   * Den best&aring;r av en diagonal 1 och resterande 0. Ex:
   * <pre>
   *      1  0  0
   *      0  1  0
   *      0  0  1
   * </pre>
   *
   * @return Resultatet av inverteringen
   * @exception NonInvertableMatrixException Matrisen inte inverterbar
   */
  public Matrix invert()
  throws NonInvertableMatrixException {
    if (x != y)
      throw new NonInvertableMatrixException();
    Matrix e = new Matrix(x, y);
    for (int i = 0; i < x; ++i) {
      for (int j = 0; j < x; ++j)
        e.setE(j, i, (i == j) ? 1 : ZERO);
    }
    Matrix n = new Matrix(x, y);
    boolean error = false;
    for (int i = 0; i < x; ++i) {
      for (int j = i; j < x; ++j) {
        error = true;
        if (matrix.getE(j, i) == 0)
          continue;
        error = false;
        for (int k = 0; k < x; ++k) {
          double s = matrix.getE(i, k);
          n.setE(i, k, matrix.getE(j, k));
          n.setE(j, k, s);
          s = e.getE(i, k);
          e.setE(i, k, e.getE(j, k));
          e.setE(j, k, s);
        }
        double t = (1 / matrix.getE(i, i));
        for (int k = 0; k < x; ++k) {
          n.setE(i, k, t * matrix.getE(i, k));
          e.setE(i, k, t * e.getE(i, k));
        }
        for (int u = 0; u < x; ++u) {
          if (u == i)
            continue;
          t = -matrix.getE(u, i);
          for (int k = 0; k < x; ++k) {
            n.setE(u, k, matrix.getE(u, k)
              + t * matrix.getE(i, k));
            e.setE(u, k, e.getE(u, k)
              + t * e.getE(i, k));
          }
        }
      }
    }
    if (error) throw
      new NonInvertableMatrixException("Singleton matrix.");
    return n;
  }
}
