# Introduction

Programming isn't just about learning class libraries, programming languages and coding. That means learning the _design_ of programs. Writing reusable programs often involves the pursuit of good design. It is to communicate the ideas in the program to other programmers and to oneself. An effect is also that the more reusable code is used, the more tested and less faulty it tends to be. With object orientation and other things, extensive reuse has become practically feasible. Java's standard classes (API) make up much of what Java can implement. There are also specially written classes for different purposes. Writing programs that use already written classes is basically programming in Java. This is a difference to e.g. languages like C++ where programming is often more traditionally oriented towards the use of the nature of the language.

This book is not aimed at those who have programming training, but still at those who have some experience of programming in Java. It is aimed at those who want to write smaller, yet reusable programs in Java. It is thus assumed that the reader has already mastered some Java and can program simpler programs.

The book aims to give the reader insight into various _structures_ for writing programs. It is hoped that the reader can take the basic step past acquired skills in the semantic and syntactic properties of Java.

## Elementary Control Structures

Traditional programming has often been based on _imperative_ languages where the sentences talk about what should happen in the form of a kind of imperative. The type of instructions has been to take two given numbers and multiply them and deliver the result to a variable. In addition to these imperatives, there have been e.g. functional programming and logic programming. However, the imperative languages have been the most widely used and are the basis for much of Java. Within this type of programming, it is particularly easy to adopt three "primitives" in terms of control structures _sequence_, _iteration_ and _selection_.

Sequences are formed by statements that end with semicolons. If several instructions are put together, they form blocks and are executed one after the other. Iterations can be formed in several ways with for, while or do-while. Some associated control is obtained through labels, break and continue. Selection is done with if, if-else, if-else-if and switch. These form a kind of basic structure for control, but which only happens _within_ methods.

One can notice that although goto is a reserved word in Java, it is not implemented. The control instruction goto has unfortunately often led to unmanageable and snarky programs so that it is avoided today in high-level languages.

Recursion is not only common in mathematics, it is also a very common control structure in addition to common iteration through while and the like. The difference is that for recursion there is no reserved word. Method factorial() in list 1.1 gives e.g. integer factorization. The method calls itself to solve the problem, recursively.

All mentioned control structures are elementary and the reader is expected to master them.

**List 1.1**

```java
class IntegerFact {
    static int factorial(int n) {
        if (n > 1)
            return factorial(n - 1) \* n;
        // otherwise
        return 1;
    }
}
```

## Parallel Programming

One of the great advantages of Java is its possibility for parallel programming through so-called threads. Another parallel programming possibility is several cooperating virtual machines (JVM) via e.g. RMI (Remote Method Invocation). However, both thread management and the collaboration of virtual machines imply a great responsibility for the programmer. The programmer should e.g. make sure that memories are managed conflict-free, that no more programs (objects) try to write simultaneously to the same memory.

In this book, a special chapter will be devoted to parallel programming in the form of threads: multi-threaded programming.

## Object Orientation

Another control occurs for objects and classes in that Java is an _object oriented_ language. Among other things there is instantiation of classes, extension of classes, inheritance between classes, constructors in classes, method overlay, method overriding, etc.

There is no room here for much of the object-orientation that _can_ be implemented in Java, but only certain object structures that are particularly interesting for reuse. Among these are abstract classes, interfaces (interface), extension and subclassing (extends). The more elementary is covered in an introductory chapter and the more advanced (such as inner classes) is covered in connection with other programming. As this chapter only briefly covers object orientation, it is assumed that the reader already knows something about object orientation or is willing to experiment on their own.

## Data Structures

Data structures include lists, trees, sets, tables, hash tables, indexes (arrays), buffers, files, records, strings, etc. Many data structures are already available in Java's standard set of packages (Java API). When the programmer is allowed to construct his own structures, these are called _abstract data types_.

Traditionally, data structures are among the most reusable code modules available. Insights into how data structures work provide valuable insights into how programming works. Therefore, some fundamental aspects of data structures will be covered, such as e.g. lists and trees.

## Algorithms

Computer algorithms are conceptually close to data structures. Both areas are well-established research areas and there is a lot of literature to search further in. In this book, the algorithms are not given special attention, other than they are related to the data structures.

## Program Structures

A relatively new area is program structures (design patterns). During the 1990s, the program structures received a lot of attention and the publications became more and more numerous. Program structures can be seen as imitations by programmers to study the code of other (better or more experienced) programmers and learn patterns that are re-adopted in another area. The program structures thus become generalizations of the imitation strategies. Algorithms therefore also fall under program structures. However, there are program structures that do not take the form of classical algorithms or other well-studied areas. Some patterns such as iterator, visitor and factory will be exemplified in this book.

### Bibliography

* Beckman, Hans & Johan Finnved, "Metodöversikt för mikrodator programmerare", _Modern Elektronik_ 1981: 8-9.

* Berg, Daniel J. & Stephen Fritzinger, _Advanced Techniques for Java Developers_ (New York: John Wiley & Sons Inc, 1998).

* Budd, Timothy, _An Introduction to Object-Oriented Programming_ (1990) 2nd ed. (Reading, MA: Addison-Wesley, 1997).

* Coad, Peter, David North & Mark Mayfield, _Object Models: Strategies, Patterns, & Applications_ (1995?) 2nd ed. (New York: Yourdon Press, 1997).

* Cormen, Thomas C., Charles E. Leiserson & Ronald L. Rivest, _Introduction to Algorithms_ (1990) (Cambridge, Massachusetts: MIT Press, 1996).

* Felleisen, Matthias & Daniel P. Friedman, _A Little Java, A Few Patterns_ (Cambridge, Massachusetts: MIT Press, 1998).

* Flanagan, David, _Java in a Nutshell_ (1996) 2nd ed. (Cambridge: O'Reilly, 1997).

* Gamma, Erich, Richard Helm, Ralph Johnson & John Vlisssides, _Design Patterns: Elements of Reusable Object-Oriented Software_ (Reading, Massachusetts: Addison-Wesley, 1995).

* Lonnert, Set, _Programming in Java_ (1997) 2nd ed., 2 vols. (Bromm: KnowWare Publications, 1997).

* Lunell, Hans, _Computer Science - An Introductory Overview_ (Lund: Student Literature, 1979).

* Manna, Zohar & Richard Waldinger, _The Logical Basis for Computer Programming_, 2 vols. (Reading, Mass.: Addison-Wesley, 1985).

* Pree, Wolfgang, _Design Patterns for Object-Oriented Software Development_ (1994) (Workingham: Addison-Wesley & ACM Press, 1995).

* Rice, Jeffrey C. & Irving Salisbury III, _Advanced Java 1.1 Programming_ (New York: McGraw-Hill, 1997).