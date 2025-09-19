package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Aeroporto {

	private static final Semaphore Semaforodecolagem = new Semaphore(2);
	private static final Semaphore norte = new Semaphore(1);
	private static final Semaphore sul = new Semaphore(1);
	
	private static void FaseDecolagem(String fase, int tempo) {
		try{
			System.out.println(Thread.currentThread().getName() + " Está na fase: " + fase);
			Thread.sleep(tempo);
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	static class aviao implements Runnable{
		private final Random random = new Random();
		public void run() {
			try {
				String pista = random.nextBoolean() ? "Norte" : "Sul";
				
				if(pista.equals("Norte")) {
					norte.acquire();
				}else {
					sul.acquire();
				}
				
				Semaforodecolagem.acquire();
				
				int taxiar = random.nextInt(501) + 500;
				int manobra = random.nextInt(401) + 300;
				int decolagem = random.nextInt(201) + 600;
				
				
				FaseDecolagem("Taxiar", taxiar);
				FaseDecolagem("Manobra", manobra);
				FaseDecolagem("Decolagem", decolagem);
				
				Semaforodecolagem.release();
				
				if(pista.equals("Norte")) {
					norte.release();
				}else {
					sul.release();
				}
				System.out.println(Thread.currentThread().getName() + " decolou.");
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
			

			
		}

	}


}
