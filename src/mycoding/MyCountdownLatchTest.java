package mycoding;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCountdownLatchTest
{

    public static void main(String[] args)
    {
        ExecutorService service=Executors.newCachedThreadPool();
        final CountDownLatch corder=new CountDownLatch(1);
        final CountDownLatch canswer=new CountDownLatch(3);
        for (int i = 0; i <3 ; i++)
        {
            Runnable task=new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        System.out.println(Thread.currentThread().getName()+" is ready to run");
                        corder.await();
                        Thread.sleep(new Random().nextInt(8000));
                        System.out.println(Thread.currentThread().getName()+" have done");
                        canswer.countDown();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(task);
        }

        try
        {
            Thread.sleep(new Random().nextInt(5000));
            System.out.println(Thread.currentThread().getName()+" all of you go!");
            corder.countDown();
            canswer.await();
            System.out.println(Thread.currentThread().getName()+" get the result");

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        service.shutdown();



    }
}
