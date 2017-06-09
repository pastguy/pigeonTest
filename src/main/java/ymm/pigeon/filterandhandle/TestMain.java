package ymm.pigeon.filterandhandle;

import java.util.ArrayList;
import java.util.List;


public class TestMain {

	public static void main(String[] args) {
		List<IFilter> filters = new ArrayList<IFilter>();
		filters.add(new LogFilter());
		filters.add(new MonitorFilter());
		filters.add(new TimeFilter());
		
		InvocationHandle handle = createHandle(filters);
		
		handle.handle("test");
	}
	
	public static InvocationHandle createHandle(List<IFilter> filters){
		InvocationHandle last = null;
//		List<IFilter> filterList = new ArrayList<IFilter>();
//		filterList.addAll(filters);
		for (int i = filters.size() - 1; i >= 0; i--) {
			final IFilter filter = filters.get(i);
			final InvocationHandle next = last;
			last = new InvocationHandle() {

				@Override
				public void handle(String str) {
					filter.filter(next, str);					
				}
			};
		}
		return last;
	}
}
