
/*
interface Enumeration {
  boolean hasMoreElements();
  Object nextElement();
}
*/

import java.util.Enumeration;
import java.util.NoSuchElementException;

class Node {
  Object h;
  Node t;
  Node(Object _h, Node _t) {
    h = _h;
    t = _t;
  }
}

class Stack {
  Node top = null;

  boolean isEmpty() {
    return top == null;
  }
  void push(Object x) {
    top = new Node(x, top);
  }
  Object peek() {
    if (!isEmpty()) {
      return top.h;
    }
    throw new NoSuchElementException("Stack empty");
  }
  Object pop() {
    if (!isEmpty()) {
      Object x = top.h;
      top = top.t;
      return x;
    }
    throw new NoSuchElementException("Stack underflow");
  }
  Enumerator elements() {
    return new Enumerator(top);
  }
}

class Enumerator implements Enumeration {
  Node i;
  Enumerator(Node _i) {
    i = _i;
  }

  public boolean hasMoreElements() {
    return i != null;
  }
  public Object nextElement() {
    if (i != null) {
      Node x = i;
      i = i.t;
      return x.h;
    }
    throw new NoSuchElementException("No more elements");
  }
}

class IntStackWrapper {
  Stack b;
  IntStackWrapper() {
    b = new Stack();
  }
  public void push(int v) {
    b.push(new Integer(v));
  }
  public int peek() {
    return ((Integer) b.peek()).intValue();
  }
  public int pop() {
    return ((Integer) b.pop()).intValue();
  }
  Enumerator elements() {
    return b.elements();
  }
}

public class Main {
  public static void main(String args[]) {
    IntStackWrapper w = new IntStackWrapper();

    //System.out.println("peek ->" + w.peek()); // get runtime error: stack empty

    w.push(23);
    w.push(89);
    w.push(10);

    // peek everyone
    for (Enumerator i = w.elements(); i.hasMoreElements(); ) {
      System.out.println(i.nextElement());
    }
    // peek a single element
    System.out.println("peek ->" + w.peek());

    int t = w.pop();
    System.out.println("t = " + t);
    for (Enumerator i = w.elements(); i.hasMoreElements(); ) {
      System.out.println(i.nextElement());
    }
    System.out.println("peek ->" + w.peek());

    t = w.pop();
    System.out.println("t = " + t);
    for (Enumerator i = w.elements(); i.hasMoreElements(); ) {
      System.out.println(i.nextElement());
    }
    System.out.println("peek ->" + w.peek());

    t = w.pop();
    System.out.println("t = " + t);
    for (Enumerator i = w.elements(); i.hasMoreElements(); ) {
      System.out.println(i.nextElement());
    }
    System.out.println("peek ->" + w.peek());

    t = w.pop(); // stack underflow

    System.out.println("t = " + t); // no such element in iterator
    for (Enumerator i = w.elements(); ; ) {
      System.out.println(i.nextElement());
    }

  }
}

