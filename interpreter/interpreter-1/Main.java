
// Main.java
// Set Lonnert, 1999

public class Main {
  public static void main(String args[]) {
    BooleanExp b;
    Context c = new Context();

    VariableExp x = new VariableExp("x");
    VariableExp y = new VariableExp("y");

    b = new OrExp(
      new AndExp(new TrueExp(), x),
      new AndExp(y, new NotExp(x)));

    c.assign(x, false);
    c.assign(y, true);

    System.out.println("x <- " + c.lookup("x"));
    System.out.println("y <- " + c.lookup("y"));
    System.out.println(b + " -> " + b.evaluate(c));

    VariableExp z = new VariableExp("z");
    NotExp not_z = new NotExp(z);

    BooleanExp r = b.replace("y", not_z);

    c.assign(z, true);

    System.out.println("z <- " + c.lookup("z"));    
    System.out.println(r + " -> " + r.evaluate(c));

    BooleanExp bb;

    bb = new OrExp(b, b.copy());

    System.out.println(bb + " -> " + bb.evaluate(c));
  }
}
