package mycoding;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReadWriteLockTest
{

    private static final Queque q=new Queque();

    public static void main(String[] args)
    {
        for (int i = 0; i <3 ; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    q.get();
                }
            }).start();


            new Thread(new Runnable()
            {
                @Override
                public void run()
                {

                    q.put(new Random().nextInt(5000));
                }
            }).start();
        }
    }


}



class Queque
{
    private int data;

    private final ReadWriteLock rwl=new ReentrantReadWriteLock();

    public void get()
    {

        try
        {
            rwl.readLock().lock();
            System.out.println(Thread.currentThread().getName()+"   is ready to read data");
            Thread.sleep(new Random().nextInt(5000));
            System.out.println(Thread.currentThread().getName()+"  have read data "+data);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            rwl.readLock().unlock();
        }


    }



    public void put(int data1)
    {

        try
        {
            rwl.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"   is ready to write data");
            Thread.sleep(new Random().nextInt(5000));
            this.data=data1;
            System.out.println(Thread.currentThread().getName()+"  have write data  "+data);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            rwl.writeLock().unlock();
        }


    }


}
