package com.dro.eight.concurrency;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ForkJoinTaskRecursiveAction extends RecursiveAction {

	private int start;
	private int end;
	private Double[] weights;
	
	public ForkJoinTaskRecursiveAction(int start, int end, Double[] weights) {
		super();
		this.start = start;
		this.end = end;
		this.weights = weights;
	}

	// RecursiveAction does not return a value
	protected void compute() {
		// The task is broken into two if it is bigger than 3
		if(end - start <= 3) {
			for(int i=start; i< end; i++) {
				weights[i] = (double) new Random().nextInt(100);
				System.out.println("Animal weighted: " + i + " --- weight= " + weights[i]);
			}
		} else {
			int middle = start + ((end - start) / 2);
			System.out.println("[start=]" + start + " ,middle=" + middle + " ,end=" + end);
			invokeAll(new ForkJoinTaskRecursiveAction(start, middle, weights),
					new ForkJoinTaskRecursiveAction(middle, end, weights));
		}
	}

	public static void main(String[] args) {
		
		Double[] weights = new Double[20];
		
		ForkJoinTask<?> task = new ForkJoinTaskRecursiveAction(0, weights.length, weights);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(task);
		
		System.out.println();
		System.out.println("Weights: ");
		Arrays.asList(weights).stream()
		.forEach(d -> System.out.print(d.intValue() + " - "));


	}

}
