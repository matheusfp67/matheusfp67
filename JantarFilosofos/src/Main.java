import java.util.ArrayList;
public class Main {
	public static void main(String []args){
		
	FilosofoJanta fj = new FilosofoJanta();
	ArrayList<Thread> filosofos = new ArrayList<Thread>();
	int pos = 0;
	
	for (int i=0; i<5; i++) {
		filosofos.add(i, new Thread(fj));
		filosofos.get(i).start();
		pos++;
		filosofos.get(i).setName("Filosofo " + (pos));
		}
	}
}