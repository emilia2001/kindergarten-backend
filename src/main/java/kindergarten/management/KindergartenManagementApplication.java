package kindergarten.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="kindergarten.management")
@ComponentScan(basePackages = "kindergarten.management")
public class KindergartenManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(KindergartenManagementApplication.class, args);
	}

}
