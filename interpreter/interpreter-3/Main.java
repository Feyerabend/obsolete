
// Main.java
// Set Lonnert, 1999

public class Main {
  public static void main(String args[]) {
    NumberExp b;
    Context c = new Context();

    VariableExp x = new VariableExp("x");
    VariableExp y = new VariableExp("y");

    c.assign(x, 1);
    c.assign(y, 0);

    String input = "(% ~x y)";

    Lexer lex = new Lexer(input);
    lex.init();

    Parser p = new Parser();
    NumberExp expr = null;
    try {
      expr = p.parse(lex);
    } catch (Parser.SyntaxErrorException e) {
      System.out.println(e);
      System.exit(1);
    }

    System.out.println("x <- " + c.lookup("x"));
    System.out.println("y <- " + c.lookup("y"));
    System.out.println(expr + " -> " + expr.evaluate(c));

  }
}
