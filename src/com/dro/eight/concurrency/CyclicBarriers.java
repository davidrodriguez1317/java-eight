package com.dro.eight.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarriers {

	private void removeAnimals() {
		System.out.println("Removing animals");
	}
	
	private void cleaningPen() {
		System.out.println("Cleaning the pen");
	}
	
	private void addAnimals() {
		System.out.println("Adding the animals");
	}
	
	
	private void performTask(CyclicBarrier c1, CyclicBarrier c2) {
		try {
			removeAnimals();
			c1.await();
			cleaningPen();
			c2.await();
			addAnimals();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.getMessage();
		}
	}
	
	
	public static void main(String[] args) {
		ExecutorService service = null;
		
		try {
			service = Executors.newFixedThreadPool(4);
			
			CyclicBarriers manager = new CyclicBarriers();
			
			CyclicBarrier c1 = new CyclicBarrier(4);
			CyclicBarrier c2 = new CyclicBarrier(4,
					() -> System.out.println("*** Pen cleaned ***"));
			
			for(int i=0; i < 4; i++) {
				service.submit(() -> manager.performTask(c1, c2));
			}
									
		} finally {
			if (service != null) {
				service.shutdown();
			}
		}

	}

}
