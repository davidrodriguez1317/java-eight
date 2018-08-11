package com.dro.eight.concurrency;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTaskRecursiveTask extends RecursiveTask<Double> {

	private int start;
	private int end;
	private Double[] weights;
	
	public ForkJoinTaskRecursiveTask(int start, int end, Double[] weights) {
		super();
		this.start = start;
		this.end = end;
		this.weights = weights;
	}

	// RecursiveTask obliges to return a value
	protected Double compute() {
		// The task is broken into two if it is bigger than 3
		if(end - start <= 3) {
			double sum = 0;
			for(int i=start; i< end; i++) {
				weights[i] = (double) new Random().nextInt(100);
				System.out.println("Animal weighted: " + i + " --- weight= " + weights[i]);
				sum += weights[i];
			}
			return sum;
		} else {
			int middle = start + ((end - start) / 2);
			System.out.println("[start=]" + start + " ,middle=" + middle + " ,end=" + end);
			RecursiveTask<Double> otherTask = new ForkJoinTaskRecursiveTask(start, middle, weights);
			otherTask.fork();
			return new ForkJoinTaskRecursiveTask(middle, end, weights).compute() + otherTask.join();
		}
	}

	public static void main(String[] args) {
		
		Double[] weights = new Double[20];
		
		ForkJoinTask<Double> task = new ForkJoinTaskRecursiveTask(0, weights.length, weights);
		ForkJoinPool pool = new ForkJoinPool();
		Double sum = pool.invoke(task);
		
		System.out.println();
		System.out.println("Weights: ");
		Arrays.asList(weights).stream()
		.forEach(d -> System.out.print(d.intValue() + " - "));

		System.out.println();
		System.out.println("Total weight= " + sum);


	}

}
