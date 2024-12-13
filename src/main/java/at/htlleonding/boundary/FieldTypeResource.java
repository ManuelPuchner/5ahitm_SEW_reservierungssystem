package at.htlleonding.boundary;

import at.htlleonding.model.FieldType;
import at.htlleonding.repository.FieldTypeRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.net.URL;
import java.util.List;

@Path("api/fieldtypes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FieldTypeResource {

    @Inject
    FieldTypeRepository fieldTypeRepository;

    @Inject
    UriInfo uriInfo;

    @GET
    @Path("health")
    @Produces(MediaType.TEXT_PLAIN)
    public String healthCheck() {
        return "FieldTypeResource is alive!";
    }


    @GET
    @Path("list")
    public List<FieldType> listFieldTypes() {
        return fieldTypeRepository.listAll();
    }


    @POST
    @Transactional
    public Response createFieldType(FieldType fieldType) {
        fieldTypeRepository.persist(fieldType);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(Long.toString(fieldType.getId()))
                .build();
        return Response.created(uri).entity(fieldType).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteFieldTypeById(@PathParam("id") Long id) {
        fieldTypeRepository.deleteById(id);
        return Response.ok().build();
    }

}
