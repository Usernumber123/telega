import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class Application  extends SpringBootServletInitializer {

    @Autowired
    private  UserRepository repository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}