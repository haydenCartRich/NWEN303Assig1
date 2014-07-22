package Lab1;

public class tutorial1 {

	private static final int THREADS = 2;
	private static final int MAXKEY = Integer.MAX_VALUE-THREADS;
	private static int key = (int)(Math.random() * MAXKEY);

	public static void main(String[] args){
		final Thread[] threads = new Thread[THREADS];
		for(int i = 0; i < THREADS; i++){
			final int start = i;
			threads[i] = new Thread(new Runnable(){
				public void run(){
					for(int j = start; j< MAXKEY; j+=THREADS){
						if(j == key){
							System.out.println(Thread.currentThread()+" found it! Key was " + j);
							for (int i = 0; i < threads.length; i++){
								threads[i].interrupt();
							}
						}
					}
				}
			});
		}
		for(int i = 0; i < THREADS; i++){
			threads[i].start();
		}
	}
}

