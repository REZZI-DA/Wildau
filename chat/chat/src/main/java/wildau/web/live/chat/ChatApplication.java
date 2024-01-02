package wildau.web.live.chat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ChatApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(ChatApplication.class, args);
		var dataSource =context.getBean("dataSource");
		System.out.println(dataSource);
	}

}
