package tacos.web.api;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import tacos.Taco;

@Component
public class TacoResourcesProcessor implements ResourceProcessor<PagedResources<Resource<Taco>>> {

    private final EntityLinks entityLinks;

    public TacoResourcesProcessor(final EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public PagedResources<Resource<Taco>> process(final PagedResources<Resource<Taco>> resources) {
        resources.add(entityLinks.linkFor(Taco.class).slash("recent").withRel("recents"));

        return resources;
    }

}
