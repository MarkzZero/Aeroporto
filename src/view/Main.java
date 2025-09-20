package view;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.Aeroporto.aviao;

public class Main {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(12);
		
		for(int i = 0; i < 12; i++) {
			executor.submit(new aviao());
		}
		
		executor.shutdown();

	}

}
