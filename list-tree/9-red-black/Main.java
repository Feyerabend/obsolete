
/*  Red-Black Tree
 *  Set Lonnert, 1999
 *   See:
 *     Introduction to Algorithms,
 *     eds. Thomas H. Cormen, Charles E. Leiserson & Ronald L. Rivest
 *     (1990), 7th print (Cambridge, Mass: MIT Press, 1996).
 */

import java.util.*;

abstract class Node { }

class RedBlackTreeNode extends Node {
  Object key, value;
  int color;
  RedBlackTreeNode right, left, parent;
  RedBlackTreeNode(Object _key, Object _value) {
    key = _key;
    value = _value;
  }
}

class RedBlackTree {
  RedBlackTreeNode root;
  final int RED = 0, BLACK = 1;

  RedBlackTreeNode nil =
    new RedBlackTreeNode("Nil", "Nil"); // special!

  public RedBlackTree() { }

  public boolean isEmpty() {
    return root == null;
  }

  int color(RedBlackTreeNode x) {
    if (x == null) {
      return BLACK;
    } else {
      return x.color;
    }
  }

  RedBlackTreeNode minimum(RedBlackTreeNode x) {
    while (x.left != null) {
      x = x.left;
    }
    return x;
  }

  RedBlackTreeNode maximum(RedBlackTreeNode x) {
    while (x.right != null) {
      x = x.right;
    }
    return x;
  }

  RedBlackTreeNode successor(RedBlackTreeNode x) {
    if (x.right != null) {
      return minimum(x.right);
    }
    RedBlackTreeNode y = x.parent;
    while ((y != null) && (x == y.right)) {
      x = y;
      y = x.parent;
    }
    return y;
  }

  RedBlackTreeNode predecessor(RedBlackTreeNode x) {
    if (x.left != null) {
      return maximum(x.left);
    }
    RedBlackTreeNode y = x.parent;
    while ((y != null) && (x == y.left)) {
      x = y;
      y = x.parent;
    }
    return y;
  }

  void rotateLeft(RedBlackTreeNode x) {
    RedBlackTreeNode y = x.right;
    x.right = y.left;
    if (y.left != null) {
      y.left.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
      root = y;
    } else {
      if (x == x.parent.left) {
        x.parent.left = y;
      } else {
        x.parent.right = y;
      }
    }
    y.left = x;
    x.parent = y;
  }

  void rotateRight(RedBlackTreeNode y) {
    RedBlackTreeNode x = y.left;
    y.left = x.right;
    if (x.right != null) {
      x.right.parent = y;
    }
    x.parent = y.parent;
    if (y.parent == null) {
      root = x;
    } else {
      if (y == y.parent.left) {
        y.parent.left = x;
      } else {
        y.parent.right = x;
      }
    }
    x.right = y;
    y.parent = x;
  } 

  synchronized RedBlackTreeNode treeInsert(RedBlackTreeNode z) {
    RedBlackTreeNode x = root, y = null;
    int i;
    while (x != null) {
      y = x;
      i = comp(x.key, z.key);
      if (i > 0) {
        x = x.left;
      } else {
        x = x.right;
      }
    }
    z.parent = y;
    if (y == null) {
      root = z;
    } else {
      i = comp(y.key, z.key);
      if (i > 0) {
        y.left = z;
      } else if (i < 0) {
        y.right = z;
      } else {
        Object t = z.value;
        z.value = y.value;
        y.value = t;
        return z;
      }
    }
    return null;
  }

  synchronized RedBlackTreeNode insert(RedBlackTreeNode x) {
    RedBlackTreeNode y;

    y = treeInsert(x);
    if (y != null) return y;

    x.color = RED;
    while ((x != root) && (x.parent.color == RED)) {

      if (x.parent == x.parent.parent.left) {
        y = x.parent.parent.right;
        if ((y != null) && (y.color == RED)) {
          x.parent.color = BLACK;
          y.color = BLACK;
          x.parent.parent.color = RED;
          x = x.parent.parent;
        } else {
          if (x == x.parent.right) {
            x = x.parent;
            rotateLeft(x);
          }
          x.parent.color = BLACK;
          x.parent.parent.color = RED;
          rotateRight(x.parent.parent);
        }
      } else {
        y = x.parent.parent.left;
        if ((y != null) && (y.color == RED)) {
          x.parent.color = BLACK;
          y.color = BLACK;
          x.parent.parent.color = RED;
          x = x.parent.parent;
        } else {
          if (x == x.parent.left) {
            x = x.parent;
            rotateRight(x);
          }
          x.parent.color = BLACK;
          x.parent.parent.color = RED;
          rotateLeft(x.parent.parent);
        }
      }
    }
    root.color = BLACK;
    return null;
  }

  static int comp(Object o, Object p) {
    return ((String) o).compareTo((String) p);
  }

  RedBlackTreeNode search(RedBlackTreeNode x, Object k) {
    final int i = comp(k, x.key);
    if ((x == null) || (i == 0)) {
      return x;
    }
    if (i < 0) {
      return search(x.left, k);
    } else {
      return search(x.right, k);
    }
  }

