package com.example.billingSystespringboot.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class TrackingIdFilter implements Filter {

    private static final String TRACKING_ID_KEY = "trackingId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            String trackingId = UUID.randomUUID().toString();
            MDC.put(TRACKING_ID_KEY, trackingId);

            chain.doFilter(request, response);
        } finally {
            MDC.remove(TRACKING_ID_KEY); // Clean up after request is done
        }
    }
}
