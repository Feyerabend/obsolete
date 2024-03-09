
// Parser.java
// Set Lonnert, 1999

import java.io.EOFException;

public class Parser {
  Lexer lex;

  Parser() { }

  class SyntaxErrorException extends Exception {
    SyntaxErrorException() { }
    SyntaxErrorException(String s) {
      super(s);
    }
  }

  void match(int _t)
  throws SyntaxErrorException {
    Token tok = lex.current_token();
    try {
      lex.next_token();
    } catch (EOFException e) { return; }
    if (tok.token != _t) {
      throw new SyntaxErrorException(
        "Expected '" + new Token(_t).toString() + "'"
          + ", found '" + tok.toString() + "'");
    }
  }

  BooleanExp parse(Lexer _l)
  throws SyntaxErrorException {
    lex = _l;
    return parseExpr();
  }

  BooleanExp parseExpr()
  throws SyntaxErrorException {
    Token tok = lex.current_token();
    if (tok.token == Symbol.IDENT) {
      match(Symbol.IDENT);
      VariableExp var = new VariableExp(tok.name);
      return (BooleanExp) var;
    } else if (tok.token == Symbol.TRUE) {
      match(Symbol.TRUE);
      TrueExp _true = new TrueExp();
      return (BooleanExp) _true;
    } else if (tok.token == Symbol.NOT) {
      match(Symbol.NOT);
      BooleanExp expr1 = parseExpr();
      NotExp not = new NotExp(expr1);
      return (BooleanExp) not;
    } else if (tok.token == Symbol.OR) {
      match(Symbol.OR);
      BooleanExp expr1 = parseExpr();
      BooleanExp expr2 = parseExpr();
      OrExp or = new OrExp(expr1, expr2);
      return (BooleanExp) or;
    } else if (tok.token == Symbol.AND) {
      match(Symbol.AND);
      BooleanExp expr1 = parseExpr();
      BooleanExp expr2 = parseExpr();
      AndExp and = new AndExp(expr1, expr2);
      return (BooleanExp) and;
    } else if (tok.token == Symbol.LPAREN) {
      match(Symbol.LPAREN);
      BooleanExp expr = parseExpr();
      match(Symbol.RPAREN);
      return expr;
    }
    throw new SyntaxErrorException(
      "Internal error: Unrecognized term");
  }
}
