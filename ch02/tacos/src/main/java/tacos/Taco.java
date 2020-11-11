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

    public Taco() {

    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(final List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Taco [name=");
        builder.append(name);
        builder.append(", ingredients=");
        builder.append(ingredients);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((ingredients == null) ? 0 : ingredients.hashCode());
        result = (prime * result) + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Taco other = (Taco) obj;
        if (ingredients == null) {
            if (other.ingredients != null) {
                return false;
            }
        } else if (!ingredients.equals(other.ingredients)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
//end::allButValidation[]
//tag::end[]
