# Abstract Structures

This chapter describes how abstractions from data structure to
some program structures may look like. From the example lists
it is shown how the implementation further leads to more general
patterns (design patterns) like for example. visitor (visitor). The
intention is to narrow down the possibilities in object structures
to some *normative* program structures. It is also in the mastered
limitation that the reuse can begin.

1. ***Lists***

Lists of various kinds are a commonly occurring structure such as e.g.
programmers, system engineers or programming language designers work
with. The advantage of lists is that they are an application of dynamic
structures of data that are easy to handle, even in programming languages ​​where it
there is little support for handling dynamic quantities. But Java is good
equipped for dynamic handling (where automatic garbage collection of objects
are examples). The list also provides insights into reusable structures, then
lists can be used for many different types of data without having to
is changed.

BröA list is, in short, a structure where the data is in a linear fashion
order (but not necessarily sorted). Lists appear in several
varieties such as singly linked or doubly linked lists. Of every
variety, there are several variants, where a single linked or double linked
list can be straight or circular. The links can of course be in
than more dimensions.

The intuitive starting point for lists is an array of atoms.
The atoms here are a, b, c, d, etc. Lists consist of a collection of atoms or
of other lists. Of a set of atoms a, b, c, d, all three are the following
composition lists:

```
[a []]

[b [d[]]

[b [c [[d [d []]][a [[b []][]]]]]]]
```

However, the atoms a, b, c or d are not lists. To the lists belong
also the empty list [] which has no atom (any element) in it
one. A list can consist of other lists. Although the number of atoms which
included in a list can be infinite, the list (here) must be finite.

To complicate matters somewhat, one can define sequences
(somewhat incomplete and informal) where element is what will
correspond to the atoms of the list.

a. If X is an element, then X is a sequence element.

b. If X is a sequence, then X is a sequence element.

c. If X … Xn are sequence elements then is
<X … Xn> a sequence where … means
more or no occurrences of sequence elements.

Then the abstract data structure sequence is mapped to
the representation lists:

*f*(<X1
X2 X3 …
Xn>) =
[*f*(X1)
[*f*(X2)
[*f*(X3) [ …
[*f*(X4) []]
… ]]]]

In the following, implementations are limited for the sake of simplicity
to simple linked straight list:

[a<sub>1</sub> [
a<sub>2</sub>
[a<sub>3</sub> [ …
[a<sub>n</sub> []] … ]]]]

Traditionally, when lists are implemented, they have often been tampered with
using pointers. Java lacks pointers such as C++ has, but offers i
instead, an object-oriented structure with many advantages in front
pointer structures. For example, directly addressed memory allocation can and
complex pointer arithmetic is avoided, and thus also possible and common
wrong. In the following, Java's reference structures are referred to when "pointers"
mentioned (but with which there are certain abstract similarities).

1. *Lists in practice*

Lists can be advantageously based on the node concept. A node is intuitive
a hub of information. The node contains chained pointers to others
nodes, but can also contain the data it carries with it. A list begins
at the starting point the root, and ending where the empty list is
implemented as end pointer.

Bridge Constructed nodes as objects can reserved fields in the objects
be links to other nodes. The objects can also carry others with them
object (satellite) placed in the list. A node can e.g.
constructed:

`class Node ``{`

`  Object o;`

`  Node n;`

`  Node(Object _o, Node _n) ``{`

` o = _o;`

`n = _n;`

`  ``}`

`}`

The nodes are created as new objects with new. Each node has an object and
a link to the next node. Last node that does not point further is null:

`Node root =`

`  new Node(new Float(23.89),`

`    new Node(new Boolean(true),`

`      new Node(new String("Mushroom"),`

`        new Node(new Integer(5),`

`          new Node(new Integer(19),`

`            new Node(new Long(460708224998), null))))));`

Then object orientation allows manipulations with data to be set
together with their data into objects, the nodes can be added with some
algorithms that allow certain manipulations, as shown in list
3.1.

**Quote - list 3.1**

`class Node ``{`

`  Object h;`

`  Node t;`

`  Node(Object _h, Node _t) ``{`

`    h = _h;`

`    t = _t;`

`  ``}`

`  boolean isEmpty() ``{`

`    return (h == null) && (t == null);`

