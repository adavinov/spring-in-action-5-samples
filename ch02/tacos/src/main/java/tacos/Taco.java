// tag::all[]
// tag::allButValidation[]
package tacos;

import java.util.List;
// end::allButValidation[]
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Taco {

	// end::allButValidation[]
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	// tag::allButValidation[]
	private String name;
	// end::allButValidation[]
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	// tag::allButValidation[]
	private List<String> ingredients;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Taco [name=");
		builder.append(name);
		builder.append(", ingredients=");
		builder.append(ingredients);
		builder.append("]");
		return builder.toString();
	}

}
//end::allButValidation[]
//tag::end[]