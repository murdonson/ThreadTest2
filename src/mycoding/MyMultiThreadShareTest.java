package mycoding;

public class MyMultiThreadShareTest
{
    private int j;


    public static void main(String[] args)
    {
        MyMultiThreadShareTest tt=new MyMultiThreadShareTest();

        Inc inc=tt.new Inc();
        Dec dec=tt.new Dec();

        for(int i=0;i<2;i++)
        {
            Thread t=new Thread(inc);
            t.start();
            t=new Thread(dec);
            t.start();
        }
    }




    private synchronized void increasement()
    {
        System.out.println(Thread.currentThread().getName()+" before  inc "+j);
        j++;
        System.out.println(Thread.currentThread().getName()+" after  inc "+j);
    }
    private synchronized void decreasement()
    {
        System.out.println(Thread.currentThread().getName()+"  before dec "+j);
        j--;
        System.out.println(Thread.currentThread().getName()+"  after dec "+j);

    }



    class Inc implements Runnable{
        @Override
        public void run()
        {
            increasement();

        }
    }

    class Dec implements Runnable
    {
        @Override
        public void run()
        {
            decreasement();
        }
    }


}
