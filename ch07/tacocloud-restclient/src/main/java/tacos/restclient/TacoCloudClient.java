package tacos.restclient;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tacos.Ingredient;
import tacos.Taco;

@Service
public class TacoCloudClient {
    static Logger log = LoggerFactory.getLogger(TacoCloudClient.class);
    private final RestTemplate rest;
    private final Traverson traverson;

    public TacoCloudClient(final RestTemplate rest, final Traverson traverson) {
        this.rest = rest;
        this.traverson = traverson;
    }

    //
    // GET examples
    //

    /*
     * Specify parameter as varargs argument
     */
    public Ingredient getIngredientById(final String ingredientId) {
        return rest.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
    }

    /*
     * Alternate implementations... The next three methods are alternative implementations of getIngredientById() as shown
     * in chapter 6. If you'd like to try any of them out, comment out the previous method and uncomment the variant you
     * want to use.
     */

    /*
     * Specify parameters with a map
     */
    // public Ingredient getIngredientById(String ingredientId) {
    // Map<String, String> urlVariables = new HashMap<>();
    // urlVariables.put("id", ingredientId);
    // return rest.getForObject("http://localhost:8080/ingredients/{id}",
    // Ingredient.class, urlVariables);
    // }

    /*
     * Request with URI instead of String
     */
    // public Ingredient getIngredientById(String ingredientId) {
    // Map<String, String> urlVariables = new HashMap<>();
    // urlVariables.put("id", ingredientId);
    // URI url = UriComponentsBuilder
    // .fromHttpUrl("http://localhost:8080/ingredients/{id}")
    // .build(urlVariables);
    // return rest.getForObject(url, Ingredient.class);
    // }

    /*
     * Use getForEntity() instead of getForObject()
     */
    // public Ingredient getIngredientById(String ingredientId) {
    // ResponseEntity<Ingredient> responseEntity =
    // rest.getForEntity("http://localhost:8080/ingredients/{id}",
    // Ingredient.class, ingredientId);
    // log.info("Fetched time: " +
    // responseEntity.getHeaders().getDate());
    // return responseEntity.getBody();
    // }

    public List<Ingredient> getAllIngredients() {
        return rest.exchange("http://localhost:8080/ingredients", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Ingredient>>() {
                }).getBody();
    }

    //
    // PUT examples
    //

    public void updateIngredient(final Ingredient ingredient) {
        rest.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
    }

    //
    // POST examples
    //
    public Ingredient createIngredient(final Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients", ingredient, Ingredient.class);
    }

    /*
     * Alternate implementations... The next two methods are alternative implementations of createIngredient() as shown in
     * chapter 6. If you'd like to try any of them out, comment out the previous method and uncomment the variant you want
     * to use.
     */

    // public URI createIngredient(Ingredient ingredient) {
    // return rest.postForLocation("http://localhost:8080/ingredients",
    // ingredient, Ingredient.class);
    // }

    // public Ingredient createIngredient(Ingredient ingredient) {
    // ResponseEntity<Ingredient> responseEntity =
    // rest.postForEntity("http://localhost:8080/ingredients",
    // ingredient,
    // Ingredient.class);
    // log.info("New resource created at " +
    // responseEntity.getHeaders().getLocation());
    // return responseEntity.getBody();
    // }

    //
    // DELETE examples
    //

    public void deleteIngredient(final Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
    }

    //
    // Traverson with RestTemplate examples
    //

    public Iterable<Ingredient> getAllIngredientsWithTraverson() {
        final ParameterizedTypeReference<Resources<Ingredient>> ingredientType = new ParameterizedTypeReference<Resources<Ingredient>>() {
        };

        final Resources<Ingredient> ingredientRes = traverson.follow("ingredients").toObject(ingredientType);

        final Collection<Ingredient> ingredients = ingredientRes.getContent();

        return ingredients;
    }

    public Ingredient addIngredient(final Ingredient ingredient) {
        final String ingredientsUrl = traverson.follow("ingredients").asLink().getHref();
        return rest.postForObject(ingredientsUrl, ingredient, Ingredient.class);
    }

    public Iterable<Taco> getRecentTacosWithTraverson() {
        final ParameterizedTypeReference<Resources<Taco>> tacoType = new ParameterizedTypeReference<Resources<Taco>>() {
        };

        final Resources<Taco> tacoRes = traverson.follow("tacos").follow("recents").toObject(tacoType);

        // Alternatively, list the two paths in the same call to follow()
        //    Resources<Taco> tacoRes =
        //        traverson
        //          .follow("tacos", "recents")
        //          .toObject(tacoType);

        return tacoRes.getContent();
    }

}
