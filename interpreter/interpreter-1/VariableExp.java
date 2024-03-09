
// VariableExp.java
// Set Lonnert, 1999

public class VariableExp extends BooleanExp {
  String var;
  public VariableExp(String _name) {
    var = _name;
  }
  boolean evaluate(Context _rho) {
    return _rho.lookup(var);
  }
  BooleanExp copy() {
    return new VariableExp(var);
  }
  BooleanExp replace(String _name, BooleanExp _bool) {
    if (_name.equals(var)) {
      return _bool.copy();
    } else {
      return new VariableExp(var);
    }
  }
  public String toString() {
    return var;
  }
}
