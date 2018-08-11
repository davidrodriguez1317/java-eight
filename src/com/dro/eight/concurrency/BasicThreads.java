package com.dro.eight.concurrency;

public class BasicThreads implements Runnable {

	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("Printing record: " + Thread.currentThread().getName() + " - " + i);
		}
	}

	public static void main(String... args) {

		System.out.println("Start");
		(new Thread(new BasicThreads(), "Thread 1")).start();
		(new Thread(new BasicThreads(), "Thread 2")).start();
		(new Thread(new BasicThreads(), "Thread 3")).start();

		System.out.println("End");

	}

}
