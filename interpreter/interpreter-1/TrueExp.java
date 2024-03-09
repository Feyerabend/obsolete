
// TrueExp.java
// Set Lonnert, 1999

public class TrueExp extends BooleanExp {
  public TrueExp() { }
  boolean evaluate(Context _rho) {
    return true;
  }
  BooleanExp copy() {
    return new TrueExp();
  }
  BooleanExp replace(String _name, BooleanExp _bool) {
    return new TrueExp();
  }
  public String toString() {
    return "(" + true + ")";
  }
}
