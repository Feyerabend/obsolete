
// BooleanExp.java
// Set Lonnert, 1999

abstract class BooleanExp {
  abstract boolean evaluate(Context _con);
  abstract BooleanExp replace(String _name, BooleanExp _bool);
  abstract BooleanExp copy();
}
