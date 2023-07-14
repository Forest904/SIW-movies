package it.uniroma3.siw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@SpringBootApplication
public class SiwMoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiwMoviesApplication.class, args);
	}

	@Configuration
	public class WebConfig implements WebMvcConfigurer {
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			String absolutePathToImages = Paths.get("src/main/upload").toAbsolutePath().toString();
			registry.addResourceHandler("/upload/**")
					.addResourceLocations("file:" + absolutePathToImages + "/")
					.setCachePeriod(0);
		}
	}

}
