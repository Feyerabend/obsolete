

abstract class Node {
  abstract Node accept(NodeVisitor ask);

  public String toString() {
    return "new " + Node.class.getName() + "()\n";
  }
}

class NextNode extends Node {
  Object t;
  Node n;
  NextNode(Object _t, Node _n) {
    t = _t;
    n = _n;
  }

  Node accept(NodeVisitor ask) {
    return ask.forNextNode(t, n);
  }

  public String toString() {
    return "new " + NextNode.class.getName() + "(" + t + ", " + n + ")\n";
  }
}

class EmptyNode extends Node {

  Node accept(NodeVisitor ask) {
    return ask.forEmptyNode();
  }

  public String toString() {
    return "new " + EmptyNode.class.getName() + "()\n";
  }
}

interface NodeVisitor {
  Node forEmptyNode();
  Node forNextNode(Object t, Node n);
}

interface EqualObjects extends NodeVisitor {
  boolean equal(Object x, Object y);
}

abstract class Comparable implements EqualObjects {
  public boolean equal(Object o, Object t) {
    EqualityFactory f =
      new StrictStringEqFactory(); // Strict or not
    return f.newEquality().isEqual(o, t);
  }
}

class Remove extends Comparable {
  Object o;
  Remove(Object _o) {
    o = _o;
  }

  public Node forEmptyNode() {
    return new EmptyNode();
  }
  public Node forNextNode(Object t, Node n) {
    if (equal(o, t)) {
      return n.accept(this);
    } else {
      return new NextNode(t, n.accept(this));
    }
  }

  public String toString() {
    return "new " + Remove.class.getName() + "()\n";
  }
}

class Add extends Comparable {
  Object o;
  Add(Object _o) {
    o = _o;
  }

  public Node forEmptyNode() {
    return new NextNode(o, new EmptyNode());
  }
  public Node forNextNode(Object t, Node n) {
    return new NextNode(t, n.accept(this));
  }

  public String toString() {
    return "new " + Add.class.getName() + "()\n";
  }
}

class Replace extends Comparable {
  Object r;
  Object o;
  Replace(Object _r, Object _o) {
    r = _r;
    o = _o;
  }

  public Node forEmptyNode() {
    return new EmptyNode();
  }
  public Node forNextNode(Object t, Node n) {
    if (equal(o, t)) { // (o.equals(t))
      return new NextNode(r, n.accept(this));
    } else {
      return new NextNode(t, n.accept(this));
    }
  }

  public String toString() {
    return "new " + Replace.class.getName() + "()\n";
  }
}

interface EqualityFactory {
  public abstract Equality newEquality();
}

class StringEqFactory implements EqualityFactory {
  public Equality newEquality() {
    return new StringEq();
  }
}

class StrictStringEqFactory implements EqualityFactory {
  public Equality newEquality() {
    return new StrictStringEq();
  }
}

interface Equality {
  public abstract boolean isEqual(Object x, Object y);
}

class Eq implements Equality {
  public boolean isEqual(Object x, Object y) {
    if ((x instanceof Integer) && (y instanceof Integer)) {
      return ((Integer) x).equals((Integer) y);
    }
    return false;
  }
}

class StringEq extends Eq {
  public boolean isEqual(Object x, Object y) {
    if ((x instanceof String) && (y instanceof String)) {
      return ((String) x).equalsIgnoreCase((String) y);
    }
    return super.isEqual(x, y);
  }
}

class StrictStringEq extends Eq {
  public boolean isEqual(Object x, Object y) {
    if ((x instanceof String) && (y instanceof String)) {
      return ((String) x).equals((String) y);
    }
    return super.isEqual(x, y);
  }
}

public class Main {
  public static void main(String args[]) {
    NextNode n =
    new NextNode("Hyperbolis",
     new NextNode(new Integer(24),
      new NextNode("Margolia",
        new NextNode("HYPERBOLIS",
          new NextNode("Grounded", new EmptyNode())))));
    System.out.println(n.accept(new Replace("Misc", new Integer(24))));
    System.out.println(n.accept(new Add(new Integer(777))));
  }
}
