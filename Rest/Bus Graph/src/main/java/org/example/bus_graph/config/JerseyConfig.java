package org.example.bus_graph.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class JerseyConfig extends ResourceConfig{


    @Bean
    public ServletRegistrationBean<ServletContainer> jerseyServlet() {
        // Initialize the Jersey Servlet container
        ServletContainer servletContainer = new ServletContainer();

        // Register the Jersey servlet for handling REST requests
        ServletRegistrationBean<ServletContainer> registrationBean = new ServletRegistrationBean<>(servletContainer, "/api/*");

        // Provide package locations for Jersey to scan for JAX-RS resources
        registrationBean.addInitParameter("jersey.config.server.provider.packages", "org.example.bus_graph");
        // Optionally enable or disable WADL (Web Application Description Language)
        registrationBean.addInitParameter("jersey.config.server.wadl.disableWadl", "false");

        return registrationBean;
    }
}