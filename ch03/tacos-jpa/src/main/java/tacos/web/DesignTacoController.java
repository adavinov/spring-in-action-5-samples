package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

//tag::injectingDesignRepository[]
//tag::injectingIngredientRepository[]
@Controller
@RequestMapping("/design")
//end::injectingIngredientRepository[]
@SessionAttributes("order")
//tag::injectingIngredientRepository[]
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    // end::injectingIngredientRepository[]
    private final TacoRepository tacoRepo;

    // end::injectingDesignRepository[]
    /*
     * //tag::injectingIngredientRepository[] public DesignTacoController(IngredientRepository ingredientRepo) {
     * this.ingredientRepo = ingredientRepo; } //end::injectingIngredientRepository[]
     */
    // tag::injectingDesignRepository[]

    @Autowired
    public DesignTacoController(final IngredientRepository ingredientRepo, final TacoRepository tacoRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Taco design() {
        return new Taco();
    }

    // end::injectingDesignRepository[]

    // tag::injectingIngredientRepository[]

    @GetMapping
    public String showDesignForm(final Model model) {
        final List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        final Type[] types = Ingredient.Type.values();
        for (final Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        return "design";
    }
    // end::injectingIngredientRepository[]

    //tag::injectingDesignRepository[]
    @PostMapping
    public String processDesign(@Valid final Taco taco, final Errors errors, @ModelAttribute final Order order) {

        if (errors.hasErrors()) {
            return "design";
        }

        final Taco saved = tacoRepo.save(taco);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    //end::injectingDesignRepository[]

    private List<Ingredient> filterByType(final List<Ingredient> ingredients, final Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    /*
     * //tag::injectingDesignRepository[] //tag::injectingIngredientRepository[]
     *
     * ... //end::injectingIngredientRepository[] //end::injectingDesignRepository[]
     */

    //tag::injectingDesignRepository[]
    //tag::injectingIngredientRepository[]

}
//end::injectingIngredientRepository[]
//end::injectingDesignRepository[]
