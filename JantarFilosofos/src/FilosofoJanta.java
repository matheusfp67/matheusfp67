import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FilosofoJanta implements Runnable{

//como você não especificou professor, coloquei um pouquinho de pausa aleatória entre cada filosófo pegando o garfo, 
//caso contrário, não iria demonstrar a disputa direito
//ah e também é só jogar tudo num for o que você quiser que seja repetido (pensar/comer/pensar e comer)

	private Semaphore garfo1 = new Semaphore(1);
	private Semaphore garfo2 = new Semaphore(1);
	private Semaphore garfo3 = new Semaphore(1);
	private Semaphore garfo4 = new Semaphore(1);
	private Semaphore garfo5 = new Semaphore(1);
	Random rand = new Random();
	ArrayList<Thread> comeu = new ArrayList<Thread>();
	int index = 0;

	public void run (){
	//pensar
		try{
			System.out.println(Thread.currentThread().getName() + " está refletindo");
			Thread.sleep(rand.nextInt((1000-100)+1) + 10);
			System.out.println(Thread.currentThread().getName() + " parou de refletir");
			}catch (InterruptedException e) { 
				e.printStackTrace (); 
			}
	//comer
		try {
			if(Thread.currentThread().getName().equals("Filosofo 1")){
				System.out.println(Thread.currentThread().getName() + " está tentando comer");
				boolean t = garfo1.tryAcquire(1000, TimeUnit.MILLISECONDS) & garfo2.tryAcquire(1000, TimeUnit.MILLISECONDS);
				if(t = true) {
					comeu.add(Thread.currentThread());
					System.out.println(Thread.currentThread().getName() + " começou a comer");
					Thread.sleep(rand.nextInt((1000-100)+1) + 10);
				}
				else {
					System.out.println(Thread.currentThread().getName() + " RIP");
				}
			}
			if(Thread.currentThread().getName().equals("Filosofo 2")){
				System.out.println(Thread.currentThread().getName() + " está tentando comer");
				boolean t = garfo2.tryAcquire(1000, TimeUnit.MILLISECONDS) & garfo3.tryAcquire(1000, TimeUnit.MILLISECONDS);
				if(t = true) {
					comeu.add(Thread.currentThread());
					System.out.println(Thread.currentThread().getName() + " começou a comer");
					Thread.sleep(rand.nextInt((1000-100)+1) + 10);
				}
				else {
					System.out.println(Thread.currentThread().getName() + " RIP");
				}
			}
			if(Thread.currentThread().getName().equals("Filosofo 3")){
				System.out.println(Thread.currentThread().getName() + " está tentando comer");
				boolean t = garfo3.tryAcquire(1000, TimeUnit.MILLISECONDS) & garfo4.tryAcquire(1000, TimeUnit.MILLISECONDS);
				if(t = true) {
					comeu.add(Thread.currentThread());
					System.out.println(Thread.currentThread().getName() + " começou a comer");
					Thread.sleep(rand.nextInt((1000-100)+1) + 10);
				}
				else {
					System.out.println(Thread.currentThread().getName() + " RIP");
				}
			}
			if(Thread.currentThread().getName().equals("Filosofo 4")){
				System.out.println(Thread.currentThread().getName() + " está tentando comer");
				boolean t = garfo4.tryAcquire(1000, TimeUnit.MILLISECONDS) & garfo5.tryAcquire(1000, TimeUnit.MILLISECONDS);
				if(t = true) {
					comeu.add(Thread.currentThread());
					System.out.println(Thread.currentThread().getName() + " começou a comer");
					Thread.sleep(rand.nextInt((1000-100)+1) + 10);
				}
				else {
					System.out.println(Thread.currentThread().getName() + " RIP");
				}
			}
			if(Thread.currentThread().getName().equals("Filosofo 5")){
				System.out.println(Thread.currentThread().getName() + " está tentando comer");
				boolean t = garfo5.tryAcquire(1000, TimeUnit.MILLISECONDS) & garfo1.tryAcquire(1000, TimeUnit.MILLISECONDS);
				if(t = true) {
					comeu.add(Thread.currentThread());
					System.out.println(Thread.currentThread().getName() + " começou a comer");
					Thread.sleep(rand.nextInt((1000-100)+1) + 10);
				}
				else {
					System.out.println(Thread.currentThread().getName() + " RIP");
				}
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
	    }finally {
			if(comeu.get(index).getName().equals("Filosofo 1")) {
				garfo1.release();
				garfo2.release();
			}
			if(comeu.get(index).getName().equals("Filosofo 2")) {
				garfo2.release();
				garfo3.release();
			}
			if(comeu.get(index).getName().equals("Filosofo 3")) {
				garfo3.release();
				garfo4.release();
			}
			if(comeu.get(index).getName().equals("Filosofo 4")) {
				garfo4.release();
				garfo5.release();
			}
			if(comeu.get(index).getName().equals("Filosofo 5")) {
				garfo5.release();
				garfo1.release();
				}
			}
		
		System.out.println(comeu.get(index).getName()+" terminou de comer");
		index++;
	}
}

	



