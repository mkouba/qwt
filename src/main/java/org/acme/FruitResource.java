package org.acme;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    @GET
    public Uni<List<Fruit>> list() {
        return Fruit.listAll();
    }

    @POST
    public Uni<List<Fruit>> add(Fruit fruit) {
        return Panache.withTransaction(fruit::persist).replaceWith(Fruit.listAll());
    }

    @DELETE
    public Uni<List<Fruit>> delete(Fruit fruit) {
        return Panache.withTransaction(() -> Fruit.delete("name", fruit.name)).replaceWith(Fruit.listAll());
    }
}
