package com.dro.eight.innerclasses;

// It can be a class or an interface
public class StaticNestedClass {

	public StaticNestedClass() {
		StaticNested st = new StaticNested();
		st.name = "";
	}
	
	// It can be a class or an interface (not necessary to put static with the interface, as it is implicit)
	private static class StaticNested {
		private String name = "";
	}

}

// The inner class can be accessed in a static way -> StaticNestedClass.StaticNested
// StaticNestedClass.StaticNested sta = new StaticNestedClass.StaticNested();

// The accessibility of the static nested class is defined by the outer class

// Static nested classes can be declared abstract or final

// Static nested classes can extend other class or can be used as base classes

// Static nested classes can have static members

// Static nested classes can access only static members of the outer class

// The outer class can access the members of the nested class through an object of a nested class. Even if they are private.



