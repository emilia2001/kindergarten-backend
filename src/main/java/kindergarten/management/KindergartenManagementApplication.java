package kindergarten.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories(basePackages="kindergarten.management")
public class KindergartenManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(KindergartenManagementApplication.class, args);
	}

}
