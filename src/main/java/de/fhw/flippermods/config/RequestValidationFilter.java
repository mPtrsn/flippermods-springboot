package de.fhw.flippermods.config;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class RequestValidationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      FilterChain filterChain) throws ServletException, IOException {

    String sigUser = httpServletRequest.getHeader("flipper-signature");
    String sigPw = httpServletRequest.getHeader("flipper-auth");

    if (!isValid(sigUser, sigPw)) {
      throw new SecurityException();
    }

    String username = "";
    String passwordMD5 = "";

    // Create our Authentication and let Spring know about it
    Authentication auth = new UsernamePasswordAuthenticationToken(username,passwordMD5,new ArrayList<>());
    SecurityContextHolder.getContext().setAuthentication(auth);

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  private boolean isValid(String sigUser, String sigPw) {
    String username = "";
    String password = "";

    int uLength = sigUser.length();
    int pLength = sigPw.length();
    boolean u = sigUser.startsWith(String.valueOf((char) uLength % 3));
    boolean p = sigPw.startsWith(String.valueOf((char) pLength % 5));
    return u && p;

  }
}



