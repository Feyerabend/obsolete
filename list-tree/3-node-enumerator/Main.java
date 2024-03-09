

class Node {
  Object h;
  Node t;
  Node(Object _h, Node _t) {
    h = _h;
    t = _t;
  }

  boolean isEmpty() {
    return (h == null) && (t == null);
  }
  int length() {
    if (isEmpty()) {
      return 0;
    } else if (t == null) {
      return 1;
    } else {
      return 1 + t.length();
    }
  }
  Object index(int i) {
    if (i < 0) {
      return null;
    } else if (i == 0) {
      return h;
    } else if (t != null) {
      return t.index(i - 1);
    } else {
      return null;
    }
  }
  java.util.Enumeration elements() {
    return this.new Enumerator();
  }

  class Enumerator implements java.util.Enumeration {
    int i = 0;
    public boolean hasMoreElements() {
      return i < Node.this.length();
    }
    public Object nextElement() {
      synchronized (Node.this) {
        if (i < Node.this.length()) {
          return Node.this.index(i++);
        }
      }
      throw new java.util.NoSuchElementException("Node");
    }
  }
}


public class Main {
  public static void main(String args[]) {
    Node list =
      new Node(new String("annat objekt"),
        new Node(new Integer(19),
          new Node(new Integer(5), null)));
    for (Enumeration e = list.elements() ; e.hasMoreElements() ;) {
      System.out.println(e.nextElement());
    }
  }
}
