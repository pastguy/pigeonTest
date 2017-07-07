package ymm.pigeon.service;

import java.util.List;

public class EchoServiceImpl implements EchoService {

	public String echo(String name) {
		return "Hello " + name;
	}

	@Override
	public String echoUsers(List<User> users) {
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users);
		}
		return null;
	}
	
}
