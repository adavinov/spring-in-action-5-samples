package tacos.restclient;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;

import tacos.Ingredient;
import tacos.Taco;

@SpringBootConfiguration
@ComponentScan
public class RestExamples {

    static Logger log = LoggerFactory.getLogger(RestExamples.class);

    public static void main(final String[] args) {
        SpringApplication.run(RestExamples.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner fetchIngredients(final TacoCloudClient tacoCloudClient) {
        return args -> {
            log.info("----------------------- GET -------------------------");
            log.info("GETTING INGREDIENT BY IDE");
            log.info("Ingredient:  " + tacoCloudClient.getIngredientById("CHED"));
            log.info("GETTING ALL INGREDIENTS");
            final List<Ingredient> ingredients = tacoCloudClient.getAllIngredients();
            log.info("All ingredients:");
            for (final Ingredient ingredient : ingredients) {
                log.info("   - " + ingredient);
            }
        };
    }

    @Bean
    public CommandLineRunner putAnIngredient(final TacoCloudClient tacoCloudClient) {
        return args -> {
            log.info("----------------------- PUT -------------------------");
            final Ingredient before = tacoCloudClient.getIngredientById("LETC");
            log.info("BEFORE:  " + before);
            tacoCloudClient.updateIngredient(new Ingredient("LETC", "Shredded Lettuce", Ingredient.Type.VEGGIES));
            final Ingredient after = tacoCloudClient.getIngredientById("LETC");
            log.info("AFTER:  " + after);
        };
    }

    @Bean
    public CommandLineRunner addAnIngredient(final TacoCloudClient tacoCloudClient) {
        return args -> {
            log.info("----------------------- POST -------------------------");
            final Ingredient chix = new Ingredient("CHIX", "Shredded Chicken", Ingredient.Type.PROTEIN);
            final Ingredient chixAfter = tacoCloudClient.createIngredient(chix);
            log.info("AFTER=1:  " + chixAfter);
            //      Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
            //      URI uri = tacoCloudClient.createIngredient(beefFajita);
            //      log.info("AFTER-2:  " + uri);      
            //      Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
            //      Ingredient shrimpAfter = tacoCloudClient.createIngredient(shrimp);
            //      log.info("AFTER-3:  " + shrimpAfter);      
        };
    }

    @Bean
    public CommandLineRunner deleteAnIngredient(final TacoCloudClient tacoCloudClient) {
        return args -> {
            log.info("----------------------- DELETE -------------------------");
            // start by adding a few ingredients so that we can delete them later...
            final Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
            tacoCloudClient.createIngredient(beefFajita);
            final Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
            tacoCloudClient.createIngredient(shrimp);

            Ingredient before = tacoCloudClient.getIngredientById("CHIX");
            log.info("BEFORE:  " + before);
            tacoCloudClient.deleteIngredient(before);
            Ingredient after = tacoCloudClient.getIngredientById("CHIX");
            log.info("AFTER:  " + after);
            before = tacoCloudClient.getIngredientById("BFFJ");
            log.info("BEFORE:  " + before);
            tacoCloudClient.deleteIngredient(before);
            after = tacoCloudClient.getIngredientById("BFFJ");
            log.info("AFTER:  " + after);
            before = tacoCloudClient.getIngredientById("SHMP");
            log.info("BEFORE:  " + before);
            tacoCloudClient.deleteIngredient(before);
            after = tacoCloudClient.getIngredientById("SHMP");
            log.info("AFTER:  " + after);
        };
    }

    //
    // Traverson examples
    //

    @Bean
    public Traverson traverson() {
        final Traverson traverson = new Traverson(URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON);
        return traverson;
    }

    @Bean
    public CommandLineRunner traversonGetIngredients(final TacoCloudClient tacoCloudClient) {
        return args -> {
            final Iterable<Ingredient> ingredients = tacoCloudClient.getAllIngredientsWithTraverson();
            log.info("----------------------- GET INGREDIENTS WITH TRAVERSON -------------------------");
            for (final Ingredient ingredient : ingredients) {
                log.info("   -  " + ingredient);
            }
        };
    }

    @Bean
    public CommandLineRunner traversonSaveIngredient(final TacoCloudClient tacoCloudClient) {
        return args -> {
            final Ingredient pico = tacoCloudClient
                    .addIngredient(new Ingredient("PICO", "Pico de Gallo", Ingredient.Type.SAUCE));
            final List<Ingredient> allIngredients = tacoCloudClient.getAllIngredients();
            log.info("----------------------- ALL INGREDIENTS AFTER SAVING PICO -------------------------");
            for (final Ingredient ingredient : allIngredients) {
                log.info("   -  " + ingredient);
            }
            tacoCloudClient.deleteIngredient(pico);
        };
    }

    @Bean
    public CommandLineRunner traversonRecentTacos(final TacoCloudClient tacoCloudClient) {
        return args -> {
            final Iterable<Taco> recentTacos = tacoCloudClient.getRecentTacosWithTraverson();
            log.info("----------------------- GET RECENT TACOS WITH TRAVERSON -------------------------");
            for (final Taco taco : recentTacos) {
                log.info("   -  " + taco);
            }
        };
    }

}
