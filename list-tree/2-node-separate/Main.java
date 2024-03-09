
/* REKURSION

En av de mest "klassiska" strukturer som förekommer i programmering är rekursion.
Fastän det i imperativa språk ibland undviks på grund av ineffektivitet, är dess struktur
ofta så givande att det ur återanvändningssynpunkt kan ha många fördelar att använda
det. Rekursion är inte bara ett välkänt begrepp inom matematiken utan även datateknik
i allmänhet, där t.ex. funktionella språk som LISP är byggt mycket utifrån
rekursionsbegreppet.

Rekursion innebär att en funktion kan återanropa sig själv ett flertal (otal) gånger.
Vanligt är att funktionen implementeras som rutiner där informationer sparas vid varje
självanrop. Senare nyttjas informationerna då rutinen återvänder rekursivt.

Längden hos en given lista t byggd av noder kan erhållas genom att för varje kontroll
av nod lägga till 1. Vid returen då listans slut nås, summeras additionerna
och returneras till det ursprungliga anropet.

class Node {
  Node t;
  ...
  int length() {
    if (t == null) {
      return 1;
    } else {
      return 1 + t.length();
    }
  }
}



Ett annat exempel är utskrift av nodernas nycklar i ett binärt träd, där en
vandring (traversering) genom hela trädet förbi varje nod innebär rekursion
över först vänster, sedan utskrift och sedan rekursion av höger subträd.


class TreeNode {
  Object key;
  TreeNode left, right;
  ...
}

class BinarySearchTree {
  ...
  void inorderWalk(TreeNode x) {
    if (x != null) {
      inorderWalk(x.left);
      System.out.println(x.key);
      inorderWalk(x.right);
    }
  }
}

*/

import java.util.NoSuchElementException;

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
  Iterator elements() {
    return new Iterator(root);
  }
}

class Iterator {
  Node i;
  Iterator(Node _i) {
    i = _i;
  }

  boolean hasMoreElements() {
    return i != null;
  }
  Object nextElement() {
    synchronized (this) {
      if (i != null) {
        Node x = i;
        i = i.t;
        return x.h;
      }
    }
    throw new NoSuchElementException("Node");
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

    for (Iterator i = list.elements(); i.hasMoreElements(); ) {
      System.out.println(i.nextElement());
    }
  }
}
