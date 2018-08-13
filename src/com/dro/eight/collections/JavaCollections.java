package com.dro.eight.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class JavaCollections {

	public JavaCollections() {
		// TODO Auto-generated constructor stub
	}
	
	
	// ArrayList --- Internally implemented as a resizable array. This is one of the most widely used
	// concrete classes. Fast to search, but slow to insert or delete. Allows duplicates.
	private static void testArrayList()
	{
		Double [] temperatureArray = {31.1, 30.0, 32.5, 34.9, 33.7, 27.8};
		System.out.println("The original array is: " + Arrays.toString(temperatureArray));
		List<Double> temperatureList = Arrays.asList(temperatureArray);
		temperatureList.set(0, 35.2);
		System.out.println("The modified array is: " + Arrays.toString(temperatureArray));
		
		try {
			temperatureList.add(0.1);
		} catch (Exception ex) {
			System.out.println("Exception when trying to add element to arrayList --- You cannot add or remove elements from a backing-arrayList for an array");
		}	
		
		List<Double> temperatureList02 = Arrays.asList(31.1, 30.0, 32.5, 34.9, 33.7, 27.8);
		temperatureList02.set(0, 35.2);
		System.out.println("The modified array02 is: " + temperatureList02);
		
		try {
			temperatureList02.add(0.1);
		} catch (Exception ex) {
			System.out.println("Exception when trying to add element to arrayList --- You cannot add or remove elements from a backing-arrayList for an array");
		}	
		
		Double [] temperatureArray03 = {31.1, 30.0, 32.5, 34.9, 33.7, 27.8};
		System.out.println("The original array is: " + Arrays.toString(temperatureArray03));
		List<Double> temperatureList03 = new ArrayList<>();
		temperatureList03.addAll(Arrays.asList(temperatureArray03));
		temperatureList03.set(0, 35.2);
		System.out.println("The modified array is (no modifications): " + Arrays.toString(temperatureArray03)); // No changes to the backing-array, as it is not really a backing-array
		
		try {
			temperatureList03.add(0.1);
			System.out.println("You can add and remove if the backing-arrayList is added --- " + temperatureList03);

		} catch (Exception ex) {
			System.out.println("Exception when trying to add element to arrayList --- You cannot add or remove elements from a backing-arrayList for an array");
		}

	}
		
	// LinkedList --- Internally implements a doubly linked list data structure. Fast to insert or delete
	// elements, but slow for searching elements. Additionally, LinkedList can be used when
	// you need a stack (LIFO) or queue (FIFO) data structure. Allows duplicates. It implements the Deque interface
	private static void testLinkedList()
	{
		// LinkedList can be synchronized in this way
		List<String> list01 = Collections.synchronizedList(new LinkedList<String>());
		
		LinkedList<String> list02 = new LinkedList<>();
		
		list02.push("uno"); // it adds at the start of the linkedList (stack way)
		list02.push("dos");
		list02.push("tres");
		
		System.out.println("Original list --- " + list02);
		
		list02.addFirst("ADDED FIRST");
		list02.addLast("ADDED LAST");

		System.out.println("ADDED FIRST AND LAST --- " + list02);
		
		list02.offer("uno"); // it adds at the end of the linkedList (queue way)
		list02.offer("dos");

		System.out.println("SECOND ADDING --- " + list02);
		
		list02.removeFirstOccurrence("uno");
		list02.removeLastOccurrence("dos");

		System.out.println("AFTER REMOVING FIRST AND LAST --- " + list02);
		
		list02.pop(); // == removeFirst(), throws NoSuchElementException 
		list02.poll();
		
		System.out.println("AFTER POP AND POLL --- " + list02);
		
		list02.offerFirst("offerFirst");
		list02.offerLast("offerLast");

		System.out.println("AFTER OFFERFIRST AND OFFERLAST --- " + list02);

		list02.removeFirst();
		list02.removeLast();

		System.out.println("AFTER REMOVEFIRST AND REMOVELAST --- " + list02);

	}
	
	// HashSet --- Internally implemented as a hash-table data structure. Used for storing a set of
	// elements—it does not allow storing duplicate elements. Fast for searching and
	// retrieving elements. It does not maintain any order for stored elements.
	
	
	
	// TreeSet --- Internally implements a red-black tree data structure. Like HashSet, TreeSet does not
	// allow storing duplicates. However, unlike HashSet, it stores the elements in a sorted
	// order. It uses a tree data structure to decide where to store or search the elements, and
	// the position is decided by the sorting order.
	
	
	// HashMap --- Internally implemented as a hash-table data structure. Stores key and value pairs. Uses
	// hashing for finding a place to search or store a pair. Searching or inserting is very fast. It
	// does not store the elements in any order.
	
	
	
	// TreeMap --- Internally implemented using a red-black tree data structure. Unlike HashMap, TreeMap
	// stores the elements in a sorted order. It uses a tree data structure to decide where to
	// store or search for keys, and the position is decided by the sorting order.
	private static void testTreeMap()
	{
		NavigableMap<Integer, String> examScores = new TreeMap<Integer, String>();
		
		examScores.put(90, "Sophia"); 
		examScores.put(20, "Isabella");
		examScores.put(10, "Emma"); 
		examScores.put(50, "Olivea");
		
		System.out.println("The data in the map is: " + examScores);
		System.out.println("The data in descending order is: " + examScores.descendingMap());
		System.out.println("Details of those who passed the exam: " + examScores.tailMap(40));
		System.out.println("Details of those did not pass the exam: " + examScores.headMap(40));
		System.out.println("Detail of the inmediate entry above the passing score: " + examScores.higherEntry(40));
		System.out.println("Detail of the inmediate entry below the passing score: " + examScores.lowerEntry(40));
		System.out.println("Detail of the first entry of the TreeMap: " + examScores.firstEntry());
		System.out.println("Detail of the last entry of the TreeMap: " + examScores.lastEntry());

	}
	
	
	// PriorityQueue --- Internally implemented using heap data structure. A PriorityQueue is for retrieving
	// elements based on priority. Irrespective of the order in which you insert, when you
	// remove the elements, the highest priority element will be retrieved first.

	
	
	// ArrayDeque --- is a special kind of a growable array that allows us to add or remove an element from both sides.
	//  It implements the Deque interface
	private static void testArrayDeque()
	{
		Deque<String> stack = new ArrayDeque<>();
		stack.push("first");  // push inserts at the beginning of the ArrayDeque (stack way)
	    stack.push("second");
	    stack.push("third"); // this is in the first position
		System.out.println("The data in the ArrayDeque  is: " + stack);

		System.out.println("The first in the ArrayDeque is: " + stack.peekFirst());
		System.out.println("The first in the ArrayDeque is (throws exception): " + stack.getFirst()); // throws exception

		System.out.println("The last in the ArrayDeque is: " + stack.peekLast());
		System.out.println("The last in the ArrayDeque is (throws exception): " + stack.getLast()); // throws exception

	    stack.pop(); // throws exception if empty
		System.out.println("The data in the ArrayDeque  is: " + stack);

		
		System.out.println();

		Deque<String> queue = new ArrayDeque<>();
		queue.offer("first");   // offer inserts at the end of the ArrayDeque
		queue.offer("second");
		queue.offer("third"); // this is in the last position
		System.out.println("The data in the ArrayDeque  is: " + queue);

		System.out.println("The first in the ArrayDeque is: " + queue.peekFirst());
		System.out.println("The first in the ArrayDeque is (throws exception): " + queue.getFirst()); // throws exception

		System.out.println("The last in the ArrayDeque is: " + queue.peekLast());
		System.out.println("The last in the ArrayDeque is (throws exception): " + queue.getLast()); // throws exception

		queue.poll(); // returns null if empty
		System.out.println("The data in the ArrayDeque  is: " + queue);
		
		
		queue.removeFirstOccurrence("first");
		queue.removeLastOccurrence("second");
				
		System.out.println("ArrayDeque  after removing first and last: " + queue);

	}
	
	
	
	public static void main(String []args) {
		
		System.out.println("testArrayList --------------------------------------------------");
		testArrayList();
		System.out.println();
		
		System.out.println("testLinkedList -------------------------------------------------");
		testLinkedList();
		System.out.println();
		
		System.out.println("testTreeMap ----------------------------------------------------");
		testTreeMap();		
		System.out.println();

		System.out.println("testArrayDeque -------------------------------------------------");
		testArrayDeque();		
		System.out.println();

		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

	}
}
