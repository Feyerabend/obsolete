

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
    throw new NoSuchElementException();
  }
}



public class Main {
  public static void main(String args[]) {
    Integer x = new Integer(5);
    Integer y = new Integer(19);
    String z = new String("annat objekt");

    Stack s = new Stack();
    s.push(x);
    s.push(y);
    s.push(z);

    for (Enumerator i = s.elements(); i.hasMoreElements(); ) {
      System.out.println(i.nextElement());
    }

    Object t = s.pop();
    System.out.println("t = " + t);
    for (Enumerator i = s.elements(); i.hasMoreElements(); ) {
      System.out.println(i.nextElement());
    }

    t = s.pop();
    System.out.println("t = " + t);
    for (Enumerator i = s.elements(); i.hasMoreElements(); ) {
      System.out.println(i.nextElement());
    }

    t = s.pop();
    System.out.println("t = " + t);
    for (Enumerator i = s.elements(); i.hasMoreElements(); ) {
      System.out.println(i.nextElement());
    }

    t = s.pop(); // stack underflow

    System.out.println("t = " + t); // no such element in iterator
    for (Enumerator i = s.elements(); ; ) {
      System.out.println(i.nextElement());
    }
  
  }
}
