package ymm.pigeon.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ymm.pigeon.service.EchoService;

public class Invoker {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("invoker.xml");
	    context.start();
		EchoService echoService = (EchoService)context.getBean("echoService"); // 获取远程服务代理
		String hello = echoService.echo("world");
		System.out.println( hello );
	}
}
