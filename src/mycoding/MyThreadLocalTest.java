package mycoding;

import java.util.Random;

public class MyThreadLocalTest
{
        private static ThreadLocal<Integer> x=new ThreadLocal<>();

    public static void main(String[] args)
    {
        for (int i = 0; i <2 ; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    int data=new Random().nextInt(100);
                    // 两个线程放数据
                    x.set(data);
                    System.out.println(Thread.currentThread().getName()+" put data"+data);

                    // 两个对象拿数据
                    new A().get();
                    new B().get();

                }
            }).start();
        }
    }


    static class A
    {
        public void get()
        {
            System.out.println("A from "+Thread.currentThread().getName()+" get data"+x.get());
        }
    }

    static class B
    {
        public void get()
        {
            System.out.println("B from"+Thread.currentThread().getName()+" get data"+x.get());
        }
    }
}
