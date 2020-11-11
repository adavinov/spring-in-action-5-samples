package tacos.web.api;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import tacos.Taco;

@Relation(value = "taco", collectionRelation = "tacos")
public class TacoResource extends ResourceSupport {

    private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();

    private final String name;

    private final Date createdAt;

    private final List<IngredientResource> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientAssembler.toResources(taco.getIngredients());
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<IngredientResource> getIngredients() {
        return ingredients;
    }

}
