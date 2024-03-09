


class Node {
  Object h;
  Node t;
  Node(Object _h, Node _t) {
    h = _h;
    t = _t;
  }
}

class List {
  Node root = null;

  boolean isEmpty() {
    return root == null;
  }
  void add(Object v) {
    root = new Node(v, root);
  }
  ListPointer elements() {
    return new ListPointer(root);
  }
}

class ListPointer {
  Node p;
  ListPointer(Node _p) {
    p = _p;
  }
    
  boolean atEnd() {
    return p == null;
  }
  void next() {
    if (p != null) {
      p = p.t;
    }
  }
  Object current() {
    if (p == null) {
      return null;
    } else {
      return p.h;
    }
  }
}

public class Main {
  public static void main(String args[]) {
    Integer x = new Integer(5);
    Integer y = new Integer(19);
    String z = new String("annat objekt");

    List list = new List();
    list.add(x);
    list.add(y);
    list.add(z);

    ListPointer j = list.elements();
    while (! j.atEnd()) {
      System.out.println(j.current());
      j.next();
    }
  }
}
