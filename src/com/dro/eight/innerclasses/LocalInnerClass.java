package com.dro.eight.innerclasses;

public class LocalInnerClass {

	void foo() {
		class LocalInner {
			
		}
	}

}

// They are available only inside the code they have been declared. LocalInnerClass cannot access LocalInner

// They cannot be declared static

// You cannot have local classes or interfaces inside an interface

// You can extend a class or implement interfaces while defining a local class

// A local class can access all the variables available in the body of the code in which it is defined.

// Variables accessed by local inner classes are considered effectively final

// You can pass only final variables to a local inner class. If you don't declare a variable that a local inner
// class accesses, the compiler will treat it as effectively final.
