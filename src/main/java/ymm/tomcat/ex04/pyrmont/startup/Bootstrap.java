package ymm.tomcat.ex04.pyrmont.startup;

/* explains Tomcat's default container */

import ymm.tomcat.ex04.pyrmont.core.SimpleContainer;
import ymm.tomcat.org.apache.catalina.connector.http.HttpConnector;

public final class Bootstrap {
  public static void main(String[] args) {
    HttpConnector connector = new HttpConnector();
    SimpleContainer container = new SimpleContainer();
    connector.setContainer(container);
    try {
      connector.initialize();
      connector.start();

      // make the application wait until we press any key.
      System.in.read();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}