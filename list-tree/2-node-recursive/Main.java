
/* REKURSION

En av de mest "klassiska" strukturer som f�rekommer i programmering �r rekursion.
Fast�n det i imperativa spr�k ibland undviks p� grund av ineffektivitet, �r dess struktur
ofta s� givande att det ur �teranv�ndningssynpunkt kan ha m�nga f�rdelar att anv�nda
det. Rekursion �r inte bara ett v�lk�nt begrepp inom matematiken utan �ven datateknik
i allm�nhet, d�r t.ex. funktionella spr�k som LISP �r byggt mycket utifr�n
rekursionsbegreppet.

Rekursion inneb�r att en funktion kan �teranropa sig sj�lv ett flertal (otal) g�nger.
Vanligt �r att funktionen implementeras som rutiner d�r informationer sparas vid varje
sj�lvanrop. Senare nyttjas informationerna d� rutinen �terv�nder rekursivt.

L�ngden hos en given lista t byggd av noder kan erh�llas genom att f�r varje kontroll
av nod l�gga till 1. Vid returen d� listans slut n�s, summeras additionerna
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



Ett annat exempel �r utskrift av nodernas nycklar i ett bin�rt tr�d, d�r en
vandring (traversering) genom hela tr�det f�rbi varje nod inneb�r rekursion
�ver f�rst v�nster, sedan utskrift och sedan rekursion av h�ger subtr�d.


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
