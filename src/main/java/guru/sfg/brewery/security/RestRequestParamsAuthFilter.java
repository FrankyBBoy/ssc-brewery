package guru.sfg.brewery.security;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class RestRequestParamsAuthFilter extends AbstractRestAuthFilter {

  public RestRequestParamsAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
    super(requiresAuthenticationRequestMatcher);
  }

  protected String getPassword(HttpServletRequest request) {
    return request.getParameter("apiSecret");
  }

  protected String getUsername(HttpServletRequest request) {
    return request.getParameter("apiKey");
  }
}
