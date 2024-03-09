

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
    return "new " + NextNode.class.getName() + "(" + t + ",\n " + n + ")\n";
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

class Remove implements NodeVisitor {
  Object o;
  Remove(Object _o) {
    o = _o;
  }

  public Node forEmptyNode() {
    return new EmptyNode();
  }
  public Node forNextNode(Object t, Node n) {
    if (o.equals(t)) {
      return n.accept(this);
    } else {
      return new NextNode(t, n.accept(this));
    }
  }

  public String toString() {
    return "new " + Remove.class.getName() + "()\n";
  }
}

class Add implements NodeVisitor {
  Object o;
  Add(Object _o) {
    o = _o;
  }

  public Node forEmptyNode() {
    return new NextNode(o, new EmptyNode());
  }
  public Node forNextNode(Object t, Node n) {
    return new NextNode(o, n.accept(this));
  }

  public String toString() {
    return "new " + Add.class.getName() + "()\n";
  }
}

class Replace implements NodeVisitor {
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
    if (o.equals(t)) {
      return new NextNode(r, n.accept(this));
    } else {
      return new NextNode(t, n.accept(this));
    }
  }

  public String toString() {
    return "new " + Replace.class.getName() + "()\n";
  }
}

public class Main {
  public static void main(String args[]) {
    NextNode n =
    new NextNode("Hyperbolis",
      new NextNode("Margolia",
        new NextNode("Hyperbolis",
          new NextNode("Grounded", new EmptyNode())
        )
      )
    );
    System.out.println(n.accept(new Replace("Misc", "Hyperbolis")));
  }
}
