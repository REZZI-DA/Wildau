//  package wildau.web.live.chat.config;

//  import org.springframework.context.annotation.Configuration;
//  import org.springframework.web.servlet.config.annotation.CorsRegistry;
//  import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//  @Configuration
//  public class CorsConfig implements WebMvcConfigurer {

//      @Override
//      public void addCorsMappings(CorsRegistry registry) {
//          registry.addMapping("/users/**")
//                  .allowedOrigins("http://localhost:8080") // Hier die Adresse deines Frontend-Servers
//                  .allowedMethods("GET", "POST", "PUT", "DELETE")
//                  .allowCredentials(true);
//      }
//  }