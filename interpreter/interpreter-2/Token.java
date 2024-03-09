
// Token.java
// Set Lonnert, 1999

public class Token {
  String name;
  int value; // int representation
  int token; // type

  Token(int _tok) {
    token = _tok; name = ""; value = 0;
  }
  Token(int _tok, String _n) {
    token = _tok; name = _n; value = 0;
  }
  Token(int _tok, int _val) {
    token = _tok; name = ""; value = _val;
  }
  public String toString() {
    switch (token) {
      case Symbol.IDENT   : return "Identifier"; 
      case Symbol.TRUE    : return "True";
      case Symbol.LPAREN  : return "Left bracket"; 
      case Symbol.RPAREN  : return "Right bracket";
      case Symbol.NOT     : return "Not";
      case Symbol.AND     : return "And";
      case Symbol.OR      : return "Or";
      case Symbol.UNKNOWN : return "Unknown";
      default             : return "";
    }
  }
}
