
// Main.java
// Set Lonnert, 1999

public class Main {
  public static void main(String args[]) {
    BooleanExp b;
    Context c = new Context();

    VariableExp x = new VariableExp("x");
    VariableExp y = new VariableExp("y");

    c.assign(x, true);
    c.assign(y, false);

    String input = "(or (and true x) (and y (not x)))";

    Lexer lex = new Lexer(input);
    lex.init();

    Parser p = new Parser();
    BooleanExp expr = null;
    try {
      expr = p.parse(lex);
    } catch (Parser.SyntaxErrorException e) {
      System.out.println(e);
      System.exit(1);
    }

    System.out.println("x <- " + c.lookup("x"));
    System.out.println("y <- " + c.lookup("y"));
    System.out.println(expr + " -> " + expr.evaluate(c));

    VariableExp z = new VariableExp("z");
    NotExp not_z = new NotExp(z);

    c.assign(z, true);

    BooleanExp r = expr.replace("y", not_z);

    System.out.println("z <- " + c.lookup("z"));
    System.out.println(r + " -> " + r.evaluate(c));
  }
}
