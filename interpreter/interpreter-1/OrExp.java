
// OrExp.java
// Set Lonnert, 1999

public class OrExp extends BooleanExp {
  BooleanExp op1, op2;
  public OrExp(BooleanExp _op1, BooleanExp _op2) {
    op1 = _op1;
    op2 = _op2;
  }
  boolean evaluate(Context _rho) {
    return (op1.evaluate(_rho) | op2.evaluate(_rho));
  }
  BooleanExp copy() {
    return new OrExp(op1.copy(), op2.copy());
  }
  BooleanExp replace(String _name, BooleanExp _bool) {
    return new OrExp(
      op1.replace(_name, _bool),
        op2.replace(_name, _bool));
  }
  public String toString() {
    return "(" + op1.toString() + " or " + op2.toString() + ")";
  }
}
