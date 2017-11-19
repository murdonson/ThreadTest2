package cn.itcast.heima2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LockTest().init();
	}
	
	private void init(){
		final Outputer outputer = new Outputer();
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output2(Thread.currentThread().getName()+"zhangxiaoxiang");
				}
			}
		}).start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// output2 线程不安全 会出现这个结果 Thread-1lihThread-0zhangxiuomingaox

					/*
					*
					* 		第二个线程输出到lih的时候 cpu切到第一个线程 输出zhangxi 然后
					*
					* */
					outputer.output2(Thread.currentThread().getName()+"lihuoming");
				}
				
			}
		}).start();
		
	}

	static class Outputer{

		Lock lock = new ReentrantLock();
		public void output(String name){
			int len = name.length();
			lock.lock();
			try{
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}finally{
				lock.unlock();
			}
		}
		
		public synchronized void output2(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		public static synchronized void output3(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}	
	}
}
