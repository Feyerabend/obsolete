

abstract class Node { }

class ListNode extends Node {
  Object value;
  Object key;
  ListNode prev, next;
  ListNode(Object _key, Object _value) {
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
}

public class Main {
  public static void main(String args[]) {

    LinkedList b = new LinkedList();

    // insertion
    b.insert(new ListNode(new Integer(6), "Macaper"));
    b.insert(new ListNode(new Integer(10), "Hyperbolis"));
    b.insert(new ListNode(new Integer(2), "Margolia"));
    b.insert(new ListNode(new Integer(8), "Hyperbolis"));
    b.insert(new ListNode(new Integer(12), "Moree"));
    b.insert(new ListNode(new Integer(4), "Grounded"));

    // search object '15' non-existant
    System.out.println(" Search object '15' " + b.search(new Integer(15)));

    // search object '2' existant
    System.out.println(" Search object '2' " + b.search(new Integer(2)));

    // delete object '6' and walk and print
    b.delete(new Integer(6));
    System.out.println();
    b.walk();
    System.out.println();

  }
}
