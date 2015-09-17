import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) throws InterruptedException
	{
		List<Task> threads = new ArrayList<Task>();
		long inicio=System.currentTimeMillis();
		for(int i=0;i<100;i++)
		{
			Thread.sleep(10);
			Task t = new Task();
			System.out.println("Thread "+i+" creado");
			threads.add(t);
			t.start();
		}
		
		double errores =0;
		long suma = 0;
		int terminaron=0;
		double total =0;
		while(!threads.isEmpty())
		{
			Task t = threads.get(0);
			if(!t.done)continue;
			
			System.out.println(terminaron++);
			if(t.error)errores++;
			suma+=t.total;
			total++;
			threads.remove(0);
		}
		long tot = System.currentTimeMillis()-inicio;
		System.out.println("%ERROR: "+ (errores/total));
		System.out.println("MEDIA: "+ (suma/total));
		System.out.println(String.format("RENDIMIENTO: %.2f", total/tot));
	}
}
