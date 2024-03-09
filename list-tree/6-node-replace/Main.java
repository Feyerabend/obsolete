

abstract class Node {
  Remove removeFunction = new Remove();
  Add addFunction = new Add();
  Replace replaceFunction = new Replace();
  abstract Node remove(Object o);
  abstract Node add(Object o);
  abstract Node replace(Object r, Object o);

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

  Node remove(Object o) {
    return removeFunction.forNextNode(t, n, o);
  }
  Node add(Object o) {
    return addFunction.forNextNode(t, n, o);
  }
  Node replace(Object r, Object o) {
    return replaceFunction.forNextNode(t, n, r, o);
  }

  public String toString() {
    return "new " + NextNode.class.getName() + "(" + t + ",\n " + n + ")\n";
  }
}

class EmptyNode extends Node {
  Node remove(Object o) {
    return removeFunction.forEmptyNode(o);
  }
  Node add(Object o) {
    return addFunction.forEmptyNode(o);
  }
  Node replace(Object r, Object o) {
    return replaceFunction.forEmptyNode(r, o);
  }

  public String toString() {
    return "new " + EmptyNode.class.getName() + "()\n";
  }
}

class Remove {
  Node forEmptyNode(Object o) {
    return new EmptyNode();
  }
  Node forNextNode(Object t, Node n, Object o) {
    if (o.equals(t)) {
      return n.remove(o);
    } else {
      return new NextNode(t, n.remove(o));
    }
  }

  public String toString() {
    return "new " + Remove.class.getName() + "()\n";
  }
}

class Add {
  Node forEmptyNode(Object o) {
    return new NextNode(o, new EmptyNode());
  }
  Node forNextNode(Object t, Node n, Object o) {
    return new NextNode(o, n.add(t));
  }

  public String toString() {
    return "new " + Add.class.getName() + "()\n";
  }
}

class Replace {
  Node forEmptyNode(Object r, Object o) {
    return new EmptyNode();
  }
  Node forNextNode(Object t, Node n, Object r, Object o) {
    if (o.equals(t)) {
      return new NextNode(r, n.replace(r, o));
    } else {
      return new NextNode(t, n.replace(r, o));
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
    System.out.println(n.replace("Misc", "Hyperbolis"));
  }
}
