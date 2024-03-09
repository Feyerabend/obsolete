
// Lexer.java
// Set Lonnert, 1999

import java.io.EOFException;

public class Lexer {
  static char next_char;
  static Token tok;
  static String input;
  static int next_pos;

  Lexer(String _i) {
    input = _i;
    next_pos = 0;
  }

  static void advance()
  throws EOFException { 
    if (next_pos < input.length()) {
      next_char = input.charAt(next_pos);
    } else {
      throw new EOFException();
    }
    ++next_pos;
  }

  static void init() {
    try {
      advance();
      next_token();
    } catch (EOFException e) {
      throw new NullPointerException("No entry");
    }
  }

  static Token current_token() {
    return tok;
  }

  static void consumeWhitespace() {
    while (Character.isWhitespace(next_char)) {
      try { advance(); }
      catch (EOFException e) { return; }
    }
  }

  static Token next_token()
  throws EOFException {
    consumeWhitespace();
    if (Character.isLetter(next_char)) {
      String identifier = "";
      do {
        identifier += next_char;
        advance();
      } while (Character.isLetterOrDigit(next_char));
      if ("and".equals(identifier)) {
        tok = new Token(Symbol.AND);
      } else if ("or".equals(identifier)) {
        tok = new Token(Symbol.OR);
      } else if ("not".equals(identifier)) {
        tok = new Token(Symbol.NOT);
      } else if ("true".equals(identifier)) {
        tok = new Token(Symbol.TRUE);
      } else {
        tok = new Token(Symbol.IDENT, identifier);
      }
    } else {
      switch (next_char) {
        case '(': advance(); tok = new Token(Symbol.LPAREN);  break;
        case ')': advance(); tok = new Token(Symbol.RPAREN);  break;
        default : advance(); tok = new Token(Symbol.UNKNOWN); break;
      }
    }
    return tok;
  }
}
