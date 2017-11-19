package mycoding;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest
{
    private static int count=0;

    static  class myTimerTask extends TimerTask
    {
        /*
        *   每隔2秒 4秒炸一次
        * */
        @Override
        public void run()
        {
            count=(count+1)%2;
            System.out.println("boom");
            new Timer().schedule(new myTimerTask(),2_000*count+2_000);
        }
    }



    static class A extends TimerTask
    {

         /*
        *   每隔2秒 4秒炸一次
        * */

        @Override
        public void run()
        {
            System.out.println("boom");
            if((++count)%2==0)
            {
                new Timer().schedule(  new A(),2000);
            }
            else {
                new Timer().schedule(  new A(),4000);
            }

        }
    }








    public static void main(String[] args)
    {
       /* new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println("boom瞎卡拉卡");
            }
        },4_000,2_000); // 刚开始延迟4秒爆炸 而后每两秒钟爆炸一次*/

        new Timer().schedule(new A(),2000);


        while(true)
        {

            System.out.println(new Date().getSeconds());

            try
            {
                Thread.sleep(1_000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
