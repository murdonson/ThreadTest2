package mycoding;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCyclicBarrierTest
{

    public static void main(String[] args)
    {
        ExecutorService threadpool= Executors.newCachedThreadPool();
        final CyclicBarrier cb=new CyclicBarrier(4);

        Runnable task=new Runnable()
        {
            @Override
            public void run()
            {
                try
                    {
                        System.out.println(Thread.currentThread().getName()+" is about to arrive at location 1");
                        Thread.sleep(new Random().nextInt(5000));
                        System.out.println((cb.getNumberWaiting()+1)+"have arrived "+(cb.getNumberWaiting()==3?" all arrived go ":" waiting"));
                        cb.await();


                        System.out.println(Thread.currentThread().getName()+" is about to arrive at location 2");
                        Thread.sleep(new Random().nextInt(5000));
                        System.out.println((cb.getNumberWaiting()+1)+"have arrived "+(cb.getNumberWaiting()==3?" all arrived go ":" waiting"));
                        cb.await();


                        System.out.println(Thread.currentThread().getName()+" is about to arrive at location 3");
                        Thread.sleep(new Random().nextInt(5000));
                        System.out.println((cb.getNumberWaiting()+1)+"have arrived "+(cb.getNumberWaiting()==3?" all arrived go ":" waiting"));
                        cb.await();

                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        };


        for (int i = 0; i <4 ; i++)
        {
            threadpool.execute(task);
        }

        threadpool.shutdown();

    }


}
