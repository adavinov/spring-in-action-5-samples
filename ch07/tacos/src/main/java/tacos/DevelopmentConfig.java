package tacos;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(final IngredientRepository repo, final UserRepository userRepo, final PasswordEncoder encoder,
            final TacoRepository tacoRepo) { // user repo for ease of testing with a built-in user
        return new CommandLineRunner() {
            @Override
            public void run(final String... args) throws Exception {
                final Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
                final Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
                final Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
                final Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
                final Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
                final Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
                final Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
                final Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
                final Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
                final Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
                repo.save(flourTortilla);
                repo.save(cornTortilla);
                repo.save(groundBeef);
                repo.save(carnitas);
                repo.save(tomatoes);
                repo.save(lettuce);
                repo.save(cheddar);
                repo.save(jack);
                repo.save(salsa);
                repo.save(sourCream);

                userRepo.save(new User("habuma", encoder.encode("password"), "Craig Walls", "123 North Street",
                        "Cross Roads", "TX", "76227", "123-123-1234"));

                final Taco taco1 = new Taco();
                taco1.setName("Carnivore");
                taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
                tacoRepo.save(taco1);

                final Taco taco2 = new Taco();
                taco2.setName("Bovine Bounty");
                taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
                tacoRepo.save(taco2);

                final Taco taco3 = new Taco();
                taco3.setName("Veg-Out");
                taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
                tacoRepo.save(taco3);

            }
        };
    }

}
