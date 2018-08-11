package com.dro.eight.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

public class ExecutorServiceThreads {

	private static int counter;
	private static int counter2;

	
	public static void main(String... args) throws InterruptedException{

		singleExecutorService();
		
		Thread.sleep(1000);
		
		singleExecutorServiceWithResult();
		
		Thread.sleep(1000);

		singleExecutorServiceShutdown();
		
		Thread.sleep(1000);

		scheduledExecutorService();
		
		Thread.sleep(1000);
		
		scheduledExecutorServiceWithTime();
	}
	
	/**
	 * Single thread executor
	 */
	public static void singleExecutorService() {
		ExecutorService service = null;
		
		try {
			
			service = Executors.newSingleThreadExecutor();
			
			System.out.println("Start BasicExecutorService");

			service.execute(() -> System.out.println(Thread.currentThread().getName()));
			service.execute(() -> {
				for (int i = 0; i < 3; i++) {
					System.out.println("Printing record: " + Thread.currentThread().getName() + " - " + i);
				}
			});

			System.out.println("End BasicExecutorService");
			
		} finally {
			if(service != null) {
				service.shutdown();
			}
		}				
	}
	
	/**
	 * Single thread executor with result
	 */
	public static void singleExecutorServiceWithResult() {
		
		System.out.println();

		ExecutorService service = null;
		
		counter = 0;
		
		try {
			service = Executors.newSingleThreadExecutor();
			
			System.out.println("Start singleExecutorServiceWithResult");

			// Submit returns a result, but execute does not
			Future<Integer> result = service.submit(() -> {
				for (int i = 0; i < 500; i++) {
					counter++;
				}; return counter;
			});
			
			System.out.println("Result : " + result.get(3, TimeUnit.SECONDS));
			
			System.out.println("End singleExecutorServiceWithResult");

			
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		} finally {
			if(service != null) {
				service.shutdown();
			}
		}		
	}
	
	/**
	 * Single thread executor
	 * @throws InterruptedException 
	 */
	public static void singleExecutorServiceShutdown() throws InterruptedException {
		
		System.out.println();

		ExecutorService service = null;
		
		try {
			
			service = Executors.newSingleThreadExecutor();
			
			System.out.println("Start singleExecutorServiceShutdown");

			service.execute(() -> System.out.println(Thread.currentThread().getName()));
			service.execute(() -> {
				for (int i = 0; i < 3; i++) {
					System.out.println("Printing record: " + Thread.currentThread().getName() + " - " + i);
				}
			});
			
			// Submit returns a result, but execute does not
			Future<Integer> result = service.submit(() -> {
				for (int i = 0; i < 5000; i++) {
					counter++;
				}; return counter;
			});
			
			Future<Long> longResult = service.submit(() -> {
				Long factorial = IntStream.iterate(1, x -> ++x)
						.limit(20)
						.mapToLong(y -> y)
						.reduce(1L, (a,b) -> a*b);
				return factorial;
			});		

			System.out.println("Factorial --- " + longResult.get());
					
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			if(service != null) {
				service.shutdown();
			}
		}
		
		if(service!= null) {
			// This method waits the specified time to complete all tasks, returning sooner if all
			// tasks finish
			service.awaitTermination(1, TimeUnit.NANOSECONDS);
			if(service.isTerminated()) {
				System.out.println("All tasks finished");
			} else {
				System.out.println("Not all tasks finished");
			}
		}
		
		System.out.println("End singleExecutorServiceShutdown");

		
	}
	
	/**
	 * This method creates a pool of threads and then executes taks with a delay
	 */
	public static void scheduledExecutorService() {
		
		System.out.println();
				
		int processors = Runtime.getRuntime().availableProcessors();

		System.out.println("Start scheduledExecutorService --- processors = " + processors);

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(processors);

		ScheduledFuture<String> scheduledFuture = scheduledExecutorService.schedule(new Callable<String>() {
		        public String call() throws Exception {
		            System.out.println(Thread.currentThread().getName() + " Executed!");
		            return "Called!";
		        }
		    }, 2, TimeUnit.SECONDS);

	
		scheduledFuture = scheduledExecutorService.schedule(() -> {
			
				Long factorial = IntStream.iterate(1, x -> ++x)
						.limit(20)
						.mapToLong(y -> y)
						.reduce(1L, (a,b) -> a*b);
	            System.out.println(Thread.currentThread().getName() + " Factorial executed!");

				return factorial.toString();
						
		}, 1, TimeUnit.SECONDS);
		
		
		try {
			System.out.println("result = " + scheduledFuture.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		if(scheduledExecutorService != null) {
			scheduledExecutorService.shutdown();
		}
		
		System.out.println("End scheduledExecutorService");

	}
	
	/**
	 * Scheduled executor at fixed rate and with fixed delay
	 */
	public static void scheduledExecutorServiceWithTime() throws InterruptedException {
		
		counter = counter2 = 0;
		
		System.out.println();
		
		int processors = Runtime.getRuntime().availableProcessors();

		System.out.println("Start scheduledExecutorServieWithTime --- processors = " + processors);
		
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(processors);
		
		
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			Long delay = 0L + (long) (Math.random() * (1000L - 0L));
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Task 01 - " + System.currentTimeMillis() + " --- counter 1 = " + ++counter);
		}, 500L, 300L, TimeUnit.MILLISECONDS);
		
		scheduledExecutorService.scheduleWithFixedDelay(() -> {
			Long delay = 0L + (long) (Math.random() * (1000L - 0L));
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Task 02 - " + System.currentTimeMillis() + " --- counter 2 = " + ++counter2);
		}, 500L, 300L, TimeUnit.MILLISECONDS);
		
		Thread.sleep(5000L);

		if(scheduledExecutorService != null) {
			scheduledExecutorService.shutdown();
		}
		
		System.out.println("End scheduledExecutorServieWithTime");

		
	}
	
	
	
	
	
	
	
	
	
}
