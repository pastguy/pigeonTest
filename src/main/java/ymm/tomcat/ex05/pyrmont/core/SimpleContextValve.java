package ymm.tomcat.ex05.pyrmont.core;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ymm.tomcat.org.apache.catalina.Context;
import ymm.tomcat.org.apache.catalina.Contained;
import ymm.tomcat.org.apache.catalina.Container;
import ymm.tomcat.org.apache.catalina.HttpRequest;
import ymm.tomcat.org.apache.catalina.Request;
import ymm.tomcat.org.apache.catalina.Response;
import ymm.tomcat.org.apache.catalina.Valve;
import ymm.tomcat.org.apache.catalina.ValveContext;
import ymm.tomcat.org.apache.catalina.Wrapper;


public class SimpleContextValve implements Valve, Contained {

  protected Container container;

  public void invoke(Request request, Response response, ValveContext valveContext)
    throws IOException, ServletException {
    // Validate the request and response object types
    if (!(request.getRequest() instanceof HttpServletRequest) ||
      !(response.getResponse() instanceof HttpServletResponse)) {
      return;     // NOTE - Not much else we can do generically
    }

    // Disallow any direct access to resources under WEB-INF or META-INF
    HttpServletRequest hreq = (HttpServletRequest) request.getRequest();
    String contextPath = hreq.getContextPath();
    String requestURI = ((HttpRequest) request).getDecodedRequestURI();
    String relativeURI =
      requestURI.substring(contextPath.length()).toUpperCase();

    Context context = (Context) getContainer();
    // Select the Wrapper to be used for this Request
    Wrapper wrapper = null;
    try {
      wrapper = (Wrapper) context.map(request, true);
    }
    catch (IllegalArgumentException e) {
      badRequest(requestURI, (HttpServletResponse) response.getResponse());
      return;
    }
    if (wrapper == null) {
      notFound(requestURI, (HttpServletResponse) response.getResponse());
      return;
    }
    // Ask this Wrapper to process this Request
    response.setContext(context);
    wrapper.invoke(request, response);
  }

  public String getInfo() {
    return null;
  }

  public Container getContainer() {
    return container;
  }

  public void setContainer(Container container) {
    this.container = container;
  }

  private void badRequest(String requestURI, HttpServletResponse response) {
    try {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, requestURI);
    }
    catch (IllegalStateException e) {
      ;
    }
    catch (IOException e) {
      ;
    }
  }

  private void notFound(String requestURI, HttpServletResponse response) {
    try {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, requestURI);
    }
    catch (IllegalStateException e) {
      ;
    }
    catch (IOException e) {
      ;
    }
  }

}