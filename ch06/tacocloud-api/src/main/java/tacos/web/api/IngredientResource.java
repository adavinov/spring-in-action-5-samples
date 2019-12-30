package tacos.web.api;

import org.springframework.hateoas.ResourceSupport;

import tacos.Ingredient;
import tacos.Ingredient.Type;

public class IngredientResource extends ResourceSupport {

	private String name;

	private Type type;

	public IngredientResource(Ingredient ingredient) {
		this.name = ingredient.getName();
		this.type = ingredient.getType();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
