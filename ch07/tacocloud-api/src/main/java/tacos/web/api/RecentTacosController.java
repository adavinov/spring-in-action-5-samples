package tacos.web.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import tacos.Taco;
import tacos.data.TacoRepository;

@RepositoryRestController
public class RecentTacosController {

    private final TacoRepository tacoRepo;

    public RecentTacosController(final TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public ResponseEntity<Resources<TacoResource>> recentTacos() {
        final PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        final List<Taco> tacos = tacoRepo.findAll(page).getContent();

        final List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
        final Resources<TacoResource> recentResources = new Resources<>(tacoResources);

        recentResources.add(linkTo(methodOn(RecentTacosController.class).recentTacos()).withRel("recents"));
        return new ResponseEntity<>(recentResources, HttpStatus.OK);
    }

}
