package ymm.pigeon.service;

import java.util.List;

public interface EchoService {

	public String echo(String name);
	
	public String echoUsers(List<User> users);
}