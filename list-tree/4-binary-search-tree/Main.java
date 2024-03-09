

abstract class Node { }

class TreeNode extends Node {
  Object value;
  int key;
  TreeNode left, right, parent;
  TreeNode(int _key, Object _value) {
    key = _key;
    value = _value;
  }
}

class BinarySearchTree {
  TreeNode root;
  TreeNode nil = new TreeNode(0, "nil"); // special

  boolean isEmpty() {
    return (root == null);
  }
  TreeNode minimum(TreeNode x) {
    while (x.left != null) {
      x = x.left;
    }
    return x;
  }
  TreeNode maximum(TreeNode x) {
    while (x.right != null) {
      x = x.right;
    }
    return x;
  }
  TreeNode successor(TreeNode x) {
    if (x.right != null) {
      return minimum(x.right);
    }
    TreeNode y = x.parent;
    while ((y != null) && (x == y.right)) {
      x = y;
      y = x.parent; // alt: y.parent :)
    }
    return y;
  }
  TreeNode predecessor(TreeNode x) {
    if (x.left != null) {
      return maximum(x.left);
    }
    TreeNode y = x.parent;
    while ((y != null) && (x == y.left)) {
      x = y;
      y = x.parent; // alt: y.parent :)
    }
    return y;
  }
  TreeNode insert(TreeNode z) {
    TreeNode x = root, y = null;
    while (x != null) {
      y = x;
      if (z.key < x.key) {
        x = x.left;
      } else {
        x = x.right;
      }
    }
    z.parent = y;
    if (y == null) {
      root = z;
    } else {
      if (z.key < y.key) {
        y.left = z;
      } else if (z.key > y.key) {
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
  TreeNode delete(TreeNode z) {
    TreeNode x = null, y = null;
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
    if (x != null) {
      x.parent = y.parent;
    }
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
      z.value = y.value; // if more fields copy them!
    }
    return y;
  }
  void inorderWalk(TreeNode x) {
    if (x != null) {
      inorderWalk(x.left);
      System.out.println(" For key: " + x.key + ", value: " + x.value);
      inorderWalk(x.right);
    }
  }
  TreeNode search(TreeNode x, int k) {
    if ((x == null) || (k == x.key)) {
      return x;
    }
    if (k < x.key) {
      return search(x.left, k);
    } else {
      return search(x.right, k);
    }
  }
}

public class Main {
  public static void main(String args[]) {

    BinarySearchTree b = new BinarySearchTree();

    // the order of insertion random
    TreeNode s = new TreeNode(6, "Macaper");
    b.insert(new TreeNode(10, "Hyperbolis"));
    b.insert(new TreeNode(2, "Margolia"));
    b.insert(s);
    b.insert(new TreeNode(8, "Hyperbolis"));
    b.insert(new TreeNode(12, "Moree"));
    b.insert(new TreeNode(4, "Grounded"));

    // walk and print
    b.inorderWalk(b.root);
    System.out.println();

    // search no 12
    System.out.println(" Search no 12 " + b.search(b.root, 12).value);
    System.out.println();

    // delete no 6 and walk and print
    b.delete(s); // b.delete(b.search(b.root, 6));
    System.out.println();
    b.inorderWalk(b.root);
    System.out.println();

    // replace no 4 and walk and print
    b.insert(new TreeNode(4, "Poff!"));
    System.out.println();
    b.inorderWalk(b.root);
  }
}
