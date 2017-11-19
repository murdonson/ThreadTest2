package mycoding;



public class MyMultiThreadShareData
{
    private static final Data data=new Data();

    public static void main(String[] args)
    {

      new Thread(new A(data)).start();

      new Thread(new B(data)).start();
    }






}


class A implements Runnable{
    private Data data;
    public A(Data data)
    {
        this.data=data;
    }
    @Override
    public void run()
    {
        while(true)
        {
            data.increment();
            try
            {
                Thread.sleep(1_000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" increase "+data.getJ());
        }

    }
}



class B implements Runnable{
    private Data data;
    public B(Data data)
    {
        this.data=data;
    }
    @Override
    public void run()
    {
        while (true)
        {
            data.decreasment();
            try
            {
                Thread.sleep(1_000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" decrease "+data.getJ());
        }

    }
}


class Data
{
    private int j=0;

    public int getJ()
    {
        return j;
    }

    public synchronized void increment()
    {
        j++;
    }
    public synchronized void decreasment()
    {
        j--;
    }

}