  synchronized RedBlackTreeNode delete(RedBlackTreeNode z) {
    RedBlackTreeNode x = null, y = null;

    nil.color = BLACK;
    if ((z.left == null) || (z.right == null)) {
      y = z;
    } else {
      y = successor(z);
    }
    if (y.left != null) {
      x = y.left;
    } else {
      if (y.right == null) {
        x = nil;
      } else {
        x = y.right;
      }
    }
    x.parent = y.parent;
    if (y.parent == null) {
      if (x == nil) {
        root = null;
      } else {
        root = x;
      }
    } else {
      if (y == y.parent.left) {
        if (x == nil) {
          y.parent.left = null;
        } else {
          y.parent.left = x;
        }
      } else {
        if (x == nil) {
          y.parent.right = null;
        } else {
          y.parent.right = x;
        }
      }
    }
    if (y != z) {
      z.key = y.key;
      z.value = y.value;
    }
    if (y.color == BLACK) {
      delete_fixup(x);
    }
    return y;
  }

  synchronized void delete_fixup(RedBlackTreeNode x) {
    RedBlackTreeNode w;

    if (x.parent == null) return;

    while ((x != root) && (x.color == BLACK)) {
      if (((x == nil) && (x.parent.left == null)) ||
          (x == x.parent.left)) {
        w = x.parent.right;
        if (color(w) == RED) {
          w.color = BLACK;
          w.parent.color = RED;
          rotateLeft(x.parent);
          w = x.parent.right;
        }
        if ((color(w.left) == BLACK)
            && (color(w.right) == BLACK)) {
          w.color = RED;
          x = x.parent;
        } else {
          if (color(w.right) == BLACK) {
            w.left.color = BLACK;
            w.color = RED;
            rotateRight(w);
            w = x.parent.right;
          }
          w.color = x.parent.color;
          x.parent.color = BLACK;
          w.right.color = BLACK;
          rotateLeft(x.parent);
          x = root;
        }
      } else {
        w = x.parent.left;
        if (color(w) == RED) {
          w.color = BLACK;
          w.parent.color = RED;
          rotateRight(x.parent);
          w = x.parent.left;
        }
        if ((color(w.left) == BLACK)
            && (color(w.right) == BLACK)) {
          w.color = RED;
          x = x.parent;
        } else {
          if (color(w.left) == BLACK) {
            w.right.color = BLACK;
            w.color = RED;
            rotateLeft(w);
            w = x.parent.left;
          }
          w.color = x.parent.color;
          x.parent.color = BLACK;
          w.left.color = BLACK;
          rotateRight(x.parent);
          x = root;
        }
      }
    }
    x.color = BLACK;
  }

  void inorderWalk(RedBlackTreeNode x) {
    if (x != null) {
      inorderWalk(x.left);
      System.out.println(" For key: " + x.key + ", value: " + x.value);
      inorderWalk(x.right);
    }
  }

  Enumeration elements() {
    return this.new Enumerator();
  }

  class Enumerator implements Enumeration {
    RedBlackTreeNode i =
      RedBlackTree.this.minimum(root);

    public boolean hasMoreElements() {
      return (i != null);
    }
    public Object nextElement() {
      synchronized (RedBlackTree.this) {
        if (i != null) {
          RedBlackTreeNode j = i;
          i = RedBlackTree.this.successor(i);
          return j.value;
        }
      }
      throw new NoSuchElementException("RedBlackTree");
    }
  }

}


public class Main {
  public static void main(String args[]) {

    RedBlackTree b = new RedBlackTree();

    // the order of insertion random
    RedBlackTreeNode s = new RedBlackTreeNode("Aoom", "Macaper");
    b.insert(s);

    b.insert(new RedBlackTreeNode("Lofty", "Yverboren"));
    b.insert(new RedBlackTreeNode("Permiculous", "Margolia"));
    b.insert(new RedBlackTreeNode("Dubioes", "Merkurius"));
    b.insert(new RedBlackTreeNode("Micro", "Moree"));
    b.insert(new RedBlackTreeNode("Doff", "Grounded"));
    b.insert(new RedBlackTreeNode("Blows", "Hyperbolisa"));

    // walk and print
    b.inorderWalk(b.root);
    System.out.println();

    // enumeration check
    for (Enumeration e = b.elements() ; e.hasMoreElements() ;) {
      System.out.println(e.nextElement());
    }

    // search Doff
    System.out.println(" Search Doff " + b.search(b.root, "Doff").value);
    System.out.println();

    // delete Aoom and walk and print
    b.delete(s);
    System.out.println();
    b.inorderWalk(b.root);
    System.out.println();

    // replace Blows and walk and print
    RedBlackTreeNode t = new RedBlackTreeNode("Blows", "Poff!");
    s = b.insert(t);
    System.out.println();
    b.inorderWalk(b.root);
  }
}
