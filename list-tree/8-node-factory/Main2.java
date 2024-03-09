
interface Comparable {
  String compare(Comparable o) throws IncomparableException;
  String order = "0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
}

class StringCompare implements Comparable {
  String s;
  StringCompare(String _s) {
    s = _s;
  }

  public String compare(Comparable o)
  throws IncomparableException {
    if (!(o instanceof StringCompare)) {
      throw new IncomparableException("StringCompare");
    }
    String t = ((StringCompare) o).s;
    int lenS, lenT, len;
    lenS = s.length();
    lenT = t.length();
    len = (lenS > lenT) ? lenT : lenS;
    for (int i = 0; i < len; ++i) {
      char x = s.charAt(i);
      char y = t.charAt(i);
      if (x != y) {
        if (order.indexOf(x) < order.indexOf(y)) {
          return s;
        } else {
          return t;
        }
      }
    }
    return (lenS > lenT) ? t : s;
  }
}

class IncomparableException extends Exception {
  IncomparableException() {
    super();
  }
  IncomparableException(String s) {
    super(s);
  }
}

public class Main2 {
  public static void main(String args[]) {
    String s = args[0];
    String t = args[1];
    StringCompare sc = new StringCompare(s);
    StringCompare tc = new StringCompare(t);
    try {
      System.out.println(s + " compare " + t + " --> " + sc.compare(tc));
    } catch (Exception e) { }
    System.out.println(s + " compareTo " + t + " --> " + s.compareTo(t));
  }
}


// yet another interface

interface Equality {
  boolean equals(Equality o);
}

class StringEqual implements Equality {
  String s;
  StringEqual(String _s) {
    s = _s;
  }
  public boolean equals(String t) {
    return s.eqalsIgnoreCase(t);
  }
  public boolean equals(Equality t) {
    return
      if (t instanceof StringEqual) {
        s.equalsIgnoreCase(t.s)
      };
  }
}
