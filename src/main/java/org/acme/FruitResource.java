package org.acme;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {
    
    @GET
    public List<Fruit> list() {
        return Fruit.listAll();
    }

    @Transactional
    @POST
    public List<Fruit> add(Fruit fruit) {
        fruit.persist();
        return Fruit.listAll();
    }

    @Transactional
    @DELETE
    public List<Fruit> delete(Fruit fruit) {
        Fruit.delete("name", fruit.name);
        return Fruit.listAll();
    }
}
