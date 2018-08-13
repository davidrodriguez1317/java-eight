package com.dro.eight.innerclasses;

public class AnonymousInnerClass {

	Object foo() {
		return new Object() { // The class that goes here must be extensible
			public String toString() {
				return "";
			}
		};
	}

}

// An anonymous inner class cannot have any explicit constructors

// You cannot explicitly extend a class or explicitly implement interfaces when defining an anonymous class.