`  ``}`

`  int length() ``{`

`    if (isEmpty()) ``{`

`      return 0;`

`    ``}`` else if (t == null) ``{`

`      return 1;`

`    ``}`` else ``{`

`      return 1 + t.length();`

`    ``}`

`  ``}`

`  Object index(int i) ``{`

`    if (i < 0) ``{`

`      return null;`

`    ``}`` else if (i == 0) ``{`

`      return h;`

`    ``}`` else if (t != null) ``{`

`      return t.index(i - 1);`

`    ``}`` else ``{`

`      return null;`

`    ``}`

`  ``}`

`  boolean includes(Object o) ``{`

`    if (h.equals(o)) ``{`

`      return true;`

`    ``}`

`    if (t == null) ``{`

`      return false;`

`    ``}`` else ``{`

`      return t.includes(o);`

`    ``}`

`  ``}`

`}`

Three methods in Listing 3.1 are recursive. Because the nodes are clustered
with fields that point to other nodes, the methods can use them
to perform their manipulations. Based on the own node: the length of
the chain, return the object the node is associated with or look for
if a particular item is in the chain.

1.  ***Iteratorer***

A problem with the list in 3.1 is that all operations become fast
depending on letting the list manage the routines itself. A relative
problem is that data and administration of data are not separated. Also
if the object orientation allows it, these are a bit too close
merged. This means here that the administration should be moved
out.

**Quote - föList 3.2**

`class Node ``{`

`  Object h;`

`  Node t;`

`  Node(Object _h, Node _t) ``{`

`    h = _h;`

`    t = _t;`

`  ``}`

`}`

`class List ``{`

`  Node root = null;`

`  boolean isEmpty() ``{`

`    return root == null;`

`  ``}`

`  void add(Object v) ``{`

`    root = new Node(v, root);`

`  ``}`

`  Iterator elements() ``{`

`    return new Iterator(root);`

`  ``}`

`}`

`class Iterator ``{`

`  Node i;`

`  Iterator(Node _i) ``{`

`    i = _i;`

`  ``}`

`  boolean hasMoreElements() ``{`

`    return i != null;`

`  ``}`

`  Object nextElement() ``{`

`    if (i != null) ``{`

`      Node x = i;`

`      i = i.t;`

`      return x.h;`

`    ``}`

`    throw new java.util.NoSuchElementException("Node");`

`  ``}`

`}`

In listing 3.2, an iterator has instead been placed between the list and
the routines that are supposed to use the list. An advantage and disadvantage is that
it is best suited for routines (methods) that do not depend on themselves
the structure of the list, but cope with (only) enumerations.

BröAnother example of administration of data that can do without
the structure is an implementation of a stack. Listing 3.3 shows a class
which uses both the Node and Iterator classes from Listing 3.2.

**Quote - list 3.3**

`class Stack ``{`

`  Node top = null;`

`  boolean isEmpty() ``{`

`    return top == null;`

`  ``}`

`  void push(Object x) ``{`

`    top = new Node(x, top);`

`  ``}`

`  Object pop() ``{`

`    if (!isEmpty()) ``{`

`      Object x = top.h;`

`      top = top.t;`

`      return x;`

`    ``}`

`    throw new java.util.NoSuchElementException("Stack underflow");`

`  ``}`

`  Iterator elements() ``{`

`    return new Iterator(top);`

`  ``}`

`}`

An iterator is a general structure (design pattern) that can be used
for many different purposes. It is common to consider the data (or
other objects) are administered by an iterator which in turn is managed by a
client. A variant of iterator can be constructed through interfaces where it
abstracted pointer can be used:

erect `init()` initialization, pointer at root

 `currentItem()` currently selected item

 `atEnd()` true if the pointer is at the end

 `nextItem()` jump to specifying the next element

**Quote - föList 3.4**

`class List ``{`

`...`

`  ListPointer elements() ``{`

`    return new ListPointer(root);`

`  ``}`

`}`

`class ListPointer ``{`

`  Node p;`

`  ListPointer(Node _p) ``{`

`    p = _p;`

`  ``}`

`  boolean atEnd() ``{`

`    return p == null;`

`  ``}`

`  void nextItem() ``{`

`    if (p != null) ``{`

