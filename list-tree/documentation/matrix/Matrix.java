
/**
 * Klass f&ouml;r enkla numeriska matriser
 * Matrix.java
 *
 * @author Set Lonnert
 * @version 1.0.2, 1998-05-05
 */

package matrix;

public class Matrix {

  /**
   * Konstant nollv&auml;rde f&ouml;r matriser
   */
  protected static final double ZERO = 0;

  /**
   * Intern representation av matris, index av index av double
   */
  protected double matrix[][];

  /**
   * Storleken av matrisen i x och y
   */
  protected int x, y;

  /**
   * Tom matris
   */
  public Matrix() {
  }

  /**
   * Skapar ny matris
   *
   * @param x Storlek f&ouml;r matris i x-led.
   * @param y Storlek f&ouml;r matris i y-led.
   */
  public Matrix(int x, int y) {
    matrix = new double[x][y];
  }

  /**
   * Ger v&auml;rdet f&ouml;r matrisens storlek i y-led
   *
   * @return Storlek f&ouml;r matris i y-led
   */
  public int getY() {
    return y;
  }

  /**
   * Ger v&auml;rdet f&ouml;r matrisens storlek i x-led
   *
   * @return Storlek f&ouml;r matris i x-led
   */
  public int getX() {
    return x;
  }

  /**
   * Ger v&auml;rdet f&ouml;r matrisens storlek l&auml;ngd
   *
   * @return Storlek f&ouml;r matris l&auml;ngd
   */
  public int getLength() {
    return matrix.length;
  }

  /**
   * Antal element
   *
   * @return Antal element i matrisen
   */
  public int size() {
    return (x * y);
  }

  /**
   * H&auml;mta element
   *
   * @param x Placeringen av elementet i x-led
   * @param y Placeringen av elementet i y-led
   * @return Elementet
   * @exception ArrayIndexOutOfBoundsException Kan inte h&auml;ta elementet
   */
  public double getE(int x, int y)
  throws ArrayIndexOutOfBoundsException {
    checkOutOfBounds(x, y);
    return matrix[x][y];
  }

  /**
   * Placera element
   *
   * @param x Placeringen av elementet i x-led
   * @param y Placeringen av elementet i y-led
   * @param e Elementet
   * @exception ArrayIndexOutOfBoundsException Kan inte placera elementet
   */
  public void setE(int x, int y, double e)
  throws ArrayIndexOutOfBoundsException {
    checkOutOfBounds(x, y);
    matrix[x][y] = e;
  }

  /**
   * Kontrollera r&auml;ckvidd.
   *
   * @param x Placeringen av elementet i x-led
   * @param y Placeringen av elementet i y-led
   * @exception ArrayIndexOutOfBoundsException Utanf&ouml;r r&auml;ckvidden
   */
  private void checkOutOfBounds(int x, int y)
  throws ArrayIndexOutOfBoundsException {
    if ((y > this.y - 1) || (x > this.x - 1))
        throw new ArrayIndexOutOfBoundsException();
    if ((y < 0) || (x < 0))
      throw new ArrayIndexOutOfBoundsException();
  }

  /**
   * Radera alla element
   */
  public void clear() {
    for (int i = 0; i < x; ++i)
      for (int j = 0; j < y; ++j)
        setE(i, j, ZERO);
  }

  /**
   * Kopiera matrisen
   *
   * @exception ArrayIndexOutOfBoundsException Kan inte kopiera elementen
   */
  public Matrix copy()
  throws ArrayIndexOutOfBoundsException {
    Matrix m = new Matrix(x, y);
    for (int i = 0; i < x; ++i)
      for (int j = 0; j < y; ++j)
        m.setE(i, j, getE(i, j));
    return m;
  }

  /**
   * Matrisj&auml;mf&ouml;relse
   *
   * @param m Matris som skall j&auml;mf&ouml;ras
   * @return Matriserna &auml;r lika
   * @exception IncompatibleMatrixException Matriserna inte kompatibla
   */
  public boolean equals(Matrix m)
  throws IncompatibleMatrixException {
    if ((y != m.getY()) || (x != m.getX()))
      throw new IncompatibleMatrixException();
    for (int i = 0; i < x; ++i)
      for (int j = 0; j < y; ++j)
        if (getE(i, j) != m.getE(i, j))
          return false;
    return true;
  }
}
