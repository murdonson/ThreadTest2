package cn.itcast.heima2;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueCommunication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Business business = new Business();
		new Thread(
				new Runnable() {
					
					@Override
					public void run() {
					
						for(int i=1;i<=5;i++){
							business.sub(i);
						}
						
					}
				}
		).start();
		
		for(int i=1;i<=5;i++){
			business.main(i);
			System.out.println(" main :"+i);
		}
		
	}

	 static class Business {
		 
		 
		  BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
		  BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);
		  
		  {
			  //Collections.synchronizedMap(null);
			  try {
				  System.out.println("xxxxxdfsdsafdsa");
				queue2.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		 // 这个地方加 synchronized  这个方法会获得 business对象的锁
		  public /*synchronized*/   void sub(int i){
			  System.out.println(" sub method queue1.put(1) "+Thread.currentThread().getName()+" queue1 "+queue1.size());
			  	try {
					queue1.put(1);
					System.out.println(" sub method queue1.put(1) "+Thread.currentThread().getName()+" queue1 "+queue1.size());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int j=1;j<=10;j++){
					System.out.println(Thread.currentThread().getName()+"   sub thread sequece of " + j + ",loop of " + i);

				}

				try {
					queue2.take();
					Thread.sleep(2_000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }

		  // 这个地方加 synchronized  这个方法会获得 business对象的锁
		  public synchronized   void main(int i)
		  {
			  	try {
					System.out.println(" main thread "+Thread.currentThread().getName()+" queue2 size"+queue2.size());
					queue2.put(1);
					System.out.println("main thread   queue2.put(1)");

				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				for(int j=1;j<=100;j++){
					System.out.println(Thread.currentThread().getName()+"   main thread sequece of " + j + ",loop of " + i);
				}
				try {
					queue1.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		  }
	  }

}