`      p = p.t;`

`    ``}`

`  ``}`

`  Object currentItem() ``{`

`    if (p == null) ``{`

`      return null;`

`    ``}`` else ``{`

`      return p.h;`

`    ``}`

`  ``}`

`}`

Instead of init(), the constructor here has ListPointer(Node)
used. A client that will use the iterator does not need more
than the interface defined to enumerate the contents of
the list.

`List list = new List();`

`list.add(new String("Athena"));`

`list.add(new Integer(7));`

`list.add(new Double(92.2));`

`…`

`ListPointer j = list.elements();`

``while (!j.atEnd()) ``{`

`  System.out.println(j.currentItem());`

`  j.nextItem();`

`}`

If practiced, reuse can Java's already established interfaces
Enumeration is applied in the context which basically looks like:

`package java.util;`

`public interface Enumeration ``{`

`  boolean hasMoreElements();`

`  Object nextElement();`

`}`

At each enumeration of list or stack or other data structure,
the entire instance can be run through the administration with e.g.:

`Stack v;`

`…`

`for (java.util.Enumeration e = v.elements(); e.hasMoreElements(); ) ``{`

`  System.out.println(e.nextElement());`

`}`

The only thing that needs to be replaced is elements() and here the name is also changed
in the iterator. In addition, the methods that implement the interface must
be public.

`import java.util.Enumeration;`

`import java.util.NoSuchElementException;`

`class Node ``{`

`	``…`

`}`

`class Stack ``{`

`…`

`  Enumerator elements() ``{`

`    return new Enumerator(top);`

`  ``}`

`}`

`class Enumerator implements Enumeration ``{`

`  Node i;`

`  Enumerator(Node _i) ``{`

`    i = _i;`

`  ``}`

`  public boolean hasMoreElements() ``{`

`    return i != null;`

`  ``}`

`  public Object nextElement() ``{`

`    if (i != null) ``{`

`      Node x = i;`

`      i = i.t;`

`      return x.h;`

`    ``}`

`    throw new NoSuchElementException();`

`  ``}`

`}`

1. ***Membership Classes***

In addition to inner classes such as local classes, anonymous classes or
nested classes, there are the inner member classes. The local and anonymous
the classes are often naturally limited by their surroundings
methods. One point for the member classes as opposed to the nested ones
the classes is that methods in the member classes can reach the surrounding
the instantiated field of the class (the defining class). Even the private ones
the fields and methods of the surrounding class are accessed. Each instance of
namely, the member class is associated with the instance of the surrounding one
the class. Another difference is that the nested classes, which are
top-level classes, must be declared static. There aren't any either
interfaces that act like the member classes. If interfaces are defined
within a class in the same way as member or nested classes,
assuming they are nested interfaces (even without static).

BröImportantly, the syntax for references, calls and the creation of
objects through super, this and new look different than at top level
the cases and the other inner classes. To take an example of an interior
member class is instantiated in the surrounding class through this.new
Enumerator():

`class Node ``{`

`…`

`  java.util.Enumeration elements() ``{`

`    return this.new Enumerator();`

`  ``}`

`  class Enumerator implements java.util.Enumeration ``{`

`    int i = 0;`

`    public boolean hasMoreElements() ``{`

`      return i < Node.this.length();`

`    ``}`

`    public Object nextElement() ``{`

`      if (i < Node.this.length()) ``{`

`        return Node.this.index(i++);`

`      ``}`

`      throw new java.util.NoSuchElementException("Node");`

`    ``}`

`  ``}`

`}`

Even the references from the inner class Enumerator become of one
new kind. Methods in the surrounding class may be called via
the instance reference e.g. Node.this.length(). The entire class Node and inner
the Enumerator member class can be seen in Listing 3.4. Here have at the moment also
the recursion is used again.

**Quote - föList 3.4**

`import java.util.Enumeration;`

`import java.util.NoSuchElementException;`

`class Node ``{`

`  Object h;`

`  Node t;`

`  Node(Object _h, Node _t) ``{`

`    h = _h;`

`    t = _t;`

`  ``}`

`  boolean isEmpty() ``{`

`    return (h == null) && (t == null);`

`  ``}`

`  int length() ``{`

`    if (isEmpty()) ``{`

`      return 0;`

`    ``}`` else if (t == null) ``{`

`      return 1;`

`    ``}`` else ``{`

`      return 1 + t.length();`

`    ``}`

`  ``}`

`  Object index(int i) ``{`

`    if (i < 0) ``{`

`      return null;`

`    ``}`` else if (i == 0) ``{`

`      return h;`

`    ``}`` else if (t != null) ``{`

`      return t.index(i - 1);`

`    ``}`` else ``{`

`      return null;`

`    ``}`

`  ``}`

`  Enumeration elements() ``{`

`    return this.new Enumerator();`

`  ``}`

`  class Enumerator implements Enumeration ``{`

`    int i = 0;`

`    public boolean hasMoreElements() ``{`

`      return i < Node.this.length();`

`    ``}`

`    public Object nextElement() ``{`

`      synchronized (Node.this) ``{`

`        if (i < Node.this.length()) ``{`

`          return Node.this.index(i++);`

`        ``}`

`      ``}`

`      throw new NoSuchElementException("Node");`

`    ``}`

`  ``}`

`}`

Sometimes a constructor of an inner class can be called outside of it
surrounding class, from a subclass to the surrounding class. Assume
that there is a constructor Enumerator() that is called from a
subclass:

`class Node ``{`

`  ``…`

`  class Enumerator implements java.util.Enumeration ``{`

`    Enumerator() ``{`

`      ``…`

`    ``}`

`    ``…`

`  ``}`

`}`

`class Enumerating extends Node.Enumerator ``{`

`  Enumerating(Node theNode) ``{`

`    theNode.super();`

`  ``}`

`  ``…`

`}`

But this does not mean that Enumerating will get its own
instance, but only Node still has the instance. Fortunately
are these types of declarations will probably remain
rare.

BröWhether inner classes are to be used or not must often become one
balance between programmatic simplification and readability. Inner classes
can simplify programming but can also complicate readability.
Reuse is about simplicity, where trade-offs are not entirely obvious
between readability and simplified programming.

1. ***Inheritance and inheritance***

[Nested classes: example: a calculator with a nested stack]

The inner classes introduce many novelties for Java. The
not only extends syntactic possibilities to place inner classes in
the proximity of surrounding classes but also the range of access
(accessibility) and visibility (visibility) of methods, fields and classes
from and by inner classes.

BröAn important modifier is static and another final. Everything that
declared static cannot be included in the inner classes but is added
only the top level, where a nested class or interface is also a member.
To reach the environment from a local class, environment declarations are required
of parameters or variables be final. Furthermore, the surrounding fields,
methods and inner classes declared private accessible from the inners
the classes and not restrictive to the surrounding class alone.

References and calls are somewhat complicated in their syntax because i
the case member classes can distinguish between a formed scope hierarchy
(containment hierarchy) and an inheritance hierarchy (inheritance hierarchy).
Which one is meant must be clearly declared. Take e.g. a length() method
which is inherited from a superclass Node. Instantiate the subclass and refer
to the method through this.length(). But in the case where a method is used within
scoped in a member class within an inherited superclass Node, it is referenced
to instead through Node.this.length().

1. ***Tr**ä**d***

As shown, it was possible to implement both lists and stacks via
node concept. Of course, both of these can be further refined by
that e.g. the lists become doubly linked and circular and the stacks
contains more complex information distributed over several stacks, etc.

BröFurther examples of structure for data are trees:

`class TreeNode extends Node ``{`

`  Object value;`

`  int key;`

`  TreeNode left, right, parent;`

`  TreeNode(int _key, Object _value) ``{`

`    key = _key;`

`    value = _value;`

`  ``}`

`}`

But trees presuppose somewhat more complicated access concepts than just that
enumeration. Here there is use for a significantly more extended lexicon
(dictionary). An example of what a lexicon is for a very simple tree
could implement:

erect search(T, k) A query given a tree and a key k,
returns the node key points to or null if no value
exists.

 insert(x) A modification operation, given the tree place node x in
the tree.

 delete(x) A modification operation, given the tree delete the node x
out of the tree.

 minimum(T) A query that, given a branch T, returns the node with it
smallest key.

 maximum(T) A query that, given a branch T, returns the node with it
biggest key.

 successor(x) A query that, given a node and tree, returns
next larger node according to its key or null if the node already is
largest.

`abstract class Node ``{`` ``}`

`class TreeNode extends Node ``{`

`  Object value;`

`  int key;`

`  TreeNode left, right, parent;`

`  TreeNode(int _key, Object _value) ``{`

`    key = _key;`

`    value = _value;`

`  ``}`

`}`

`class BinarySearchTree ``{`

`  TreeNode root;`

`  TreeNode nil = new TreeNode(0, "nil"); // special`

`  boolean isEmpty() ``{`

`    return (root == null);`

`  ``}`

`  TreeNode minimum(TreeNode x) ``{`

`    while (x.left != null) ``{`

`      x = x.left;`

`    ``}`

`    return x;`

`  ``}`

`  TreeNode maximum(TreeNode x) ``{`

`    while (x.right != null) ``{`

`      x = x.right;`

`    ``}`

`    return x;`

`  ``}`

`  TreeNode successor(TreeNode x) ``{`

`    if (x.right != null) ``{`

`      return minimum(x.right);`

`    ``}`

`    TreeNode y = x.parent;`

`    while ((y != null) && (x == y.right)) ``{`

`x = y;`

`      y = x.parent;`

`    ``}`

`    return y;`

`  ``}`

`  TreeNode predecessor(TreeNode x) ``{`

`    if (x.left != null) ``{`

`      return maximum(x.left);`

`    ``}`

`    TreeNode y = x.parent;`

`    while ((y != null) && (x == y.left)) ``{`

`x = y;`

` y = x.parent; // alt:y.parent`

`    ``}`

`    return y;`

`  ``}`

`  TreeNode insert(TreeNode z) ``{`

`    TreeNode x = root, y = null;`

`    while (x != null) ``{`

` y = x;`

`      if (z.key < x.key) ``{`

`        x = x.left;`

`      ``}`` else ``{`

`        x = x.right;`

`      ``}`

`    ``}`

` z.parent = y;`

`    if (y == null) ``{`

`      root = z;`

`    ``}`` else ``{`

`      if (z.key < y.key) ``{`

`        y.left = z;`

`      ``}`` else if (z.key > y.key) ``{`

`        y.right = z;`

`      ``}`` else ``{`

`        Object t = z.value;`

`        z.value = y.value;`

`        y.value = t;`

`        return z;`

`      ``}`

`    ``}`

`    return null;`

`  ``}`

`  TreeNode delete(TreeNode z) ``{`

`    TreeNode x = null, y = null;`

`    if ((z.left == null) || (z.right == null)) ``{`

`      y = z;`

`    ``}`` else ``{`

`      y = successor(z);`

`    ``}`

`    if (y.left != null) ``{`

`      x = y.left;`

`    ``}`` else ``{`

`      if (y.right == null) ``{`

`        x = nil;`

`      ``}`` else ``{`

`        x = y.right;`

`      ``}`

`    ``}`

`    if (x != null) ``{`

`      x.parent = y.parent;`

`    ``}`

`    if (y.parent == null) ``{`

`      if (x == nil) ``{`

`        root = null;`

`      ``}`` else ``{`

`        root = x;`

`      ``}`

`    ``}`` else ``{`

`      if (y == y.parent.left) ``{`

`        if (x == nil) ``{`

`          y.parent.left = null;`

`        ``}`` else ``{`

`          y.parent.left = x;`

`        ``}`

`      ``}`` else ``{`

`        if (x == nil) ``{`

`          y.parent.right = null;`

`        ``}`` else ``{`

`          y.parent.right = x;`

`        ``}`

`      ``}`

`    ``}`

`    if (y != z) ``{`

`      z.key = y.key;`

`      z.value = y.value;`

`    ``}`

`    return y;`

`  ``}`

`  void inorderWalk(TreeNode x) ``{`

`    if (x != null) ``{`

`      inorderWalk(x.left);`

`      System.out.println(" For key: " + x.key + ", value: " + x.value);`

`      inorderWalk(x.right);`

`    ``}`

`  ``}`

`  TreeNode search(TreeNode x, int k) ``{`

`    if ((x == null) || (k == x.key)) ``{`

`      return x;`

`    ``}`

`    if (k < x.key) ``{`

`      return search(x.left, k);`

`    ``}`` else ``{`

`      return search(x.right, k);`

`    ``}`

`  ``}`

`}`

`public class Main ``{`

`  public static void main(String args[]) ``{`

`    BinarySearchTree b = new BinarySearchTree();`

`    // the order of insertion random`

`    TreeNode s = new TreeNode(6, "Macaper");`

`    b.insert(new TreeNode(10, "Hyperbolis"));`

`    b.insert(new TreeNode(2, "Margolia"));`

`    b.insert(s);`

`    b.insert(new TreeNode(8, "Hyperbolis"));`

`    b.insert(new TreeNode(12, "Moree"));`

`    b.insert(new TreeNode(4, "Grounded"));`

`    // walk and print`

`    b.inorderWalk(b.root);`

`    System.out.println();`

`    // search no 12`

`    System.out.println(" Search no 12: " + b.search(b.root, 12).value);`

`    System.out.println();`

`    // delete no 6 and walk and print`

`    b.delete(s);`

`    System.out.println();`

`    b.inorderWalk(b.root);`

`    System.out.println();`

`    // replace no 4 and walk and print`

`    b.insert(new TreeNode(4, "Poff!"));`

`    System.out.println();`

`    b.inorderWalk(b.root);`

`  ``}`

`}`

1. ***Bes**ö**kare***

`abstract class Node ``{`

`  abstract Node accept(NodeVisitor ask);`

`}`

`class NextNode extends Node ``{`

`  Object t;`

`  Node n;`

`  NextNode(Object _t, Node _n) ``{`

`    t = _t;`

`n = _n;`

`  ``}`

`  Node accept(NodeVisitor ask) ``{`

`    return ask.forNextNode(t, n);`

`  ``}`

`}`

`class EmptyNode extends Node ``{`

`  Node accept(NodeVisitor ask) ``{`

`    return ask.forEmptyNode();`

`  ``}`

`}`

`interface NodeVisitor ``{`

`  Node forEmptyNode();`

`  Node forNextNode(Object t, Node n);`

`}`

`class Remove implements NodeVisitor ``{`

`  Object o;`

`  Remove(Object _o) ``{`

` o = _o;`

`  ``}`

`  public Node forEmptyNode() ``{`

`    return new EmptyNode();`

`  ``}`

`  public Node forNextNode(Object t, Node n) ``{`

`    if (o.equals(t)) ``{`

`      return n.accept(this);`

`    ``}`` else ``{`

`      return new NextNode(t, n.accept(this));`

`    ``}`

`  ``}`

`}`

`class Add implements NodeVisitor ``{`

`  Object o;`

`  Add(Object _o) ``{`

` o = _o;`

`  ``}`

`  public Node forEmptyNode() ``{`

`    return new NextNode(o, new EmptyNode());`

`  ``}`

`  public Node forNextNode(Object t, Node n) ``{`

`    return new NextNode(o, n.accept(this));`

`  ``}`

`}`

`class Replace implements NodeVisitor ``{`

`  Object r;`

`  Object o;`

`  Replace(Object _r, Object _o) ``{`

`    r = _r;`

` o = _o;`

`  ``}`

`  public Node forEmptyNode() ``{`

`    return new EmptyNode();`

`  ``}`

`  public Node forNextNode(Object t, Node n) ``{`

`    if (o.equals(t)) ``{`

`      return new NextNode(r, n.accept(this));`

`    ``}`` else ``{`

`      return new NextNode(t, n.accept(this));`

`    ``}`

`  ``}`

`}`

`public class Main ``{`

`  public static void main(String args[]) ``{`

`    NextNode n =`

`    new NextNode("Hyperbolis",`

`      new NextNode("Margolia",`

`        new NextNode("Hyperbolis",`

`          new NextNode("Grounded", new EmptyNode())`

`        )`

`      )`

`    );`

`    System.out.println(n.accept(new Replace("Misc", "Hyperbolis")));`

`  ``}`

`}`

1. ***Protocol***

`abstract class Node ``{`

`  abstract Object accept(NodeVisitor ask);`

`}`

`class NextNode extends Node ``{`

`  Object t;`

`  Node n;`

`  NextNode(Object _t, Node _n) ``{`

`    t = _t;`

`n = _n;`

`  ``}`

`  Object accept(NodeVisitor ask) ``{`

`    return ask.forNextNode(this);`

`  ``}`

`}`

`class EmptyNode extends Node ``{`

`  Object accept(NodeVisitor ask) ``{`

`    return ask.forEmptyNode(this);`

`  ``}`

`}`

`interface NodeVisitor ``{`

`  Object forEmptyNode(EmptyNode that);`

`  Object forNextNode(NextNode that);`

`}`

`class Remove implements NodeVisitor ``{`

`  Object o;`

`  Remove(Object _o) ``{`

` o = _o;`

`  ``}`

`  public Object forEmptyNode(EmptyNode that) ``{`

`    return new EmptyNode();`

`  ``}`

`  public Object forNextNode(NextNode that) ``{`

`    if (o.equals(that.t)) ``{`

`      return that.n.accept(this);`

`    ``}`` else ``{`

`      return new NextNode(that.t, (Node) that.n.accept(this));`

`    ``}`

`  ``}`

`}`

`class Insert implements NodeVisitor ``{`

`  Object o;`

`  Insert(Object _o) ``{`

` o = _o;`

`  ``}`

`  public Object forEmptyNode(EmptyNode that) ``{`

`    return new NextNode(o, new EmptyNode());`

`  ``}`

`  public Object forNextNode(NextNode that) ``{`

`    return new NextNode(that.t, (Node) that.n.accept(this));`

`  ``}`

`}`

`class Replace implements NodeVisitor ``{`

`  Object r;`

`  Object o;`

`  Replace(Object _r, Object _o) ``{`

`    r = _r;`

` o = _o;`

`  ``}`

`  public Object forEmptyNode(EmptyNode that) ``{`

`    return new EmptyNode();`

`  ``}`

`  public Object forNextNode(NextNode that) ``{`

`    if (o.equals(that.t)) ``{`

`      return new NextNode(r, (Node) that.n.accept(this));`

`    ``}`` else ``{`

`      return new NextNode(that.t, (Node) that.n.accept(this));`

`    ``}`

`  ``}`

`}`

`class PrintElements implements NodeVisitor ``{`

`  public Object forEmptyNode(EmptyNode that) ``{`

`    System.out.println();`

`    return (Object) null;`

`  ``}`

`  public Object forNextNode(NextNode that) ``{`

`    System.out.println((Object) that.t);`

`    that.n.accept(this);`

`    return (Object) that.t;`

`  ``}`

`}`

`interface TreeDuties ``{`

`  void add(Object o);`

`  void insert(Object o);`

`  void remove(Object o);`

`  void replace(Object o, Object p);`

`}`

`class Gardener implements TreeDuties ``{`

`  Node t = new EmptyNode();`

`  public void add(Object o) ``{`

`    t = new NextNode(o, t);`

`  ``}`

`  public void insert(Object o) ``{`

`    t = (Node) t.accept(new Insert(o));`

`  ``}`

`  public void remove(Object o) ``{`

`    t = (Node) t.accept(new Remove(o));`

`  ``}`

`  public void replace(Object o, Object p) ``{`

`    t = (Node) t.accept(new Replace(o, p));`

`  ``}`

`  public Object printAllElements() ``{`

`    return (Object) t.accept(new PrintElements());`

`  ``}`

`}`

`public class Main ``{`

`  public static void main(String args[]) ``{`

`    Gardener g = new Gardener();`

`    g.add("1");`

`    g.add("2");`

`    g.add("3");`

`    g.add("4");`

`    g.add("5");`

`    g.add("6");`

`    g.insert("7");`

`    g.insert("8");`

`    g.add("9");`

`    g.printAllElements();`

`    g.replace("0", "1");`

`    g.printAllElements();`

`    g.replace("2", "5");`

`  ``}`

`}`

*Fabriker* (factory)

[separate abstract factory from method factory]

`abstract class Node ``{`

`  abstract Node accept(NodeVisitor ask);`

`}`

`class NextNode extends Node ``{`

`  Object t;`

`  Node n;`

`  NextNode(Object _t, Node _n) ``{`

`    t = _t;`

`n = _n;`

`  ``}`

`  Node accept(NodeVisitor ask) ``{`

`    return ask.forNextNode(t, n);`

`  ``}`

`}`

`class EmptyNode extends Node ``{`

`  Node accept(NodeVisitor ask) ``{`

`    return ask.forEmptyNode();`

`  ``}`

`}`

`interface NodeVisitor ``{`

`  Node forEmptyNode();`

`  Node forNextNode(Object t, Node n);`

`}`

`interface EqualObjects extends NodeVisitor ``{`

`  boolean equal(Object x, Object y);`

`}`

`abstract class Comparable implements EqualObjects ``{`

`  public boolean equal(Object o, Object t) ``{`

`    EqualityFactory f =`

`      new StrictStringEqFactory(); // Strict or not`

`    return f.newEquality().isEqual(o, t);`

`  ``}`

`}`

`class Remove extends Comparable ``{`

`  Object o;`

`  Remove(Object _o) ``{`

` o = _o;`

`  ``}`

`  public Node forEmptyNode() ``{`

`    return new EmptyNode();`

`  ``}`

`  public Node forNextNode(Object t, Node n) ``{`

`    if (equal(o, t)) ``{`

`      return n.accept(this);`

`    ``}`` else ``{`

`      return new NextNode(t, n.accept(this));`

`    ``}`

`  ``}`

`}`

`class Add extends Comparable ``{`

`  Object o;`

`  Add(Object _o) ``{`

` o = _o;`

`  ``}`

`  public Node forEmptyNode() ``{`

`    return new NextNode(o, new EmptyNode());`

`  ``}`

`  public Node forNextNode(Object t, Node n) ``{`

`    return new NextNode(t, n.accept(this));`

`  ``}`

`}`

`class Replace extends Comparable ``{`

`  Object r;`

`  Object o;`

`  Replace(Object _r, Object _o) ``{`

`    r = _r;`

` o = _o;`

`  ``}`

`  public Node forEmptyNode() ``{`

`    return new EmptyNode();`

`  ``}`

`  public Node forNextNode(Object t, Node n) ``{`

`    if (equal(o, t)) ``{`` // (o.equals(t))`

`      return new NextNode(r, n.accept(this));`

`    ``}`` else ``{`

`      return new NextNode(t, n.accept(this));`

`    ``}`

`  ``}`

`}`

`interface EqualityFactory ``{`

`  public abstract Equality newEquality();`

`}`

`class StringEqFactory implements EqualityFactory ``{`

`  public Equality newEquality() ``{`

`    return new StringEq();`

`  ``}`

`}`

`class StrictStringEqFactory implements EqualityFactory ``{`

`  public Equality newEquality() ``{`

`    return new StrictStringEq();`

`  ``}`

`}`

`interface Equality ``{`

`  public abstract boolean isEqual(Object x, Object y);`

`}`

`class Eq implements Equality ``{`

`  public boolean isEqual(Object x, Object y) ``{`

`    if ((x instanceof Integer) && (y instanceof Integer)) ``{`

`      return ((Integer) x).equals((Integer) y);`

`    ``}`

`    return false;`

`  ``}`

`}`

`class StringEq extends Eq ``{`

`  public boolean isEqual(Object x, Object y) ``{`

`    if ((x instanceof String) && (y instanceof String)) ``{`

`      return ((String) x).equalsIgnoreCase((String) y);`

`    ``}`

`    return super.isEqual(x, y);`

`  ``}`

`}`

`class StrictStringEq extends Eq ``{`

`  public boolean isEqual(Object x, Object y) ``{`

`    if ((x instanceof String) && (y instanceof String)) ``{`

`      return ((String) x).equals((String) y);`

`    ``}`

`    return super.isEqual(x, y);`

`  ``}`

`}`

`public class Main ``{`

`  public static void main(String args[]) ``{`

`    NextNode n =`

`    new NextNode("Hyperbolis",`

`     new NextNode(new Integer(24),`

`      new NextNode("Margolia",`

`        new NextNode("HYPERBOLIS",`

`          new NextNode("Grounded", new EmptyNode())))));`

`    System.out.println(n.accept(new Replace("Misc", new Integer(24))));`

`    System.out.println(n.accept(new Add(new Integer(777))));`

`  ``}`

`}`


