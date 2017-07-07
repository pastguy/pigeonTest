package ymm.pigeon.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ymm.pigeon.service.EchoService;
import ymm.pigeon.service.User;

public class Invoker {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("invoker.xml");
	    context.start();
		EchoService echoService = (EchoService)context.getBean("echoService"); // 获取远程服务代理
		String hello = echoService.echo("world");
		List<User> users = new ArrayList<User>();
		User u1 = new User();
		u1.setAge(12);
		u1.setName("a");
		u1.setMoney(200l);
		users.add(u1);
		users.add(u1);
		echoService.echoUsers(users);
		System.out.println( hello );
	}
}
