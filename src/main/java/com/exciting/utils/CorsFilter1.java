package com.exciting.utils;
//package com.exciting.utils;
////import org.springframework.web.filter.GenericFilterBean;
////import javax.servlet.*;
////import javax.servlet.http.HttpServletResponse;
////import java.io.IOException;
////
////public class CorsFilter1 extends GenericFilterBean {
////    @Override
////    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
////            throws IOException, ServletException {
////        HttpServletResponse httpResponse = (HttpServletResponse) response;
////        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8080/");
////        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
////        httpResponse.setHeader("Access-Control-Allow-Headers", "*");
////        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
////        chain.doFilter(request, response);
////    }
////}
////
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class CorsFilter1 implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*")
//                .allowCredentials(true)
//                .maxAge(3600);
//    }
//}
