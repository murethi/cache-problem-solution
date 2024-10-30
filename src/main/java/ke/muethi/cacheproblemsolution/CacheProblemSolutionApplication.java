package ke.muethi.cacheproblemsolution;

import ke.muethi.cacheproblemsolution.service.CacheService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CacheProblemSolutionApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheProblemSolutionApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CacheService cacheService) throws Exception{

        return args -> {
            cacheService.put("user1","Brian Murethi",2);
            System.out.println(String.format("User1 before 2 seconds: %s", cacheService.get("user1")));
            Thread.sleep(1);
            System.out.println(String.format("User1 after 2 seconds: %s", cacheService.get("user1")));
        };
    }

}
