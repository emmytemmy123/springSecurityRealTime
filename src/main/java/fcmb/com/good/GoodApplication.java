package fcmb.com.good;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class GoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodApplication.class, args);
	}
@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
              
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("HEAD",
                                "GET", "POST", "PUT", "DELETE", "PATCH")
                        .allowedHeaders("Access-Control-Request-Method",
                                "Access-Control-Request-Headers", "Authorization", "Access-Control-Allow-Origin", "Cache-Control", "Content-Type");
            }
        };
    }
}
