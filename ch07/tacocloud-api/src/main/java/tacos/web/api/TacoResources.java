package tacos.web.api;

import java.util.List;

import org.springframework.hateoas.Resources;

public class TacoResources extends Resources<TacoResource> {
    public TacoResources(final List<TacoResource> tacoResources) {
        super(tacoResources);
    }
}
