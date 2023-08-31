package ac.prg381.student_portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("home");
    registry.addViewController("/home").setViewName("home");
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/student").setViewName("student");
    registry.addViewController("/admin").setViewName("admin");
  }

}