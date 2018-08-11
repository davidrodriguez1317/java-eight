package com.dro.eight.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncronizingData {

	private static final int ROUNDS = 20;
	private static int counter;
	private static AtomicInteger atomicCounter;
	
	public static void main(String... args) throws InterruptedException {
		
		countingWithoutSyncronization();
		countingWithoutSyncronization();
		countingWithoutSyncronization();

		Thread.sleep(1000);
		
		countingWithAtomicVariables();
		countingWithAtomicVariables();
		countingWithAtomicVariables();
	
		Thread.sleep(1000);
		
		countingWithSyncronization();
		countingWithSyncronization();
		countingWithSyncronization();
	
	}
	
	/**
	 * With this method the result will be unordered, and even some numbers can be repeated
	 * @throws InterruptedException
	 */
	public static void countingWithoutSyncronization() throws InterruptedException {
		
		counter = 0;
		
		System.out.print("countingWithoutSyncronization ---------- ");
		
		ExecutorService service = null;
		
		try {
			
			service = Executors.newFixedThreadPool(8);
			
			for(int i = 0; i < ROUNDS; i++) {
				service.submit(() -> incrementAndReportWithoutSyncronization());
			}
		} finally {
			if (service != null) {
				service.shutdown();
			}
		}
		
		Thread.sleep(1000);

		System.out.println();
		
	}
	
	/**
	 * With this method we are sure that we will get all the numbers, but not of the order
	 * @throws InterruptedException
	 */
	public static void countingWithAtomicVariables() throws InterruptedException {
		
		atomicCounter = new AtomicInteger(0);
		
		System.out.print("countingWithAtomicVariables   ---------- ");
		
		ExecutorService service = null;
		
		try {
			
			service = Executors.newFixedThreadPool(8);
			
			for(int i = 0; i < ROUNDS; i++) {
				service.submit(() -> incrementAndReportWithAtomicVariable());
			}
		} finally {
			if (service != null) {
				service.shutdown();
			}
		}
		
		Thread.sleep(1000);

		System.out.println();
	}
	
	/**
	 * Syncronizing this method we are sure that we get all the numbers and with the correct order
	 * @throws InterruptedException
	 */
	public static void countingWithSyncronization() throws InterruptedException {
		
		counter = 0;
		
		System.out.print("incrementAndReportWithSyncronization --- ");
		
		ExecutorService service = null;
		
		try {
			
			service = Executors.newFixedThreadPool(8);
			
			for(int i = 0; i < ROUNDS; i++) {
				service.submit(() -> incrementAndReportWithSyncronization());
			}
		} finally {
			if (service != null) {
				service.shutdown();
			}
		}
		
		Thread.sleep(1000);

		System.out.println();
	}
	
	
	////////////////////////
	
	private static void incrementAndReportWithoutSyncronization() {
	
		System.out.print((++counter) + " ");
	}
	
	private static void incrementAndReportWithAtomicVariable() {
		
		System.out.print((atomicCounter.incrementAndGet()) + " ");
	}
	
	private static void incrementAndReportWithSyncronization() {
		
		synchronized(SyncronizingData.class) {
			System.out.print((++counter) + " ");
		}
	}
}
