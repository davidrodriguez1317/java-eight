package com.dro.eight.innerclasses;

public class InnerClass {
	
	Inner inner;
	
	public InnerClass(Inner inner) {
		// The object can ne initialized with this.new Whatever(). Just new Whatever() is not possible as the inner instance is associated with the outer one
		this.inner = this.new Inner(); 
		
		// Inner.name = ""; is not possible, as we need an instance of the inner class to access members.
	}
	
	class Inner {
		String name = "";
		
	}

}

// The accessibility of the static nested class is defined by the outer class

// Inner classes can extend other class or can be used as base classes

// Inner classes can be declared abstract or final

// Member accesses are valid irrespective of the access specifiers such as private

// The outer class needs an instance of the inner class to access its members

// You cannot declare static members in an inner class

// Inner classes can have inner classes