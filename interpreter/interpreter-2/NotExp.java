
// NotExp.java
// Set Lonnert, 1999

public class NotExp extends BooleanExp {
  BooleanExp op1;
  public NotExp(BooleanExp _op1) {
    op1 = _op1;
  }
  boolean evaluate(Context _rho) {
    return (!op1.evaluate(_rho));
  }
  BooleanExp copy() {
    return new NotExp(op1.copy());
  }
  BooleanExp replace(String _name, BooleanExp _bool) {
    return new NotExp(op1.replace(_name, _bool));
  }
  public String toString() {
    return "(not " + op1.toString() + ")";
  }
}
