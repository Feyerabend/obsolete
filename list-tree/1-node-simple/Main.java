

class Node {
  Object h;
  Node t;
  Node(Object _h, Node _t) {
    h = _h;
    t = _t;
  }

  public String toString() {
    return "new " + Node.class.getName() + "(" + h + ",\n " + t + ")\n";
  }
}

public class Main {
  public static void main(String args[]) {
    Node list =
      new Node(new Float(23.89),
        new Node(new Boolean(true),
          new Node(new String("Ruiiing"),
            new Node(new Integer(5),
              new Node(new Integer(19),
                new Node(new String("ett objekt"), null))))));
    System.out.println(list);
  }
}
