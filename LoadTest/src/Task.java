
public class Task extends Thread
{
	public long total;
	boolean error, done;
	public void run() {
		done = false;
		error = false;
		long inicio = System.currentTimeMillis();
		try {
			REST.GET("https://arqui201326232.herokuapp.com/informes");
		} catch (Exception e) {
			//e.printStackTrace();
			error = true;
		}
		//System.out.println("Termino");
		total = System.currentTimeMillis() -inicio;
		done = true;
	}
}
