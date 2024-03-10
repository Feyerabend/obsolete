
A member class is a class defined within another class. Each instance of the member class is associated with a corresponding instance of the defining class. Unlike a nested class, methods of a member class can access instance fields in a containing class. 


A nested class must be declared static, and is used to group related classes together. A member class, on the other hand, implies a more intimate relationship, with each instance of the class corresponding to an instance of the enclosing class. 


To see how this works, consider an example like: 


	        public class mem1 {
		        private int i = 37;
	
		        class mem11 {}
	
		        public static void main(String args[]) {
				mem1 ref1 = new mem1();
				mem11 ref11 = ref1.new mem11();
			}	
		}

mem1 is an ordinary top-level class, and mem11 a member class within it. New instances of mem1 are created in the usual way, but new instances of mem11 require new syntax: 


        mem11 ref11 = ref1.new mem11();

That is, an instance of mem11 is being created in the context of a specific enclosing instance of mem1, which is referred to by ref1. 


A further example of how member classes are used is illustrated by this code: 


        public class mem2 {
                int i = 0;

                class mem22 {
                        int i = 0;
                        void f() {
                                this.i = 37;
                                mem2.this.i = 47;
                                System.out.println(this.i);
                                System.out.println(mem2.this.i);
                        }
                }

                public static void main(String args[]) {
                        mem2 m2 = new mem2();
                        mem22 m22 = m2.new mem22();
                        m22.f();
                }
        }

We create the new top-level and member class instances, and then call the f() method in the member class. Both the enclosing and member classes have an "i" field, and to access each of these, we say: 


        this.i = 37;
        mem2.this.i = 47;

"mem2.this" is the reference to the enclosing instance of mem2. 


Member classes are most useful as helper classes to other classes, in situations where the helper class needs to get at the instance variables of the containing class. An example might be some type of a data structure class like a tree, that defines a member class implementing java.util.Enumeration to traverse the structure. 



