package tacos.ingredientclient.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("feign")
//@Slf4j
@EnableFeignClients
public class FeignClientConfig {
	static Logger log = LoggerFactory.getLogger(FeignClientConfig.class);
  @Bean
  public CommandLineRunner startup() {
    return args -> {
      log.info("**************************************");
      log.info("        Configuring with Feign");
      log.info("**************************************");
    };
  }
  
}
