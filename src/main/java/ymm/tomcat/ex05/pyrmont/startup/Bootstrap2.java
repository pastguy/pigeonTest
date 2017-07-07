package ymm.tomcat.ex05.pyrmont.startup;

import ymm.tomcat.ex05.pyrmont.core.SimpleContext;
import ymm.tomcat.ex05.pyrmont.core.SimpleContextMapper;
import ymm.tomcat.ex05.pyrmont.core.SimpleLoader;
import ymm.tomcat.ex05.pyrmont.core.SimpleWrapper;
import ymm.tomcat.ex05.pyrmont.valves.ClientIPLoggerValve;
import ymm.tomcat.ex05.pyrmont.valves.HeaderLoggerValve;
import ymm.tomcat.org.apache.catalina.Context;
import ymm.tomcat.org.apache.catalina.Loader;
import ymm.tomcat.org.apache.catalina.Mapper;
import ymm.tomcat.org.apache.catalina.Pipeline;
import ymm.tomcat.org.apache.catalina.Valve;
import ymm.tomcat.org.apache.catalina.Wrapper;
import ymm.tomcat.org.apache.catalina.connector.http.HttpConnector;

public final class Bootstrap2 {
  public static void main(String[] args) {
    HttpConnector connector = new HttpConnector();
    Wrapper wrapper1 = new SimpleWrapper();
    wrapper1.setName("Primitive");
    wrapper1.setServletClass("PrimitiveServlet");
    Wrapper wrapper2 = new SimpleWrapper();
    wrapper2.setName("Modern");
    wrapper2.setServletClass("ModernServlet");

    Context context = new SimpleContext();
    context.addChild(wrapper1);
    context.addChild(wrapper2);

    Valve valve1 = new HeaderLoggerValve();
    Valve valve2 = new ClientIPLoggerValve();

    ((Pipeline) context).addValve(valve1);
    ((Pipeline) context).addValve(valve2);

    Mapper mapper = new SimpleContextMapper();
    mapper.setProtocol("http");
    context.addMapper(mapper);
    Loader loader = new SimpleLoader();
    context.setLoader(loader);
    // context.addServletMapping(pattern, name);
    context.addServletMapping("/Primitive", "Primitive");
    context.addServletMapping("/Modern", "Modern");
    connector.setContainer(context);
    try {
      connector.initialize();
      connector.start();

      // make the application wait until we press a key.
      System.in.read();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}