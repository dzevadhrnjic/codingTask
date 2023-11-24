package com.zira.codingtask.config;

import com.zira.codingtask.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    private final List<String> secureUrls = Arrays.asList(
            "/api/product",
            "/api/product/closest/",
            "/api/category"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!secureUrls.contains(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");

        try {
            TokenUtil.verifyJwt(token);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(401);
        }
    }

    private boolean shouldExec(String url) {
        return secureUrls.contains(url);
    }
}
