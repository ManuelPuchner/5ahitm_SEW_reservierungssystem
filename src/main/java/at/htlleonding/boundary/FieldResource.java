package at.htlleonding.boundary;

import at.htlleonding.model.Field;
import at.htlleonding.repository.FieldRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Path("api/fields")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FieldResource {

    @Inject
    FieldRepository fieldRepository;

    @Inject
    EntityManager entityManager;

    @Inject
    UriInfo uriInfo;

    @GET
    @Path("health")
    @Produces(MediaType.TEXT_PLAIN)
    public String healthCheck() {
        return "FieldResource is alive!";
    }


    @POST
    @Transactional
    public Response createField(Field field) {
        fieldRepository.persistAndFlush(field);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(Long.toString(field.getId()))
                .build();
        entityManager.refresh(field);
        return Response.created(uri).entity(field).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteField(@PathParam("id") Long id) {
        fieldRepository.deleteById(id);
        return Response.ok().build();
    }

    @GET
    @Path("/list")
    public List<Field> listFields() {
        return fieldRepository.listAll();
    }
}
