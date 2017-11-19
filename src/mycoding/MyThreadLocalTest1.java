package mycoding;


import java.util.Random;

public class MyThreadLocalTest1
{


    public static void main(String[] args)
    {
        for (int i = 0; i <2 ; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    int data= new Random().nextInt(50);
                    User.getInstance().setName("name"+data);
                    User.getInstance().setAge(data);
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
            User user=User.getInstance();
            System.out.println("A from "+Thread.currentThread().getName()+"get name "+user.getName()+" get age "+user.getAge());
        }
    }


    static class B
    {
        public void get()
        {
            User user=User.getInstance();   //就是从ThreadLocal取数据
            System.out.println("B from "+Thread.currentThread().getName()+"get name "+user.getName()+" get age "+user.getAge());
        }
    }






}



class User
{
    public static ThreadLocal<User> map=new ThreadLocal<User>();
    private String name;
    private int age;
    public static User getInstance()
    {
        // 懒加载模式  只有等到需要的时候才加载
        // 双重加锁
        User user=map.get();
        if(user==null)
        {
            synchronized (User.class)
            {
                if(user==null)
                    user=new User();
            }

            map.set(user);// user对象绑定到线程上
        }
        return user;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}



