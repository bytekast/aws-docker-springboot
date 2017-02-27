package com.bytekast.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SSLSecurityRequestWrapper extends HttpServletRequestWrapper {
  public SSLSecurityRequestWrapper( HttpServletRequest request ) {
    super( request );
  }

  public boolean isSecure() {
    return true; // secured using a load-balancer, let container know
  }

  public String getScheme() {
    return "https"; // secured using a load-balancer, let container know
  }
}
