package at.htlleonding.boundary;

import at.htlleonding.model.Customer;
import at.htlleonding.repository.CustomerRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Path("api/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    UriInfo uriInfo;

    @GET
    @Path("health")
    @Produces(MediaType.TEXT_PLAIN)
    public String healthCheck() {
        return "CustomerResource is alive!";
    }

    @POST
    @Transactional
    public Response createCustomer(Customer customer) {
        customerRepository.persist(customer);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(Long.toString(customer.getId()))
                .build();
        return Response.created(uri).entity(customer).build();
    }

    @GET
    @Path("{id}")
    public Customer getCustomerById(@PathParam("id") Long id) {
        return customerRepository.findById(id);
    }

    @PUT
    @Transactional
    public Response updateCustomer(Customer customer) {
        customerRepository.update(customer);
        return Response.ok().entity(customer).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
        customerRepository.deleteById(id);
        return Response.noContent().build();
    }

    @GET
    @Path("list")
    public List<Customer> listCustomers() {
        return customerRepository.listAll();
    }

}
