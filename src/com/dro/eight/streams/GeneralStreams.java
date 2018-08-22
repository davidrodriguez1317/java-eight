package com.dro.eight.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GeneralStreams {

	private static String rainingBlood = "Raining blood From a lacerated sky Bleeding its horror Creating my structure now I shall reign in blood";
	
	private static List<Product> productList = Arrays.asList(
			new GeneralStreams().new Product(23, "potatoes", 13.5),
			new GeneralStreams().new Product(14, "orange", 18), 
			new GeneralStreams().new Product(13, "lemon", 12.4),
			new GeneralStreams().new Product(10, "cheese", 20.6), 
			new GeneralStreams().new Product(13, "rice", 2), 
			new GeneralStreams().new Product(14, "salt", 2), 
			new GeneralStreams().new Product(13, "sugar", 3.5));
	
	public static void waysOfInitializeStreams() throws IOException {
		
		
		List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6);
		int[] intArray = {1, 2, 3, 4, 5, 6};

		
		// Empty stream
		Stream<Integer> emptyStream = Stream.empty();
		
		// From a collection
		Stream<Integer> collectionStream = intList.stream();
		
		// From an array
		IntStream arrayStream = Arrays.stream(intArray);		// There are specialised streams for some primitives:  int, long, double and boolean
		
		// Generation of infinite streams
		Stream<Double> streamGenerated = Stream.generate(Math::random) 		// It generates an infinite stream
				.limit(10); 												// It limits the stream size
		
		Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2)		// It iterates an infinite sequence from a seed (40), and
																			// it inserts into the stream the values generated (n -> n + 2)
				.limit(20);
		
		// Generation from specific classes		
		DoubleStream doubleStream = new Random().doubles(3);		// doubles(int) or ints(int) generates a stream of random numbers
		
		IntStream streamOfChars = "abc".chars();
		
		Path path = Paths.get("C:\\file.txt");						
		Stream<String> streamOfStrings = Files.lines(path);			// it creates a stream which components are each line in the file
		
		// Generation of a stream that uses multi-threading
		Stream<Integer> intParallel = intList.parallelStream();
		
	}
	
	public static void mapStringsToItsLenght(String incoming) {

		System.out.println("	mapStringsToIstLenght ---" );

		String[] incomingArray = incoming.split(" ");
		
		Arrays.stream(incomingArray)
			.map(String::length)             // The string stream is converted into other type of stream (Integer in this case)
			.forEach(s -> System.out.print(s + " "));
		
		
		System.out.println();

	}
	
	public static void mapStringsToItsLenghtWithString(String incoming) {
		
		System.out.println("	mapStringsToItsLenghtWithString ---" );

		String [] incomingArray = incoming.split(" ");
		
		Map<String, Integer> stringMapped = Arrays.stream(incomingArray)
				.distinct()
				.collect(								// Collect is a reduction to a different type of object - from a stream to an object
						Collectors.toMap(Function.identity(), String::length)     	// This operation creates a Map<K,V>. In this case the Key
				);																	// is the string itself and the Value is its lenght
		
		System.out.println(stringMapped);
		System.out.println();

	}
	
	/**
	 * With anyMatch, allMatch and noneMatch we find out if the stream fulfils a condition.
	 * They return a boolean with the result
	 * @param incoming
	 * @param matchingCharacter
	 */
	public static void matchingCharactersInStrings(String incoming, String matchingCharacter) {
		
		System.out.println("	matchingCharactersInStrings ---" );	

		String [] incomingArray = incoming.split(" ");

		boolean anyMatch = Arrays.stream(incomingArray)
				.anyMatch(s -> s.contains(matchingCharacter));		// We use a Predicate<T> to check if the elements in the array
																	// match the condition and return a boolean				
		boolean allMatch = Arrays.stream(incomingArray)
				.allMatch(s -> s.contains(matchingCharacter));
	
		boolean noneMatch = Arrays.stream(incomingArray)
				.noneMatch(s -> s.contains(matchingCharacter));
		
		System.out.println("anyMatch = " + anyMatch + "; allMatch = " + allMatch + "; noneMatch = " + noneMatch);
		System.out.println();
				
	}
	
	/**
	 * With findFirst and firstAny we get the first element that meets the condition in the filter. The element is encapsulated into
	 * an Optional, in case there is no result, an Optional.empty() is inside the Optional wrapper
	 * @param incoming
	 * @param length
	 */
	public static void findingStringsThatHaveLength(String incoming, int length) {
		
		System.out.println("	findingStringsThatHaveLength ---" );	

		String [] incomingArray = incoming.split(" ");
		
		Optional<String> findAnyOptional = Arrays.stream(incomingArray)		// Optional stores an object or a null to avoid NullPointerExceptions
				.parallel()													// This converts a stream into a parallel one
				.filter(s -> s.length() == length)
				.findAny();
		
		findAnyOptional.ifPresent(System.out::println);						// If there is no result, it won't print anything
		
		Optional<String> findFirstOptional = Arrays.stream(incomingArray)	
				.filter(s -> s.length() == length)
				.findFirst();
		
		findFirstOptional.ifPresent(System.out::println);						
	
		System.out.println();
		
	}
	
	
	
	// DELETE ALL
	
	
	class Product {
		
		int id;		
		String name;
		double price;
		
		public Product(int id, String name, double price) {
			super();
			this.id = id;
			this.name = name;
			this.price = price;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}				
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		
		@Override
		public String toString() {
			return "Product [name=" + name + "]";
		}		
		
		
	}
	
	

	public static void createListFromObjects() {
		
		System.out.println("	createListFromObjects ---" );	
		
		// From object to object
		List<String> names = productList.stream()
				.map(Product::getName)
				.sorted()
				.collect(Collectors.toList());
		
		System.out.println(names);
		
		// From object to integer
		List<Integer> ids = productList.stream()
				.mapToInt(Product::getId)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		
		System.out.println(ids);
		System.out.println();

	}
	
	public static void createListsClassifyingById() {
		
		System.out.println("	createListsClassifyingById ---" );	

		Map<Integer,List<Product>> mappedById = productList.stream()
				.collect(Collectors.groupingBy(Product::getId));
		
		System.out.println("Mapped list - " + mappedById);
		
		Map<Integer,Set<Product>> mappedByIdToSet = productList.stream()
				.collect(Collectors.groupingBy(Product::getId, Collectors.toSet()));
		
		System.out.println("Mapped set - " + mappedByIdToSet);
		
		
		Map<Integer, Long> countingProductsWithSameId = productList.stream()
				.collect(Collectors.groupingBy(Product::getId, Collectors.counting()));
	
		System.out.println("Producs with same id - " + countingProductsWithSameId);

		System.out.println();

	}
	
	public static void partitioningByPriceCriteria(double priceCriteria) {
		
		
		System.out.println("	partitioningByPriceCriteria ---" );	

		Predicate<Product> pricePred = x -> x.getPrice() > priceCriteria;
		
		Map<Boolean, Set<Product>> mapByPrice = productList.stream()
				.collect(Collectors.partitioningBy(pricePred, Collectors.toSet()));
				
		System.out.println("Partitioned set - " + mapByPrice);

		
		Map<Boolean, DoubleSummaryStatistics> statisticsByPrice = productList.stream()
				.collect(Collectors.partitioningBy(pricePred, Collectors.summarizingDouble(Product::getPrice)));
		
		System.out.println("Statistics map - " + statisticsByPrice);

		
		Map<Boolean, String> mappedToStringByPrice = productList.stream()
				.collect(Collectors.partitioningBy(pricePred, 
						Collectors.mapping(Product::getName, Collectors.joining(", ", "PRODUCTS: [", "]"))));
		
		System.out.println("Mapping by price and name - " + mappedToStringByPrice);

		System.out.println();

	}
	
	public static void main(String[] args) {
	

		
		// Streams using strings
		mapStringsToItsLenght(rainingBlood);
		mapStringsToItsLenghtWithString(rainingBlood);
		matchingCharactersInStrings(rainingBlood, "h");
		findingStringsThatHaveLength(rainingBlood, 5);
		
		// Grouping and partitioning
		createListFromObjects();
		createListsClassifyingById();
		partitioningByPriceCriteria(4.0);	
		
		// Failing stream --- nothing printed as streams are lazy and there is no terminal operation
		"abracadabra".chars().distinct().peek(ch -> System.out.printf("%c ", ch));
	}
}
