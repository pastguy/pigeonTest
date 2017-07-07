package ymm.tomcat.ex05.pyrmont.startup;

import ymm.tomcat.ex05.pyrmont.core.SimpleLoader;
import ymm.tomcat.ex05.pyrmont.core.SimpleWrapper;
import ymm.tomcat.ex05.pyrmont.valves.ClientIPLoggerValve;
import ymm.tomcat.ex05.pyrmont.valves.HeaderLoggerValve;
import ymm.tomcat.org.apache.catalina.Loader;
import ymm.tomcat.org.apache.catalina.Pipeline;
import ymm.tomcat.org.apache.catalina.Valve;
import ymm.tomcat.org.apache.catalina.Wrapper;
import ymm.tomcat.org.apache.catalina.connector.http.HttpConnector;

public final class Bootstrap1 {
  public static void main(String[] args) {

/* call by using http://localhost:8080/ModernServlet,
   but could be invoked by any name */

    HttpConnector connector = new HttpConnector();
    Wrapper wrapper = new SimpleWrapper();
    wrapper.setServletClass("ModernServlet");
    Loader loader = new SimpleLoader();
    Valve valve1 = new HeaderLoggerValve();
    Valve valve2 = new ClientIPLoggerValve();

    wrapper.setLoader(loader);
    ((Pipeline) wrapper).addValve(valve1);
    ((Pipeline) wrapper).addValve(valve2);

    connector.setContainer(wrapper);

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