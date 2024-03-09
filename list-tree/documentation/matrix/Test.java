
public class Test {
  public static void main(String[] args) {
    Matrix t, u;
    t = new Matrix(2, 2);
    try {
      t.setE(0, 0, 2);
      t.setE(1, 0, 3);
    //  t.setE(2, 0, 1);

      t.setE(0, 1, 2);
      t.setE(1, 1, 9);
    //  t.setE(2, 1, 1);

    //  t.setE(0, 2, 1);
    //  t.setE(1, 2, 1);
    //  t.setE(2, 2, 1);

      PrintMatrix m = new PrintMatrix(t);
      System.out.println("t:");
      m.print();

      MatrixManipulate n = MatrixManipulate(t);
      System.out.println("square n:");
      Matrix w = n.invert().square();
      new PrintMatrix(w).print();

    } catch (NonInvertableMatrixException e) {
      System.out.println("Hm. Icke-inverterbar.");
    } catch (IncompatibleMatrixException e) {
      System.out.println("Hm, hm.");
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Fel vid inmatningen. Hm.");
    }
  }
}

/*
  private Matrix makePositiveGame() {
    Matrix t = new Matrix(x, y, true);
    for (int i = 0; i < x; ++i)
      for (int j = 0; j < y; ++j)
        setE(i, j, getE(i, j));
    for (int i = 0; i < x; ++i)
      t.setE(i, y + 1, 1);
    for (int j = 0; j < y; ++j)
      t.setE(x + 1, j, 1);
    t.setE(x + 1, y + 1, 0);
    return t;
  }
*/

