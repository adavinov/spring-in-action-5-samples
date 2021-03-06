package tacos.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import tacos.Taco;

public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource> {

    public TacoResourceAssembler() {
        super(DesignTacoController.class, TacoResource.class);
    }

    @Override
    protected TacoResource instantiateResource(final Taco taco) {
        return new TacoResource(taco);
    }

    @Override
    public TacoResource toResource(final Taco taco) {
        return createResourceWithId(taco.getId(), taco);
    }

}
