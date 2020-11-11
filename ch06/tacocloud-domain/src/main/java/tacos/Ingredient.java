package tacos;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ingredient {

    @Id
    private String id;
    private String name;
    private Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    public Ingredient() {

    }

    public Ingredient(String id, String name, Type type) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

}
