package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication // <1>
public class TacoCloudApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        System.out.println("***************************");
        SpringApplication.run(TacoCloudApplication.class, args); // <2>
    }
    /*
     * @Override protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { String configLocation
     * = System.getProperty("global.appconf.dir"); // get the default config directory location // set // the // configpath
     * // of // this // application // instance // exclusively String configPath = configLocation + File.separator +
     * "springApplication" + File.separator + "application.yml"; logger.info("Configpath: " + configPath);
     * logger.info("Starting to run Spring boot app..."); if (configLocation != null && !configLocation.isEmpty()) {
     * Properties props = new Properties(); props.setProperty("spring.config.location", configPath); // set the config file
     * to use application.application().setDefaultProperties(props); } else {
     * logger.info("No global.appconf.dir property found, starting with default on classpath"); } return
     * application.sources(SpringApplication.class); }
     */

}
