package tacos.ingredientclient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@Data
//@RequiredArgsConstructor
//@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Ingredient {

  private final String id;
  private final String name;
  private final Type type;

  public static enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
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