package org.durcit.be.log.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class CachedBodyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            if (httpRequest.getContentType() != null && httpRequest.getContentType().startsWith("multipart/form-data")) {
                chain.doFilter(request, response);
                return;
            }
            HttpServletRequest cachedRequest = new CachedBodyHttpServletRequest(httpRequest);
            chain.doFilter(cachedRequest, response);
        } else {
            chain.doFilter(request, response);
        }
    }

}