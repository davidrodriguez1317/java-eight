package com.dro.eight.streams;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class PrimitiveStreams {

	private static int start = 11;
	private static int end = 20;
	
	public static void generateAverageOfNumberMultiplied(int[] numbers, int factor) {
		
		IntStream intStream = IntStream.of(numbers);
		
		intStream.map(n -> factor * n)
			.average()
			.ifPresent(System.out::println);
			
	}
	
	public static void generateAverageOfPower(double[] numbers, double power) {
		
		DoubleStream doubleStream = DoubleStream.of(numbers);
		
		doubleStream.map(n -> Math.pow(n, power))
			.average()
			.ifPresent(System.out::println);
			
	}
	
	
public static void sumUsingReduce(int from, int to) {
		
		int result = IntStream.rangeClosed(from, to)
				.reduce(0, (a, b) -> a + b);					// reduce() returns an object of the type of the stream. In this case it has
																// a seed (0) and a BinaryOperator<T, T, T> that sums sequentially the
																// elements from the stream
	
		System.out.println("	sumUsingReduce --- ");
		System.out.println("result --- " + result);
		System.out.println();

	}
	
	public static void sumUsingIntStream(int from, int to) {
		
		int result = IntStream.rangeClosed(from, to)
			.sum();
		
		System.out.println("	sumUsingIntStream --- ");
		System.out.println("result --- " + result);
		System.out.println();
	}
	
	public static void filterByMultiple(int from, int to, int multiple) {
		
		System.out.println("	filterByMultiple ---" );

		IntStream.rangeClosed(from, to)
				.filter(x -> x % multiple == 0)    	// The filter works with a predicate. It receives a parameter and if meets the condition
													// it includes it in the new stream
				.forEach(System.out::println);
		
		System.out.println();

	}
	
	public static void calculateStatistics(int[] intArray) {
		
		System.out.println("	calculateStatistics ---" );


		DoubleSummaryStatistics st = Arrays.stream(intArray)
				.collect(DoubleSummaryStatistics::new,
						DoubleSummaryStatistics::accept,
						DoubleSummaryStatistics::combine);
		
		System.out.println("Sum --- " + st.getSum());
		System.out.println("Average --- " + st.getAverage());
		System.out.println("Count --- " + st.getCount());
		System.out.println("Min --- " + st.getMin());
		System.out.println("Max --- " + st.getMax());
		
		System.out.println();


		
		// Streams using numbers
		sumUsingReduce(start, end);
		sumUsingIntStream(start, end);
		filterByMultiple(start, end, 3);
		calculateStatistics(new int[]{1, 2, 3, 4, 5, 6});
		
		
	}
	
	public static void main(String[] args) {

		int[] intNumbers = {1, 2, 3, 4, 5};
		double[] doubleNumbers = {1, 2, 3, 4, 5};
		
		generateAverageOfNumberMultiplied(intNumbers, 1);
		
		System.out.println(" xxxxx ");
		
		generateAverageOfPower(doubleNumbers, 2);
		
		System.out.println(" xxxxx ");

		
	}

}
