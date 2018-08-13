package com.dro.eight.funct;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaces {

/*	@FunctionalInterface
	public interface Predicate<T> {
		boolean test(T t);
	}*/	
	private static void testPredicate()
	{
		Predicate<String> nullCheck = arg -> arg != null;
		Predicate<String> emptyCheck = arg -> arg.length() > 0;
		
		// Predicate has three default methods: and, or, negate
		Predicate<String> nullAndEmptyCheck = nullCheck.and(emptyCheck);
		Predicate<String> nullOrEmptyCheck = nullCheck.or(emptyCheck);
		Predicate<String> negateEmptyCheck = nullCheck.negate();
	
		String helloStr = "hello";
		
		String nullStr = null;
		
		System.out.println("nullAndEmptyCheck " + helloStr + " --- " + nullAndEmptyCheck.test(helloStr));
		System.out.println("nullAndEmptyCheck " + nullStr + " --- " + nullAndEmptyCheck.test(nullStr));
		
		System.out.println("nullOrEmptyCheck " + helloStr + " --- " + nullOrEmptyCheck.test(helloStr));
		
		System.out.println("negateEmptyCheck " + helloStr + " --- " + negateEmptyCheck.test(helloStr));
		
		Predicate<String> predContains = "I am going to write OCP8 exam"::contains;
		System.out.println(predContains.test("OCPJP") ? "contains" : "doesn't contain");
	}
	
	
/*	@FunctionalInterface
	public interface Consumer<T> {
		void accept(T t);
	}*/
	private static void testConsumer()
	{
		Consumer<String> printUpperCase = str -> System.out.println(str.toUpperCase());
		Consumer<String> takeOutFirstAndLast = str -> System.out.println(str.substring(1, str.length() - 1));
		
		printUpperCase.accept("hello");
		
		// Consumer has one default method: andThen. The argument from accept will be used in both, but the return
		// type is void, so the result of one is not sent to the other
		printUpperCase.andThen(takeOutFirstAndLast).accept("hello");
		
		
		ObjIntConsumer<String> charAt = (str, i) -> str.charAt(i); 
		// System.out.println(charAt.accept("java", 2)); //It will not compile, as Consumer does not return anything
		
	}
	
	
/*	@FunctionalInterface
	public interface Function<T, R> {
		R apply(T t);
	}*/
	private static void testFunction()
	{
		Function<String, Integer> strLength = str -> str.length();
		System.out.println("text lenght --- " + strLength.apply("supercalifragilisticexpialidocious"));
		
		Function<String, Integer> parseInt = Integer::parseInt;
		Function<Integer, Integer> absInt = Math::abs;
		
		// Function has default methods: andThen, compose (that makes first the function inside the parameter)
		Function<String, Integer> parseAndAbsInt = parseInt.andThen(absInt);	
		Function<String, Integer> absIntComposeParse = absInt.compose(parseInt);
		
		System.out.print("andThen --- ");
		Arrays.stream("4, -9, 1".split(", "))
			.map(parseAndAbsInt)
			.forEach(System.out::print);

		System.out.println();
		
		System.out.print("compose --- ");
		Arrays.stream("4, -9, 1".split(", "))
		.map(absIntComposeParse)
		.forEach(System.out::print);
		
		System.out.println();

	}
	
/*	@FunctionalInterface
	public interface Supplier<T> {
		T get();
	}*/
	private static void testSupplier()
	{
		Supplier<String> currentDateTime = () -> LocalDateTime.now().toString();
		System.out.println(currentDateTime.get());
		
		// Supplier doesn't have default methods
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("testPredicate -------------------------------------------");
		testPredicate();
		System.out.println();
		
		System.out.println("testConsumer -------------------------------------------");
		testConsumer();
		System.out.println();

		System.out.println("testFunction -------------------------------------------");
		testFunction();
		System.out.println();
		
		System.out.println("testSupplier -------------------------------------------");
		testSupplier();
		System.out.println();
		

			
		
	}
	
}
