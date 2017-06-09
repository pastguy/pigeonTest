package ymm.pigeon.filterandhandle;

public  class LogFilter implements IFilter{

	@Override
	public void filter(InvocationHandle hanlde, String str) {
		System.out.println("LogFilter start");
		hanlde.handle(str);
		System.out.println("LogFilter end");
		
	}

}
