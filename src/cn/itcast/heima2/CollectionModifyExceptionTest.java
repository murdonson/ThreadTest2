package cn.itcast.heima2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
public class CollectionModifyExceptionTest {
	public static void main(String[] args) {
		Collection users = new CopyOnWriteArrayList();

		//Collection users=new ArrayList();
			
			//new ArrayList();
		users.add(new User("����",28));	
		users.add(new User("����",25));			
		users.add(new User("����",31));	
		Iterator itrUsers = users.iterator();
		while(itrUsers.hasNext()){
			System.out.println("aaa");
			User user = (User)itrUsers.next();
			System.out.println(user);
			if("����".equals(user.getName())){
				users.remove(user);
				//itrUsers.remove();
			} else {
				System.out.println(user);				
			}
		}
	}
}	 
