
/**
 * Klass f&ouml;r numeriska matriser och
 * ett slags enkla tableauer.
 * Matrix.java
 *
 * @author Set Lonnert
 * @version 1.0.1, 02:18 1997-05-14
 */

package matrix;

public class OldMatrix {

  /**
   * Konstant nollv&auml;rde f&ouml;r matriser
   */
  protected static final double ZERO = 0;

  /**
   * Utfyllnad f&ouml;r utskrift av element
   */
  protected static final int PADDING = 10;

  /**
   * Utfyllnad mellan element f&ouml;r utskrift
   */
  protected static final int SPACE = 5;

  /**
   * Flagga f&ouml;r tableauer
   */
  protected boolean tableau = false;

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
  public OldMatrix() {
  }

  /**
   * Skapar ny matris
   *
   * @param x Storlek f&ouml;r matris i x-led.
   * @param y Storlek f&ouml;r matris i y-led.
   */
  public OldMatrix(int x, int y) {
    this(x, y, false);
  }

  /**
   * Skapar ny tableau (fysisk matris)
   *
   * @param x Storlek f&ouml;r matris i x-led.
   * @param y Storlek f&ouml;r matris i y-led.
   * @param tableau Tableau, annars matris.
   */
  public OldMatrix(int x, int y, boolean tableau) {
    this.x = x;
    this.y = y;
    this.tableau = tableau;
    matrix = (tableau ?
      new double[x + 1][y + 1] :
      new double[x][y]);
  }

  /**
   * Tableaupredikat.
   *
   * @return &Auml;r tableau.
   */
  public boolean isTableau() {
    return tableau;
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
    if (tableau)
      if ((y > this.y) || (x > this.x))
        throw new ArrayIndexOutOfBoundsException();
    else
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

  /**
   * Skriver ut matris eller tableau p&aring; sk&auml;rm.
   */
  public void print() {
    if (tableau)
      printTableau(PADDING, SPACE);
    else
      printMatrix(PADDING, SPACE);
  }

  /**
   * Skriver ut matris eller tableau p&aring; sk&auml;rm.
   *
   * @param pad Utfyllnad f&ouml;r element
   * @param space Utfyllnad mellan element
   */
  public void print(int pad, int space) {
    if (tableau)
      printTableau(pad, space);
    else
      printMatrix(pad, space);
  }

  /**
   * Skriver ut matrisen p&aring; sk&auml;rmen
   *
   * @param pad Utfyllnad f&ouml;r utskrift
   */
  private void printMatrix(int pad, int space) {
    for (int i = 0; i < x; ++i) {
      for (int j = 0; j < y; ++j) {
        printPadd(getE(i, j), pad);
        printSpaces(space);
      }
      printNewline();
    }
  }

  /**
   * Skriver ut tableau p&aring; sk&auml;rmen
   *
   * @param pad Utfyllnad f&ouml;r utskrift av element
   * @param space Utfyllnad mellan element
   */
  private void printTableau(int pad, int space) {
    for (int i = 0; i < x; ++i) {
      for (int j = 0; j < y; ++j) {
        printPadd(getE(i, j), pad);
        printSpaces(space);
      }
      printVertical();
      printPadd(getE(i, y), pad);
      printNewline();
    }
    printDashes(y, pad, space);
    printCross();
    printDashes(1, pad, space);
    printNewline();
    for (int j = 0; j < y; ++j) {
      printPadd(getE(x, j), pad);
      printSpaces(space);
    }
    printVertical();
    printPadd(getE(x, y), pad);
    printNewline();
  }

  /**
   * Fyll ut utrymmet f&ouml;r utskrift av element.
   *
   * @param e Element
   * @param pad Utfyllnad f&ouml;r utskrift av element
   */
  private void printPadd(double e, int pad) {
    String s = new Double(e).toString();
    for (int k = 0; k < pad - s.length(); ++k)
      System.out.print(" ");
    System.out.print(s);
  }

  /**
   * Utskrift av mellanrum mellan elementen.
   *
   * @param space Mellanrum mellan elementen
   */
  private void printSpaces(int space) {
    for (int k = 0; k < space; ++k)
      System.out.print(" ");
  }

  /**
   * Ny rad
   */
  private void printNewline() {
    System.out.println();
  }

  /**
   * Horisontell linje
   *
   * @param length Antalet element inkl. pad som skrivs ut
   * @param pad Utfyllnad f&ouml;r utskrift av element
   * @param space Utfyllnad mellan element
   */
  private void printDashes(int length, int pad, int space) {
    for (int k = 0; k < length; ++k) {
      for (int u = 0; u < pad; ++u)
        System.out.print("-");
      for (int v = 0; v < space; ++v)
        System.out.print("-");
    }
  }

  /**
   * En vertikal linje
   */
  private void printVertical() {
    System.out.print("|");
  }

  /**
   * Ett kryss
   */
  private void printCross() {
    System.out.print("+");
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
        n.setE(i, j, getE(i, j) + m.getE(i, j));
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
        n.setE(i, j, getE(i, j) - m.getE(i, j));
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
    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < mY; ++j) {
        n.setE(i, j, ZERO);
        for (int k = 0; k < y; ++k)
          n.setE(i, j, (n.getE(i, j) +
          getE(i, k) * m.getE(k, j)));
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
    m = copy();
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
        if (getE(j, i) == 0)
          continue;
        error = false;
        for (int k = 0; k < x; ++k) {
          double s = getE(i, k);
          n.setE(i, k, getE(j, k));
          n.setE(j, k, s);
          s = e.getE(i, k);
          e.setE(i, k, e.getE(j, k));
          e.setE(j, k, s);
        }
        double t = (1 / getE(i, i));
        for (int k = 0; k < x; ++k) {
          n.setE(i, k, t * getE(i, k));
          e.setE(i, k, t * e.getE(i, k));
        }
        for (int u = 0; u < x; ++u) {
          if (u == i)
            continue;
          t = -getE(u, i);
          for (int k = 0; k < x; ++k) {
            n.setE(u, k, getE(u, k)
              + t * getE(i, k));
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
