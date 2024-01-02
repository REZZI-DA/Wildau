// package wildau.web.live.chat.config;

// import java.io.IOException;

// import org.springframework.stereotype.Component;

// import jakarta.servlet.Filter;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.ServletRequest;
// import jakarta.servlet.ServletResponse;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class CorsFilter implements Filter {

//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//             throws IOException, ServletException {
//         HttpServletResponse httpResponse = (HttpServletResponse) response;
//         httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8080"); // Hier die Adresse deines Frontend-Servers
//         httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//         httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//         httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//         chain.doFilter(request, response);
//     }

//     // Implementierung der anderen Filter-Methoden (falls benötigt)
// }