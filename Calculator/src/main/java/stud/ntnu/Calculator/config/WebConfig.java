package stud.ntnu.Calculator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "http://localhost:5174", "http://localhost:5175") // Inkluderer den nye originen
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Tillater spesifikke HTTP-metoder
                .allowedHeaders("*") // Tillater alle headers
                .allowCredentials(true); // Tillater credentials
    }
}