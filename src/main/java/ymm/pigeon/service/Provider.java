package ymm.pigeon.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ymm.pigeon.Person;

public class Provider {

	public static void main(String[] args) throws Exception {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"provider.xml"});
    	context.start();
     	System.in.read(); // 按任意键退出
    }
}
