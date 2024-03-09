
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
}


public class Main {
  public static void main(String args[]) {
    Integer x = new Integer(5);
    Integer y = new Integer(19);
    String z = new String("annat objekt");

    Node list = new Node(z, new Node(y, new Node(x, null)));

    for (int i = 0; i < list.length(); ++i) {
      System.out.println(list.index(i));
    }
  }
}
