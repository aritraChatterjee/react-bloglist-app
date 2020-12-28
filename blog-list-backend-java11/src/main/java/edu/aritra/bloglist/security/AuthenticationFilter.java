package edu.aritra.bloglist.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        logger.info("Inside Authentication Filter");
        logger.info("request path: {}, method: {}", httpServletRequest.getRequestURI(), httpServletRequest.getMethod());

        if (!isOpenUrl(httpServletRequest)) {
            String header = httpServletRequest.getHeader("Authorization");

            if (header == null || !(header.startsWith("Bearer ") || header.startsWith("bearer "))) {
                logger.info("Authorization header absent");
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private boolean isOpenUrl(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURI().equals("/api/login") || httpServletRequest.getMethod().equals("GET");
    }
}
