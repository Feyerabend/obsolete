

import java.util.*;

class Convert {
  public static <A> Collection<A> up(LinkedList<A> x) {
    return (Collection<A>) x;
  }
  public static <A> LinkedList<A> down(Collection<A> x)
  throws ConvertException {
    if (x instanceof LinkedList<A>)
      return (LinkedList<A>) x;
    else throw new ConvertException();
  }
}

class ConvertException extends Exception {
  ConvertException() { super(); }
}

public class Main2 {

  public static void main(String[] args) {

    LinkedList<String> s = new LinkedList<String>();
    s.add("zero");
    s.add("one");

    Convert c = new Convert();
    LinkedList<String> list = null;
    try {
      list = (c.down(c.up(s))); // upp och ner
    } catch (ConvertException e) { }

    ListIterator it = list.listIterator();
    while (it.hasNext())
      System.out.println(it.next());
  }
}
