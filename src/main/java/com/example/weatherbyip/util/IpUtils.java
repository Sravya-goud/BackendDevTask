package com.example.weatherbyip.util;

import jakarta.servlet.http.HttpServletRequest;

public class IpUtils {

	public static String extractClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0]; // Take the first IP in case of proxies
        }
        return request.getRemoteAddr();
    }
}
