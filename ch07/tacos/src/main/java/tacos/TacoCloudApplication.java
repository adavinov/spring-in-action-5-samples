package tacos;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    // To avoid 404s when using Angular HTML 5 routing
    @Bean
    ErrorViewResolver supportPathBasedLocationStrategyWithoutHashes() {
        return new ErrorViewResolver() {
            @Override
            public ModelAndView resolveErrorView(final HttpServletRequest request, final HttpStatus status,
                    final Map<String, Object> model) {
                return status == HttpStatus.NOT_FOUND
                        ? new ModelAndView("index.html", Collections.<String, Object>emptyMap(), HttpStatus.OK)
                        : null;
            }
        };
    }

}
