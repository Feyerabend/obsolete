

abstract class Node { }

class ListNode extends Node {
  Object value;
  int key;
  ListNode prev, next;
  ListNode(int _key, Object _value) {
    key = _key;
    value = _value;
  }
}

class LinkedList {
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
  ListNode search(int k) {
    ListNode z = head;
    while (z != null && z.key != k) {
      z = z.next;
    }
    return z;
  }
  void walk() {
    ListNode x = head;
    while (x != null) {
      System.out.println(" Key: " + x.key + " Value: " + x.value);
      x = x.next;
    }
  }
}

public class Main {
  public static void main(String args[]) {

    LinkedList b = new LinkedList();

    // insertion
    ListNode s = new ListNode(6, "Macaper");
    b.insert(new ListNode(10, "Hyperbolis"));
    b.insert(new ListNode(2, "Margolia"));
    b.insert(s);
    b.insert(new ListNode(8, "Hyperbolis"));
    b.insert(new ListNode(12, "Moree"));
    b.insert(new ListNode(4, "Grounded"));

    // search no 15 non-existant
    System.out.println(" Search no 15 " + b.search(15));

    // search no 2 existant
    System.out.println(" Search no 2 " + b.search(2).value);

    // delete no 6 and walk and print
    b.delete(s);
    System.out.println();
    b.walk();
    System.out.println();

  }
}
