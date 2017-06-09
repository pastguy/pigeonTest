package ymm.pigeon.filterandhandle;

public class MonitorFilter implements IFilter{

	@Override
	public void filter(InvocationHandle hanlde, String str) {
		System.out.println("MonitorFilter start");
		hanlde.handle(str);
		System.out.println("MonitorFilter end");
		
	}

}
