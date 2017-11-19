package mycoding;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreeCondition
{
    public static void main(String[] args)
    {
        final Business business=new Business();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i <10 ; i++)
                {
                    business.sub1(i);
                }

            }
        }).start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i <10 ; i++)
                {
                    business.sub2(i);
                }
            }
        }).start();

        for (int i = 0; i <10 ; i++)
        {
            business.main(i);
        }
    }





}


class Business
{
   private  Lock lock=null;
   private Condition condition1=null;
   private Condition condition2=null;
   private Condition condition3=null;
   private int startnum=1;
   public Business()
   {
       lock=new ReentrantLock();
       condition1=lock.newCondition();
       condition2=lock.newCondition();
       condition3=lock.newCondition();
   }

   public void sub1(int i)
   {
       lock.lock();
       try
           {
               while (startnum!=1)
               {
                   condition1.await();
               }

               for (int j = 0; j <10 ; j++)
               {
                   System.out.println("sub1 thread work"+j+" loop of "+i);
               }

               startnum=2;
               condition2.signal(); //叫醒  线程2


           } catch (InterruptedException e)
           {
               e.printStackTrace();
           }
           finally
       {
           lock.unlock();
       }
   }


    public void sub2(int i)
    {
        lock.lock();
        try
        {
            while (startnum!=2)
            {
                condition2.await();
            }

            for (int j = 0; j <10 ; j++)
            {
                System.out.println("sub2 thread work"+j+" loop of "+i);
            }
            startnum=3;
            condition3.signal();


        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
    }



    public void main(int i)
    {
        lock.lock();
        try
        {
            while (startnum!=3)
            {
                condition3.await();
            }

            for (int j = 0; j <10 ; j++)
            {
                System.out.println("main thread work"+j+"  loop of "+i);
            }
            startnum=1;
            condition1.signal();


        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
    }





}