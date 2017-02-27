package com.bytekast.filter;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForceSSLFilter implements Filter {

  public static final String X_FORWARDED_PROTO = "x-forwarded-proto";

  @Override
  public void init( FilterConfig filterConfig ) throws ServletException {
    // do nothing
  }

  @Override
  public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain )
    throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    // Force SSL Redirect on Load Balancer
    if ( request.getHeader( X_FORWARDED_PROTO ) != null ) {
      if ( request.getHeader( X_FORWARDED_PROTO ).indexOf( "https" ) != 0 ) {
        String url = StringUtils.replace( request.getRequestURL() + "", "http", "https" );
        response.sendRedirect( url );
        return;
      }
    }

    SSLSecurityRequestWrapper requestWrapper = new SSLSecurityRequestWrapper( request );
    filterChain.doFilter( requestWrapper, servletResponse );
  }

  @Override
  public void destroy() {
    // do nothing
  }
}
