package ymm.pigeon.service;

public class EchoServiceImpl implements EchoService {

	public String echo(String name) {
		return "Hello " + name;
	}
	
}
