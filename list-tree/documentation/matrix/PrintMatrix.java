
/**
 * Klass f&ouml;r utskrift av numeriska matriser.
 * PrintMatrix.java
 *
 * @author Set Lonnert
 * @version 0.1, 1998-05-05
 */

package matrix;

import java.io.PrintStream;

public class PrintMatrix {

  /**
   * Instans av utskriftsstr&ouml;m
   */
  protected PrintStream printstream;

  /**
   * Instans av matris f&ouml;r utskrift
   */
  protected Matrix matrix;

  /**
   * Utfyllnad f&ouml;r utskrift av element
   */
  protected static final int PADDING = 10;

  /**
   * Utfyllnad mellan element f&ouml;r utskrift
   */
  protected static final int SPACE = 5;

  /**
   * Tom matris
   */
  public PrintMatrix() {
  }

  /**
   * Skapar ny utskriftsinstans givet matris
   *
   * @param _matrix Intern matris f&ouml;r utskrift.
   */
  public PrintMatrix(Matrix _matrix) {
    matrix = _matrix;
    printstream = System.out;
  }

  /**
   * Skapar ny utskriftsinstans givet matris och utstkriftsstr&ouml;m
   *
   * @param _matrix Intern matris f&ouml;r utskrift.
   * @param _printstream
   */
  public PrintMatrix(Matrix _matrix, PrintStream _printstream) {
    matrix = _matrix;
    printstream = _printstream;
  }

  /**
   * S&auml;tter instansen av en matris
   *
   * @param _matrix Intern matris f&ouml;r utskrift.
   */
  public void setMatrix(Matrix _matrix) {
    matrix = _matrix;
  }

  /**
   * S&auml;tter instansen av en utskriftsstr&ouml;m
   *
   * @param _printstream  f&ouml;r utskrift.
   */
  public void setPrintStream(PrintStream _printstream) {
    printstream = _printstream;
  }

  /**
   * Skriver ut en matris
   */
  public void print() {
    printMatrix(PADDING, SPACE);
  }

  /**
   * Skriver ut matris
   *
   * @param pad Utfyllnad f&ouml;r element
   * @param space Utfyllnad mellan element
   */
  public void print(int pad, int space) {
    printMatrix(pad, space);
  }

  /**
   * Skriver ut matrisen p&aring; sk&auml;rmen
   *
   * @param pad Utfyllnad f&ouml;r utskrift
   */
  private void printMatrix(int pad, int space) {
    for (int i = 0; i < matrix.getX(); ++i) {
      for (int j = 0; j < matrix.getY(); ++j) {
        printPadd(matrix.getE(i, j), pad);
        printSpaces(space);
      }
      printNewline();
    }
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
      printstream.print(" ");
    printstream.print(s);
  }

  /**
   * Utskrift av mellanrum mellan elementen.
   *
   * @param space Mellanrum mellan elementen
   */
  private void printSpaces(int space) {
    for (int k = 0; k < space; ++k)
      printstream.print(" ");
  }

  /**
   * Ny rad
   */
  private void printNewline() {
    printstream.println();
  }
}
