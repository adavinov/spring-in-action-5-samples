package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // <1>
public class TacoCloudApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args); // <2>
    }

}
