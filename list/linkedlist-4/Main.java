

abstract class Node extends Object { }

class ListNode extends Node {
  Object value;
  Object key;
  ListNode prev, next;
  ListNode(Object _key, Object _value) {
    key = _key;
    value = _value;
  }
  public boolean equals(Object o) {
    if (o instanceof ListNode) {
      ListNode u = (ListNode) o;
      // the links themselves not tested
      if (key.equals(u.key) && value.equals(u.value)) {
        return true;
      }
    }
    return false;
  }
}

class LinkedList implements Cloneable {
  ListNode head;

  boolean isEmpty() {
    return (head == null);
  }
  void insert(ListNode x) {
    x.next = head;
    if (head != null) {
      head.prev = x;
    }
    head = x;
    x.prev = null;
  }
  void delete(ListNode x) {
    if (x.prev != null) {
      x.prev.next = x.next;
    } else {
      head = x.next;
    }
    if (x.next != null) {
      x.next.prev = x.prev;
    }
  }
  void delete(Object k) {
    delete(searchNode(k));
  }
  ListNode searchNode(Object k) {
    ListNode z = head;
    while (z != null && !z.key.equals(k)) {
      z = z.next;
    }
    return z;
  }
  Object search(Object k) {
    ListNode z = searchNode(k);
    if (z != null) {
      return z.value;
    }
    return z;
  }
  void walk() {
    ListNode x = head;
    while (x != null) {
      System.out.println(" Key: " + x.key + ", Value: " + x.value);
      x = x.next;
    }
  }
  public Object clone() {
    LinkedList o = null;
    try {
      o = (LinkedList) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new InternalError();
    }
    // handle only
    o.head = (ListNode) head;
    return o;
  }
  Object copy() { // doesn't work as the list is reversed!
    LinkedList o = new LinkedList();
    ListNode x = head;
    while (x != null) {
      o.insert(new ListNode(x.key, x.value));
      x = x.next;
    }
    return o;
  }
  public boolean equals(Object o) {
    boolean flag = true;
    if (o instanceof LinkedList) {
      LinkedList p = (LinkedList) o;
      ListNode y = p.head;
      ListNode x = head;
      while ((x != null) && (y != null)) {
        flag &=
          x.key.equals(y.key) &&
          x.value.equals(y.value);
        System.out.println("x.key.equals(y.key) -> (" + x.key + ")=(" + y.key + ")");
        System.out.println("x.value.equals(y.value) -> (" + x.value + ")=(" + y.value + ")");
        x = x.next; y = y.next;
      }
      return flag;
    }
    return false;
  }

}

public class Main {
  public static void main(String args[]) {

    // original list
    LinkedList b = new LinkedList();

    // insertion
    b.insert(new ListNode(new Integer(6), "Macaper"));
    b.insert(new ListNode(new Integer(10), "Hyperbolis"));
    b.insert(new ListNode(new Integer(2), "Margolia"));
    b.insert(new ListNode(new Integer(8), "Hyperbolis"));
    b.insert(new ListNode(new Integer(12), "Moree"));
    b.insert(new ListNode(new Integer(4), "Grounded"));

    // clone the original
    LinkedList a = (LinkedList) b.clone();

    // and a local var set
    LinkedList e = b;

    // search object '15' non-existant
    System.out.println(" Search object '15' " + b.search(new Integer(15)));

    // search object '2' existant
    System.out.println(" Search object '2' " + b.search(new Integer(2)));

    // delete object '6' and walk and print
    b.delete(new Integer(6));
    System.out.println();
    b.walk();
    System.out.println();

    // print the clone -- it's only a handle
    System.out.println("LinkedList a: ");
    System.out.println("a != b is " + (a != b));
    System.out.println("a.getClass() == b.getClass() is " + (a.getClass() == b.getClass()));
    System.out.println("a.equals(b) is " + (a.equals(b)));
    a.walk();

    // print the local var
    System.out.println("LinkedList e: ");
    e.walk();

    System.out.println("LinkedList c: ");
    LinkedList c = (LinkedList) b.copy();

    System.out.println("c != b is " + (c != b));
    System.out.println("c.getClass() == b.getClass() is " + (c.getClass() == b.getClass()));
    System.out.println("c.equals(b) is " + (c.equals(b)));

    c.walk();
  }
}



/*

Creates and returns a copy of this object. The precise meaning of "copy" may
depend on the class of the object. The general intent is that, for any object x,
the expression:

 x.clone() != x

will be true, and that the expression:

 x.clone().getClass() == x.getClass()

will be true, but these are not absolute requirements. While it is typically
the case that:

 x.clone().equals(x)

will be true, this is not an absolute requirement. Copying an object will typically
entail creating a new instance of its class, but it also may require copying of
internal data structures as well. No constructors are called.

The method clone for class Object performs a specific cloning operation. First, if
the class of this object does not implement the interface Cloneable, then a
CloneNotSupportedException is thrown. Note that all arrays are considered to implement
the interface Cloneable. Otherwise, this method creates a new instance of the class
of this object and initializes all its fields with exactly the contents of the
corresponding fields of this object, as if by assignment; the contents of the fields
are not themselves cloned. Thus, this method performs a "shallow copy" of this object,
not a "deep copy" operation.

The class Object does not itself implement the interface Cloneable, so calling the
clone method on an object whose class is Object will result in throwing an exception
at run time. The clone method is implemented by the class Object as a convenient,
general utility for subclasses that implement the interface Cloneable, possibly
also overriding the clone method, in which case the overriding definition can refer
to this utility definition by the call:

 super.clone()

*/
